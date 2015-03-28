/**
 * Nombre del Programa : ConsultarSolicitudXEstatusServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.documento.DictamenDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.RelacionDocumentoElementoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudMandamientoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudMandamientoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudMandamiento;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudXEstatusService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudAudienciaTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudPericialTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarSolicitudXEstatusServiceImpl
        implements
            ConsultarSolicitudXEstatusService {

    private final static Logger logger = Logger
            .getLogger(ConsultarSolicitudXEstatusServiceImpl.class);

    @Autowired
    private SolicitudDAO solicitudDato;
    @Autowired
    private SolicitudMandamientoDAO solicitudMandamientoDAO;
    @Autowired
    private RelacionDocumentoElementoDAO relDocElementosDao;
    @Autowired
    private SolicitudPericialDAO solicitudPericialDAO;
    
    @Autowired
    private SolicitudTranscricpionAudienciaDAO solicitudTranscricpionAudienciaDAO;
    
    @Autowired
    private DictamenDAO dictamenDao;
    @Autowired
    private DocumentoDAO docDao;
    @Autowired
    private DelitoDAO delitoDAO;
    @Autowired
    private InvolucradoDAO involucradoDAO;
    @Autowired
    private NumeroExpedienteDAO numExpDAO;
    
    @Override
    public List<SolicitudDTO> consultarSolicitudXEstatus(
            EstatusSolicitud estatusSolicitud, UsuarioDTO usuario,
            TiposSolicitudes tipoSolicitud) throws NSJPNegocioException {    	
    	
        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA CONSULTAR SOLICITUDES POR ESTATUS ****/");
            logger.debug("estatusSolicitud :: " + estatusSolicitud);
            logger.debug("tipoSolicitud :: " + tipoSolicitud);
            logger.debug("usuario :: " + usuario.getClaveUsuario());
        }
        if (estatusSolicitud == null)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        List<Solicitud> solicitudes = new ArrayList<Solicitud>();

        if (tipoSolicitud.equals(TiposSolicitudes.ASESORIA)
                || tipoSolicitud.equals(TiposSolicitudes.EVIDENCIA)) {
            logger.debug("/**** SOLICITUDES PERICIALES ****/");
            if (!usuario.isCoordinador()) {
                solicitudes = solicitudPericialDAO
                        .consultarSolicitudesPericialPorTipoEstatusYUsuario(
                                tipoSolicitud.getValorId(),
                                estatusSolicitud.getValorId(),
                                usuario.getIdUsuario());
            } else {
                logger.debug("/**** EL FUNCIONARIO ES CORDINADOR ****/");
                solicitudes = solicitudDato.consultarSolicitudXEstatusYPuesto
                			(estatusSolicitud.getValorId(),tipoSolicitud.getValorId(),
                			usuario.getFuncionario().getPuesto().getIdCampo());                        
            }
        } else {
            solicitudes = solicitudDato.consultarSolicitudXEstatus(
                    estatusSolicitud.getValorId(), tipoSolicitud.getValorId());
        }

        SolicitudDTO sdto = null;
        SolicitudPericialDTO spdto = null;
        List<SolicitudDTO> solicitudesDTO = new ArrayList<SolicitudDTO>();
        for (Solicitud solicitud : solicitudes) {
            if (logger.isDebugEnabled()) {
                logger.debug("solicitud con id :: "
                        + solicitud.getDocumentoId() + " del tipo "
                        + solicitud.getTipoSolicitud().getValorId()
                        + " y con objeto del tipo " + solicitud);
            }
            if (solicitud instanceof SolicitudPericial) {
                spdto = SolicitudPericialTransformer
                        .solPericialTransformer((SolicitudPericial) solicitud);
                List<Elemento> eles = relDocElementosDao
                        .consultarElementosPorDocId(solicitud.getDocumentoId());
                for (Elemento el : eles) {
                    try
                    {
	                	if (el instanceof Objeto) {
	                        sdto.addElemento(ObjetoTransformer
	                                .tranformarComoEvidenciaBasico((Objeto) el));
                    }
                    }catch(Exception e)
                    {
                    	logger.debug(e.getStackTrace());
                    }
                }
                if (estatusSolicitud.equals(EstatusSolicitud.CERRADA)) {
                    Long dictamenId = this.dictamenDao
                            .existeDocumentoDefinitivo(solicitud
                                    .getDocumentoId());
                    spdto.setDictamenTerminado(dictamenId != null);
                    if (spdto.isDictamenTerminado()) {
                        Documento dic = this.docDao
                                .consultarDocumentoPorDocumentoIdLigero(dictamenId);
                        DictamenDTO dicDto = new DictamenDTO();
                        dicDto.setDocumentoId(dictamenId);
                        dicDto.setNombreDocumento(dic.getNombreDocumento());
                        dicDto.setTipoDocumentoDTO(new ValorDTO(dic
                                .getTipoDocumento().getValorId(), dic
                                .getTipoDocumento().getValor()));
                        spdto.addDictamen(dicDto);
                    }
                }
                solicitudesDTO.add(spdto);
            } else {
                sdto=SolicitudTransformer.solicitudTransformer(solicitud);
                List<Delito> delitos = delitoDAO.obtenerDelitoPorExpediente(sdto.getExpedienteDTO().getExpedienteId());
                List<DelitoDTO> delitosDTO=new ArrayList<DelitoDTO>();
                for (Delito del : delitos) {
					delitosDTO.add(DelitoTransfromer.transformarDelito(del));
				}
                List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(sdto.getExpedienteDTO().getExpedienteId());
                List<InvolucradoDTO> involucradosDTO=new ArrayList<InvolucradoDTO>();
                for (Involucrado inv : involucrados) {
                	involucradosDTO.add(InvolucradoTransformer.transformarInvolucrado(inv));
				}
                NumeroExpediente numeroExp = numExpDAO.obtenerNumeroExpedienteXExpediente(sdto.getExpedienteDTO().getExpedienteId());
                
                ExpedienteDTO expTemp = sdto.getExpedienteDTO();
                expTemp.setNumeroExpediente(numeroExp.getNumeroExpediente());
                expTemp.setNumeroExpedienteId(numeroExp.getNumeroExpedienteId());
				expTemp.setInvolucradosDTO(involucradosDTO);
				expTemp.setDelitos(delitosDTO);
                
                solicitudesDTO.add(sdto);
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("solicitudesDTO.size() :: " + solicitudesDTO.size());
        }

        return solicitudesDTO;
    }

    @Override
    public List<SolicitudTranscripcionAudienciaDTO> consultarSolicitudTranscripcion(
            UsuarioDTO usuario) throws NSJPNegocioException {
        List<SolicitudTranscripcionAudiencia> lista = solicitudTranscricpionAudienciaDAO.consultarSolicitudesTrascripcionAudienciaPendientes();
        List<SolicitudTranscripcionAudienciaDTO> solicitudes = new LinkedList<SolicitudTranscripcionAudienciaDTO>();
        for (SolicitudTranscripcionAudiencia solicitud : lista) {
            solicitudes.add(SolicitudAudienciaTransformer.transformarSolicitud(solicitud));
        }

        return solicitudes;
    }

    /**
     * Consulta las solicitudes que ordenan la Atencción de un Mandato Judicial
     * (Resolutivo)
     * 
     * @return
     * @throws NSJPNegocioException
     */

    public List<SolicitudMandamientoDTO> consultarSolicitudMandatoJudicial()
            throws NSJPNegocioException {
        List<SolicitudMandamiento> lista = new LinkedList<SolicitudMandamiento>();
        lista = solicitudMandamientoDAO
                .consultarSolicitudesMandamientoBy(EstatusSolicitud.ABIERTA);
        List<SolicitudMandamientoDTO> solicitudes = new LinkedList<SolicitudMandamientoDTO>();
        for (SolicitudMandamiento solicitud : lista) {
            solicitudes.add(SolicitudTransformer
                    .solicitudTransformer(solicitud));
        }

        return solicitudes;
    }

}

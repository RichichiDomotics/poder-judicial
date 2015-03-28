/**
* Nombre del Programa : ActualizarCarpetaDeInvestigacionServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28/07/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.expediente.ActualizarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.hecho.IngresarHechoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.objeto.IngresarObjetoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Definicion de servisios para actualizar la información de la carpeta de investigacion (Expediente)
 * que proviene de Procuraduria.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ActualizarCarpetaDeInvestigacionServiceImpl implements ActualizarCarpetaDeInvestigacionService {
    private final static Logger logger = Logger
    .getLogger(ActualizarCarpetaDeInvestigacionServiceImpl.class);
    
	@Autowired 
	private SolicitudDAO solicitudDAO;
	@Autowired  
	private IngresarIndividuoService ingresarIndividuoService;
	@Autowired  
	private GuardarDocumentoService guardarDocumentoService;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private IngresarObjetoService ingresarObjetoService;
	@Autowired
	private IngresarHechoService ingresarHechoService;
	@Autowired
	private RegistrarBitacoraService regBitService;
    @Autowired
    private DelitoDAO delitoDao;
    @Autowired
    private DelitoPersonaDAO delitoPerDao;
    @Autowired
    private InvolucradoDAO involucradoDAO;
	
	@Override
    public ExpedienteDTO consultarExpedientePorFolioSolicitud(
            String folioSolicitud) throws NSJPNegocioException {

        if (folioSolicitud == null || folioSolicitud.trim().isEmpty())
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        Solicitud solicitud = solicitudDAO
                .consultarSolicitudPorFolio(folioSolicitud);
        if (solicitud == null || solicitud.getDocumentoId() == null
                || solicitud.getDocumentoId() < 0)
            throw new NSJPNegocioException(
                    CodigoError.INFORMACION_PARAMETROS_ERRONEA);

        Expediente expediente = solicitudDAO
                .consultarExpedienteDeNumeroExpedienteSolicitud(solicitud
                        .getDocumentoId());

        ExpedienteDTO expedienteDTO = ExpedienteTransformer
                .transformaExpediente(expediente);
        expedienteDTO.setNumeroExpedienteId(solicitud.getNumeroExpediente()
                .getNumeroExpedienteId());
        return expedienteDTO;
    }
	
	@Override
    public Long actualizarExpedientePorFolioSolicitud(String folioSolicitud,
            EstatusExpediente estatus) throws NSJPNegocioException {
        if (folioSolicitud == null || folioSolicitud.trim().isEmpty())
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        Solicitud solicitud = solicitudDAO
                .consultarSolicitudPorFolio(folioSolicitud);
        if (solicitud == null || solicitud.getDocumentoId() == null
                || solicitud.getDocumentoId() < 0)
            throw new NSJPNegocioException(
                    CodigoError.INFORMACION_PARAMETROS_ERRONEA);
        logger.info("Numero de Expediente a actualizar "
                + solicitud.getNumeroExpediente().getNumeroExpediente());
        NumeroExpediente expediente = solicitud.getNumeroExpediente();
        expediente.setEstatus(new Valor(estatus.getValorId()));
        numeroExpedienteDAO.update(expediente);
        RegistroBitacora regBitEta = new RegistroBitacora();

        regBitEta.setFechaInicio(new Date());
        regBitEta.setTipoMovimiento(new Valor(
                TipoMovimiento.CAMBIO_DE_ESTATUS_DE_EXPEDIENTE.getValorId()));
        regBitEta.setNuevo(String.valueOf(estatus.getValorId()));
        regBitEta.setNumeroExpediente(expediente);
        regBitService.registrarMovimientoDeExpedienteEnBitacora(regBitEta);
        return expediente.getNumeroExpedienteId();

    }
	
    public ExpedienteDTO actualizarExpedienteDeCarpetaInvestigacionDefensoria(
            ExpedienteDTO expedienteDTO) throws NSJPNegocioException {

        logger.info(" *********** Servicio actualizarExpedienteDeCarpetaInvestigacionDefensoria***********");
        logger.info(" Expediente:" + expedienteDTO);

        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null
                || expedienteDTO.getExpedienteId() < 0)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

        logger.info(" Expediente ID:" + expedienteDTO.getExpedienteId());

        if (expedienteDTO.getObjetosDTO() != null
                && !expedienteDTO.getObjetosDTO().isEmpty()) {
            List<ObjetoDTO> objetosDTO = expedienteDTO.getObjetosDTO();
            for (ObjetoDTO objetoDTO : objetosDTO) {
                objetoDTO.setExpedienteDTO(new ExpedienteDTO(expedienteDTO
                        .getExpedienteId()));
                Long idObjeto = ingresarObjetoService
                        .ingresarObjetoCarpetaInvestigacion(objetoDTO);
                logger.info(" Se ingreso en objeto con id: " + idObjeto);
            }
        }

        // Ingresar involucrado con toda su dependencia
        if (expedienteDTO.getInvolucradosDTO() != null
                && !expedienteDTO.getInvolucradosDTO().isEmpty()) {
            for (InvolucradoDTO involucradoDTO : expedienteDTO
                    .getInvolucradosDTO()) {
                involucradoDTO.setExpedienteDTO(new ExpedienteDTO(expedienteDTO
                        .getExpedienteId()));
                
                final Involucrado involucrado = involucradoDAO
				.consultarInvolucradoPorFolioElemento(involucradoDTO
						.getFolioElemento());
                
                Long idIndividuo = ingresarIndividuoService
                        .ingresarIndividuoInterInstitucion(involucradoDTO, true);
                logger.info("Se ingreso un individuo con id: " + idIndividuo);
                
                
                //Si se encontro el involucrado es posible que tenga delitos relacionados
                if(involucrado!=null){
                	//Se debe de eliminar la informacion del delito y delitoPersona
                	this.eliminarDelitos(idIndividuo) ;
                }
                // guarda delito cometidos
                if (involucradoDTO.getDelitosCometidos() != null) {
                	logger.debug("Delitos a guardar para el involucrado :: " + idIndividuo);
                    for (DelitoDTO deli : involucradoDTO.getDelitosCometidos()) {
                    	Delito delitoPojo = new Delito();
                        delitoPojo.setCatDelito(new CatDelito(deli.getCatDelitoDTO().getCatDelitoId()));
                        logger.debug("deli.getIdCatDelito() :: " + deli.getCatDelitoDTO().getCatDelitoId());
                        delitoPojo.setEsProbable(Boolean.TRUE);
                        delitoPojo.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
                        delitoPojo.setEsPrincipal(deli.getEsPrincipal());
                        
                        Long idDelito = this.delitoDao.create(delitoPojo);
                        logger.info(" Delito creado:" + idDelito);
                        Delito delitoPojoBD = delitoDao.read(idDelito);
                        Involucrado involucrado2 = involucradoDAO.read(idIndividuo);
                        
                        DelitoPersona delPerPojo = new DelitoPersona();
                        delPerPojo.setDelito(delitoPojoBD);
                        delPerPojo.setEsActivo(Boolean.TRUE);
                        delPerPojo.setProbableResponsable(involucrado2);
                        Long idDelitoPersona = this.delitoPerDao.create(delPerPojo);
                        logger.info(" idDelitoPersona: "+ idDelitoPersona);
                    }
                } 
            }
        }
        // Hechos.. Utilizados en IPH
        if (logger.isDebugEnabled()) {
            logger.debug(" Se ingresará el Hecho (Lugar-Domicilio, Tiempo): "
                    + expedienteDTO.getHechoDTO());
        }
        if (expedienteDTO.getHechoDTO() != null) {
            HechoDTO hechoDTO = expedienteDTO.getHechoDTO();
            hechoDTO.setExpediente(new ExpedienteDTO(expedienteDTO
                    .getExpedienteId()));
            if (hechoDTO.getLugar() != null)
                hechoDTO.getLugar().setExpedienteDTO(
                        new ExpedienteDTO(expedienteDTO.getExpedienteId()));
            if (hechoDTO.getDomicilio() != null)
                hechoDTO.getDomicilio().setExpedienteDTO(
                        new ExpedienteDTO(expedienteDTO.getExpedienteId()));
            //Se agrega la fecha de arribo
            hechoDTO.setFechaDeArribo(expedienteDTO.getHechoDTO().getFechaDeArribo());
            ingresarHechoService.ingresarHecho(hechoDTO);
            logger.info(" Se ingreso el Hecho");
        }

        // Delitos
        if (logger.isDebugEnabled()) {
            logger.debug(" Se ingresará los delitos: "
                    + expedienteDTO.getDelitos());
        }
//        if (expedienteDTO.getDelitos() != null
//                && !expedienteDTO.getDelitos().isEmpty()) {
//            ExpedienteDTO expedienteTempDTO = new ExpedienteDTO(
//                    expedienteDTO.getExpedienteId());
//            guardarDelitoService.guardarDelito(expedienteDTO.getDelitos(),
//                    expedienteTempDTO);
//        }

        // Documentos y sus Archivos Digitales
        if (logger.isDebugEnabled()) {
            logger.debug(" Se ingresará los documentos: "
                    + expedienteDTO.getDocumentosDTO());
        }
        if (expedienteDTO.getDocumentosDTO() != null
                && !expedienteDTO.getDocumentosDTO().isEmpty()) {
            for (DocumentoDTO documentoDTO : expedienteDTO.getDocumentosDTO()) {
                Long idDocumento = guardarDocumentoService
                        .guardarDocumentoIntraInstitucion(documentoDTO,
                                expedienteDTO);
                logger.info(" Se ingreso el documento con id: " + idDocumento);
            }
        }

        return expedienteDTO;
    }
    
    public void eliminarDelitos(Long idIndividuo) throws NSJPNegocioException {
    	//Se debe de eliminar la informaci�n del delito y delitoPersona
        List<DelitoPersona> delitosPersona = delitoPerDao.consultarDelitoPerByInvolucrado(idIndividuo);
        for (DelitoPersona delitoPersona : delitosPersona) {
        	Long delitoId = delitoPersona.getDelito().getDelitoId();
        	delitoPerDao.delete(delitoPersona);
        	Delito delito = delitoDao.read(delitoId);
        	delitoDao.delete(delito);
		}
    }
    
}

/**
* Nombre del Programa : AsignarEvidenciaServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/07/2011
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
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.RelacionDocumentoElementoDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelacionDocumentoElemento;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.evidencia.AsignarEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para la asignación de evidencias
 * a peritos o solicitudes de pericial
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class AsignarEvidenciaServiceImpl implements AsignarEvidenciaService {
	Log logger;
	@Autowired
	SolicitudPericialDAO solicitudPericialDAO;
	@Autowired
	EvidenciaDAO evidenciaDAO;
	
	@Autowired
	RelacionDocumentoElementoDAO relacionDocDAO;
	@Autowired
	ElementoDAO elementoDAO;
    @Autowired
    GenerarFolioSolicitudService generarFolioSolicitudService;
    @Autowired
    NumeroExpedienteDAO numeroExpedienteDAO;
    
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.evidencia.AsignarEvidenciaService#asignarEvidenciaASolicitudPericial(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO, mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public void asignarEvidenciaASolicitudPericial(EvidenciaDTO evidencia,
			SolicitudPericialDTO solicitud,FuncionarioDTO perito) throws NSJPNegocioException {
		logger.debug("SOBRECARGADO");
		SolicitudPericial solicitudPericial = solicitudPericialDAO.read(solicitud.getDocumentoId());
		Evidencia evidenciaBD = evidenciaDAO.read(evidencia.getEvidenciaId()); 
		if(solicitudPericial != null && evidenciaBD != null){
			RelacionDocumentoElemento relacion = new RelacionDocumentoElemento();
			relacion.setElemento(evidenciaBD.getObjeto());
			relacion.setDocumento(solicitudPericial);
			relacion.setCatRelacion(new CatRelacion((long)Relaciones.EVIDENCIA_EN_SOLICITUD.ordinal()));
			for(SolicitudPericial solHija:solicitudPericial.getSolicitudesHijas()){
				if(solHija.getDestinatario()!=null && solHija.getDestinatario().getClaveFuncionario().longValue() ==
					perito.getClaveFuncionario()){
					solHija.getRelacionElementos().add(relacion);
					solicitudPericialDAO.saveOrUpdate(solHija);
					break;
				}
			}
			
			
		}
		
	}
	
	@Override
	public Long asignarEvidenciaASolicitudPericial(
			List<EvidenciaDTO> evidencias, SolicitudPericialDTO solicitud) throws NSJPNegocioException, Exception {
		SolicitudPericial solicitudPericial = new SolicitudPericial();
		SolicitudPericial solicitudPericialDest = new SolicitudPericial();
		
		if(solicitud.getDestinatario()!=null)
		{
			solicitudPericial.setFuncionarioSolicitante(FuncionarioTransformer.transformarFuncionario(solicitud.getUsuarioSolicitante()));
			solicitudPericialDest.setFuncionarioSolicitante(FuncionarioTransformer.transformarFuncionario(solicitud.getUsuarioSolicitante()));
			solicitudPericialDest.setDestinatario(FuncionarioTransformer.transformarFuncionario(solicitud.getDestinatario()));
			if (solicitud.getDestinatario().getJerarquiaOrganizacional() != null
					&& solicitud.getDestinatario().getJerarquiaOrganizacional()
							.getJerarquiaOrganizacionalId() != null){
				
				solicitudPericialDest.setAreaDestino(solicitud
						.getDestinatario().getJerarquiaOrganizacional()
						.getJerarquiaOrganizacionalId());
			}
				
		}
		if(solicitud.getUsuarioSolicitante()!=null)
			solicitudPericial.setFuncionarioSolicitante(FuncionarioTransformer.transformarFuncionario(solicitud.getUsuarioSolicitante()));
			
		if(solicitud.getEspecialidad()!=null)
		{
			solicitudPericial.setEspecialidad(new Valor(solicitud.getEspecialidad().getIdCampo()));
			solicitudPericialDest.setEspecialidad(new Valor(solicitud.getEspecialidad().getIdCampo()));
		}
		
		solicitudPericial.setFechaCreacion(new Date());
		solicitudPericialDest.setFechaCreacion(new Date());
		if(solicitud.getFechaLimite()!=null)
		{
			solicitudPericial.setFechaLimite(solicitud.getFechaLimite());
			solicitudPericialDest.setFechaLimite(solicitud.getFechaLimite());
		}
		
		
		solicitudPericial.setFechaInicioPrestamo(new Date());
		solicitudPericialDest.setFechaInicioPrestamo(new Date());
		
		solicitudPericial.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
		solicitudPericialDest.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		
		solicitudPericial.setPuestoUsuarioSolicitante("_");
		solicitudPericialDest.setPuestoUsuarioSolicitante("_");
		
		solicitudPericial.setForma(new Forma(1L));
		solicitudPericialDest.setForma(new Forma(1L));
		
		solicitudPericial.setTipoDocumento(new Valor(82L));// Solicitud
		solicitudPericialDest.setTipoDocumento(new Valor(82L));// Solicitud
		
		solicitudPericial.setFolioDocumento("Folio Documento");		
		solicitudPericialDest.setFolioDocumento("Folio Documento");
		
		solicitudPericial.setNombreDocumento("Solicitud");
		solicitudPericialDest.setNombreDocumento("Solicitud");
		
		solicitudPericial.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		solicitudPericialDest.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		
		solicitudPericial.setTipoSolicitud(new Valor(solicitud.getTipoSolicitudDTO().getIdCampo()));
		solicitudPericialDest.setTipoSolicitud(new Valor(solicitud.getTipoSolicitudDTO().getIdCampo()));
		
		if(solicitud.getObservaciones()!=null)
		{
			solicitudPericial.setObservaciones(solicitud.getObservaciones());
			solicitudPericialDest.setObservaciones(solicitud.getObservaciones());
		}
		
		if(solicitud.getExpedienteDTO().getNumeroExpedienteId()!=null)
		{
			Long idNumeroExpediente = solicitud.getExpedienteDTO().getNumeroExpedienteId();
			try
			{
				NumeroExpediente ne = numeroExpedienteDAO.read(idNumeroExpediente);
				solicitudPericial.setNumeroExpediente(ne);
				solicitudPericialDest.setNumeroExpediente(ne);
			}catch(Exception ex)
			{
				throw ex;
			}
		}
		
		Long idSolPeritoAMP;
		Long idSolCoord;
		try
		{
			//Genera solicitud pericial para perito AMP				
			idSolPeritoAMP = solicitudPericialDAO.create(solicitudPericial);
				
			//Genera solicitud pericial para Coordinador de periciales		
		
			solicitudPericialDest.setSolicitudPadre(solicitudPericialDAO.read(idSolPeritoAMP));
							
			idSolCoord = solicitudPericialDAO.create(solicitudPericialDest);
		}catch (Exception ex)
		{
			throw ex;
		}
		
		for(EvidenciaDTO row: evidencias)
		{
			RelacionDocumentoElemento relacion = new RelacionDocumentoElemento();
			relacion.setCatRelacion(new CatRelacion((long)Relaciones.EVIDENCIA_EN_SOLICITUD.ordinal()));
			relacion.setDocumento(solicitudPericialDAO.read(idSolCoord));
			
			Evidencia relEvidencia = new Evidencia();			
			relEvidencia = evidenciaDAO.read(row.getEvidenciaId());
			relacion.setElemento(elementoDAO.read(relEvidencia.getObjeto().getElementoId()));			
			relacionDocDAO.create(relacion);
		}
		return idSolPeritoAMP;
	}	
}

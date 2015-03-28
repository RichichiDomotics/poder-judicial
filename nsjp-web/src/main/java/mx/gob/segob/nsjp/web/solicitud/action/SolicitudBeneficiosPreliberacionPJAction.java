/**
 * 
 */
package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author EdgarAG
 *
 */

public class SolicitudBeneficiosPreliberacionPJAction extends GenericAction {
	
private static final Logger log = Logger.getLogger(SolicitudCiudadanaDefensorAction.class);
@Autowired
private SolicitudDelegate solicitudDelegate;
@Autowired
private ExpedienteDelegate expedienteDelegate;
@Autowired
private AudienciaDelegate audienciaDelegate;

@Autowired
private DocumentoDelegate documentoDelegate;

	public ActionForward solicitudesBeneficiosPreliberacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes Preliberacion");
			UsuarioDTO usuario = null;
			
			
			
			List<SolicitudDTO> solicitudDTOs = new ArrayList<SolicitudDTO>();
			solicitudDTOs = (List<SolicitudDTO>) solicitudDelegate.consultarSolicitudXEstatus(EstatusSolicitud.ABIERTA, usuario, TiposSolicitudes.BENEFICIO_PRELIBERACION);
			log.info("Solicitudes Preliberacion" + solicitudDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = solicitudDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(SolicitudDTO solicitudDTO : solicitudDTOs){

				writer.print("<row id='"+solicitudDTO.getDocumentoId()+ "'>");
				
				writer.print("<cell>");
				if(solicitudDTO.getExpedienteDTO()!=null&&solicitudDTO.getExpedienteDTO().getNumeroExpediente()!=null){
					writer.print(solicitudDTO.getExpedienteDTO().getNumeroExpediente());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getNombreSolicitante()!=null){
					writer.print(solicitudDTO.getNombreSolicitante());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(solicitudDTO.getNombreInstitucionSolicitante()!=null){
				writer.print(solicitudDTO.getNombreInstitucionSolicitante());
				}
			    writer.print("</cell>");
			    
				writer.print("<cell>"+""+ "</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}


	public ActionForward buscaCarpetaEjecucion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			//NSJYUCPROC2011333WT

			log.info("Numero de Audiencia");
			String audienciaNum = request.getParameter("audienciaNum");
			log.info("Numero de Au =="+audienciaNum);
			
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setConsecutivo(Short.parseShort(audienciaNum));
			audienciaDTO = audienciaDelegate.obtenerAudienciaByNumeroAudiencia(audienciaDTO);
			log.info("Numero de Au =="+audienciaDTO);
			
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("audienciaDTO", AudienciaDTO.class);
			xml = converter.toXML(audienciaDTO);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward audienciadeEjecucion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes Preliberacion");
			UsuarioDTO usuario = getUsuarioFirmado(request);
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setFechaEvento(new Date());
			List<AudienciaDTO> audienciaDTOs = new ArrayList<AudienciaDTO>();
			audienciaDTOs = audienciaDelegate.consultarAudienciasByTipoYFecha(audienciaDTO, TipoAudiencia.EJECUCION,usuario);
			
			
			
			log.info("Solicitudes Preliberacion" + audienciaDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = audienciaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");			
			
			for(AudienciaDTO audienciaDTO2 : audienciaDTOs){

				writer.print("<row id='"+audienciaDTO2.getId()+ "'>");
				
				writer.print("<cell>");
				if(audienciaDTO2.getExpediente().getNumeroExpediente()!=null){
					writer.print(audienciaDTO2.getExpediente().getNumeroExpediente());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				//if(audienciaDTO2.getExpediente().getNumeroExpediente()!=null){
					writer.print("-");
				//}
				writer.print("</cell>");
				
				writer.print("<cell>");
				//if(audienciaDTO2.getExpediente().getNumeroExpediente() !=null){
				writer.print("-");
				//}
			    writer.print("</cell>");
			    
			    writer.print("<cell>");
				//if(audienciaDTO2.getFechaEvento()!=null){
				writer.print(audienciaDTO2.getFechaEvento());
				//}
			    writer.print("</cell>");
			    
			    
				writer.print("<cell>"+""+ "</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public ActionForward carpetaEjecucion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Carpetas de Ejecucion");
			List<ExpedienteDTO> expedienteDTOs = new ArrayList<ExpedienteDTO>();
			expedienteDTOs = expedienteDelegate.consultarNumeroExpedienteByEstatus(TipoExpediente.CARPETA_DE_EJECUCION, EstatusExpediente.ABIERTO);
			log.info("Carpetas de Ejecucion::::"  +expedienteDTOs);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = expedienteDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(ExpedienteDTO expedienteDTO : expedienteDTOs){

				writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+ "'>");
				
				writer.print("<cell>");
				if(expedienteDTO.getNumeroExpediente()!=null){
					writer.print(expedienteDTO.getNumeroExpediente());
						}
				writer.print("</cell>");
				
			
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward historico(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes Preliberacion");
			List<ExpedienteDTO> expedienteDTOs = new ArrayList<ExpedienteDTO>();
			UsuarioDTO usuario = getUsuarioFirmado(request);
			expedienteDTOs = expedienteDelegate.consultarNumeroExpedienteHistorico(usuario);
			
			log.info("Expediente Historico" + expedienteDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = expedienteDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(ExpedienteDTO expedienteDTO : expedienteDTOs){

				writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId() + "'>");
				
				
				
				writer.print("<cell>");
				if(expedienteDTO.getNumeroExpediente()!=null){
					writer.print(expedienteDTO.getNumeroExpediente());
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				for (ExpedienteDTO carpeta : expedienteDTO.getNumExpHijos()) {
				if( carpeta.getNumeroExpedienteId()!=null){
					writer.print( carpeta.getNumeroExpedienteId());
						}
				}
				writer.print("</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultarDocumentosAudiencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String audienciaid = request.getParameter("audienciaid");
			log.info("consulta Audiencia::::"  +audienciaid);
			AudienciaDTO audienciaDTO = new AudienciaDTO();
			audienciaDTO.setId(Long.parseLong(audienciaid));
			List<DocumentoDTO> documentoDTOs = new ArrayList<DocumentoDTO>();
			documentoDTOs = documentoDelegate.consultarDocumentosAudienciaByTipoForma(audienciaDTO,TipoForma.RESOLUCION );
			
		
			log.info("consulta Documento::::"  +documentoDTOs);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = documentoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(DocumentoDTO documentoDTO : documentoDTOs){

				writer.print("<row id='"+documentoDTO.getDocumentoId()+ "'>");
				
				writer.print("<cell>");
				if(documentoDTO.getNombreDocumento()!=null){
					writer.print(documentoDTO.getNombreDocumento());
						}
				writer.print("</cell>");
				
			
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public ActionForward buscaCarpetaEjecucionPorCausa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			//NSJYUCPROC2011333WT

			String audienciaid = request.getParameter("numCarpetaEjecucion");
			log.info("buscaCarpetaEjecucionPorCausa numexp::::"  +audienciaid);
			List<AudienciaDTO> audienciaDTOs = new ArrayList<AudienciaDTO>();
			FiltroAudienciaDTO filtroAudienciaDTO = new FiltroAudienciaDTO();
			filtroAudienciaDTO.setNumeroExpediente(audienciaid);
			audienciaDTOs = audienciaDelegate.buscarAudiencias(filtroAudienciaDTO);
			log.info("consulta Filtro Audiencia ::::"  +audienciaDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = audienciaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(AudienciaDTO audienciaDTO : audienciaDTOs){

				writer.print("<row id='"+audienciaDTO.getId()+ "'>");
				
				writer.print("<cell>");
				if(audienciaDTO.getId() !=null){
					writer.print(audienciaDTO.getId());
						}
				writer.print("</cell>");
				
			
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public ActionForward consultarDocumentosAudienciaPorCausa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String audienciaid = request.getParameter("audienciaid");
			log.info("consulta Audiencia::::"  +audienciaid);
			List<AudienciaDTO> audienciaDTOs = new ArrayList<AudienciaDTO>();
			FiltroAudienciaDTO filtroAudienciaDTO = new FiltroAudienciaDTO();
			filtroAudienciaDTO.setNumeroExpediente(audienciaid);
			audienciaDTOs = audienciaDelegate.buscarAudiencias(filtroAudienciaDTO);
			
		
			log.info("consulta Filtro Audiencia ::::"  +audienciaDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = audienciaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(AudienciaDTO audienciaDTO : audienciaDTOs){

				writer.print("<row id='"+audienciaDTO.getId()+ "'>");
				
				writer.print("<cell>");
				if(audienciaDTO.getExpediente().getNumeroExpediente()!=null){
					writer.print(audienciaDTO.getExpediente().getNumeroExpediente());
						}
				writer.print("</cell>");
				
			
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
}

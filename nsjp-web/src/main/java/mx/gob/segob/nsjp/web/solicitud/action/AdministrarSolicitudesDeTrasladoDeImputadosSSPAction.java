/**
 * 
 */
package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class AdministrarSolicitudesDeTrasladoDeImputadosSSPAction extends GenericAction {

	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(AdministrarSolicitudesDeTrasladoDeImputadosSSPAction.class);
	@Autowired
	SolicitudDelegate solicitudDelegate;
	
	/**
	 * Metodo utilizado para consultar las solicitudes de traslado de imputado no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesDeTrasladoDeImputadoNoAtendidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION CONSULTAR SOLICITUDES DE TRASLADO DE IMPUTADOS NO ATENDIDAS___________________");
			
			List<SolicitudAudienciaDTO> listaSolicitudesNoAtendidas = 
			(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.TRASLADO_DE_IMPUTADO
					,EstatusSolicitud.ABIERTA);
			log.info("DESPUES DEL DELEGATE");
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
		
			int lTotalRegistros = listaSolicitudesNoAtendidas.size();
		
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (SolicitudAudienciaDTO sol : listaSolicitudesNoAtendidas) {
				writer.print("<row id='"+ sol.getDocumentoId()  + "'>");
					//Numero de IPH (informe policial homologado)
					writer.print("<cell>"+ "" + "</cell>");
					//Nombre del Imputado
					writer.print("<cell>"+ (sol.getInvolucradoDTO() != null ? sol.getInvolucradoDTO().getNombreCompleto():"")+ "</cell>");	
					//Tipo de audiencia
					writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getTipoAudiencia() != null) ? sol.getAudiencia().getTipoAudiencia().getValor() :"") +  "</cell>");
					//Fecha de audiencia
					writer.print("<cell>"+((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null) ? DateUtils.formatear(sol.getAudiencia().getFechaEvento()): "")+  "</cell>");
					//Hora de audiencia
					writer.print("<cell>"+((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null) ? DateUtils.formatearHora(sol.getAudiencia().getFechaEvento()): "")+  "</cell>");
					//Domicilio sala
					writer.print("<cell>"+ (sol.getAudiencia().getSala()!= null ? sol.getAudiencia().getSala().getDomicilioSala():"") +  "</cell>");
					//Sala
					writer.print("<cell>"+ (sol.getAudiencia().getSala() != null ? sol.getAudiencia().getSala().getNombreSala(): "")+  "</cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar las solicitudes de traslado de imputado no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesDeTrasladoDeImputadoEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR SOLICITUDES DE TRASLADO DE IMPUTADOS EN PROCESO___________________");
			
			List<SolicitudAudienciaDTO> listaSolicitudesEnProceso = 
				(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.TRASLADO_DE_IMPUTADO
						,EstatusSolicitud.EN_PROCESO);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
			
				int lTotalRegistros = listaSolicitudesEnProceso.size();
			
				writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudAudienciaDTO sol : listaSolicitudesEnProceso) {
					writer.print("<row id='"+ sol.getDocumentoId()  + "'>");
					
						//Solicitante
						writer.print("<cell>"+ sol.getUsuarioSolicitante() + "</cell>");
						//Numero de IPH
						writer.print("<cell>"+ "" + "</cell>");
						//Nombre del imputado
						writer.print("<cell>"+ (sol.getInvolucradoDTO() != null ? sol.getInvolucradoDTO().getNombreCompleto():"")+ "</cell>");
						//Tipo de audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getTipoAudiencia() != null) ? sol.getAudiencia().getTipoAudiencia().getValor() :"") +  "</cell>");
						//Fecha Audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null ) ? DateUtils.formatear(sol.getAudiencia().getFechaEvento()): "") +  "</cell>");
						//Hora de Audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null) ? DateUtils.formatearHora(sol.getAudiencia().getFechaEvento()):"") +"</cell>");
						//Domicilio sala
						writer.print("<cell>"+ (sol.getAudiencia().getSala()!= null ? sol.getAudiencia().getSala().getDomicilioSala():"") +  "</cell>");
						//Sala
						writer.print("<cell>"+ (sol.getAudiencia().getSala() != null ? sol.getAudiencia().getSala().getNombreSala(): "")+  "</cell>");
						//Fecha autorizacion de descarcelacion
						writer.print("<cell>"+""+  "</cell>");
						

					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar las solicitudes de traslado de imputado no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesDeTrasladoDeImputadoTerminadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR SOLICITUDES DE TRASLADO DE IMPUTADOS TERMINADAS___________________");
			
			List<SolicitudAudienciaDTO> listaSolicitudesConcluidas = 
				(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.TRASLADO_DE_IMPUTADO
						,EstatusSolicitud.CERRADA);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
			
				int lTotalRegistros = listaSolicitudesConcluidas.size();
			
				writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudAudienciaDTO sol : listaSolicitudesConcluidas) {
					writer.print("<row id='"+ sol.getDocumentoId()  + "'>");
						//Solicitante
						writer.print("<cell>"+ sol.getUsuarioSolicitante() + "</cell>");
						//Numero de IPH
						writer.print("<cell>"+ "" + "</cell>");
						//Nombre del imputado
						writer.print("<cell>"+ (sol.getInvolucradoDTO() != null ? sol.getInvolucradoDTO().getNombreCompleto():"")+ "</cell>");
						//Tipo de audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getTipoAudiencia() != null) ? sol.getAudiencia().getTipoAudiencia().getValor() :"") +  "</cell>");
						//Fecha Audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null ) ? DateUtils.formatear(sol.getAudiencia().getFechaEvento()): "") +  "</cell>");
						//Hora de Audiencia
						writer.print("<cell>"+ ((sol.getAudiencia() != null && sol.getAudiencia().getFechaEvento() != null) ? DateUtils.formatearHora(sol.getAudiencia().getFechaEvento()):"") +"</cell>");
						//Domicilio sala
						writer.print("<cell>"+ (sol.getAudiencia().getSala()!= null ? sol.getAudiencia().getSala().getDomicilioSala():"") +  "</cell>");
						//Sala
						writer.print("<cell>"+ (sol.getAudiencia().getSala() != null ? sol.getAudiencia().getSala().getNombreSala(): "")+  "</cell>");
						//Fecha autorizacion de descarcelacion
						writer.print("<cell>"+""+  "</cell>");
						//Fecha autorizacion de Examen Medico
						writer.print("<cell>"+""+  "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
}






//String tipoSolicitud = request.getParameter("tipoSolicitud");
//if(tipoSolicitud != null){
//	
//	Long tipoSol = Long.parseLong(tipoSolicitud);
//	List<SolicitudPericialDTO> listaSolicitudesNoAtendidas = solicitudPericialDelegate.consultarSolicitudesPericialesPorTipoYEstatus(TiposSolicitudes.getByValor(tipoSol),EstatusSolicitud.ABIERTA);
//	if (log.isDebugEnabled()){
//	    log.debug("SOLICITUDES NO ATENDIDAS" + listaSolicitudesNoAtendidas);
//	}
//	
//	response.setContentType("text/xml; charset=UTF-8");
//	response.setHeader("Cache-Control", "no-cache");
//	PrintWriter writer = response.getWriter();
//
//	writer.print("<rows>");
//
//	int lTotalRegistros = listaSolicitudesNoAtendidas.size();
//
//	writer.print("<records>" + lTotalRegistros + "</records>");
//	for (SolicitudPericialDTO solicitudPericialDTO : listaSolicitudesNoAtendidas) {
//	    if (log.isDebugEnabled()){
//	        log.debug("SOLICITUD PERICIAL DTO" + solicitudPericialDTO);
//	    }
		//		writer.print("<row id='"+ solicitudPericialDTO.getDocumentoId() + "'>");
		//			writer.print("<cell>"+ ((solicitudPericialDTO.getExpedienteDTO()!=null && solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente()!=null) ? solicitudPericialDTO.getExpedienteDTO().getNumeroExpediente():"-") +  "</cell>");
		//			writer.print("<cell>"+ (solicitudPericialDTO.getNumeroCasoAsociado()!=null ? solicitudPericialDTO.getNumeroCasoAsociado():"-") +  "</cell>");
		//			writer.print("<cell>"+ (solicitudPericialDTO.getNombreSolicitante()!=null ? solicitudPericialDTO.getNombreSolicitante():"-") +  "</cell>");
		//			writer.print("<cell>"+ (solicitudPericialDTO.getEspecialidad()!=null && solicitudPericialDTO.getEspecialidad().getValor()!=null? solicitudPericialDTO.getEspecialidad().getValor():"-") +  "</cell>");
		//			if(solicitudPericialDTO.getFechaLimite() != null){
		//				String fechaLimite=DateUtils.formatear(solicitudPericialDTO.getFechaLimite());
		//				writer.print("<cell>"+ fechaLimite + "</cell>");
		//			}
		//			else{
		//				writer.print("<cell>"+ "---" + "</cell>");
		//			}
		//			writer.print("<cell>"+  "true" +  "</cell>");
		//		writer.print("</row>");
//	}
//	writer.print("</rows>");
//	writer.flush();
//	writer.close();
//}

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
 * Action encargado de atender las peticiones para realizar las consultas de las solicitudes
 * de descarcelación, ya sea nuevas, en proceso o cerradas
 * @author Emigdio Hernández
 *
 */
public class AdministrarSolicitudesDeDescarcelacionSSPAction extends GenericAction {

	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(AdministrarSolicitudesDeDescarcelacionSSPAction.class);
	@Autowired
	SolicitudDelegate solicitudDelegate;
	
	/**
	 * Metodo utilizado para consultar las solicitudes de descarcelación no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesDeDescarcelacionNoAtendidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			List<SolicitudAudienciaDTO> solicitudes = 
			(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.DESCARCELACION
					,EstatusSolicitud.ABIERTA);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
		
			int lTotalRegistros = solicitudes.size();
		
			writer.print("<records>" + lTotalRegistros + "</records>");
			for (SolicitudAudienciaDTO sol : solicitudes) {
			  		    
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
	public ActionForward consultarSolicitudesDeDescarcelacionEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			List<SolicitudAudienciaDTO> solicitudes = 
				(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.DESCARCELACION
						,EstatusSolicitud.EN_PROCESO);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
			
				int lTotalRegistros = solicitudes.size();
			
				writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudAudienciaDTO sol : solicitudes) {
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
	public ActionForward consultarSolicitudesDeDescarcelacionTerminadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			List<SolicitudAudienciaDTO> solicitudes = 
				(List<SolicitudAudienciaDTO>) solicitudDelegate.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.DESCARCELACION
						,EstatusSolicitud.CERRADA);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
			
				int lTotalRegistros = solicitudes.size();
			
				writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudAudienciaDTO sol : solicitudes) {
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
					//Fecha cierre
					writer.print("<cell>"+ (sol.getFechaCierre() != null ? DateUtils.formatear(sol.getFechaCierre()): "" ) +  "</cell>");
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




package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.action.DelitoAction;

/**
 * @author AlejandroGA
 *
 */
public class ConsultaEventosPJENAAction extends GenericAction{

	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultaEventosPJENAAction.class);
	
	/**
	 * Metodo utilizado para realizar el paso del parametro id del evento
	 * a la JSP de atencionSolicitudAudienciaNotificador
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward visorSolicitudPJENA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando action visor solicitud PJENA");
			
			String tipoExpediente = request.getParameter("tipoExpediente");
			String idEvento = request.getParameter("idEvento");
			log.info("tipo expediente:::"+ tipoExpediente);
			log.info("id evento:::"+ idEvento);
			request.setAttribute("tipoExpediente",tipoExpediente);
			request.setAttribute("idEvento",idEvento);
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}	
	
//	@Autowired
//	public AudienciaDelegate audienciaDelegate;
//
//	/**
//	 * Metodo utilizado para realizar la consulta de nuevos eventos 
//	 * @param mapping
//	 * @param form
//	 * @param request 
//	 * @param response
//	 * @return succes - En caso de que el proceso sea correcto
//	 * @throws IOException En caso de obtener una exception
//	 */
//	public ActionForward consultaNuevasNotificaciones(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		try {
//			log.info("ejecutando Consulta Nuevas Notificaciones - Carga eventos");
//			
//			EventoDTO eventoDTO = new EventoDTO(); 
//			eventoDTO.setBandeja(BandejaNotificador.NUEVO);
//			
//			List<EventoDTO> listaNuevosEventos=audienciaDelegate.consultarEventosParaNotificar(eventoDTO);
//
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			PrintWriter writer = response.getWriter();
//			
//			writer.print("<rows>");
//			
//			int lTotalRegistros=listaNuevosEventos.size();
//			
//			writer.print("<records>" + lTotalRegistros + "</records>");
//			for (EventoDTO eventoNuevoDTO : listaNuevosEventos) {
//				
//				writer.print("<row id='" + eventoNuevoDTO.getId()+ "'>");
//					writer.print("<cell>" + eventoNuevoDTO.getExpediente().getNumeroExpediente()+ "</cell>");
//					writer.print("<cell>" + eventoNuevoDTO.getTipo().getValor()+ "</cell>");				
//					writer.print("<cell>" + eventoNuevoDTO.getTipoEvento().getValor()+ "</cell>");
//					String fechaSolicitud=DateUtils.formatear(eventoNuevoDTO.getFechaSolicitud());
//					String horaSolicitud=DateUtils.formatearHora(eventoNuevoDTO.getFechaSolicitud());
//					writer.print("<cell>" + fechaSolicitud+" "+horaSolicitud+ "</cell>");
//					String fechaEvento=DateUtils.formatear(eventoNuevoDTO.getFechaEvento());
//					String horaEvento=DateUtils.formatearHora(eventoNuevoDTO.getFechaEvento());
//					writer.print("<cell>" + fechaEvento+" "+horaEvento+ "</cell>");
//				writer.print("</row>");
//			}			
//			writer.print("</rows>");
//			writer.flush();
//			writer.close();
//			
//		} catch (Exception e) {		
//			log.info(e.getCause(),e);
//			
//		}
//		return null;
//	}
//	
//	/**
//	 * Metodo utilizado para realizar del detalle de eventos
//	 * @param mapping
//	 * @param form
//	 * @param request 
//	 * @param response
//	 * @return succes - En caso de que el proceso sea correcto
//	 * @throws IOException En caso de obtener una exception
//	 */
//	public ActionForward consultaDetalleNotificaciones(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		try {
//			log.info("ejecutando consultaDetalleNotificaciones - Carga Datos expediente-evento");
//			
//			String idEvento = request.getParameter("idEvento");
//
//			log.info("ID-DEL EVENTO:::"+ idEvento);
//			
//			
//			EventoDTO eventoDTO = new EventoDTO(); 
//			eventoDTO.setId(Long.parseLong(idEvento));
//			
//			log.info("ANTES DEL DELEGATE");
//			eventoDTO = audienciaDelegate.obtenerEvento(eventoDTO);
//			log.info("evento dto="+ eventoDTO);
//			
//			converter.alias("eventoDTO", EventoDTO.class);
//			String xml = converter.toXML(eventoDTO);
//			//enviamos la respuesta al cliente
//			escribir(response, xml,null);	
//
//			
//		} catch (Exception e) {		
//			log.info("Error al consultar evento");
//			log.info(e.getCause(),e);
//			escribir(response, "consultaDetalleNotificaciones",null);
//			
//		}
//		return null;
//	}
//	
//	
	
}

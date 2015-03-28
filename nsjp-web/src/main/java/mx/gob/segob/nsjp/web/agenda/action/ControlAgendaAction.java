package mx.gob.segob.nsjp.web.agenda.action;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.eventocita.EventoCitaDelegate;
import mx.gob.segob.nsjp.delegate.tarea.TareaDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.agenda.form.AgendaForm;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;

public class ControlAgendaAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(ControlAgendaAction.class);

	@Autowired
	private EventoCitaDelegate eventoCitaDelegate;
	@Autowired
	private TareaDelegate tareaDelegate;
	
	/**
	 * Metodo utilizado para guardar los datos de un individuo dependiendo su calidad 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward inicio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info("ejecutando inicio agenda");
		AgendaForm agendaForm =(AgendaForm) form;
		log.info("Agenda:");
		log.info("\tMetodo:" + agendaForm.getMethod());
		log.info("\tMetodo:" + agendaForm.getViewtype());
		log.info("\tFecha:" + agendaForm.getShowdate());
		
		return mapping.findForward("success");
	}
	
	public ActionForward listado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		log.info("ejecutando listado agenda");
		AgendaForm agendaForm =(AgendaForm) form;
		Date fechaInicio     = null;
		Date fechaFin        = null;
		log.info("Agenda:");
		log.info("\tMetodo:" + agendaForm.getMethod());
		log.info("\tviewType:" + agendaForm.getViewtype());
		log.info("\tshowDate:" + agendaForm.getShowdate());
		
		
			Calendar calendarioReferencia = Calendar.getInstance();
			calendarioReferencia.setTimeInMillis(agendaForm.getShowdateToDate().getTime());
			if (agendaForm.getViewtype().equals(AgendaForm.VIEW_TYPE_MONTH)) {
				calendarioReferencia.set(Calendar.DAY_OF_MONTH, calendarioReferencia.getActualMinimum(Calendar.DAY_OF_MONTH));
				fechaInicio = calendarioReferencia.getTime();  
				calendarioReferencia.set(Calendar.DAY_OF_MONTH, calendarioReferencia.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendarioReferencia.set(Calendar.HOUR_OF_DAY, AgendaForm.MAX_HOUR_DAY);
				calendarioReferencia.set(Calendar.MINUTE, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.SECOND, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.MILLISECOND, AgendaForm.MAX_MIN_SEC);
				fechaFin = calendarioReferencia.getTime();
			} else if (agendaForm.getViewtype().equals(AgendaForm.VIEW_TYPE_WEEK)) {
				int diaSemana = calendarioReferencia.get(Calendar.DAY_OF_WEEK);
				calendarioReferencia.set(Calendar.DAY_OF_MONTH, calendarioReferencia.get(Calendar.DAY_OF_MONTH) - (diaSemana - 1));
				fechaInicio = calendarioReferencia.getTime();
				calendarioReferencia.set(Calendar.DAY_OF_MONTH, calendarioReferencia.get(Calendar.DAY_OF_MONTH) + AgendaForm.DAYS_WEEK);
				calendarioReferencia.set(Calendar.HOUR_OF_DAY, AgendaForm.MAX_HOUR_DAY);
				calendarioReferencia.set(Calendar.MINUTE, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.SECOND, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.MILLISECOND, AgendaForm.MAX_MIN_SEC);
				fechaFin    = calendarioReferencia.getTime();
			} else if (agendaForm.getViewtype().equals("day")) {			
				fechaInicio = calendarioReferencia.getTime();
				calendarioReferencia.set(Calendar.HOUR_OF_DAY, AgendaForm.MAX_HOUR_DAY);
				calendarioReferencia.set(Calendar.MINUTE, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.SECOND, AgendaForm.MAX_MIN_SEC);
				calendarioReferencia.set(Calendar.MILLISECOND, AgendaForm.MAX_MIN_SEC);
				fechaFin    = calendarioReferencia.getTime();
			}			
			LinkedHashMap<String, Object>  mapa  = new LinkedHashMap<String, Object> ();
			mapa.put("events", new JSONArray());
			mapa.put("issort", true);
			mapa.put("start", agendaForm.getDateFormat().format(fechaInicio));
			mapa.put("end", agendaForm.getDateFormat().format(fechaFin));
			mapa.put("error", null);
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			
			List<EventoCitaDTO> lista = eventoCitaDelegate.consultarEventosCitasPorUsuario(usuario,fechaInicio,fechaFin);
			if(lista == null){
				lista = new LinkedList<EventoCitaDTO>();
			}
			SimpleDateFormat formater = agendaForm.getDateFormat();
			JSONArray evento = new JSONArray ();
			for(EventoCitaDTO cita : lista){
				evento = new JSONArray ();
				evento.add(""+cita.getEventoCitaId()); 							// Id Evento
				evento.add(""+cita.getNombreEvento());							// Asunto
				evento.add(""+formater.format(cita.getFechaInicioEvento()));	// Fecha inicio tarea o evento
				evento.add(""+formater.format(cita.getFechaFinEvento()));		// fecha fin tarea o evento
				evento.add("0");												// Evento de todo el día
				evento.add("0");												// Mas de un día
				evento.add("0");												// Recurrente
				evento.add("0");												// Color
				evento.add("0");												// draggable
				evento.add("0");												// Editable
				evento.add(""+cita.getDireccion());								// Direccion
				evento.add("");													// mark as attending
				((JSONArray)mapa.get("events")).add(evento);	
			}
			
			StringWriter out = new StringWriter();
			JSONValue.writeJSONString(mapa, out);
			log.info("agenda:" + out.toString());
			response.setContentType("text/javascript;charset=UTF-8");
			response.getWriter().println(out.toString());
		
		return null;
	}
	
	public ActionForward agregarEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info("ejecutando agregar agenda");
		AgendaForm agendaForm =(AgendaForm) form;

		
		
		StringWriter out = new StringWriter();
		LinkedHashMap  mapa  = new LinkedHashMap ();
		mapa.put("IsSuccess", true);
		mapa.put("Msg", "Evento Agregado");
		mapa.put("Data", "Evento Agregado");
		
		log.info("MAPA DE LA AGENDA:::::::::::::::::::::::::::::::::::::::::::"+mapa);
		JSONValue.writeJSONString(mapa, out);
		log.info(out.toString());
		response.setContentType("text/javascript;charset=UTF-8");
		response.getWriter().println(out.toString());
		return null;
	}
	
	public ActionForward detalle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		log.info("ejecutando detalle agenda");
		AgendaForm agendaForm =(AgendaForm) form;
		Date fechaInicio     = null;
		Date fechaFin        = null;
		if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()){
			Long id = new Long (request.getParameter("id"));
			EventoCitaDTO evento = eventoCitaDelegate.consultarEventoCitaPorId(id);
			agendaForm.setIdEvento(""+evento.getEventoCitaId());
			agendaForm.setAsunto(evento.getNombreEvento());
			if(evento.getTarea() != null){
				agendaForm.setTipoTarea(""+evento.getTarea().getValor().getIdCampo());
			}
			Date date = evento.getFechaInicioEvento();
			String fecha = agendaForm.getSdt().format(date);
			log.debug("FECHA :: "+fecha);
			agendaForm.setFechaInicio(fecha.split(" ")[0]);
			agendaForm.setHoraInicio(fecha.split(" ")[1]);
			date = evento.getFechaFinEvento();
			fecha = agendaForm.getSdt().format(date);
			agendaForm.setFechaFinal(fecha.split(" ")[0]);
			agendaForm.setHoraFinal(fecha.split(" ")[1]);
			agendaForm.setLugar(evento.getDireccion());
			agendaForm.setDescripcion(evento.getDescripcionEvento());
			TareaDTO tarea = tareaDelegate.consultarTareaEventoCita(id);
			agendaForm.setTipoTarea(tarea.getValor().getIdCampo().toString());
			agendaForm.setAlarma(evento.getEsAlertaAlarma().toString());

		}else{
			agendaForm = new AgendaForm();
		}
		request.setAttribute("AgendaForm", agendaForm);
		return mapping.findForward("success");
	}

	public ActionForward eliminarEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		log.info("ejecutando eliminar agenda");
		AgendaForm agendaForm =(AgendaForm) form;
		Date fechaInicio     = null;
		Date fechaFin        = null;
		log.info("Agenda:");
		log.info("\tMetodo:" + agendaForm.getMethod());
		log.info("\tviewType:" + agendaForm.getViewtype());
		log.info("\tshowDate:" + agendaForm.getShowdate());
		log.info("\tCalendarId:" + agendaForm.getIdEvento());
		EventoCitaDTO evento = new EventoCitaDTO();
		if(agendaForm.getIdEvento() != null && !agendaForm.getIdEvento().isEmpty()){
			evento.setEventoCitaId(Long.parseLong(agendaForm.getIdEvento()));
			eventoCitaDelegate.eliminarEventoCita(evento);
		}
		
		StringWriter out = new StringWriter();
		LinkedHashMap  mapa  = new LinkedHashMap ();
		mapa.put("IsSuccess", true);
		mapa.put("Msg", "Evento Eliminado");	
		
		String xml = converter.toXML("success");
		escribir(response, xml,null);
		
		//JSONValue.writeJSONString(mapa, out);
		//log.info(out.toString());
		//response.setContentType("text/javascript;charset=UTF-8");
		//response.getWriter().println(out.toString());
		return null;
	}
	
	public ActionForward modificarEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		log.info("ejecutando modificar agenda");
		AgendaForm agendaForm =(AgendaForm) form;
		StringWriter out = new StringWriter();
		LinkedHashMap  mapa  = new LinkedHashMap ();
		EventoCitaDTO evento = new EventoCitaDTO();
		evento.setTipoEvento(new ValorDTO(TipoEvento.TAREA.getValorId()));
		Date date = DateUtils.obtenerUS(agendaForm.getFechaInicio(), agendaForm.getHoraInicio());
		evento.setFechaInicioEvento(date);
		date = DateUtils.obtenerUS(agendaForm.getFechaFinal(), agendaForm.getHoraFinal());
		evento.setFechaFinEvento(date);
		evento.setNombreEvento(agendaForm.getAsunto());
		evento.setDireccion(agendaForm.getLugar());
		evento.setDescripcionEvento(agendaForm.getDescripcion());
		evento.setUsuario(getUsuarioFirmado(request));
		evento.setEstatus(new ValorDTO(EstatusEventoCita.NO_ATENDIDO.getValorId()));
		evento.setEsAlertaAlarma(BooleanUtils.toBoolean(agendaForm.getAlarma(), "true", "false"));
		TareaDTO tarea = new TareaDTO();
		tarea.setValor(new ValorDTO(Long.parseLong(agendaForm.getTipoTarea())));
		tarea.setEventoCita(evento);
		tarea.setUsuario(getUsuarioFirmado(request));
		tarea.setIdFuncionario(getUsuarioFirmado(request).getFuncionario().getClaveFuncionario());
		tarea.setNtiempoReal((short)10);
		tarea.setDiaTarea(evento.getFechaInicioEvento());
		evento.setTarea(tarea);
		
		if(agendaForm.getIdEvento() != null && !agendaForm.getIdEvento().isEmpty()){
			evento.setEventoCitaId(Long.parseLong(agendaForm.getIdEvento()));
			eventoCitaDelegate.actualizarEventoCita(evento);
			
		}else{
			tareaDelegate.asignarTareaFuncionario(tarea, getUsuarioFirmado(request).getFuncionario());
		}
		
		mapa.put("IsSuccess", true);
		mapa.put("Msg","Evento Modificado");
		log.info("MAPA DE LA AGENDA:::::::::::::::::::::::::::::::::::::::::::"+mapa);
		
		String xml = converter.toXML("success");
		escribir(response, xml,null);
		
		//JSONValue.writeJSONString(mapa, out);
		//log.info(out.toString());
		//response.setContentType("text/javascript;charset=UTF-8");
		//response.getWriter().println(out.toString());
		return null;
	}
}

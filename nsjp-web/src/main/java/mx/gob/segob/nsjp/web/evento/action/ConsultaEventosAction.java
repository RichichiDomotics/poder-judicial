/**
* Nombre del Programa 			: ConsultaEventosAction.java
* Autor                         : AlejandroGA
* Compania                    	: Ultrasist
* Proyecto                      : NSJP                    Fecha: 01 June 2011
* Marca de cambio        		: N/A
* Descripcion General    		: Clase encargada de consultar los eventos para
* 								  notificaciones
* Programa Dependiente  		:N/A
* Programa Subsecuente 			:N/A
* Cond. de ejecucion        	:N/A
* Dias de ejecucion          	:N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       	:N/A
* Compania               		:N/A
* Proyecto                		:N/A                                 Fecha: N/A
* Modificacion          		:N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.funcionario.Especialidades;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.persona.PersonaDelegate;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author AlejandroGA
 *
 */
public class ConsultaEventosAction extends GenericAction{

	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultaEventosAction.class);
	
	@Autowired
	public AudienciaDelegate audienciaDelegate;
	
	@Autowired
	public PersonaDelegate personaDelegate;
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	private final static String KEY_SESSION_EVENTO = "KEY_SESSION_EVENTO_DTO";

	/**
	 * Metodo utilizado para realizar la consulta de nuevos eventos 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaNuevasNotificaciones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Consulta Nuevas Notificaciones - Carga eventos");
			
			EventoDTO eventoDTO = new EventoDTO(); 
			eventoDTO.setBandeja(BandejaNotificador.NUEVO);
			
			List<EventoDTO> listaNuevosEventos=audienciaDelegate.consultarEventosParaNotificar(eventoDTO);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=listaNuevosEventos.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
			log.info("ANTES DE ENTRAR AL FOR");
			for (EventoDTO eventoNuevoDTO : listaNuevosEventos) {
				
				log.info("EVENTO"+eventoNuevoDTO);
				writer.print("<row id='" + eventoNuevoDTO.getId()+ "'>");
					if( eventoNuevoDTO.getExpediente() != null && eventoNuevoDTO.getExpediente().getNumeroExpediente() != null){
						writer.print("<cell>" + eventoNuevoDTO.getExpediente().getNumeroExpediente()+ "</cell>");
					}
					else{
						writer.print("<cell>" + "---" + "</cell>");
					}
					if( eventoNuevoDTO.getTipo() != null && eventoNuevoDTO.getTipo().getValor() != null ){
						writer.print("<cell>" + eventoNuevoDTO.getTipo().getValor()+ "</cell>");
					}
					else{
						writer.print("<cell>" + "---" + "</cell>");
					}
					if(eventoNuevoDTO.getTipoEvento() != null && eventoNuevoDTO.getTipoEvento().getNombre() != null){
						
						if(eventoNuevoDTO.getEstatusAudiencia().getIdCampo() == EstatusAudiencia.CANCELADA.getValorId().longValue())
							writer.print("<cell>Audiencia Cancelada</cell>");
						else{
							if(eventoNuevoDTO.getEstatusAudiencia().getIdCampo() == EstatusAudiencia.REPROGRAMADA.getValorId().longValue())
								writer.print("<cell>Audiencia Reprogramada</cell>");
							else
								writer.print("<cell>" + eventoNuevoDTO.getTipoEvento().getNombre()+ "</cell>");
						}
							
						
					}
					else{
						writer.print("<cell>" + "---" + "</cell>");
					}
					if(eventoNuevoDTO.getFechaSolicitud() != null){
						String fechaSolicitud=DateUtils.formatear(eventoNuevoDTO.getFechaSolicitud());
						String horaSolicitud=DateUtils.formatearHora(eventoNuevoDTO.getFechaSolicitud());
						writer.print("<cell>" + fechaSolicitud+" "+horaSolicitud+ "</cell>");
					}
					else{
						writer.print("<cell>" + "---" + "</cell>");
					}
					if( eventoNuevoDTO.getFechaEvento() != null){

						String fechaEvento=DateUtils.formatear(eventoNuevoDTO.getFechaEvento());
						String horaEvento=DateUtils.formatearHora(eventoNuevoDTO.getFechaEvento());
						writer.print("<cell>" + fechaEvento+" "+horaEvento+ "</cell>");
					}
					else{
						writer.print("<cell>" + "---" + "</cell>");
					}
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
	 * Metodo utilizado para realizar del detalle de eventos
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaDetalleNotificaciones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO CONSULTA DETALLE NOTIFICACIONES - CARGA DETALLE DEL EVENTO");
			
			//Se obtiene el id del evento a consultar a detalle
			String idEvento = request.getParameter("idEvento");
			//Se obtiene el tipo de respuesta que se desea, 
			String tipoDeRespuesta = request.getParameter("tipoDeRespuesta");
			
			log.info("_____________________________________________________________");
			log.info("tipo respuesta::::::::::"+tipoDeRespuesta);
			log.info("id del envento::::::::::"+ idEvento);
			log.info("_____________________________________________________________");
			
			EventoDTO eventoDTO = new EventoDTO(); 
			eventoDTO.setId(Long.parseLong(idEvento));
			eventoDTO.setTipoEvento(Eventos.AUDENCIA);
			
			
			if(Integer.parseInt(tipoDeRespuesta) == 1){
				
				log.info("EL TIPO DE RESPUESTA ES EN FORMATO PARA DETALLE DE EVENTO:::::");
				/**
				 * Se desea enviar el objeto evento y en esta, parte de la respuesta
				 * solo llenar los campos que la TAB Detalle evento de la pantalla
				 * atencionSolicitudAudienciaNotificador.jsp
				 */
				
				log.info("antes del delegate:::::");
				eventoDTO = audienciaDelegate.obtenerEvento(eventoDTO);
				request.setAttribute("numeroExpediente", eventoDTO.getExpediente().getNumeroExpediente());
				setExpedienteTrabajo(request, eventoDTO.getExpediente());
				log.info("depues del delegate::: eventoDTO"+ eventoDTO);
				converter.alias("eventoDTO", EventoDTO.class);
				String xml = converter.toXML(eventoDTO);
				escribir(response, xml,null);
				//Se sube a sesion el objeto evento con todos sus atributos
				request.getSession().setAttribute(KEY_SESSION_EVENTO+idEvento, eventoDTO);
			}
			else{
				
				log.info("EL TIPO DE RESPUESTA ES EN FORMATO PARA GRID:::::");				
				/**
				 * En esta parte de la respuesta se desea enviar la informacion del grid,
				 * correspondiente a la TAB notificaciones de la pantalla 
				 * atencionSolicitudAudienciaNotificador.jsp
				 */
				eventoDTO = (EventoDTO) request.getSession().getAttribute(KEY_SESSION_EVENTO+idEvento);

				log.info("OBJETO EVENTO DTO OBTENIDO DE SESION---------------------------------"+eventoDTO);
				ExpedienteDTO expedienteDTO = new ExpedienteDTO();
				log.info("OBJETO EVENTO DTO OBTENIDO DE SESION---------------------------------"+ eventoDTO.getExpediente());
			    
				setExpedienteTrabajo(request, eventoDTO.getExpediente());
				request.setAttribute("numeroExpediente", eventoDTO.getExpediente().getNumeroExpediente());
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				writer.print("<rows>");				
				writer.print("<records>" + eventoDTO.getInvolucrados().size()+ "</records>");
				String institucion = "";
				for (FuncionarioDTO func : eventoDTO.getFuncionarios()) {
					
					log.info("funcioanrio ::: "+func);
					writer.print("<row id='" + func.getClaveFuncionario()+ "'>");
						//Nombre del Involucrado
						writer.print("<cell>" + func.getNombreCompleto()+ "</cell>");
						//Institucion
						switch(TipoEspecialidad.getByValor(func.getTipoEspecialidad().getIdCampo())){
							case DEFENSOR:
								func.getInstitucion().setNombreInst("DEFENSORIA");
								func.getInstitucion().setConfInstitucionId(Instituciones.DEF.getValorId());
								break;
							case MINISTERIO_PUBLICO:
								func.getInstitucion().setNombreInst("PROCURADURIA");
								func.getInstitucion().setConfInstitucionId(Instituciones.PGJ.getValorId());
								break;
							default:
								func.getInstitucion().setNombreInst("PODER JUDICIAL");
								func.getInstitucion().setConfInstitucionId(Instituciones.PJ.getValorId());

								break;
						}
						
						writer.print("<cell>"+func.getInstitucion().getConfInstitucionId()+"</cell>");
						writer.print("<cell>"+func.getInstitucion().getNombreInst()+"</cell>");
						
						//Notificacion Enviada
						String fechaCreacion="";
						String horaCreacion="";
						String fechaRecepcion="";
						String horaRecepcion="";
						
						if( func.getNotificaciones()!= null && func.getNotificaciones().size()!= 0){								
							fechaCreacion=DateUtils.formatear(func.getNotificaciones().get(0).getFechaCreacion());
							horaCreacion=DateUtils.formatearHora(func.getNotificaciones().get(0).getFechaCreacion());
							fechaRecepcion=DateUtils.formatear(func.getNotificaciones().get(0).getFechaCitado());
							horaRecepcion=DateUtils.formatearHora(func.getNotificaciones().get(0).getFechaCitado());
							writer.print("<cell>" + fechaCreacion+" "+horaCreacion+ "</cell>");
							writer.print("<cell>" + fechaRecepcion+" "+horaRecepcion+ "</cell>");
						}else{
							writer.print("<cell> - </cell>");
							writer.print("<cell> - </cell>");
						}

						if( func.getNotificaciones() != null){
							writer.print("<cell>" + func.getNotificaciones().size()+ "</cell>");	
							log.info("FUNCIONARIO :: "+func.getNotificaciones().size()+ "id"+func.getNotificaciones());							
						}
						else{
							writer.print("<cell>0</cell>");
						}
						
						writer.print("<cell>1</cell>");
							
					writer.print("</row>");
				}	
				
				for (InvolucradoDTO invo : eventoDTO.getInvolucrados()) {
						
						log.info("INVOLUCRADO:::"+invo);
						writer.print("<row id='" + invo.getElementoId()+ "'>");
							//Nombre del Involucrado
							writer.print("<cell>" + invo.getNombreCompleto()+ "</cell>");
							//Institucion
							writer.print("<cell>" +((invo.getInstitucionPresenta() != null) ? invo.getInstitucionPresenta().getConfInstitucionId(): "0")+ "</cell>");
							writer.print("<cell>" +((invo.getInstitucionPresenta() != null && invo.getInstitucionPresenta().getNombreInst() != null) ? invo.getInstitucionPresenta().getNombreInst():"")+ "</cell>");
							
							//Notificacion Enviada
							String fechaCreacion="";
							String horaCreacion="";
							String fechaRecepcion="";
							String horaRecepcion="";
							
							if( invo.getNotificaciones()!= null && invo.getNotificaciones().size()!= 0){								
								fechaCreacion=DateUtils.formatear(invo.getNotificaciones().get(0).getFechaCreacion());
								horaCreacion=DateUtils.formatearHora(invo.getNotificaciones().get(0).getFechaCreacion());
								fechaRecepcion=DateUtils.formatear(invo.getNotificaciones().get(0).getFechaCitado());
								horaRecepcion=DateUtils.formatearHora(invo.getNotificaciones().get(0).getFechaCitado());
								writer.print("<cell>" + fechaCreacion+" "+horaCreacion+ "</cell>");
								writer.print("<cell>" + fechaRecepcion+" "+horaRecepcion+ "</cell>");
							}else{
								writer.print("<cell> - </cell>");
								writer.print("<cell> - </cell>");

							}
							if( invo.getNotificaciones() != null){
								writer.print("<cell>" + invo.getNotificaciones().size()+ "</cell>");	
								log.info("INVOLUCRADO :: "+invo.getNotificaciones().size()+ "id"+invo.getNotificaciones());							
							}
							else{
								writer.print("<cell>0</cell>");
							}
							writer.print("<cell>0</cell>");
						writer.print("</row>");
					}			
				writer.print("</rows>");
				writer.flush();
				writer.close();			
			}
			
			

			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR EL EVENTO ---- consultaDetalleNotificaciones");
			log.info(e.getCause(),e);
			escribir(response, "consultaDetalleNotificaciones",null);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para realizar la consulta de eventos por expediente (SOLO HISTÓRCIO) 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaEventosPorExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Consulta Eventos por expediente- HISTORICO");
			
			EventoDTO eventoDTO = new EventoDTO(); 
			eventoDTO.setBandeja(BandejaNotificador.HISTORICO);
			
			
			String numeroExpedienteId = request.getParameter("numeroExpedienteId");
			log.info("ID DEL NUMERO DE EXPEDIENTE="+numeroExpedienteId);
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();	
			expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
			eventoDTO.setExpediente(expedienteDTO);
	
			
			List<EventoDTO> listaNuevosEventos=audienciaDelegate.consultarEventosParaNotificar(eventoDTO);

			log.info("EVENTOs"+listaNuevosEventos);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=listaNuevosEventos.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
				for (EventoDTO eventoNuevoDTO : listaNuevosEventos) {
					
					log.info("EVENTO"+eventoNuevoDTO);
					
					writer.print("<row id='" + eventoNuevoDTO.getId()+ "'>");
						if(eventoNuevoDTO.getExpediente() != null && eventoNuevoDTO.getExpediente().getNumeroExpediente() != null){
							writer.print("<cell>" + eventoNuevoDTO.getExpediente().getNumeroExpediente()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if( eventoNuevoDTO.getTipo() != null && eventoNuevoDTO.getTipo().getValor() != null){
							writer.print("<cell>" + eventoNuevoDTO.getTipo().getValor()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if( eventoNuevoDTO.getTipoEvento() != null && eventoNuevoDTO.getTipoEvento().getNombre() != null ){
							writer.print("<cell>" + eventoNuevoDTO.getTipoEvento().getNombre()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if(eventoNuevoDTO.getFechaSolicitud() != null ){
							String fechaSolicitud=DateUtils.formatear(eventoNuevoDTO.getFechaSolicitud());
							String horaSolicitud=DateUtils.formatearHora(eventoNuevoDTO.getFechaSolicitud());
							writer.print("<cell>" + fechaSolicitud+" "+horaSolicitud+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if( eventoNuevoDTO.getFechaEvento() != null){
							String fechaEvento=DateUtils.formatear(eventoNuevoDTO.getFechaEvento());
							String horaEvento=DateUtils.formatearHora(eventoNuevoDTO.getFechaEvento());
							writer.print("<cell>" + fechaEvento+" "+horaEvento+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
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
	 * Metodo utilizado para realizar el seguimiento a audiencias
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward darSeguimientoEventos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando darSeguimientoAudiencias - SEGUIMENTO");
	
			
			EventoDTO eventoDTO = new EventoDTO(); 
			eventoDTO.setBandeja(BandejaNotificador.SEGUIMIENTO);
			
			String tipoEvento = request.getParameter("eventoTipo");
		
			if(tipoEvento.equalsIgnoreCase("audiencia")){
				log.info("SEGUIMENTO AUDIENCIAS");
				eventoDTO.setTipoEvento(Eventos.AUDENCIA);
			}
			
			if(tipoEvento.equalsIgnoreCase("recurso")){
				log.info("SEGUIMENTO RECURSO");
				eventoDTO.setTipoEvento(Eventos.RECURSO);
			}
	
			List<EventoDTO> listaNuevosEventos=audienciaDelegate.consultarEventosParaNotificar(eventoDTO);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=listaNuevosEventos.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
				for (EventoDTO eventoNuevoDTO : listaNuevosEventos) {
					
					log.info("EVENTO"+eventoNuevoDTO);
					writer.print("<row id='" + eventoNuevoDTO.getId()+ "'>");
						if(eventoNuevoDTO.getExpediente() != null && eventoNuevoDTO.getExpediente().getNumeroExpediente() != null){
							writer.print("<cell>" + eventoNuevoDTO.getExpediente().getNumeroExpediente()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if(eventoNuevoDTO.getTipo() != null &&  eventoNuevoDTO.getTipo().getValor() != null){
							writer.print("<cell>" + eventoNuevoDTO.getTipo().getValor()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if(eventoNuevoDTO.getTipoEvento() != null && eventoNuevoDTO.getTipoEvento().getNombre() != null){
							writer.print("<cell>" + eventoNuevoDTO.getTipoEvento().getNombre()+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
						if( eventoNuevoDTO.getFechaSolicitud() != null){
							String fechaSolicitud=DateUtils.formatear(eventoNuevoDTO.getFechaSolicitud());
							String horaSolicitud=DateUtils.formatearHora(eventoNuevoDTO.getFechaSolicitud());
							writer.print("<cell>" + fechaSolicitud+" "+horaSolicitud+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}						
						if( eventoNuevoDTO.getFechaEvento() != null){
							String fechaEvento=DateUtils.formatear(eventoNuevoDTO.getFechaEvento());
							String horaEvento=DateUtils.formatearHora(eventoNuevoDTO.getFechaEvento());
							writer.print("<cell>" + fechaEvento+" "+horaEvento+ "</cell>");
						}
						else{
							writer.print("<cell>" + "---" + "</cell>");
						}
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
	 * Metodo utilizado para realizar el paso del parametro id del evento
	 * a la JSP de atencionSolicitudAudienciaNotificador
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward acarrearIdEvento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando action acarrearIdEvento");
			
			String idEvento = request.getParameter("idEvento");
			log.info("ID-DEL EVENTO:::"+ idEvento);
			request.setAttribute("idEvento",idEvento);
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}	
	
	/**
	 * Metodo utilizado para realizar la consulta de el detalle de la persona por notificacion 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaDetalleNotificacionesPersona(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {	
			
			log.info("EJECUTANDO CONSULTA DETALLE NOTIFICACIONES PERSONA");
			
			//Se obtiene el id del elemento
			String idInvolucrado = request.getParameter("rowID");
			//Se obtiene el tipo de respuesta que se desea, 
			String idEvento = request.getParameter("idEvento");
			//Se obtiene el tipo de respuesta que se desea 
			String tipoDeRespuesta = request.getParameter("tipoDeRespuesta");
			//Se obtiene el tipo de respuesta que se desea 
			String esFuncionario = request.getParameter("esFuncionario");
			log.info("_____________________________________________________________");
			log.info("id del evento::::::::::"+ idEvento);
			log.info("id del involucrado::::::::::"+ idInvolucrado);
			log.info("tipo respuesta::::::::::"+tipoDeRespuesta);
			log.info("_____________________________________________________________");
			
			if(idEvento != null && idInvolucrado != null && !idInvolucrado.equalsIgnoreCase("") && tipoDeRespuesta != null && !tipoDeRespuesta.equalsIgnoreCase("")){
				
				//Obtenemos el objeto evento de sesion
				EventoDTO eventoDTO = new EventoDTO();			
				eventoDTO = (EventoDTO) request.getSession().getAttribute(KEY_SESSION_EVENTO+idEvento);
				log.info("OBJETO EVENTO DTO OBTENIDO DE SESION---------------------------------"+eventoDTO);
				
				
				if(Integer.parseInt(tipoDeRespuesta) == 1){
					
					log.info("EL TIPO DE RESPUESTA ES EN FORMATO PARA DETALLE DE LOS DATOS DE LA PERSONA:::::");
					/**
					 * Se desea enviar el objeto evento y en esta, parte de la respuesta
					 * solo llenar los campos que la TAB Detalle evento de la pantalla
					 * atencionSolicitudAudienciaNotificador.jsp
					 */
					InvolucradoDTO involucrado = null;
					FuncionarioDTO funcionario = null;
					String xml = "";
					if(esFuncionario.equals("1")){
						for (FuncionarioDTO func : eventoDTO.getFuncionarios()) {					
							if(func.getClaveFuncionario() == Long.parseLong(idInvolucrado)){
								funcionario = func;
								log.info("FUNCIONARIO_DTO, OBTENIDO:::::"+func);
							}
						}
						converter.alias("funcionario", FuncionarioDTO.class);
						xml = converter.toXML(funcionario);
					}else{
						for (InvolucradoDTO invo : eventoDTO.getInvolucrados()) {					
							if(invo.getElementoId() == Long.parseLong(idInvolucrado)){
								involucrado = invo;
								log.info("INVOLUCRADO_DTO, OBTENIDO:::::"+invo);
							}
						}
						converter.alias("involucrado", InvolucradoDTO.class);
						xml = converter.toXML(involucrado);
					}
					

					escribir(response, xml, null);
				}
				else{
					
					log.info("EL TIPO DE RESPUESTA ES EN FORMATO PARA GRID DETALLE NOTIFICACIONES:::::");					
					
					/**
					 * En esta parte de la respuesta se desea enviar la informacion del grid,
					 * correspondiente al detalle de notificaciones de una persona en la ventana modal de  
					 * atencionSolicitudAudienciaNotificador.jsp
					 */
					List<NotificacionDTO> lista = null;
					if(esFuncionario.equals("1")){
						for (FuncionarioDTO func : eventoDTO.getFuncionarios()) {					
							if(func.getClaveFuncionario() == Long.parseLong(idInvolucrado)){
								lista = func.getNotificaciones();
								log.info("FUNCIONARIO_DTO, OBTENIDO:::::"+func);
							}
						}
					}else{
						for (InvolucradoDTO invo : eventoDTO.getInvolucrados()) {					
							if(invo.getElementoId() == Long.parseLong(idInvolucrado)){
								lista = invo.getNotificaciones();
								log.info("INVOLUCRADO_DTO, OBTENIDO:::::"+invo);
							}
						}
					}
					
					log.info("NUMERO DE NOTIFICACIONES DEL INVOLUCRADO_DTO:::::"+lista.size());
					
					response.setContentType("text/xml; charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					
					PrintWriter writer = response.getWriter();
					writer.print("<rows>");
					writer.print("<records>" + lista.size()+ "</records>");
						
					for(NotificacionDTO notificacionDTO : lista){
						
						log.info("NOTIFICACION:::::::::::::::"+notificacionDTO);
						writer.print("<row id='" + notificacionDTO.getDocumentoId()+ "'>");
						
							if(notificacionDTO.getConsecutivoNotificacion() != null){
								writer.print("<cell>" + notificacionDTO.getConsecutivoNotificacion()+ "</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
							
							if(notificacionDTO.getUsuario() != null){
								writer.print("<cell>" + notificacionDTO.getUsuario().getFuncionario().getPersona().getNombreCompleto()+ "</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
								
							if(notificacionDTO.getTipo() != null){
								writer.print("<cell>" + notificacionDTO.getTipo().getNombre()+ "</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
							
							if(notificacionDTO.getFechaCreacion() != null){
								String fechaEnvio=DateUtils.formatear(notificacionDTO.getFechaCreacion());
								String horaEnvio=DateUtils.formatearHora(notificacionDTO.getFechaCreacion());
								writer.print("<cell>" + fechaEnvio +" "+horaEnvio+"</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
							
							if(notificacionDTO.getFechaRecepcion() != null){
								String fechaRecepcion=DateUtils.formatear(notificacionDTO.getFechaRecepcion());
								String horaRecepcion=DateUtils.formatearHora(notificacionDTO.getFechaRecepcion());
								writer.print("<cell>" + fechaRecepcion +" "+ horaRecepcion+"</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
							
							if(notificacionDTO.getNombreDocumento() != null){
								writer.print("<cell>" +notificacionDTO.getNombreDocumento()+"</cell>");
							}
							else{
								writer.print("<cell>" + "---"+ "</cell>");
							}
							
							writer.print("</row>");
					}
				
					writer.print("</rows>");
					writer.flush();
					writer.close();
				}
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para agregar un destinatario
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward agregarDestinatario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("EJECUTANDO ACTION AGREGAR DESTINATARIO");
			
			String idEventoStr = request.getParameter("idEvento");
			String idExpediente = request.getParameter("idExpediente");
			String nombreDest = request.getParameter("nombreDest");
			String apPatDest = request.getParameter("apPatDest");
			String apMatDest = request.getParameter("apMatDest");
			String institucionDest = request.getParameter("institucionDest");
			String dirElectDest = request.getParameter("dirElectDest");
			String pais = request.getParameter("pais");
			String codigoPostal = request.getParameter("codigoPostal");
			String entidadFederativa = request.getParameter("entidadFederativa");
			String ciudad = request.getParameter("ciudad");
			String delegacionMunicipio = request.getParameter("delegacionMunicipio");
			String asentamientoColonia = request.getParameter("asentamientoColonia");
			String tipoAsentamiento = request.getParameter("tipoAsentamiento");
			String tipoCalle = request.getParameter("tipoCalle");
			String calle = request.getParameter("calle");
			String numExterior = request.getParameter("numExterior");
			String numInterior = request.getParameter("numInterior");
			String referencias = request.getParameter("referencias");
			String entreCalle = request.getParameter("entreCalle");
			String ycalle = request.getParameter("ycalle");
			String aliasDomicilio = request.getParameter("aliasDomicilio");
			String edificio = request.getParameter("edificio");
			String longitud = request.getParameter("longitud");
			String latitud = request.getParameter("latitud");
		
			/****************************VERIFICANDO PARAMETROS***********************************/
			log.info("ID DEL EVENTO="+idEventoStr);
			log.info("ID DEL EXPEDIENTE="+idExpediente);
			log.info("NOMBRE DESTINATARIO="+nombreDest);
			log.info("AP PAT DESTINATARIO="+apPatDest);
			log.info("AP MAT DESTINATARIO="+apMatDest);
			log.info("INSTITUCION DESTINATARIO="+institucionDest);
			log.info("DIR ELECTRONICA DESTINATARIO="+dirElectDest);
			log.info("PAIS="+pais);
			log.info("CP="+codigoPostal);
			log.info("ENTIDAD FEDERATIVA="+entidadFederativa);
			log.info("CIUDAD="+ciudad);
			log.info("DELEGACION-MUNICIPIO="+delegacionMunicipio);
			log.info("ASENTAMIENTO COLONIA="+asentamientoColonia);
			log.info("TIPO ASENTAMIENTO="+tipoAsentamiento);
			log.info("TIPO CALLE="+tipoCalle);
			log.info("CALLE="+calle);
			log.info("NUMERO EXTERIOR="+numExterior);
			log.info("NUMERO INTERIOR="+numInterior);
			log.info("REFERENCIAS="+referencias);
			log.info("ENTRE CALLE="+entreCalle);
			log.info("Y CALLE="+ycalle);
			log.info("ALIAS DOMICILIO="+aliasDomicilio);
			log.info("EDIFICIO="+edificio);
			log.info("LONGITUD="+longitud);
			log.info("LATITUD="+latitud);
			
			//Seteamos el id del expedienta
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setExpedienteId(Long.parseLong(idExpediente));
			
			//SETEAMOS LOS DATOS DE LA PERSONA
				//Para setear la calidad
			CalidadDTO calidadDTO = new CalidadDTO();
				//cambiar la calidad del individuo
			calidadDTO.setCalidades(Calidades.SIN_CALIDAD_JURIDICA);
			
				//Nombre
			NombreDemograficoDTO nombre = new NombreDemograficoDTO();
			
			nombre.setNombre(nombreDest);
			nombre.setApellidoPaterno(apPatDest);
			nombre.setApellidoMaterno(apMatDest);
			
			List<NombreDemograficoDTO> nombresDemograficoDTO = new ArrayList<NombreDemograficoDTO>();
			
			
			nombresDemograficoDTO.add(nombre);
			
			InvolucradoDTO involucradoNuevo = new InvolucradoDTO();
			involucradoNuevo.setCalidadDTO(calidadDTO);
			involucradoNuevo.setNombresDemograficoDTO(nombresDemograficoDTO);
				
				//Fecha de creacion del elemento
			involucradoNuevo.setFechaCreacionElemento(new Date());
			
			//CUANDO EL PAIS ES MEXICO
			if((Long.parseLong(pais)==10 || pais.equalsIgnoreCase("mexico") || pais.equalsIgnoreCase("méxico")) && (Long.parseLong(pais)!= -1L)){
				
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				
				//parte izquierda de la pantalla ingresar domicilio				
					//entidad federativa
				if(!entidadFederativa.equalsIgnoreCase("")){
					
					if(Long.parseLong(entidadFederativa)!= -1L ){
						EntidadFederativaDTO entidadDTO = new EntidadFederativaDTO();
						entidadDTO.setEntidadFederativaId(Long.parseLong(entidadFederativa));
						domicilioDTO.setEntidadDTO(entidadDTO);
					}
				}
				
					//ciudad
				if(!ciudad.equalsIgnoreCase("")){
					
					if(Long.parseLong(ciudad)!= -1L ){
						CiudadDTO ciudadDTO = new CiudadDTO();
						ciudadDTO.setCiudadId(Long.parseLong(ciudad));
						domicilioDTO.setCiudadDTO(ciudadDTO);
					}
				}
					//delegacion-municipio
				if(!delegacionMunicipio.equalsIgnoreCase("")){
					
					if(Long.parseLong(delegacionMunicipio)!= -1L ){
						MunicipioDTO municipioDTO = new MunicipioDTO();
						municipioDTO.setMunicipioId(Long.parseLong(delegacionMunicipio));
						domicilioDTO.setMunicipioDTO(municipioDTO);
					}
				}
					
					//asentamiento-colonia
				if(!asentamientoColonia.equalsIgnoreCase("")){
					
					if(Long.parseLong(asentamientoColonia)!= -1L ){
						AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
						asentamientoDTO.setAsentamientoId(Long.parseLong(asentamientoColonia));
						domicilioDTO.setAsentamientoDTO(asentamientoDTO);		
					}
				}
					
					//tipo de calle
				if(tipoCalle.equalsIgnoreCase("")){
					
					if(Long.parseLong(tipoCalle) != -1){
						
						ValorDTO valorCalleId = new ValorDTO(Long.parseLong(tipoCalle));
						domicilioDTO.setValorCalleId(valorCalleId);
					}
				}					
				
				//parte derecha de la pantalla ingresar domicilio
				domicilioDTO.setCalle(calle);
				domicilioDTO.setNumeroExterior(numExterior);
				domicilioDTO.setNumeroInterior(numInterior);
				domicilioDTO.setEntreCalle1(entreCalle);
				domicilioDTO.setEntreCalle2(ycalle);
				domicilioDTO.setAlias(aliasDomicilio);
				domicilioDTO.setEdificio(edificio);
				domicilioDTO.setReferencias(referencias);
				
				if(StringUtils.isNotBlank(longitud)){
					domicilioDTO.setLongitud(longitud);
				}
				else{
					domicilioDTO.setLongitud(null);
				}
				if(StringUtils.isNotBlank(latitud)){
					domicilioDTO.setLatitud(latitud);
				}
				else{
					domicilioDTO.setLatitud(null);
				}
				//Seteamos la fecha de creacion del elemento
				domicilioDTO.setFechaCreacionElemento(new Date());
				
				//seteamos el expediente
				domicilioDTO.setExpedienteDTO(expedienteDTO);
				
				//Seteamos la calidad del domicilio
				CalidadDTO calidadDomicilioDTO = new CalidadDTO();
				calidadDomicilioDTO.setCalidades(Calidades.DOMICILIO);
				domicilioDTO.setCalidadDTO(calidadDomicilioDTO);
				
				//Seteamos el id del aexpediente al elemento persona
				involucradoNuevo.setExpedienteDTO(expedienteDTO);
				//Seteamos el domicilio de notificaciones a la persona
				involucradoNuevo.setDomicilioNotificacion(domicilioDTO);				
	
			}
			//CUANDO EL PAIS NO ES MEXICO
			else{
				
				DomicilioExtranjeroDTO domicilioExtranjeroDTO = new DomicilioExtranjeroDTO();
				
				//Parte izq de la pantalla ingresar domicilio
				if(!pais.equalsIgnoreCase("")){
					if(Long.parseLong(pais)!= -1L){
					
							//id del pais
						domicilioExtranjeroDTO.setPais(pais);
					}
				}
				
				domicilioExtranjeroDTO.setCodigoPostal(codigoPostal);
				domicilioExtranjeroDTO.setEstado(entidadFederativa);
				domicilioExtranjeroDTO.setCiudad(ciudad);
				domicilioExtranjeroDTO.setMunicipio(delegacionMunicipio);
				domicilioExtranjeroDTO.setAsentamientoExt(asentamientoColonia);
				
				//parte derecha de la pantalla ingresar domicilio
				domicilioExtranjeroDTO.setCalle(calle);
				domicilioExtranjeroDTO.setNumeroExterior(numExterior);
				domicilioExtranjeroDTO.setNumeroInterior(numInterior);
				domicilioExtranjeroDTO.setEntreCalle1(entreCalle);
				domicilioExtranjeroDTO.setEntreCalle2(ycalle);
				domicilioExtranjeroDTO.setAlias(aliasDomicilio);
				domicilioExtranjeroDTO.setEdificio(edificio);
				domicilioExtranjeroDTO.setReferencias(referencias);
				if(!longitud.equalsIgnoreCase("")){
					domicilioExtranjeroDTO.setLongitud(longitud);
				}
				else{
					domicilioExtranjeroDTO.setLongitud(null);
				}
				if(!latitud.equalsIgnoreCase("")){
					domicilioExtranjeroDTO.setLatitud(latitud);
				}
				else{
					domicilioExtranjeroDTO.setLatitud(null);
				}
				//Seteamos la fecha de creacion del elemento
				domicilioExtranjeroDTO.setFechaCreacionElemento(new Date());
				
				//Seteamos el expediente
				domicilioExtranjeroDTO.setExpedienteDTO(expedienteDTO);
				
				//Seteamos la calidad del domicilio
				CalidadDTO calidadDomicilioExtranjeroDTO = new CalidadDTO();
				calidadDomicilioExtranjeroDTO.setCalidades(Calidades.DOMICILIO);
				domicilioExtranjeroDTO.setCalidadDTO(calidadDomicilioExtranjeroDTO);
				
				involucradoNuevo.setExpedienteDTO(expedienteDTO);
				//Seteamos el domicilio extranjero de notificaciones a la persona
				involucradoNuevo.setDomicilioNotificacion(domicilioExtranjeroDTO);
		}
		
			//Delegate para ingresar
			involucradoNuevo.setElementoId(involucradoDelegate.guardarInvolucrado(involucradoNuevo));
			
			Long idEvento = NumberUtils.toLong(idEventoStr,0L);
			if(idEvento > 0){
				audienciaDelegate.asociarInvolucradoAAudiencia(involucradoNuevo.getElementoId(), idEvento);
			}
			
			String xml = converter.toXML("ok");
			escribir(response, xml,null);
			
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;		
	}

}

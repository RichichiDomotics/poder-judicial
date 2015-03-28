package mx.gob.segob.nsjp.web.atencionTempranaPenal.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AdministracionSolicitudesUAVDAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(AdministracionSolicitudesUAVDAction.class);
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate;

	
	
	/**
	 * Metodo utilizado para consultar las solicitudes UAVD en status de 
	 * no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesUAVDNoAtendidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR SOLICITUDES UAVD NO ATENDIDAS");
			String tipoSolicitud = request.getParameter("tipoSolicitud");
			
			if(tipoSolicitud != null){				
				Long tipoSol = Long.parseLong(tipoSolicitud);
				List<SolicitudDTO> listaSolicitudesNoAtendidas = (List<SolicitudDTO>) solicitudDelegate.consultarSolicitudXEstatus(EstatusSolicitud.ABIERTA, getUsuarioFirmado(request), TiposSolicitudes.ATENCION_A_VICTIMAS_DEL_DELITO);
				if (log.isDebugEnabled()){
				    log.debug("SOLICITUDES NO ATENDIDAS" + listaSolicitudesNoAtendidas);
				}
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");

				int lTotalRegistros = listaSolicitudesNoAtendidas.size();

				writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudDTO solicitudDTO : listaSolicitudesNoAtendidas) {
				    if (log.isDebugEnabled()){
				        log.debug("SOLICITUD UAVD DTO" + solicitudDTO);
				    }
						writer.print("<row id='"+ solicitudDTO.getDocumentoId() + "'>");
						//Fecha y hora de la solicitud
						writer.print("<cell>"+ (solicitudDTO.getFechaCreacion()!=null ? DateUtils.formatear(solicitudDTO.getFechaCreacion()) + ", " +
								DateUtils.formatearHora(solicitudDTO.getFechaCreacion()):"-") +  "</cell>");
						//Tipo de solicitud
						writer.print("<cell>"+ (solicitudDTO.getTipoSolicitudDTO()!=null ? solicitudDTO.getTipoSolicitudDTO().getValor():"-") +  "</cell>");
						//Para la celda del caso
						writer.print("<cell>"+ (solicitudDTO.getExpedienteDTO() != null && 
								solicitudDTO.getExpedienteDTO().getCasoDTO()!= null &&
								solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso() != null? 
										solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"-") +  "</cell>");
						//Para la celda del nombre de la victima
						List<InvolucradoDTO> loInvolucrados = solicitudDTO.getExpedienteDTO().getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA);
						if(loInvolucrados != null && loInvolucrados.size() > 0)
							writer.print("<cell>"+ loInvolucrados.get(0).getNombreCompleto() +"</cell>");
						else
							writer.print("<cell>"+ "-" +"</cell>");
						//Para la celda de los delitos
						if(solicitudDTO.getExpedienteDTO() != null && solicitudDTO.getExpedienteDTO().getDelitos() != null){
							String delitosDelExp = "";
							for (DelitoDTO delito : solicitudDTO.getExpedienteDTO().getDelitos()) {									
								delitosDelExp+= delito.getNombreDelito() + ", ";						
							}									
							if(delitosDelExp.lastIndexOf(",")>=0){
								delitosDelExp= delitosDelExp.substring(0, delitosDelExp.lastIndexOf(","));
								writer.print("<cell>"+ delitosDelExp +"</cell>");									
							}else{
								writer.print("<cell>"+ "-" +"</cell>");	
							}
						}else{
							writer.print("<cell>"+ "-" +"</cell>");	
						}
						
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;	

	}
	
	
	/**
	 * Metodo utilizado para consultar las solicitudes UAVD en status de 
	 * pendientes, o en proceso
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesUAVDEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				
				log.info("EJECUTANDO ACTION CONSULTAR SOLICITUDES UAVD EN PROCESO");
				String tipoSolicitud = request.getParameter("tipoSolicitud");
				
				if(tipoSolicitud != null){				
					Long tipoSol = Long.parseLong(tipoSolicitud);
//			
					List<SolicitudDTO> listaSolicitudesNoAtendidas = (List<SolicitudDTO>) solicitudDelegate.consultarSolicitudXEstatus(EstatusSolicitud.EN_PROCESO, getUsuarioFirmado(request), TiposSolicitudes.ATENCION_A_VICTIMAS_DEL_DELITO);
					if (log.isDebugEnabled()){
					    log.debug("SOLICITUDES EN PROCESO" + listaSolicitudesNoAtendidas);
					}
					
					response.setContentType("text/xml; charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					PrintWriter writer = response.getWriter();

					writer.print("<rows>");

					int lTotalRegistros = listaSolicitudesNoAtendidas.size();

					writer.print("<records>" + lTotalRegistros + "</records>");
					for (SolicitudDTO solicitudDTO : listaSolicitudesNoAtendidas) {
					    if (log.isDebugEnabled()){
					        log.debug("SOLICITUD UAVD DTO" + solicitudDTO);
					    }
							writer.print("<row id='"+ solicitudDTO.getDocumentoId() + "'>");
							//Fecha y hora de la solicitud
							writer.print("<cell>"+ (solicitudDTO.getFechaCreacion()!=null ? DateUtils.formatear(solicitudDTO.getFechaCreacion()) + ", " +
									DateUtils.formatearHora(solicitudDTO.getFechaCreacion()):"-") +  "</cell>");
							//Tipo de solicitud
							writer.print("<cell>"+ (solicitudDTO.getTipoSolicitudDTO()!=null ? solicitudDTO.getTipoSolicitudDTO().getValor():"-") +  "</cell>");
							//Para la celda del caso
							writer.print("<cell>"+ (solicitudDTO.getExpedienteDTO() != null && 
									solicitudDTO.getExpedienteDTO().getCasoDTO()!= null &&
									solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso() != null? 
											solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"-") +  "</cell>");
							//Para la celda del nombre de la victima
							List<InvolucradoDTO> loInvolucrados = solicitudDTO.getExpedienteDTO().getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA);
							if(loInvolucrados != null && loInvolucrados.size() > 0)
								writer.print("<cell>"+ loInvolucrados.get(0).getNombreCompleto() +"</cell>");
							else
								writer.print("<cell>"+ "-" +"</cell>");
							//Para la celda de los delitos
							if(solicitudDTO.getExpedienteDTO() != null && solicitudDTO.getExpedienteDTO().getDelitos() != null){
								String delitosDelExp = "";
								for (DelitoDTO delito : solicitudDTO.getExpedienteDTO().getDelitos()) {									
									delitosDelExp+= delito.getNombreDelito() + ", ";						
								}									
								if(delitosDelExp.lastIndexOf(",")>=0){
									delitosDelExp= delitosDelExp.substring(0, delitosDelExp.lastIndexOf(","));
									writer.print("<cell>"+ delitosDelExp +"</cell>");									
								}else{
									writer.print("<cell>"+ "-" +"</cell>");	
								}
							}else{
								writer.print("<cell>"+ "-" +"</cell>");	
							}
							
						writer.print("</row>");
					}
					writer.print("</rows>");
					writer.flush();
					writer.close();
				}
				
			} catch (Exception e) {		
				log.info(e.getCause(),e);
				
			}
			return null;	
	}
	
	/**
	 * Metodo para consultar el detalle de una solicitud
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaDetalleDeSolicitudPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
						
			log.info("EJECUTANDO CONSULTA DETALLE SOLICITUDES");
			
			//Se obtiene el id de la solicitud a consultar 
			String solicitudId = request.getParameter("solicitudId");			
			log.info("id de la solicitud::::::::::"+ solicitudId);						
			SolicitudDTO solicitudDTO = new SolicitudDTO(); 
			solicitudDTO.setDocumentoId(Long.parseLong(solicitudId));
											
			SolicitudDTO solicitudDTO2 = solicitudDelegate.obtenerDetalleSolicitud(solicitudDTO);
			log.info("depues del delegate::: solicitudDTO" + solicitudDTO2);
			converter.alias("solicitudDTO", SolicitudDTO.class);
			String xml = converter.toXML(solicitudDTO2);
			log.info("xml de la solicitud respuesta: :::::::::"+ solicitudId);
			
			//Se sube a session el numero de expediente
			//super.setExpedienteTrabajo(request,solicitudDTO2.getExpedienteDTO());

			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR LA SOLICITUD ---- consultaDetalleDeSolicitudPorId");
			log.info(e.getCause(),e);
			escribir(response, "consultaDetalleDeSolicitudPorId",null);
			
		}
		return null;
	}
	
	public ActionForward actualizaEstatusSolicitudUAVD(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("designar cambio estatus");
		   
		    SolicitudDTO solicitudDTO = new SolicitudDTO();
		    String numSolicitud = request.getParameter("numSolicitud");		    
		   
		    solicitudDTO.setDocumentoId(Long.parseLong(numSolicitud));
		   
		    log.info("designar cambio estatus::::: "+ numSolicitud);  

		    solicitudDelegate.actualizaEstatusSolicitud(solicitudDTO , EstatusSolicitud.EN_PROCESO);	
		    
		   
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para crear un nuevo numero de expediente
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward asignarNumeroExpedienteUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setUsuario(super.getUsuarioFirmado(request));
			expedienteDTO.setArea(new AreaDTO(Areas.UNIDAD_INVESTIGACION));
			expedienteDTO.setFechaApertura(new Date());			
								
			expedienteDTO = expedienteDelegate.asignarNumeroExpediente(expedienteDTO);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			escribir(response, xml,null);
				
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
		}
		return null;
	}
	
	
}

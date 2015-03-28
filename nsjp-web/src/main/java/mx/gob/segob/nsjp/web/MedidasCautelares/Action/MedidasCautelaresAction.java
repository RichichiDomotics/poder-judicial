package mx.gob.segob.nsjp.web.MedidasCautelares.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.enums.documento.Periodicidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.agenda.AgendaDelegate;
import mx.gob.segob.nsjp.delegate.compromiso.CompromisoDelegate;
import mx.gob.segob.nsjp.delegate.medidasalternas.MedidasAlternasDelegate;
import mx.gob.segob.nsjp.delegate.medidascautelares.MedidasCautelaresDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class MedidasCautelaresAction extends GenericAction{
	
	/** Log de clase */
	private static final Logger log = Logger.getLogger(MedidasCautelaresAction.class);
	
	private MedidasAlternasDelegate medidasAlternasDelegate;
	
	@Autowired
	private MedidasCautelaresDelegate medidasCautelaresDelegate;
	
	@Autowired
	AgendaDelegate agendaDelegate;
	
	@Autowired
	CompromisoDelegate compromisoDelegate;

	/**
	 * Método para obtener las solicitar programarAudienciasCarpetaPreliberacion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultaGridMedidasCautelaresAlternativas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("consultaGridMedidasCautelaresAlternativas");
		log.info("Action de Consultar beneficios datos:::::");
		String numeroExpedienteId = request.getParameter("numeroExpedienteId");
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(Long.parseLong(numeroExpedienteId));
		
		try {
			List<MedidaAlternaDTO> medidasAlternas = medidasAlternasDelegate.consultarMedidasAlternasPorNumeroExpediente(expedienteDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");

			final PaginacionDTO pag = PaginacionThreadHolder.get();
			log.debug("pag :: " + pag);
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }
            
//				Número de causa
//				- Número de carpeta de ejecución
//				- Nombre
//				- Descripcion
//				- Periodicidad
				for (MedidaAlternaDTO medidaAlterna : medidasAlternas) {
					log.info("/******** ::::: /" + medidaAlterna);
					writer.print("<row id='" + medidaAlterna.getDocumentoId() +"'>");
					writer.print("<cell>"+medidaAlterna.getInvolucrado().getNombreCompleto() + "</cell>");
					writer.print("<cell>"+medidaAlterna.getNombreDocumento() + "</cell>");
					writer.print("<cell>"+medidaAlterna.getDescripcionMedida() + "</cell>");	
					writer.print("<cell>"+medidaAlterna.getValorPeriodicidad().getValor() + "</cell>");
					writer.print("<cell>"+medidaAlterna.getCompromisoPeriodico() + "</cell>");	
					writer.print("<cell>"+medidaAlterna.getSeguimiento() + "</cell>");
					
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}

		
		return null;
		
	}

	/**
	 * Método para obtener las Medidas Cautelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultaGridMedidasCautelares(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar Medidas Cautelares:::::");
		Long estatus = new Long(request.getParameter("estatus"));
		log.debug("estatus ... " + estatus);
		MedidaCautelarDTO medidaCautelarDTO = new MedidaCautelarDTO();
		ValorDTO valorDTO = new ValorDTO();
		valorDTO.setIdCampo(estatus);
		medidaCautelarDTO.setEstatus(valorDTO);
		
		try {
			List<MedidaCautelarDTO> medidasCautelaresDTOs = medidasCautelaresDelegate.consultaMedidasCautelaresPorEstatus(medidaCautelarDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + medidasCautelaresDTOs.size() + "</records>");
				for (MedidaCautelarDTO medidacautelarDTO : medidasCautelaresDTOs) {
					log.info("/******** ::::: /" + medidacautelarDTO);
					writer.print("<row id='" + medidacautelarDTO.getDocumentoId() +"'>");
					writer.print("<cell>"+medidacautelarDTO.getInvolucrado().getNombreCompleto()+"</cell>");
					if(medidacautelarDTO.getExpedienteDTO()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getExpedienteDTO().getNumeroExpediente()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCaso()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCaso()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCausa()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCausa() + "</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					//writer.print("<cell>"+medidacautelarDTO.getNumeroCarpetaEjecucion() + "</cell>");
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;		
	}

	
	/**
	 * Método para obtener las Medidas Cautelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultaGridMedidasCautelaresIncumplidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action consultaGridMedidasCautelaresIncumplidas:::::");
		try {
			List<MedidaCautelarDTO> medidasCautelaresDTOs = medidasCautelaresDelegate.obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior();
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + medidasCautelaresDTOs.size() + "</records>");
				for (MedidaCautelarDTO medidacautelarDTO : medidasCautelaresDTOs) {
					log.info("/******** ::::: /" + medidacautelarDTO);
					writer.print("<row id='" + medidacautelarDTO.getDocumentoId() +"'>");
					writer.print("<cell>"+medidacautelarDTO.getInvolucrado().getNombreCompleto()+"</cell>");
					if(medidacautelarDTO.getExpedienteDTO()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getExpedienteDTO().getNumeroExpediente()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCaso()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCaso()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCausa()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCausa() + "</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					writer.print("<cell>"+medidacautelarDTO.getValorTipoMedida().getNombreCampo() + "</cell>");
					//writer.print("<cell>"+medidacautelarDTO.getNumeroCarpetaEjecucion() + "</cell>");
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;		
	}
	
	/**
	 * Método para cambiar el estatus de las Medidas Cautelares
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public void cambiarEstatusMedida(Long id, Long estatus)
			throws IOException {
		
		log.info("Método para cambiar estatus de Medida Cautelar:::::");

		try {
			medidasCautelaresDelegate.cambiarEstatusMedida(id, estatus);			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
	}

	/**
	 * Método para consultar una Medida Cautelar por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarMedidaCautelarPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de consultar Medida Cautelar por id:::::");

		Long medidaCautelarId = new Long(request.getParameter("medidaCautelarId"));
		Long estatusId = new Long(request.getParameter("estatusId"));
		
		log.debug("medidaCautelarId... " + medidaCautelarId);
		log.debug("estatusId ... " + estatusId);
		
		try {
			MedidaCautelarDTO medidaCautelarDTO = new MedidaCautelarDTO();
			medidaCautelarDTO = medidasCautelaresDelegate.consultarMedidasCautelaresPorId(medidaCautelarId);
			
			converter.alias("MedidaCautelarDTO", MedidaCautelarDTO.class);
			String xml = converter.toXML(medidaCautelarDTO);
			log.debug("xml consultarMedidaCautelarPorId() ... " + xml);
			escribir(response, xml,null);
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;
	}

	/**
	 * Método para guardar los días de compromiso en la base de datos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward generarCalendarizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Generar Calendarizacion:::::");

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		Long medidaCautelarId = new Long(request.getParameter("medidaCautelarId"));
		Long periodicidadId = new Long(request.getParameter("periodicidadId"));
		Long usuarioId = getUsuarioFirmado(request).getFuncionario().getClaveFuncionario();
		String strFechaInicio = request.getParameter("fechaInicio");
		String strFechaFin = request.getParameter("fechaFin");
		Long estatus = new Long(request.getParameter("estatus"));
		
		log.debug("medidaCautelarId... " + medidaCautelarId);
		log.debug("estatus... " + estatus);
		log.debug("periodicidadId ... " + periodicidadId);
		log.debug("usuarioId ... " + usuarioId);
		log.debug("fechaInicio ... " + strFechaInicio);
		log.debug("fechaFin ... " + strFechaFin);
		
		try {
			//cambiamos el estatus de la medida cautelar
			if(estatus.equals(EstatusMedida.NO_ATENDIDA.getValorId())){
				log.info("Cambiando estatus a en proceso...changeStatusCuatelar...");
				cambiarEstatusMedida(medidaCautelarId, EstatusMedida.EN_PROCESO.getValorId());
				log.info("Cambie estatus a: en proceso...changeStatusCuatelar...");
			}
			
			Date dFechaInicio = (Date)formatter.parse(strFechaInicio);
			Calendar calFechaInicio = Calendar.getInstance();
			calFechaInicio.setTime(dFechaInicio);
			
			Date dFechaFin = (Date)formatter.parse(strFechaFin);
			Calendar calFechaFin = Calendar.getInstance();
			calFechaFin.setTime(dFechaFin);
			
			agendaDelegate.generarCalendarizacion(calFechaInicio, calFechaFin, Periodicidad.getByValor(periodicidadId), 
												  medidaCautelarId, usuarioId);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		} catch (ParseException e1) {
			log.error(e1);
		}  
			
		return null;
	}

	/**
	 * Método para consultar los días de cmpromiso en base a una medida seleccionada para asi poder armar el grid de seguimiento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultarCalendarizacionPorMedidaIdReducido(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar Calendarizacion por Medida Id:::::");

		Long medidaCautelarId = new Long(request.getParameter("medidaCautelarId"));
		
		log.debug("medidaCautelarId... " + medidaCautelarId);
		
		try {
			List<FechaCompromisoDTO> fechaCompromisoDTOs = new ArrayList<FechaCompromisoDTO>(); 
			fechaCompromisoDTOs = 
				agendaDelegate.consultarCalendarizacionPorMedidaIdReducido(medidaCautelarId);
			
			log.debug("fechaCompromisoDTOs.size() ... " + fechaCompromisoDTOs.size());
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");
			
			writer.print("<records>" + fechaCompromisoDTOs.size() + "</records>");
			
			for(FechaCompromisoDTO fechaCompromisoDTO : fechaCompromisoDTOs){
				//Id de fechaCompromiso
				writer.print("<row id='" + fechaCompromisoDTO.getFechaCompromisoId() + "'>");							
			
				//Descripcion del compromiso. 
				//Unicamente sirve para generar una columna para el boton de registro
				writer.print("<cell>");
				if(fechaCompromisoDTO.getDescripcionCompromiso() != null){
					writer.print(fechaCompromisoDTO.getDescripcionCompromiso());
				}
				writer.print("</cell>");
			
				//Fecha Compromiso [dd/MM/yyy] 
				writer.print("<cell>");
				if(fechaCompromisoDTO.getFechaCompromiso() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(fechaCompromisoDTO.getFechaCompromiso());
					int day = cal.get(Calendar.DATE);
			        int month = cal.get(Calendar.MONTH) + 1;
			        int year = cal.get(Calendar.YEAR);
					writer.print((day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year);
				}
				writer.print("</cell>");
			
				//Fecha Cumplimiento [HH:mm]
				writer.print("<cell>");
				if(fechaCompromisoDTO.getFechaCumplimiento() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(fechaCompromisoDTO.getFechaCumplimiento());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int minutes = cal.get(Calendar.MINUTE);
					writer.print((hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes));
				}
				writer.print("</cell>");
				writer.print("</row>");					
			}
					
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}  
			
		return null;
		
	}

	/**
	 * Método para actualizar la hora del cumplimiento de un compromiso de una medida cautelar determinada
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward actualizarCumplimiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Actualizar Cumplimiento:::::");

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Long fechaCompromisoId = new Long(request.getParameter("fechaCompromisoId"));
		String strFechaCumplimiento = request.getParameter("fechaCumplimiento");
		
		log.debug("fechaCompromisoId... " + fechaCompromisoId);
		log.debug("strFechaCumplimiento ... " + strFechaCumplimiento);
		
		try {
			Date dFechaCumplimiento = (Date)formatter.parse(strFechaCumplimiento);
			FechaCompromisoDTO fechaCompromisoDTO = new FechaCompromisoDTO();
			fechaCompromisoDTO.setFechaCompromisoId(fechaCompromisoId);
			fechaCompromisoDTO.setFechaCumplimiento(dFechaCumplimiento);
			
			agendaDelegate.actualizarFechaCumplimiento(fechaCompromisoDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		} catch (ParseException e1) {
			log.error(e1);
		} 
			
		return null;
	}
	
	public ActionForward consultarCalendarizacionPorMedidaId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de Consultar Calendarizacion por Medida Id:::::");

		Long medidaCautelarId = new Long(request.getParameter("medidaCautelarId"));
		
		log.debug("medidaCautelarId... " + medidaCautelarId);
		
		try {
			MedidaCautelarDTO medidaDTO = new MedidaCautelarDTO();
			medidaDTO.setDocumentoId(medidaCautelarId);
			CompromisoPeriodicoDTO compromisoDTO = new CompromisoPeriodicoDTO(); 
			compromisoDTO = 
				compromisoDelegate.obtenerCompromisoPeriodico(medidaDTO);
			
			log.debug("compromisoDTO.getFechaCompromisos().size() ... " + compromisoDTO.getFechaCompromisos().size());
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			String xml = null;
			converter.alias("compromisoDTO", CompromisoPeriodicoDTO.class);
			xml = converter.toXML(compromisoDTO);
			response.setContentType("text/xml");
			log.info("xml de consultarCalendarizacionPorMedidaId: " + xml );	
			writer = response.getWriter();
			writer.print(xml);
			
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}  
			
		return null;
		
	}
	
	
	/**
	 * Método para guardar los días de compromiso en la base de datos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward cancelarMedida(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action de cambiar estatus de medida cautelar o alterna:::::");

		try {
			Long medidaCautelarId = new Long(request.getParameter("medidaCautelarId"));
			log.debug("medidaCautelarId... " + medidaCautelarId);
			
			log.info("cancelando medida...");
			cambiarEstatusMedida(medidaCautelarId, EstatusMedida.CANCELADA.getValorId());
			log.info("medida cancelada");
			
			escribirRespuesta(response, "<bandera>1</bandera>");
			
		} catch (Exception e1) {
			escribirRespuesta(response, "<bandera>0</bandera>");
			log.error(e1);
		}  
			
		return null;
	}
	
	/**
	 * Método para obtener las Medidas Alternas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ok - En caso de que el proceso se cumpla correctamente
	 */
	public ActionForward consultaGridMedidasAlternasIncumplidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Action consultaGridMedidasCautelaresIncumplidas:::::");
		try {
			List<MedidaAlternaDTO> medidasAlternasDTOs = medidasAlternasDelegate.obtenerMedidasAlternasIncumplidasDelDiaAnterior();
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
				writer.print("<records>" + medidasAlternasDTOs.size() + "</records>");
				for (MedidaAlternaDTO medidacautelarDTO : medidasAlternasDTOs) {
					log.info("/******** ::::: /" + medidacautelarDTO);
					writer.print("<row id='" + medidacautelarDTO.getDocumentoId() +"'>");
					writer.print("<cell>"+medidacautelarDTO.getInvolucrado().getNombreCompleto()+"</cell>");
					if(medidacautelarDTO.getExpedienteDTO()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getExpedienteDTO().getNumeroExpediente()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCaso()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCaso()+"</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					if(medidacautelarDTO.getNumeroCausa()!=null)
					{
						writer.print("<cell>"+medidacautelarDTO.getNumeroCausa() + "</cell>");
					}
					else
					{
						writer.print("<cell>---</cell>");
					}
					writer.print("<cell>"+medidacautelarDTO.getValorTipoMedida().getNombreCampo() + "</cell>");
					//writer.print("<cell>"+medidacautelarDTO.getNumeroCarpetaEjecucion() + "</cell>");
					writer.print("</row>");
				} 
									
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (NSJPNegocioException e) {
			log.error(e);
		}
		
		return null;		
	}
	
	
}

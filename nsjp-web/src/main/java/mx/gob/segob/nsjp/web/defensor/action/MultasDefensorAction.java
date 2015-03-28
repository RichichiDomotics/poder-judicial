package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.inspeccion.InspeccionDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

public class MultasDefensorAction extends GenericAction {

	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(MultasDefensorAction.class);
	
	@Autowired
	public InspeccionDelegate inspeccionDelegate;

	public ActionForward registrarMultaDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		try {
			log.info("Entra multa :::::::");
			String motivoMulta = request.getParameter("motivoMulta");
			log.info("motivoMulta======" + motivoMulta);
			
			String descripcionMulta = request.getParameter("descripcionMulta");
			log.info("descripcionMulta====" + descripcionMulta);

			Long defensorId = new Long(request.getParameter("defensorId"));
			log.info("defensorId====" + defensorId);

			String multaRequest = request.getParameter("multaId");
			Long multaId=null; 
			log.info("multaRequest====" + multaRequest);
			if(multaRequest.trim() != null && multaRequest.trim() != "0"){
				log.info("multaRequest.trim()====" + multaRequest.trim());
				multaId = new Long(request.getParameter("multaId"));
				log.info("multaId if====" + multaId);
			}


			log.info("MultaSancionDTO====");
			MultaSancionDTO multaDTO = new MultaSancionDTO();
			multaDTO.setMotivo(motivoMulta);
			multaDTO.setDescripcion(descripcionMulta);
			
			FuncionarioDTO funcionarioMultado = new FuncionarioDTO();
			funcionarioMultado.setClaveFuncionario(defensorId);
			multaDTO.setFuncionarioMultado(funcionarioMultado);
			
			UsuarioDTO usuarioRegistra = super.getUsuarioFirmado(request);
			multaDTO.setFuncionarioRegistra(usuarioRegistra.getFuncionario());
			
			Long multa=null;
			if(multaId != null && multaId >0L){
				log.info("ACTUALIZA====");
				multaDTO.setMultaSancionId(multaId);
				inspeccionDelegate.actualizarDescripcion(multaDTO);
			}else{
				log.info("REGISTRA====");
				multa = inspeccionDelegate.registrarMulta(multaDTO);
				log.info("Regreso de la multa" + multa);	
			}
			String xml = null;
			PrintWriter pw = null;
			converter.alias("multaDTO", MultaSancionDTO.class);
			xml = converter.toXML(multa);
			log.info("XML Multa--- "+xml);
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
	
	public ActionForward consultarMultasPorDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Long defensorId = Long.parseLong(request.getParameter("defensorId"));
			log.info("multas defensorId---- "+defensorId);

			List<MultaSancionDTO> multaDTOs = new ArrayList<MultaSancionDTO>();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			funcionarioDTO.setClaveFuncionario(defensorId);
			multaDTOs = inspeccionDelegate.consultarMultas(funcionarioDTO);
			log.info("multaDTOs.size -- " + multaDTOs.size());
			
			
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
            
			for(MultaSancionDTO multaSancionDTO : multaDTOs){

				writer.print("<row id='"+multaSancionDTO.getMultaSancionId()+ "'>");
				writer.print("<cell>"+ multaSancionDTO.getFolioMultaSancion()+  "</cell>");
				writer.print("<cell>"+ multaSancionDTO.getMotivo()+  "</cell>");
				
				writer.print("<cell>");
				if(multaSancionDTO.getFechaRegistro() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(multaSancionDTO.getFechaRegistro());
					int day = cal.get(Calendar.DATE);
			        int month = cal.get(Calendar.MONTH) + 1;
			        int year = cal.get(Calendar.YEAR);
			        String fecha = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year; 

					int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int minutes = cal.get(Calendar.MINUTE);
			        String hora = (hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes);
			        
			        writer.print(fecha +"  "+hora);
				}
				writer.print("</cell>");
				
				writer.print("<cell>"+ multaSancionDTO.getDescripcion()+  "</cell>");

				writer.print("<cell>");
				if(multaSancionDTO.getEstatus() != null){
					writer.print(multaSancionDTO.getEstatus().getValor());
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

	public ActionForward consultarMultaDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra multa LA CONSULTA:::::::");
		Long idMulta = NumberUtils.toLong(request.getParameter("multaId")) ;
		log.info("idMulta pasa idMulta====" + idMulta);
		
		try {
			log.info("Entra Multa :::::::");
			MultaSancionDTO multaSancionDTO = new MultaSancionDTO();
			
			multaSancionDTO = inspeccionDelegate.obtenerMulta(idMulta);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("multaSancionDTO", MultaSancionDTO.class);
			xml = converter.toXML(multaSancionDTO);
			log.info("Termina multa:::xml::::"+xml);
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
	
	public ActionForward saldarMultaDefensor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra multa saldar:::::::");
		Long idMulta = NumberUtils.toLong(request.getParameter("multaId")) ;
		log.info("idMulta pasa idMulta====" + idMulta);
		
		try {
			log.info("Entra Multa :::::::");
			MultaSancionDTO multaSancionDTO = new MultaSancionDTO();
			multaSancionDTO.setMultaSancionId(idMulta);
			inspeccionDelegate.saldarMulta(multaSancionDTO);
			
			String xml = null;
			PrintWriter pw = null;
			xml = converter.toXML(multaSancionDTO);
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
	
	public ActionForward registrarInspeccionExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra inspeccion :::::::");
		String motivoInspeccion = request.getParameter("motivoInspeccion");
		log.info("motivoInspeccion======" + motivoInspeccion);
		
		String descripcionInspeccion = request.getParameter("descripcionInspeccion");
		log.info("descripcionInspeccion====" + descripcionInspeccion);

		Long multaAsociadaId = new Long(request.getParameter("multaAsociadaId"));
		log.info("multaAsociadaId====" + multaAsociadaId);

		Long defensorId = new Long(request.getParameter("defensorId"));
		log.info("defensorId====" + defensorId);

		Long numeroExpedienteId = new Long(request.getParameter("numeroExpedienteId"));
		log.info("numeroExpedienteId====" + numeroExpedienteId);
		
		String inspeccionReuquest = request.getParameter("inspeccionId");
		Long inspeccionId = 0L;
		if(inspeccionReuquest != null && inspeccionReuquest != "0"){
			inspeccionId = new Long(request.getParameter("inspeccionId"));
			log.info("inspeccionId====" + inspeccionId);
		}

		try {
			InspeccionDTO inspeccionDTO = new InspeccionDTO();
			inspeccionDTO.setMotivo(motivoInspeccion);
			inspeccionDTO.setDescripcion(descripcionInspeccion);
			
			MultaSancionDTO multaSancionDTO = new MultaSancionDTO();
			multaSancionDTO.setMultaSancionId(multaAsociadaId);
			inspeccionDTO.setMultaSancion(multaSancionDTO);
			
			FuncionarioDTO funcionarioInspeccionado = new FuncionarioDTO();
			funcionarioInspeccionado.setClaveFuncionario(defensorId);
			inspeccionDTO.setFuncionarioInspeccionado(funcionarioInspeccionado);
			
			UsuarioDTO usuarioRegistra = super.getUsuarioFirmado(request);
			inspeccionDTO.setFuncionarioRegistra(usuarioRegistra.getFuncionario());

			ExpedienteDTO expedienteInspeccionado = new ExpedienteDTO();
			expedienteInspeccionado.setNumeroExpedienteId(numeroExpedienteId);
			inspeccionDTO.setExpediente(expedienteInspeccionado);

			Long inspeccion=null;
			if(inspeccionId != null && inspeccionId > 0L){
				inspeccionDTO.setInspeccionId(inspeccionId);
				inspeccionDelegate.actualizarDescripcion(inspeccionDTO);
			}else{
				inspeccion = inspeccionDelegate.registrarInspeccion(inspeccionDTO);
				log.info("Regreso de la inspeccion" + inspeccion);	
			}
			String xml = null;
			PrintWriter pw = null;
			converter.alias("inspeccionDTO", MultaSancionDTO.class);
			xml = converter.toXML(inspeccion);
			log.info("XML Multa--- "+xml);
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
	
	public ActionForward consultarInspeccionesPorExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Long defensorId = Long.parseLong(request.getParameter("defensorId"));
			log.info("inspecciones defensorId---- "+defensorId);

			Long numeroExpedienteId = Long.parseLong(request.getParameter("numeroExpedienteId"));
			log.info("inspecciones numeroExpedienteId---- "+numeroExpedienteId);
			ExpedienteDTO expedienteInspeccionado = new ExpedienteDTO();
			expedienteInspeccionado.setNumeroExpedienteId(numeroExpedienteId);

			List<InspeccionDTO> inspeccionDTOs = new ArrayList<InspeccionDTO>();
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			funcionarioDTO.setClaveFuncionario(defensorId);
			inspeccionDTOs = inspeccionDelegate.consultarInspecciones(funcionarioDTO, expedienteInspeccionado);
			log.info("inspeccionDTOs.size -- " + inspeccionDTOs.size());
			
			
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
            
			for(InspeccionDTO inspeccionDTO : inspeccionDTOs){

				writer.print("<row id='"+inspeccionDTO.getInspeccionId()+ "'>");
				writer.print("<cell>"+ inspeccionDTO.getFolioInspeccion()+  "</cell>");
				writer.print("<cell>"+ inspeccionDTO.getMotivo()+  "</cell>");
				writer.print("<cell>"+ inspeccionDTO.getFuncionarioRegistra().getNombreCompleto()+  "</cell>");
				
				writer.print("<cell>");
				if(inspeccionDTO.getFechaRegistro() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(inspeccionDTO.getFechaRegistro());
					int day = cal.get(Calendar.DATE);
			        int month = cal.get(Calendar.MONTH) + 1;
			        int year = cal.get(Calendar.YEAR);
			        String fecha = (day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year; 

					int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int minutes = cal.get(Calendar.MINUTE);
			        String hora = (hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes);
			        
			        writer.print(fecha +"  "+hora);
				}
				writer.print("</cell>");
				
				writer.print("<cell>"+ inspeccionDTO.getDescripcion()+  "</cell>");

				writer.print("<cell>");
				if(inspeccionDTO.getMultaSancion() != null){
					writer.print(inspeccionDTO.getMultaSancion().getFolioMultaSancion());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(inspeccionDTO.getEstatus() != null){
					writer.print(inspeccionDTO.getEstatus().getValor());
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

	public ActionForward consultarInspeccionExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra inspeccion LA CONSULTA:::::::");
		Long idInspeccion = NumberUtils.toLong(request.getParameter("inspeccionId")) ;
		log.info("idInspeccion pasa idInspeccion====" + idInspeccion);
		
		try {
			log.info("Entra Inspeccion :::::::");
			InspeccionDTO inspeccionDTO = new InspeccionDTO();
			
			inspeccionDTO = inspeccionDelegate.obtenerInspeccion(idInspeccion);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("inspeccionDTO", InspeccionDTO.class);
			xml = converter.toXML(inspeccionDTO);
			log.info("Termina inspeccionDTO:::xml::::"+xml);
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
	
	public ActionForward cerrarInspeccionExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra inspeccion cierre:::::::");
		Long idInspeccion = NumberUtils.toLong(request.getParameter("inspeccionId")) ;
		log.info("idInspeccion pasa idInspeccion====" + idInspeccion);
		
		try {
			log.info("Entra Inspeccion :::::::");
			InspeccionDTO inspeccionDTO = new InspeccionDTO();
			inspeccionDTO.setInspeccionId(idInspeccion);
			inspeccionDelegate.concluirInspeccion(inspeccionDTO);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("inspeccionDTO", InspeccionDTO.class);
			xml = converter.toXML(inspeccionDTO);
			log.info("Termina inspeccionDTO:::xml::::"+xml);
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
	
}

package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AdministrarActuacionDefensoriaAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(AdministrarActuacionDefensoriaAction.class);
	
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	public ActionForward consultarExpedientesUsuarioArea(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			List<ExpedienteDTO> expedienteDTOs = new ArrayList<ExpedienteDTO>();
			expedienteDTOs = expedienteDelegate.consultarExpedientesUsuarioArea(usuarioDTO);			
			converter.alias("lista", java.util.List.class);
			converter.alias("expedienteDTOs", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTOs);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}

	public ActionForward consultarExpedientesEtapa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("Inicia Action actuacion consulta por etapa y usuario");
			List<ExpedienteDTO> expedienteDTOs = new ArrayList<ExpedienteDTO>();
			Long etapa = new Long(request.getParameter("etapa"));
			UsuarioDTO usuarioDTO = super.getUsuarioFirmado(request);
			expedienteDTOs = expedienteDelegate.consultarExpedientesPorUsuarioYEtapa(usuarioDTO, etapa);
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
			InvolucradoDTO defendido = null;
			for(ExpedienteDTO expedienteDTO : expedienteDTOs){			
				writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
				if(expedienteDTO.getEtapa().getIdCampo().longValue()!= EtapasExpediente.ASESORIA.getValorId().longValue()){
					if(expedienteDTO.getCasoDTO() != null){//FIXME ELIMINAR ESTE IF, EL EXPEDIENTE SIEMPRE DEBE DE TRAER EL CASO EN ESTE MOMENTO
						writer.print("<cell> "+expedienteDTO.getCasoDTO().getNumeroGeneralCaso()+" </cell>");
					}else{
						writer.print("<cell> - </cell>");
					}
					
					writer.print("<cell> "+expedienteDTO.getNumeroExpediente()+" </cell>");
					
					defendido = expedienteDTO.getInputado();
					if(defendido == null){
						List<InvolucradoDTO> imputados = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
						if(imputados != null && !imputados.isEmpty()){
							defendido= imputados.get(0);
						}
					}
					if(defendido != null){
						writer.print("<cell> "+defendido.getNombreCompleto()+" </cell>");
					}
					else{
						writer.print("<cell> - </cell>");

					}
				
					switch(EtapasExpediente.getByValor(etapa)){
						case CONCILIACION_Y_MEDIACION:
							llenarCamposGridRestaurativa(expedienteDTO, writer, defendido);
							break;
						case INTEGRACION:
							llenarCamposGridIntegracion(expedienteDTO, writer);
							break;
						case TECNICA:
							llenarCamposGridTecnica(expedienteDTO, writer);
							break;
						case EJECUCION:
							writer.print("<cell> - </cell>");
							writer.print("<cell> - </cell>");
							writer.print("<cell> - </cell>");
							break;
			 			
						}
					
				}else{
					log.info("entra asesoria");
					writer.print("<cell> "+expedienteDTO.getNumeroExpediente()+" </cell>");
					
					List<InvolucradoDTO> imputados = expedienteDTO.getInvolucradoByCalidad(Calidades.DEFENDIDO);
					InvolucradoDTO imputado = null;
					if(imputados != null && !imputados.isEmpty()){
						imputado = imputados.get(0);
						writer.print("<cell> "+imputado.getNombreCompleto()+" </cell>");
					}
					else{
						writer.print("<cell> - </cell>");

					}
					
					Date fecha = null;
					AvisoDesignacionDTO aviso = null;
					for(AvisoDesignacionDTO avisoD : expedienteDTO.getAvisosDesignacion()){
						aviso = avisoD;
					}
					fecha = aviso.getFechaCreacion();
					writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
									
				}
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
	
	private void llenarCamposGridRestaurativa(ExpedienteDTO expediente, PrintWriter writer, InvolucradoDTO imputado) {
		
		boolean detenido = false;
		if(imputado != null){
			detenido = (imputado.getEsDetenido() != null && imputado.getEsDetenido());
		}
		if(detenido){	
			writer.print("<cell> SI </cell>");
		}else{
			writer.print("<cell> NO </cell>");
		}
		
		Date fecha = null;
		AvisoDesignacionDTO aviso = null;
		
		for(AvisoDesignacionDTO avisoD : expediente.getAvisosDesignacion()){
			aviso = avisoD;
		}
		
		if(aviso.getSolicitudDefensor() != null){
			fecha =aviso.getSolicitudDefensor().getFechaLimite();
		}else{
			fecha =aviso.getFechaCreacion();
		}
		writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
		fecha = aviso.getFechaCreacion();
		writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
	}
	
	private void llenarCamposGridIntegracion(ExpedienteDTO expediente, PrintWriter writer) {
		
		AvisoDesignacionDTO aviso = null;
		
		for(AvisoDesignacionDTO avisoD : expediente.getAvisosDesignacion()){
			aviso = avisoD;
		}
		
		if(aviso.getSolicitudDefensor() != null){
			writer.print("<cell> "+aviso.getSolicitudDefensor().getInstitucion().getNombreInst()+" </cell>");
		}else{
			writer.print("<cell> Procuraduría </cell>");
		}
		Date fecha = null;
		if(aviso.getAvisoDetencion() != null){
			fecha = aviso.getAvisoDetencion().getFechaDetencion();
			writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
		}else{
			writer.print("<cell> - </cell>");
		}
		
		fecha = aviso.getFechaCreacion();
		writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
	}
	
	private void llenarCamposGridTecnica(ExpedienteDTO expediente, PrintWriter writer) {
		
		List<AudienciaDTO> lista = expediente.getAudiencias();
		AudienciaDTO audiencia  = null;
		if(lista.isEmpty()){
			AvisoDesignacionDTO aviso = null;
			
			for(AvisoDesignacionDTO avisoD : expediente.getAvisosDesignacion()){
				aviso = avisoD;
			}
			if(aviso.getSolicitudDefensor() != null){
				audiencia = aviso.getSolicitudDefensor().getAudiencia();
			}
		}else{
			audiencia = lista.get(lista.size() - 1);
		}
		
		if(audiencia != null){

			writer.print("<cell> "+audiencia.getTipoAudiencia().getValor()+" </cell>");
			writer.print("<cell> "+audiencia.getSala().getNombreSala()+" </cell>");
			Date fecha = audiencia.getFechaEvento();
			writer.print("<cell>"+ DateUtils.formatear(fecha)+" - "+DateUtils.formatearHora(fecha)   + "</cell>");
		
		}else{
			writer.print("<cell> - </cell>");
			writer.print("<cell> - </cell>");
			writer.print("<cell> - </cell>");
		}
	}

}

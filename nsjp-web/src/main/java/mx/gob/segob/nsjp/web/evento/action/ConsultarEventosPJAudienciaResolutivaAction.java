package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.forma.FormaDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.solicitud.action.SolicitudCiudadanaDefensorAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsultarEventosPJAudienciaResolutivaAction extends GenericAction {

	@Autowired
	private AudienciaDelegate audienciaDelegate;
	@Autowired
	private FormaDelegate formaDelegate;
	
	private static final Logger log = Logger.getLogger(SolicitudCiudadanaDefensorAction.class);
	
	public ActionForward cargaTranscripcionResolutivos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Transcripcion de resolutivos");
			
			List<AudienciaDTO> audienciaDTOs =  audienciaDelegate.buscarAudienciasSinTranscripcionResolutivos();
					
			if (log.isDebugEnabled()) {
			    log.debug("lista de Transcripciones" + audienciaDTOs);   	
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");		
			int lTotalRegistros = audienciaDTOs.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");			
		
			for (AudienciaDTO audienciaDTO : audienciaDTOs) {
				writer.print("<row id='"+ audienciaDTO.getId()+ "'>");
				writer.print("<cell>" +audienciaDTO.getId() +"</cell>");
				writer.print("<cell>" +audienciaDTO.getFechaHoraFin() +"</cell>");
				if(audienciaDTO.getExpediente().getCausaPadre() != null){
					writer.print("<cell>" +audienciaDTO.getExpediente().getCausaPadre().getNumeroExpediente() + "</cell>");
					writer.print("<cell>" +audienciaDTO.getExpediente().getNumeroExpediente() +"</cell>");
				
				}else{
					writer.print("<cell>" +audienciaDTO.getExpediente().getNumeroExpediente() + "</cell>");
					writer.print("<cell>" +" N/A "+"</cell>");
					
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
	
	public ActionForward cargaResolutivos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes no Atendidas");
			Long idAudiencia = Long.parseLong(request.getParameter("idAudiencia"));
			AudienciaDTO audienciaDTO = new AudienciaDTO();			
			audienciaDTO.setId(idAudiencia);
			audienciaDTO =  audienciaDelegate.obtenerAudiencia(audienciaDTO);
			ExpedienteDTO numeroExpediente = audienciaDTO.getExpediente();
			super.setExpedienteTrabajo(request, numeroExpediente);

			
			List<ResolutivoDTO> resolutivos = audienciaDTO.getResolutivos();
			
        	log.info("lista resolutivas" + resolutivos );	
						
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		
			
			int lTotalRegistros = resolutivos.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (ResolutivoDTO resolutivoDTO : resolutivos) {
				writer.print("<row id='"+ resolutivoDTO.getResolutivoId() + "'>");
				writer.print("<cell>"+ resolutivoDTO.getDetalle() + "</cell>");
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
	
	public ActionForward cargaPlantillas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes no Atendidas");
			String idR = request.getParameter("idResolutivo");
			Long idResolutivo = 0L;
			if(idR != null && !idR.isEmpty()){
				idResolutivo = Long.parseLong(idR);
			}else{
				return null;
			}
			log.info("Solicitudes no Atendidas" + idResolutivo);
			List<FormaDTO> formas = null;
			FormaDTO formaDTO = new FormaDTO();
			
			formas =  formaDelegate.consultarPlantillaPorTipo(TipoForma.RESOLUCION.getValorId());
			
			log.info("formas" + formas);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = formas.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for(FormaDTO forma : formas){
				writer.print("<row id='"+forma.getFormaId()+ "'>");
				
				writer.print("<cell>"+forma.getNombre()+ "</cell>");
				
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
	
	public ActionForward marcarAudienciaResolutivos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes no Atendidas");
			Long idAudiencia = Long.parseLong(request.getParameter("idAudiencia"));
			log.info("Solicitudes no Atendidas" + idAudiencia);
			audienciaDelegate.marcarAudienciaResolutivos(idAudiencia);
	

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
}

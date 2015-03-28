
package mx.gob.segob.nsjp.web.evento.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.leycodigo.LeyCodigoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;

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
public class ConsultaEventosPJJUAction extends ReporteBaseAction{

	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultaEventosPJJUAction.class);
	
	@Autowired
	public DocumentoDelegate documentoDelegate;
	
	@Autowired
	public SolicitudDelegate solicitudDelegate;
	
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	@Autowired
	public LeyCodigoDelegate leyCodigoDelegate;
	
	@Autowired
	public AudienciaDelegate audienciaDelegate;
			
	//private final static String KEY_SESSION_EVENTO = "KEY_SESSION_EVENTO_DTO";
		
	
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
	public ActionForward visorAudienciaJuezPJJUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando action visorAudienciaPJENS");
			
			String idEvento = request.getParameter("idEvento");
			log.info("ID-DEL EVENTO:::"+ idEvento);
			request.getSession().setAttribute("idEvento",idEvento);
			
			String carpetaResolutivos = request.getParameter("carpetaResolutivos");
			log.info("carpetaResolutivos:::"+ carpetaResolutivos);
			request.getSession().setAttribute("carpetaResolutivos",carpetaResolutivos);
						
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}	
	
	/**
	 * Método para obtener los datos para rellenar el grid de los documentos asociados a la carpeta
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 */
	public ActionForward obtenerDocumentosAsociados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.debug("ENTRA A OBTENER DOCUMENTOS ASOCIADOS");
		 
		Long numeroExpedienteId = NumberUtils.toLong(request.getParameter("numeroExpedienteId"));
		
		log.debug("Numero de expediente id" + numeroExpedienteId);
		
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(numeroExpedienteId);
		List<DocumentoDTO> documentos = documentoDelegate.consultarDocumentosPorNumeroExpediente(expediente);
		log.debug("documentos" + documentos);
		
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();
		
		writer.print("<rows>");
	
		writer.print("<records>" + documentos.size() + "</records>");

		for (DocumentoDTO documento : documentos) {
			writer.print("<row id='" + documento.getArchivoDigital().getArchivoDigitalId()+ "'>");
			writer.print("<cell>" + documento.getNombreDocumento()+ "</cell>");
			writer.print("</row>");
		}			
		
		writer.print("</rows>");
		writer.flush();
		writer.close();
		
		
		return null;
	}
	
	/**
	 * Método para obtener los datos para rellenar el grid de los resolutivos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 */
	public ActionForward consultarResolutivosAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, NSJPNegocioException {
		
		log.debug("ENTRA A CONSULTAR RESOLUTIVOS DE AUDIENCIA");
		 
		String audienciaId = request.getParameter("idEvento");
		
		log.debug("Audiencia id" + audienciaId);
		
		List<ResolutivoViewDTO> resolutivos;
		
		resolutivos = audienciaDelegate.leerResolutivosAudiencia(Long.parseLong(audienciaId));
		 
		log.debug("resolutivos" + resolutivos);
		
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();
		
		writer.print("<rows>");
	
		writer.print("<records>" + resolutivos.size() + "</records>");

		for (ResolutivoViewDTO resolutivoViewDTO : resolutivos) {
			
			writer.print("<row id='" + resolutivoViewDTO.getResolutivoId()+ "'>");
			writer.print("<cell>" + resolutivoViewDTO.getTemporizador()+ "</cell>");
			writer.print("<cell>" + resolutivoViewDTO.getDetalle()+ "</cell>");			
			writer.print("</row>");
		}			
		
		writer.print("</rows>");
		writer.flush();
		writer.close();
		
		
		return null;
	}
	
	
}

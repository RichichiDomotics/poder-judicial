/**
 * Nombre del Programa : AdministrarSolicitudesTranscripcionAudioVideo.java
 * Autor               : Alejandro Galaviz
 * Compania            : Ultrasist
 * Proyecto            : NSJP                    Fecha: 22/08/2011
 * Marca de cambio     : N/A
 * Descripcion General : Clase Action para administrar las solicitudes de transcripcion
 * 						 y de audio/video
 * Programa Dependiente: N/A
 * Programa Subsecuente: N/A
 * Cond. de ejecucion  : N/A
 * Dias de ejecucion   : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor               :N/A
 * Compania            :N/A
 * Proyecto            :N/A                                   Fecha: N/A
 * Modificacion        :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Clase Action ara administrar las solicitudes de transcripcion y audio/video
 * @author AlejandroGA
 * @version 1.0
 */
public class AdministrarSolicitudesTranscripcionAudioVideo extends GenericAction {
	
	/* Log de clase */
	private static final Logger log = Logger.getLogger(AdministrarSolicitudesTranscripcionAudioVideo.class);
	
	@Autowired
	public SolicitudDelegate solicitudDelegate;
	@Autowired
	DocumentoDelegate documentoDelegate;
	
	
	/**
	 * Metodo utilizado para la consulta de solicitudes, de transcripcion
	 * y solicitudes de audio/video
	 * 
	 * @author AlejandroGA
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarSolcicitudesTranscripcionAudioVideo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("EJECUTANDO ACTION ---- CONSULTAR SOLICITUDES DE TRANSCRIPCION Y DE AUDIO/VIDEO");
			Long estatus = NumberUtils.toLong(request.getParameter("estatus"));
			Long tipoSolicitud = NumberUtils.toLong(request.getParameter("tipoSolicitud"));
			int solicitudesDelDia = NumberUtils.toInt(request.getParameter("isDia"));
			
			Date fechaHoy = null;
				
			log.info("ESTATUS:::"+estatus);
			log.info("TIPO SOLICITUD:::"+tipoSolicitud);
			log.info("SOLICITUD ES DEL DIA??:::"+solicitudesDelDia);
			
			if(solicitudesDelDia==1){
				fechaHoy = new Date();
				//fechaHoy = DateUtils.obtener(DateUtils.formatear(fechaHoy));
			}
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			List<SolicitudTranscripcionAudienciaDTO> listaSolicitudes = solicitudDelegate.consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(tipoSolicitud,estatus,usuario, fechaHoy, null);
			
			

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

//			int lTotalRegistros = listaSolicitudes.size();
//			writer.print("<records>" + lTotalRegistros + "</records>");
			for (SolicitudTranscripcionAudienciaDTO solicitud : listaSolicitudes) {
				
				writer.print("<row id='" +solicitud.getDocumentoId()+ "'>");
					//Numero caso
					writer.print("<cell>"+((solicitud.getExpedienteDTO() != null && solicitud.getExpedienteDTO().getCasoDTO()!= null) ? solicitud.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()   :"")+ "</cell>");
					//Numero causa
					writer.print("<cell>"+(solicitud.getExpedienteDTO() != null ? solicitud.getExpedienteDTO().getNumeroExpediente():"")+"</cell>");
					//FechaHora Sol
					writer.print("<cell>"+(solicitud.getFechaCreacion() != null ? DateUtils.formatear(solicitud.getFechaCreacion()):"")+ "</cell>");
					//Solicitante
					writer.print("<cell>"+(solicitud.getNombreSolicitante() != null ? solicitud.getNombreSolicitante():"")+ "</cell>");
					//Institucion
					//writer.print("<cell>"+(solicitud.getNombreInstitucionSolicitante() != null ? solicitud.getNombreInstitucionSolicitante():"")+ "</cell>");
					if(solicitud.getInstitucion() != null && solicitud.getInstitucion().getNombreInst() != null){
						writer.print("<cell>"+solicitud.getInstitucion().getNombreInst()+ "</cell>");
					}else{
						writer.print("<cell>"+ " " + "</cell>");
					}
					
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para obtener el objeto de tipo SolicitudTranscripcionAudienciaDTO 
	 * 
	 * @author AlejandroGA
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarDetalleSolicitudTranscripcionAudioVideo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("EJECUTANDO ACTION ---- CONSULTAR DETALLE DE SOLICITUD DE TRANSCRIPCION Y DE AUDIO Y VIDEO");
			Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"));
			
			log.info("SOLICITUD ID:::"+solicitudId);
			SolicitudTranscripcionAudienciaDTO sol = solicitudDelegate.consultarDetalleSolicitudTranscripcion(solicitudId);
			
			//No es una solicitud master entonces consultar el archivo digital de la solicitud master
			if(StringUtils.isNotBlank(sol.getNombreSolicitante())){
				 List<SolicitudTranscripcionAudienciaDTO> listaMasters = solicitudDelegate.
				consultarSolicitudMaster(sol.getAudiencia().getId(), sol.getTipoSolicitudDTO().getIdCampo());
				 SolicitudTranscripcionAudienciaDTO solMaster = listaMasters!=null && listaMasters.size()>0 ?
						 listaMasters.get(0):null;
				if(solMaster != null){
					
					if(sol.getArchivoDigital() == null){
						sol.setArchivoDigital(solMaster.getArchivoDigital());
						//actualizar la solicitud actual con el archivo digital
						documentoDelegate.asociarArchivoDigitalADocumento(sol.getDocumentoId(), 
								sol.getArchivoDigital().getArchivoDigitalId());
					}
					
					
				}
			}
			setExpedienteTrabajo(request, sol.getExpedienteDTO());
			converter.alias("solicitudTranscripcionAudienciaDTO",SolicitudTranscripcionAudienciaDTO.class);
			
			escribirRespuesta(response,converter.toXML(sol));

			
		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	/**
	 * Método para actualizar una solicitud de trancripción audiencia / audio y video
	 * 
	 * @author Emigdio Hernández
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward finalizarSolicitudTranscripcionAudioVideo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"));
		
		try {
			SolicitudDTO solicitudDTO = new SolicitudDTO(solicitudId);
			solicitudDelegate.actualizaEstatusSolicitud(solicitudDTO, EstatusSolicitud.CERRADA);
			log.debug("Enviando actualización de transcripción a institución solicitante");
			solicitudDelegate.enviarActualizacionSolicitudTranscripcionAudiencia(solicitudId);	
			
			
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(),e);
		}
		
		escribirRespuesta(response,converter.toXML(solicitudId));
		
		
		return null;
	}
}

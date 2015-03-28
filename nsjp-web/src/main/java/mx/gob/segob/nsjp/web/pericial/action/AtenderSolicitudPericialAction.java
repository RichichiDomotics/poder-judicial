/**
* Nombre del Programa : AtenderSolicitudPericialAction.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.pericial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import antlr.Utils;

/**
 * Action que atiende las solicitudes de vista con el objetivo 
 * de anteder el procedimiento de una solicitud pericial asignada a un perido, ya sea
 * para generar dictámenes, informes o agregar notas a un dictamen
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class AtenderSolicitudPericialAction extends GenericAction {
	
	@Autowired
	SolicitudPericialDelegate solicitudPericialDelegate;
	@Autowired
	SolicitudDelegate solicitudDelegate;
	@Autowired
	DocumentoDelegate documentoDelegate;
	private List<SolicitudPericialDTO> listaDocumentoDTOs;
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(AtenderSolicitudPericialAction.class);

	/**
	 * Método para realizar al búsqueda de solcitudes periciales
	 * asignadas al usuario actual y que tengan estatus EN_PROCESO
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 */
	public ActionForward consultarSolicitudesPericialesPeritoEnProceso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
	
		FuncionarioDTO destinatario = getUsuarioFirmado(request).getFuncionario();
		List<SolicitudPericialDTO> solicitudes = solicitudPericialDelegate.
		consultarSolicitudesPericialesPorEstatusYDestinatario(EstatusSolicitud.EN_PROCESO, destinatario);
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();

		writer.print("<rows>");
		writer.print("<records>" + solicitudes.size() + "</records>");
		Long dictamenId = null;
		for (SolicitudPericialDTO sol : solicitudes) {
			dictamenId = solicitudPericialDelegate.consultarDictamenIdDeSolicitudPericial(sol.getDocumentoId());   
			writer.print("<row id='"+ sol.getDocumentoId() + "," + (dictamenId!=null?dictamenId:"0")+ "'>");
			writer.print("<cell>"+ (sol.getExpedienteDTO()!=null?sol.getExpedienteDTO().getNumeroExpediente():"") +  "</cell>");
			writer.print("<cell>"+ (sol.getExpedienteDTO()!=null&&
									sol.getExpedienteDTO().getCasoDTO()!=null?
									sol.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"") +  "</cell>");
			writer.print("<cell>"+ DateUtils.formatear(sol.getFechaLimite()) +  "</cell>");
			writer.print("<cell>"+ DateUtils.formatear(sol.getFechaModificacion()) +  "</cell>");
			writer.print("<cell>"+ (dictamenId!=null?"Si":"No") +  "</cell>");
			writer.print("<cell>"+ (sol.getExpedienteDTO()!=null?sol.getExpedienteDTO().getExpedienteId():"") +  "</cell>");
			writer.print("<cell>"+ (sol.getExpedienteDTO()!=null?sol.getExpedienteDTO().getNumeroExpedienteId():"") +  "</cell>");
			log.info("idExpParaDocumentos"+sol.getExpedienteDTO().getNumeroExpedienteId());
			log.info("idExpParaDocumentos"+sol.getExpedienteDTO().getNumeroExpedienteId());
			writer.print("</row>");
		}
								
		
		
		writer.print("</rows>");
		writer.flush();
		writer.close();
		return null;
	}

	/**
	 * Método para crear un nuevo dictamen asociado a la solicitud pericial que sea enviada como
	 * parámetro
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 */
	public ActionForward crearDictamenParaSolicitudPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long solicitudPericialId = NumberUtils.toLong(request.getParameter("solicitudPericialId"),0);
		if(solicitudPericialId > 0){
			
			DictamenDTO dictamen = new DictamenDTO();
			dictamen.setSolicitudPericial(new SolicitudPericialDTO(solicitudPericialId));
			dictamen.setFechaCreacion(new Date());
			dictamen.setFormaDTO(new FormaDTO(NumberUtils.toLong(request.getParameter("formaId"),0)));
			solicitudPericialDelegate.registrarDictamenParaSolicitudPericial(dictamen);
			escribirRespuesta(response, converter.toXML(dictamen));
		}
		
		
		return null;
		
	}
	/**
	 * Método para colocar el expediente en sesión de la solicitud elegida
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 */
	public ActionForward colocarExpedienteParaSolicitudPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long solicitudPericialId = NumberUtils.toLong(request.getParameter("solicitudPericialId"),0);
		SolicitudPericialDTO solicitud = solicitudPericialDelegate.consultarSolicitudPericialPorId(solicitudPericialId);
		setExpedienteTrabajo(request, solicitud.getExpedienteDTO());
		escribirRespuesta(response, converter.toXML(solicitud.getExpedienteDTO()));
		return null;
		
	}
	
	/**
	 * Actualiza el estatus de una solicitud pericial a CERRADO
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 */
	public ActionForward terminarSolicitudPericialPerito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long solicitudPericialId = NumberUtils.toLong(request.getParameter("solicitudPericialId"),0);
		
		solicitudDelegate.actualizaEstatusSolicitud(new SolicitudDTO(solicitudPericialId),EstatusSolicitud.CERRADA);
		escribirRespuesta(response, converter.toXML(solicitudPericialId));
		return null;
	}
	
	
	/**
	 * funcion para consultar los  Documentos generados para el visor atencion a solicitudes periciales
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDocumentosAsociadosASolicitud(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"),0); 
			log.info("::::::::::::: ID de la solicitud para consultar documentos consultarDocumentos ::::::::::::.:: "+solicitudId);

			if(solicitudId >0){
				
				List<ArchivoDigitalDTO> listaDocumentoDTOs = documentoDelegate.consultarArchivosDeSolicitud(solicitudId);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDocumentoDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (ArchivoDigitalDTO documentoDTO : listaDocumentoDTOs) {
					writer.print("<row id='"+documentoDTO.getArchivoDigitalId()+"'>");
					writer.print("<cell>"+documentoDTO.getNombreArchivo()+"</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * funcion para consultar los  Documentos generados para el visor atencion a solicitudes periciales
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDocumentosPropiosAsociadosASolicitudPericial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {		
			Long solicitudId = NumberUtils.toLong(request.getParameter("solicitudId"),0); 
			log.info("::::::::::::: ID de la solicitud hijo para consultar documentos consultarDocumentos ::::::::::::.:: " + solicitudId);
			solicitudId=solicitudPericialDelegate.consultarPadreSolicitudPericial(solicitudId);
			log.info("::::::::::::: ID de la solicitud padre para consultar documentos consultarDocumentos ::::::::::::.:: " + solicitudId);

			if(solicitudId >0){
				
				List<DocumentoDTO> listaDocumentosDTOs = documentoDelegate.consultarArchivosDeSolicitudPericial(solicitudId);
				//List<ArchivoDigitalDTO> listaDocumentoDTOs = documentoDelegate.consultarArchivosDeSolicitud(solicitudId);				
				
				request.getSession().setAttribute("totalRegistrosDocumentos", listaDocumentosDTOs.size());						
				request.setAttribute("totalRegistrosDocumentos", listaDocumentosDTOs.size());				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaDocumentosDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
			
				//for (ArchivoDigitalDTO documentoDTO : listaDocumentosDTOs) {
				for (DocumentoDTO documentoDTO : listaDocumentosDTOs) {
										
					writer.print("<row id='"+documentoDTO.getArchivoDigital().getArchivoDigitalId()+"'>");
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+							
							((documentoDTO.getArchivoDigital()!=null && 
							  documentoDTO.getArchivoDigital().getUsuario()!=null && 
							  documentoDTO.getArchivoDigital().getUsuario().getArea()!=null)?
							  documentoDTO.getArchivoDigital().getUsuario().getArea():"-") + " </div>]]></cell>");
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							((documentoDTO.getActividadDTO()!=null && 
							  documentoDTO.getActividadDTO().getFechaCreacion()!=null)? 
							  documentoDTO.getActividadDTO().getFechaCreacion():"-") + " </div>]]></cell>");
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							((documentoDTO.getActividadDTO()!=null &&
							  documentoDTO.getActividadDTO().getNombre()!=null)?
							  documentoDTO.getActividadDTO().getNombre():"-") + " </div>]]></cell>");
																	
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+
							((documentoDTO.getArchivoDigital()!=null &&
							  documentoDTO.getArchivoDigital().getTipoArchivo()!=null)?
							  documentoDTO.getArchivoDigital().getTipoArchivo():"-") +  " </div>]]></cell>");
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ 
							((documentoDTO.getArchivoDigital()!=null &&
							  documentoDTO.getArchivoDigital().getNombreArchivo()!=null)?
							  documentoDTO.getArchivoDigital().getNombreArchivo():"-") +  " </div>]]></cell>");
					
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ 
							((documentoDTO.getStrFechaCreacion()!=null)?
							  documentoDTO.getStrFechaCreacion():"-") +  " </div>]]></cell>");
												
					writer.print("</row>");					
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}
			
		} catch (Exception e) {
			log.info(" execpcion metodoo consultarDocumentosPropiosAsociadosASolicitudPericial ");
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * Método utilizado para finalizar una solicitud pericial una vez que ya fue generado el dictamen o el informe
	 * Adjunta a la solicitud de pericial el archivo digital recién generado y actualiza
	 * el estado de la solicitud del perito y la solicitud padre del coordinador de periciales a CERRADA
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author Emigdio Hernández
	 * @return
	 * @throws IOException
	 */
	public ActionForward finalizarDictamenInformePericialPeritoAMP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		solicitudPericialDelegate.finalizarSolicitudPericial(NumberUtils.toLong(request.getParameter("solicitudId")));
		
		
		
		return null;
	}
	
		
}

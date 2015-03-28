/**
* Nombre del Programa : DocumentosReinsercionAction.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.web.reinsercionsocial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.ActividadesRS;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
@SuppressWarnings("unused")
public class DocumentosReinsercionAction extends GenericAction{
	
	/* Log de clase*/
	private static final Logger LOG  = Logger.getLogger(DocumentosReinsercionAction.class);
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	@Autowired
	private DocumentoDelegate documentoDelegate;
	
	/***
	 * Metodo para mandar consultar las solicitudes por atender con cierto estatus,tipo y area
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarDocumentosConCriterios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				LOG.info("ejecutando Action DocumentosReinsercionAction en metodo consultarDocumentosConCriterios:#####");
				
				String tipoSolicitud = request.getParameter("tipoSoliciutd");
				String estatusSolicitud = request.getParameter("estatus");
				String areaSolicitud = request.getParameter("idArea");
				String tipoConsulta = request.getParameter("tipoConsulta");
				String idNumeroExpediente = request.getParameter("numeroExpedienteId");
				String cNumeroExpediente = request.getParameter("cNumeroExpediente");
				
				//construimos la lista de los tipos de solicitud a consultar
				String[] idTiposols=tipoSolicitud.split(",");
				
				LOG.info("idsTipoSols:: "+tipoSolicitud);
				List<Long> idsTipSols=new ArrayList<Long>();
				
				LOG.info("arrIDsTipoSols:: "+idTiposols);
				for (String long1 : idTiposols) {
					idsTipSols.add(Long.parseLong(long1));
				}
				
				//construimos la lista de los estatus a consultar
				estatusSolicitud = estatusSolicitud != null ? estatusSolicitud : ""; 
				String[] idEstatus = estatusSolicitud.split(",");
				List<Long> idsEstatus=new ArrayList<Long>();
				LOG.info("arrIDsEstatus:: "+idEstatus);
				if (!estatusSolicitud.equals("")){
					for (String long1 : idEstatus) {
						idsEstatus.add(Long.parseLong(long1));
					}
				}
				//consultamos las solicitudes por atender
				UsuarioDTO loUsuario = super.getUsuarioFirmado(request);
				Long claveAgencia = null;
				if(loUsuario.getFuncionario() != null && loUsuario.getFuncionario().getDiscriminante() != null  && loUsuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null)
					claveAgencia = loUsuario.getFuncionario().getDiscriminante().getCatDiscriminanteId();

				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				ExpedienteDTO expedienteDTO = new ExpedienteDTO();
				if(idNumeroExpediente != null && idNumeroExpediente != "") {
					expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				}
				
				expedienteDTO.setNumeroExpediente(cNumeroExpediente);
				
				if (tipoConsulta.equals(SolicitudDTO.PARCIALES)) {
					DocumentoDTO documentoDTO = new DocumentoDTO();
					documentoDTO.setExpedienteDTO(expedienteDTO);
					List<DocumentoDTO> listaDocumentos = documentoDelegate.consultarDocumentosReinsercionSocial(loUsuario.getFuncionario(), documentoDTO);
					final PaginacionDTO pag = PaginacionThreadHolder.get();
					writer = llenarXMLDocumentos(listaDocumentos, writer, pag);
				} else {
					SolicitudDTO solicitudDTO = new SolicitudDTO();
					solicitudDTO.setExpedienteDTO(expedienteDTO);
					
					if (areaSolicitud!= null && areaSolicitud != ""){
						solicitudDTO.setAreaOrigen(Long.parseLong(areaSolicitud));
					}
					
					if (tipoConsulta.equals(SolicitudDTO.GENERADAS)) {
						solicitudDTO.setUsuarioSolicitante(loUsuario.getFuncionario());
					} else if (tipoConsulta.equals(SolicitudDTO.POR_ATENDER)) {
						solicitudDTO.setDestinatario(loUsuario.getFuncionario());
					}
					
					List<SolicitudDTO> listaSolicitudes = solicitudDelegate.consultarSolicitudesConCriterios(solicitudDTO, idsEstatus, idsTipSols, tipoConsulta);
					final PaginacionDTO pag = PaginacionThreadHolder.get();
					writer = llenarXMLSolicitudes(listaSolicitudes, writer, pag);
				}
				//generamos el HTML del grid
				
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
		}
		return null;
	}

	/**
	 * @param listaSolicitudes
	 * @param writer
	 */
	private PrintWriter llenarXMLSolicitudes(List<SolicitudDTO> listaSolicitudes,
			PrintWriter writer, PaginacionDTO pag) {
		writer.print("<rows>");
		if (pag != null && pag.getTotalRegistros() != null) {
            writer.print("<total>" + pag.getTotalPaginas() + "</total>");
            writer.print("<records>" + pag.getTotalRegistros() + "</records>");
        } else {
            writer.print("<total>0</total>");
            writer.print("<records>0</records>");
        }
		
		for (SolicitudDTO solicitudDTO : listaSolicitudes) {
			writer.print("<row id='"+solicitudDTO.getDocumentoId()+"'>");
			
			if(solicitudDTO.getExpedienteDTO()==null || solicitudDTO.getExpedienteDTO().getCasoDTO()==null || solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+" </div]]></cell>");
			}
			
			if(solicitudDTO.getExpedienteDTO()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getNumeroExpediente()+" </div>]]></cell>");
			}
			
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFolioSolicitud()+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getEstatus().getValor()+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaCreacion())+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaLimite())+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(solicitudDTO.getInstitucion() != null && solicitudDTO.getInstitucion().getNombreInst() != null? solicitudDTO.getInstitucion().getNombreInst(): "-")+" </div>]]></cell>");
			if(solicitudDTO.getDestinatario()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getDestinatario().getNombreCompleto()+" </div>]]></cell>");
			}
			if(solicitudDTO.getExpedienteDTO()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getExpedienteDTO().getExpedienteId()+"</div>]]></cell>");
			}
			writer.print("</row>");	
		}
		writer.print("</rows>");
		return writer;
	}
	
	/**
	 * @param listaSolicitudes
	 * @param writer
	 */
	private PrintWriter llenarXMLDocumentos(List<DocumentoDTO> listaDocumentos,
			PrintWriter writer, PaginacionDTO pag) {
		writer.print("<rows>");
		if (pag != null && pag.getTotalRegistros() != null) {
            writer.print("<total>" + pag.getTotalPaginas() + "</total>");
            writer.print("<records>" + pag.getTotalRegistros() + "</records>");
        } else {
            writer.print("<total>0</total>");
            writer.print("<records>0</records>");
        }
		
		for (DocumentoDTO documentoDTO : listaDocumentos) {
			writer.print("<row id='"+documentoDTO.getDocumentoId()+"'>");
			
			if(documentoDTO.getExpedienteDTO()==null || documentoDTO.getExpedienteDTO().getCasoDTO()==null || documentoDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+" </div]]></cell>");
			}
 			
			if(documentoDTO.getExpedienteDTO()==null) {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div>]]></cell>");
			} else {
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+documentoDTO.getExpedienteDTO().getNumeroExpediente()+" </div]]></cell>");
			}
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+" "+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ documentoDTO.getFolioDocumento() != null ? documentoDTO.getFolioDocumento() : "---"   +" </div]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ "Guardado Parcial" +" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(documentoDTO.getFechaCreacion())+" </div]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+" "+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(documentoDTO.getConfInstitucion() != null && documentoDTO.getConfInstitucion().getNombreInst() != null ? documentoDTO.getConfInstitucion().getNombreInst() : "---")+" </div>]]></cell>");
			writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+(documentoDTO.getNombreDocumento() != null ? documentoDTO.getNombreDocumento() : "---")+" </div>]]></cell>");
			
			if (documentoDTO.getActividadDTO() != null &&  documentoDTO.getActividadDTO().getTipoActividadRS() != null ){
				ActividadesRS actividadRS =  documentoDTO.getActividadDTO().getTipoActividadRS();
				writer.print("<cell><![CDATA[" + actividadRS.getValorId() + "]]></cell>");
			} else {
				writer.print("<cell><![CDATA[---]]></cell>");
			}
			writer.print("</row>");	
		}
		writer.print("</rows>");
		return writer;
	}

}

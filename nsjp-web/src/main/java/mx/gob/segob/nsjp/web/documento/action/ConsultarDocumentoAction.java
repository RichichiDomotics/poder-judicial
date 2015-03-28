/**
* Nombre del Programa : ConsultarDocumentoAction.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.web.documento.action;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.CONTENT_TYPE_PDF;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CACHE_CONTROL;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CONTENT_DISPOSITION;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_INLINE_FILE_NAME;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_NOCACHE;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_PRAGMA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.forma.FormaDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action encargado de consultar el archivo digital de un documento y enviarlo
 * al navegador
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ConsultarDocumentoAction extends ReporteBaseAction {
	@Autowired
	private DocumentoDelegate documentoDelegate;
	@Autowired
	private FormaDelegate formaDelegate;
		
	private static final Logger logger = Logger
    .getLogger(ConsultarDocumentoAction.class);
	/**
     * Realiza la consulta de un documento o archivoDigital en base a su ID enviado como parámetro del 
     * request.
     * Lo recupera de la base de datos, consulta su archivo digital y el contenido
     * lo escribe al response con el nombre del archivo digital y el tipo de archivo
     * Parametros necesario:
     * documentoId - Identificador del documento o
     * archivoDigitalId - Identificador del archivoDigital
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward consultarContenidoArchivoDigital(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
try {
    		
    		logger.info("EJECUTANDO CONSULTAR ARCHIVO DIGITAL................");
	    		Long documentoId = request.getParameter("documentoId")!=null?NumberUtils.toLong(request.getParameter("documentoId")):null;
	    		logger.info("DOCUMENTO ID OBTENIDO="+documentoId);
	    		Long archivoDigitalId = request.getParameter("archivoDigitalId")!=null?NumberUtils.toLong(request.getParameter("archivoDigitalId")):null; 
	    		logger.info("ARCHIVO DIGITAL ID OBTENIDO="+archivoDigitalId);
				ArchivoDigitalDTO archivo = documentoDelegate.consultarArchivoDigitalCompletoPorArchivoODocumento(documentoId, archivoDigitalId);
				
	    		if(archivo != null){
	    			
	    			response.setContentType("application/octet-stream");
    	            response.setHeader(ENCABEZADO_CACHE_CONTROL, ENCABEZADO_NOCACHE);
    	            response.setHeader(ENCABEZADO_PRAGMA, ENCABEZADO_NOCACHE);
//	    			if(request.getParameter("inFrame") == null){
	    				//Escribirlo como attachment
	    				response.setHeader(ENCABEZADO_CONTENT_DISPOSITION,
	    						ENCABEZADO_INLINE_FILE_NAME
	    	                    + archivo.getNombreArchivo().trim().replaceAll(" ", "_") + archivo.getTipoArchivo());
//	    			}	    			
	    			response.setContentLength(archivo.getContenido().length);
	    			  ServletOutputStream sos = response.getOutputStream();
	    	            sos.write(archivo.getContenido());
	    	            sos.flush();
	    			
	    		}
				
			
			   
		    }catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
	    
    return null;
    }
    
    
    /**
     * Realiza la consulta de todas las plantillas por el flitro de tipo de documento
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward consultarPlantillasPorTipo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    			logger.info("_____________________________________________________________");
    			logger.info("EJECUTANDO CONSULTA PLANTILLAS POR TIPO ACTION");
    			logger.info("_____________________________________________________________");
    			
	    		//Se obtiene el id del tipo de documento
				String tipoDocumento = request.getParameter("tipoDocumento");
				logger.info("TIPO DE DOCUMENTO::::::::::"+tipoDocumento);
				List<FormaDTO> listaPlantillas = formaDelegate.consultarFormasPorTipoForma(NumberUtils.toLong(tipoDocumento));
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
	            if (pag != null && pag.getTotalRegistros() != null) {
	                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
	                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
	            } else {
	                writer.print("<total>0</total>");
	                writer.print("<records>0</records>");
	            }	
				
				for (FormaDTO plantilla : listaPlantillas) {
					writer.print("<row id='" + plantilla.getFormaId()+ "'>");
						//Nombre de la plantilla
						writer.print("<cell>" + plantilla.getNombre()+ "</cell>");
						//Tipo de plantilla
						if(plantilla.getTipoFormaDTO() != null){
							writer.print("<cell>" + plantilla.getTipoFormaDTO().getValor()+ "</cell>");
						}else{
							writer.print("<cell>-</cell>");
						}
						//Fecha de creacion de la plantilla
						if(plantilla.getFechaCreacion() != null){
							writer.print("<cell>" + DateUtils.formatear(plantilla.getFechaCreacion())+ "</cell>");
						}else{
							writer.print("<cell>-</cell>");
						}
					writer.print("</row>");
				}			
				writer.print("</rows>");
				writer.flush();
				writer.close();
		    }catch (Exception e) {
				logger.error(e);
			}
		    
        return null;
    	
    }
    
    
    /**
     * Realiza la consulta de todas las plantillas por el flitro de tipo de documento
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward administrarPlantilla(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    			logger.info("****************************************************************");
    			logger.info("EJECUTANDO ADMINISTRAR PLANTILLA ACTION");
    			logger.info("****************************************************************");
    			
	    		//Se obtiene el id del tipo de plantilla
				logger.info("PLANTILLA ID::::::::::"+request.getParameter("plantillaId"));
				Long plantillaId = null; 
				FormaDTO plantilla = null;
				
				if(request.getParameter("plantillaId") != null){
					plantillaId = NumberUtils.toLong(request.getParameter("plantillaId"));
				}
				if(plantillaId != null){
					plantilla	= formaDelegate.consultarDetalleForma(plantillaId);
				}
				if(plantilla == null){
					plantilla = new FormaDTO();
				}
				logger.info("PLANTILLA"+plantilla);
				request.setAttribute("tipoPlantilla",plantilla.getTipoFormaDTO().getIdCampo());
				request.setAttribute("cuerpoPlantilla",plantilla.getCuerpo());
				request.setAttribute("nombrePlantilla",plantilla.getNombre());
				
		    }catch (Exception e) {
				logger.error(e);
			}
		    
        return mapping.findForward("succes");
    	
    }
    
    /**
     * Crea una nueva plantilla o almacena la plantilla editada
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward guardarPlantilla(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    
    	
    	
    	
    	FormaDTO forma = new FormaDTO();
    	Long formaId = NumberUtils.toLong(request.getParameter("formaId"));
    	if(formaId<1){
    		formaId = null;
    	}
    	forma.setFormaId(formaId);
    	forma.setCuerpo(request.getParameter("cuerpo"));
    	forma.setNombre(request.getParameter("nombre"));
    	forma.setDescForma(request.getParameter("nombre"));
    	forma.setTipoFormaDTO(new ValorDTO(NumberUtils.toLong(request.getParameter("tipoForma"))));
    	
    	Long respuesta = 0L;
    	
    	try {
			formaDelegate.guardarForma(forma);
		} catch (NSJPNegocioException e) {
			respuesta = 1L;
			logger.error(e);
		}
		
		escribirRespuesta(response,converter.toXML(respuesta));
    	
    	
    	return null;
    	
    }
    
    /**
     * Consulta los campos forma disponibles en el sistema 
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward consultarCamposForma(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    
    	
    	try {
			List<CamposFormaDTO> resultado = formaDelegate.consultarCamposForma();
			converter.alias("camposFormaDTO", CamposFormaDTO.class);
			escribirRespuesta(response,converter.toXML(resultado));
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}
    	
    	
    	return null;
    }

    
    
    
    /**
     * Realiza la consulta de todas las plantillas por el flitro de tipo de documento
     * 
     * @param mapping Configuración del mapping
     * @param form Form generado
     * @param request Objeto request
     * @param response Objeto response
     * @return null
     */
    public ActionForward ConsultaExpedientesDocumento(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response)  {    	
    	try {
    			logger.info("_____________________________________________________________");
    			logger.info("EJECUTANDO CONSULTA Documentos ACTION");
    			logger.info("_____________________________________________________________");
    			
	    		//Se obtiene el id del tipo de documento
				String numeroExpedienteId = request.getParameter("numeroExpedienteId");
				logger.info("TIPO DE DOCUMENTO::::::::::"+numeroExpedienteId);
				
				ExpedienteDTO expedienteDTO = new ExpedienteDTO();
				expedienteDTO.setNumeroExpedienteId(NumberUtils.toLong(numeroExpedienteId));
				List<DocumentoDTO> documentoDTOs = new ArrayList<DocumentoDTO>();
				documentoDTOs = documentoDelegate.consultarDocumentosExpediente(expedienteDTO);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				int lTotalRegistros=documentoDTOs.size();
				
				writer.print("<records>" + lTotalRegistros + "</records>");
				
				for (DocumentoDTO documentoDTO : documentoDTOs) {
					writer.print("<row id='" + documentoDTO.getDocumentoId()+ "'>");
						//Nombre de la plantilla
						writer.print("<cell>" +  documentoDTO.getNombreDocumento()+ "</cell>");
						//Tipo de plantilla
						
					writer.print("</row>");
				}			
				writer.print("</rows>");
				writer.flush();
				writer.close();
		    }catch (Exception e) {
				logger.error(e);
			}
		    
        return null;
    	
    }
        		
    public ActionForward consultarArchivoDigitalIframe(ActionMapping mapping,
    		ActionForm form, HttpServletRequest request,
    		HttpServletResponse response)  {    	
    	try {

    		logger.info("EJECUTANDO CONSULTAR ARCHIVO DIGITAL................");
    		Long documentoId = request.getParameter("documentoId")!=null?NumberUtils.toLong(request.getParameter("documentoId")):null;
    		Long archivoDigitalId = request.getParameter("archivoDigitalId")!=null?NumberUtils.toLong(request.getParameter("archivoDigitalId")):null;

    		byte[]contenido = documentoDelegate.consultarContenidoArchivoDigitalPorArchivoODocumento(documentoId, archivoDigitalId);
    		if(contenido != null){
    			escribirResponsePDF(contenido, request, response);
    		}else{
    			if (documentoId != null ){
    				DocumentoDTO documento = documentoDelegate.consultarDocumentoXId(documentoId);
    				if (documento != null && documento.getEsGuardadoParcial() && documento.getTextoParcial()!= null){
    					contenido = generarReportePDFDeHTML(documento.getTextoParcial()).toByteArray();
    					escribirResponsePDF(contenido, request, response);
    				}
    			}
    		}
    	}catch (Exception e) {
    		logger.error(e);
    	}
    	return null;
    }
    
    private void escribirResponsePDF(byte[] contenido, 
    		HttpServletRequest request, HttpServletResponse response) throws IOException{
    	ByteArrayOutputStream archivoPDF = null;
    	if (contenido != null){    		
    		archivoPDF = new ByteArrayOutputStream();
    		archivoPDF.write(contenido);
    		if(request.getParameter("inFrame") == null){
    			//Escribirlo como attachment
    			escribirReporte(response, archivoPDF, "documentoDigital");
    		}else{
    			//escribirlo en linea
    			response.setContentType(CONTENT_TYPE_PDF);
    			response.setHeader(ENCABEZADO_CACHE_CONTROL, 
    					ENCABEZADO_NOCACHE);
    			response.setHeader(ENCABEZADO_PRAGMA,
    					ENCABEZADO_NOCACHE);
    			response.setContentLength(archivoPDF.size());
    			ServletOutputStream sos = response.getOutputStream();
    			archivoPDF.writeTo(sos);
    			sos.flush();
    			sos.close();
    		}
    	}
    }
}


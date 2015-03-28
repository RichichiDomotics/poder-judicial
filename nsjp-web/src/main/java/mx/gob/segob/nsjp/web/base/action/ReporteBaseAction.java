/**
 * Nombre del Programa : ReporteBaseAction.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 May 2011
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
package mx.gob.segob.nsjp.web.base.action;

import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_APERTURA;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.BODY_TAG_CIERRE;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.CONTENT_TYPE_PDF;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_ATTACH_FILE_NAME;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CACHE_CONTROL;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_CONTENT_DISPOSITION;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_NOCACHE;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.ENCABEZADO_PRAGMA;
import static mx.gob.segob.nsjp.comun.constants.ConstantesGenerales.EXTENSION_PDF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mx.gob.segob.nsjp.comun.util.HTMLUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ReporteBaseAction extends GenericAction {
    private static final Logger logger = Logger
            .getLogger(ReporteBaseAction.class);
    /**
     * Escribe el reporte en el <code>OutputStream</code> del <code>response</code>.
     * @param response
     * @param baos
     * @param fileName
     */
    protected void escribirReporte(HttpServletResponse response,
            ByteArrayOutputStream baos, String fileName) {
        try {
        	
        	String fileNameWihtoutSpaces = fileName.replace(' ', '_');
            response.setContentType(CONTENT_TYPE_PDF);
            response.setHeader(ENCABEZADO_CONTENT_DISPOSITION,
            		ENCABEZADO_ATTACH_FILE_NAME
                    + fileNameWihtoutSpaces + EXTENSION_PDF);
            response.setHeader(ENCABEZADO_CACHE_CONTROL, 
            		ENCABEZADO_NOCACHE);
            response.setHeader(ENCABEZADO_PRAGMA,
            		ENCABEZADO_NOCACHE);
            response.setContentLength(baos.size());
            ServletOutputStream sos = response.getOutputStream();
            baos.writeTo(sos);
            sos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected ByteArrayOutputStream generarReportePDF(
            Map<String, Object> parametrosReporte, String urlPlantilla)
            throws JRException {
        return generarReporteJasper(parametrosReporte, urlPlantilla,
                new JREmptyDataSource());
    }
    /**
     * Utiliza las librer�as de XHTML y iText para generar
     * un reporte en PDF a partir de un archivo XHTML
     * @param xml
     * @return
     */
    @SuppressWarnings("deprecation")
	protected ByteArrayOutputStream generarReportePDFDeHTML(String xHTML){
    	
    	ByteArrayOutputStream archivoPDF = null;
    	try{
    		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    	    Document doc = builder.parse(new StringBufferInputStream(
    	    		HTMLUtils.encodeHtmlToXhtml(BODY_TAG_APERTURA 
            				+ xHTML +
            				BODY_TAG_CIERRE)));

    	    ITextRenderer renderer = new ITextRenderer();
    	    renderer.setDocument(doc, null);
    	    
            archivoPDF = new ByteArrayOutputStream();
            
    	    renderer.layout();
    	    
    	    renderer.createPDF(archivoPDF);
    	}catch (Exception e) {
			logger.error(e);
		}
    	
	    
	    return archivoPDF;
    	
    }

    /**
     * 
     * @param parametrosReporte
     * @param urlPlantilla
     * @param jrds
     * @return
     * @throws JRException
     */
    protected ByteArrayOutputStream generarReporteJasper(
            Map<String, Object> parametrosReporte, String urlPlantilla,
            JRDataSource jrds) throws JRException {
        ByteArrayOutputStream baos = null;
        // Obtiene la ruta real de la aplicacion, para poder ubicar la plantilla
        // y guardar el reporte
        String realPath = getRealPath("jasper/");
        final String entePublico = "mpio-slp"; // TODO recuperar el nombre del
                                               // cliente
        String rutaImagenes = getRealPath("/imagenes/" + entePublico + "/");
        if (parametrosReporte == null) {
            parametrosReporte = new HashMap<String, Object>();
        }

        parametrosReporte.put("rutaImagenes", rutaImagenes);

        if (logger.isDebugEnabled()) {
            logger.debug("[SEMN] realPath::" + realPath);
            logger.debug("[SEMN] urlPlantilla::" + urlPlantilla);
            logger.debug("parametrosReporte :: " + parametrosReporte);

        }
        // Obtiene la plantilla del reporte y la compila

        JasperReport report = obtenerReporte(realPath +"/"+ urlPlantilla);
        logger.debug("[SEMN] report::" + report);
        JasperPrint print = null;
        if (jrds != null) {
            print = JasperFillManager.fillReport(report, parametrosReporte,
                    jrds);
        }
        if (print != null) {
            baos = new ByteArrayOutputStream();

            // Exportar Formato PDF
//            JasperExportManager.exportReportToPdfStream(print, baos);
            JRPdfExporter exporter = new JRPdfExporter();
            
            exporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                    print);
            exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
                    baos);
            exporter.exportReport();
        }
        return baos;
    }

    /**
     * 
     * @param ruta
     * @return
     * @throws URISyntaxException
     */
    protected String getRealPath(String ruta) {
        logger.debug("ruta :: " + ruta);
        String path = getServlet().getServletContext().getRealPath(ruta);
        logger.debug("path :: " + path);
        return path;
    }

    protected JasperReport obtenerReporte(String rutaTemplate) {
        // final String ruta = realPath + urlPlantilla;
        JasperReport report = null;
        logger.info("... en obtenerReporte->" + rutaTemplate);
        InputStream template;
        try {

            template = new FileInputStream(new File(rutaTemplate));
            report = (JasperReport) JRLoader.loadObject(template);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (JRException e) {
            logger.error(e.getMessage(), e);
        }

        return report;
    }

}

/**
 * Nombre del Programa : GraficaDenunciasVSTipoDelitoAction.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Action para generar la grafica de denuncias vs tipo de delito
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
package mx.gob.segob.nsjp.web.graficacion.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.graficacion.GraficacionDelegate;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action para generar la grafica de denuncias vs tipo de delito.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class ReportesAction extends ReporteBaseAction {

    /**
	 * 
	 */
    public static final Logger logger = Logger.getLogger(ReportesAction.class);

    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private ExpedienteDelegate expedienteDelegate;
    @Autowired
    private DelitoDelegate delitoDelegate;
    @Autowired
    private GraficacionDelegate graficacionDelegate;
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward visorReportes(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            String inicial = request.getParameter("tiempoI");
            logger.info("Tiempo inicial::" + inicial);
            String fin = request.getParameter("tiempoF");
            logger.info("Tiempo Final::" + fin);

            String tipoReporte = request.getParameter("tipoReporte");
            logger.info("Tipo Reporte::" + tipoReporte);
            final Map<String, Object> parametrosReporte = new HashMap<String, Object>();
            parametrosReporte.put("fechaInicio", inicial);
            parametrosReporte.put("fechaFin", fin);
            Date fechaInicial = DateUtils.obtener(inicial);
            Date fechaFin = DateUtils.obtener(fin);
            final List<Map<String, String>> datos = new ArrayList<Map<String, String>>();
            Map<String, String> row = null;
            if (tipoReporte.equals("0")) {
                parametrosReporte.put("tituloReporte",
                        "Denuncia VS Tipo Delito");
                List<Object[]> expedientesResult = expedienteDelegate
                        .expedientesPorMes(fechaInicial, fechaFin);
                List<Object[]> delGravesResult = delitoDelegate
                        .delitosTipoPorMes(fechaInicial, fechaFin, true);
                List<Object[]> delNoGravesResult = delitoDelegate
                        .delitosTipoPorMes(fechaInicial, fechaFin, false);

                for (Object[] objects : expedientesResult) {
                    row = new HashMap<String, String>();
                    row.put("campo1", "Expediente por mes");
                    row.put("campo2", String.valueOf(objects[1]));
                    datos.add(row);
                }
                for (Object[] objects : delGravesResult) {
                    row = new HashMap<String, String>();
                    row.put("campo1", "Delitos graves");
                    row.put("campo2", String.valueOf(objects[1]));
                    datos.add(row);
                }
                for (Object[] objects : delNoGravesResult) {
                    row = new HashMap<String, String>();
                    row.put("campo1", "Delitos no graves");
                    row.put("campo2", String.valueOf(objects[1]));
                    datos.add(row);
                }
            }

            if (tipoReporte.equals("7")) {
                parametrosReporte.put("tituloReporte", "Casos Remitidos");
                List<Object[]> remitidosResult = graficacionDelegate
                        .obtenerCasosRemitidos(fechaInicial, fechaFin);
                for (Object[] objects : remitidosResult) {
                    row = new HashMap<String, String>();
                    row.put("campo1", String.valueOf(objects[0]));
                    row.put("campo2", "Casos Remitidos");
                    row.put("campo3", String.valueOf(objects[1]));
                    datos.add(row);
                }
            }
            logger.debug("datos.size() :: " + datos.size());
            JRDataSource jrds = new JRMapCollectionDataSource(datos);
            ByteArrayOutputStream baos = super.generarReporteJasper(
                    parametrosReporte, "/layoutBasico.jasper", jrds);
            super.escribirReporte(response, baos,
                    String.valueOf(parametrosReporte.get("tituloReporte"))
                            .replace(" ", "") + SDF.format(new Date()) + ".pdf");

        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
        } catch (JRException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

}

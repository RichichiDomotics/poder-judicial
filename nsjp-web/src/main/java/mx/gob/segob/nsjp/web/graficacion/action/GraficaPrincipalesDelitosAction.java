/**
* Nombre del Programa : GraficaDenunciasVSTipoDelitoAction.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Action para generar la grafica de pirncipales delitos
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

/**
 * Action para generar la grafica de pirncipales delitos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaPrincipalesDelitosAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaPrincipalesDelitosAction.class);
	
	@Autowired
	private DelitoDelegate delitoDelegate;
	
	public ActionForward graficaPricipalesDelitos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String inicial = request.getParameter("tiempoI");
			logger.info("Tiempo inicial::"+inicial);
			String fin = request.getParameter("tiempoF");
			logger.info("Tiempo Final::"+fin);
			
			Date fechaInicio = DateUtils.obtener(inicial);
			Date fechaFin = DateUtils.obtener(fin);
		
			List<Object[]> violacionResult = delitoDelegate.obtenerDelitoPorMes(fechaInicio, fechaFin, new Long(1));
			List<Object[]> robosResult = delitoDelegate.obtenerDelitoPorMes(fechaInicio, fechaFin, new Long(2));
			List<Object[]> dañoResult = delitoDelegate.obtenerDelitoPorMes(fechaInicio, fechaFin, new Long(3));
//			List<Object[]> violacionResult = delitoDelegate.obtenerDelitoPorMes(fechaInicio, fechaFin, new Long(1));
//			List<Object[]> robosResult = delitoDelegate.obtenerDelitoPorMes(fechaInicio, fechaFin, new Long(2));
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (Object[] objects : violacionResult) {				
				dataset.addValue((Number)objects[1], objects[0].toString(), "Violacion");				
			}									
			for (Object[] objects : robosResult) {
				dataset.addValue((Number)objects[1], objects[0].toString(), "Robos");
			} 
			for (Object[] objects : dañoResult) {
				dataset.addValue((Number)objects[1], objects[0].toString(), "Da�o propiedad");
			}			
			
			JFreeChart chart = ChartFactory.createBarChart("Principales Delitos", "DELITOS", "MESES", 
															dataset, PlotOrientation.VERTICAL, true, true, true);			
		
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 600, 300);
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}	
		return null;
	}
	
}

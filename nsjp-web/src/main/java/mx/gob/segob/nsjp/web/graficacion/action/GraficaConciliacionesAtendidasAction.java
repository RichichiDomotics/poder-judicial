/**
* Nombre del Programa : GraficaConciliacionesAtendidasAction.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Genera la grafica de consiliaciones atendidas
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
import mx.gob.segob.nsjp.delegate.graficacion.GraficacionDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

/**
 * Genera la grafica de consiliaciones atendidas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaConciliacionesAtendidasAction extends GenericAction {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaConciliacionesAtendidasAction.class);
	
	@Autowired
	private GraficacionDelegate graficacionDelegate;
	
	public ActionForward graficaConsilacionesAtendidas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String inicial = "01/07/2011";
			String fin = "31/08/2011";
			
			Date fechaInicio = DateUtils.obtener(inicial);
			Date fechaFin = DateUtils.obtener(fin);
		
			List<Object[]> remitidosResult= graficacionDelegate.obtenerCasosRemitidos(fechaInicio, fechaFin);						
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (Object[] objects : remitidosResult) {				
				dataset.addValue((Number)objects[1], "Casos Remitidos", objects[0].toString());				
			}									
			
			JFreeChart chart = ChartFactory.createLineChart("Casos Remitidos", "MESES", "CASOS REMITIDOS", 
															dataset, PlotOrientation.VERTICAL, true, true, true);			
		
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 300, 300);
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}	
		return null;
	}
}

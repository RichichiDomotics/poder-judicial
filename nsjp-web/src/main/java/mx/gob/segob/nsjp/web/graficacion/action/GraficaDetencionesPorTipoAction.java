/**
* Nombre del Programa : GraficaDetencionesPorTipoAction.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Action para generar la grafica Detenciones por tipo
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action para generar la grafica Detenciones por tipo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaDetencionesPorTipoAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaDetencionesPorTipoAction.class);
	
	@Autowired
	DelitoDelegate delitoDelegate;
	
	public ActionForward graficaDetencionesPorTipo(ActionMapping mapping, ActionForm form,
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
		
			Long faltasResult = delitoDelegate.obtenerDetenidosPorMesYDelito(fechaInicio, fechaFin, new Long(1));
			Long flagranciaResult = delitoDelegate.obtenerDetenidosPorMesYDelito(fechaInicio, fechaFin, new Long(2));						
			
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			dataset.setValue("Faltas administrativas", faltasResult);
			dataset.setValue("Flagrancia", flagranciaResult);
						
			JFreeChart chart = ChartFactory.createPieChart("Detenciones por tipo", dataset, true, true, true);																		
			
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 600, 300);
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}	
		return null;
	}
}

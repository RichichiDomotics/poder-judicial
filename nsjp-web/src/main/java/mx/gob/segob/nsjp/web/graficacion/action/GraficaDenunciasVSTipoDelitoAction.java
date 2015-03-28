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

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action para generar la grafica de denuncias vs tipo de delito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaDenunciasVSTipoDelitoAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaDenunciasVSTipoDelitoAction.class);
	
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	@Autowired 
	private DelitoDelegate delitoDelegate;
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward graficaDenunciaVSTipoDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			
			String inicial = request.getParameter("tiempoI");
			logger.info("Tiempo inicial::"+inicial);
			String fin = request.getParameter("tiempoF");
			logger.info("Tiempo Final::"+fin);
			Date fechaInicial = DateUtils.obtener(inicial);
			Date fechaFin = DateUtils.obtener(fin);
		
			List<Object[]> expedientesResult = expedienteDelegate.expedientesPorMes(fechaInicial, fechaFin);
			List<Object[]> delGravesResult = delitoDelegate.delitosTipoPorMes(fechaInicial, fechaFin, true);
			List<Object[]> delNoGravesResult = delitoDelegate.delitosTipoPorMes(fechaInicial, fechaFin, false);
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (Object[] objects : expedientesResult) {				
				dataset.addValue((Number)objects[1], "Denuncias", objects[0].toString());				
			}									
			for (Object[] objects : delGravesResult) {
				dataset.addValue((Number)objects[1], "Delitos Graves", objects[0].toString());
			} 
			for (Object[] objects : delNoGravesResult) {
				dataset.addValue((Number)objects[1], "Delitos No Graves", objects[0].toString());
			}			
			
			JFreeChart chart = ChartFactory.createBarChart("Denuncias vs Tipo de Delito", "MESES", "DENUNCIAS", 
															dataset, PlotOrientation.VERTICAL, true, true, true);			
		
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 600, 300);

//			ChartFrame frame = new ChartFrame("First", chart);
//			frame.pack();
//			frame.setVisible(true);							
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}			
		
		return null;
	}
	
	
	
}

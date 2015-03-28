/**
* Nombre del Programa : GraficaDeterminacionPorDenuncia.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Action para generar la grafica determinacion por denuncia
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
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.audiencia.AudienciaDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

/**
 * Action para generar la grafica determinacion por denuncia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaDeterminacionPorDenunciaAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaDeterminacionPorDenunciaAction.class);
	
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	@Autowired
	private AudienciaDelegate audienciaDelegate;
	
	public ActionForward graficaDeterminacionPorDenuncia(ActionMapping mapping, ActionForm form,
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
			List<Object[]> judicializadasResult = audienciaDelegate.obtenerAudienciasJudicializadasPorMes(fechaInicial, fechaFin);
			List<Object[]> temporales = expedienteDelegate.obtenerNumExpPorEstatusYMes(fechaInicial, fechaFin, EstatusExpediente.ARCHIVO_TEMPORAL);
			List<Object[]> definitivos = expedienteDelegate.obtenerNumExpPorEstatusYMes(fechaInicial, fechaFin, EstatusExpediente.ARCHIVO_DEFINITIVO);
			//FIXME CAS Descomentar la linea de abajo en cuanto se tenga listo el estatus de expediente NEAP
//			List<Object[]> temporales = expedienteDelegate.obtenerNumExpPorEstatusYMes(fechaInicial, fechaFin, EstatusExpediente.ARCHIVO_TEMPORAL);
			
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (Object[] objects : expedientesResult) {				
				dataset.addValue((Number)objects[1], "Denuncias", objects[0].toString());				
			}	
			for (Object[] objects : judicializadasResult) {
				dataset.addValue((Number)objects[1], "Judicializadas", objects[0].toString());
			}
			for (Object[] objects : temporales) {
				dataset.addValue((Number)objects[1], "Archivo temporal", objects[0].toString());
			}
			for (Object[] objects : definitivos) {
				dataset.addValue((Number)objects[1], "Archivo definitivo", objects[0].toString());
			}
			
			JFreeChart chart = ChartFactory.createBarChart("Denuncias vs Tipo de Delito", "MESES", "DENUNCIAS", 
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

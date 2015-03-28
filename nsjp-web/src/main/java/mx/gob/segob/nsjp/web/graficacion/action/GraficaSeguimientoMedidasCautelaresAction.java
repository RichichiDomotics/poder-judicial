/**
* Nombre del Programa : GraficaSeguimientoMedidasCautelaresAction.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
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
package mx.gob.segob.nsjp.web.graficacion.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.graficacion.GraficacionDelegate;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class GraficaSeguimientoMedidasCautelaresAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaSeguimientoMedidasCautelaresAction.class);
	
	@Autowired
	private GraficacionDelegate graficacionDelegate;
	
	public ActionForward graficaSeguimientoMedidasCautelares(ActionMapping mapping, ActionForm form,
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
		
			Long prisionPreventiva = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.Prision_Preventiva);
			Long garantiaEconomica = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.GARANTIA_ECONOMICA);
			Long someterVigilancia = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.SOMETER_A_VIGILANCIA);
			Long presentacionperiodica = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PRESENTARSE_PERIODICAMENTE);
			Long localizadorElec = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.COLOCAR_LOCALIZADORES_ELECTRONICOS);
			Long arrestroDomicilio = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.ARRESTO_DOMICILIARIO);
			Long prohibirConvivencia = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PROHIBIR_DETERMINADA_CONVIVENCIA);
			Long separacionDomicilio = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.SEPARACION_DEL_DOMICILIO);
			Long suspenderDerechos = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.SUSPENDER_DERECHOS);
			Long internoCentroSalud = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.INTERNAR_EN_CENTRO_DE_SALUD);
			Long prohibirSalidaTer = graficacionDelegate.obtenerMedCauPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PROHIBIR_SALIDA_TERRITORIAL);
			
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			dataset.setValue("Prision preventiva", prisionPreventiva);
			dataset.setValue("Garantia economica", garantiaEconomica);
			dataset.setValue("Someter a vigilancia", someterVigilancia);
			dataset.setValue("Presentarse periodicamente", presentacionperiodica);
			dataset.setValue("Colocar localizadores electronicos", localizadorElec);
			dataset.setValue("Arresto domiciliario", arrestroDomicilio);
			dataset.setValue("Prohibir determinada convivencia", prohibirConvivencia);
			dataset.setValue("Separacion de domicilio", separacionDomicilio);
			dataset.setValue("Suspender derechos", suspenderDerechos);
			dataset.setValue("Internar en centro de salud", internoCentroSalud);
			dataset.setValue("Prohibir salida territorial", prohibirSalidaTer);
			
			
			JFreeChart chart = ChartFactory.createPieChart("Seguimiento Medidas Cautelares", dataset, true, true, true);
						
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 600, 300);
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}	
		return null;
	}
}

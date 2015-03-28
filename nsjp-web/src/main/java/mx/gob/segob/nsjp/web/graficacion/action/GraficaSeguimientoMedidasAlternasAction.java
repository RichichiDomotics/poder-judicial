/**
* Nombre del Programa : GraficaSeguimientoMedidasAlternasAction.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Genera la grafica seguimiento medidas alternas
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
 * Genera la grafica seguimiento medidas alternas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaSeguimientoMedidasAlternasAction extends GenericAction {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(GraficaSeguimientoMedidasAlternasAction.class);
	
	@Autowired
	private GraficacionDelegate graficacionDelegate;
	
	public ActionForward graficaSeguimientoMedidasAlternas(ActionMapping mapping, ActionForm form,
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
		
			Long localizadorElec = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.COLOCAR_LOCALIZADORES_ELECTRONICOS);
			Long internoCentroSalud = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.INTERNAR_EN_CENTRO_DE_SALUD);
			Long presentacionPeriodica = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PRESENTARSE_PERIODICAMENTE);
			Long prohibirConvivencia = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PROHIBIR_DETERMINADA_CONVIVENCIA);
			Long prohibirSalidaTer = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.PROHIBIR_SALIDA_TERRITORIAL);
			Long separacionDomicilio = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.SEPARACION_DEL_DOMICILIO);
			Long someterVigilancia = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.SOMETER_A_VIGILANCIA);
			
//			Long multaRepacionDano = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.);
//			Long estudiar = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.);									
//			Long trabajar = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.);						
//			Long desintoxicacion = graficacionDelegate.obtenerMedAltPorFechasYTipoNedida(fechaInicio, fechaFin, TipoMedida.);
			
			
			
			DefaultPieDataset dataset = new DefaultPieDataset();
			
			dataset.setValue("Colocar localizadores electronicos", localizadorElec);
			dataset.setValue("Internar en centro de salud", internoCentroSalud);
			dataset.setValue("Presentarse periodicamente", presentacionPeriodica);
			dataset.setValue("Prohibir determinada convivencia", prohibirConvivencia);
			dataset.setValue("Prohibir salida territorial", prohibirSalidaTer);
			dataset.setValue("Separacion de domicilio", separacionDomicilio);
			dataset.setValue("Someter a vigilancia", someterVigilancia);
			
//			dataset.setValue("Multa o reparacion de danios", multaRepacionDano);
//			dataset.setValue("Estudiar", estudiar);									
//			dataset.setValue("Trabajo", trabajar);						
//			dataset.setValue("Desintoxicacion", desintoxicacion);									
			
			JFreeChart chart = ChartFactory.createPieChart("Seguimiento Medidas Alternas", dataset, true, true, true);
						
			OutputStream out = response.getOutputStream();
	        response.setContentType("image/png");
	        ChartUtilities.writeChartAsPNG(out, chart, 600, 300);
		} catch (NSJPNegocioException e) {
			logger.error(e);
		}	
		return null;
	}
}

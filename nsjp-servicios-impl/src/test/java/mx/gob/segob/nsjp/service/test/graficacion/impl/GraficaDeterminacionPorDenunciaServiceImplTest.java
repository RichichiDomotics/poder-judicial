/**
* Nombre del Programa : GraficaDeterminacionPorDenunciaServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del servicio GraficaDeterminacionPorDenunciaServiceImpl
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
package mx.gob.segob.nsjp.service.test.graficacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.service.graficacion.GraficaDeterminacionPorDenunciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del servicio GraficaDeterminacionPorDenunciaServiceImpl.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaDeterminacionPorDenunciaServiceImplTest extends
		BaseTestServicios<GraficaDeterminacionPorDenunciaService> {
	
	public void testObtenerAudienciasJudicializadasPorMes () {
		try {
			List<Object[]> respuesta = service.obtenerAudienciasJudicializadasPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"));
			
			assertTrue("La lista debe tener minimo un registro ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro " + respuesta.size());
			for (Object[] objects : respuesta) {
				logger.info("Mes "+objects[0]+" NUM "+objects[1]);
			}			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testObtenerNumExpPorEstatusYMes () {
	
		try {
			List<Object[]> respuesta = service.obtenerNumExpPorEstatusYMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), EstatusExpediente.ARCHIVO_TEMPORAL);
			assertTrue("La lista debe tener minimo un registro ", respuesta.size()>0);
			
			logger.info("La lista debe tener minimo un registro " + respuesta.size());
			for (Object[] objects : respuesta) {
				logger.info("Mes "+objects[0]+" NUM "+objects[1]);
			}																				
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
		
	}
}

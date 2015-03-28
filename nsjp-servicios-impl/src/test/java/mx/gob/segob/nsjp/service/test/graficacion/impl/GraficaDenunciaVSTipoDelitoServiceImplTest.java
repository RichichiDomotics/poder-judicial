/**
* Nombre del Programa : GraficaDenunciaVSTipoDelitoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio GraficaDenunciaVSTipoDelitoService
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.service.graficacion.GraficaDenunciaVSTipoDelitoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio GraficaDenunciaVSTipoDelitoService.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class GraficaDenunciaVSTipoDelitoServiceImplTest extends
		BaseTestServicios<GraficaDenunciaVSTipoDelitoService> {

	public void testExpedientesPorMes () {
		
		try {
			List<Object[]> resultado = service.expedientesPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"));
			assertTrue("La lista debe tener minimo un registro ", resultado.size()>0);
			
			for (Object[] objects : resultado) {
				logger.info("Mes "+ objects[0] + " num " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}		
	}
	
	public void testDelitosTipoPorMes () {
		try {
			List<Object[]> resultado = service.delitosTipoPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), true);
			assertTrue("La lista debe tener minimo un registro ", resultado.size()>0);
			
			for (Object[] objects : resultado) {
				logger.info("Mes "+ objects[0] + " num " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}	
	}
	
}

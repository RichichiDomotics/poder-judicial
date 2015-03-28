/**
* Nombre del Programa : CalcularCargaTrabajoServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class CalcularCargaTrabajoServiceImplTest extends BaseTestServicios<CalcularCargaTrabajoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.audiencia.impl.CalcularCargaTrabajoServiceImpl#calcularCargaTrabajo(java.lang.Long, java.lang.Long)}.
	 */
	public void testCalcularCargaTrabajo() {
		Long sumatoriaPRDelito=50L;
		Long complejidadAudiencia=3L;
		try {
			double carga = service.calcularCargaTrabajo(complejidadAudiencia, sumatoriaPRDelito);
			logger.info("La carga de trabajo es: "+carga);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

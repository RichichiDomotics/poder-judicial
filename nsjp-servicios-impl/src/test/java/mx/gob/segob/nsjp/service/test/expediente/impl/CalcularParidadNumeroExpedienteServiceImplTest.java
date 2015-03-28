/**
* Nombre del Programa : CalcularParidadNumeroExpedienteServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.CalcularParidadNumeroExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el cálculo y asignación de la paridad de
 * un número de expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class CalcularParidadNumeroExpedienteServiceImplTest extends BaseTestServicios<CalcularParidadNumeroExpedienteService> {

	
	public void testCalcularParidad(){
		
		try {
			Boolean esPar = service.consultarParidadDeNumeroExpediente(14L);
			logger.debug("Paridad: " + esPar);
		} catch (Exception e) {
			logger.error(e);
			assertNull(e);
		}
		
	}
}

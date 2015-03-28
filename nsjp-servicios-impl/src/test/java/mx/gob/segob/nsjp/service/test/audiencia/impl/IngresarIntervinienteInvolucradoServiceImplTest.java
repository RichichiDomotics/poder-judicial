/**
* Nombre del Programa : IngresarIntervinienteInvolucradoServiceImplTest.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/10/2011
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
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteInvolucradoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public class IngresarIntervinienteInvolucradoServiceImplTest extends BaseTestServicios<IngresarIntervinienteInvolucradoService>  {
	public void testIngresarIntervinienteInvolucrado() {
		try {
			IntervinienteDTO respuesta = service
					.ingresarIntervinienteInvolucrado(104L, 1L);

			assertTrue("Interviniente creado ", respuesta != null);
			logger.info("Interviniente: " + respuesta.getIntervinienteId());
			

			logger.info("-----------------------");

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}
	
	
}

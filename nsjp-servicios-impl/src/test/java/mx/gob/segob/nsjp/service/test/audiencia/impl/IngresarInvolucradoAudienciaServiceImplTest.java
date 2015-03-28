/**
* Nombre del Programa : IngresarInvolucradoAudienciaServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/09/2011
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
import mx.gob.segob.nsjp.service.audiencia.IngresarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el servicio que asocia un individuo a una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class IngresarInvolucradoAudienciaServiceImplTest extends BaseTestServicios<IngresarInvolucradoAudienciaService>{
	
	/**
	 * Prueba para asociar un involucrado a una audiencia
	 * Ambos deben existir
	 */
	public void textAsociarInvolucrado(){
		try {
			
			
			service.asociarInvolucradoAAudiencia(161L, 34L);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
	}

}

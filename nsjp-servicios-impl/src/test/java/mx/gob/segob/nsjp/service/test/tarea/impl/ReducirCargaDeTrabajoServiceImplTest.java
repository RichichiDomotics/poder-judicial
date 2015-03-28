/**
* Nombre del Programa : ReducirCargaDeTrabajoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servico de reduccion de la carga de trabajo de los funcionarios
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
package mx.gob.segob.nsjp.service.test.tarea.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.tarea.ReducirCargaDeTrabajoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servico de reduccion de la carga de trabajo de los funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ReducirCargaDeTrabajoServiceImplTest extends BaseTestServicios<ReducirCargaDeTrabajoService> {

	public void testReducirCargaDeTrabajo () {
		try {
			service.reducirCargaDeTrabajo();
			logger.info("/**** LA REDUCCION DE LA CARGA FUE EXITOSA ****/");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
			fail(e.getMessage());
		}
	}
}

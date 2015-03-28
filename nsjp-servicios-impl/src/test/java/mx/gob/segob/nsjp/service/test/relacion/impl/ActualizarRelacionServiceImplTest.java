/**
* Nombre del Programa : ActualizarRelacionServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Clase de Pruebas unitarias para los servicios implementados
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
package mx.gob.segob.nsjp.service.test.relacion.impl;

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.relacion.ActualizarRelacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de Pruebas unitarias para los servicios implementados
 * 
 * @version 1.0
 * @author GustavoBP 
 */
public class ActualizarRelacionServiceImplTest
	extends BaseTestServicios<ActualizarRelacionService>{

	public void testActualizarEsActivoRelaciones(){
		Long[] ids= {1L,2L,3L};
		List<Long> idRelaciones= Arrays.asList(ids);
		
		try {
			service.actualizarEsActivoRelaciones(idRelaciones);
		} catch (NSJPNegocioException e) {
			logger.info("Error al actualizar el estatus");
		}
	}
}

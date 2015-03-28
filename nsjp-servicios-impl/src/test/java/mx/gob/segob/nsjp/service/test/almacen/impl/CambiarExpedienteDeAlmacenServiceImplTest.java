/**
* Nombre del Programa : CambiarExpedienteDeAlmacenServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para del servicio para cambiar un expediente de almacen
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.almacen.CambiarExpedienteDeAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para del servicio para cambiar un expediente de almacen.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class CambiarExpedienteDeAlmacenServiceImplTest extends
		BaseTestServicios<CambiarExpedienteDeAlmacenService> {

	public void testCambiaExpedienteDeAlmacen () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(1L);
		
		AlmacenDTO almActual = new AlmacenDTO();
		almActual.setIdentificadorAlmacen(1L);
		
		AlmacenDTO almNuevo = new AlmacenDTO();
		almNuevo.setIdentificadorAlmacen(2L);
		
		try {
			service.cambiaExpedienteDeAlmacen(almActual, almNuevo, expedienteDTO);
			logger.info("/**** PRUEBA EXITOSA ****/");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
			fail();
		}		
	}
}

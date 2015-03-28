/**
* Nombre del Programa : RegistrarPermisoExpedienteServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Oct 2011
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.expediente.RegistrarPermisoExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author cesar
 *
 */
public class RegistrarPermisoExpedienteServiceImplTest extends
		BaseTestServicios<RegistrarPermisoExpedienteService> {

	public void testRegistrarPermisoExpedienteFuncionario() {
		
		try {
			service.registrarPermisoExpedienteFuncionario(new Long(3), new Long(1), new Date(), false);
			fail();
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testEliminarPermisoExpedienteFuncionario() {
		try {
			service.eliminarPermisoExpedienteFuncionario(new Long(3), new Long(1));
			fail();
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}

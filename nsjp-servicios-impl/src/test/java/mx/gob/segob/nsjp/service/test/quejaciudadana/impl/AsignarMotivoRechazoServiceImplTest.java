/**
 * 
 */
package mx.gob.segob.nsjp.service.test.quejaciudadana.impl;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.quejaciudadana.AsignarMotivoRechazoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class AsignarMotivoRechazoServiceImplTest extends BaseTestServicios<AsignarMotivoRechazoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.quejaciudadana.impl.AsignarMotivoRechazoServiceImpl#asignarMotivoRechazo(java.lang.Long, mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo)}.
	 */
	public void testAsignarMotivoRechazo() {
		try {
			service.asignarMotivoRechazo(2L, MotivoRechazo.DATOS_INSUFICIENTES);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

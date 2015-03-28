/**
 * 
 */
package mx.gob.segob.nsjp.service.test.parametro.impl;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ParametroDTO;
import mx.gob.segob.nsjp.service.parametro.ConsultarParametroService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarParametroServiceImplTest extends BaseTestServicios<ConsultarParametroService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.parametro.ConsultarParametroServiceImpl#consultarParametro(mx.gob.segob.nsjp.comun.enums.configuracion.Parametros)}.
	 */
	public void testConsultarParametro() {
		try {
			ParametroDTO result = service.consultarParametro(Parametros.ENTIDAD_FEDERATIVA_DESPLIEGUE);
			assertNotNull(result);
			logger.info("Parametro:"+result);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

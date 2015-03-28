package mx.gob.segob.nsjp.service.test.medida.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.medida.EnviarMedidaCautelarService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class EnviarMedidaCautelarServiceImplTest extends BaseTestServicios<EnviarMedidaCautelarService> {
	
	public void testEnviarMedidaCautelar() throws NSJPNegocioException{
		Long idMedida = 405L;
		service.enviarMedidaCautelarASSP(idMedida);
	}

}

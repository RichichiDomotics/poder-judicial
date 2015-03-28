package mx.gob.segob.nsjp.service.test.medida.impl;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusMedidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class CambiarEstatusMedidaServiceTest extends BaseTestServicios<CambiarEstatusMedidaService> {
	public void testCambiarEstatusMedida() throws NSJPNegocioException
	{
		service.cambiarEstatusMedida(244L, EstatusMedida.EN_PROCESO.getValorId());
	}
}

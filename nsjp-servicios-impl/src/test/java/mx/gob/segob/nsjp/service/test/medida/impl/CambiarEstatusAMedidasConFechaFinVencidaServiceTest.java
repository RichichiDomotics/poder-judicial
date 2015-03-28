package mx.gob.segob.nsjp.service.test.medida.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusAMedidasConFechaFinVencidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class CambiarEstatusAMedidasConFechaFinVencidaServiceTest extends BaseTestServicios<CambiarEstatusAMedidasConFechaFinVencidaService> {
	
	public void testObtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior() throws NSJPNegocioException
	{
		List<MedidaDTO> medidas = service.cambiarEstatusAMedidasConFechaFinVencidaService();
		logger.debug("Medidas encontradas: " + medidas.size());
		for(MedidaDTO row:medidas)	
		{
			logger.debug("Medida ID: " + row.getDocumentoId());
		}
	}
	
}

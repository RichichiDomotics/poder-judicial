package mx.gob.segob.nsjp.service.test.medida.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.service.medidasalternas.ObtenerMedidasAlternasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class ObtenerMedidasAlternasServiceTest extends BaseTestServicios<ObtenerMedidasAlternasService> {	
	
	public void testObtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior() throws NSJPNegocioException
	{
		List<MedidaAlternaDTO> medidas = service.obtenerMedidasAlternasIncumplidasDelDiaAnterior();
		logger.debug("Medidas encontradas: " + medidas.size());
		for(MedidaAlternaDTO row:medidas)	
		{
			logger.debug("Medida ID: " + row.getDocumentoId());
			logger.debug("Medida ID: " + row.getValorTipoMedida());
		}
	}
	
}

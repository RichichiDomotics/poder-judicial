package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;


public class AdministrarAudienciaJAVSServiceImplTest extends BaseTestServicios<AdministrarAudienciaJAVSService> {
	
	public void testCancelarAudiencia() {
		try {
			Long audienciaId = 229L;		
			service.cancelarAudiencia(audienciaId);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarAudiencia() {
		try {
			Long audienciaId = 296L;		
			service.consultarEstadoAudiencia(audienciaId);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

}

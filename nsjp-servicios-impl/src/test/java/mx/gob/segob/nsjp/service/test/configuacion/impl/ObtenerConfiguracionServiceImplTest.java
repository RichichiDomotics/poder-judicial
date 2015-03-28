/**
 * 
 */
package mx.gob.segob.nsjp.service.test.configuacion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO;
import mx.gob.segob.nsjp.service.configuracion.ObtenerConfiguracionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author AlineGS
 *
 */
public class ObtenerConfiguracionServiceImplTest extends BaseTestServicios<ObtenerConfiguracionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.configuacion.impl.ObtenerConfiguracionServiceImpl#obtgenerConfiguracionGlobal()}.
	 */
	public void testObtgenerConfiguracionGlobal() {
		
		ConfiguracionDTO configuracionDTO = null;
		
		configuracionDTO = service.obtgenerConfiguracionGlobal();
		logger.info("ConfiguracionDTO - getEntidadFederativaDespliegue:"+ configuracionDTO.getEntidadFederativaDespliegue());
		logger.info("ConfiguracionDTO - getUrlServidorChat:"+ configuracionDTO.getUrlServidorChat());
		logger.info("ConfiguracionDTO - getHabilitarTurno:"+ configuracionDTO.getHabilitarTurno());
		logger.info("ConfiguracionDTO - getTiempoRevisionAlarmas:"+ configuracionDTO.getTiempoRevisionAlarmas());
		logger.info("ConfiguracionDTO - getValidaDelitoGrave:"+ configuracionDTO.getValidaDelitoGrave());
		logger.info("ConfiguracionDTO - getUsuario:"+ configuracionDTO.getUsuario());
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.configuacion.impl.ObtenerConfiguracionServiceImpl#consultarInstitucionActual()}.
	 */
	public void testConsultarInstitucionActual() {
		try {
			ConfInstitucionDTO dto = service.consultarInstitucionActual();
			assertNotNull(dto);
			logger.info("Id: "+dto.getConfInstitucionId());
			logger.info("Clave: "+dto.getClave());
			logger.info("Nombre: "+dto.getNombreInst());
			logger.info("URL: "+dto.getUrlInst());
			logger.info("Actual: "+dto.getEsInstalacionActual());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

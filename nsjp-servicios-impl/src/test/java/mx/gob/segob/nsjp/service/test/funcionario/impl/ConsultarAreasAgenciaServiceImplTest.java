/**
 * 
 */
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAreasAgenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarAreasAgenciaServiceImplTest extends BaseTestServicios<ConsultarAreasAgenciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.funcionario.impl.ConsultarAreasAgenciaServiceImpl#consultarAreasXAgencia(java.lang.Long)}.
	 */
	public void testConsultarAreasXAgencia() {
		try {
			List<JerarquiaOrganizacionalDTO> areas = service.consultarAreasXAgencia(1L);
			logger.info("Existen "+ areas.size()+" areas");
			for (JerarquiaOrganizacionalDTO area : areas) {
				logger.info("------------------------------");
				logger.info("ID: "+area.getJerarquiaOrganizacionalId());
				logger.info("Nombre: "+area.getNombre());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

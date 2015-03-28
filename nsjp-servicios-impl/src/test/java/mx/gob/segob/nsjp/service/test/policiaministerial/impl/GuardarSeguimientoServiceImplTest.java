/**
 * 
 */
package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarSeguimientoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarSeguimientoServiceImplTest extends BaseTestServicios<GuardarSeguimientoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.GuardarSeguimientoServiceImpl#guardarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)}.
	 */
	public void testGuardarSeguimientoLI() {
		ExpedienteDTO expDTO=new ExpedienteDTO();
		SeguimientoLIDTO dto=new SeguimientoLIDTO();
		
		expDTO.setNumeroExpediente("NSJYUCPG20114433333");
//		expDTO.setNumeroExpedienteId(1L);
		
		dto.setSeguimientoLIId(null);
		dto.setFechaCreacion(new Date());
		dto.setHipotesis("Hipotesis de creación");

		dto.setExpedienteDTO(expDTO);
		try {
			Long idSeguim = service.guardarSeguimientoLI(dto);
			assertNotNull(idSeguim);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

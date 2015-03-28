/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.documento.GuardarControversiaResueltaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarControversiaResueltaServiceImplTest extends
		BaseTestServicios<GuardarControversiaResueltaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.GuardarControversiaResueltaServiceImpl#guardarControversiaResuelta(mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO)}.
	 */
	public void testGuardarControversiaResuelta() {
		CartaCumplimientoDTO dto=new CartaCumplimientoDTO();
		dto.setExpedienteDTO(new ExpedienteDTO(2L));
		dto.setFuncionario(new FuncionarioDTO(1L));
		dto.setArchivoDigital(new ArchivoDigitalDTO(1L));
		try {
			Long idCarta = service.guardarControversiaResuelta(dto);
			assertNotNull(idCarta);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

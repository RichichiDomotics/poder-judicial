/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.documento.ActualizarControversiaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ActualizarControversiaServiceImplTest extends BaseTestServicios<ActualizarControversiaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.ActualizarControversiaServiceImpl#actualizarControversia(mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO)}.
	 */
	public void testActualizarControversia() {
		CartaCumplimientoDTO cumplimientoDTO=new CartaCumplimientoDTO();
		cumplimientoDTO.setDocumentoId(2L);
		cumplimientoDTO.setFuncionario(new FuncionarioDTO(1L));
		try {
			service.actualizarControversia(cumplimientoDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

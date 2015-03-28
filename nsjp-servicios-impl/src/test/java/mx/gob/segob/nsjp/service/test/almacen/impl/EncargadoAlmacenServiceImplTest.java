/**
 * 
 */
package mx.gob.segob.nsjp.service.test.almacen.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.almacen.EncargadoAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class EncargadoAlmacenServiceImplTest extends BaseTestServicios<EncargadoAlmacenService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.almacen.impl.EncargadoAlmacenServiceImpl#asignarEncargadoDAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)}.
	 */
	public void testAsignarEncargadoDAlmacen() {
		try {
			Long idResp = service.asignarEncargadoDAlmacen(new AlmacenDTO(1L), new FuncionarioDTO(3L));
			assertNotNull(idResp);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.almacen.impl.EncargadoAlmacenServiceImpl#removerEncargadoDAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)}.
	 */
	public void testRemoverEncargadoDAlmacen() {
		try {
			Long idResp = service.removerEncargadoDAlmacen(new AlmacenDTO(2L), new FuncionarioDTO(4L));
			assertNotNull(idResp);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

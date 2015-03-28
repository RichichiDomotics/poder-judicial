/**
 * 
 */
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarEncargadosAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarEncargadosAlmacenServiceImplTest extends
		BaseTestServicios<ConsultarEncargadosAlmacenService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.almacen.impl.ConsultarEncargadosAlmacenServiceImpl#consultarEncargadosDAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)}.
	 */
	public void testConsultarEncargadosDAlmacen() {
		try {
			List<FuncionarioDTO> encargados = service.consultarEncargadosDAlmacen(null);
			assertNotNull(encargados);
			logger.info("Existen "+encargados.size()+" encargados");
			for (FuncionarioDTO fun : encargados) {
				logger.info("-----------------------------------");
				logger.info("ID: "+fun.getClaveFuncionario());
				logger.info("Nombre: "+fun.getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		
		}
	}

}

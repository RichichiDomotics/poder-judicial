/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.almacen.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.almacen.EncargadoAlmacenDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * @author adrian
 *
 */
public class EncargadoAlmacenDAOImplTest extends BaseTestPersistencia<EncargadoAlmacenDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.almacen.impl.EncargadoAlmacenDAOImpl#consultarEncargadosDAlmacen(java.lang.Long)}.
	 */
	public void testConsultarEncargadosDAlmacen() {
		List<Funcionario> encargados = daoServcice.consultarEncargadosDAlmacen(1L);
		assertNotNull(encargados);
		logger.info("Existen "+ encargados.size()+" encargados");
		for (Funcionario fun : encargados) {
			logger.info("Funcionario: "+fun);
		}
	}

}

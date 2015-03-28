/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.defensoria.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.defensoria.ArancelDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Arancel;

/**
 * @author adrian
 *
 */
public class ArancelDAOImplTest extends BaseTestPersistencia<ArancelDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.defensoria.impl.ArancelDAOImpl#consultarArancelesXNumeroExpediente(java.lang.Long)}.
	 * @throws NSJPNegocioException 
	 */
	public void testConsultarArancelesXNumeroExpediente() throws NSJPNegocioException {
		List<Arancel> aranceles = daoServcice.consultarArancelesXNumeroExpediente(1L, null);
		assertNotNull(aranceles);
		logger.info("Existen "+ aranceles.size()+" aranceles");
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.defensoria.impl.ArancelDAOImpl#consultarArancelesXFuncionario(java.lang.Long)}.
	 */
	public void testConsultarArancelesXFuncionario() {
		List<Arancel> aranceles = daoServcice.consultarArancelesXFuncionario(1L);
		assertNotNull(aranceles);
		logger.info("Existen "+ aranceles.size()+" aranceles");
	}

}

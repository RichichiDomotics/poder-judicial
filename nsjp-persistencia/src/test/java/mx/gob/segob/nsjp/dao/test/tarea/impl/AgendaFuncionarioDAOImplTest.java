/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.tarea.impl;

import mx.gob.segob.nsjp.dao.tarea.AgendaFuncionarioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AgendaFuncionario;

/**
 * @author adrian
 *
 */
public class AgendaFuncionarioDAOImplTest extends BaseTestPersistencia<AgendaFuncionarioDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.tarea.impl.AgendaFuncionarioDAOImpl#obtenerAgendaFuncionario(java.lang.Long)}.
	 */
	public void testObtenerAgendaFuncionario() {
		AgendaFuncionario agendaF = daoServcice.obtenerAgendaFuncionario(14L);
		assertNotNull(agendaF);
	}

}

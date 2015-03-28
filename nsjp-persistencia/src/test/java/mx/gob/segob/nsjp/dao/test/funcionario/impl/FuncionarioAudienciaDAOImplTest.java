package mx.gob.segob.nsjp.dao.test.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;

/**
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class FuncionarioAudienciaDAOImplTest
        extends BaseTestPersistencia<FuncionarioAudienciaDAO> {

	
	public void testConsultarCoordinadorDefensores() {
//			List<FuncionarioAudiencia> loFuncionariosAud = daoServcice.consultaFuncionariosPorAudiencia(286L);
//			for (FuncionarioAudiencia funcionarioAudiencia : loFuncionariosAud) {
//				daoServcice.delete(funcionarioAudiencia);
//			}
		
		FuncionarioAudiencia loFuncionarioAudiencia = new FuncionarioAudiencia(new FuncionarioAudienciaId(286L, 22L),null,null);
		daoServcice.delete(loFuncionarioAudiencia);

		
	}
	
	
	public void testConsultarFuncionariosPorAudiencia() {
		daoServcice.consultarFuncionariosPorAudiencia(286L);	
}
}
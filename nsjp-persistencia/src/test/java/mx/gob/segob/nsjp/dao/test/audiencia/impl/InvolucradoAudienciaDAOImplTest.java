/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.audiencia.impl;

import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;

/**
 * @author EduardoAD
 *
 */
public class InvolucradoAudienciaDAOImplTest extends BaseTestPersistencia<InvolucradoAudienciaDAO>{
	
	public void testConsultarIndicadorPresenteInvolucradoAudiencia(){
		InvolucradoAudiencia involucrado =  daoServcice.consultarIndicadorPresenteInvolucradoAudiencia(694L,335L);
		assertNull("involucrado es nulo", involucrado);
		//assertNotNull("involucrado no es nulo", involucrado);

		logger.info("ID involucrado: " + involucrado.getAudiencia().getAudienciaId());
		logger.info("ID audiencia:   " + involucrado.getId().getaudienciaId());
		logger.info("esPresente:     " + involucrado.getEsPresente());	
	}
}

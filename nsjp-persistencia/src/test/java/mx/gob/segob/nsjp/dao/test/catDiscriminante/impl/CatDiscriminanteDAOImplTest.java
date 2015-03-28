/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.catDiscriminante.impl;

import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDiscriminante;

/**
 * @author AlejandroGA
 *
 */
public class CatDiscriminanteDAOImplTest extends BaseTestPersistencia<CatDiscriminateDAO> {
	
	public void testConsultarCarDiscriminantePorId(){
		
		Long discriminanteId = 0L;
		
		CatDiscriminante catDis =  daoServcice.consultarDiscriminantePorId(discriminanteId);
		logger.info(" Cat Discriminante Obtenido: "+ catDis);
		logger.info(" Cat DiscriminanteId Obtenido: "+ catDis.getCatDiscriminanteId());
	}

}

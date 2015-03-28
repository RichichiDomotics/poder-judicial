/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoLugarDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelitoLugar;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoLugarDAOImplTest extends BaseTestPersistencia<CatDelitoLugarDAO>{
	
	public void testConsultarTodos() {
        List<CatDelitoLugar> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
        for (CatDelitoLugar catDelitoLugar : data) {
			logger.info(" ID :  "+ catDelitoLugar.getCatDelitoLugarId());
			logger.info(" Desc: "+ catDelitoLugar.getDescripcion() );			
		}
    }
}

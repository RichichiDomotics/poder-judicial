/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModusDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelitoModus;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoModusDAOImplTest extends BaseTestPersistencia<CatDelitoModusDAO>{
	
	public void testConsultarTodos() {
        List<CatDelitoModus> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
        for (CatDelitoModus catDelitoModus : data) {
			logger.info(" ID :  "+ catDelitoModus.getCatDelitoModusId());
			logger.info(" Clave :  "+ catDelitoModus.getClaveModus());
			logger.info(" Desc: "+ catDelitoModus.getDescripcion() );			
		}
    }
}

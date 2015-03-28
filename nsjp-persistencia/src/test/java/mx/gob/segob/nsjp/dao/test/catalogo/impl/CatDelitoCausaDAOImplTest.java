/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoCausaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelitoCausa;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoCausaDAOImplTest extends BaseTestPersistencia<CatDelitoCausaDAO>{
	
	public void testConsultarTodos() {
        List<CatDelitoCausa> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
        for (CatDelitoCausa catDelitoCausa : data) {
			logger.info(" ID :  "+ catDelitoCausa.getCatDelitoCausaId());
			logger.info(" Clave :  "+ catDelitoCausa.getClaveCausa());
			logger.info(" Desc: "+ catDelitoCausa.getDescripcion() );			
		}
    }
}

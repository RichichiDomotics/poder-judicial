/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoModalidadDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelitoModalidad;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoModalidadDAOImplTest extends BaseTestPersistencia<CatDelitoModalidadDAO>{
	
	public void testConsultarTodos() {
        List<CatDelitoModalidad> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
        for (CatDelitoModalidad catDelitoModalidad : data) {
			logger.info(" ID :  "+ catDelitoModalidad.getCatDelitoModalidadId());
			logger.info(" Clave :  "+ catDelitoModalidad.getClaveModalidad());
			logger.info(" Desc: "+ catDelitoModalidad.getDescripcion() );			
		}
    }
}

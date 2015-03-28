package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoClasificacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelitoClasificacion;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoClasificacionDAOImplTest extends BaseTestPersistencia<CatDelitoClasificacionDAO> {
	
    public void testConsultarTodos() {
        List<CatDelitoClasificacion> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
        for (CatDelitoClasificacion catDelitoClasificacion : data) {
			logger.info(" ID :  "+ catDelitoClasificacion.getCatDelitoClasificacionId());
			logger.info(" Desc: "+ catDelitoClasificacion.getDescripcion() );			
		}
    }

}

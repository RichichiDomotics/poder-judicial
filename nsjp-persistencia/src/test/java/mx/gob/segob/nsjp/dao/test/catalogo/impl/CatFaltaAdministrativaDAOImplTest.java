package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.CatFaltaAdministrativaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatFaltaAdministrativa;

public class CatFaltaAdministrativaDAOImplTest 
       extends BaseTestPersistencia<CatFaltaAdministrativaDAO>{
    public void testConsultarCatalogoFaltaAdministrativa()
    {
    	logger.debug("Catalogo de Faltas Administrativas :: " + daoServcice);
        List<CatFaltaAdministrativa> resp = daoServcice.consultarCatalogoFaltaAdministrativa();

        assertFalse("La lista no deberia estar vacía", resp.isEmpty());        
        logger.info("resp :: " + resp);
        
        for(CatFaltaAdministrativa reg: resp)
        {
        	logger.debug("---------------CATALOGO FALTA ADMINISTRATIVA-------------");
        	logger.debug("Id: " + reg.getCatFaltaAdministrativaId());
        	logger.debug("Clave Falta: " + reg.getClaveFalta());
        	logger.debug("Nombre Falta: " + reg.getNombreFalta());
        	logger.debug("Descripción Falta: " + reg.getDescripcionFalta());
        	logger.debug("-------------------------------------------------------");
        }
    }
}

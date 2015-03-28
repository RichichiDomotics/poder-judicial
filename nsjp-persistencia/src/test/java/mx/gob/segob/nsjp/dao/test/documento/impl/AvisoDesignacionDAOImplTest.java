package mx.gob.segob.nsjp.dao.test.documento.impl;

import mx.gob.segob.nsjp.dao.documento.AvisoDesignacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AvisoDesignacion;

public class AvisoDesignacionDAOImplTest extends BaseTestPersistencia<AvisoDesignacionDAO> {

	public void testEliminarAvisoDesignacionDAO(){
		AvisoDesignacion aviso =  daoServcice.read(60L);
		daoServcice.delete(aviso);
		
//		aviso =  daoServcice.read(60L);
//		daoServcice.delete(aviso);
//		aviso =  daoServcice.read(71L);
//		daoServcice.delete(aviso);
	}
}

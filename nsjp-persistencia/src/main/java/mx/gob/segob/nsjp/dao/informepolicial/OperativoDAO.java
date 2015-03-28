package mx.gob.segob.nsjp.dao.informepolicial;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Operativo;

public interface OperativoDAO 
extends GenericDao<Operativo,Long>{

	
	/**
	 * Consultar el Operativo asociado al IPH
	 * @param idIPH
	 * @return
	 */
	Operativo consultarOperativoPorIdIPH(Long idIPH);	
}

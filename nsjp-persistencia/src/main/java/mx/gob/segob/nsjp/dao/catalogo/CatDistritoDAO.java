/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDistrito;

/**
 * @author AlineGS
 *
 */
public interface CatDistritoDAO extends GenericDao<CatDistrito, Long> {
	
    public List<CatDistrito> consultarTodos();
    
    public CatDistrito consultarDistritoPorDiscriminante(Long discriminante);
}

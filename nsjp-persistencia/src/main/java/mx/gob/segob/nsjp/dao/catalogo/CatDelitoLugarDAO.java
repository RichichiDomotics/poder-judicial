/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoLugar;

/**
 * @author EduardoAD
 *
 */
public interface CatDelitoLugarDAO extends GenericDao<CatDelitoLugar, Long> {
	/**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelitoLugar> consultarTodos();
}

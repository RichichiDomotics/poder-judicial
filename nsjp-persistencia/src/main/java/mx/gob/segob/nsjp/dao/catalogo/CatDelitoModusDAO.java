/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoModus;

/**
 * @author EduardoAD
 *
 */
public interface CatDelitoModusDAO extends GenericDao<CatDelitoModus, Long> {
	/**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelitoModus> consultarTodos();
}

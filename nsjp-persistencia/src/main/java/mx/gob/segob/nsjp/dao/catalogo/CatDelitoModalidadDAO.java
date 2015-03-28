/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoModalidad;

/**
 * @author EduardoAD
 *
 */
public interface CatDelitoModalidadDAO extends GenericDao<CatDelitoModalidad, Long> {
	/**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelitoModalidad> consultarTodos();
}

/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoCausa;

/**
 * @author EduardoAD
 *
 */
public interface CatDelitoCausaDAO extends GenericDao<CatDelitoCausa, Long> {
	/**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelitoCausa> consultarTodos();
}

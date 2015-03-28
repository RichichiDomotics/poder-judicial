package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoClasificacion;

/**
 * @author EduardoAD
 *
 */
public interface CatDelitoClasificacionDAO extends GenericDao<CatDelitoClasificacion, Long>{
	/**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelitoClasificacion> consultarTodos();
}

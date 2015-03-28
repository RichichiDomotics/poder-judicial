/**
 * 
 */
package mx.gob.segob.nsjp.dao.quejaciudadana;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.QuejaCiudadana;

/**
 * @author adrian
 *
 */
public interface QuejaCiudadanaDAO extends GenericDao<QuejaCiudadana, Long> {

	/**
	 * Operación que realiza la funcionalidad de consultar las quejas ciudadanas asociadas al estatus recibido.
	 * @param estatus
	 * @return
	 */
	List<QuejaCiudadana> consultarQuejasPorEstatus(Long estatus);

}

/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDiscriminante;

/**
 * @author AlineGS
 *
 */
public interface CatDiscriminateDAO extends GenericDao<CatDiscriminante,Long> {

	/**
	 * Permite consultar los discriminantes de un distrito
	 * @param distritoID
	 * @param tipo 
	 * @return
	 */

	public String consultarClaveDiscrimiante(String dicriminanteId);

	List<CatDiscriminante> consultarDiscriminantesXDistrito(Long distritoID, Integer tipo);

	List<CatDiscriminante> consultarDiscriminantesXTipo(int ordinal);

	/**
	 * Permite consultar el catdiscriminante por su id
	 * @param dicriminanteId
	 * @return
	 */
	CatDiscriminante consultarDiscriminantePorId(Long dicriminanteId);
}

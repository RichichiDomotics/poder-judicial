/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.LineaInvestigacion;

/**
 * @author adrian
 *
 */
public interface LineaInvestigacionDAO extends
		GenericDao<LineaInvestigacion, Long> {

	List<LineaInvestigacion> consultarLineasInvestigacionXExpedienteId(
			Long expedienteId);

	/**
	 * Obtiene el maximo de consecutivo por expediente.
	 * @param expedienteId
	 * @return
	 */
	Integer maxConsecutivoPorExp(Long expedienteId);

}

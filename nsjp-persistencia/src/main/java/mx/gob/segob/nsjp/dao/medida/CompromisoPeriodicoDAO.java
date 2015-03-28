/**
 * 
 */
package mx.gob.segob.nsjp.dao.medida;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;

/**
 * @author adrian
 *
 */
public interface CompromisoPeriodicoDAO extends
		GenericDao<CompromisoPeriodico, Long> {

	/**
	 * Consulta el compromiso Periodico por el id de Medida
	 * 
	 * @param idMedida
	 * @return
	 */
	CompromisoPeriodico consultarCompromisoPeriodicoPorMedida(Long idMedida);
	
}

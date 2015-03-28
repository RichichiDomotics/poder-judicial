/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CartaCumplimiento;

/**
 * @author adrian
 *
 */
public interface CartaCumplimientoDAO extends
		GenericDao<CartaCumplimiento, Long> {

	/**
	 * Operación que consulta las cartas de controversia (Documentos tipo Carta de Cumplimiento de Acuerdo)
	 * @return
	 */
	List<CartaCumplimiento> consultarControversiasResueltas();

}

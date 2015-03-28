/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.SeguimientoLI;

/**
 * @author adrian
 *
 */
public interface SeguimientoLIDAO extends GenericDao<SeguimientoLI, Long> {

	List<SeguimientoLI> consultarSeguimientosLIXExpedienteId(
			Long numeroExpedienteId);

	Expediente consultarExpedienteDeSeguimiento(Long seguimientoLIId);

}

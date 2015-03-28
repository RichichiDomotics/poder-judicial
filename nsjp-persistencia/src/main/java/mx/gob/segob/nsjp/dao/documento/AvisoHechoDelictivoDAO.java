/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;

/**
 * @author adrian
 *
 */
public interface AvisoHechoDelictivoDAO extends GenericDao<AvisoHechoDelictivo, Long> {

	/**
	 * Operación que realiza la funcionalidad de consultar los "avisos de posibles hechos delictivos" de acuerdo al estatus que se recibe.
	 * @param estatusNotificacion: idEstatus
	 * @return
	 */
	List<AvisoHechoDelictivo> consultarAvisosHDelictivoPorEstatus(
			Long estatusNotificacion);

	/**
	 * 
	 * @return
	 */
	String obtenerUltimoFolio();

}

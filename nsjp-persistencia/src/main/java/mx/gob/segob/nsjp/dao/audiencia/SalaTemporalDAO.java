/**
 * Nombre del Programa : SalaTemporalDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DAO para SalaTemporal
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SalaTemporal;

/**
 * DAO para SalaTemporal.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface SalaTemporalDAO extends GenericDao<SalaTemporal, Long> {

	/**
	 * Metodo para recurar todos los registro de la entidad SalaTemporal
	 * @author CesarAgustin
	 * @return lista de entidad SalaTemporal
	 */
	List<SalaTemporal> consultarTodos();


}

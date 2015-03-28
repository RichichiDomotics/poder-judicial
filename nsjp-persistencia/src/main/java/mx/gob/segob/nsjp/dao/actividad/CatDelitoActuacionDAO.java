/**
 * Nombre del Programa : CatDelitoActuacion.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 24-08-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.actividad;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelitoActuacion;
import mx.gob.segob.nsjp.model.CatDelitoActuacionId;
import mx.gob.segob.nsjp.model.Valor;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface CatDelitoActuacionDAO extends
        GenericDao<CatDelitoActuacion, CatDelitoActuacionId> {
	/**
	 * Permite consultar las Actividades asociadas a un conjunto de identificadores de CatDelitos
	 * @param idCatDelitos
	 * @return List<Valor>
	 */
	public List<Valor> consultarActuacionesPorIdsCatDelito(List<Long> idCatDelitos);
	
}

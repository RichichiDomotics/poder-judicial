/**
 * Nombre del Programa : CiudadDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para accesar a la entidad Ciudad
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
package mx.gob.segob.nsjp.dao.domicilio;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Ciudad;

/**
 * Interface para accesar a la entidad Ciudad.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface CiudadDAO extends GenericDao<Ciudad, Long> {
    /**
     * 
     * @param idEntidadFederativa
     * @return
     */
    List<Ciudad> consultarPorEntidadFederativa(Long idEntidadFederativa);

    /**
     * Obtiene todos los registros de la entidad Ciudad 
     * @return
     */
	List<Ciudad> consultarTodos();
}

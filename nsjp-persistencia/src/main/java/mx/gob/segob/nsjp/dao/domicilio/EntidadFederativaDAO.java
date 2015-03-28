/**
* Nombre del Programa : EntidadFederativaDAO.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de accedo a datos para la tabla de entidad federativa.
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
import mx.gob.segob.nsjp.model.EntidadFederativa;

/**
 * Objeto de accedo a datos para la tabla de entidad federativa.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface EntidadFederativaDAO extends GenericDao<EntidadFederativa, Long> {
    /**
     * 
     * @param idPais
     * @return
     */
    List<EntidadFederativa> consultarPorPais(Long idPais);
    /**
     * Recupera todos los registros de la entidad EntidadFederativa
     * @return
     */
	List<EntidadFederativa> consultarTodos();
    
}

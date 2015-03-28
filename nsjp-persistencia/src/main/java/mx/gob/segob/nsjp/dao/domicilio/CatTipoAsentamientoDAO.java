/**
 * Nombre del Programa : TipoAsentamientoDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para accesar a la entidad cattipoasentamiento
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
import mx.gob.segob.nsjp.model.CatTipoAsentamiento;

/**
 * Interface para accesar a la entidad cattipoasentamiento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface CatTipoAsentamientoDAO
        extends
            GenericDao<CatTipoAsentamiento, Long> {
    /**
     * Recupera todos los tipos de asentamientos.
     * @return
     */
    List<CatTipoAsentamiento> consultarTodos();
}

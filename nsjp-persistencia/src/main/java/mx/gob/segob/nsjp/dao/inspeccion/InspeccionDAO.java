/**
 * Nombre del Programa : InspeccionDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.inspeccion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Inspeccion;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface InspeccionDAO extends GenericDao<Inspeccion, Long> {
    /**
     * 
     * @return
     */
    String obtenerUltimoFolio();
    /**
     * 
     * @param cveFuncionarioInspeccionado
     * @param numExpIdInspeccionado
     * @return
     */
    List<Inspeccion> consultarInspecciones(Long cveFuncionarioInspeccionado, Long numExpIdInspeccionado);
}

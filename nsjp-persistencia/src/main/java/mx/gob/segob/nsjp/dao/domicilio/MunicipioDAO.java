/**
 * Nombre del Programa : MuicipioDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para accesar a la entidad Municipio
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
import mx.gob.segob.nsjp.model.Municipio;

/**
 * Interface para accesar a la entidad Municipio.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface MunicipioDAO extends GenericDao<Municipio, Long> {
    /**
     * 
     * @param identidadFederativa
     * @return
     */
    List<Municipio> consultarPorEntidadFederativa(Long identidadFederativa);
}

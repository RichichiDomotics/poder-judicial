/**
 * Nombre del Programa : AudienciaEvidenciaDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DAO de AudienciaEvidencia
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
import mx.gob.segob.nsjp.model.AudienciaEvidencia;
import mx.gob.segob.nsjp.model.AudienciaEvidenciaId;

/**
 * DAO de AudienciaEvidencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AudienciaEvidenciaDAO
        extends
            GenericDao<AudienciaEvidencia, AudienciaEvidenciaId> {

    List<AudienciaEvidencia> consultarObjetosByAudiencia(Long id);

}

/**
 * Nombre del Programa : AudienciaEvidenciaDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del  DAO de AudienciaEvidencia
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
package mx.gob.segob.nsjp.dao.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.audiencia.AudienciaEvidenciaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.AudienciaEvidencia;
import mx.gob.segob.nsjp.model.AudienciaEvidenciaId;

import org.springframework.stereotype.Repository;

/**
 * Implementación del DAO de AudienciaEvidencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class AudienciaEvidenciaDAOImpl
        extends
            GenericDaoHibernateImpl<AudienciaEvidencia, AudienciaEvidenciaId>
        implements
            AudienciaEvidenciaDAO {
    @SuppressWarnings("unchecked")
    @Override
    public List<AudienciaEvidencia> consultarObjetosByAudiencia(Long id) {
        return getHibernateTemplate().find(
                "from AudienciaEvidencia o where o.id.audienciaId = ?", id);
    }

}

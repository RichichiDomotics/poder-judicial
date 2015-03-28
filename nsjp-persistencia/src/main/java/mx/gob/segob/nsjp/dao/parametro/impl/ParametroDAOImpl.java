/**
 * Nombre del Programa : ParametroDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos de acceso a datos para la entidad Parametros
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
package mx.gob.segob.nsjp.dao.parametro.impl;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.model.Parametro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos para la entidad Parametros.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class ParametroDAOImpl extends GenericDaoHibernateImpl<Parametro, Long>
        implements
            ParametroDAO {

    @Override
    public Parametro obtenerPorClave(Parametros clveParam) {
        logger.debug("Recuperando " + clveParam);
        final StringBuffer cq = new StringBuffer();
        cq.append(" from Parametro where clave = :cve");
        final Query qry = super.getSession().createQuery(cq.toString())
                .setParameter("cve", clveParam.name());
        return (Parametro) qry.uniqueResult();
    }

}

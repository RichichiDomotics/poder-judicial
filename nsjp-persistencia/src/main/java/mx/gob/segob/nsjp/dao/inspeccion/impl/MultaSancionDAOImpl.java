/**
 * Nombre del Programa : MultaSancionDAOImpl.java
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
package mx.gob.segob.nsjp.dao.inspeccion.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.inspeccion.MultaSancionDAO;
import mx.gob.segob.nsjp.model.MultaSancion;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class MultaSancionDAOImpl
        extends
            GenericDaoHibernateImpl<MultaSancion, Long>
        implements
            MultaSancionDAO {

    @Override
    public String obtenerUltimoFolio() {
        final StringBuffer query = new StringBuffer();
        query.append("SELECT MAX(n.folioMultaSancion) ");
        query.append("FROM MultaSancion n ");
        query.append("WHERE n.folioMultaSancion like '%");
        query.append(Calendar.getInstance().get(Calendar.YEAR));
        query.append("%'");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return (String) hbq.uniqueResult();
    }

    @Override
    public List<MultaSancion> consultarMultas(Long cveFunMultado) {
        final StringBuffer query = new StringBuffer();
        query.append("FROM MultaSancion m ");
        query.append("WHERE m.funcionarioMultado.claveFuncionario = ");
        query.append(cveFunMultado);
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return hbq.list();
    }

}

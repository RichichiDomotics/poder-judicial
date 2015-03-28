/**
 * Nombre del Programa : InspeccionDAOImpl.java
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

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.inspeccion.InspeccionDAO;
import mx.gob.segob.nsjp.model.Inspeccion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class InspeccionDAOImpl
        extends
            GenericDaoHibernateImpl<Inspeccion, Long> implements InspeccionDAO {

    @Override
    public String obtenerUltimoFolio() {
        final StringBuffer query = new StringBuffer();
        query.append("SELECT MAX(n.folioInspeccion) ");
        query.append("FROM Inspeccion n ");
        query.append("WHERE n.folioInspeccion like '%");
        query.append(Calendar.getInstance().get(Calendar.YEAR));
        query.append("%'");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return (String) hbq.uniqueResult();
    }

    @Override
    public List<Inspeccion> consultarInspecciones(
            Long cveFuncionarioInspeccionado, Long numExpIdInspeccionado) {
        final StringBuffer query = new StringBuffer();
        query.append("FROM Inspeccion i ");
        query.append("WHERE i.funcionarioInspeccionado.claveFuncionario = ");
        query.append(cveFuncionarioInspeccionado);
        if (numExpIdInspeccionado != null) {
            query.append(" AND i.numeroExpediente.numeroExpedienteId = ");
            query.append(numExpIdInspeccionado);
        }
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return hbq.list();
    }
}

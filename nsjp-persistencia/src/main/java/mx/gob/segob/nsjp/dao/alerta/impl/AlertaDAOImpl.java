/**
 * 
 */
package mx.gob.segob.nsjp.dao.alerta.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Usuario;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 * 
 */
@Repository
public class AlertaDAOImpl extends GenericDaoHibernateImpl<Alerta, Long>
        implements
            AlertaDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Alerta> consultarAlertasXUsuario(Usuario usuario,
            EstatusAlarmaAlerta estatusAlerta) {
        final StringBuffer queryStr = new StringBuffer();

        queryStr.append(" FROM Alerta al");
        queryStr.append(" WHERE al.usuario.usuarioId = ");
        queryStr.append(usuario.getUsuarioId());
        if (estatusAlerta != null && estatusAlerta.getValorId() != null) {
            queryStr.append(" AND al.estatusAlarmaAlerta=");
            queryStr.append(estatusAlerta.getValorId());
        }
        queryStr.append(" AND CONVERT (varchar, al.fechaAlerta, 120) <= '");
        queryStr.append(DateUtils.formatearBD120(new Date()));
        queryStr.append("'");

        if (logger.isDebugEnabled()) {
            logger.debug("queryStr :: " + queryStr);
        }

        Query qry = super.getSession().createQuery(queryStr.toString());
        return qry.list();
    }

}

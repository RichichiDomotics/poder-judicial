/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.policiaministerial.SeguimientoLIDAO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.SeguimientoLI;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class SeguimientoLIDAOImpl extends GenericDaoHibernateImpl<SeguimientoLI, Long>
		implements SeguimientoLIDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SeguimientoLI> consultarSeguimientosLIXExpedienteId(
			Long expedienteId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" FROM SeguimientoLI sl");
		sb.append(" WHERE sl.expediente.expedienteId="+expedienteId);
		
		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}

	@Override
	public Expediente consultarExpedienteDeSeguimiento(Long seguimientoLIId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT sl.expediente FROM SeguimientoLI sl");
		sb.append(" WHERE sl.seguimientoLIId="+seguimientoLIId);
		
		Query query = super.getSession().createQuery(sb.toString());
		return (Expediente) query.uniqueResult();
	}


}

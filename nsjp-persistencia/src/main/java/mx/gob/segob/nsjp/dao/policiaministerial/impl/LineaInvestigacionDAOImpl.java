/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.policiaministerial.LineaInvestigacionDAO;
import mx.gob.segob.nsjp.model.LineaInvestigacion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class LineaInvestigacionDAOImpl extends GenericDaoHibernateImpl<LineaInvestigacion, Long>
		implements LineaInvestigacionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LineaInvestigacion> consultarLineasInvestigacionXExpedienteId(
			Long expedienteId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" FROM LineaInvestigacion li");
		sb.append(" WHERE li.expediente.expedienteId="+expedienteId);
		
		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}

	@Override
	public Integer maxConsecutivoPorExp(Long expedienteId) {
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("SELECT MAX(l.consecutivo) FROM LineaInvestigacion l ")
					.append("WHERE l.expediente.expedienteId=").append(expedienteId);
		
		Query query = super.getSession().createQuery(queryString.toString());
		return (Integer) query.uniqueResult();
	}

}

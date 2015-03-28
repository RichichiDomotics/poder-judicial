/**
 * 
 */
package mx.gob.segob.nsjp.dao.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.ConvenioDAO;
import mx.gob.segob.nsjp.model.Convenio;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class ConvenioDAOImpl extends GenericDaoHibernateImpl<Convenio, Long> implements ConvenioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Convenio> consultarConveniosPorExpediente(
			Long numeroExpedienteId) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" FROM Convenio ar");
		queryStr.append(" WHERE ar.numeroExpediente.numeroExpedienteId="+numeroExpedienteId);
		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return qry.list();
	}

}

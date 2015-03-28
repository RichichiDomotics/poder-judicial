/**
 * 
 */
package mx.gob.segob.nsjp.dao.medida.impl;


import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.CompromisoPeriodicoDAO;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class CompromisoPeriodicoDAOImpl extends GenericDaoHibernateImpl<CompromisoPeriodico, Long>
		implements CompromisoPeriodicoDAO {

	@Override
	public CompromisoPeriodico consultarCompromisoPeriodicoPorMedida(Long idMedida){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT CP FROM  CompromisoPeriodico CP ");
		queryStr.append(" WHERE CP.medida.documentoId = ")
			.append(idMedida);
		logger.info("Query"+queryStr);
		Query query = super.getSession().createQuery(queryStr.toString());
		
		return (CompromisoPeriodico) query.uniqueResult();
	}
	
}

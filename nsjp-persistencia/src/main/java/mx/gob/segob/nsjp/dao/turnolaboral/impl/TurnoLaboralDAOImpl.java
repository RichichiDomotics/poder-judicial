package mx.gob.segob.nsjp.dao.turnolaboral.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.turnolaboral.TurnoLaboralDAO;
import mx.gob.segob.nsjp.model.TurnoLaboral;

@Repository
public class 		TurnoLaboralDAOImpl 
	   extends 		GenericDaoHibernateImpl<TurnoLaboral,Long>
	   implements 	TurnoLaboralDAO
{

	@Override	
	@SuppressWarnings("unchecked")
	public List<TurnoLaboral> ConsultarCatalogoTurnoLaboral() {
		final StringBuffer qString = new StringBuffer();
		qString.append("from TurnoLaboral t");        
        logger.debug("query :: " + qString);
        Query query = super.getSession().createQuery(qString.toString());        
		List<TurnoLaboral> result = query.list();
		return result;
	}

}

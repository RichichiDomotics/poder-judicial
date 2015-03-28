/**
 * 
 */
package mx.gob.segob.nsjp.dao.chat.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.chat.OfPropertyDAO;
import mx.gob.segob.nsjp.model.OfProperty;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author IgnacioFO
 *
 */
@Repository("ofPropertyDAO")
public class OfPropertyDAOImpl extends GenericDaoHibernateImpl<OfProperty, String> implements OfPropertyDAO{
	
	
	 @Override
	    public OfProperty findById(String name) {
	        final StringBuffer query = new StringBuffer();
	        logger.debug("query :: Entra al inicio" );
	        query.append("from OfProperty ofp");
	        query.append(" where ofp.name =");
	        query.append("'"+name+"'");
	        logger.debug("query :: " + query);
	        Query hbq = super.getSession().createQuery(query.toString());
//	        hbq.setString("name", name);
	        return (OfProperty) hbq.uniqueResult();
	    }

}

/**
 * 
 */
package mx.gob.segob.nsjp.dao.chat.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.chat.OfUserDAO;
import mx.gob.segob.nsjp.model.OfUser;

import org.springframework.stereotype.Repository;

/**
 * @author IgnacioFO
 *
 */
@Repository("ofUserDAO")
public class OfUserDAOImpl extends GenericDaoHibernateImpl<OfUser, String> implements OfUserDAO{
	

}

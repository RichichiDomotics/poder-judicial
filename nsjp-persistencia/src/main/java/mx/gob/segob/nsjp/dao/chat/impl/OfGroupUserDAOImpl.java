/**
 * 
 */
package mx.gob.segob.nsjp.dao.chat.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.chat.OfGroupUserDAO;
import mx.gob.segob.nsjp.model.OfGroupUser;
import mx.gob.segob.nsjp.model.OfGroupUserId;

import org.springframework.stereotype.Repository;

/**
 * @author IgnacioFO
 *
 */
@Repository("ofGroupUserDAO")
public class OfGroupUserDAOImpl extends GenericDaoHibernateImpl<OfGroupUser, OfGroupUserId> implements OfGroupUserDAO{
	

}

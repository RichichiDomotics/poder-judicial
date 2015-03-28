package mx.gob.segob.nsjp.dao.chat;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.OfProperty;

/**
 *
 * @author jfernandez
 */

public interface OfPropertyDAO extends GenericDao<OfProperty, String> {

	public OfProperty findById(String name);

}

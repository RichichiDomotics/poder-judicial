/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.domicilio.impl;

import mx.gob.segob.nsjp.dao.domicilio.LugarDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;

/**
 * @author vaguirre
 *
 */
public class LugarDAOImplTest extends BaseTestPersistencia<LugarDAO> {
	public void testMapeo(){
		daoServcice.read(1L);
	}
}

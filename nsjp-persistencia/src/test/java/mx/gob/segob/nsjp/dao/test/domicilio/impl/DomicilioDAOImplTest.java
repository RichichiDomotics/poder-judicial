/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.domicilio.impl;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Domicilio;

/**
 * @author cesarAgustin
 *
 */
public class DomicilioDAOImplTest extends BaseTestPersistencia<DomicilioDAO> {

	public void testObtenerDomicilioByRelacion () {
		
		Domicilio domicilio = daoServcice.obtenerDomicilioByRelacion(1938L, new Long(Relaciones.UBICACION.ordinal()));
		
		logger.info("Domicilio " + domicilio.getElementoId());
		assertTrue("", domicilio.getElementoId()>0);
	}
}

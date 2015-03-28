/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.audiencia.impl;

import mx.gob.segob.nsjp.dao.audiencia.SalaJAVSDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SalaJAVS;

/**
 * @author GustavoBP
 *
 */
public class SalaJAVSDAOImplTest
	extends
		BaseTestPersistencia<SalaJAVSDAO> {

	public void testIngresarSalaJAVS(){
		Long salaAudienciaId = 1L;
		
		SalaJAVS salaJAVS = new SalaJAVS();
		salaJAVS.setDireccionIP("http://");
		salaJAVS.setPassword("Pass");
		salaJAVS.setUsuario("Usuario");
		salaJAVS.setSalaAudiencia(new SalaAudiencia(salaAudienciaId ));
		Long id= daoServcice.create(salaJAVS);
		logger.info("id:"+id);
	}
	
	public void testConsultarSalaJAVAS(){
		
		Long salaJId = 1L; 
		SalaJAVS salaJavs = daoServcice.read(salaJId);
		logger.info(" SalaJAVS:"+ salaJavs.getSalaJAVSId());
		logger.info(" SalaJAVS:"+ salaJavs.getDireccionIP());
		logger.info(" SalaJAVS:"+ salaJavs.getPassword());
		logger.info(" SalaJAVS:"+ salaJavs.getUsuario());
//		logger.info(" SalaJAVS:"+ salaJavs.getSalaAudiencia());
	}
}


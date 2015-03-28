/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.alerta.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * @author adrian
 *
 */
public class AlertaDAOImplTest extends BaseTestPersistencia<AlertaDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.almacen.impl.EncargadoAlmacenDAOImpl#consultarEncargadosDAlmacen(java.lang.Long)}.
	 */
	public void testConsultarAlarmas() {
		
		Usuario loUsuario = new Usuario();
		loUsuario.setUsuarioId(3L);
		List<Alerta> alarmas = daoServcice.consultarAlertasXUsuario(loUsuario, EstatusAlarmaAlerta.NO_EJECUTADA);
		assertNotNull(alarmas);
		logger.info("Existen "+ alarmas.size()+" alarma(s)");
		for (Alerta alarma : alarmas) {
			logger.info("Alarmas: "+alarma);
		}
	}

}

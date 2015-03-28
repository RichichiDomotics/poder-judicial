/**
 * 
 */
package mx.gob.segob.nsjp.dao.alerta;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * @author adrian
 *
 */
public interface AlertaDAO extends GenericDao<Alerta, Long> {
	
	/**
	 * Metodo que permite consultar Alertas con estatus NO_EJECUTADAS y con fecha menor a la fecha actual 
	 * @param usuario.usuarioId: Permite consultar las Alertas de un usuario en particular
	 * @param estatusAlerta: Representa el estatus de la alerta
	 * @return List<Alerta>
	 */
	public List<Alerta> consultarAlertasXUsuario(Usuario usuario, EstatusAlarmaAlerta estatusAlerta);

}

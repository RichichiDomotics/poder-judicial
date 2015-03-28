/**
 * 
 */
package mx.gob.segob.nsjp.service.alerta;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarAlertasService {

	/**
	 * Operación que permite consultar las alertas para un usuario y opcionalmente se puede filtrar por estatus
	 * @param usuario.usuarioId: Permite consultar las Alertas de un usuario en particular
	 * @param estatusAlerta: Representa el estatus del la Alerta
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AlertaDTO> consultarAlertasXUsuario(UsuarioDTO usuario,
			EstatusAlarmaAlerta estatusAlerta)throws NSJPNegocioException;

}

/**
 * 
 */
package mx.gob.segob.nsjp.delegate.alarma;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * @author adrian
 *
 */
public interface AlarmaDelegate {
	/**
	 * Operación que permite registrar una nueva alarma con sus alertas
	 * @param alarmaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarAlarma(AlarmaDTO alarmaDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar las alarmas para un funcionario y opcionalmente se puede filtrar por estatus
	 * @param claveFuncionario
	 * @param estatusAlarma
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AlarmaDTO> consultarAlarmasXFuncionario(Long claveFuncionario, Long estatusAlarma)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar una alarma por su identificador
	 * @param idAlarma
	 * @return
	 * @throws NSJPNegocioException
	 */
	AlarmaDTO consultarAlarma(Long idAlarma)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar las alertas para un usuario y opcionalmente se puede filtrar por estatus
	 * @param usuario.usuarioId: Permite consultar las Alertas de un usuario en particular
	 * @param estatusAlerta: Representa el estatus del la Alerta
	 * @return
	 * @throws NSJPNegocioException
	 */	
	List<AlertaDTO> consultarAlertasXUsuario(UsuarioDTO usuario,
			EstatusAlarmaAlerta estatusAlerta)throws NSJPNegocioException;
	
	 /**
     * Actualiza una solicitud al estatus recibido.
     * 
     * @param alerta.alertaId: Representa el id de la evidencia a actualizar
     * @param estatusAlerta: Representa el nuevo estatus
     * @throws NSJPNegocioException
     */

	 public void actualizaEstatusyFechaAlerta(AlertaDTO alerta,
	    		EstatusAlarmaAlerta estatusAlerta) throws NSJPNegocioException;   

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.alarma;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarAlarmaService { 

	/**
	 * Operación que permite consultar las alarmas para un funcionario y opcionalmente se puede filtrar por estatus
	 * @param claveFuncionario
	 * @param estatusAlarma
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AlarmaDTO> consultarAlarmasXFuncionario(Long claveFuncionario,
			Long estatusAlarma)throws NSJPNegocioException;

	/**
	 * Operación que permite consultar una alarma por su identificador
	 * @param idAlarma
	 * @return
	 * @throws NSJPNegocioException
	 */
	AlarmaDTO consultarAlarma(Long idAlarma)throws NSJPNegocioException;

}

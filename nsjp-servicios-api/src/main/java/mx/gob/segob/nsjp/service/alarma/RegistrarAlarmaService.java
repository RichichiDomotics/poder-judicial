/**
 * 
 */
package mx.gob.segob.nsjp.service.alarma;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;

/**
 * @author adrian
 *
 */
public interface RegistrarAlarmaService {

	/**
	 * Operación que permite registrar una nueva alarma con sus alertas
	 * @param alarmaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarAlarma(AlarmaDTO alarmaDTO)throws NSJPNegocioException;

}

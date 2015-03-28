/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;

/**
 * @author adrian
 *
 */
public interface RegistrarQuejaCiudadanaService {

	/**
	 * Operación que permite registrar inicialmente una queja ciudadana
	 * @param quejaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarQuejaCiudadana(QuejaCiudadanaDTO quejaDTO)throws NSJPNegocioException;

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarQuejaCiudadanaService {

	/**
	 * Operación que permite consultar todas las quejas existentes
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<QuejaCiudadanaDTO> consultarQuejasCiudadanas()throws NSJPNegocioException;

	/**
	 * Operación que permite consultar el detalle de una queja dada
	 * @param idQueja
	 * @return
	 * @throws NSJPNegocioException
	 */
	QuejaCiudadanaDTO consultarQuejaCiudadanaXId(Long idQueja)throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de consultar las quejas ciudadanas asociadas al estatus recibido.
	 * @param estatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<QuejaCiudadanaDTO> consultarQuejasPorEstatus(Long estatus)throws NSJPNegocioException;

}

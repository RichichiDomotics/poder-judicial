/**
 * 
 */
package mx.gob.segob.nsjp.delegate.quejaciudadana;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;

/**
 * @author adrian
 *
 */
public interface QuejaCiudadanaDelegate {
	
	/**
	 * Operación que permite registrar inicialmente una queja ciudadana
	 * @param quejaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long registrarQuejaCiudadana(QuejaCiudadanaDTO quejaDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar todas las quejas existentes
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<QuejaCiudadanaDTO> consultarQuejasCiudadanas()throws NSJPNegocioException;

	/**
	 * Operación que permite consultar el detalle de una queja dada
	 * @param idQueja
	 * @return
	 * @throws NSJPNegocioException
	 */
	public QuejaCiudadanaDTO consultarQuejaCiudadanaXId(Long idQueja)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar las quejas ciudadanas asociadas al estatus recibido.
	 * @param estatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<QuejaCiudadanaDTO> consultarQuejasPorEstatus(Long estatus)throws NSJPNegocioException;
	
	/**
	 * Operación que permite asignar un motivo de rechazo y actualizar el estatus de una queja registrada
	 * @param idQueja
	 * @param motivoRechazo
	 * @throws NSJPNegocioException
	 */
	public void asignarMotivoRechazo(Long idQueja, MotivoRechazo motivoRechazo)throws NSJPNegocioException;
}

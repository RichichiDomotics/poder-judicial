/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * @author adrian
 *
 */
public interface AsignarMotivoRechazoService {

	/**
	 * Operación que permite asignar un motivo de rechazo y actualizar el estatus de una queja registrada
	 * @param idQueja
	 * @param motivoRechazo
	 * @throws NSJPNegocioException
	 */
	void asignarMotivoRechazo(Long idQueja, MotivoRechazo motivoRechazo)throws NSJPNegocioException;

}

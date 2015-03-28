/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;

/**
 * @author adrian
 *
 */
public interface ActualizarNotificacionService {

	/**
	 * Operación que realiza la funcionalidad de actualizar el estado de una notificación.
	 * @param notificacionDTO: idNotificación
	 * @param estatusNotificacion: idEstatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	NotificacionDTO actualizarEstatusNotificacion(
			NotificacionDTO notificacionDTO, Long estatusNotificacion)throws NSJPNegocioException;

}

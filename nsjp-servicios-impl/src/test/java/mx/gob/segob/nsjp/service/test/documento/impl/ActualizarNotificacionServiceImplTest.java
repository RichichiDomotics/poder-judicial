/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.service.documento.ActualizarNotificacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ActualizarNotificacionServiceImplTest extends BaseTestServicios<ActualizarNotificacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.ActualizarNotificacionServiceImpl#actualizarEstatusNotificacion(mx.gob.segob.nsjp.dto.documento.NotificacionDTO, java.lang.Long)}.
	 */
	public void testActualizarEstatusNotificacion() {
		Long estatusNotificacion=EstatusNotificacion.ATENDIDA.getValorId();
		NotificacionDTO notificacionDTO=new NotificacionDTO();
		notificacionDTO.setDocumentoId(3L);
		try {
			NotificacionDTO actualizado = service.actualizarEstatusNotificacion(notificacionDTO, estatusNotificacion);
			assertNotNull("Se actualizó", actualizado);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

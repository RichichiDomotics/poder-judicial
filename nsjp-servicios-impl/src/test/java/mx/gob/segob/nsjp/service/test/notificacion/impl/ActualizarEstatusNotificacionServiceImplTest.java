/**
 * 
 */
package mx.gob.segob.nsjp.service.test.notificacion.impl;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.service.notificacion.ActualizarEstatusNotificacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ActualizarEstatusNotificacionServiceImplTest extends
		BaseTestServicios<ActualizarEstatusNotificacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.notificacion.impl.ActualizarEstatusNotificacionServiceImpl#actualizarEstatusNotificacion(mx.gob.segob.nsjp.dto.documento.NotificacionDTO, mx.gob.segob.nsjp.dto.catalogo.ValorDTO)}.
	 */
	public void testActualizarEstatusNotificacion() {
		NotificacionDTO notDTO=new NotificacionDTO();
		notDTO.setDocumentoId(22L);
		ValorDTO estado=new ValorDTO(EstatusNotificacion.ATENDIDA.getValorId());
		try {
			service.actualizarEstatusNotificacion(notDTO, estado);
			assertTrue(true);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

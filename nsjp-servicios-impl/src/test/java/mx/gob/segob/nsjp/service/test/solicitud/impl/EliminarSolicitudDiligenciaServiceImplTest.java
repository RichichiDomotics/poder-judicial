/**
* Nombre del Programa : EliminarSolicitudDiligenciaServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria deñ servicio para eliminar una solicitud de diligencia
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.service.solicitud.EliminarSolicitudDiligenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria deñ servicio para eliminar una solicitud de diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EliminarSolicitudDiligenciaServiceImplTest extends
		BaseTestServicios<EliminarSolicitudDiligenciaService> {

	
	public void testActualizarSolicitudDiligencia () {
		SolicitudDiligenciaDTO solicitudDiligenciaDTO = new SolicitudDiligenciaDTO(1L);
		
		try {
			service.eliminarSolicitudDiligencia(solicitudDiligenciaDTO);
			logger.info("PRUEBA UNITARIA EXITOSA");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
			fail();
		}
	}
}

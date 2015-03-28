/**
* Nombre del Programa : AsociarSolicitudANumeroExpedienteServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servico de asociar solicitud a numero expediente
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.service.solicitud.AsociarSolicitudANumeroExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servico de asociar solicitud a numero expediente.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AsociarSolicitudANumeroExpedienteServiceImplTest extends
		BaseTestServicios<AsociarSolicitudANumeroExpedienteService> {
	
	public void testAsociarSolicitudANumeroExpediente () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		
		expedienteDTO.setNumeroExpedienteId(22L);
		solicitudDTO.setDocumentoId(8L);
		
		try {
			service.asociarSolicitudANumeroExpediente(expedienteDTO, solicitudDTO);			
		} catch (NSJPNegocioException e) {
			fail(e.getMessage());
			logger.error(e.getMessage());
		}
	}

}

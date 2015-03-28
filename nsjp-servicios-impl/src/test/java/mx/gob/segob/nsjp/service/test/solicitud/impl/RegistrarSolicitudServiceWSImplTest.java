/**
* Nombre del Programa : RegistrarSolicitudServiceImplTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 02 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de registro de una solicitud de transcripcion
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

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudDeTranscripcionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de registro de una solicitud de transcripcion
 * @version 1.0
 * @author rgama
 *
 */
public class RegistrarSolicitudServiceWSImplTest extends
		BaseTestServicios<EnviarSolicitudDeTranscripcionService> {
  	
	public void testRegistrarSolicitudTranscripcion() {
		SolicitudTranscripcionAudienciaDTO solicitudDTO = new SolicitudTranscripcionAudienciaDTO();
		
		
		solicitudDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.AUDIENCIA.getValorId()));
		solicitudDTO.setFechaCreacion(new Date());
		solicitudDTO.setNombreSolicitante("Ricardo Gama Garcia");
		solicitudDTO.setAudiencia(new AudienciaDTO(2L));
		solicitudDTO.setAreaOrigen(Long.valueOf(Instituciones.PJ.ordinal()));
		solicitudDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
	
		
		try {
			SolicitudDTO respuesta = service.enviarSolicitudDeTranscripcion(solicitudDTO);
			assertTrue("El identificador de la solicitud debe ser mayor a cero : ", respuesta.getDocumentoId()>0);
			logger.info("El identificador de la solicitud debe ser mayor a cero : " + respuesta.getDocumentoId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}

}

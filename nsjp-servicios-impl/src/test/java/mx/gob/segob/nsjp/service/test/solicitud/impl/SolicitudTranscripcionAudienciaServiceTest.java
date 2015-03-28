package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.solicitud.SolicitudTranscripcionAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class SolicitudTranscripcionAudienciaServiceTest extends BaseTestServicios<SolicitudTranscripcionAudienciaService> {

	public void testConsultarAudienciaBySolicitudTranscricpion() throws NSJPNegocioException{
		SolicitudDTO solicitud = new SolicitudDTO();
		solicitud.setDocumentoId(233L);
		AudienciaDTO audiencia = service.consultarAudienciaDeSolicitudTranscripcion(solicitud);
		
	}
	/**
	 * Prueba unitaria de la funcionalidad para la cosulta de solicitudes de transcripcion por
	 * tipo y estado
	 * @author Emigdio 
	 */
	public void testConsultarSolicitudPorEstadoyTipo(){
		
		UsuarioDTO usuario = new UsuarioDTO();
		List<SolicitudTranscripcionAudienciaDTO> sols =  service.
		consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(
				TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId(), 
				EstatusSolicitud.EN_PROCESO.getValorId(),usuario, new Date(), null);
		logger.debug("Solicitudes:" + sols.size());
		for (SolicitudTranscripcionAudienciaDTO solicitudTranscripcionAudienciaDTO : sols) {
			logger.info("DocumentoId: " + solicitudTranscripcionAudienciaDTO.getDocumentoId());
			logger.info("Estatus: " + solicitudTranscripcionAudienciaDTO.getEstatus());
			logger.info("TipoSolicitudDTO: " + solicitudTranscripcionAudienciaDTO.getTipoSolicitudDTO());
			
		}
		
	}
	/**
	 * Prueba unitaria de actualización de estado de una solicitud de transcripción de audiencia
	 * @author Emigdio Hernández
	 */
	public void testEnviarActualizacionEstadoSolicitud(){
		
		try {
			service.enviarActualizacionSolicitudTranscripcionAudiencia(224L);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
		
	}
}

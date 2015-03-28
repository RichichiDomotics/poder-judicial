/**
 * Nombre del Programa : ConsultarSolicitudXEstatusServiceImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudXEstatusService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class ConsultarSolicitudXEstatusServiceImplTest extends
		BaseTestServicios<ConsultarSolicitudXEstatusService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.solicitud.impl.ConsultarSolicitudXEstatusServiceImpl#consultarSolicitudXEstatus(int, mx.gob.segob.nsjp.dto.usuario.UsuarioDTO, long)}
	 * .
	 */
	public void testConsultarSolicitudXEstatus() {
		/* Datos de entrada */
		UsuarioDTO usuario = null;
		EstatusSolicitud estatusSolicitud = EstatusSolicitud.ABIERTA;
		TiposSolicitudes tipoSolicitud = TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA;

		List<SolicitudTranscripcionAudienciaDTO> solicitudes;
		try {
			solicitudes = service.consultarSolicitudTranscripcion(usuario);

			logger.info("Existen " + solicitudes.size() + " formas");
			for (SolicitudTranscripcionAudienciaDTO sol : solicitudes) {
				logger.info("-----------------------------------------------------------");
				logger.info("FolioSolicitud: " + sol.getFolioSolicitud());
				logger.info("Tipo Solicitud: " + sol.getTipoSolicitudDTO());
				logger.info("Estatus: " + sol.getEstatus());
				
				logger.info("Identificador de documento: " + sol.getDocumentoId());
				logger.info("Nombre del solicitante: " + sol.getNombreSolicitante());
				logger.info("Número de caso: " + sol.getNumeroCasoAsociado());
				logger.info("Fecha Inicio Prestamo: " + sol.getFechaModificacion());
				logger.info("Fecha Fin Prestamo: " + sol.getFechaCierre());
				logger.info("Fecha Límite: " + sol.getFechaLimite());
//				logger.info("Acuse de recibo: " + sol.getAcusesReciboDTO().size());
				
				AudienciaDTO audiencia = sol.getAudiencia();
				
				logger.info("Expedient id: " + audiencia.getExpediente().getExpedienteId());
				if(audiencia.getExpediente().getCausaPadre() != null){
					logger.info("Causa Numero: " + audiencia.getExpediente().getCausaPadre().getNumeroExpediente());
					logger.info("Toca  Numero: " + audiencia.getExpediente().getNumeroExpediente());
				}else{
					logger.info("Causa Numero: " + audiencia.getExpediente().getNumeroExpediente());
				}
				
			}
		} catch (NSJPNegocioException e) {
			assertTrue("Fallo: consultarSolicitudXEstatus", false);
			e.printStackTrace();
		}

	}
	
	public void testConsultarSolicitudXEstatusGen() {
		/* Datos de entrada */
		
		UsuarioDTO usuario = new UsuarioDTO();
		
		usuario.setIdUsuario(7L);
		usuario.setCoordinador(true);		
		EstatusSolicitud estatusSolicitud = EstatusSolicitud.ABIERTA;
		TiposSolicitudes tipoSolicitud = TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA;

		try {
			List<SolicitudDTO> solicitudes = service.consultarSolicitudXEstatus(estatusSolicitud, usuario, tipoSolicitud);

			assertTrue("La lista debe retornar minimo un registro", solicitudes.size()>0);
			logger.info("Existen " + solicitudes.size() + " solicitudes");
			for (SolicitudDTO sol : solicitudes) {
				logger.info("-----------------------------------------------------------");
				logger.info("FolioSolicitud: " + sol.getFolioSolicitud());
				logger.info("Tipo Solicitud: " + sol.getTipoSolicitudDTO());
				logger.info("Estatus: " + sol.getEstatus());
				
				logger.info("Documento ID: " + sol.getDocumentoId());
				logger.info("Solicitante : " + sol.getNombreSolicitante());							
				logger.info("Fecha Solic : " + sol.getStrFechaCreacion());
				logger.info("TIPO: "+sol.getTipoSolicitudDTO().getValor());
				if(sol.getExpedienteDTO()!=null){
					logger.info("Expediente: "+sol.getExpedienteDTO().getExpedienteId());
					logger.info("NExpediente: "+sol.getExpedienteDTO().getNumeroExpedienteId()+" ("+sol.getExpedienteDTO().getNumeroExpediente()+")");
					if(sol.getExpedienteDTO().getCasoDTO()!=null)
				logger.info("NUMERO CASO: "+sol.getExpedienteDTO().getCasoDTO().getCasoId());
				logger.info("NOMBRE VICTIMA: "+sol.getExpedienteDTO().getInvolucradosDTO().size());
				logger.info("DELITOS: "+sol.getExpedienteDTO().getDelitos().size());
				}
				
			}			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}


}

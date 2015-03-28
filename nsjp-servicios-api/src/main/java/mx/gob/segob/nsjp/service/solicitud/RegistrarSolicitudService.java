/**
 * Nombre del Programa : RegistrarSolicitudService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para el registro de una solicitud de una manera generica para que
 * pueda reusar
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Interface para el registro de una solicitud de una manera generica para que
 * pueda reusar.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface RegistrarSolicitudService {
    /**
     * 
     * @param input
     * @return
     * @throws NSJPNegocioException
     */
    SolicitudDTO registrarSolicitud(SolicitudDTO input)
            throws NSJPNegocioException;

	/**
	 * Registara una nueva Orden de Detencion y se la envia al Comandante de Policia Ministerial
	 * @param ordenDetencion 
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudDTO registrarSolicitudOrdenDeDetencion(SolicitudDTO ordenDetencion, Long idDocumentoAnexo) throws NSJPNegocioException;

	SolicitudAudienciaDTO registrarSolicitudAudiencia(SolicitudAudienciaDTO solicitud) throws NSJPNegocioException;

	SolicitudTranscripcionAudienciaDTO registrarSolicitudTranscripcionAudiencia(
			SolicitudTranscripcionAudienciaDTO solicitud) throws NSJPNegocioException;


	/**
	 * Registra una solicitud de Carpeta de Invesigación y la envia a Procuraduría.
	 * @param idNumeroExpediente
	 * @param usuario
	 * @return
	 */
	public SolicitudDTO registrarSolicitudCarpetaInvestigacion(Long idExpediente, UsuarioDTO usuario) throws NSJPNegocioException;

}

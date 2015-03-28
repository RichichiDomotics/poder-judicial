/**
* Nombre del Programa : ConsultarSolicitudXEstatus.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/06/2011
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
package mx.gob.segob.nsjp.service.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudMandamientoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarSolicitudXEstatusService {

	/**
	 * Operación que realiza la funcionalidad de consultar las solicitudes asociadas al estatus recibido.
	 * 
	 * @param estatusSolicitud
	 * @param usuario
	 * @param tipoSolicitud
	 * @return Devuelve un listado de solicitudes que cumplen con los criterios, en caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDTO> consultarSolicitudXEstatus(EstatusSolicitud estatusSolicitud, 
			UsuarioDTO usuario, TiposSolicitudes tipoSolicitud) throws NSJPNegocioException;

	List<SolicitudTranscripcionAudienciaDTO> consultarSolicitudTranscripcion(
			UsuarioDTO usuario) throws NSJPNegocioException;

	List<SolicitudMandamientoDTO> consultarSolicitudMandatoJudicial() throws NSJPNegocioException;

}

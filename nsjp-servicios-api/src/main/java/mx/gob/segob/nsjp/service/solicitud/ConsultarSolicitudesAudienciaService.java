/**
 * Nombre del Programa : ConsultarSolicitudesAudienciaService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio de consulta de solicitudes de audiencia
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
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio de consulta de solicitudes de audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ConsultarSolicitudesAudienciaService {
    /**
     * Consulta las solicitudes que en la audiencia no tiene valor la fecha de
     * asignación de sala.
     * 
     * @param usr
     * @return
     */
    List<SolicitudAudienciaDTO> consultarSolicitudesAudiencia(UsuarioDTO usr);

    /**
     * Consulta las demas solicitudes menos audiencia.
     * 
     * @param usr
     * @return
     */
    List<SolicitudDTO> consultarOtrasSolicitudes(UsuarioDTO usr);
    
    /**
     * Consulta un listado de solicitudes de audiencia de cierto tipo y cierto estado
     * @param tipo Tipo a buscar
     * @param estado Estado a buscar
     * @return Solicitudes encontradas
     */
    List<SolicitudAudienciaDTO> consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes tipo,EstatusSolicitud estado);

    /**
     * Consulta un listado de solicitudes de audiencia con criterios 
     * @param solicitudAudienciaDTO
     * @param lstIdEstatusSolicitud
     * @param lstIdTipoSolicitud
     * @param tipoConsulta
     * @return
     * @throws NSJPNegocioException
     */
    
    List<SolicitudAudienciaDTO> consultarSolicitudesAudienciaConCriterios (
			SolicitudAudienciaDTO solicitudAudienciaDTO,
			List<Long> lstIdEstatusSolicitud, List<Long> lstIdTipoSolicitud,
			List<Long> lstIdEstatusAudiencia, List<Long> lstIdTipoAudiencia,
			String tipoConsulta
			) throws NSJPNegocioException;
    
}

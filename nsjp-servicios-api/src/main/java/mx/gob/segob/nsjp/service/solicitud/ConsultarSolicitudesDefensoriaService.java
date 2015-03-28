/**
* Nombre del Programa : ConsultarSolicitudesDefensoriaService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de consultaa de solicitudes de defensoria
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

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Contrato del servicio de consultaa de solicitudes de defensoria
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface ConsultarSolicitudesDefensoriaService {
	
	/**
	 * Consulta de solicitudes de defensoria no atendidas
	 * @return List<SolicitudDefensorDTO>
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDefensorDTO> obtenerSolicitudesDefensoriaPorEstatus(EstatusSolicitud estatusSolc,Instituciones institucion)
			throws NSJPNegocioException;
	
	/**
     * Realiza la consulta de las solicitudes de acuerdo a la variable historico y un estatus determinado.
	 * @author cesarAgustin
	 * @param estatusSolicitud
	 * @return Lista de solicitudes obtenidas
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDefensorDTO> consultarSolicitudesDefensoriaByHistoricoYEstatus(
			EstatusSolicitud estatusSolicitud) throws NSJPNegocioException;


	/**
	 * Consulta de solicitudes de asesoria de defensor no atendidas
	 * @return List<SolicitudDefensorDTO>
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDefensorDTO> obtenerSolicitudesAsesoriaDefensoriaPorEstatus(
			EstatusSolicitud estatusSolc, Instituciones institucion)
			throws NSJPNegocioException;

	/**
	 * 
	 * @param sol
	 * @return
	 * @throws NSJPNegocioException 
	 */
	public SolicitudDefensorDTO obtenerSolicitudDefensor(SolicitudDefensorDTO sol) throws NSJPNegocioException;

}

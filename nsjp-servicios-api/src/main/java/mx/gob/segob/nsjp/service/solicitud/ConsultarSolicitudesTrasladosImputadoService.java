/**
* Nombre del Programa : ConsultarSolicitudesTrasladosImputadoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas a solicitud traslado imputado
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
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;

/**
 * Contrato del servicio para realizar las consultas a solicitud traslado imputado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarSolicitudesTrasladosImputadoService {

	/**
	 * Consulta las solicitudes de traslados de imputados por estatus.
	 * @param estatusSolicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<SolicitudTrasladoImputadoDTO> consultarSolicitudTrasladoPorEstatus(EstatusSolicitud estatusSolicitud) throws NSJPNegocioException;
	
	/**
	 * Obtiene el detalle de una solicitud traslado de imputado
	 * @param solicitudTrasladoImputadoDTO
	 * 			<li>documentoId<li> Identificador de la solicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudTrasladoImputadoDTO obtenerSolicitudTrasladoImputadoPorId(SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) throws NSJPNegocioException;
}

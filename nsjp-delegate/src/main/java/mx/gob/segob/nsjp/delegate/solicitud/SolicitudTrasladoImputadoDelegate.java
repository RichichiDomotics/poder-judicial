/**
* Nombre del Programa : SolicitudTrasladoImputadoDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del delegate para los servicio de la Solicitud de traslado del imputado
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
package mx.gob.segob.nsjp.delegate.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;

/**
 * Contrato del delegate para los servicio de la Solicitud de traslado del imputado.
 * @author cesarAgustin
 *
 */
public interface SolicitudTrasladoImputadoDelegate {

	/**
	 * Registra la informacion requerida para una solicitud de traslado de un imputado
	 * @param solicitudTrasladoImputadoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudTrasladoImputadoDTO registrarSolicitudTrasladoImputado (SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) 
					throws NSJPNegocioException;
	/**
	 * Envia la solicitud de traslado de imputado a SSP.
	 * @author cesarAgustin
	 * @param solicitudTrasladoImputadoDTO
	 * 			<li>documentoId<li> Identificador de la solicitud a enviar
	 * @throws NSJPNegocioException
	 */
	public void enviarSolicitudTrasladoImputadoASSP(SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) throws NSJPNegocioException;
	
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

/**
* Nombre del Programa : AsociarSolicitudANumeroExpedienteService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para asociar una solicitud a un NumeroExpediente.
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
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

/**
 * Contrato del servicio para asociar una solicitud a un NumeroExpediente.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AsociarSolicitudANumeroExpedienteService {

	/**
	 * Realiza la asociacion de una solicitud a un NumeroExpediente de un tipo determinado.
	 * @param expedienteDTO
	 * @param soiSolicitudDTO
	 * @throws NSJPNegocioException
	 */
	void asociarSolicitudANumeroExpediente(ExpedienteDTO expedienteDTO, SolicitudDTO solicitudDTO) throws NSJPNegocioException;
}

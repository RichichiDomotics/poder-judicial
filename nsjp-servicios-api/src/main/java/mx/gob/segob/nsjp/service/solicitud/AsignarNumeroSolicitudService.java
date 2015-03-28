/**
* Nombre del Programa : AsignarNumeroSolicitudService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio de generacion de Solicitudes de Defensor.
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Contrato para el servicio de generacion de Solicitudes de Defensor. 
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface AsignarNumeroSolicitudService {
	
	/**
	 * Servicio que genera una nueva solicitud de defensoria
	 * 
	 * @param solicitudDefensorDTO
	 * @return SolicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	SolicitudDefensorDTO asignarNumeroSolicitud(Long  idSolicitud) throws NSJPNegocioException;

}

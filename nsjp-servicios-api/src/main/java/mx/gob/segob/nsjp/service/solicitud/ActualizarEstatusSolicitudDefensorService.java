/**
* Nombre del Programa : ActualizarEstatusSolicitudDefensorService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la actualizacion de estus de las solicitudes de defensoria
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

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Contrato del servicio para realizar la actualizacion de estus de las solicitudes de defensoria.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ActualizarEstatusSolicitudDefensorService {

	/** Actuliza la solicitud defensoria, al estatus que se envia como parametro
	* @author cesarAgustin 
	* @param solDefensorDTO
	* 			-Identificador de la solicitud defensoria a actualizar <li>documentoId<li>
	* @param estatusSolicitud
	* 			-Estatus al que se actualizara la solicitud defensoria
	* @throws NSJPNegocioException
	*/
	public void actualizarEstatusSolicitudDefensor (SolicitudDefensorDTO solDefensorIn, 
						EstatusSolicitud estatusSolicitud) throws NSJPNegocioException;
	
}

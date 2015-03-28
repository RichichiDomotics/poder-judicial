/**
* Nombre del Programa : ActualizarSolicitudDiligenciaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la actualizacion de informacion de una solicitud de diligencia
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;

/**
 * Contrato del servicio para realizar la actualizacion de informacion de una solicitud de diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ActualizarSolicitudDiligenciaService {

	/**
	 * Actualiza la informacion de solicitud diligencia.
	 * @author cesarAgustin
	 * @param solicitudDiligenciaDTO
	 * @throws NSJPNegocioException
	 */
	public void actualizarSolicitudDiligencia (SolicitudDiligenciaDTO solicitudDiligenciaDTO) throws NSJPNegocioException; 
}

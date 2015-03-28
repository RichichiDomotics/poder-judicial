/**
* Nombre del Programa : RegistrarSolicitudDiligenciaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas de las solicitudes diligencia
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
 * Contrato del servicio para realizar las consultas de las solicitudes diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface RegistrarSolicitudDiligenciaService {

	/**
	 * Realiza el registro de la informacio para una solicitud de diligencia
	 * @author cesarAgustin
	 * @param solicitudDiligenciaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public SolicitudDiligenciaDTO registrarSolicitudDiligencia(SolicitudDiligenciaDTO solicitudDiligenciaDTO)
						throws NSJPNegocioException;
}

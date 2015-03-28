/**
* Nombre del Programa : EnviarSolicitudTrasladoImputadoSSPService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para enviar a SSP la solicitude de trasalado de imputado
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;

/**
 * Contrato del servicio para enviar a SSP la solicitude de trasalado de imputado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface EnviarSolicitudTrasladoImputadoSSPService {

	/**
	 * Envia la solicitud de traslado de imputado a SSP.
	 * @author cesarAgustin
	 * @param solicitudTrasladoImputadoDTO
	 * 			<li>documentoId<li> Identificador de la solicitud a enviar
	 * @throws NSJPNegocioException
	 */
	public void enviarSolicitudTrasladoImputadoASSP(SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) throws NSJPNegocioException;
}

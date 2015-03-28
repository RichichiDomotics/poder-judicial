/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para registrar una nueva solicitud de tranferencia del imputado
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
 * Contrato del servicio para registrar una nueva solicitud de tranferencia del imputado.
 * @author cesarAgustin
 *
 */
public interface RegistrarSolicitudTransferenciaImputadoService {

	/**
	 * Registra la informacion requerida para una solicitud de traslado de un imputado
	 * @param solicitudTrasladoImputadoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudTrasladoImputadoDTO registrarSolicitudTrasladoImputado (SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) 
							throws NSJPNegocioException;
}

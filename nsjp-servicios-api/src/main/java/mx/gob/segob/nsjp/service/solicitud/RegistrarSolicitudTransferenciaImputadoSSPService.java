/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoSSPService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Realiza el resgistro de las solicitudes de transferencia de imputados, que son
 * 							enviadas de Poder Judicial
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoWSDTO;

/**
 * Contrato del servicio para el resgistro de las solicitudes de transferencia de imputados, que son
 * enviadas de Poder Judicial.
 * @author cesarAgustin
 *
 */
public interface RegistrarSolicitudTransferenciaImputadoSSPService {

	/**
	 * @author cesarAgustin
	 * @param solicitudTrasladoImputadoWSDTO
	 * @throws NSJPNegocioException
	 */
	public void registrarSolicitudTransferenciaImputadoSSP(SolicitudTrasladoImputadoWSDTO solicitudTrasladoImputadoWSDTO) throws NSJPNegocioException;
	
}

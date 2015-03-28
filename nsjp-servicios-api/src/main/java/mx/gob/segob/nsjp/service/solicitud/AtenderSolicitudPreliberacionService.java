/**
* Nombre del Programa : AtenderSolicitudPreliberacionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio pata atender la solicitud beneficios preliberacion
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
 * Contrato del servicio pata atender la solicitud beneficios preliberacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AtenderSolicitudPreliberacionService {

	/**
	 * Actualiza el estatus de la solicitud beneficios preliberacion a en proceso y obtiene la carpeta de
	 * ejecucion correspondiente
	 * @author cesarAgustin
	 * @param solicitudDTO
	 * 			<li>documentoId<li>
	 * 			<li>expedienteDTO.numeroExpedienteId<li>
	 * @return Carpeta ejecucion
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO atenderSolicitudPreliberacionService (SolicitudDTO solicitudDTO) throws NSJPNegocioException;
	
}

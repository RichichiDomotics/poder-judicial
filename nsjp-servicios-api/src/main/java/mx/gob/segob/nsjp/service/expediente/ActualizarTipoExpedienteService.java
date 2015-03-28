/**
* Nombre del Programa : ActualizarTipoExpedienteService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del Servicio para la actualizacion del tipo de Expediente
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del Servicio para la actualizacion del tipo de Expediente
 * @version 1.0
 * @author rgama
 *
 */
public interface ActualizarTipoExpedienteService {
	
	
	/**
	 * Metodo que permite actualizar el tipo de Expediente
	 * @param expedienteDTO.NumeroExpedienteId: Representa el numero del expediente a actualizar
	 * @param tipo: Representa el tipo de expediente, usar enum TipoExpediente
	 * @throws NSJPNegocioException
	 */
	public void actualizarTipoExpediente(ExpedienteDTO expedienteDTO, OrigenExpediente tipo) throws NSJPNegocioException;
	
	
	

}

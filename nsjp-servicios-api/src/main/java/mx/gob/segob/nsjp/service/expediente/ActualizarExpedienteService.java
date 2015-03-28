/**
* Nombre del Programa : ActualizarExpedienteService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del Servicio para la actualizacion del Expediente
 * Se pretende engoblar aquellos servicios relacionados con la actualizacion del Expediente 
 *  
 * @version 1.0
 * @author GustavoBP
 */
public interface ActualizarExpedienteService {

	/**
	 * Servicio que permite actualizar el estatus del NumeroExpediente.
	 * Se requiere el Id del Expediente y el Valor del Estatus. 
	 * 
	 * @param expedienteDTO
	 * @return 
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO actualizarEstatusExpediente(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite actualizar el discriminante de un expediente
	 * Se requiere el numeroExpedienteId  y el cat DiscriminanteId que se desea actualizar 
	 * @param expedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Boolean actualizarCatDiscriminanteDeExpediente (ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
}

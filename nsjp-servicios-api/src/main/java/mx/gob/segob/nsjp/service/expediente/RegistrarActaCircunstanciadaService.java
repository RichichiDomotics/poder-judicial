/**
* Nombre del Programa : RegistrarActaCircunstanciadaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/07/2011
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
import mx.gob.segob.nsjp.dto.expediente.ActaCircunstanciadaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface RegistrarActaCircunstanciadaService {

	/**
	 * Función que realiza la inserción y registro de los elementos de un acta circunstanciada relacionados a un expediente dado
	 * @param actaDTO
	 * @param expedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO registrarActaCircunstanciada(ActaCircunstanciadaDTO actaDTO,
			ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

}

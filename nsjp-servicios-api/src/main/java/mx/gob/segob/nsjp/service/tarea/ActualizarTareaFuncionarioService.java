/**
* Nombre del Programa : ActualizarTareaFuncionarioService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la actualizacion de las Tareas de los Funcionarios
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
package mx.gob.segob.nsjp.service.tarea;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;

/**
 * Contrato del servicio para realizar la actualizacion de las Tareas de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ActualizarTareaFuncionarioService {

	/**
	 * Actualiza la informacion de las tareas de un funcionario.
	 * @author cesarAgustin
	 * @param tareaDTO
	 * @throws NSJPNegocioException
	 */
	void actualizarTareaFuncionario(TareaDTO tareaDTO) throws NSJPNegocioException;

}

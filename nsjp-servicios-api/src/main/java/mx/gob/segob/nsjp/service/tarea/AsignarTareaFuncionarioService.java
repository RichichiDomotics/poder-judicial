/**
* Nombre del Programa : AsignarTareaFuncionarioService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar la asignacion de tareas a los funcionarios
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
 * Contrato del servicio para realizar la asignacion de tareas a los funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AsignarTareaFuncionarioService {

	/**
	 * Realiza la asignacion de una tarea a un funcionario.
	 * @author cesarAgustin
	 * @param tareaDTO
	 * @param funcionarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	TareaDTO asignarTareaFuncionario(TareaDTO tareaDTO) throws NSJPNegocioException;

}

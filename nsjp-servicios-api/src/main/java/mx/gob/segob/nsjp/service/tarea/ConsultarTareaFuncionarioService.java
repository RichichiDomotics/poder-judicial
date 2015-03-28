/**
* Nombre del Programa : ConsultarTareaFuncionario.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar consultas de las Tareas de los Funcionarios
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;

/**
 * Contrato del servicio para realizar consultas de las Tareas de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarTareaFuncionarioService {

	/**
	 * Obtiene las tareas de un funcionario en un dia especifico
	 * @author cesarAgustin
	 * @param tareaDTO
	 * 			<li>idFuncionario<li> Identificador del funcionario del que se desea obtener las tareas
	 * 			<li>diaTarea<li> Feacha de la cual se requieren las activiades
	 * @return Lista de tareas obtenidas.
	 * @throws NSJPNegocioException
	 */
	List<TareaDTO> consultarTareasFuncionario(TareaDTO tareaDTO) throws NSJPNegocioException;

	/**
	 * Obtiene las tareas en determinado estatus de un funcionario en un dia especifico
	 * @author cesarAgustin
	 * @param tareaDTO
	 * 			<li>idFuncionario<li> Identificador del funcionario del que se desea obtener las tareas
	 * 			<li>diaTarea<li> Feacha de la cual se requieren las activiades
	 * @param estatusEvento Estatus en el que se desea obtener las tareas
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<TareaDTO> consultarTareasFuncionarioByEstatus(TareaDTO tareaDTO,
			EstatusEventoCita estatusEvento) throws NSJPNegocioException;
	
	/**
	 * Obtiene la tarea con determinado id
	 * @author Eduardo Alvarado
	 * @param id
	 * @return TareaDTO
	 * @throws NSJPNegocioException
	 */
	TareaDTO consultarTareaEventoCita(Long id) throws NSJPNegocioException;

}

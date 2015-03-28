/**
* Nombre del Programa : TareaDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contratao de metodos para conectar la vista con los servicio de Tarea
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
package mx.gob.segob.nsjp.delegate.tarea;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;

/**
 * Contratao de metodos para conectar la vista con los servicio de Tarea.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface TareaDelegate {
	
	/**
	 * Realiza la asignacion de una tarea a un funcionario.
	 * @author cesarAgustin
	 * @param tareaDTO
	 * @param funcionarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public TareaDTO asignarTareaFuncionario (TareaDTO tareaDTO, FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
	
	/**
	 * Actualiza la informacion de las tareas de un funcionario.
	 * @author cesarAgustin
	 * @param tareaDTO
	 * @throws NSJPNegocioException
	 */
	public void actualizarTareaFuncionario (TareaDTO tareaDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene las tareas de un funcionario en un dia especifico
	 * @author cesarAgustin
	 * @param tareaDTO
	 * 			<li>idFuncionario<li> Identificador del funcionario del que se desea obtener las tareas
	 * 			<li>diaTarea<li> Feacha de la cual se requieren las activiades
	 * @return Lista de tareas obtenidas.
	 * @throws NSJPNegocioException
	 */
	public List<TareaDTO> consultarTareasFuncionario (TareaDTO tareaDTO) throws NSJPNegocioException;
	
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
	public List<TareaDTO> consultarTareasFuncionarioByEstatus (TareaDTO tareaDTO, EstatusEventoCita estatusEvento) throws NSJPNegocioException;
	
	/**
	 * Obtiene la tarea con determinado id 
	 * @author Eduardo Alvarado
	 * @param id
	 * @return TareaDTO
	 * @throws NSJPNegocioException
	 */
	public TareaDTO consultarTareaEventoCita(Long id) throws NSJPNegocioException;
}

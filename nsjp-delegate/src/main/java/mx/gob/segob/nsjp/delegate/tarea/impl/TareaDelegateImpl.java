/**
* Nombre del Programa : TareaDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos para conectar la vista con los servicio de Tarea
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
package mx.gob.segob.nsjp.delegate.tarea.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.tarea.TareaDelegate;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.service.tarea.ActualizarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.tarea.AsignarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.tarea.ConsultarTareaFuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de metodos para conectar la vista con los servicio de Tarea.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("tareaDelegate")
public class TareaDelegateImpl implements TareaDelegate {

	@Autowired
	private AsignarTareaFuncionarioService asignarTareaFuncionarioService;
	@Autowired
	private ConsultarTareaFuncionarioService consultarTareaFuncionarioService;
	@Autowired
	private ActualizarTareaFuncionarioService actualizarTareaFuncionarioService;
	
	@Override
	public TareaDTO asignarTareaFuncionario(TareaDTO tareaDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {		
		return asignarTareaFuncionarioService.asignarTareaFuncionario(tareaDTO);
	}

	@Override
	public void actualizarTareaFuncionario(TareaDTO tareaDTO)
			throws NSJPNegocioException {
		this.actualizarTareaFuncionarioService.actualizarTareaFuncionario(tareaDTO);
	}

	@Override
	public List<TareaDTO> consultarTareasFuncionario(TareaDTO tareaDTO)
			throws NSJPNegocioException {		
		return this.consultarTareaFuncionarioService.consultarTareasFuncionario(tareaDTO);
	}

	@Override
	public List<TareaDTO> consultarTareasFuncionarioByEstatus(
			TareaDTO tareaDTO, EstatusEventoCita estatusEvento)
			throws NSJPNegocioException {		
		return this.consultarTareaFuncionarioService.consultarTareasFuncionarioByEstatus(tareaDTO, estatusEvento);
	}

	@Override
	public TareaDTO consultarTareaEventoCita(Long id)
			throws NSJPNegocioException {
		return this.consultarTareaFuncionarioService.consultarTareaEventoCita(id);
	}

}

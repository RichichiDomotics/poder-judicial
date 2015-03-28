/**
* Nombre del Programa : ConsultarTareaFuncionarioServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar consultas de las Tareas de los Funcionarios
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
package mx.gob.segob.nsjp.service.tarea.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.model.Tarea;
import mx.gob.segob.nsjp.service.tarea.ConsultarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.tarea.impl.transform.TareaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar consultas de las Tareas de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarTareaFuncionarioServiceImpl implements
		ConsultarTareaFuncionarioService {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ConsultarTareaFuncionarioServiceImpl.class);
	
	@Autowired
	private TareaDAO tareaDAO;
	
	@Override
	public List<TareaDTO> consultarTareasFuncionario(TareaDTO tareaDTO) throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS TAREAS DE UN FUNCIONARIO ****/");
		
		if (tareaDTO.getIdFuncionario()==null
				|| tareaDTO.getDiaTarea()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Tarea> tareasFuncionario = tareaDAO.consultarTareasFuncionario(tareaDTO.getIdFuncionario(), tareaDTO.getDiaTarea());
		
		List<TareaDTO> tareasRetorno = new ArrayList<TareaDTO>();
		for (Tarea tarea : tareasFuncionario) {
			tareasRetorno.add(TareaTransformer.transfromarTarea(tarea));
		}
		
		return tareasRetorno;
	}

	@Override
	public List<TareaDTO> consultarTareasFuncionarioByEstatus(
			TareaDTO tareaDTO, EstatusEventoCita estatusEvento)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS TAREAS POR ESTATUS DE UN FUNCIONARIO ****/");
		
		if (tareaDTO.getIdFuncionario()==null
				|| tareaDTO.getDiaTarea()==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Tarea> tareasFuncionario = tareaDAO.consultarTareasFuncionarioByEstatus(tareaDTO.getIdFuncionario(), tareaDTO.getDiaTarea(), estatusEvento.getValorId());
		
		List<TareaDTO> tareasRetorno = new ArrayList<TareaDTO>();
		for (Tarea tarea : tareasFuncionario) {
			tareasRetorno.add(TareaTransformer.transfromarTarea(tarea));
		}
		
		return tareasRetorno;
	}

	@Override
	public TareaDTO consultarTareaEventoCita(Long id)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LA TAREA POR ID ****/");
		
		if (id==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		Tarea tarea = tareaDAO.read(id);
		return TareaTransformer.transfromarTarea(tarea);
	}

}

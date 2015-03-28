/**
* Nombre del Programa : ActualizarTareaFuncionarioServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implemenatcion del servicio para realizar la actualizacion de las Tareas de los Funcionarios
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.model.Tarea;
import mx.gob.segob.nsjp.service.tarea.ActualizarTareaFuncionarioService;

/**
 * Implemenatcion del servicio para realizar la actualizacion de las Tareas de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ActualizarTareaFuncionarioServiceImpl implements
		ActualizarTareaFuncionarioService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ActualizarTareaFuncionarioServiceImpl.class);
	
	@Autowired
	private TareaDAO tareaDAO;
	
	
	@Override
	public void actualizarTareaFuncionario(TareaDTO tareaDTO) throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS TAREAS POR ESTATUS DE UN FUNCIONARIO ****/");
		
		if (tareaDTO.getIdFuncionario()==null || tareaDTO.getTareaId()!=null
				|| tareaDTO.getNtiempoReal()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Tarea tarea = tareaDAO.read(tareaDTO.getTareaId());
		
		tarea.setNtiempoReal(tareaDTO.getNtiempoReal());
		tareaDAO.update(tarea);
	}

}

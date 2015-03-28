/**
* Nombre del Programa : ConsultarFuncionarioPorDepartamentoServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorDepartamentoService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarFuncionarioPorDepartamentoServiceImpl implements
		ConsultarFuncionarioPorDepartamentoService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarFuncionarioPorDepartamentoServiceImpl.class);
	
	@Autowired
	private FuncionarioDAO funcDao;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorDepartamentoService#consultarFuncionarioPorDepartamento(java.lang.Long)
	 */
	@Override
	public List<FuncionarioDTO> consultarFuncionarioPorDepartamento(
			Long idDepartamento) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR FUNCIONARIOS POR TIPO DE DEPARTAMENTO ****/");
		
		/*Verificación de parámetros*/
		if(idDepartamento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<Funcionario> funcionarios=funcDao.consultarFuncionarioPorDepartamento(idDepartamento);
		List<FuncionarioDTO> funcDTOs =new ArrayList<FuncionarioDTO>();
		for (Funcionario func : funcionarios) {
			funcDTOs.add(FuncionarioTransformer.transformarFuncionario(func));
		}
		return funcDTOs;
	}

}

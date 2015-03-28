/**
* Nombre del Programa : ConsultarFuncionarioExternoServiceImpl.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 Apr 2012
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioExternoService;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.CriterioConsultaFuncionarioExternoWSDTOTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.FuncionarioWSDTOTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Service("consultarFuncionarioExternoService")
@Transactional
public class ConsultarFuncionarioExternoServiceImpl implements
		ConsultarFuncionarioExternoService {
	
	@Autowired
	private FuncionarioDAO funcionarioDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioExternoService#consultarFuncionariosXCriterio(mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO)
	 */
	@Override
	public List<FuncionarioWSDTO> consultarFuncionariosXCriterio(
			CriterioConsultaFuncionarioExternoWSDTO criterioConsultaFuncionarioExternoWSDTO)
			throws NSJPNegocioException {
		
		List<FuncionarioWSDTO> funcionariosWSDTO = null;
		List<Funcionario> funcionarios = funcionarioDAO.consultarFuncionariosXCriterio(
				CriterioConsultaFuncionarioExternoWSDTOTransformer.transformar(criterioConsultaFuncionarioExternoWSDTO));
		if (funcionarios != null && !funcionarios.isEmpty()){
			funcionariosWSDTO = new ArrayList<FuncionarioWSDTO>();
			for (Funcionario f : funcionarios){
				funcionariosWSDTO.add(FuncionarioWSDTOTransformer.transformarFuncionario(f));
			}
		}
		return funcionariosWSDTO;
	}

}

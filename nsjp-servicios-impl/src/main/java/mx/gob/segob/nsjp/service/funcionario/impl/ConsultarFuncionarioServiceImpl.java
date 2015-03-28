package mx.gob.segob.nsjp.service.funcionario.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarFuncionarioServiceImpl implements
		ConsultarFuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public FuncionarioDTO obtenerFuncionarioDTO(FuncionarioDTO funcionario)
			throws NSJPNegocioException {
		Funcionario func = funcionarioDAO.read(funcionario.getClaveFuncionario());
		return FuncionarioTransformer.transformarFuncionario(func);
	}

}

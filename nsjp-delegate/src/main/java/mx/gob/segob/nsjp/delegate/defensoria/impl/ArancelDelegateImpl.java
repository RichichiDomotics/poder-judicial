/**
 * 
 */
package mx.gob.segob.nsjp.delegate.defensoria.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.defensoria.ArancelDelegate;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService;
import mx.gob.segob.nsjp.service.defensoria.GuardarArancelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service ("arancelDelegate")
public class ArancelDelegateImpl implements ArancelDelegate {

	@Autowired
	private GuardarArancelService guardarArancelService;
	@Autowired
	private ConsultarArancelService consultarArancelService;


	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.defensoria.ArancelDelegate#guardarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)
	 */
	@Override
	public Long guardarArancel(ArancelDTO arancelDTO)
			throws NSJPNegocioException {
		return guardarArancelService.guardarArancel(arancelDTO);
	}

	public void pagarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException{
		guardarArancelService.pagarArancel(arancelDTO);
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.defensoria.ArancelDelegate#consultarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)
	 */
	@Override
	public ArancelDTO consultarArancel(ArancelDTO arancelDTO)
			throws NSJPNegocioException {
		return consultarArancelService.consultarArancel(arancelDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.defensoria.ArancelDelegate#consultarArancelesXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<ArancelDTO> consultarArancelesXNumeroExpediente(
			ExpedienteDTO expedienteDTO, Long claveFuncionario) throws NSJPNegocioException {
		return consultarArancelService.consultarArancelesXNumeroExpediente(expedienteDTO, claveFuncionario);
	}

	@Override
	public List<ArancelDTO> consultarArancelesXFuncionario(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		return consultarArancelService.consultarArancelesXFuncionario(funcionarioDTO);
	}

}

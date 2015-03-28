/**
 * 
 */
package mx.gob.segob.nsjp.delegate.almacen.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.almacen.EncargadoAlmacenDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarEncargadosAlmacenService;
import mx.gob.segob.nsjp.service.almacen.EncargadoAlmacenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service ("encargadoAlmacen")
public class EncargadoAlmacenDelegateImpl implements EncargadoAlmacenDelegate {
	
	@Autowired
	private EncargadoAlmacenService encargadoAlmacenService;
	@Autowired
	private ConsultarEncargadosAlmacenService consultarEncargadosAlmacenService;

	@Override
	public Long asignarEncargadoDAlmacen(AlmacenDTO almacenDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		return encargadoAlmacenService.asignarEncargadoDAlmacen(almacenDTO, funcionarioDTO);
	}

	@Override
	public Long removerEncargadoDAlmacen(AlmacenDTO almacenDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		return encargadoAlmacenService.removerEncargadoDAlmacen(almacenDTO, funcionarioDTO);
	}

	@Override
	public List<FuncionarioDTO> consultarEncargadosDAlmacen(
			AlmacenDTO almacenDTO) throws NSJPNegocioException {
		return consultarEncargadosAlmacenService.consultarEncargadosDAlmacen(almacenDTO);
	}

}

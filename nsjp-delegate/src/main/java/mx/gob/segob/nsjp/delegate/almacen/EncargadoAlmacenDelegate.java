/**
 * 
 */
package mx.gob.segob.nsjp.delegate.almacen;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public interface EncargadoAlmacenDelegate {
	
	/**
	 * Operación que permite asignar un almacén a un funcionario 
	 * @param almacenDTO: Id
	 * @param funcionarioDTO: Id
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long asignarEncargadoDAlmacen(AlmacenDTO almacenDTO, FuncionarioDTO funcionarioDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite des-asignar un almacén a un funcionario (Si elimina el cruce, no los objetos)
	 * @param almacenDTO: Id
	 * @param funcionarioDTO: Id
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long removerEncargadoDAlmacen(AlmacenDTO almacenDTO, FuncionarioDTO funcionarioDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar los funcionarios asignados como encargados para un almacén dado
	 * @param almacenDTO: Id
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarEncargadosDAlmacen(AlmacenDTO almacenDTO)throws NSJPNegocioException;

}

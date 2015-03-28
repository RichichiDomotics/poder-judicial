/**
 * 
 */
package mx.gob.segob.nsjp.delegate.defensoria;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public interface ArancelDelegate {
	
	/**
	 * Operación que permite Registrar o Actualizar un Arancel
	 * Guarda si arancelId=null
	 * Actualiza si arancelId!=null
	 * @param arancelDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long guardarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException;

	/**
	 * Operación que permite registrar el pago de un Arancel
	 * Guarda si arancelId=null
	 * Actualiza si arancelId!=null
	 * @param arancelDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public void pagarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException;

	
	/**
	 * Operación que permite Consultar un Arancel por su ID
	 * @param arancelDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ArancelDTO consultarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite Consultar los Aranceles que pertenecen a un Número de Expediente
	 * @param expedienteDTO: numeroExpediente / numeroExpedienteId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ArancelDTO> consultarArancelesXNumeroExpediente(ExpedienteDTO expedienteDTO, Long claveFuncionario)throws NSJPNegocioException;
	
	/**
	 * Operación que permite Consultar los Aranceles que pertenecen a un Funcionario
	 * @param funcionarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ArancelDTO> consultarArancelesXFuncionario(FuncionarioDTO funcionarioDTO)throws NSJPNegocioException;

}

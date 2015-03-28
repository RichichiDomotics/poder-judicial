/**
 * 
 */
package mx.gob.segob.nsjp.service.defensoria;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarArancelService {

	/**
	 * Operación que permite Consultar un Arancel por su ID
	 * @param arancelDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	ArancelDTO consultarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException;

	/**
	 * Operación que permite Consultar los Aranceles que pertenecen a un Número de Expediente
	 * @param expedienteDTO: numeroExpediente / numeroExpedienteId
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ArancelDTO> consultarArancelesXNumeroExpediente(
			ExpedienteDTO expedienteDTO, Long claveFuncionario)throws NSJPNegocioException;

	/**
	 * Operación que permite Consultar los Aranceles que pertenecen a un Funcionario
	 * @param funcionarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ArancelDTO> consultarArancelesXFuncionario(
			FuncionarioDTO funcionarioDTO)throws NSJPNegocioException;

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarFuncionariosXTribunalService {
	
	/**
	 * Servicio que permite consultar Funcionarios por Tribunal
	 * @param catDiscriminanteId: Representa el id del Tribunal
	 * @return List<FuncionarioWSDTO>
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioWSDTO> consultarFuncionariosXTribunal(Long catDiscriminanteId)throws NSJPNegocioException;


}

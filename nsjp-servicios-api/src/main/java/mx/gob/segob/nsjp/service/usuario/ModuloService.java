/**
 * 
 */
package mx.gob.segob.nsjp.service.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;

/**
 * @author LuisMG
 *
 */
public interface ModuloService {

	/**
	 * Consulta los módulos registrados en el sistema
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ModuloDTO> consultarModulos() throws NSJPNegocioException;
	
}

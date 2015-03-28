/**
 * 
 */
package mx.gob.segob.nsjp.service.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;

/**
 * @author LuisMG
 *
 */
public interface FuncionService {
	
	/**
	 * Método que obtiene una lista de todos los archivos dado un url dentro de la aplicación con base en el arreglo de extensiones que se le indican
	 * @param url
	 * @param extensiones
	 * @return
	 * @throws NSJPNegocioException
	 */
	
	public List<FuncionDTO> inventariarFunciones (FuncionDTO url,List<String> extensiones) throws NSJPNegocioException;

}

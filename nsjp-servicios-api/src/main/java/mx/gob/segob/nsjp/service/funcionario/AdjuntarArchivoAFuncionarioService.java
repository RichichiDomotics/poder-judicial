/**
 * 
 */
package mx.gob.segob.nsjp.service.funcionario;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;

/**
 * @author Alfonso
 *
 */
public interface AdjuntarArchivoAFuncionarioService {

	/**
	 * Servicio que es utilizado para ingresar la foto de un funcionario
	 * @param funcionario
	 * @param adjunto
	 * @throws NSJPNegocioException
	 */
	void adjuntarArchivoAFuncionario(Long idfuncionario,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException;
	
}

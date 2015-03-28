/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarEvidenciasNoDevueltasService {

	/**
	 * Operación que permite ver las evidencias que deberían estar devueltas y no han sido devueltas a la fecha
	 * @param usuarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasNoDevueltas(UsuarioDTO usuarioDTO)throws NSJPNegocioException;

}

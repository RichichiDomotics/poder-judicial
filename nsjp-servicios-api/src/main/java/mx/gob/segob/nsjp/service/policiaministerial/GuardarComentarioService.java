/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;

/**
 * @author adrian
 *
 */
public interface GuardarComentarioService {

	Long guardarComentario(ComentarioDTO comentarioDTO)throws NSJPNegocioException;

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;

/**
 * @author adrian
 *
 */
public interface GuardarSeguimientoService {

	/**
	 * Operación que permite guardar / actualizar un seguimientoLI
	 * guardar: Id = 0 o NULL
	 * @param seguimientoLIDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarSeguimientoLI(SeguimientoLIDTO seguimientoLIDTO)throws NSJPNegocioException;

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;

/**
 * @author adrian
 *
 */
public interface ActualizarControversiaService {

	/**
	 * Operación que actualiza cuando se lee una Controversia resuelta
	 * @param cumplimientoDTO (idDocumento, funcionarioId)
	 * @throws NSJPNegocioException
	 */
	void actualizarControversia(CartaCumplimientoDTO cumplimientoDTO)throws NSJPNegocioException;

}

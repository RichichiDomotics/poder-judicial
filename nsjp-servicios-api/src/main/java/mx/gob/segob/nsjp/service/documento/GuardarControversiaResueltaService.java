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
public interface GuardarControversiaResueltaService {

	/**
	 * Operación que permite registrar una carta de cumplimiento de acuerdo y crea una actividad para el expediente
	 * @param cumplimientoDTO: Es un documento
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarControversiaResuelta(CartaCumplimientoDTO cumplimientoDTO)throws NSJPNegocioException;

}

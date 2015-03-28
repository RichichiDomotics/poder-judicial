/**
 * 
 */
package mx.gob.segob.nsjp.service.defensoria;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;

/**
 * @author adrian
 *
 */
public interface GuardarArancelService {

	/**
	 * Operación que permite Registrar o Actualizar un Arancel
	 * Guarda si arancelId=null
	 * Actualiza si arancelId!=null
	 * @param arancelDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarArancel(ArancelDTO arancelDTO)throws NSJPNegocioException;

	public void pagarArancel(ArancelDTO arancelDTO) throws NSJPNegocioException;

}

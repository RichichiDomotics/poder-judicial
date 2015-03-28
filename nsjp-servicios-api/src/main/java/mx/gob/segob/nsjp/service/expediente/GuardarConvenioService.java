/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;

/**
 * @author adrian
 *
 */
public interface GuardarConvenioService {

	/**
	 * Operación que permite registrar o modificar un acuerdo restaurativo
	 * idAcuerdo==null entonces REGISTRA
	 * idAcuerdo!=null entonces MODIFICA
	 * @param restaurativoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarConvenio(ConvenioDTO restaurativoDTO)throws NSJPNegocioException;

}

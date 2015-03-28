/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarCuerpoOficioService {

	/**
	 * Operación que realiza la consulta completa de un cuerpo de Oficio
	 * @param cuerpoOficioID
	 * @return
	 * @throws NSJPNegocioException
	 */
	CuerpoOficioEstructuradoDTO consultarCuerpoOficio(CuerpoOficioEstructuradoDTO cuerpoOficio)throws NSJPNegocioException;

}

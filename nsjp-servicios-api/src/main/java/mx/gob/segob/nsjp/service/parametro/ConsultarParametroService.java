/**
 * 
 */
package mx.gob.segob.nsjp.service.parametro;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ParametroDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarParametroService {

	/**
	 * Operación que realiza la funcionalidad de consultar un parámetro de configuración asociado a su clave.
	 * @param parametroDTO: nombre=clave
	 * @return
	 * @throws NSJPNegocioException
	 */
	ParametroDTO consultarParametro(Parametros parametro)throws NSJPNegocioException;

}

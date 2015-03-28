/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * Contrato del servicio para ingresar un objeto de tipo Otro 
 * 
 * @version 1.0
 * @author GustavoBP
 */
public interface IngresarOtroObjetoService {

	/**
	 * Servicio utlizado para registrar un Objeto de tipo Otro.
	 * 
	 * @param objetoDto
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarOtroObjeto(ObjetoDTO objetoDto) throws NSJPNegocioException;
	
}

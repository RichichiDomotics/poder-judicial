/**
 * 
 */
package mx.gob.segob.nsjp.service.lugar;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;

/**
 * @author adrian
 *
 */
public interface CompletarAsentamientoService {

	/**
	 * Operación que permite completar por un código postal un asentamiento
	 * @param codigoPostal
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AsentamientoDTO> completarAsentamientoXCodigoPostal(String codigoPostal)throws NSJPNegocioException;

	String obtenerCodigoPostalXIdAsentamiento(Long idAsentamiento)throws NSJPNegocioException;

	/**
	 * Obtiene la informacion de un asentmiento de acuerdo al id enviado como parametro
	 * @param idAsentamiento
	 * @return
	 * @throws NSJPNegocioException
	 */
	AsentamientoDTO obtenerAentamientoPrId(Long idAsentamiento) throws NSJPNegocioException;

}

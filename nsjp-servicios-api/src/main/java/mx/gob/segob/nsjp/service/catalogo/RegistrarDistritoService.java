/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;

/**
 * @author AlineGS
 *
 */
public interface RegistrarDistritoService {

	/**
	 * Servicio que permite registrar y modificar un distrito
	 * @param distritoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarDistrito(CatDistritoDTO distritoDTO)throws NSJPNegocioException;

	/**
	 * Servicio que permite guardar un distrito y un discriminante fantasma para el mismo.
	 * @param distritoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarDistritoConFantasma(CatDistritoDTO distritoDTO)throws NSJPNegocioException;

}

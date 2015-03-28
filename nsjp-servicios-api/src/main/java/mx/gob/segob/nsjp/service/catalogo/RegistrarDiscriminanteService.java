/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;

/**
 * @author AlineGS
 *
 */
public interface RegistrarDiscriminanteService {

	/**
	 * Servicio que permite registrar y modificar un discriminante
	 * @param DiscriminanteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarDiscriminante(CatDiscriminanteDTO discriminanteDTO)throws NSJPNegocioException;

}

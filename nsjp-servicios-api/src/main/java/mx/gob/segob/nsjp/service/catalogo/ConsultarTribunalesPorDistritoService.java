/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteWSDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarTribunalesPorDistritoService {

	
	/**
	 * Servicio que permite consultar Tribunales por Distrito 
	 * @param distritoID: Representa el Id del distrito
	 * @return List<CatDiscriminanteWSDTO>
	 * @throws NSJPNegocioException
	 */

	public List<CatDiscriminanteWSDTO> consultarTribunalesPorDistrito(Long distritoID)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar Agencias por Distrito 
	 * @param idAgencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CatDiscriminanteWSDTO> consultarAgenciasPorDistrito(Long idAgencia)throws NSJPNegocioException;
	
}

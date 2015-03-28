/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;

/**
 * @author AlineGS
 *
 */
public interface ConsultarDiscriminanteService {

	/**
	 * Servicio que permite consultar todos los discriminantes
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatDiscriminanteDTO> consultarDiscriminantes(TipoDiscriminante tipo)throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar un discriminante
	 * @param discriminanteID
	 * @return
	 * @throws NSJPNegocioException
	 */
	CatDiscriminanteDTO consultarDiscriminanteXId(Long discriminanteID)throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar todos los discriminantes de un distrito
	 * @param distritoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatDiscriminanteDTO> consultarDiscriminantesXDistrito(Long distritoID,TipoDiscriminante tipo)throws NSJPNegocioException;
	
	
	/**
	 * Servicio que permite consultar los discriminantes de un distrito de acuerdo a la institucion 
	 * se obtiene el tipo. Pe. si es PJ regresara tribunales, si es PG regresara Agencias
	 * @param distritoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatDiscriminanteDTO> consultarDiscriminantesXDistritoYTipoInstitucion(Long distritoID)throws NSJPNegocioException;

}

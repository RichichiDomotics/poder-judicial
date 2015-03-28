/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;

/**
 * @author AlineGS
 *
 */
public interface ConsultarDistritoService {

	/**
	 * Servicio que permite consultar todos los distritos
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatDistritoDTO> consultarDistritos()throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar un distrito
	 * @param distritoID
	 * @return
	 * @throws NSJPNegocioException
	 */
	CatDistritoDTO consultarDistritoXId(Long distritoID)throws NSJPNegocioException;

}

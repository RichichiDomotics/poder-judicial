/**
 * 
 */
package mx.gob.segob.nsjp.delegate.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;

/**
 * @author AlineGS
 *
 */
public interface DistritoDelegate {
	
	/**
	 * Servicio que permite registrar y modificar un distrito
	 * @param distritoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long registrarDistrito(CatDistritoDTO distritoDTO)throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar todos los distritos
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CatDistritoDTO> consultarDistritos()throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar un distrito
	 * @param distritoID
	 * @return
	 * @throws NSJPNegocioException
	 */
	public CatDistritoDTO consultarDistritoXId(Long distritoID)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite registrar y modificar un discriminante
	 * @param DiscriminanteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long registrarDiscriminante(CatDiscriminanteDTO DiscriminanteDTO)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar todos los discriminantes de un distrito
	 * @param distritoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CatDiscriminanteDTO> consultarDiscriminantesXDistrito(Long distritoID, TipoDiscriminante tipo)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar los discriminantes de un distrito de acuerdo a la institucion
	 * si es PJ consultara del tipo tribunal, si es PG consultara del tipo Agencia, Etc
	 * 
	 * @param distritoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CatDiscriminanteDTO> consultarDiscriminantesXDistritoYTipoInstitucion(Long distritoID)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar un discriminante
	 * @param discriminanteID
	 * @return
	 * @throws NSJPNegocioException
	 */
	public CatDiscriminanteDTO consultarDiscriminanteXId(Long discriminanteID)throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar todos los discriminantes
	 * @param tipo	Si NULL: consulta todos,	Si DATO: consulta por tipo
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<CatDiscriminanteDTO> consultarDiscriminantes(TipoDiscriminante tipo)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite guardar un distrito y un discriminante fantasma para el mismo.
	 * @param distritoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long registrarDistritoConFantasma(CatDistritoDTO distritoDTO)throws NSJPNegocioException;
	
	/**
	 * Metodo que permite eliminar un Distrito siempre y cuando no tenga realaciones con otras tablas pe. 
	 * Funcioario, Expediente
	 * @param distritoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long eliminarDistrito(CatDistritoDTO distritoDTO)
	throws NSJPNegocioException;
}

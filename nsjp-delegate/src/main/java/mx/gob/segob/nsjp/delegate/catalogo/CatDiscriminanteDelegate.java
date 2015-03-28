/**
 * 
 */
package mx.gob.segob.nsjp.delegate.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;

/**
 * @author rgama
 *
 */
public interface CatDiscriminanteDelegate {
	
    /**
     * Servicio que se conecta a la institución indicada por <code>target</code> para consultar 
     * los tribunales por el <code>distritoId</code> 
     * @param distritoId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    public List<CatDiscriminanteDTO> consultarTribunalesPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException;
    
    /**
     * Servicio que se conecta a la institución indicada por <code>target</code> para consultar 
     * los agencias por el <code>distritoId</code> 
     * @param distritoId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    public List<CatDiscriminanteDTO> consultarAgenciasPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException;
    
    /**
     * Metodo que permite eliminar una agencia siempre y cuando no tenga relaciones
     * @param discriminanteDTO
     * @return
     * @throws NSJPNegocioException
     */
    public Long eliminarAgencia(CatDiscriminanteDTO discriminanteDTO) throws NSJPNegocioException;
    
}

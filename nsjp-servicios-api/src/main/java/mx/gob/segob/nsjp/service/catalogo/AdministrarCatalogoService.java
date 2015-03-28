/**
 * Nombre del Programa : AdministrarCatalogoService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para el servicio que administra catalogos
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Contrato para el servicio que administra catalogos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AdministrarCatalogoService {

    CatalogoDTO obtenerValor(CatalogoDTO idValor)  throws NSJPNegocioException;
    
    CatalogoDTO obtenerDefinicion(Long idCatalogo)  throws NSJPNegocioException;
    
    Long registrarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    void actualizarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    void eliminarValor(CatalogoDTO input) throws NSJPNegocioException;
    
    CatalogoCompletoDTO obtenerCatalogo(Long idCatalogo) throws NSJPNegocioException;
    
    
    List<CatalogoDTO> obtenerListaCatalogos()throws NSJPNegocioException;

    /**
     * Se conecta a la intitución indicada por <code>target</code> para consultar
     * Funcionarios por id del catDiscriminante
     * @param catDiscriminanteId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> consultarFuncionariosXTribunal(
      		 Long catDiscriminanteId,Instituciones target) throws NSJPNegocioException;
    /**
     * Cliente que se conecta a la institución indicada por <code>target</code> para consultar 
     * los tribunales por el <code>distritoId</code> 
     * @param distritoId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    List<CatDiscriminanteDTO> consultarTribunalesPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException ;
    
    /**
     * Cliente que se conecta a la institución indicada por <code>target</code> para consultar 
     * las agencias por el <code>distritoId</code> 
     * @param distritoId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    List<CatDiscriminanteDTO> consultarAgenciasPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException ;
}

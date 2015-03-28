/**
* Nombre del Programa : CatRelacionDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de CatRelacion
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
package mx.gob.segob.nsjp.dao.relacion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import mx.gob.segob.nsjp.model.CatRelacion;

/**
 * Contrato para los metodos de acceso a datos de CatRelacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface CatRelacionDAO extends GenericDao<CatRelacion, Long> {


    /**
     * Consulta los CatRelacion de una categoriaRelacion.
     * @param idCategoriaRelacion El id de la de la categoria relacion a buscar.
     * @return Una lista con los CatRelacion asociadas a la categoria. Regresa
     * la lista vacia en caso de no encontrar CatRelacion asociadas.
     */
    List<CatRelacion> consultarRelacionesXCategoria(Long idCategoriaRelacion);

    /**
     * Consulta los CatRelacion de una CatCategoriaRelacion.
     */
    List<CatRelacion> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacion catCategoriaRelacion);
    
    /**
     * Consulta los Parentescos asociados a una persona
     * @return List<CatRelacionDTO>
     * @throws NSJPNegocioException
     */
    List<CatRelacion> consultarParentescos();

}

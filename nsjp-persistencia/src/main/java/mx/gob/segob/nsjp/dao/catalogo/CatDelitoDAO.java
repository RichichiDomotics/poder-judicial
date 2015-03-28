/**
 * Nombre del Programa : CatDelitoDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatDelito;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface CatDelitoDAO extends GenericDao<CatDelito, Long> {
    /**
     * Consulta todos
     * 
     * @return
     */
   List<CatDelito> consultarTodos();
   List<CatDelito> consultarDelitoPorGravedad(CatDelito gravedad);
   
   /**
    * Consultar el catálogo de delitos por cualquiera, o combinación, de los siguientes filtros:
    *  -catDelitoId
    *  -claveDelito
    *  -nombre
    *  -esGrave
    *  -esAccionPenPriv
    * @param catDelitoFiltro
    * @return
    */
   List<CatDelito> consultarCatDelitoPorFilro(CatDelito catDelitoFiltro);
   
   /**
    * Consultar el catálogo de delitos por expediente
    * @param numExpId
    * @return
    */
   List<CatDelito> consultarDelitosSinIdsGrid(String idsGrid);
   
   
   /**
    * Consulta catDelito por id y por clave
    * @param catDelitoFiltro
    * @return
    */
   CatDelito consultarCatDelitoPorFiltro(CatDelito catDelitoFiltro);
   
   /**
    * Consulta el numero de delitos que pertenecen al cat delito que se esta pasando como parametro
    * @param catDelitoFiltro contiene el catDelito id
    * @return Numero de delitos 
    */
   Long consultarNumeroDelitosPorCatDelitoId(CatDelito catDelitoFiltro);
}

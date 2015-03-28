/**
*
* Nombre del Programa : NarrativaDAO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para el DAO para obtener catalogos                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Catalogo;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Interface para el DAO para obtener catalogos
 * @author vaguirre
 * @version 1.0
 * 
 */

public interface CatalogoDAO extends GenericDao<Catalogo, Long> {
	/**
	 * Recupera un catalogo sencillo.
	 * 
	 * @param idCatalogo
	 * @return
	 */
	List<Valor> recuperarCatalogoSencillo(Catalogos idCatalogo);

	   /**
     * Recupera un catalogo completo, con todos sus campos.
     * 
     * @param idCatalogo
     * @return
     */
    List<Valor> recuperarCatalogoCompleto(Catalogos idCatalogo);
	
	/**
	 * Recupera un catalogo dependiente.
	 * 
	 * @param idCatalogoHijo
	 * @param idValorPadre
	 * @return
	 */
	List<Valor> recuperarCatalogoDependiente(Catalogos idCatalogoHijo,
			Long idValorPadre);

	
	List<Catalogo> obtenerTodos();

}

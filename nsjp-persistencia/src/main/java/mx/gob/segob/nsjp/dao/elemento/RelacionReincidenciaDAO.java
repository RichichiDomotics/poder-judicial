/**
*
* Nombre del Programa : RelacionReincidenciaDAOImpl.java                                    
* Autor                            : rgama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 23/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para el DAO de la entidad RelacionReincidencia                      
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
package mx.gob.segob.nsjp.dao.elemento;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.RelacionReincidencia;

/**
 * 
 * @author rgama
 * @version 1.0
 */
public interface RelacionReincidenciaDAO extends GenericDao<RelacionReincidencia, Long> {
	/**
	 * Permite consultar las relacines de reincidencia por elemento
	 * @param elementoId: Identificador del elemento
	 * @return List<RelacionReincidencia>
	 */
	public List<RelacionReincidencia> consultarRelacionesDeReincidencia(Long elementoId);
    
}

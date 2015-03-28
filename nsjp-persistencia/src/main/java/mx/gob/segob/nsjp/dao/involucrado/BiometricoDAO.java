/**
* Nombre del Programa : BiometricoInvolucradoDAO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Contrato para los metodos de acceso a datos de la entidad Biometrico                      
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
package mx.gob.segob.nsjp.dao.involucrado;


import java.util.List;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Biometrico;

/**
 * Contrato para los metodos de acceso a datos de la entidad BiometricoInvolucrado
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface BiometricoDAO extends GenericDao<Biometrico, Long> {
	
	/**
	 * 
	 * Consultar los Biometricos por Involucrado
	 * @param elementoId
	 * @return
	 */
	List <Biometrico> consultarBiometricoByInvolucrado(Long elementoId);

}

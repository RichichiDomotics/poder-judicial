/**
*
* Nombre del Programa : NombreDemograficoDAO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para el DAO de la entidad Nombre demografico                      
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

package mx.gob.segob.nsjp.dao.persona;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Persona;

/**
 * Interface para el DAO de la entidad Nombre demografico.
 * @author CesarAgustin
 * @version 1.0
 *
 */
public interface NombreDemograficoDAO extends GenericDao<NombreDemografico, Long> {

	/**
	 * Recupera los nombres demograficos de un involucrado.
	 * @param involucradoId
	 * @return
	 */
	List<NombreDemografico> consutarNombresByInvolucrado(Long involucradoId);

	/**
	 * Consulta un nombre demografico que cumple el criterio
	 * nombre+' '+apellidoPaterno+' '+apellidoMaterno = nombre
	 * @param nombre nombre concatenado que debe satisfacer el nombre demografico devuelto
	 * @return
	 */
	public NombreDemografico consultarNombreDemograficoByNombreCompleto(String nombre);
	
	public Persona obtenerPersonaXDemografico(Long idDemografico);

}

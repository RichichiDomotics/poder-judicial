/**
*
* Nombre del Programa : DelitoDAO.java                                    
* Autor                  : Ricardo Gama                                              
* Compania               : Ultrasist                                                
* Proyecto               : NSJP                    Fecha: 24/05/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para la entidad Delito.                      
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
package mx.gob.segob.nsjp.dao.delito;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * Interface para la entidad Delito.
 * @author rgama
 * @since 1.0
 *
 */
public interface DelitoDAO extends GenericDao<Delito, Long> {
	/**
	 * 
	 * @param numeroCaso Numero de caso
	 * @return Lista de delitos
	 */
	public List<Delito> consultarDelitosPorCaso(String numeroCaso);

	/**
	 * @author cesarAgustin
	 * @param expedienteId
	 * @returnv lista delitos de un expediente
	 */
	public List<Delito> obtenerDelitoPorExpediente(Long expedienteId);

    /**
     * Asocia la lista de delitos al expediente.
     * @param delitos Los delitos que seran asociados al expediente.
     * @param expediente El expediente al que se asociara la lista de delitos.
     */
    void guardarDelito(List<Delito> delitos, Expediente expediente);

    /**
     * @author cesarAgustin
     * @param fechaIni
     * @param fechaFin
     * @param tipoDelito
     * @return
     */
    List<Object[]> obtenerTipoDelitoPorMes (Date fechaIni, Date fechaFin, Boolean tipoDelito);
    
    /**
     * Obtiene el numero de veces que se dio de alta un delito por mes.
     * @author cesarAgustin
     * @param fechaIni
     * @param fechaFin
     * @param catDelito
     * @return
     */
    List<Object[]> obtenerDelitoPorMes (Date fechaIni, Date fechaFin, Long catDelito );
    
    /**
     * Permite obtener un Delito a partir del id del Delito
     * @param delitoId
     * @return 
     */
	public Delito obtenerDelitoPorId(Long delitoId);

	/**
	 * Consulta la lista de delitos asociada al expediente con el 
	 * expediente id que recibe como parametro
	 * @param expedienteId
	 * @return
	 */
	public List<Delito> obtenerListaDeDelitoPorExpediente(Long expedienteId);

}

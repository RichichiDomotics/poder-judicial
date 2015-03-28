/**
 *
 * Nombre del Programa : DomicilioDAOImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Interface para el DAO de la entidad Caso                      
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

package mx.gob.segob.nsjp.dao.caso;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Elemento;

/**
 * 
 * @author Cesar Agustin
 * @version 1.0
 * 
 */

public interface CasoDAO extends GenericDao<Caso, Long> {

	/**
	 * Recupera el último número de caso registrado en la BD.
	 * En caso de que monogramaInstitucion sea distinto a nulo, se hace
	 * la consulta del último número de caso generado en la institución
	 * indicada por el monograma.
	 * 
	 * @param monogramaInstitucion
	 * @return Último número de caso registrado en la BD.
	 */
    public String recuperarUltimoNumero(String monogramaInstitucion);

    /**
     * Recupera los casos que corresponden al parametro recibido.
     * 
     * @param numeroCaso
     * @return
     */
    public List<Caso> consultarCasosPorNumero(String numeroCaso);
    /**
     * 
     * @param idUsuario
     * @return
     */
    List<Caso> consultarCasos(Long idUsuario);

    /**
     * Recupera los casos que tengan una fecha de creacion dentro dle rango enviado como parametro
     * @param fechaCreacionInicio
     * @param fechaCreacionFin
     * @return
     */
	public List<Caso> consultarCasosPorFecha(Date fechaCreacionInicio,
			Date fechaCreacionFin);

	/**
	 * Recupera los casos que correspondan al delito enviado como parametro
	 * @param delitoId
	 * @return
	 */
	public List<Caso> consultarCasosPorDelito(String delito);
	
	/**
     * Consulta los números e id's de casos que tengan la menos un evento que se deba mostrar
     * en el árbol de consulta de históricos
     * @param usr Usuario que consulta
     * @return Lista de casos encontrados
     */
    List<Caso> consultarCasosConEventosHistoricos(Long usuarioId) throws NSJPNegocioException;

    /**
     * Consulta el caso que ampara al numero de expediente dado
     * @param expedienteId
     * @return
     */
	public Caso consultarCasoPorExpediente(Long expedienteId);

	/**
	 * Obtiene la informacion del caso de acuero al paramtro numero general caso
	 * @param numeroCasoAsociado
	 * @return
	 */
	public Caso obtenerCasoByNumeroGeneral(String numeroCasoAsociado);
	
	  /**
     * Permite buscar las reincidencias de un elemnto:
     * Si es un Automovil, permitira filtrar por el numero de serie y por el numero de placas
     * Si es un Arma, permitira filtrar por el numero de serie
     * Se es una persona, permitira filtrar por el nombre y apellidos
     * @param elemento
     * @return List<Caso> 
     * @throws NSJPNegocioException
     */
	public List<Caso> consultarReincidenciasDeElementos(Elemento elemento);

	public Long consultarIdXNumeroCaso(String numeroGeneralCaso);
}

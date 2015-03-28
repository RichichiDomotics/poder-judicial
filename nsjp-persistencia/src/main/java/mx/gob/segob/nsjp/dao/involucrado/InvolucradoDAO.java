/**
*
* Nombre del Programa : InvolucradoDAO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 05/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Interface para el DAO de la entidad Involucrado                      
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

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 */
public interface InvolucradoDAO extends GenericDao<Involucrado, Long> {

	public List<Involucrado> obtenerInvolucradosAll();

	public Expediente obtenerExpediente(Integer i);

	/**
	 * Consulta que regresa los involucrados que estan en el expediente que es recibido como parametro.
	 * @param expedienteId
	 * @return Lista de involucrado encontrados
	 */
	public List<Involucrado> consultarInvolucradosByExpediente(Long expedienteId);

	
	 /**
	  * Consulta de involucrados por calidades asociados a un expediente.
	  * 
	  * @param expedienteId
	  * @param calidades
	  * @return
	  */
	List<Involucrado> obtenerInvolucradosPorExpedienteYCalidades(Long expedienteId, Calidades[] calidades);
	
	/**
	 * 
	 * @param aliasInvolucrado
	 * @return
	 */
	public List<Involucrado> consultarExpedientesByAlias (String aliasInvolucrado);
	
	
	/**
	 * 
	 * @param aliasInvolucrado
	 * @return
	 */
	public List<Involucrado> consultarExpedientesByAliasLike(String aliasInvolucrado);

	
	/**
	 * realiza la busqueda de personas por nombre y/o apellido paterno y/o apellido materno 
	 * @version 1.1
	 * @author CesarAgustin
	 * @param apellidoMat
	 * @param apellidoPat
	 * @param nombre
	 * @return
	 */
	public List<Involucrado> consultarExpedientesByNombre(String nombre, String apellidoPat, String apellidoMat);

	/**
	 * 
	 * @param idExpediente
	 * @param calidadId
	 * @return
	 */
	public List<Involucrado> obtenerInvByIdExpAndCalidad(Long idExpediente, Long calidadId, Boolean esDetenido);

	/**
	 * Operación que realiza la funcionalidad de consultar los involucrados en una audiencia.
	 * 
	 * @param calidad: La calidad del involucrado (si no recibe calidad, consulta todas las calidades).
	 * @param audiencia: El identificador de la audiencia
	 * @return Devuelve un listado de involucrados asociados a la audiencia, en caso contrario null.
	 */
	public List<Involucrado> obtenerInvolucradosByAudiencia(Long audienciaId, Long calidadId);
	/**
	 * Busca los involucrados que sean de cierto tipo de calidad y que estén
	 * relacionados a cierto numero general de caso
	 * @param numeroCaso Número general de caso donde se encuentran los involucrados a buscar
	 * @param calidades calidades buscadas en los involucrados a obtener
	 * de resultado
	 * @return Lista de involucrados encontrados
	 */
	List<Involucrado> obtenerInvolucradosByCasoYCalidades(String numeroCaso,Calidades []calidades);

	/**
	 * Consulta los involucrados dado una lista de Identificadores, en caso de 
	 * que sea vacia la lista, se consulta nuevamente.  
	 * 
	 * @param idInvolucrados
	 * @return
	 */
	public List<Involucrado> consultarInvolucradosByIds(List<Long> idInvolucrados);
	
	/**
	 * Obtiene el numero de expedientes por mes dentro de un rango de fechas, en el cual el expediente
	 * cuente con algun involucrado con la condicion detenido, solicitado por el ususario.
	 * @param fechaInicio
	 * @param fechaFin
	 * @param condicionDet
	 * @return
	 */
	public List<Object[]> obtenerExpedientesPorCondicionDetencionInvYMes (Date fechaInicio, Date fechaFin, Boolean condicionDet);

	/**
	 * Consulta un involucrado de acuerdo a su propiedad folio de elemento.
	 * @param folioElemento
	 * @return
	 */
	public Involucrado consultarInvolucradoPorFolioElemento(String folioElemento);

	/**
	 * 
	 * @param folioInvolucrado
	 * @return
	 */
	public Involucrado obtenerInvolucradoByFolioElemento(String folioInvolucrado);


}

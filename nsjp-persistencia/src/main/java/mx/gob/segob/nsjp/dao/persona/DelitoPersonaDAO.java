/**
* Nombre del Programa : DelitoPersonaDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de accesos a datos de la entidad Delito Persona
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
package mx.gob.segob.nsjp.dao.persona;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;

/**
 * Contrato de metodos de accesos a datos de la entidad Delito Persona.
 * @version 1.0
 * @author cesarAgutin
 *
 */
public interface DelitoPersonaDAO extends GenericDao<DelitoPersona, Long> {

	/**
	 * 
	 * @param involucradoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DelitoPersona> consultarDelitoPerByInvolucrado (Long involucradoId);
	
	/**
	 * Metodo que realiza la funcionalidad de consultar los delitos asociados a un individuo por expediente
	 * @param involucradoId
	 * @return List<DelitoPersona> 
	 */
	public List<DelitoPersona> consultarDelitosPorImputado(Long involucradoId, Long expedienteId);

	/**
	 * Metodo que realiza la funcionalidad de consultar los delitos asociados a un individuo probable responsable por expediente
	 * @param involucradoId
	 * @return List<DelitoPersona> 
	 */
	public List<DelitoPersona> consultarDelitosPorImputadoResponsable(Long idInvolucrado,
			Long idExpediente);

	/**
	 * Servicio que consulta la relación delito-persona por expediente y delito
	 * @param idDelito
	 * @param idExpediente
	 * @return
	 */
	public List<DelitoPersona> consultarVictimaImputadoPorDelito(Long idDelito,
			Long idExpediente);
	
	/**
	 * Consulta los delitospersona por expediente y los ids de delitos, para saber si existen delitos asociados
	 * a un PR de acuerdo al expediente y si estan activos.
	 * 
	 * En caso de que se omita la lista de id de Delitos, se consultan todos los existentes del expediente que 
	 * esten activos.
	 * 
	 * @param idsDelitos
	 * @param idExpediente
	 * @return
	 */
	List<DelitoPersona> consultarDelitosPersonaPorExpedienteIdsDelito(List<Long> idsDelitos,
			Long idExpediente) ;

	/**
	 * Elimina las relaciones para un delito dado que esta por ser eliminado del expediente
	 * @param delPersist
	 */
	public void eliminarRelacionPorDelito(Delito delPersist);
	
	
	/**
	 * Metodo que realiza la funcionalidad de consultar los delitos asociados a un individuo probable responsable 
	 * @param involucradoId
	 * @author Emigdio Hernández
	 * @return List<DelitoPersona> 
	 */
	public List<DelitoPersona> consultarDelitosPorImputadoResponsable(Long idInvolucrado);
	
	/**
	 * Permite actualizar el campo esActivo de un DelitoPersona, es decir permite 
	 * dejar activo o inactivo un Delito Persona
	 * @param delitoPersonaId: Identificador 
	 */
	public void desactivarDelitoPersona(Long delitoPersonaId);
	
	/**
	 * Obtiene los detenidos por delito y mes, dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param catDelito
	 * @return
	 */
	Long obtenerDetenidosPorMesYDelito(Date fechaInicio, Date fechaFin, Long catDelito);

	/**
	 * Consulta que permite determinar si exite la relacion de un delito, con el probable
	 * responsable, la victima y el mismo modo de participacion.
	 * Con el objetivo que no se registre la misma relacion.
	 * 
	 * @param delitoId
	 * @param idProbableResponsable
	 * @param idVictima
	 * @param idFormaParticipacion
	 * @return
	 */
	Boolean existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(Long delitoId, Long idProbableResponsable, Long idVictima, Long idFormaParticipacion) ;

}
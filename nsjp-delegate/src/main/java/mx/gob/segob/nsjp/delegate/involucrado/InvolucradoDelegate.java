/**
* Nombre del Programa : InvolucradoDelegate.java                                    
* Autor                            : vaguirre                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 04/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integraci�n xxxxxxxxxxx                      
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
package mx.gob.segob.nsjp.delegate.involucrado;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DefensaInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Delegate para las operaciones relacionadas con los involucrados de una caso.
 * @author vaguirre
 *
 */
public interface InvolucradoDelegate {
	
	/**
	 * Guarda en base de datos la informaci�n referente a un involucrado.
	 * @param involucradoDTO
	 * @throws NSJPNegocioException
	 * @return
	 */
	public Long guardarInvolucrado (InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene la informacin referente a un involucrado de acuerdo a los parametros enviados.
	 * @param involucradoDTO
	 * @throws NSJPNegocioException
	 * @return InvolucradoDTO
	 */
	public InvolucradoDTO consultarIndividuo (InvolucradoDTO involucradoDTO)throws NSJPNegocioException;
		
	/**
	 * Obtiene la informacion referente a un involucrado a partir de su ID
	 * @param involucradoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	InvolucradoDTO obtenerInvolucrado(InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
	
	/**
	 * Actualiza la informacion de un individuo
	 * @param involucradoDTO
	 * @throws NSJPNegocioException
	 */
	public void actualizarIndividuo(InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
	
	/**
	 * Ingresa los datos de un involucrado que es menor de edad y los datos de su tutor.
	 * @param involucradoDTO
	 * @param tutorDTO
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> ingresarIndividuoMenor (InvolucradoDTO involucradoDTO, InvolucradoDTO tutorDTO) throws NSJPNegocioException;
	
	/**
	 * Ingresa los datos del involucrado en calida de victima
	 * @param involucradoDTO
	 * @return Long
	 * @throws NSJPNegocioException
	 */
	public Long ingresarVictima(InvolucradoDTO involucradoDTO)throws NSJPNegocioException;
	
	/**
	 * Obtiene los involucrados de un expediente que correspondan a una calidad especifica.
	 * @param expedienteDTO
	 * @param calidad
	 * @return involucrados obtenidos de la consulta
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarInvolucradoExpediente (ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario) throws NSJPNegocioException;

	/**
	 * Obtiene los involucrados de una audiencia que correspondan a las calidades de PROBABLE RESPONSABLE y VICTIMA, tanto como personas y organizaciones.
	 * @param audienciaDTO
	 * @return involucrados obtenidos de la consulta
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoViewDTO> obtenerImputadosVictimasAudiencia(AudienciaDTO audienciaDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene los involucrados de una audiencia que correspondan a las calidades de PROBABLE RESPONSABLE tanto como personas y organizaciones.
	 * @param audienciaDTO
	 * @return involucrados obtenidos de la consulta
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoViewDTO> obtenerImputadosAudiencia(AudienciaDTO audienciaDTO) throws NSJPNegocioException;

	/**
	 * Obtiene los involucrados de una audiencia, as� como los funcionarios que participan en la misma
	 * @param audienciaDTO
	 * @return involucrados y funcionarios obtenidos de la consulta
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoViewDTO> obtenerInvolucradosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza la funcionalidad de consultar los involucrados en una audiencia.
	 * 
	 * @param calidad: La calidad del involucrado (si no recibe calidad, consulta todas las calidades).
	 * @param audiencia: El identificador de la audiencia
	 * @return Devuelve un listado de involucrados asociados a la audiencia, en caso contrario null.
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarInvolucradosPorAudiencia(Long calidad, AudienciaDTO audiencia)throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de consultar los probables responsables asociados al expediente y cuya situaci�n sea detenido.
	 * 
	 * @param expedienteDTO: El identificador de expediente
	 * @param esDetenido: La bandera esDetenido 
	 * @return Regresa el listado de probables responsables detenidos y asociados al expediente, en caso contrario regresa null.
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarProbablesResponsablesDetenidos(ExpedienteDTO expedienteDTO, Boolean esDetenido)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza la funcionalidad de consultar los datos de los involucrados que se encuentren en cierta calidad y que se encuentren asociados a un caso
	 * 
	 * @param casoDTO: casoId
	 * @param calidadDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarInvolucradoPorCalidadCaso(CasoDTO casoDTO, CalidadDTO calidadDTO)throws NSJPNegocioException;

	/**
	 * Busca los involucrados que sean de cierto tipo de calidad y que est�n
	 * relacionados a cierto n�mero de expediente
	 * @param numeroExpedienteId N�mero expediente de los involucrados a buscar
	 * @param calidades calidades buscadas en los involucrados a obtener
	 * de resultado
	 * @return Lista de involucrados encontrados
	 */
	List<InvolucradoDTO> obtenerInvolucradosByCasoYCalidades(String numeroCaso,Calidades []calidades);
	
	/**
	 * Consulta de involucrados por calidades asociados a un numeroExpediente.
	 * 
	 * @param expedienteId
	 * @param calidades
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> obtenerInvolucradosPorExpedienteYCalidades(String numeroExpediente, Calidades[] calidades) throws NSJPNegocioException;
	

	/**
	 * Servicio que realiza la funcionalidad de consultar los involucrados que 
	 * tengan alguna medida (alterna) cuya fecha fin de medida  
	 * sea mayor o igual a la fecha (proporcionada o actual del sistema), adem�s de que 
	 * sean asociados a un funcionario particular
	 * 
	 * @param cFuncionario  clave del funcionario 
	 * @param fechaActual  la fecha actual del sistema
	 * @param esMedidaAlterna determina si la medida es Medida Alterna
	 * @return Lista de involucrados con Medida Alterna
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> consultarInvolucradosMedidasAlternasPorFecha(Long cFuncionario, Date fechaFin, Boolean esMedidaAlterna) throws NSJPNegocioException;

	/**
	 * Obtiene el numero de expedinetes que cumplem con la condicion detencion requerida y que se encuentren en 
	 * un rango de fechas
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param condicion
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> obtenerExpedientesPorCondicionDetencionInvYMes(
			Date fechaInicio, Date fechaFin, Boolean condicion) throws NSJPNegocioException;
	
	/**
	 * Actualiza la situacion juridica de un involucrado, si esta es sentenciado, se genera la carperta de ejecucion
	 * @author cesarAgustin
	 * @param involucradoDTO
	 * @param situacionJuridica
	 * @throws NSJPNegocioException
	 */
	public Long actualizarSituacionJuridicaInvolucrado(InvolucradoDTO involucradoDTO, Long situacionJuridica, SentenciaDTO sentenciaDTO) throws NSJPNegocioException;
	/**
	 * Obtiene la situacion juridica de un involucrado
	 * @author cesarAgustin
	 * @param involucradoDTO
	 * @param situacionJuridica
	 * @throws NSJPNegocioException
	 */
	public Long obtenerSituacionJuridicaInvolucrado(InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
    /**
     * Actualiza la situaci�n juridica de un invlucrado sin aplicar ninguna otra
     * regla de negocio.
     * 
     * @param involucradoDTO requerido: <b>elementoId</b>.
     * @param situacionJuridica
     * @return
     * @throws NSJPNegocioException
     */
    public void actualizarSituacionJuridicaDefensoria(
            InvolucradoDTO involucradoDTO, SituacionJuridica situacionJuridica)
            throws NSJPNegocioException;
	/**
	 * Operaci�n que guarda un involucrado como Defensor y lo asigna por relaci�n a un probable responsable involucrado
	 * @param involucradoDTO: Nuevo defensor
	 * @param probableResponsableId: Id Involucrado Existente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long guardarDefensorAsignadoInvolucrado(InvolucradoDTO involucradoDTO, Long probableResponsableId) throws NSJPNegocioException;

	/**
	 * Obtiene una lista de objetos DefensaInvolucradoDTO que contiene los involucrados del expediente <code>expedienteDTO</code>
	 * con la <code>calidad</code>
	 * @param expedienteDTO
	 * @param calidad
	 * @param usuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DefensaInvolucradoDTO> consultarInvolucradoExpedienteDefensoria(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario)
			throws NSJPNegocioException;
	
	 /**
     * Recupera los imputados de una audiencia que a�n no sean programados a otra audiencia siguiente
     * a partir de la audiencia actual (pasada como par�metro).
     * Esto se resuelve incluir solo aquellos imputados que cumplan: su Maxima Audiencia
     * que sea exactamente la actual. 
     * 
     * @param audienciaDTO - IdAudiencia actual
     * @return
     * @throws NSJPNegocioException
     */
    List<InvolucradoViewDTO> obtenerImputadosSiguienteAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException;
	/**
	 * Operaci�n que realiza la funcionalidad de consultar los involucrados de un expediente.
	 * 
	 * @param idExpediente: El identificador del expediente
	 * @return Devuelve un listado de involucrados asociados al expediente, en caso contrario null.
	 * @throws NSJPNegocioException
	 */
    public List<InvolucradoDTO> consultarInvolucradosPorExpediente(Long idExpediente) throws NSJPNegocioException;
}

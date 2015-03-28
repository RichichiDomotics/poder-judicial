/**
 *
 * Nombre del Programa : Expediente.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Interface para el DAO de la entidad Expediente                      
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

package mx.gob.segob.nsjp.dao.expediente;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 * 
 */

public interface ExpedienteDAO extends GenericDao<Expediente, Long> {
    /**
     * Recupera el último número de expediente registrado en la BD.
     * 
     * @return Último número de expediente registrado en la BD.
     */
    public String obtenerUltimoNumero(Long area);

    /**
     * Recupera los expedientes de acuerdo con los parametros de busqueda
     * El areaID es opcional para el Query
     * 
     * @param numeroExpediente
     * @return Lista de expedientes obetenidos
     */
    public List<Expediente> buscarExpedientes(String numeroExpediente, Long areaId,Long discriminanteId);
	
    public Expediente buscarUltimoNumeroPorExpedienteIdAreaId(Long expedienteId,
			Long areaId);
	
    /**
     * Consulta los expdientes por el id del caso.
     * 
     * @param idCaso
     * @return
     */
    List<Expediente> consultarExpedientesPorIdCaso(Long idCaso, Long areaId);

    /**
     * Consulta los expdientes por el id del caso.
     * Sin hacer referencia al numero de Expediente
     * 
     * @param idCaso
     * @return
     */
    List<Expediente> consultarExpedientesPorId(Long idCaso, Long areaId);
    
    /**
     * Recupera los expedientes de acuerdo con los parametros de busqueda
     * @param nombreEvidencia
     * @param palabrasClave
     * @return
     */
	public List<Long> consultarExpedientesPorEvidencia(
			String nombreEvidencia, List<String> palabrasClave);

	/**
	 * Obtiene el ultimo numero de expedintes del tipo de expediente requerido
	 * @param nomExpediente
	 * @return
	 */
	String obtenerUltimoNumTipoExp(String nomExpediente);
	
	/**
	 * Obtiene la lista de expedients por Actividad
	 * @param actividad
	 * @return List<Expediente>
	 */
	List<Expediente> consultarExpedientesPorActividadActual(Long actividad, Long estatusExp);
	
	/**
	 * Obtiene los expedientes de acuerdo a la actividad y area enviados como parametro.
	 * @param areaId
	 * @param actividadId
	 * @param anio
	 * @return lista de expedientes recuperados con la consulta
	 */
	public List<NumeroExpediente> consultarExpedientesActividadAreaAnio (Long areaId, Long actividadId, Long anio, Long agenciaId, Long cveFuncionario);
	
	 /**
     * Permite filtrar los Expedientes en base a:
     * @param etapa Permite filtrar  por las difierentes etapas que puede tener un NumeroExpediente
     * @param usuarioId Permite filtrar los expedientes designados a un defensor
     * @param areaId Representa el area a la que esta asociada el expediente
     * @return List<NumeroExpediente>
     */
    public List<NumeroExpediente> consultarExpedientesPorEtapa(EtapasExpediente etapa, Long usuarioId,Long areaId);

    /**
     * Consulta los detalles de un expediente, dado un expediente y un usuario.
     * Los detalles que se obtienen en el objeto regresado son:
     * <ol>
     * <li> Expediente.ExpedienteId
     * <li> Expediente.Caso
     * <li> Expediente.NumeroExpediente
     * <li> Si existe un imputado para el expediente este se guarda en
     * Expediente.involucrados[0], si no se puede encontrar un imputado para
     * este expediente se guarda null.
     * <li> Si existe un imputado, sus delitos se guardan en Expediente.delitos
     * </ol>
     * @param expediente
     * @param usuario
     * @return
     */
    Expediente consultarDetalleExpediente(Expediente expediente, Usuario usuario);
    /**
     * Obiente el expediente
     * @param numExp
     * @return Long
     */
    Long consultarExpedientePorNumeroExpediente(String numExp);

    /**
     * Consulta los expedientes para un área, recibidas de otra área
     * @param areaReceptora
     * @param areaOrigen
     */
	public List<Expediente> buscarExpedientesRemitentes(Long areaReceptora, Long areaOrigen);

	/**
	 * Consultar los expedientes asociados a un caso y a una institucion
	 *  
	 * @param idCaso
	 * @param idConfInstitucion
	 * @return
	 */
	List<Expediente> consultarExpedientesPorIdCasoConfInstitucion(Long idCaso,
			Long idConfInstitucion);
	/**
	 * Consultar las Causas asociadas a un caso 
	 * @param idCaso
	 * @return List<Expediente> 
	 */
	public List<Expediente> consultarCausasPorIdCaso(Long idCaso);

	/**
	 * Busca un expediente que pertenezca al caso numeroGeneralcaso y que 
	 * tenga un imputado que cumpla con los parametros nombre, aPaterno, 
	 * aMaterno, y tipo de calidad
	 * @param numeroGeneralcaso
	 * @param nombre
	 * @param aPaterno
	 * @param aMaterno
	 * @param idCalidad
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Expediente buscarExpedientePorCasoCalidadNombreImputado(String numeroGeneralcaso,
			String nombre, String aPaterno, String aMaterno, Long idCalidad) throws NSJPNegocioException;

	
	/**
	 * 
	 * @param numeroCaso
	 * @param imputado
	 * @param valorId
	 * @return
	 */
	public Expediente buscarExpedientePorCasoImputado(String numeroCaso,
			String imputado, Long valorId);

	
	/**
	 * Consulta un expediente que se encuentra asociado a un numero expediente
	 * identificado por <code>idNumeroExpediente</code>
	 * @param idNumeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Expediente consultarExpedientePorIdNumerExpediente(
			Long idNumeroExpediente)throws NSJPNegocioException;
	
	/** Obtiene el ID del expediente que se encuentra asociado a un numero expediente
     * identificado por <code>idNumeroExpediente</code>
     * @param idNumeroExpediente
     * @return
     * @throws NSJPNegocioException
     */
    public Long obtenerExpedienteIdPorIdNumerExpediente(
            Long idNumeroExpediente)throws NSJPNegocioException;
	/**
	 * @author cesarAgustin
	 * @param mesIni
	 * @param mesFin
	 * @return
	 */
	public List<Object[]> obtenerExpedientesPorMes (Date fechaIni, Date fechaFin);

	/**
	 * 
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Expediente> consultarExpedientesPorNumeroCaso(String numeroExpediente) throws NSJPNegocioException;

	
	/**
	 * Busca un expediente que este asociado al caso identificado por <code>numeroGeneralCaso</code> y que tenga un
	 * involucrado con folio de elemento igual a <code>folioInvolucrado</code>
	 * @param numeroGeneralCaso
	 * @param folioInvolucrado
	 * @return
	 * @throws NSJPNegocioException
	 */
	Expediente buscarExpedientePorCasoFolioInvolucrado(
			String numeroGeneralCaso, String folioInvolucrado)
			throws NSJPNegocioException;
	
	/**
     *	Buscar los numeros de expedientes mediante un expedienteID y que pertenece al
     *	area indicada por el filtro.
     * 
     * @param expedienteID
     * @param areaId
     * @return
     */
    List<NumeroExpediente> buscarNumeroExpedientes(Long expedienteID, Long areaId);
    
    
	/**
	 * Consultar el ExpedienteId al que se encuentra asociado el Número de Expediente
	 * por la cadena de numeroExpediente
	 * @param numExp
	 * @return
	 */
	Long consultarExpedienteIdPorNumeroExpediente(String numExp) ;
	
	
	String obtenerUltimoNumeroDeExpediente(Long area);

	public JerarquiaOrganizacional consultarOrigendeExpediente(Long expedienteId);

	/**
	 * Consulta los expedientes de estan ligados al numero de caso enviado como parametro.
	 * @param idCaso
	 * @return Lista de expedientes obtenidos
	 * @author CesarAgustin
	 */
	List<Expediente> consultarExpedientesPorIdCaso(Long idCaso);
	
	/**
     * Recupera los expedientes de acuerdo con los parametros de busqueda
     * El areaID es opcional para el Query
     * 
     * @param numeroExpediente
     * @return Lista de expedientes obetenidos
     */
    public List<Expediente> buscadorDeExpedientes(String numeroExpediente, Long areaId,Long discriminanteId,Long institucionId);
	
	
}

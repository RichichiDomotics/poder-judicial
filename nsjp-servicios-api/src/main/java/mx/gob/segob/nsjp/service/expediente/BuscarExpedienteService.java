/**
 *
 * Nombre del Programa : BuscarExpedienteService.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 05/04/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Interface del servicio para obtener expedientes                      
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

package mx.gob.segob.nsjp.service.expediente;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteUAVDDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Este caso de uso permitirá al usuario buscar expediente, según el criterio
 * seleccionado y los datos ingresados, tomando en cuenta la institución a la
 * que pertenece, el cargo e identificador del usuario.
 * 
 * @version 1.0
 * @author CesarAgustin
 * @version 1.0
 */
public interface BuscarExpedienteService {
    /**
     * Hace la búsqueda de expedientes a partir del filtro.
     * 
     * En caso de que requiere consultar todos los expedientes por Número de 
     * expediente, sin considerar el área al que pertenece el usuario, 
     * se debe mandar el filtro sin un usuario.
     *  
     * @param filtrosBusquedaExpediente
     *            Filtro de consulta.
     * @return Lista de expedientes que coincidan con el filtro.
     * @throws NSJPNegocioException
     *             En caso de que exista un problema al consultar.
     */
    public List<ExpedienteDTO> buscarExpedientes(
            FiltroExpedienteDTO filtrosBusquedaExpediente)
            throws NSJPNegocioException;
    
    /**
     * Hace la búsqueda de expedientes a partir del Numero de Expediente
     * y Discriminante 
     * En caso de que la institucion sea PJ no filtra por clave de usuario
     * (jerarquia organizacional), pero Si filtra por discriminante para todas
     * las instituciones 
     *  
     * @param filtrosBusquedaExpediente
     *            Filtro de consulta.
     * @return Lista de expedientes que coincidan con el filtro.
     * @throws NSJPNegocioException
     *             En caso de que exista un problema al consultar.
     */
    public List<ExpedienteDTO> buscarExpedientesPorNumExpDiscriminanteArea(
            FiltroExpedienteDTO filtrosBusquedaExpediente)
            throws NSJPNegocioException;
    
    

    /**
     * Consulta el detalle de un expediente en base a su identificador.
     * 
     * @param expedienteDTO
     *            Identificador del expediente en el sistema e identificador del
     *            area: <b>expedienteId</b> y <b>area.areaId</b> <br>
     * @return El expediente.
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error de negocio al consultar.
     */
    ExpedienteDTO obtenerExpediente(ExpedienteDTO expedienteDTO)
            throws NSJPNegocioException;
    
    /**
     * Consulta el detalle de un expediente en base a su identificador.
     * 
     * @param expedienteDTO
     *            Identificador del expediente en el sistema e identificador del
     *            area: <b>expedienteId</b> y <b>area.areaId</b> <br>
     * @return El expediente.
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error de negocio al consultar.
     */
    public ExpedienteDTO obtenerExpedientePorExpedienteId(ExpedienteDTO expedienteDTO)
            throws NSJPNegocioException;
    
    /**
     * Servicio que recupera el id del Expediente a partir del id del Número Expediente
     * @param expedienteDTO Requerido <b>numeroExpedienteId</b>.
     * @return expedienteId.
     * @throws NSJPNegocioException
     */
    Long obtenerExpedienteIdPorNumExpId(ExpedienteDTO expedienteDTO)
    throws NSJPNegocioException;
    
    /**
     * Consulta los expedientes de un caso para pintar el árbol de
     * casos-expedientes.
     * 
     * @param caso
     *            caso.<b>casoId</b> es requerido.
     * @return Una lista con los siguientes valores de expedientes asignados:
     *         <ul>
     *         <li>expedienteId</il>
     *         <li>numeroExpediente</il>
     *         </ul>
     * @throws NSJPNegocioException
     */
    List<ExpedienteDTO> consultarExpedientesPorCaso(CasoDTO caso)
            throws NSJPNegocioException;

    /**
     * Consulta los expedientes que contengan un involucrado con el alias
     * enviado como parametro
     * 
     * @param filtrosBusquedaExpediente
     * @return Lista de involucrados con su expedinete correspondiente
     * @throws NSJPNegocioException
     * 
     */
    public List<InvolucradoDTO> buscarExpedientesPorFiltros(
            FiltroExpedienteDTO filtrosBusquedaExpediente)
            throws NSJPNegocioException;
    /**
     * Consulta los expedientes que contengan un involucrado con el alias
     * enviado como parametro
     * 
     * @param filtrosBusquedaExpediente
     * @return Lista de involucrados con su expedinete correspondiente
     * @throws NSJPNegocioException
     * 
     */
    public List<InvolucradoDTO> buscarExpedientesPorFiltrosYDiscriminante(
            FiltroExpedienteDTO filtrosBusquedaExpediente,UsuarioDTO usuarioFirmado)
            throws NSJPNegocioException;

    /**
     * 
     * @param filtroExpedienteDTO
     * @return
     * @throws NSJPNegocioException
     */
    public List<ExpedienteDTO> consultarExpedienteActividadAreaAnio(
            FiltroExpedienteDTO filtroExpedienteDTO)
            throws NSJPNegocioException;
    
    /**
     * Consulta el detalle de un expediente en base a su identificador.
     * 
     * @param expedienteDTO
     *            Identificador del expdiente en el sistema e identificador del
     *            area: <b>expedienteId</b> y <b>area.areaId</b> <br>
     * @return El expediente.
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error de negocio al consultar.
     */	
    public DatosGeneralesExpedienteDTO obtenerDatosGeneralesExpediente(ExpedienteDTO expedienteDTO)
            		throws NSJPNegocioException;
					
    /**
     * Servicio que obtiene el expedienteDTO a partir del numero de expediente
     * @param numeroExpediente
     * @return ExpedienteDTO
     * @throws NSJPNegocioException
     */
    ExpedienteDTO obtenerExpedientePorNumeroExpediente(String numeroExpediente)throws NSJPNegocioException;
    
    /**
     * Consulta los expedientes que tienen al menos un evento (audiencia o recurso) con fecha de evento
     * entre la fecha actual y el periodo hacia atrás definido por el parámetro del sistmea de tiempo de 
     * consulta histórica y que sean de algún caso en particular
     * @param casoId caso a buscar
     * @param usuario Usuario que consulta
     * @return Lista de expediente con al menos un evento para el periodo histórico
     * @throws NSJPNegocioException
     */
    List<ExpedienteDTO> consultarExpedientesConEventosHistorico(Long casoId,UsuarioDTO usuario) throws NSJPNegocioException;

    /**
     * Obtiene el NumeroExpediente, de acuerdo al numero expediente enviado como parametro
     * @author cesarAgustin
     * @param expedienteDTO
     * 			-Cadena de numero de expediente
     * @return
     * @throws NSJPNegocioException
     */
    ExpedienteDTO obtenerNumeroExpedienteByNumExp(ExpedienteDTO expedienteDTO,UsuarioDTO usuario) throws NSJPNegocioException;
    

    /**
     * 
     * @return
     */
	public List<ExpedienteDTO> consultarNumeroExpedienteHistorico(UsuarioDTO usuario) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param tipoExpediente
	 * @param estatusExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarNumeroExpedienteByEstatus(
			TipoExpediente tipoExpediente, EstatusExpediente estatusExpediente) throws NSJPNegocioException;

	/**
	 * Consulta los expedientes relacionados al usuario de un area
	 * @author cesarAgustin
	 * @param usuarioDTO
	 * 			<li>idUsuario<li> identificador del usuario
	 * 			<li>areaActual<li> area del usuario 
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarExpedientesUsuarioArea(
			UsuarioDTO usuarioDTO) throws NSJPNegocioException;

	/**
	 * Consulta los expedientes de un área según los filtros dados. 
	 * 
	 * @param usuarioDTO: Identificador del usuario firmado y el área a que pertenece (Obligatorio)
	 * @param area: Identificador del área remitente
	 * @param estatusExpediente: Identificador del estado por el cual se filtran los expedientes
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarExpedientePorAreaEstatusRemitente(
			UsuarioDTO usuarioDTO, AreaDTO area,
			Long estatusExpediente)throws NSJPNegocioException;

	/**
     * Consulta los numerosExpediente del tipo Causa que tengan un numeroExpediente de tipo carpeta de investigacion 
     * en el estatus cerrado.
     * @author cesarAgustin
     * @return
     * @throws NSJPNegocioException
     */
	public List<ExpedienteDTO> consultarHistoricoCausasExpediente() throws NSJPNegocioException;

	/**
     * Consulta los numerosExpediente del tipo carpeta de ejecucion perteneciente a la causa enviada oomo parametro
     * @author cesarAgustin
     * @param expedienteDTO
     * @return
     * @throws NSJPNegocioException
     */
	public List<ExpedienteDTO> consultarCarpetasEjecucionPorCausa(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	
	/**
	 * Consulta numeros de expediente de cierto tipo (TOCA, CAUSA o CARPETA) en base a los filstros enviados como parametro
	 * si un filtro es nulo entonces no se considera en la cosulta
	 * @param fechaInicio Límite inferior para la fecha de apertura
	 * @param fechaFin Límite superior para la fecha de apertura
	 * @param usuario Usuario al cuál debe de pertenecer el expediente
	 * @param tipo Tipo de expediente
	 * @param numeroExpedientePadreId Si existe el parámetro se consultan los número de expedientes
	 * cuya causa padre sea la enviada en este parámetro
	 * @return Lista con los números de expediente encontrados
	 */
	List<ExpedienteDTO> consultarExpedientesPorFiltro(Date fechaInicio,Date fechaFin,FuncionarioDTO usuario,
			TipoExpediente tipo,Long numeroExpedientePadreId) throws NSJPNegocioException;
	
	/**
     * Consulta números de expediente asociados a cierto caso
     * @param caso
     * @return
     */
	List<ExpedienteDTO> consultarNumeroExpedientePorNumeroCaso(String caso);

	/**
	 * Obtiene numerosExpediente de un determinado tipo que se encuntren en un estatus especifico
	 * @author cesarAguistin
	 * @param tipoExpediente
	 * @param estatusExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarNumeroExpedienteByTipoYEstatus(
			TipoExpediente tipoExpediente, EstatusExpediente estatusExpediente,UsuarioDTO usuario) throws NSJPNegocioException;

	/**
	 *	Servicio que realiza la consulta por Usuario (Funcionario), Área y Estatus
	 *	El área se encuentra denstro del usuarioDTo
	 * 	En caso de que no pase el estatus, se consulta de todos los expedientes por estatus.
	 * 
	 * @param usuarioDTO
	 * @param expedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarExpedientesPorUsuarioAreaEstatus(
			UsuarioDTO usuarioDTO, Long estatus) throws NSJPNegocioException ;

	public ExpedienteDTO obtenerExpedienteDefensoria(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

	/**
	 * Obtiene la informacion del expediente si el funcionario tiene permisos sobre el numeroExpediente.
	 * @author cesarAgustin
	 * @param claveFuncionario
	 * @param numExpId
	 * @return
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO consultarNumExpPorFuncionarioYNumExp (Long claveFuncionario, Long numExpId) throws NSJPNegocioException;
	
	/**
	 * 
	 * @author cesarAgustin
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ExpedienteDTO> consultarNumExpPorFuncionario (Long claveFuncionario) throws NSJPNegocioException;

	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarExpedientesDelFuncionario(
			UsuarioDTO usuario) throws NSJPNegocioException;

	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ExpedienteDTO> consultarExpedientescConPermisoFuncionario(
			Long claveFuncionario) throws NSJPNegocioException;
	
	/**
	 *  Se hace la busqueda del Expediente al que se encuentra asociado, 
	 *  despues se hace la busqueda por el expedienteId y el Area del o los 
	 *  expedientes bajo ese criterio. Si se obtienen varios NEx por dicho filtro
	 *  se considera solo el primero.  
	 * 
	 * @param numeroExpediente
	 * @param areaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO consultarExpedienteRelacionadoAArea (String numeroExpediente, Long areaId  ) throws NSJPNegocioException;
	
    /**
     * Consulta el resumen con los datos generales de un expediente en base al identificador de la solicitud.
     * 
     * @param solicitudDTO
     *            Identificador de la solicitud
     * @return DatosGeneralesExpedienteUAVDDTO con el Resumen del expediente
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error de negocio al consultar.
     */    
	public DatosGeneralesExpedienteUAVDDTO obtenerResumenDeExpedienteUAVD(
			SolicitudDTO solicitudDTO) throws NSJPNegocioException;
	
	/**
	 * Servicio utilizado para obtener los expedientes recibidos de IPH, es decir, 
	 * remision IPH. 
	 * 
	 * @param estatusExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ExpedienteDTO> buscarRemisionesConIPH(EstatusExpediente estatusExpediente)throws NSJPNegocioException;
	
	
	/**
     * Consulta los expedientes de acuerdo a los filtros,
     * si las fechas de los filtros es null, no se tomará en cuenta,
     * los demas parametros son requeridos.
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param usuario
     * @param estatusExpediente
     * @return
     * @throws NSJPNegocioException
     */
	List<ExpedienteDTO> consultarExpedientesPorFiltroST(
			Date fechaInicio, Date fechaFin, UsuarioDTO usuario,
			Long estatusExpediente)throws NSJPNegocioException;

	/**
	 * Consultar los expedientes ligados al caso enviado como parametro, realizando la consulta por el idcaso
	 * @param caso
	 * @return
	 * @throws NSJPNegocioException
	 * @author CesarAgustin
	 */
	List<ExpedienteDTO> consultarExpedientesPorIdCaso(CasoDTO caso)
			throws NSJPNegocioException;
	
}

/**
* Nombre del Programa : SolicitudPericialDelegate.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del delegate para los metodos de comunicacion de Solicitud entre la vista y los servicios
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
package mx.gob.segob.nsjp.delegate.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de SolicitudPericial entre la vista y los servicios.
 * @version 1.0
 * @author rgama
 *
 */
public interface SolicitudPericialDelegate {
	
	/**
	 * Metodo que permite registrar a un Abogado Defensor una solicitud pericial
	 * Se registra la solicitud con un estatus de NUEVA
	 * @param solicitudPericialDTO
	 * @return SolicitudPericialDTO Regresa el identificador con el que se guardo la solicitud
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO registrarSolicitudPericialAD(SolicitudPericialDTO solicitudPericialDTO) throws NSJPNegocioException;
	
	/**
	 * Metodo que permite consultar el padre de una solicitud pericial
	 * @param documentoHijo
	 * @return documentoPadre: Regresa el identificador del padre de la solicitud pericial
	 * @throws NSJPNegocioException
	 */	
	public Long consultarPadreSolicitudPericial(
			Long documentoHijo)
		throws NSJPNegocioException;

	/**
	 * Metodo que permite registrar/actualizar a un Abogado Defensor una Solicitud Pericial
     * Se registra/actualiza la solicitud con un estatus de PENDIENTE
	 * @param solicitudPericialDTO
	 * @return SolicitudPericialDTO: Regresa el identificador con el que se guardo la solicitud o null en caso contrario
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO guardarParcialmenteSolicitudPericialAD(SolicitudPericialDTO solicitudPericialDTO)
	throws NSJPNegocioException;
	
	
	/**
	 * Método que permite consultar a un Coordinador de defensoria SolicitudesPericialesDTO de tipo DICTAMEN por estatus,
	 * los estatus  
	 * @param estatusSolicitud
	 * @return List<SolicitudPericialDTO>
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesCD(Long estatusSolicitud)
	throws NSJPNegocioException;
	
	/**
	 * Metodo que permite registrar a un Coordiandor Defensor una Solicitud Pericial
     * Se registra la solicitud con un estatus de NUEVA
	 */
	public SolicitudPericialDTO registrarSolicitudPericialCD(
			SolicitudPericialDTO solicitudPericialDTO)
			throws NSJPNegocioException;
	
	/**
	 * Metodo que permite registrar/actualizar a un Coordiandor Defensor una Solicitud Pericial
     * Se registra/actualiza la solicitud con un estatus de PENDIENTE
	 * @param solicitudPericialDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO guardarParcialmenteSolicitudPericialCD(SolicitudPericialDTO solicitudPericialDTO)
	throws NSJPNegocioException;
	

	/**
	 * Metodo que permite recuperar a un Coordinador de Periciales(Defensoria) las solicitudes hechas 
	 * por el Coordinador Defensor, por estatus:
	 * @param estatusSolicitud
	 * @return List<SolicitudPericialDTO>
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesCP(Long estatusSolicitud)
	throws NSJPNegocioException;
	

	
	/**
	 * Metodo que permite consultar Peritos por filtro
	 * @param filtro: permite configurar el objeto para poder filtrar por
	 * 
	 * - Identificador de personal operativo
	 * - Nombre
	 * - Apellido Materno
	 * - Apellido Paterno
	 * - Especialidad
	 * - CURP
	 * - RFC (con homoclave)
	 * - Correo electrónico (email))
	 * - Nombre de la organización a la que pertenece.
	 * - Puesto
	 * - Cédula profesional
	 * - Área
	 * - Institución
	 * @return List<PeritoDTO>
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionarioPorFiltro(FuncionarioDTO filtro)
			throws NSJPNegocioException;
	

	/**
	 * Método que realiza la funcionalidad de Registrar/Actualizar la información 
	 * de una Solicitud Pericial. 
	 * Registar Nueva Solicitud Pericial no se pasa el IdDocumento
	 * Actualizar Solicitud Pericial es necsario ingresar el IdDocumento
	 * 
	 * Es posible para Registrar/Actualizar la informacion de la solicitud Pericial para el caso de 
	 * pasar, o no, los siguientes parametros 
	 *  -Manejo de expediente (con un numero de expediente) para ser asignado a la solicitud
	 *  -Funcionario - Solicitante considerando el area a que pertence y el puesto del solicitante. 
	 *  
	 * Pendientes: 
	 * 1)El manejo de estatus de la solicitud.
	 * 2)Los atributos de documento y forma, que son necesario para guardar/actualiza la solicitud.
	 * 
	 * @param solicitudPericialDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudPericialDTO registrarActulizarSolicitudPericial(
			SolicitudPericialDTO solicitudPericialDTO)
			throws NSJPNegocioException;
	
	/**
     * Consulta una solicitud de servicio pericial en base al estatus de la solicitud
     * y el tipo de solicitud de pericial para cierto rol de destinatario
     * @param estado Estado a consultar
     * @param tipo tipo de solicitud a consultar
     * @param puesto - Puesto a filtrar en el destinatario
     * @return Lista de solicitudes de servicio encontradas
     * @throws NSJPNegocioException 
     */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Puestos puesto)
    throws NSJPNegocioException;
    
    /**
     * Consulta una solicitud de servicio pericial en base al estatus de la solicitud
     * y el tipo de solicitud de pericial para cierto rol de destinatario
     * @param estado Estado a consultar
     * @param tipo tipo de solicitud a consultar
     * @param area - area del destinatario
     * @return Lista de solicitudes de servicio encontradas
     * @throws NSJPNegocioException 
     */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorTipoEstatusAreaDestino(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Long area)
    throws NSJPNegocioException;
            
    /**
     * Consulta una solicitud pericial por identificador
     * @param documentoId ID de la solicitud pericial
     * @return solicitud encontrada
     */
	SolicitudPericialDTO consultarSolicitudPericialPorId(Long documentoId) throws NSJPNegocioException;

	/**
	 * Consulta una o mas solicitudes cuyo destinatario sea el funcionario enviado como parámetro y filtradas
	 * por el estatus deseado
	 * @param destinatario Destinatario de las solicitudes a consultar
	 * @param estatus Estado de la solicitud
	 * @return Listado de solicitudes encontradas
	 * @throws NSJPNegocioException
	 */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorEstatusYDestinatario(EstatusSolicitud estatus, 
    		FuncionarioDTO destinatario)
    throws NSJPNegocioException;
    /**
     * Consulta el último dictamen generado para la solicitud pericial, si no se tiene ningún dictamen
     * creado entonces se retorna nulo
     * @param solicitudPericialId Solicitud pericial para la cuál se busca el dictamen
     * @return ID del dictamen localizado, null si no se localiza
     * @throws NSJPNegocioException
     */
    Long consultarDictamenIdDeSolicitudPericial(Long solicitudPericialId) throws NSJPNegocioException;
    
    /**
	 * Crea un nuevo dictamen o informe que será asociado a una solicitud de pericial
	 * @param dictamen Datos de origen para el dictamen o informe y la solicitud de pericial
	 * asociada
	 * @return Objeto de dictamen origen con el ID del dictamen asociado
	 */
	DictamenDTO registrarDictamenParaSolicitudPericial(DictamenDTO dictamen) throws NSJPNegocioException;
	
	SolicitudDTO actualizarStatusSolicitudEvidencia(SolicitudDTO solicitudPericial) throws NSJPNegocioException;
	
	List<SolicitudDTO> designarPeritoASolicitud(Long idSolicitud, List<FuncionarioDTO> funcionarios) throws NSJPNegocioException;
	
	/**
	 * Crea una nueva solicitud pericial 
	 * Asignado las evidencias enviadas
	 * Se crean 2 SolicitudesPericiales
	 * 1 - Solicitud funcionario solicitante con estatus "EN PROCESO"
	 * 2 - Solicitud funcionario destinatario con estatus "ABIERTA"
	 * Se crean las relaciones en la tabla Documento_Elemento 
	 * @param List<EvidenciaDto> Evidencia a asignar
	 * @param SolicitudPericial solicitud inicial para la cual se debe buscar el perito
	 * donde se va a relacionar la evidencia
	 * @throws NSJPNegocioException 
	 * @throws Exception 
	 */
	
	Long asignarEvidenciaASolicitudPericial(List<EvidenciaDTO> evidencias, SolicitudPericialDTO solicitud) throws NSJPNegocioException, Exception;
	
	/**
	 * Asigna la soicitud pericial a un Perito
	 * Se crean 2 SolicitudesPericiales
	 * 1 - Solicitud para el funcionario solicitante (Coordinador de periciales) con estatus "EN PROCESO"
	 * 2 - Solicitud para el funcionario destinatario (El perito) Con estatus "ABIERTA" 
	 * @param Long idPerito: El id del funcionario destinatario (Perito)
	 * @param Long idSolicitud: El id de la solicitud que será asignada al perito
	 * @throws NSJPNegocioException 
	 * @throws Exception 
	 */
	public Long asignarFuncionarioASolicitud(Long idPerito, Long idSolicitud) throws NSJPNegocioException, Exception;
	

    /**
     * Finaliza una solicitud de pericial que ya ha sido atendida por un perito, esta función debe ser invocada
     * una vez generado el dictamen o informe.
     * El archivo digital recién generado va a ser adjuntado a las solicitud padre del coordinador de periciales
     * y a la solicitud origen del AMP
     * También se actualizarán los estados de las solicitudes a CERRADA
     * La solicitud origen de AMP se cambia a solicitud en proceso
     * @author Emigdio Hernández
     * @param solicitudId Identificador de la solicitud a finalizar
     */
    void finalizarSolicitudPericial(Long solicitudId);
    
    /**
     * Consulta una lista de solicitudes de pericial enviadas por cierto perfil y relacionadas a cierto
     * numero de Causa/Expediente y con ciertos est atus
     * @param puesto Puesto del solicitante buscado
     * @param estados Estados buscados en la solicitud
     * @param numeroExpedienteId Id del número de expediente
     * @author Emigdio Hernández
     * @return Lista de solicitudes de pericial encontradas
     */
    List<SolicitudPericialDTO> consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(Puestos puesto,
    		EstatusSolicitud[] estados,Long numeroExpedienteId) throws NSJPNegocioException;
	
}

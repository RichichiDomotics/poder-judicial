package mx.gob.segob.nsjp.service.solicitud;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;


/**
 * Nombre del Programa : SolicitarPericialService.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 03 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para realizar la solicitud pericial
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

/**
 * Contrato del servicio para realizar la solicitud pericial.
 * @version 1.0
 * @author rgama
 *
 */
public interface SolicitarPericialService {

	/**
	 * Metodo que permite registrar una solicitud pericial
	 * @param solicitudPericialDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO registrarSolicitudPericial(
			SolicitudPericialDTO solicitudPericialDTO)
			throws NSJPNegocioException;
	
	/**
	 * Metodo que permite consultar el padre de una solicitud pericial
	 * @param documentoHijo
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long consultarPadreSolicitudPericial(
				Long documentoHijo)
			throws NSJPNegocioException;
	
	/**
	 * Método que permite guardar/actualizar la solicitud pericial así como el estado de la solicitud a PENDIENTE
	 * @param solicitudPericialDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO guardarParcialmenteSolicitudPericial(SolicitudPericialDTO solicitudPericialDTO)
	throws NSJPNegocioException;
	

	/**
	 * Metodo que permite consultar Solicitudes Periciales de Dictamen
	 * @param estatusSolicitud:Indica el estatus de la solicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesDeDictamen(Long estatusSolicitud)
	throws NSJPNegocioException;
	
	/**
     * Metodo que permite registrar a un Coordiandor Defensor una Solicitud Pericial
     * Así como el estado de la solicitud a NUEVA
     * @param solicitudPericialDTO
     * @return
     * @throws NSJPNegocioException
     */    
    public SolicitudPericialDTO registrarSolicitudPericialCD(SolicitudPericialDTO solicitudPericialDTO)
            throws NSJPNegocioException;
    
    /**
     * Metodo que permite registrar/actualizar a un Coordiandor Defensor una Solicitud Pericial
     * Así como el estado de la solicitud a PENDIENTE
     * @param solicitudPericialDTO
     * @return
     * @throws NSJPNegocioException
     */
    public SolicitudPericialDTO guardarParcialmenteSolicitudPericialCD(
            SolicitudPericialDTO solicitudPericialDTO)
            throws NSJPNegocioException;
    
    /**
     * Metodo que permite recuperar a un Coordinador de Periciales(Defensoria) las solicitudes hechas por el Coordinador Defensor
     */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesCP(Long estatusSolicitud)
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
     * @param area - area a filtrar en el destinatario
     * @return Lista de solicitudes de servicio encontradas
     * @throws NSJPNegocioException 
     */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorTipoEstatusAreaDestinatario(TiposSolicitudes tipo,
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



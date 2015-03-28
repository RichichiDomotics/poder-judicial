/**
 * Nombre del Programa : AudienciaDelegate.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del delegate para las audiencias
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
package mx.gob.segob.nsjp.delegate.audiencia;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.DiaDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.MesDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioAudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoAudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del delegate para las audiencias.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AudienciaDelegate {
    /**
     * Consulta los eventos pendientes de notificar.
     * 
     * @param input
     *            Obligatorio uno de los dos campos: <b>bandeja</b> o
     *            <b>expediente.expedienteId</b> Filtro.
     * @return Lista de eventos a notificar
     * @throws NSJPNegocioException
     */
    List<EventoDTO> consultarEventosParaNotificar(EventoDTO input)
            throws NSJPNegocioException;

    /**
     * Obtiene un evento.
     * 
     * @param input
     *            requerido: <b>id</b> y <b>tipoEvento</b>.
     * @return
     * @throws NSJPNegocioException
     */
    EventoDTO obtenerEvento(EventoDTO input) throws NSJPNegocioException;

    /**
     * Consulta las solicitudes de audiencia.
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    List<SolicitudAudienciaDTO> consultarSolicitudesAudiencia(UsuarioDTO filtro)
            throws NSJPNegocioException;

    /**
     * Consulta las solicitudes <b>diferentes</b> a las de audiencia.
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    List<SolicitudDTO> consultarOtrasSolicitudes(UsuarioDTO filtro)
            throws NSJPNegocioException;

    /**
     * Obtiene la disponibilidad del mes.
     * 
     * @param filtro
     * @return
     */
    MesDisponibilidadDTO consultarDisponibilidadPorMes(
            MesDisponibilidadDTO filtro) throws NSJPNegocioException;
  
    /**
     * Obtiene la disponibilidad del dia.
     * 
     * @param filtro
     * @return
     */
    DiaDisponibilidadDTO consultarDisponibilidadPorDia(
            DiaDisponibilidadDTO filtro) throws NSJPNegocioException;

    /**
     * Obtiene le número de salas.
     * 
     * @param usr
     * @return
     * @throws NSJPNegocioException
     */
    List<SalaAudienciaDTO> obtenerNombresSalas(UsuarioDTO usr) throws NSJPNegocioException;

    /**
     * 
     * @param encargadoSala
     * @return
     * @throws NSJPNegocioException
     */
    List<AudienciaDTO> buscarAudiencias(FiltroAudienciaDTO filtro)
            throws NSJPNegocioException;

    /**
     * Busca y regresa las audiencias para las cuales no se haya terminado de generar los documentos pertinentes
     * a las resoluciones que se almacenaron una vez terminada la audiencia
     * @return
     * @throws NSJPNegocioException
     */
    List<AudienciaDTO> buscarAudienciasSinTranscripcionResolutivos() throws NSJPNegocioException;
    
    /**
     * Obtiene una audiencia a partir del id.
     * 
     * @param input
     *            Obligatorio: <b>id</b>.
     * @return
     * @throws NSJPNegocioException
     */
    AudienciaDTO obtenerAudiencia(AudienciaDTO input)
            throws NSJPNegocioException;

    /**
     * Obtiene una audiencia en base al id de la solicitud.
     * 
     * @param solAudIn
     *            obligatorio <b>documentoId</b>.
     * @return
     * @throws NSJPNegocioException
     */
    AudienciaDTO obtenerAudiencia(SolicitudAudienciaDTO solAudIn)
            throws NSJPNegocioException;

    /**
     * Registra una solicitud, el valor <b>tipoSolicitudDTO</b> es el indicador
     * del tipo de objeto que se debe enviar.
     * 
     * 
     * @param input
     *            Datos a guardar: <lu> <li>SolicitudDTO para el tipo audio y
     *            video, transcripción de audiencia...</li> <li>
     *            SolicitudAudienciaDTO para el audiencia, debe incluir
     *            AudienciaDTO</li> </lu>
     * @return
     */
    public SolicitudDTO registrarSolicitud(SolicitudDTO input)
            throws NSJPNegocioException;
    
    /**
     * Asigna la sala temporal a la audiencia, los pasos que ejecuta son:
     * <ol>
     * <li>Registra la sala temporal</li>
     * <li>Hace la asociación de la audiencia con la sala temporal</li>
     * <li>Asigna la fecha y hora de la audiencia</li>
     * </ol>
     * 
     * @param audConSala
     *            <code>sala</code> con los siguientes valores de la sala:
     *            <ul>
     *            <li>domicilioSala</li>
     *            <li>ubicacionSala</li>
     *            <li>motivo</li>
     *            </ul>
     *            Los valores de audiencia:
     *            <ul>
     *            <li>id</li>
     *            <li>fechaEvento</li>
     *            <li>duracionEstimada</li>
     *            </ul>
     * @throws NSJPNegocioException en caso de error.
     */
    void asignarSalaTemporal(AudienciaDTO audConSala)
            throws NSJPNegocioException;

    /**
	 * Consulta los jueces disponibles para una cierta fecha y que estén desocupados durante el 
	 * toda la duración estimada de la audiencia.
	 * 
	 * @param fecha fecha y hora de la audiencia
	 * @param duracionEstimada Duración estimada en minutos	
	 * @return Lista de jueces disponibles para la audiencia a programar
	 */
	List<FuncionarioDTO> consultarJuecesDisponiblesParaFechaYHoraAudiencia(Date fecha,Integer duracionEstimada, FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

	/**
	 * Consulta una lista de jueces disponibles para una audiencia a cierto día y cierta hora con cierta duración estimada
	 * retornando el juez con menor carga de trabajo.
	 * @param fecha Fecha y hora de la audiencia
	 * @param duracionEstimada Duración en minutos estimada de la audiencia
	 * @return Juez o jueces elegidos, Lista vacía si no hay jueces disponibles
	 * @throws NSJPNegocioException 
	 */
	List<FuncionarioDTO> consultarJuezAutomaticoADesignar(Date fecha,Integer duracionEstimada,AudienciaDTO audiencia,boolean juezSustituto, FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
	
	/**
	 * Almacena los cambios hechos a la audiencia:
	 * Fecha, Hora, duración estimada
	 * Sala o Sala temporal
	 * Juez o jueces asignados
	 * Genera las notificaciones a los funcionarios involucrados y 
	 * actualiza la carga de trabajo de los funcionarios jueces de la audiencia
	 * @param audiencia Datos fuente
	 */
	void guardarProgramacionAudiencia(AudienciaDTO audiencia) throws NSJPNegocioException;
	
	
	/**
	 * Registra un elemento en una audiencia para su enunciación durante la misma:
	 * @param audiencia para la que se registra el elemento
	 * @param elemento que será registrado en la audiencia
	 */
	public void registrarTestigoEnAudiencia(AudienciaDTO audiencia, InvolucradoDTO testigo) throws NSJPNegocioException;
	
	/**
	 * Registra un Objeto en una audiencia para su enunciación durante la misma:
	 * @param audiencia para la que se registra el Objeto
	 * @param Objeto que será registrado en la audiencia
	 */
	public void registrarObjetoEnAudiencia(Long audienciaId, Long insitutcion,
			String descripcion, Long condicionFisica, String noCustodia,
			Long noPrueba) throws NSJPNegocioException;
	
	
	/**
	 * Registra los resolutivos generados durante la audiencia y los asocia a la misma
	 * @param audiencia para la que se registran los resolutivos
	 * @param listado de resolutivos que serán registrado en la audiencia
	 * @return 
	 */
	public Long registrarResolutivoAudiencia(ResolutivoDTO resolutivo) throws NSJPNegocioException;

	/**
	 * Modifica los resolutivos generados durante la audiencia y los asocia a la misma
	 * @param audiencia para la que se registran los resolutivos
	 * @param listado de resolutivos que serán registrado en la audiencia
	 */
	public void modificarResolutivoAudiencia(ResolutivoDTO resolutivo) throws NSJPNegocioException;

	/**
	 * Elimina los resolutivos generados durante la audiencia y los asocia a la misma
	 * @param audiencia para la que se registran los resolutivos
	 * @param listado de resolutivos que serán registrado en la audiencia
	 */
	public void eliminarResolutivoAudiencia(ResolutivoDTO resolutivo) throws NSJPNegocioException;
	
	/**
	 * Consultalos resolutivos generados durante la audiencia
	 * @param audiencia para de la cual se desean consultar los resolutivos
	 * @param listado de resolutivos registrados en la audiencia
	 */
	public List<ResolutivoViewDTO> leerResolutivosAudiencia(Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar las Audiencias por un estatus de la Audiencia
	 * 
	 * @param estatus
	 * @return Devuelve un listado de Audiencias que cumplen con el criterio.
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasPorEstatus(Long estatus)throws NSJPNegocioException;
	
	/**
	 * Consulta las audiencias que cumplen con el estatus definido por audiencia.estatus y que se encuentran
	 * entre las fechas definidas por audiencia.fechaFiltroInicio y audiencia.fechaFiltroFin y que se encuentran
	 * registradas en la base de datos de PODER JUDICIAL.
	 * @param audiencia Parametros para la busqueda de las audiencias en poder judicial
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasFromPoderJudicial(AudienciaDTO audiencia)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar los resolutivos de una audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia 
	 * @return Devuelve un listado de textos que corresponden a los resolutivos.
	 * @throws NSJPNegocioException
	 */
	List<ResolutivoDTO> consultarResolutivosAudiencia(Long idAudiencia)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de marcar la Audiencia con la transcripción del resolutivo de audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia.
	 * @return Devuelve un valo boleano, verdadero en caso de que haya realizado la marca, falso en caso contrario
	 * @throws NSJPNegocioException
	 */
	void marcarAudienciaResolutivos(Long idAudiencia)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de asignar el juez a la audiencia.
	 * 
	 * @param audiencia
	 * @param jueces
	 * @return Devuelve un valor boleano, verdadero en caso de que se haya asignado el juez a la audiencia, 
	 * 										falso en caso contrario.
	 * @throws NSJPNegocioException
	 */
	void asignarJuezAudiencia(AudienciaDTO audiencia, List<FuncionarioDTO> jueces)throws NSJPNegocioException;

	List<EvidenciaDTO> consultarObjetosAudiencia(EventoDTO input)
			throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de consultar el tipo de audiencia asociado al identificador de la audiencia.
	 * 
	 * @param audiencia: El identificador de la audiencia.
	 * @return Devuelve el tipo de audiencia asociado al identificador de la audiencia, en  caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	ValorDTO consultarTipoAudienciaPorIdentificador(AudienciaDTO audiencia)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar la complejidad de una audiencia.
	 * @param audiencia: El identificador de la audiencia.
	 * @return Devuelve la complejidad de la audiencia asociada al identificador de la audiencia, en  caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	ValorDTO consultarComplejidadAudiencia(AudienciaDTO audiencia)throws NSJPNegocioException;
	
	/**
	 * Servicio que actualiza la complejidad de un Tipo de Audiencia
	 * @param tipoAudiencia Valor de Identificador del Tipo de Audiencia al que se le actualizará la complejidad
	 * @param complejidad nuevo valor de complejidad para el tipo de audiencia definido por <b>tipoAudiencia</b>
	 * @throws NSJPNegocioException
	 */
	public void actualizarComplejidadAudiencia(Long tipoAudiencia, Long complejidad) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de calcular la carga de trabajo de la audiencia, mediante la siguiente fórmula:
	 * 
	 * Carga de trabajo = complejidad de la audiencia + ln (sumatoria relaciones de probables responsable - delito)
	 *  
	 * @param complejidadAudiencia
	 * @param sumatoriaPRDelito
	 * @return Devuelve la carga de trabajo de la audiencia, en  caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	Double calcularCargaTrabajo(Long complejidadAudiencia, Long sumatoriaPRDelito)throws NSJPNegocioException;

	Double calcularCargaTrabajo(AudienciaDTO audienciaDTO)throws NSJPNegocioException;

	void finalizarAudiencia(AudienciaDTO audiencia) throws NSJPNegocioException;

	AudienciaDTO consultarAudienciaBySolicictudTranscripcionId(SolicitudDTO solicitud) throws NSJPNegocioException;
	/**
	 * Operación que realiza la funcionalidad de validar si la audiencia tiene registradas pruebas.
	 * @param audienciaDTO: el identificador de la audiencia.
	 * @return Devuelve un boleano, verdadero en caso de que la audiencia tenga pruebas asociadas, en caso contrario, regresa falso.
	 * @throws NSJPNegocioException
	 */
	Boolean validarExistenciaPruebas(AudienciaDTO audienciaDTO)throws NSJPNegocioException;
	
	/**
	 * @author cesarAgustin
	 * Consulta las audiencia de un determinado tipo, y que la fecha de audiencia es de la fecha actual en adelante.
	 * @param aunAudienciaDTO
	 * 			-Fecha de audiencia <li>fechaEvento<li> 
	 * @param tipoAudiencia
	 * 			-Tipo de la audiencia a consltar
	 * @return Lista de audiencias recuperadas
	 * @throws NSJPNegocioException
	 */
	public List<AudienciaDTO> consultarAudienciasByTipoYFecha (AudienciaDTO aunAudienciaDTO, TipoAudiencia tipoAudiencia,UsuarioDTO usuario) throws NSJPNegocioException;
	
	/**
	 * Obtiene una audiencia de acuerdo al numero de la audiencia capturado por el usuario
	 * @param audienciaDTO
	 * 			-Numero de la audiencia <li>consecutivo<li>
	 * @return Audiencia obtenida
	 * @throws NSJPNegocioException
	 */
	public AudienciaDTO obtenerAudienciaByNumeroAudiencia (AudienciaDTO audienciaDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentrn en un estatus determinado.
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * 		 	-Identificador del numeroExpediente del cual se requieren las Audiencias <li>numeroExpedienteId<li>
	 * @param estatusAudiencia
	 * 			-Estatus en el cual se requieren las Audiencias en la consulta
	 * @return Lista de audiencias en el estatus requerido y asociadas al numeroExpediente deseado.
	 * @throws NSJPNegocioException
	 */
	public List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYEstatus(ExpedienteDTO expedienteDTO, EstatusAudiencia estatusAudiencia) throws NSJPNegocioException;
	
	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentran en un estatus determinado.
	 * @author AlejandroGA
	 * @param expedienteDTO
	 * 		 	-Identificador del numeroExpediente del cual se requieren las Audiencias <li>numeroExpedienteId<li>
	 * @param estatusAudiencia[]
	 * 			-Estatus en el cual se requieren las Audiencias en la consulta
	 * @return Lista de audiencias en el estatus requerido y asociadas al numeroExpediente deseado.
	 * @throws NSJPNegocioException
	 */
	public List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYListaEstatus(ExpedienteDTO expedienteDTO, EstatusAudiencia[] estatusAudiencia) throws NSJPNegocioException;
	
	/**
	 * Consulta una lista de audiencias que tengan al menos una solicitud asociada que este en algunos de los estatus
	 * enviados como parámetro y que sea de alguno de los tipos enviados como parámetro.
	 * Las solicitudes asociadas son los objetos del tipo
	 * SolicitudTranscripcion, en este objeto se guardan las solicitudes de transcripción y las solicitudes
	 * de audio y video
	 * @param tipos Tipos de la solicitudes asociadas a las audiencias buscadas
	 * @param estados Estados de las solicitudes asociadas a las audiencias buscadas
	 * @author Emigdio Hernández
	 * @return Lista de audiencias que cumplen con las condiciones descritas
	 */
	
	List<AudienciaDTO> consultarAudienciasConSolicitudesPorTipoYEstado(TiposSolicitudes []tipos,EstatusSolicitud []estados,UsuarioDTO usuario) throws NSJPNegocioException;
	
	/**
	 * Consulta las audiencias de acuerdo a:
	 * @param tipoAudiencia
	 * @param estatusAudiencia
	 * @author cesarAgustin
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasByTipoYEstatus (TipoAudiencia tipoAudiencia, EstatusAudiencia estatusAudiencia) throws NSJPNegocioException;
	
	/**
	 * Solicita las audiencias programadas entre las fechas inico y fin de audiencia y del tipo seleccionado.
	 * @param audiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<AudienciaDTO> consultarAudienciasByFechasyEstatus(AudienciaDTO audiencia) throws NSJPNegocioException;

	
	/**
	 * Solicita la informacicón de una audiencia by id
	 * @param audiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	public AudienciaDTO consultarAudienciaFromPoderJudicial(AudienciaDTO audiencia) throws NSJPNegocioException;

	/**
	 * Obtiene las audienciad de un tipo determindo y de acuerdo a un rango de fechas de apertura del numero
	 * expediente relacionado
	 * @author cesarAgustin
	 * @param fechaInicial
	 * @param fechaFin
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Object[]> obtenerAudienciasJudicializadasPorMes(Date fechaInicial,
			Date fechaFin) throws NSJPNegocioException;
	/**
	 * 
	 * @param funcionario Obligatorios: puesto y especialidad. Opcionales a)nombreFuncionario, apellidoPaternoFuncionario y apellidoMaternoFuncionario b)claveFuncionario  
	 * @param audiencia Obligatorio: id
	 * @throws NSJPNegocioException
	 */
	void ingresarFuncionarioAudiencia(FuncionarioDTO funcionario, AudienciaDTO audiencia) throws NSJPNegocioException;	
	
	/**
	 * Registrar la asistencia o inasistencia de un involucrado en una audiencia
	 * @param involucradoId EL id único del involucrado en audiencia
	 * @param audienciaId ID de la audiencia
	 * @param presente Presente/Ausente
	 */
	void registrarAsistenciaInvolucrado(Long involucradoId, Long audienciaId, boolean presente) throws NSJPNegocioException;
	
	
	/**
	 * Registrar la asistencia o inasistencia de un funcionario en una audiencia
	 * @param claveFuncionario EL id único del funcionario en audiencia
	 * @param audienciaId ID único de la audiencia
	 * @param presente Presente/Ausente
	 */
	void registrarAsistenciaFuncionario(Long claveFuncionario, Long audienciaId ,boolean presente, Boolean esTitular) throws NSJPNegocioException;
	
	/**
	 * Consultar la asistencia o inasistencia de un funcionario en una audiencia
	 * @param claveFuncionario EL id único del funcionario en audiencia
	 * @param audienciaId ID único de la audiencia
	 */
	FuncionarioAudienciaDTO consultarAsistenciaFuncionario(Long claveFuncionario, Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Consultar la asistencia o inasistencia de un involucrado en una audiencia
	 * @param claveFuncionario EL id único del funcionario en audiencia
	 * @param audienciaId ID único de la audiencia
	 */
	InvolucradoAudienciaDTO consultarAsistenciaInvolucradoAudiencia(Long claveInvolucradoAudiencia, Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Asocia un involucrado existente a una audiencia existente, si 
	 * el involucrado ya está asociado a la audiencia no envía error, se hace una validación primero
	 * @param involucradoId ID del involucrado a asociar
	 * @param AudienciaId ID de la audiencia a asociar
	 * @throws NSJPNegocioException
	 */
	void asociarInvolucradoAAudiencia(Long involucradoId,Long AudienciaId) throws NSJPNegocioException; 
	
	/**
	 * Servicio que crea una audiencia vacía a partir de una audiencia
	 * @param audienciaDTO: audienciaId 
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long crearAudienciaSiguiente(AudienciaDTO audienciaDTO)throws NSJPNegocioException;
	
	/**
	 * Servicio que se encarga de consultar la lista de solicitudes siguientes, de acuerdo a 
	 * la audiencia actual y a la clasificación de grupos que se define en la misma clase.
	 * 
	 * @param audienciaActual
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatalogoDTO> consultarTipoSolicitudAudienciaSiguientes(TipoAudiencia audienciaActual ) throws NSJPNegocioException ;
	
	/**
	 * Se conecta al Servidor del JAVS para agendar la audiencia.
	 *  
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long agendarAudiencia(Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para reagendar la audiencia.
	 *  
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long reagendarAudiencia(Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para consultar la audiencia.
	 *  
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long consultarAudiencia(Long idAudiencia) throws NSJPNegocioException;
	
	
	/**
	 * Se conecta al Servidor del JAVS para eliminar la audiencia.
	 *  
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long eliminarAudiencia(Long idAudiencia) throws NSJPNegocioException;
	
	/**
	 * Relaciona un documento a una audiencia, para posteriormente porder consultarlos
	 * @param audienciaDTO
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO asociarDocumentoAAudiencia(AudienciaDTO audienciaDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException;
	
	/**
	 * Permite cancelar una Audiencia acutualizando su estatus a CANCELADA
	 * @param idAudiencia
	 * @throws NSJPNegocioException
	 */
	public Long cancelarAudiencia(Long idAudiencia)	throws NSJPNegocioException;

	/**
	 * Permite cancelar una Audiencia acutualizando su estatus a CANCELADA
	 * @param idAudiencia
	 * @throws NSJPNegocioException
	 */
	public Long cancelarAudienciaSistema(Long idAudiencia)	throws NSJPNegocioException;

	// Eliminar audiencia en JAVS	
	public boolean cancelarAudienciaJAVS(Long idAudiencia) throws NSJPNegocioException;

	
	/**
	 * COnsultar solicitudes de Audiencia con criterios
	 * @param solicitudAudienciaDTO
	 * @param lstIdEstatusSolicitud
	 * @param lstIdTipoSolicitud
	 * @param lstIdTipoAudiencia
	 * @param tipoConsulta
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<SolicitudAudienciaDTO> consultarSolicitudesAudienciaConCriterios (
			SolicitudAudienciaDTO solicitudAudienciaDTO,
			List<Long> lstIdEstatusSolicitud, List<Long> lstIdTipoSolicitud,
			List<Long> lstIdEstatusAudiencia, List<Long> lstIdTipoAudiencia,
			String tipoConsulta
			) throws NSJPNegocioException; 	
}

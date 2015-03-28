/**
 * Nombre del Programa : DocumentoDelegate.java
 * Autor                            : Emigdio Hern�ndez
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de metodos para operar los servicios relativos a un Documento
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
package mx.gob.segob.nsjp.delegate.documento;

import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
 
/**
 * Contrato de metodos para operar los servicios relativos a un Documento
 * 
 * @author Emigdio Hern�ndez
 * 
 */
public interface DocumentoDelegate {

	/**
	 * Consulta un documento del tipo {@code tipoDocumento} asociado al numero
	 * de expediente indicado por {@code expedienteDto.numeroExpediente}.
	 * 
	 * @param expedienteDto
	 *            Indica el numero de expediente del que buscamos su documento
	 *            asociado.
	 * @param tipoDocumento
	 *            El tipo del documento que estamos buscando.
	 * @return El documento asociado a los parametros de busqueda o {@code null}
	 *         en caso de no existir un documento asociado del tipo indicado.
	 * @throws NSJPNegocioException
	 *             En caso que alguno de los siguientes sea nulo:
	 *             <ol>
	 *             <li> {@code expedienteDto}
	 *             <li> {@code expedienteDto.numeroExpediente}
	 *             <li> {@code tipoDocumento}
	 *             </ol>
	 */
	DocumentoDTO consultarDocumentoXExpediente(ExpedienteDTO expedienteDto,
			Long tipoDocumento) throws NSJPNegocioException;

	
	/**
	 * Actualiza los datos de una nota. Actualiza la nota con los parametros
	 * distintos de {@code null} que vengan dentro de la nota y asigna actualiza
	 * automaticamente la nota con la fecha actual del sistema.
	 * 
	 * @throws NSJPNegocioException
	 *             En caso que "{@code notaDto}" o "{@code notaDto.idNota}" sean
	 *             {@code null}.
	 */
	
	void actualizarNota(NotaDTO notaDto) throws NSJPNegocioException;

	/**
	 * Asocia una nueva nota a un documento. La nota NO DEBE TENER un id
	 * asignado y el documento debe existir en el sistema.
	 * <p/>
	 * El sistema asigna automaticamente una fecha de creacion y una fecha de
	 * actualizacion con la fecha del sistema.
	 * 
	 * @param notaDto
	 *            Una nueva nota que sera asociada a un documento ya existente.
	 * @param documentoDto
	 *            Un documento ya existente en el sistema al cual se le asociara
	 *            una nueva nota.
	 *            <p/>
	 * @throws NSJPNegocioException
	 *             Si se cumple cualquiera de los siguientes casos:<br/>
	 *             "{@code notaDto == null}", "{@code notaDto.idNota != null}",
	 *             "{@code documentoDto == null}" o "
	 *             {@code documentoDto.documentoId == null}"
	 */
	void asociarNotaADocumento(NotaDTO notaDto, DocumentoDTO documentoDto)
			throws NSJPNegocioException;

	/**
	 * Consulta los Documentos que est�n asociados a un Usuario
	 * 
	 * @param usuarioDto
	 *            El usuario del que se consultan sus documentos
	 * @param tipoDocumento
	 *            El tipo de documento por el que se filtraran los documentos
	 *            para la consultar. (Dictamen / Informe) asociados.
	 * @return El listado de documentos asociados al Usuario. Si el usuarioDto
	 *         no existe en la base de datos o si no tiene documentos asociados,
	 *         se regresa la lista vacia.
	 * @throws NSJPNegocioException
	 *             En caso que alguno de: "{@code usuarioDto}", "
	 *             {@code tipoDocumento}", "{@code usuarioDto.funcionario}" o "
	 *             {@code usuarioDto.funcionario.claveFuncionario}" sean
	 *             {@code null}.
	 */
	List<DocumentoDTO> consultarDocumentoPorUsuario(UsuarioDTO usuarioDto,
			Long tipoDocumento) throws NSJPNegocioException;

	/**
	 * Crea un nuevo documento con la plantilla del tipo de forma enviada como
	 * par�metro y llena la plantilla con los datos del expediente enviado, si
	 * ya existe un documento que ha sido guardado parcialmente correspondiente
	 * a ese expediente y con ese tipo de forma carga los datos parcialmente
	 * guardados.
	 * 
	 * @param expediente
	 *            Expediente para el cu�l se realiza el documento
	 * @param tipoForma
	 *            Tipo de forma deseada del documento
	* @param parametrosExtras conjunto de valores llave-valor que representan par�metros extras que no ext�n definidos en la configuraci�n
	 * de campos a reemplazar pero que sin embargo la plantilla los puede contener, se utiliza para incluir informaci�n en la plantilla
	 * que no est� directamente relacionada con un expediente como por ejemplo: solicitudes, acuses de recibo y otros documentos operativos
	 * @return Objeto de documento con el formato HTML listo para presentarse
	 */
	DocumentoDTO cargarDocumentoPorExpedienteYForma(ExpedienteDTO expediente,
			FormaDTO tipoForma,Map<String,Object> parametrosExtras) throws NSJPNegocioException;
	/**
	  * Variante de <code>cargarDocumentoPorExpedienteYForma(ExpedienteDTO expediente,FormaDTO tipoForma,Map<String, Object> parametrosExtras)</code>
	  * que no utiliza los par�metros extras
	  * @param expediente Expediente para el cu�l se realiza el documento
	  * @param tipoForma Tipo de forma deseada del documento
	  * @return Objeto de documento con el formato HTML listo para presentarse
	  * @throws NSJPNegocioException
	  */
	DocumentoDTO cargarDocumentoPorExpedienteYForma(ExpedienteDTO expediente,
			FormaDTO tipoForm) throws NSJPNegocioException;

	/**
	 * Almacena el contenido de un archivo digital cargado o generado en la
	 * aplicaci�n
	 * 
	 * @param archivo
	 *            Datos del archivo a guardar
	 * @return El indentificador �nico de archivo generado
	 */
	Long guardarArchivoDigital(ArchivoDigitalDTO archivo)
			throws NSJPNegocioException;

	/**
	 * Almacena los cambios de un Documento, en caso de que el documento ya
	 * cuente con ID entonces lo crea y devuelve el ID generado
	 * 
	 * @param documento
	 *            Documento a guardar
	 * @param expedienteActual
	 *            expediente para el cu�l se almacenar� el documento
	 * @return ID del documento creado/actualizado
	 */
	Long guardarDocumento(DocumentoDTO documento, ExpedienteDTO expedienteActual)
			throws NSJPNegocioException;

	/**
	 * Consulta una Forma en base a su identificador
	 * 
	 * @param formaId
	 *            Identificador de la forma
	 * @return Objeto DTO con los datos de la forma
	 */
	FormaDTO buscarForma(Long formaId) throws NSJPNegocioException;

	/**
	 * Crea un objeto <code>ExpedienteResumenDTO</code> en base a los datos de
	 * un expediente. En este objeto se coloca la estructura resumida y
	 * organizada del expediente para que sea mas f�cil consumirlo en la capa de
	 * vista
	 * 
	 * @param expediente
	 *            Expediente a cargar
	 * @return Datos completos y acomdados del expediente
	 */
	ParametrosDocumentoDTO cargarResumenExpedienteParaDocumento(
			ExpedienteDTO expediente) throws NSJPNegocioException;

	/**
	 * Consulta los documentos y obtiene los que estan asociados a un expediente
	 * y al area del usuario
	 * 
	 * @param expedienteDTO
	 * @param usuario
	 * @return lista de documentos del expediente
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosExpediente(
			ExpedienteDTO expedienteDTO, UsuarioDTO usuario)
			throws NSJPNegocioException;

		
	/**
	 * Consulta y carga un documento en base a su identificador
	 * 
	 * @param documentoId
	 *            Identificador del documento
	 * @return Documento encontrado, null en caso contratio
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarDocumentoPorId(Long documentoId)
			throws NSJPNegocioException;

	/**
	 * Consulta y carga un documento en base a su identificador llen�ndolo con
	 * los datos del expediente enviado como par�metro
	 * 
	 * @param documentoId
	 *            Identificador del documento
	 * @return Documento encontrado, null en caso contratio
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarDocumentoPorId(Long documentoId, ExpedienteDTO expediente)
			throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de consultar los documentos
	 * asociados a un expediente y al tipo de documento
	 * 
	 * @param expedienteDTO
	 *            : El n�mero de expediente
	 * @param tipoDocumento
	 *            : El tipo de documento a consultar.
	 * @return Regresa un listado de objetos de tipo Documento, en caso
	 *         contrario regresa null.
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosXTipoDocumento(
			ExpedienteDTO expedienteDTO, Long tipoDocumento)
			throws NSJPNegocioException;

	/**
	 * Carga un documento que requiere valores que se encuentran asociados una
	 * audiencia en particular
	 * 
	 * @param audiencia
	 *            Obejeto que contiene el identificador de la audiencia de la
	 *            que se obtendr�n la informaci�n necesaria para generar el
	 *            documento
	 * @param tipoForma
	 *            Forma a partir de la cual se generar� el documetno
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarDocumentoPorAudienciaYForma(AudienciaDTO audiencia,
			FormaDTO tipoForma) throws NSJPNegocioException;

	/**
	 * Almacena los cambios de una Transcripcion, en caso de que la
	 * transcricpion no cuente con ID entonces lo crea y devuelve el ID generado
	 * 
	 * @param documento
	 *            Transcripci�n a ser almacenada
	 * @param idTranAudi
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarDocumentoTranscripcion(DocumentoDTO documento, Long idTranAudi)
			throws NSJPNegocioException;

	/**
	 * Almacena los cambios de un Acta de audiencia, en caso de que el acta de
	 * audiencia no cuente con ID entonces lo crea y devuelve el ID generado
	 * 
	 * @param documento
	 * @param expTrabajo
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarActaAudiencia(DocumentoDTO documento, ExpedienteDTO expTrabajo)
			throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de consultar las notas asociadas a
	 * un documento.
	 * 
	 * @param idDocumento
	 *            Recibe el identificador del documento.
	 * @return Devuelve un listado de notas asociadas al documento
	 * @throws NSJPNegocioException
	 */
	List<NotaDTO> consultarNotasPorDocumento(Long idDocumento)
			throws NSJPNegocioException;

	/**
	 * Consulta los documentos y obtiene los que estan asociados a un expediente
	 * 
	 * @param expedienteDTO
	 *            : numeroExpedienteId
	 * @return lista de documentos del expediente
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException;

	/**
	 * Carga un documento que requiere valores que se encuentran asociados un
	 * resolutivo en particular
	 * 
	 * @param resolutivo
	 *            Obejeto que contiene el identificador del resolutivo del que
	 *            se obtendr�n la informaci�n necesaria para generar el
	 *            documento
	 * @param tipoForma
	 *            Forma a partir de la cual se generar� el documetno
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarDocumentoPorResolutivoYForma(ResolutivoDTO resolutivo,
			FormaDTO formaDTO) throws NSJPNegocioException;

	/**
	 * Consulta la informaci�n de un archivo digital sin su contenido, para
	 * efectos de manipulaci�n en la vista, siempre y cuando este asociado a una
	 * actividad de tipo <code>actividad</code>
	 * 
	 * @param actividad
	 *            Actividad a la que debe estar asociado el Archivo Digital qu
	 *            ese desea consultar.
	 * @return ArchivoDigitalDTO con la informaci�n del archivo sin su
	 *         contenido.
	 * @throws NSJPNegocioException
	 */
	public ArchivoDigitalDTO consultarArchivoDitalSinContenidoPorActividad(
			Long idExpediente, Actividades actividad)
			throws NSJPNegocioException;

	/**
	 * Consulta el contenido de un archivo digital en base al ID del archivo o
	 * al ID del documento
	 * 
	 * @param documentoId
	 *            En caso de tener el id del documento se env�a este ID
	 * @param archivoId
	 *            En caso de tener el ID del archivo digital se env�a este ID
	 * @return Contenido del archivo, null si no se localiza el archivo
	 */
	byte[] consultarContenidoArchivoDigitalPorArchivoODocumento(
			Long documentoId, Long archivoId) throws NSJPNegocioException;

	/**
	 * Consulta los documentos asociados el n�mero de expediente que tengan
	 * asociado un archivo digital sin importar el tipo de forma o tipo de
	 * documento
	 * 
	 * @param expediente
	 *            Contiene el n�mero de expediente a buscar
	 * @return Lista de documentos encontrados
	 */
	List<DocumentoDTO> consultarDocumentosPorNumeroExpediente(
			ExpedienteDTO expediente);

	/**
	 * Consulta los documentos que tienen unba forma del tipo de forma enviado,
	 * relacionados a una Audiencia.
	 * 
	 * @author cesarAgustin
	 * @param audienciaDTO
	 *            -Identificador de la Audiencia <li>id<li>
	 * @return Lista de documentos relacionados a la Audiencia
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosAudienciaByTipoForma(
			AudienciaDTO audienciaDTO, TipoForma tipoForma)
			throws NSJPNegocioException;

	public void asociarDocuementoA(ResolutivoDTO resolutivo,
			DocumentoDTO documento) throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de consultar los
	 * "avisos de posibles hechos delictivos" de acuerdo al estatus que se
	 * recibe.
	 * 
	 * @param estatusNotificacion
	 *            :idEstatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AvisoHechoDelictivoDTO> consultarAvisosHDelictivoPorEstatus(
			Long estatusNotificacion) throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de actualizar el estado de una
	 * notificaci�n.
	 * 
	 * @param notificacionDTO
	 *            : idNotificaci�n
	 * @param estatusNotificacion
	 *            : idEstatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	NotificacionDTO actualizarEstatusNotificacion(
			NotificacionDTO notificacionDTO, Long estatusNotificacion)
			throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la funcionalidad de guardar/actualizar un Aviso de
	 * Hecho Delictivo
	 * 
	 * @param avisoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
			throws NSJPNegocioException;

	/**
	 * Consulta los documentos recibidos por un usuario (AMP) para ser evaluados
	 * seg�n el caso de uso Evaluar documentos recibidos
	 * 
	 * @param funcionario
	 *            Funcionario que recibe
	 * @param tipoDocumento
	 *            tipo de documento a consultar
	 * @return Lista de documentos (dict�menes) recibidos
	 * @throws NSJPNegocioException
	 */
	List<DictamenDTO> consultarDocumentosRecibidosPorUsuario(
			Formas tipoDocumento, FuncionarioDTO funcionario)
			throws NSJPNegocioException;

	/**
	 * Metodo que regresa una lista de IndicesEstructurado
	 * 
	 * @param tipoOficio
	 *            : Permite filtrar por el tipo de Oficio, En caso de ser nullo
	 *            consulta todos los indices
	 * @return List<IndiceEstructuradoDTO>
	 */
	List<IndiceEstructuradoDTO> consultarIndicesEstructuradosPorTipoOficio(
			Long tipoOficio) throws NSJPNegocioException;

	/**
	 * Consulta el detalle de un dictamen en base a su identificador
	 * 
	 * @param dictamenId
	 *            Dictamen ID
	 * @return Dictamen encontrado
	 */
	DictamenDTO consultarDetalleDictamenPorId(Long dictamenId)
			throws NSJPNegocioException;

	/**
	 * Consulta el detalle de una nota en base a su ID
	 * 
	 * @param notaId
	 * @return
	 */
	NotaDTO consultarDetalleNotaPorId(Long notaId) throws NSJPNegocioException;

	/**
	 * Operaci�n que realiza la consulta de un aviso Hecho delictivo por su identificador
	 * @param avisoDTO: idAvisoHechoDelictivo
	 * @return
	 * @throws NSJPNegocioException
	 */
	AvisoHechoDelictivoDTO consultarAvisoHDelictivo(
			AvisoHechoDelictivoDTO avisoDTO) throws NSJPNegocioException;
	/**
	 * Operaci�n que realiza la consulta de las teor�a de caso por expedientes
	 * @param expedienteDTO: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO consultarTeoriasDelCasoXExpediente(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza la consulta de las teor�a de caso por expedientes
	 * @param expedienteDTO: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO consultarPliegoDeConsignacionXExpediente(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
	
	
	/**
	 * Operaci�n que realiza la consulta completa de un cuerpo de Oficio
	 * @param cuerpoOficio: (id u objeto con indice)
	 * @return
	 * @throws NSJPNegocioException
	 */
	CuerpoOficioEstructuradoDTO consultarCuerpoOficio(CuerpoOficioEstructuradoDTO cuerpoOficio) throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza el guardado del oficio estructurado previo al documento
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarTeoriaDeCaso(DocumentoDTO documentoDTO)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza el guardado del oficio estructurado previo al documento
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	
	Long guardarPliegoConsignacion(DocumentoDTO documentoDTO)throws NSJPNegocioException;

	
	/**
	 * Carga la plantilla de un documento en base a un idDocumento y a la forma, con los campos
	 * del documento sustituidos por sus valores de nogocio.
	 * @param idDocumento
	 * @param forma
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO cargarDocumentoPorIdDocumentoYForma(Long idDocumento,
			FormaDTO forma) throws NSJPNegocioException;
	
	/**
	 * Operaci�n que realiza la funcionalidad de consultar las controversias resueltas en justicia alternativa.
	 * @param idTipoDocumento: Controversia es en Tipos "Carta de cumplimiento de acuerdo"
	 * @return
	 * Los datos que se obtienen son:
	 * - N�mero de caso
	 * - Identificador de la controversia resuelta
	 * - Nombre completo (nombre, apellido paterno, apellido materno) del fiscal que llev� a cabo la controversia
	 * - Nombre del documento
	 * - Bandera si ya ha sido le�da la controversia
	 * - Fecha de env�o de la misma.
	 * @throws NSJPNegocioException
	 */
	List<CartaCumplimientoDTO> consultarControversiasResueltas(Long idTipoDocumento)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que actualiza cuando se lee una Controversia resuelta
	 * @param cumplimientoDTO (idDocumento, funcionarioId)
	 * @throws NSJPNegocioException
	 */
	void actualizarControversia(CartaCumplimientoDTO cumplimientoDTO)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que permite registrar una carta de cumplimiento de acuerdo y crea una actividad para el expediente
	 * Obligatorios: Expediente, Funcionario, ArchivoDigital
	 * @param cumplimientoDTO: Es un documento
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarControversiaResuelta(CartaCumplimientoDTO cumplimientoDTO)throws NSJPNegocioException;
	
	/**
	 * Operaci�n que permite adjuntar un archivo digital y asociarlo a un expediente mediante una actividad
	 * @param expedienteDTO: expedienteID
	 * @param archivoDigitalDTO: Objeto
	 * @param funcionarioDTO: claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long adjuntarArchivoDigitalAExpediente(ExpedienteDTO expedienteDTO, ArchivoDigitalDTO archivoDigitalDTO, FuncionarioDTO funcionarioDTO, Actividades actividad) throws NSJPNegocioException;
	
	/**
	 * Operaci�n que permite consultar cuales son los archivos digitales
	 * asociados a una Solicitud.
	 * @param idSolicitud: Long El id de solicitud de la cual se quieran consultar sus
	 * 					   archivos digitales asociados	 
	 * @return List<ArchivoDigitalDTO> Lista con los archivos digitales asociados a la solicitud
	 * @throws NSJPNegocioException
	 */
	List<ArchivoDigitalDTO> consultarArchivosDeSolicitud(Long IdSolicitud) throws NSJPNegocioException;

	/**
	 * Operaci�n que permite consultar los documentos junto con los archivos digitales
	 * asociados a una Solicitud.
	 * @param IdSolicitud: Long El id de solicitud de la cual se quieran consultar sus
	 * 					   archivos digitales asociados	 
	 * @return List<DocumentoDTO>: Lista con los documentos y sus archivos digitales anexos
	 *                             asociados a la solicitud
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarArchivosDeSolicitudPericial(Long IdSolicitud) throws NSJPNegocioException;
	/**
	 * Lee el contenido de un archivo digital en base a su ID de un Elemento
	 * Esto permite consultar la foto asociada a cualquier elemento
	 * @param id ID de archivo o documento
	 * @param esArchivo Indica si el ID es de archivo
	 * @return
	 */
	public byte[] consultarArchivoDigitalXElementoId(Long idElemento) throws NSJPNegocioException;
	
	
	Boolean asociarArchivosDigitalesASolicitud(Long idSolicitud,
			String cadenaIds) throws Exception;	
	
	/**
	 * Obtiene los documentos relacionados a una medida
	 * @author cesarAgustin
	 * @param medidaDTO
	 * 			<li>documentoId<li> Identificador de Medida
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ArchivoDigitalDTO> consultarArchivoDigitalPorMedida (MedidaDTO medidaDTO) throws NSJPNegocioException; 
	
	/**
	 * Asocia un archivo digital ya creado a un documento ya creado
	 * @param documentoId ID del documento al cu�l se asociar� el archivo
	 * @param archivoDigitalId Archivo digital a asociar
	 * @throws NSJPNegocioException
	 * @author Emigdio Hern�ndez
	 */
	void asociarArchivoDigitalADocumento(Long documentoId,Long archivoDigitalId) throws NSJPNegocioException;
	/**
	 * Consulta un archivo digital completo en base al ID de documento
	 * @param documentoId ID del documento para buscar el archivo digital
	 * @return Archivo digital Completo
	 * @throws NSJPNegocioException
	 * @author Emigdio Hern�ndez
	 */
	ArchivoDigitalDTO consultarArchivoDigitalCompleto(Long documentoId) throws NSJPNegocioException;
	
	/**
	 * Consulta un archivo digital completo de un archivo digital en base al ID del archivo o al ID del documento
	 * @param documentoId En caso de tener el id del documento se env�a este ID
	 * @param archivoId En caso de tener el ID del archivo digital se env�a este ID
	 * @return Archivo digital con contenido, null en caso de no localizarlo
	 */
	ArchivoDigitalDTO consultarArchivoDigitalCompletoPorArchivoODocumento(Long documentoId,Long archivoId) throws NSJPNegocioException;
	
	/**
	 * Permite crear un documento junto con su archivo digital 
	 * @param expedienteDTO
	 * @param documentoDTO
	 * @param funcionarioDTO
	 * @param actividad
	 * @param tipoDocumento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long adjuntarDocumentoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO, FuncionarioDTO funcionarioDTO, Actividades actividad, TipoDocumento tipoDocumento) throws NSJPNegocioException;
	
	/**
	 * Interfaz del servicio que permite enviar un Documento a cualquier otra instituci�n mediante un WebService. 
	 * @param documentoDTO
	 * @param numeroExpediente
	 * @param target
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long enviarDocumentoAInstitucion(DocumentoDTO documentoDTO,String numeroExpediente, Instituciones target)
	throws NSJPNegocioException;
	
    /**
     * Consulta los Documentos que estén asociados a un expediente y  Usuario de Reinsercion Social
     * 
     * @param usuario
     *            El usuario del que se consultan sus documentos
     * @param documento
     *            Los datos del documento solicitado, por default el NumeroExpediente_id.
     * @return El listado de documentos asociados al Usuario. Si el usuario no
     *         existe en la base de datos o si no tiene documentos asociados, se
     *         regresa la lista vacia.
     */    
	List<DocumentoDTO> consultarDocumentosReinsercionSocial(FuncionarioDTO funcionarioDTO , DocumentoDTO documentoDTO) throws NSJPNegocioException;
	
	/**
	 * Consulta los documentos que estan asociados a una Audiencia
	 * @param AudienciaDTO
	 * @return lista de documentos de la Audiencia
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosAudiencia(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException;
	
	
	/**
	 * Relaciona en la tabla de cruce el documento con la audiencia
	 * @param audienciaDTO
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	DocumentoDTO relacionarDocumentoAudiencia(AudienciaDTO audienciaDTO,DocumentoDTO documentoDTO)
		throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de un documento en base a su Identificador.
	 * @param idDocumento - Identificador del documento a consultar.
	 * @return DocumentoDTO - DTO con la informaci&oacute;n asociada al documento en BD.
	 */
	DocumentoDTO consultarDocumentoXId(Long idDocumento);
}

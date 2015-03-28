/**
 * Nombre del Programa : DocumentoDelegateImpl.java
 * Autor                            : Emigdio Hern�ndez
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26/05/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementaci�n del Delegate para manipulaci�n de documentos
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
package mx.gob.segob.nsjp.delegate.documento.impl;

import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
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
import mx.gob.segob.nsjp.service.archivo.ConsultarContenidoArchivoDigitalService;
import mx.gob.segob.nsjp.service.archivo.GuardarArchivoDigitalService;
import mx.gob.segob.nsjp.service.documento.ActualizarControversiaService;
import mx.gob.segob.nsjp.service.documento.ActualizarNotaService;
import mx.gob.segob.nsjp.service.documento.ActualizarNotificacionService;
import mx.gob.segob.nsjp.service.documento.AdjuntarArchivoDigitalAExpedienteService;
import mx.gob.segob.nsjp.service.documento.AsociarArchivosDigitalesASolicitudService;
import mx.gob.segob.nsjp.service.documento.AsociarDocumentoAService;
import mx.gob.segob.nsjp.service.documento.AsociarNotaADocumentoService;
import mx.gob.segob.nsjp.service.documento.CargarDocumentoService;
import mx.gob.segob.nsjp.service.documento.ConsultarArchivosDigitalesService;
import mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService;
import mx.gob.segob.nsjp.service.documento.ConsultarControversiasResueltasService;
import mx.gob.segob.nsjp.service.documento.ConsultarCuerpoOficioService;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoPorUsuarioService;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoService;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoXExpedienteService;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosDictamenPorTipoService;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosXTipoDocumentoService;
import mx.gob.segob.nsjp.service.documento.ConsultarIndicesEstructuradosService;
import mx.gob.segob.nsjp.service.documento.ConsultarPliegoDeConsignacionService;
import mx.gob.segob.nsjp.service.documento.ConsultarResumenExpedienteParaDocumentoService;
import mx.gob.segob.nsjp.service.documento.ConsultarTeoriaDelCasoService;
import mx.gob.segob.nsjp.service.documento.EnviarDocumentoAInstitucionService;
import mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.documento.GuardarControversiaResueltaService;
import mx.gob.segob.nsjp.service.documento.GuardarDocumentoService;
import mx.gob.segob.nsjp.service.forma.BuscarFormaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementaci�n del Delegate para manipulaci�n de documentos
 * 
 * @author Emigdio Hern�ndez
 * 
 */
@Service("documentoDelegate")
public class DocumentoDelegateImpl implements DocumentoDelegate {

	@Autowired
	private CargarDocumentoService cargarDocumentoService;
	@Autowired
	private GuardarArchivoDigitalService guardarArchivoDigitalService;
	@Autowired
	private BuscarFormaService buscarFormaService;
	@Autowired
	private GuardarDocumentoService guardarDocumentoService;
	@Autowired
	private ConsultarDocumentoService consultarDocumentoService;
	@Autowired
	private ConsultarResumenExpedienteParaDocumentoService consultarResumenExpedienteParaDocumentoService;
	@Autowired
	private ConsultarDocumentosXTipoDocumentoService consultarDocumentosXTipoDocumentoService;
	@Autowired
	private ConsultarDocumentoPorUsuarioService consultarDocumentoPorUsuarioService;
	@Autowired
	private AsociarNotaADocumentoService asociarNotaADocumentoService;
	@Autowired
	private ActualizarNotaService actualizarNotaService;
	@Autowired
	private ConsultarDocumentoXExpedienteService consultarDocumentoXExpedienteService;
	@Autowired
	private ConsultarContenidoArchivoDigitalService consultarContenidoArchivoDigitalService;
	@Autowired
	private AsociarDocumentoAService asociarDocumentoAService;
	@Autowired
	private ConsultarAvisosHDelictivoService consultarAvisosHDelictivoService;
	@Autowired
	private ActualizarNotificacionService actualizarNotificacionService;
	@Autowired
	private GuardarAvisoHDelictivoService guardarAvisoHDelictivoService;
	@Autowired
	private ConsultarDocumentosDictamenPorTipoService consultarDocumentosDictamenPorTipoService;
	@Autowired
	private ConsultarIndicesEstructuradosService consultarIndicesEstructuradosService;
	@Autowired
	private ConsultarTeoriaDelCasoService consultarTeoriaDelCasoService;
	@Autowired
	private ConsultarPliegoDeConsignacionService consultarPliegoDeConsignacionService;
	@Autowired
	private ConsultarCuerpoOficioService consultarCuerpoOficioService;
	@Autowired
	private ConsultarControversiasResueltasService consultarControversiasResueltasService;
	@Autowired
	private ActualizarControversiaService actualizarControversiaService;
	@Autowired
	private GuardarControversiaResueltaService guardarControversiaResueltaService;
	@Autowired
	private AdjuntarArchivoDigitalAExpedienteService adjuntarArchivoDigitalAExpedienteService;
	@Autowired
	private ConsultarArchivosDigitalesService consultarArchivosDigitales;
	
	@Autowired
	AsociarArchivosDigitalesASolicitudService asociarArchivoService;
	@Autowired
	EnviarDocumentoAInstitucionService aInstitucionService;
	
	
	

	/*-------------------------------------------------------*/
	@Override
	public DocumentoDTO cargarDocumentoPorExpedienteYForma(
			ExpedienteDTO expedienteParam, FormaDTO tipoForma)
			throws NSJPNegocioException {
		return cargarDocumentoService.cargarDocumentoPorExpedienteYForma(
				expedienteParam, tipoForma);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorAudienciaYForma(
			AudienciaDTO audiencia, FormaDTO tipoForma)
			throws NSJPNegocioException {
		return cargarDocumentoService.cargarDocumentoPorAudienciaYForma(
				audiencia, tipoForma);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorResolutivoYForma(
			ResolutivoDTO resolutivo, FormaDTO tipoForma)
			throws NSJPNegocioException {
		return cargarDocumentoService.cargarDocumentoPorResolutivoYForma(
				resolutivo, tipoForma);
	}

	@Override
	public Long guardarArchivoDigital(ArchivoDigitalDTO archivo)
			throws NSJPNegocioException {
		return guardarArchivoDigitalService.guardarArchivoDigital(archivo);
	}

	@Override
	public FormaDTO buscarForma(Long formaId) throws NSJPNegocioException {
		return buscarFormaService.buscarForma(formaId);
	}

	@Override
	public Long guardarDocumento(DocumentoDTO documento,
			ExpedienteDTO expedienteActual) throws NSJPNegocioException {

		return guardarDocumentoService.guardarDocumento(documento,
				expedienteActual);
	}

	@Override
	public Long guardarDocumentoTranscripcion(DocumentoDTO documento,
			Long idSolTranAudi) throws NSJPNegocioException {
		return guardarDocumentoService.guardarDocumentoTranscripcion(documento,
				idSolTranAudi);
	}

	public ParametrosDocumentoDTO cargarResumenExpedienteParaDocumento(
			ExpedienteDTO expediente) throws NSJPNegocioException {
		return consultarResumenExpedienteParaDocumentoService
				.consultarResumenExpedienteParaDocumento(expediente);
	}

	public List<DocumentoDTO> consultarDocumentosExpediente(
			ExpedienteDTO expedienteDTO, UsuarioDTO usuario)
			throws NSJPNegocioException {
		return consultarDocumentoService.consultarDocumentosExpediente(
				expedienteDTO, usuario);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorId(Long documentoId)
			throws NSJPNegocioException {
		return cargarDocumentoService.cargarDocumentoPorId(documentoId);
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosXTipoDocumento(
			ExpedienteDTO expedienteDTO, Long tipoDocumento)
			throws NSJPNegocioException {
		return consultarDocumentosXTipoDocumentoService
				.consultarDocumentosXTipoDocumento(expedienteDTO, tipoDocumento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentoDTO> consultarDocumentoPorUsuario(
			UsuarioDTO usuarioDto, Long tipoDocumento)
			throws NSJPNegocioException {
		return consultarDocumentoPorUsuarioService
				.consultarDocumentoPorUsuario(usuarioDto, tipoDocumento);
	}

	public Long guardarActaAudiencia(DocumentoDTO documento,
			ExpedienteDTO expTrabajo) throws NSJPNegocioException {
		return guardarDocumentoService.guardarActaAudiencia(documento,
				expTrabajo);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void asociarNotaADocumento(NotaDTO notaDto, DocumentoDTO documentoDto)
			throws NSJPNegocioException {
		asociarNotaADocumentoService.asociarNotaADocumento(notaDto,
				documentoDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actualizarNota(NotaDTO notaDto) throws NSJPNegocioException {
		actualizarNotaService.actualizarNota(notaDto);
	}

	public List<NotaDTO> consultarNotasPorDocumento(Long idDocumento)
			throws NSJPNegocioException {
		return consultarDocumentoService
				.consultarNotasPorDocumento(idDocumento);
	}

	public List<DocumentoDTO> consultarDocumentosExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarDocumentoService
				.consultarDocumentosExpediente(expedienteDTO);
	}
		

	public ArchivoDigitalDTO consultarArchivoDitalSinContenidoPorActividad(Long idExpediente,
			Actividades actividad) throws NSJPNegocioException {
		return consultarContenidoArchivoDigitalService
				.consultarArchivoDitalSinContenidoPorActividad(idExpediente, actividad);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DocumentoDTO consultarDocumentoXExpediente(
			ExpedienteDTO expedienteDto, Long tipoDocumento)
			throws NSJPNegocioException {
		return consultarDocumentoXExpediente(expedienteDto, tipoDocumento);
	}

	@Override
	public byte[] consultarContenidoArchivoDigitalPorArchivoODocumento(
			Long documentoId, Long archivoId) throws NSJPNegocioException {
		return consultarContenidoArchivoDigitalService
				.consultarContenidoArchivoDigitalPorArchivoODocumento(
						documentoId, archivoId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#
	 * consultarDocumentosPorNumeroExpediente
	 * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<DocumentoDTO> consultarDocumentosPorNumeroExpediente(
			ExpedienteDTO expediente) {
		return consultarDocumentoXExpedienteService
				.consultarDocumentosPorNumeroExpediente(expediente);
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosAudienciaByTipoForma(
			AudienciaDTO audienciaDTO, TipoForma tipoForma)
			throws NSJPNegocioException {
		return this.consultarDocumentoService
				.consultarDocumentosAudienciaByTipoForma(audienciaDTO,
						tipoForma);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorIdDocumentoYForma(Long idDocumento, FormaDTO forma) throws NSJPNegocioException{
		return cargarDocumentoService.cargarDocumentoPorIdDocumentoYForma(idDocumento, forma);
	}
	
	public void asociarDocuementoA(ResolutivoDTO resolutivo,
			DocumentoDTO documento) throws NSJPNegocioException {
		asociarDocumentoAService.asociarDocuementoA(resolutivo, documento);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorId(Long documentoId,
			ExpedienteDTO expediente) throws NSJPNegocioException {
		return cargarDocumentoService.cargarDocumentoPorId(documentoId,
				expediente);
	}

	@Override
	public List<AvisoHechoDelictivoDTO> consultarAvisosHDelictivoPorEstatus(
			Long estatusNotificacion) throws NSJPNegocioException {
		return consultarAvisosHDelictivoService.consultarAvisosHDelictivoPorEstatus(estatusNotificacion);
	}

	@Override
	public NotificacionDTO actualizarEstatusNotificacion(
			NotificacionDTO notificacionDTO, Long estatusNotificacion)
			throws NSJPNegocioException {
		return actualizarNotificacionService.actualizarEstatusNotificacion(notificacionDTO, estatusNotificacion);
	}

	@Override
	public Long guardarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
			throws NSJPNegocioException {
		return guardarAvisoHDelictivoService.guardarAvisoHDelictivo(avisoDTO);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarDocumentosRecibidosPorUsuario(mx.gob.segob.nsjp.comun.enums.forma.Formas, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public List<DictamenDTO> consultarDocumentosRecibidosPorUsuario(
			Formas tipoDocumento, FuncionarioDTO funcionario)
			throws NSJPNegocioException {
		return consultarDocumentosDictamenPorTipoService.consultarDocumentosRecibidosAMP(tipoDocumento,funcionario);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarIndicesEstructuradosPorTipoOficio(java.lang.Long)
	 */
	@Override
	public List<IndiceEstructuradoDTO> consultarIndicesEstructuradosPorTipoOficio(Long tipoOficio)
			throws NSJPNegocioException {
		return consultarIndicesEstructuradosService.consultarIndicesEstructuradosXTipoOficio(tipoOficio);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarDetalleDictamenPorId(java.lang.Long)
	 */
	@Override
	public DictamenDTO consultarDetalleDictamenPorId(Long dictamenId) throws NSJPNegocioException {
		return consultarDocumentosDictamenPorTipoService.consultarDetalleDictamenPorId(dictamenId);
	}

	@Override
	public NotaDTO consultarDetalleNotaPorId(Long notaId) throws NSJPNegocioException {
		return consultarDocumentoService.consultarDetalleNotaPorId(notaId);
	}

	@Override
	public AvisoHechoDelictivoDTO consultarAvisoHDelictivo(
			AvisoHechoDelictivoDTO avisoDTO) throws NSJPNegocioException {
		return consultarAvisosHDelictivoService.consultarAvisoHDelictivo(avisoDTO);
	}

	@Override
	public DocumentoDTO consultarTeoriasDelCasoXExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarTeoriaDelCasoService.consultarTeoriasDelCasoXExpediente(expedienteDTO);
	}
	
	@Override
	public DocumentoDTO consultarPliegoDeConsignacionXExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarPliegoDeConsignacionService.consultarPliegoDeConsignacionXExpediente(expedienteDTO);
	}

	@Override
	public CuerpoOficioEstructuradoDTO consultarCuerpoOficio(CuerpoOficioEstructuradoDTO cuerpoOficio)
			throws NSJPNegocioException {
		return consultarCuerpoOficioService.consultarCuerpoOficio(cuerpoOficio);
	}

	@Override
	public Long guardarTeoriaDeCaso(DocumentoDTO documentoDTO)
			throws NSJPNegocioException {
		return guardarDocumentoService.guardarTeoriaDeCaso(documentoDTO);
	}

	@Override
	public Long guardarPliegoConsignacion(DocumentoDTO documentoDTO)
			throws NSJPNegocioException {
		return guardarDocumentoService.guardarPliegoConsignacion(documentoDTO);
	}
	
	@Override
	public List<CartaCumplimientoDTO> consultarControversiasResueltas(
			Long idTipoDocumento) throws NSJPNegocioException {
		return consultarControversiasResueltasService.consultarControversiasResueltas(idTipoDocumento);
	}

	@Override
	public void actualizarControversia(CartaCumplimientoDTO cumplimientoDTO)
			throws NSJPNegocioException {
		actualizarControversiaService.actualizarControversia(cumplimientoDTO);
	}

	@Override
	public Long guardarControversiaResuelta(CartaCumplimientoDTO cumplimientoDTO)
			throws NSJPNegocioException {
		return guardarControversiaResueltaService.guardarControversiaResuelta(cumplimientoDTO);
	}

	@Override
	public Long adjuntarArchivoDigitalAExpediente(ExpedienteDTO expedienteDTO,
			ArchivoDigitalDTO archivoDigitalDTO, FuncionarioDTO funcionarioDTO, Actividades actividad) throws NSJPNegocioException {
		return adjuntarArchivoDigitalAExpedienteService.adjuntarArchivoDigitalAExpediente(expedienteDTO, archivoDigitalDTO,funcionarioDTO, actividad);
	}
	
	
	@Override
	public List<ArchivoDigitalDTO> consultarArchivosDeSolicitud(Long IdSolicitud) throws NSJPNegocioException {
		return consultarArchivosDigitales.consultarArchivosDigitalesXSolicitud(IdSolicitud);		
	}
	
	@Override
	public List<DocumentoDTO> consultarArchivosDeSolicitudPericial(Long IdSolicitud) throws NSJPNegocioException {
		return consultarArchivosDigitales.consultarArchivosDigitalesXSolicitudPericial(IdSolicitud);		
	}
	
	public byte[] consultarArchivoDigitalXElementoId(Long idElemento) throws NSJPNegocioException {
		return consultarContenidoArchivoDigitalService.consultarArchivoDigitalXElementoId(idElemento);				
	}

	@Override
	public Boolean asociarArchivosDigitalesASolicitud(Long idSolicitud,
			String cadenaIds) throws Exception {
		return asociarArchivoService.asociarArchivosDigitalesASolicitud(idSolicitud, cadenaIds);		
	}

	@Override
	public List<ArchivoDigitalDTO> consultarArchivoDigitalPorMedida(
			MedidaDTO medidaDTO) throws NSJPNegocioException {
		return consultarContenidoArchivoDigitalService.consultarArchivoDigitalPorMedida(medidaDTO);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#asociarArchivoDigitalADocumento(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void asociarArchivoDigitalADocumento(Long documentoId,
			Long archivoDigitalId) throws NSJPNegocioException {
		asociarArchivoService.asociarArchivoDigitalADocumento(documentoId, archivoDigitalId);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarArchivoDigitalCompleto(java.lang.Long)
	 */
	@Override
	public ArchivoDigitalDTO consultarArchivoDigitalCompleto(Long documentoId)
			throws NSJPNegocioException {
		return consultarArchivosDigitales.consultarArchivoDigitalCompleto(documentoId);
	}

	@Override
	public DocumentoDTO cargarDocumentoPorExpedienteYForma(ExpedienteDTO expediente,
			FormaDTO tipoForma,Map<String,Object> parametrosExtras) throws NSJPNegocioException{
		return cargarDocumentoService.cargarDocumentoPorExpedienteYForma(expediente, tipoForma, parametrosExtras);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarArchivoDigitalCompletoPorArchivoODocumento(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ArchivoDigitalDTO consultarArchivoDigitalCompletoPorArchivoODocumento(
			Long documentoId, Long archivoId) throws NSJPNegocioException {
		return consultarContenidoArchivoDigitalService.consultarArchivoDigitalCompletoPorArchivoODocumento(documentoId, archivoId);
	}
	
	@Override
	public Long adjuntarDocumentoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO, FuncionarioDTO funcionarioDTO, Actividades actividad, TipoDocumento tipoDocumento) throws NSJPNegocioException {
		return adjuntarArchivoDigitalAExpedienteService.adjuntarDocumentoAExpediente(expedienteDTO, documentoDTO,funcionarioDTO, actividad, tipoDocumento);
	}

	@Override
	public Long enviarDocumentoAInstitucion(DocumentoDTO documentoDTO,
			String numeroExpediente, Instituciones target)
			throws NSJPNegocioException {
		return aInstitucionService.enviarDocumentoAInstitucion(documentoDTO, numeroExpediente, target);
	}
	
	
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
	public List<DocumentoDTO> consultarDocumentosReinsercionSocial(FuncionarioDTO funcionarioDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException {
		return consultarDocumentoService.consultarDocumentosReinsercionSocial(funcionarioDTO, documentoDTO);
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		return consultarDocumentoService.consultarDocumentosAudiencia(audienciaDTO);
	}

	@Override
	public DocumentoDTO relacionarDocumentoAudiencia(AudienciaDTO audienciaDTO,
			DocumentoDTO documentoDTO) throws NSJPNegocioException {
		return asociarDocumentoAService.asociarDocumentoAAudiencia(audienciaDTO, documentoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate#consultarDocumentoXId(java.lang.Long)
	 */
	@Override
	public DocumentoDTO consultarDocumentoXId(Long idDocumento) {
		return consultarDocumentoService.consultarDocumentoXId(idDocumento);
	}

}

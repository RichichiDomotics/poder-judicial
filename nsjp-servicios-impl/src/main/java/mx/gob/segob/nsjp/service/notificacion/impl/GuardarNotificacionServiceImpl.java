package mx.gob.segob.nsjp.service.notificacion.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoTarea;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;
import mx.gob.segob.nsjp.service.infra.ClienteGeneralService;
import mx.gob.segob.nsjp.service.notificacion.GuardarNotificacionService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.tarea.AsignarTareaFuncionarioService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 * 
 */
@Service
@Transactional
public class GuardarNotificacionServiceImpl implements
		GuardarNotificacionService {

	public final static Logger logger = Logger
			.getLogger(GuardarNotificacionServiceImpl.class);
	@Autowired
	private NotificacionDAO notificacionDAO;
	@Autowired
    private AsignarTareaFuncionarioService asignarTareaFuncionarioService;    
	@Autowired
	private InvolucradoAudienciaDAO involucradoAudienciaDAO;
	@Autowired
	private FuncionarioAudienciaDAO funcionarioAudienciaDAO;
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
    private FuncionarioDAO funDao;    
	@Autowired
	private GenerarFolioSolicitudService generarFolioSolicitudService;
	@Autowired
	private AudienciaDAO audienciaDao;
	@Autowired
	private ClienteGeneralService clienteWs;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private ValorDAO valorDAO;
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	private RelNumExpedienteAuditoriaDAO relNumExpedienteAuditoriaDAO;
	@Autowired
	private JerarquiaOrganizacionalDAO jerarquiaOrganizacionalDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.notificacion.GuardarNotificacionService#
	 * guardarNotificacion(mx.gob.segob.nsjp.dto.documento.NotificacionDTO)
	 */
	@Override
	public Long guardarNotificacion(NotificacionDTO notificacionDTO,
			AudienciaDTO audienciaDTO, InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR UN DOCUMENTO TIPO NOTIFICACIÓN DE AUDIENCIA ****/");

		/* Verificación de parámetros */
		if (notificacionDTO == null || audienciaDTO == null
				|| involucradoDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Audiencia audiencia = audienciaDao.read(audienciaDTO.getId());
		Involucrado involucrado = involucradoDAO.read(involucradoDTO
				.getElementoId());

		Notificacion notificacion = NotificacionTransformer
				.transformarNotificacion(notificacionDTO);
		/* Obligatorios de Docmento */
		notificacion.setNombreDocumento("NOTIFICACION DE AUDIENCIA");
		notificacion.setFechaCreacion(new Date());
		// notificacion.setForma(formaDAO.consultarFormaPorNombre("Blanco"));
		notificacion.setForma(formaDAO.read(Formas.NOTIFICACION_DE_AUDIENCIA
				.getValorId()));

		notificacion.setTipoDocumento(new Valor(TipoDocumento.NOTIFICACION
				.getValorId()));

		/* Obligatorios de Notificacion */
		InvolucradoAudiencia invAud = new InvolucradoAudiencia(
				new InvolucradoAudienciaId(audienciaDTO.getId(),
						involucradoDTO.getElementoId()), new Involucrado(
						involucradoDTO.getElementoId()), new Audiencia(
						audienciaDTO.getId()));

		InvolucradoAudiencia bdInvAud = involucradoAudienciaDAO.read(invAud
				.getId());
		if (bdInvAud == null)
			involucradoAudienciaDAO.create(invAud);

		notificacion.setInvolucrado(involucrado);
		notificacion.setAudiencia(audiencia);
		List<Notificacion> notificaciones = notificacionDAO
				.consultarNotificacionesPorAudienciaInvolucrado(
						audienciaDTO.getId(), involucradoDTO.getElementoId());
		int consecutivo = 1;
		if (notificaciones != null)
			consecutivo = notificaciones.size() + 1;
		notificacion.setConsecutivoNotificacion(String.valueOf(consecutivo));

		notificacion.setFolioNotificacion(generarFolioSolicitudService
				.generarFolioNotificacion());

		return notificacionDAO.create(notificacion);
	}

	 private void registrarAudienciaEnAgendaDeFuncionario(Funcionario funcionario, AudienciaDTO audiencia) throws NSJPNegocioException{
	        final UsuarioDTO usr = new UsuarioDTO(funcionario.getUsuario().getUsuarioId());
	        final FuncionarioDTO frio = new FuncionarioDTO(funcionario.getClaveFuncionario());
	        usr.setFuncionario(frio);

	        EventoCitaDTO evento = new EventoCitaDTO();
	        evento.setUsuario(usr);
	        //evento.setFechaInicioEvento(audiencia.getFechaInicio());
	        evento.setFechaInicioEvento(audiencia.getFechaEvento());
	        //evento.setFechaFinEvento(audiencia.getFechaFin());
	        Calendar auxFin = Calendar.getInstance();
	        auxFin.setTime(audiencia.getFechaEvento());
	        auxFin.add(Calendar.MINUTE, audiencia.getDuracionEstimada());
	        evento.setFechaFinEvento(auxFin.getTime());
	        //evento.setDireccion(audiencia.getDomicilioSala());
	        if (audiencia.getSala()!=null) {
	        	logger.debug(audiencia.getSala());
	            logger.debug(audiencia.getSala().getNombreSala());	            
	            evento.setDireccion(audiencia.getSala().getDomicilioSala());
	        }
	        evento.setTipoEvento(new ValorDTO(TipoEvento.TAREA.getValorId()));
	        evento.setNombreEvento("Audiencia " + audiencia.getFolioAudiencia());
	        evento.setDescripcionEvento("Audiencia " + audiencia.getFolioAudiencia());
	        evento.setEstatus(new ValorDTO(EstatusEventoCita.NO_ATENDIDO.getValorId()));
	        
	        TareaDTO tarea = new TareaDTO();
	        tarea.setValor(new ValorDTO(TipoTarea.ACUDIR_AUDIENCIA.getValorId()));
	        tarea.setEventoCita(evento);
	        tarea.setUsuario(usr);
	        tarea.setIdFuncionario(frio.getClaveFuncionario());
	        tarea.setDiaTarea(evento.getFechaInicioEvento());
	        evento.setTarea(tarea);

	        asignarTareaFuncionarioService.asignarTareaFuncionario(tarea);
	    }
	 
	public void enviarNotificacion(Long idNotificacion, Long idAudiencia,
			String nombreCompletoFuncionario, Instituciones institucion)
			throws NSJPNegocioException {
		// se valida que le funcionario es de otra institución
		logger.debug("Enviando la notificacion " + idNotificacion + " para "
				+ nombreCompletoFuncionario + " de " + institucion);
		if (institucion.getValorId().longValue() != this.notificacionDAO
				.consultarInsitucionActual().getConfInstitucionId().longValue()) {
			Notificacion notiBd = this.notificacionDAO.read(idNotificacion);
			enviarNotificacionAOtraIsntitucion(idAudiencia,
					nombreCompletoFuncionario, institucion,
					notiBd.getFolioNotificacion(),
					notiBd.getConsecutivoNotificacion());
		}
		// FJA-29
		// CUANDO SON DE LA MISMA INSTITUCIÓN
		else{
			logger.debug("Enviando la notificacion " + idNotificacion + " para "
					+ nombreCompletoFuncionario + " a la misma institcuión ");						
			Funcionario funcionario = funDao.obtenerFuncionarioPorNombreCompleto(nombreCompletoFuncionario);
			logger.debug("Se encontro el funcionario: " + funcionario.getNombreCompleto() );									
	        if (funcionario != null) {
	        	Audiencia audFromBD = this.audienciaDao.read(idAudiencia);
	        	AudienciaDTO audienciaDTO = AudienciaTransformer.transformarDTO(audFromBD);
	        	logger.debug("Audiencia recuperada: " + audienciaDTO );											        
	        	registrarAudienciaEnAgendaDeFuncionario(funcionario,audienciaDTO);
	        }	        			
		}			
	}
	 
	/**
	 * 
	 * @param idAudiencia
	 * @param nombreCompletoFuncionario
	 * @param institucion
	 * @param folioNotificacion
	 * @param consecutivoNotificacion
	 * @return
	 * @throws NSJPNegocioException
	 */
	private Boolean enviarNotificacionAOtraIsntitucion(Long idAudiencia,
			String nombreCompletoFuncionario, Instituciones institucion,
			String folioNotificacion, String consecutivoNotificacion)
			throws NSJPNegocioException {
		final Audiencia audFromBD = this.audienciaDao.read(idAudiencia);
		return clienteWs.enviarNotificacionAudienciaFuncionarioExtenor(
				audFromBD, nombreCompletoFuncionario, institucion,
				folioNotificacion, consecutivoNotificacion);
	}

	@Override
	public Long guardarNotificacion(NotificacionDTO notificacionDTO,
			AudienciaDTO audienciaDTO, FuncionarioDTO funcionario)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR UN DOCUMENTO TIPO NOTIFICACIÓN DE AUDIENCIA ****/");

		/* Verificación de parámetros */
		if (notificacionDTO == null || audienciaDTO == null
				|| funcionario == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Funcionario fun = funcionarioDAO
				.read(funcionario.getClaveFuncionario());
		Audiencia audiencia = audienciaDao.read(audienciaDTO.getId());

		Notificacion notificacionBD = NotificacionTransformer
				.transformarNotificacion(notificacionDTO);
		/* Obligatorios de Docmento */
		notificacionBD.setNombreDocumento("NOTIFICACION DE AUDIENCIA");
		notificacionBD.setFechaCreacion(new Date());
		notificacionBD.setForma(formaDAO.read(Formas.NOTIFICACION_DE_AUDIENCIA
				.getValorId()));
		notificacionBD.setTipoDocumento(new Valor(TipoDocumento.NOTIFICACION
				.getValorId()));

		/* Obligatorios de Notificacion */
		FuncionarioAudiencia funcionarioAudiencia = new FuncionarioAudiencia(
				new FuncionarioAudienciaId(audienciaDTO.getId(),
						funcionario.getClaveFuncionario()), new Funcionario(
						funcionario.getClaveFuncionario()), new Audiencia(
						audienciaDTO.getId()));

		FuncionarioAudiencia bdInvAud = funcionarioAudienciaDAO
				.read(funcionarioAudiencia.getId());
		if (bdInvAud == null) {
			funcionarioAudienciaDAO.create(funcionarioAudiencia);
		}

		notificacionBD.setFuncionario(fun);
		notificacionBD.setAudiencia(audiencia);
		List<Notificacion> notificaciones = null;
		notificaciones = notificacionDAO
				.consultarNotificacionesPorAudienciaFuncionario(
						audienciaDTO.getId(), funcionario.getClaveFuncionario());
		int consecutivo = 1;
		if (notificaciones != null) {
			consecutivo = notificaciones.size() + 1;
		}
		notificacionBD.setConsecutivoNotificacion(String.valueOf(consecutivo));
		notificacionBD.setFolioNotificacion(generarFolioSolicitudService
				.generarFolioNotificacion());

		notificacionBD.setEsFisica(false);
		notificacionBD.setFechaCitado(new Date());
		return notificacionDAO.create(notificacionBD);
	}

	@Override
	public Long guardarYEnviarNotificacionAMismaInstitucion(
			ExpedienteDTO expedienteDTO, DocumentoDTO documentoDTO)
			throws NSJPNegocioException {

		logger.info("/**** Servicio enviarNotificacionMismaInstitucion ****/");
		if (expedienteDTO == null
				|| expedienteDTO.getNumeroExpedienteId() == null
				|| expedienteDTO.getNumeroExpedienteId() < 0
				|| expedienteDTO.getUsuario() == null
				|| expedienteDTO.getUsuario().getAreaActual() == null
				|| expedienteDTO.getUsuario().getAreaActual().getAreaId() == null
				|| expedienteDTO.getUsuario().getFuncionario() == null
				|| expedienteDTO.getUsuario().getFuncionario()
						.getClaveFuncionario() == null || documentoDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		logger.info(" NumeroExpedienteId:"
				+ expedienteDTO.getNumeroExpedienteId());
		logger.info(" Usuario:" + expedienteDTO.getUsuario());
		logger.info(" Funcionario:"
				+ expedienteDTO.getUsuario().getFuncionario());

		// Buscar el documento por medio de la Actividad asociada a dicho
		// Expediente.
		// 1.- Buscar el expedienteID
		Expediente expediente = expedienteDAO
				.consultarExpedientePorIdNumerExpediente(expedienteDTO
						.getNumeroExpedienteId());

		// 2-. Obtener la ultima actividad asociada al expediente, dado que el
		// tipo de actividad es => Actividades.NOTIFICAR_AUDITORIA
		Long tipoActividad = Actividades.NOTIFICAR_AUDITORIA.getValorId();
		Actividad actividad = actividadDAO
				.consultarActividadPorExpedienteIdTipoActividad(
						expediente.getExpedienteId(), tipoActividad);

		// Obtener el documento para ser asigando a una notificacion
		// Documento documento =
		// documentoDAO.consultarDocumentoPorId(actividad.getDocumento().getDocumentoId());

		// Ingresar los elementos del documento en la notificacion
		Notificacion notificacion = new Notificacion();
		notificacion.setTextoParcial(documentoDTO.getTextoParcial());
		notificacion.setForma(FormaTransformer.transformarFormaDTO(documentoDTO
				.getFormaDTO()));
		notificacion
				.setArchivoDigital(ArchivoDigitalTransformer
						.transformarArchivoDigitalDTO(documentoDTO
								.getArchivoDigital()));
		notificacion.setFechaCreacion(new Date());
		notificacion.setNombreDocumento(documentoDTO.getNombreDocumento());
		if (documentoDTO.getTipoDocumentoDTO() != null
				&& documentoDTO.getTipoDocumentoDTO().getIdCampo() != null)
			notificacion.setTipoDocumento(new Valor(documentoDTO
					.getTipoDocumentoDTO().getIdCampo()));

		// Datos Propios de la Notificacion
		// Luga => Nombre del área del usuario firmado
		notificacion.setLugar(expedienteDTO.getUsuario().getAreaActual()
				.getNombre());
		// Motivo => Nombre de la actividad
		Valor actividadValor = valorDAO.read(Actividades.NOTIFICAR_AUDITORIA
				.getValorId());
		notificacion.setMotivo(actividadValor.getValor());

		// PersonaAutoriza => Nombre completo del Usuario Firmado
		notificacion.setPersonaAutoriza(expedienteDTO.getUsuario()
				.getFuncionario().getNombreCompleto());
		// Puesto Autoriza => Nombre del puesto usuario firmado
		if (expedienteDTO.getUsuario().getFuncionario().getPuesto() != null)
			notificacion.setPuestoAutoriza(expedienteDTO.getUsuario()
					.getFuncionario().getPuesto().getValor());

		// Obtener el Expediente auditado consultando la relacion de Visitaduria
		NumeroExpediente numCarpetaAuditoria = new NumeroExpediente(
				expedienteDTO.getNumeroExpedienteId());
		numCarpetaAuditoria.setEstatus(null);
		List<RelNumExpedienteAuditoria> relaciones = relNumExpedienteAuditoriaDAO
				.consultarRelNumeroExpedienteAuditoriaPorFiltro(null, null,
						numCarpetaAuditoria);
		// Obtener el Funcionario Auditado
		if (relaciones != null && !relaciones.isEmpty()
				&& relaciones.get(0).getNumeroExpediente() != null) {
			Funcionario ampAuditado = relaciones.get(0).getNumeroExpediente()
					.getFuncionario();
			JerarquiaOrganizacional departamentoAMP = ampAuditado.getArea();
			logger.info(" DepartamentoAMP:"
					+ departamentoAMP.getJerarquiaOrganizacionalId());

			// Coordinador del área auditado
			JerarquiaOrganizacional areaCoordinacion = jerarquiaOrganizacionalDAO
					.read(departamentoAMP.getJerarquiaOrganizacionalId());
			logger.info(" Area Coordinacion:"
					+ areaCoordinacion.getJerarquiaOrgResponsable()
							.getJerarquiaOrganizacionalId());
			List<Funcionario> listaCoordinadorDep = funcionarioDAO
					.consultarFuncionariosPorAreaYPuesto(areaCoordinacion
							.getJerarquiaOrgResponsable()
							.getJerarquiaOrganizacionalId(), null);
			if (listaCoordinadorDep != null && !listaCoordinadorDep.isEmpty()) {
				logger.info(" Numero de Coordinadores:"
						+ listaCoordinadorDep.size());
				Funcionario coordinadorDep = listaCoordinadorDep.get(0);
				// ClaveFuncionario => Clave de la persona a la que se le
				// mandara la notificación.
				notificacion.setFuncionario(new Funcionario(coordinadorDep
						.getClaveFuncionario()));

				List<Notificacion> notificaciones = notificacionDAO
						.consultarNotificacionesXFuncionario(
								coordinadorDep.getClaveFuncionario(), null);
				logger.info(" notificaciones " + notificaciones);
				logger.info(" notificaciones " + notificaciones != null ? notificaciones
						.size() : "null");
				int consecutivo = 1;
				if (notificaciones != null)
					consecutivo = notificaciones.size() + 1;
				// Consecutivo de la notificación correspondiente a la persona a
				// la que se le manda la notificación (iClaveFuncionario)
				notificacion.setConsecutivoNotificacion(String
						.valueOf(consecutivo));
			} else
				notificacion.setConsecutivoNotificacion("0");
		}
		notificacion.setEstatus(new Valor(EstatusNotificacion.NO_ATENDIDA
				.getValorId()));

		// Folio de la notificación conformado
		notificacion.setFolioNotificacion(generarFolioSolicitudService
				.generarFolioNotificacion());

		// Numero de Caso del Expediente
		if (expediente.getCaso() != null)
			notificacion.setNumeroCasoAsociado(expediente.getCaso()
					.getNumeroGeneralCaso());

		if (documentoDTO.getArchivoDigital() != null) {
			ArchivoDigital archivoDigital = ArchivoDigitalTransformer
					.transformarArchivoDigitalDTO(documentoDTO
							.getArchivoDigital());
			Long idAD = archivoDigitalDAO.create(archivoDigital);
			logger.info(" idAD:" + idAD);
			archivoDigital.setArchivoDigitalId(idAD);
			notificacion.setArchivoDigital(archivoDigital);
		}
		Long idNotificacion = notificacionDAO.create(notificacion);

		// Actualizar la actividad con el nuevo Id de Documento(Notificación)
		// creado.
		actividad.setDocumento(new Documento(idNotificacion));
		actividadDAO.update(actividad);

		return idNotificacion;
	}

}

/**
 * Nombre del Programa : RecibirNotificacionAudienciaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.agenda.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoTarea;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaWSDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.AsignarSalaTemporalService;
import mx.gob.segob.nsjp.service.audiencia.RecibirNotificacionAudienciaService;
import mx.gob.segob.nsjp.service.tarea.AsignarTareaFuncionarioService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("recibirNotificacionAudienciaService")
@Transactional
public class RecibirNotificacionAudienciaServiceImpl
        implements
            RecibirNotificacionAudienciaService {
    /**
     * 
     */
    private final static Logger logger = Logger
            .getLogger(RecibirNotificacionAudienciaServiceImpl.class);

    @Autowired
    private FuncionarioDAO funDao;
    @Autowired
    private AudienciaDAO audienciaDAO;
    @Autowired
    private SalaAudienciaDAO salaAudienciaDAO;
    @Autowired
    private AsignarSalaTemporalService asignarSalaTemporalService;
    @Autowired
    private AsignarTareaFuncionarioService asignarTareaFuncionarioService;
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    @Autowired
    private FuncionarioAudienciaDAO funAudDao;
    @Autowired
    private NotificacionDAO notificacionDao;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.audiencia.RecibirNotificacionAudienciaService
     * #recibirNotificacionAudiencia
     * (mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO)
     */
    @Override
    public Boolean recibirNotificacionAudiencia(
            SolicitudAudienciaBasicoWSDTO notiWS)
            throws NSJPNegocioException {

        if (notiWS==null ||StringUtils.isEmpty(notiWS .getNombreSolicitante())) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        String nombre = notiWS.getNombreSolicitante();
        final Funcionario funcionario = funDao.obtenerFuncionarioPorNombreCompleto(nombre);
        if (funcionario == null) {
            logger.warn("Funcionario no encontrado");
            return Boolean.FALSE;
        }
        logger.debug("Funcionario encontrado :: " + funcionario.getClaveFuncionario());
        AudienciaWSDTO audiencia = notiWS.getAudiencia();
        Long idAudiencia = null;
        if (notiWS.getInvolucradosDTO()!=null){
        	for(InvolucradoWSDTO involucrado : notiWS.getInvolucradosDTO()){
        		if(involucrado.getCalidad().getValorIdCalidad().longValue() == Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId().longValue()){
        			NumeroExpediente ne = null;
        			ne = recuperarExpediente(funcionario, audiencia,
                            involucrado);
        			if(ne != null){
        			    idAudiencia = registrarAudiencia(audiencia, ne);			
        			}
        		}
        	}
        }
        Notificacion notiPojo = new Notificacion();
        logger.debug("idAudiencia :: " + idAudiencia);
        if (idAudiencia != null) {
            FuncionarioAudiencia funAud = new FuncionarioAudiencia();
            FuncionarioAudienciaId id = new FuncionarioAudienciaId();
            id.setAudienciaId(idAudiencia);
            id.setClaveFuncionario(funcionario.getClaveFuncionario());
            funAud.setId(id);
            this.funAudDao.create(funAud);
            notiPojo.setAudiencia(new Audiencia(idAudiencia));
        }
        notiPojo.setFuncionario(new Funcionario(funcionario.getClaveFuncionario()));
    	notiPojo.setFechaCreacion(new Date());
    	notiPojo.setConsecutivoNotificacion(audiencia.getNotificaion().getConsecutivoNotificacion());
    	notiPojo.setFolioNotificacion(audiencia.getNotificaion().getFolioNotificacion());
    	notiPojo.setForma(new Forma(Formas.AUDIENCIA.getValorId()));
    	notiPojo.setTipoDocumento(new Valor(TipoDocumento.NOTIFICACION.getValorId()));
        notiPojo.setEstatus(new Valor(EstatusNotificacion.NO_LEIDA.getValorId()));
        notiPojo.setConfInstitucion(new ConfInstitucion(notiWS.getConfInstitucionId()));
        notiPojo.setNombreDocumento("Notificación audiencia "+ audiencia.getFolioAudiencia());
        notiPojo.setLugarCitado(audiencia.getDomicilioSala());
        notiPojo.setFechaCitado(audiencia.getFechaInicio());
    	this.notificacionDao.create(notiPojo);
    	registrarAudienciaEnAgendaDeFucionario(funcionario, audiencia);
        
        return Boolean.TRUE;
    }

    /**
     * @param funcionario
     * @param audiencia
     * @param involucrado
     * @return
     * @throws NSJPNegocioException 
     */
    private NumeroExpediente recuperarExpediente(final Funcionario funcionario,
            AudienciaWSDTO audiencia, InvolucradoWSDTO involucrado) throws NSJPNegocioException {
        NumeroExpediente ne = null;
        if (numeroExpedienteDAO.consultarInsitucionActual()
                .getConfInstitucionId().longValue() == Instituciones.DEF
                .getValorId().longValue()) {
            ne = numeroExpedienteDAO
            .obtenerExpedienteDefensaPorCasoFolioImputado(
                    audiencia.getNumeroGeneralCaso(),
                    involucrado.getFolioElemento(),
                    funcionario.getClaveFuncionario());
        } else {
            ne = numeroExpedienteDAO
                    .buscarNumeroExpedientePorCasoFolioImputado(
                            audiencia.getNumeroGeneralCaso(),
                            involucrado.getFolioElemento(),
                            funcionario.getClaveFuncionario());
        }
        return ne;
    }
    
    private void registrarAudienciaEnAgendaDeFucionario(Funcionario funcionario, AudienciaWSDTO audiencia) throws NSJPNegocioException{
        final UsuarioDTO usr = new UsuarioDTO(funcionario.getUsuario().getUsuarioId());
        final FuncionarioDTO frio = new FuncionarioDTO(funcionario.getClaveFuncionario());
        usr.setFuncionario(frio);

        EventoCitaDTO evento = new EventoCitaDTO();
        evento.setUsuario(usr);
        evento.setFechaInicioEvento(audiencia.getFechaInicio());
        evento.setFechaFinEvento(audiencia.getFechaFin());
        evento.setDireccion(audiencia.getDomicilioSala());
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
    
    private Long registrarAudiencia(AudienciaWSDTO audiencia,
            NumeroExpediente numero) throws NSJPNegocioException {
        // Registrar Audiencia

        Audiencia audExistente = audienciaDAO.obtnerAudienciaByFolio(audiencia
                .getFolioAudiencia());
        Long idAudiencia = null;
        if (audExistente == null) {
            Audiencia audienciaNew = new Audiencia();
            audienciaNew.setConsecutivo((short) 1);
            audienciaNew.setNumeroExpediente(numero);
            audienciaNew.setFolioAudiencia(audiencia.getFolioAudiencia());
            audienciaNew.setTipo(new Valor(audiencia.getTipoAudienciaId()));
            audienciaNew.setDuracionEstimada(audiencia.getDuracionEstimada());
            audienciaNew.setFechaAudiencia(audiencia.getFechaHoraAudiencia());
            audienciaNew.setFechaAsignacionSala(audiencia
                    .getFechaAsignacionSala());
            audienciaNew
                    .setEstatus(new Valor(audiencia.getEstatusAudienciaId()));
            audienciaNew.setEstatus(new Valor(EstatusAudiencia.PROGRAMADA
                    .getValorId()));
            idAudiencia = audienciaDAO.create(audienciaNew);
        } else {
            idAudiencia = audExistente.getAudienciaId();
        }
        AudienciaDTO audDTO = new AudienciaDTO();
        audDTO.setSala(new SalaAudienciaDTO());
        audDTO.setId(idAudiencia);
        audDTO.setFechaEvento(audiencia.getFechaHoraAudiencia());
        audDTO.setDuracionEstimada(audiencia.getDuracionEstimada());

        if (audiencia.isSalaTemporal()) {
            audDTO.getSala().setTemporal(true);
            audDTO.getSala().setMotivo(audiencia.getMotivo());
            audDTO.getSala().setDomicilioSala(audiencia.getDomicilioSala());
            audDTO.getSala().setUbicacionSala(audiencia.getUbicacionSala());
            asignarSalaTemporalService.asignarSalaTemporal(audDTO);
        } else {
            // Buscar sala por nombre y descripcion , si no se encuentra crear
            // nueva
            SalaAudiencia sala = buscarSalaAudiencia(audiencia);
            if (sala == null) {
                sala = crearSalaAudiencia(audiencia);
            }
        }
        return idAudiencia;
    }
    
    /**
     * Busca una sala de audiencia por nombre y descripción, si no se encuentra
     * se crea sala nueva
     * 
     * @param solicitud
     * @return
     */
    private SalaAudiencia buscarSalaAudiencia(
    		AudienciaWSDTO audienciaNot) {
        SalaAudiencia filtro = new SalaAudiencia();
        filtro.setNombreSala(audienciaNot.getNombreSala());
        filtro.setDomicilioSala(audienciaNot.getDomicilioSala());
        filtro.setUbicacionSala(audienciaNot.getUbicacionSala());

        List<SalaAudiencia> salas = salaAudienciaDAO
                .consultarSalasPorFiltro(filtro);
        return salas != null && !salas.isEmpty() ? salas.get(0) : null;
    }
    
    /**
     * Crea una nueva sala de audiencia basada en los datos de la solicitud de
     * defensor
     * 
     * @param solicitud
     *            Datos de origen
     * @return Sala creada
     */
    private SalaAudiencia crearSalaAudiencia(
    		AudienciaWSDTO audienciaNot) {
        SalaAudiencia sala = new SalaAudiencia();
        sala.setNombreSala(audienciaNot.getNombreSala());
        sala.setDomicilioSala(audienciaNot.getDomicilioSala());
        sala.setUbicacionSala(audienciaNot.getUbicacionSala());
        sala.setEsActivo(true);
        salaAudienciaDAO.create(sala);
        return sala;
    }
}

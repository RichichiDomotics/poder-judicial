/**
 * Nombre del Programa : SolicitarAudienciaBasicoServiceImpl.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.SolicitarAudienciaBasicoService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.expediente.ActualizarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosPorRolService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.ExpedienteWSDTOTransformer;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementaci�n del servicio de negocio para registra la solicitud de una
 * audiencia con datos b�sicos, ya sea interna o deste otra �rea
 * 
 * @version 1.0
 * @author Emigdio Hern�ndez
 * 
 */
@Service("solicitarAudienciaBasicoService")
@Transactional
public class SolicitarAudienciaBasicoServiceImpl
        implements
            SolicitarAudienciaBasicoService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(SolicitarAudienciaBasicoServiceImpl.class);
    @Autowired
    private AudienciaDAO audienciaDAO;
    @Autowired
    private SolicitudAudienciaDAO solicitudAudienciaDAO;
    @Autowired
    private CasoDAO casoDAO;
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    @Autowired
    private AsignarNumeroExpedienteService asignarNumExpService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ParametroDAO paramDao;
    @Autowired
    ActualizarCarpetaDeInvestigacionService actualizarCarpetaDeInvestigacionService;
    @Autowired
    private InvolucradoDAO involucradoDao;
    @Autowired
    private InvolucradoAudienciaDAO invoAudDao;

    @Autowired
    private FuncionarioDAO funcionarioDao;
    @Autowired
    private FuncionarioAudienciaDAO funAudDao;
    @Autowired
    private ConsultarFuncionariosPorRolService consultarFuncionariosPorRolService;
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private CatDiscriminateDAO catDiscriminateDAO;
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.audiencia.SolicitarAudienciaBasicoService#
     * registrarSolicitudAudienciaBasico
     * (mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoDTO)
     */
    @Override
    public Long registrarSolicitudAudienciaBasico(
            SolicitudAudienciaBasicoWSDTO solicitudWs)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarSolicitudAudienciaBasico(...)");
        logger.debug("Recibiendo solicitud con folio :: "
                + solicitudWs.getFolioSolicitud());
        
        logger.info(" SE RECIBE LA SOLICITUD: " + 
    			" idDistrito:" + solicitudWs.getDistritoId()+ 
    			" idTribunal:" +solicitudWs.getTribunalId() + 
    			" idClaveFuncionario:" + solicitudWs.getClaveFuncionarioId());
        
        Valor estadoSolicitud = new Valor(EstatusSolicitud.ABIERTA.getValorId());
        Valor tipoSolicitud = new Valor(TiposSolicitudes.AUDIENCIA.getValorId());
        SolicitudAudiencia solicitudAudiencia = null;
        NumeroExpediente numExpediente = null;
        Long idSolAud = null;
        if (solicitudWs != null && solicitudWs.getDistritoId()!=null && solicitudWs.getTribunalId()!=null && solicitudWs.getClaveFuncionarioId()!=null ) {
        	//FIXME GBP Se debe de quitar el codigo q busca funccionario Por Discriminante y pasar el de buscar por clave
        	//Consultar los funcionarios del Tribuna que sean del rol AdmonPJ
        	List<FuncionarioDTO> funcionariosDTO = consultarFuncionariosPorRolService.consultarFuncionariosPorDicriminanteYRol(solicitudWs.getTribunalId(), Roles.ADMONPJ.getValorId(),null);
        	UsuarioDTO usuarioDTO = null;
        	if(funcionariosDTO!=null && !funcionariosDTO.isEmpty() || funcionariosDTO.get(0)==null || funcionariosDTO.get(0).getUsuario().getUsuario()==null){
        		Usuario usuario = usuarioDAO.read(funcionariosDTO.get(0).getUsuario().getIdUsuario());
        		usuarioDTO = UsuarioTransformer.transformarUsuario(usuario );
        	}
        	else //Si no existe el no se recibe la solicitud
        		return idSolAud;
//        	Funcionario funcionario = funcionarioDao.read(solicitudWs.getClaveFuncionarioId());
//        	if(funcionario==null || funcionario.getUsuario()==null)
//        		return idSolAud;
            solicitudAudiencia = EventoTransformer
                    .transformarSolicitudAudienciaBasico(solicitudWs);
            solicitudAudiencia.setTipoSolicitud(tipoSolicitud);
            solicitudAudiencia.setEstatus(estadoSolicitud);
            // Buscar expediente con el n�mero de caso asociado
            if (solicitudWs.getNumeroCasoAsociado() != null) {
                numExpediente = obtenerNumeroExpedienteDeCaso(solicitudWs
                        .getNumeroCasoAsociado(), usuarioDTO);
            }
            solicitudAudiencia.setNumeroCasoAsociado(solicitudWs
                    .getNumeroCasoAsociado());
            solicitudAudiencia.getAudiencia()
                    .setNumeroExpediente(numExpediente);
            solicitudAudiencia.setNumeroExpediente(numExpediente);
            solicitudAudiencia.getAudiencia().setConsecutivo((short) 1);
            solicitudAudiencia.getAudiencia().setTipo(
                    new Valor(solicitudWs.getAudiencia().getTipoAudienciaId()));
            solicitudAudiencia.getAudiencia().setEstatus(
                    new Valor(solicitudWs.getAudiencia()
                            .getEstatusAudienciaId()));

            solicitudAudiencia
                    .setForma(new Forma(Formas.SOLICITUD.getValorId()));
            solicitudAudiencia.setNombreDocumento(StringUtils.EMPTY);
            solicitudAudiencia.setTipoDocumento(new Valor(
                    TipoDocumento.SOLICITUD_DE_AUDIENCIA.getValorId()));

            solicitudAudiencia.setConfInstitucion(new ConfInstitucion(
                    solicitudWs.getConfInstitucionId()));
            solicitudAudiencia.setFolioSolicitud(solicitudWs
                    .getFolioSolicitud());
            final Long idAud = audienciaDAO.create(solicitudAudiencia
                    .getAudiencia());
            logger.debug("idAud :: " + idAud);
            idSolAud = solicitudAudienciaDAO.create(solicitudAudiencia);
            ExpedienteWSDTO expWSD = new ExpedienteWSDTO();
            expWSD.setInvolucradosDTO(solicitudWs.getInvolucradosDTO());
            expWSD.setObjetosDTO(solicitudWs.getObjetosDTO());
            ExpedienteDTO expParamActualizar = ExpedienteWSDTOTransformer
                    .expedienteWsdto2ExpedienteDto(expWSD);
            expParamActualizar.setExpedienteId(numExpediente.getExpediente()
                    .getExpedienteId());
            actualizarCarpetaDeInvestigacionService
                    .actualizarExpedienteDeCarpetaInvestigacionDefensoria(expParamActualizar);
            //Se actualiza el discriminante del expediente
        	Expediente expedienteBD = expedienteDAO.read(numExpediente.getExpediente()
                    .getExpedienteId());
        	CatDiscriminante dicriminante = catDiscriminateDAO.read(solicitudWs.getTribunalId());
        	expedienteBD.setDiscriminante(dicriminante);
        	expedienteDAO.update(expedienteBD);
        			
            if (expWSD.getInvolucradosDTO() != null) {
                for (InvolucradoWSDTO invow : expWSD.getInvolucradosDTO()) {
                    Involucrado invoPojo = involucradoDao
                            .consultarInvolucradoPorFolioElemento(invow
                                    .getFolioElemento());
                    InvolucradoAudiencia invoAud = new InvolucradoAudiencia();
                    InvolucradoAudienciaId iaID = new InvolucradoAudienciaId();
                    iaID.setaudienciaId(idAud);
                    iaID.setInvolucradoId(invoPojo.getElementoId());
                    invoAud.setId(iaID);
                    this.invoAudDao.create(invoAud);
                }
            }
            if (solicitudWs.getSolicitante() != null) {
                final StringBuffer nombreCompleto = new StringBuffer(
                        solicitudWs.getSolicitante().getNombre());
                nombreCompleto.append(" ").append(
                        solicitudWs.getSolicitante().getApellidoPaterno());
                nombreCompleto.append(" ").append(
                        solicitudWs.getSolicitante().getApellidoMaterno());
                Funcionario funLocal = funcionarioDao
                        .obtenerFuncionarioPorNombreCompleto(nombreCompleto
                                .toString());
                if (funLocal == null) {
                    funLocal = crearFuncionario(solicitudWs);
                }
                logger.debug("funLocal.getClaveFuncionario() :: " + funLocal.getClaveFuncionario());
                final FuncionarioAudiencia funAud = new FuncionarioAudiencia();
                final FuncionarioAudienciaId faID = new FuncionarioAudienciaId();
                faID.setAudienciaId(idAud);
                faID.setClaveFuncionario(funLocal.getClaveFuncionario());
                funAud.setId(faID);
                this.funAudDao.create(funAud);

            }
            logger.info("Id Solicitud Creada :: [" + idSolAud + "]");

        }
        logger.info(" REGRESANDO RESPUESTA:"+ idSolAud);
        return idSolAud;

    }
    
    /**
     * @param solicitudWs
     * @return
     */
    private Funcionario crearFuncionario(
            SolicitudAudienciaBasicoWSDTO solicitudWs) {
        Funcionario funLocal;
        funLocal = new Funcionario();
        funLocal.setNombreFuncionario(solicitudWs.getSolicitante().getNombre());
        funLocal.setApellidoPaternoFuncionario(solicitudWs.getSolicitante()
                .getApellidoPaterno());
        funLocal.setApellidoMaternoFuncionario(solicitudWs.getSolicitante()
                .getApellidoMaterno());
        funLocal.setPuesto(new Valor(solicitudWs.getSolicitante().getPuestoId()));
        funLocal.setTipoEspecialidad(new Valor(solicitudWs.getSolicitante()
                .getTipoEspecialidadId()));
        funLocal.setEspecialidad(new Valor(solicitudWs.getSolicitante()
                .getEspecialidadId()));
        funLocal.setArea(new JerarquiaOrganizacional(solicitudWs.getSolicitante().getJerarquiaOrganizacionalId()));
        funLocal.setClaveFuncionario(this.funcionarioDao.create(funLocal));
        return funLocal;
    }
    /**
     * Busca un NumeroExpediente en base a un caso, si el caso no existe o no
     * tiene expedientes se crea un primer expediente y n�mero de expediente
     * 
     * @param numeroCasoAsociado
     * @return
     */
    private NumeroExpediente obtenerNumeroExpedienteDeCaso(
            String numeroCasoAsociado, UsuarioDTO usuarioDTO) throws NSJPNegocioException {
        // TODO: Revisar la l�gica de asignaci�n de expediente a
        // SolicitudAudiencia
        List<Caso> casos = casoDAO.consultarCasosPorNumero(numeroCasoAsociado);
        Caso caso = !casos.isEmpty() ? casos.get(0) : null;
        Expediente exp = null;
        NumeroExpediente numExp = null;
        boolean masDeUnExpediente = false;
        if (caso != null) {
            if (caso.getExpedientes().size() == 1) {
                exp = caso.getExpedientes().iterator().next();
                if (exp.getNumeroExpedientes().size() == 1) {
                    numExp = exp.getNumeroExpedientes().iterator().next();
                } else if (exp.getNumeroExpedientes().size() > 1) {
                    masDeUnExpediente = true;
                }
            } else if (caso.getExpedientes().size() > 1) {
                masDeUnExpediente = true;
            }

        }

        if (exp != null) {// hay expediente
            if (numExp != null) { // solo un n�mero
                logger.debug("Expediente localizado");
                return numExp;
            } else {
                if (numExp == null) { // no hay numero
                    // Crear n�mero

                    ExpedienteDTO expParam = new ExpedienteDTO();
                    // Usuario que "recibe" temporalmente el expediente
                    // Encargado Causa
                    // FIXME VAP
//                    Parametro paramUsr = this.paramDao
//                            .obtenerPorClave(Parametros.ID_USUARIO_ROBOT_SISTEMA);
//                    UsuarioDTO usuario = usuarioService
//                            .consultarUsuarioPorClaveFuncionario(paramUsr
//                                    .getValorAsLong());

                    expParam.setExpedienteId(exp.getExpedienteId());
                    expParam.setArea(usuarioDTO.getAreaActual());
                    expParam.setUsuario(usuarioDTO);
                    expParam = this.asignarNumExpService
                            .asignarNumeroExpediente(expParam);
                    numExp = new NumeroExpediente();
                    numExp.setNumeroExpedienteId(expParam
                            .getNumeroExpedienteId());
                    numExp.setExpediente(exp);
                }
            }
        } else {
            // Crear expediente y n�mero
            Expediente expediente=new Expediente();
            Long expedienteid = expedienteDAO.create(expediente);
            ExpedienteDTO expParam = new ExpedienteDTO();
            // Usuario que "recibe" temporalmente el expediente Encargado Causa
            // FIXME VAP
//            UsuarioDTO usuario = usuarioService
//                    .consultarUsuarioPorClaveFuncionario(16L);

            //expParam.setExpedienteId(exp.getExpedienteId());
            expParam.setFechaApertura(exp.getFechaCreacion());
            expParam.setExpedienteId(expedienteid);
            expParam.setArea(usuarioDTO.getAreaActual());
            expParam.setUsuario(usuarioDTO);
            expParam = this.asignarNumExpService
                    .asignarNumeroExpediente(expParam);
            numExp = new NumeroExpediente();
            numExp.setNumeroExpedienteId(expParam.getNumeroExpedienteId());
            numExp.setExpediente(new Expediente(expParam.getExpedienteId()));
        }
        return numExp;
    }
}

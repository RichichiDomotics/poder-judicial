/**
 * Nombre del Programa : AsignarNumeroExpedienteServiceImpl.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Apr 2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.ConsecutivosUtil;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteAdscritoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteRestDefensoriaDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteTecnicoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.ExpedienteAdscrito;
import mx.gob.segob.nsjp.model.ExpedienteRestDefensoria;
import mx.gob.segob.nsjp.model.ExpedienteTecnico;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.actividad.AdministradorActividadesService;
import mx.gob.segob.nsjp.service.caso.AsignarNumeroCasoService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service
@Transactional
public class AsignarNumeroExpedienteServiceImpl
        implements
            AsignarNumeroExpedienteService {

    @Autowired
    private AdministradorActividadesService adminActividadesService;
    

    private String SEPARAR = "/";
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private ExpedienteRestDefensoriaDAO expRestDefensoriaDAO;
    @Autowired
    private ExpedienteAdscritoDAO expedienteAdscritoDAO;
    @Autowired
    private ExpedienteTecnicoDAO expedienteTecnicoDAO;
    @Autowired
    private TurnoDAO turnoDao;
    
    @Autowired
    private AsignarNumeroCasoService casoService;

    @Autowired
    private NumeroExpedienteDAO noExpDao;
    @Autowired
    private SolicitudDAO solicitudDAO;
    @Autowired
    private ConfInstitucionDAO confInsDao;
    @Autowired
    private CasoDAO casoDao;
	@Autowired
	private ConfInstitucionDAO confInstitucionDAO;
	@Autowired
	private RelNumExpedienteAuditoriaDAO relNumExpedienteAuditoriaDAO;
	@Autowired
	private AsignarNumeroCasoService asignarNumeroCasoService;
	@Autowired
	private ValorDAO valorDAO;
    @Autowired
    private CatDiscriminateDAO discriminateDAO;
    @Autowired


    private static final Logger logger = Logger
            .getLogger(AsignarNumeroExpedienteServiceImpl.class);

    
    
    private synchronized String obtenerNumeroExpediente(Long idArea, UsuarioDTO aoUsuarioFirmado) throws NSJPNegocioException{
    	
    	String libre = "RAC";
        //String libre = "RAC";
    	
    	if(idArea == Areas.AGENCIA_DEL_MINISTERIO_PUBLICO.ordinal()){
    		libre = "SJT";
    	}
        
        //YUC
        String entidadFederativa = "";
        String institucion = confInsDao.consultarInsitucionActual().getMonograma();
        Calendar calTemp = Calendar.getInstance();
        String año = String.valueOf(calTemp.get(Calendar.YEAR));
        String ultimoNumero = null;
        /*CatDiscriminateDAO CatDiscriminante=null;
        FuncionarioDAO funcionario = null;
        TareaDTO tarea = null;*/

        /*FuncionarioDAO funcionarioDAO=null;

        FuncionarioDTO funcionarioDTO=aoUsuarioFirmado.getFuncionario();

        UsuarioDTO usuarioDTO=null;

        TareaDTO tareaDTO=null;
        logger.debug(aoUsuarioFirmado.getFuncionario().getDiscriminante().getClave()+" id del funcionario");*/
        //String discriminante= funcionarioDAO.obtenerDiscriminanteFuncionario(funcionarioDTO.getClaveFuncionario().toString());
        String Agencia=aoUsuarioFirmado.getFuncionario().getDiscriminante().getClave(); //discriminateDAO.consultarClaveDiscrimiante(discriminante);
        logger.debug(Agencia+" Clave de la agencia");

        // Si es procuraduria consulta el ultimo numero de expediente no importando el area
        ConfInstitucion confInstitucion = confInstitucionDAO.consultarInsitucionActual();
        if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) && idArea != Areas.AGENCIA_DEL_MINISTERIO_PUBLICO.ordinal())
        	ultimoNumero = expedienteDAO.obtenerUltimoNumeroDeExpediente(null);
        else
        	ultimoNumero = expedienteDAO.obtenerUltimoNumeroDeExpediente(idArea);

        String incrementoString = "";
        logger.debug(ultimoNumero+" este es el ultimo consecutivo");
        /*if (ultimoNumero != null) {
            String consecutivo = ultimoNumero.substring(ultimoNumero.length() - 5, ultimoNumero.length());
            incrementoString = ConsecutivosUtil.incrementarConsecutivo(consecutivo, 5);
        } else {
            incrementoString = ConsecutivosUtil.incrementarConsecutivo(null, 5);
        }

        StringUtils.leftPad(incrementoString, 5, "0");*/
         incrementoString=consultarConsecutivoExpediente(ultimoNumero);
        //YUC se hace referencia al mismo servicio que consulta el prefijo del estado.
        entidadFederativa = asignarNumeroCasoService.obtenerPrefijoDelEstado();
        
        String numeroExpediente = null;
        

//        if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) ){
        	numeroExpediente = libre+SEPARAR
                    //+ entidadFederativa
                    + "12"+SEPARAR
                    //+ institucion+SEPARAR;
                    + Agencia+SEPARAR;
                    /*+ (aoUsuarioFirmado != null && aoUsuarioFirmado.getFuncionario() != null && aoUsuarioFirmado.getFuncionario().getDiscriminante() != null && aoUsuarioFirmado.getFuncionario().getDiscriminante().getDistrito() != null ? aoUsuarioFirmado.getFuncionario().getDiscriminante().getDistrito().getClaveDistrito() : "--");
        	if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId())||confInstitucion.getConfInstitucionId().equals(Instituciones.PJ.getValorId()) ){
        		numeroExpediente=numeroExpediente+ (aoUsuarioFirmado != null && aoUsuarioFirmado.getFuncionario() != null 
        				&& aoUsuarioFirmado.getFuncionario().getDiscriminante() != null 
        				&& aoUsuarioFirmado.getFuncionario().getDiscriminante().getClave() != null ? 
        						aoUsuarioFirmado.getFuncionario().getDiscriminante().getClave() : "---");
        	}else{
        		numeroExpediente=numeroExpediente+"000";
        	}*/
        	
        	
                    numeroExpediente=numeroExpediente+ año+SEPARAR+ incrementoString;//nueva estructura del rac para hidalgo 06-ene-2015 R.H.R. DOMOTICS
                    //numeroExpediente= año+ incrementoString;
//        	logger.info("FOLIO GENERADO: " + numeroExpediente + " EN PG");
//        }else{
//        	numeroExpediente = libre
//                    + entidadFederativa
//                    + institucion
//                    + a�o
//                    + (idArea < 10 ? "0" + idArea : String.valueOf(idArea)) 
//                    + incrementoString;
//        	logger.info("FOLIO GENERADO: " + numeroExpediente + " EN OTRA INSTITUCION");
//        }
        
        
        
        return numeroExpediente;
    	
	}
    public String consultarConsecutivoExpediente(String ultimoNumeroGeneralCaso) throws NSJPNegocioException{
        String consecutivoDelCaso = "";
        logger.debug("longitud cadena ultimo numero general caso");
        //if(ultimoNumeroGeneralCaso!=null && ultimoNumeroGeneralCaso.length() == 21) {
        logger.debug(ultimoNumeroGeneralCaso+" ultimo numero de caso");
        if(ultimoNumeroGeneralCaso!=null && ultimoNumeroGeneralCaso.length() == 21) {
            logger.debug("longitud cadena ultimo numero general caso paso longitud");
            //pe 01/02/XX/RBO/2011/CC-12345 -> CC-12346
            consecutivoDelCaso = ConsecutivosUtil.incrementarConsecutivoNumeroExpediente2(ultimoNumeroGeneralCaso);

        } else {
            consecutivoDelCaso = "00001";//SE CAMBIA EL CONSECUTIVO DE AA-00000 POR 00000 03-DIC-2014 DOMOTICS
        }
        return consecutivoDelCaso;
    }
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public synchronized ExpedienteDTO asignarNumeroExpedienteDefensoria(
            ExpedienteDTO input) throws NSJPNegocioException {
        if (logger.isDebugEnabled()){
            logger.debug("inputExpediente :: "+ input);
        }
        //INI SCRZ 05-01-12
        
        if (input.getArea() == null || input.getArea().getAreaId() == null||input.getUsuario()==null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        
        String strNumeroExpediente = obtenerNumeroExpediente(Areas.DEFENSORIA.parseLong(), input.getUsuario());
        //FIN SCRZ 05-01-12
        
        Calendar calendar = Calendar.getInstance();
        Expediente expediente = null;
        if(input.getExpedienteId() == null){
        	expediente = new Expediente();
        }else{
        	expediente = ExpedienteTransformer.transformarExpedienteBasicoDefensoria(input);
        }
        expediente.setFechaCreacion(new Date());
        NumeroExpediente numeroExpediente = new NumeroExpediente();
        
        numeroExpediente.setNumeroExpediente(strNumeroExpediente);
        numeroExpediente.setFechaApertura(calendar.getTime());
        numeroExpediente.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
        numeroExpediente.setJerarquiaOrganizacional(new JerarquiaOrganizacional(Areas.DEFENSORIA.parseLong()));
        numeroExpediente.setFuncionario( new Funcionario( input.getUsuario().getFuncionario().getClaveFuncionario()));
        
        if (input.getFechaApertura()==null){
        	input.setFechaApertura(calendar.getTime());
        }

        if(input.getTipoExpediente() != null){
        	numeroExpediente.setTipoExpediente(new Valor(input.getTipoExpediente().getIdCampo()));
        }
        
        if(input.getEtapa() != null){
        	numeroExpediente.setEtapa(new Valor(input.getEtapa().getIdCampo()));
        	Long idEtapa = input.getEtapa().getIdCampo();
        	numeroExpediente.setEtapa(new Valor(idEtapa));
        	
        	Long idEstatus = EstatusExpediente.ABIERTO_RESTAURATIVA.getValorId();
        	switch(EtapasExpediente.getByValor(idEtapa)){
    			case ASESORIA:
    				idEstatus = EstatusExpediente.ABIERTO.getValorId();
    				break;
        		case CONCILIACION_Y_MEDIACION:
        			idEstatus = EstatusExpediente.ABIERTO_RESTAURATIVA.getValorId();
        			break;
        		case INTEGRACION:
        			idEstatus = EstatusExpediente.ABIERTO_INTEGRACION.getValorId();
        			break;
        		case TECNICA:
        			idEstatus = EstatusExpediente.ABIERTO_TECNICA_SIN_CARPETA.getValorId();
        			break;
        		case EJECUCION:
        			idEstatus = EstatusExpediente.ABIERTO_EJECUCION.getValorId();
        			break;
        	}
        	numeroExpediente.setEstatus(new Valor(idEstatus));
        }
        
        if (input.getExpedienteId() == null) {
            if (input.getCasoDTO() != null){
            	if(input.getCasoDTO().getCasoId() != null) {
            		expediente.setCaso(new Caso(input.getCasoDTO().getCasoId()));
            	}else{
            		Caso caso = casoDao.obtenerCasoByNumeroGeneral(input.getCasoDTO().getNumeroGeneralCaso());
            		if(caso != null){
            			expediente.setCaso(caso);
            		}
            	}
            }
        	expediente.setNumeroExpediente(strNumeroExpediente);
        	
        	if(expediente.getExpedienteId() != null){
        		expedienteDAO.update(expediente);
        	}else{
        		expediente.setExpedienteId(expedienteDAO.create(expediente));
        	}
        	input.setExpedienteId(expediente.getExpedienteId());
        }
        numeroExpediente.setExpediente(new Expediente(input.getExpedienteId()));
        
        Long idNumeroExpediente = this.noExpDao.create(numeroExpediente);

        input.setNumeroExpediente(strNumeroExpediente);
        input.setNumeroExpedienteId(idNumeroExpediente);
        return input;
    }
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public synchronized ExpedienteDTO asignarNumeroExpediente(
            ExpedienteDTO inputExpediente) throws NSJPNegocioException {
    	//Permite saber si es necesario usar el mismo numero de expediente para procuraduraia
    	boolean esMismoNumeroExpediente = false;
    	
        logger.info("Inicia - asignarNumeroExpediente(...)");
        
        if (logger.isDebugEnabled()){
            logger.debug("inputExpediente :: "+ inputExpediente);
        }
        if (inputExpediente.getArea() == null
        		|| inputExpediente.getArea().getAreaId() == null) {
        	throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        
        ExpedienteDTO nuevoExp = new ExpedienteDTO();

        /* Si ya tiene un n�mero de expediente para el �rea, ya no se debe de generar uno nuevo,
        por el contrario se debe de consultar. Solo aplica para la instituci�n de PGJ */
        ConfInstitucion confInstitucion = confInstitucionDAO.consultarInsitucionActual();
        logger.debug("EL valor de confInstitucion :: "+ inputExpediente);
        if(inputExpediente.getExpedienteId() != null && confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) ){

        	//Buscar el �ltimo n�mero de expediente asociado al expedidiente y por area.
        	//Regresa nulo en caso de que no exista un numero expediente asociado a esa �rea
        	Expediente expediente = expedienteDAO.buscarUltimoNumeroPorExpedienteIdAreaId(
        			inputExpediente.getExpedienteId(), 
        			inputExpediente.getArea().getAreaId());
            logger.debug("EL Expediente :: "+ expediente);
        	
        	if(expediente !=null ){
        		nuevoExp.setNumeroExpediente(expediente.getNumeroExpediente());
        		nuevoExp.setNumeroExpedienteId(expediente.getNumeroExpedienteId());
        		logger.info("##Datos NEX - NumeroExpediente:"+ nuevoExp.getNumeroExpediente() + " - NumeroExpedienteId:" + nuevoExp.getNumeroExpedienteId());
        		return nuevoExp;
        	}else{
        		//PASO NECESARIO PARA UNIFICAR EL NUMERO DE EXPEDIENTE EN PROCURADURIA
        		expediente = expedienteDAO.buscarUltimoNumeroPorExpedienteIdAreaId(
            			inputExpediente.getExpedienteId(), null);
        		
        		if(expediente !=null ){
            		nuevoExp.setNumeroExpediente(expediente.getNumeroExpediente());
            		nuevoExp.setNumeroExpedienteId(expediente.getNumeroExpedienteId());
            		esMismoNumeroExpediente = true;
            	}
        		
        	}
        }
        
        Expediente expediente = new Expediente();
        expediente.setPertenceConfInst(confInstitucionDAO.consultarInsitucionActual());

        expediente.setFechaCreacion(new Date());


        Calendar calTemp = Calendar.getInstance();
        if (inputExpediente.getFechaApertura()!=null){
        	calTemp.setTime(inputExpediente.getFechaApertura()); 
        }else{
        	calTemp.setTime(new Date());
        	inputExpediente.setFechaApertura(new Date());
        }
        UsuarioDTO loUsuarioFirmado = inputExpediente.getUsuario();
        String numeroExpediente  = null;
        if(esMismoNumeroExpediente == true)
        	numeroExpediente = nuevoExp.getNumeroExpediente();
        else
        	numeroExpediente = obtenerNumeroExpediente(inputExpediente.getArea().getAreaId(),loUsuarioFirmado);
        
        logger.info("el numeroExpediente GENERADO ES: " + numeroExpediente);


        expediente.setNumeroExpediente(numeroExpediente);

        if (inputExpediente.getCasoDTO() != null
                && inputExpediente.getCasoDTO().getCasoId() != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Asociando al caso con id :: "
                        + inputExpediente.getCasoDTO().getCasoId());
            }
            expediente.setCaso(new Caso(inputExpediente.getCasoDTO()
                    .getCasoId()));
        }
        

        this.adminActividadesService.generarActividad(inputExpediente, nuevoExp, expediente);

        NumeroExpediente noExpBD = new NumeroExpediente();
        noExpBD.setNumeroExpediente(numeroExpediente);
//        noExpBD.setFechaApertura(inputExpediente.getFechaApertura());FIXME VAP
        noExpBD.setFechaApertura(new Date());

        if (inputExpediente.getExpedienteId() == null) {
            nuevoExp.setExpedienteId(expediente.getExpedienteId());
            noExpBD.setExpediente(new Expediente( expediente.getExpedienteId()));
        } else {
            nuevoExp.setExpedienteId(inputExpediente.getExpedienteId());
            noExpBD.setExpediente(new Expediente(inputExpediente.getExpedienteId()));
        }
        if (inputExpediente.getCausaPadre()!=null){
            noExpBD.setNumeroExpedientePadre(new NumeroExpediente(inputExpediente.getCausaPadre().getNumeroExpedienteId()));
            noExpBD.setTipoExpediente(new Valor(inputExpediente.getTipoExpediente().getIdCampo()));
        }
        
        noExpBD.setJerarquiaOrganizacional(new JerarquiaOrganizacional(inputExpediente.getArea().getAreaId()));
        noExpBD.setFuncionario( new Funcionario( inputExpediente.getUsuario().getFuncionario().getClaveFuncionario()));
        if (inputExpediente.getTipoExpediente()!=null && inputExpediente.getTipoExpediente().getIdCampo()!=null){
        	noExpBD.setTipoExpediente(new Valor(inputExpediente.getTipoExpediente().getIdCampo()));
        }
        if (inputExpediente.getEstatus()!=null && inputExpediente.getEstatus().getIdCampo()!=null){
            noExpBD.setEstatus(new Valor(inputExpediente.getEstatus().getIdCampo()));
        }
        Long idNoExpNuevo = this.noExpDao.create(noExpBD);

        nuevoExp.setNumeroExpediente(numeroExpediente);
        nuevoExp.setNumeroExpedienteId(idNoExpNuevo);
        nuevoExp.setFechaApertura(inputExpediente.getFechaApertura());
        if (inputExpediente.getEstatus() != null
                && inputExpediente.getEstatus().getIdCampo() != null) {
            nuevoExp.setEstatus(new ValorDTO(inputExpediente.getEstatus()
                    .getIdCampo()));
        }
    
        return nuevoExp;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public synchronized ExpedienteDTO asignarNumeroExpedienteTipo(
            ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
        if (logger.isDebugEnabled())
            logger.debug("/**** ASIGNAR NUMERO DE EXPEDIENTE DEL TIPO ****/");

        String ultimoNumero = "";
        String libre = "NSJ";
        //YUC
        String entidadFederativa = "";
        String institucion = "DEFE";
        String numeroExpediente = "";
        Long expedienteId = new Long(0);

        Calendar calTemp = Calendar.getInstance();
        String año = String.valueOf(calTemp.get(Calendar.YEAR));
        
        //YUC se hace referencia al mismo servicio que consulta el prefijo del estado.
        entidadFederativa = asignarNumeroCasoService.obtenerPrefijoDelEstado();
        
        numeroExpediente = libre + entidadFederativa + institucion + año;

        Expediente expediente = ExpedienteTransformer
                .transformarExpediente(expedienteDTO);

        ultimoNumero = expedienteDAO.obtenerUltimoNumero(1L); //TODO CAS Quitar codigo duro al enviar el idArea        
//        expediente.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
        if (expediente instanceof ExpedienteAdscrito) {
            ExpedienteAdscrito expedienteAd = (ExpedienteAdscrito) expediente;
            // ultimoNumero =
            // expedienteDAO.obtenerUltimoNumTipoExp("ExpedienteAdscrito");
            numeroExpediente = numeroExpediente
                    + formatoUltimoNumero(ultimoNumero);
            expedienteAd.setNumeroExpediente(numeroExpediente);
            expedienteId = expedienteAdscritoDAO.create(expedienteAd);
            logger.debug("ExpedienteAdscrito");
        } else if (expediente instanceof ExpedienteTecnico) {
            ExpedienteTecnico expedienteTec = (ExpedienteTecnico) expediente;
            // ultimoNumero =
            // expedienteDAO.obtenerUltimoNumTipoExp("ExpedienteTecnico");
            numeroExpediente = numeroExpediente
                    + formatoUltimoNumero(ultimoNumero);
            expedienteTec.setNumeroExpediente(numeroExpediente);
            expedienteId = expedienteTecnicoDAO.create(expedienteTec);
            logger.debug("ExpedienteTecnico");
        } else if (expediente instanceof ExpedienteRestDefensoria) {
            ExpedienteRestDefensoria expedienteRest = (ExpedienteRestDefensoria) expediente;
            // ultimoNumero =
            // expedienteDAO.obtenerUltimoNumTipoExp("ExpedienteRestDefensoria");
            numeroExpediente = numeroExpediente
                    + formatoUltimoNumero(ultimoNumero);
            expedienteRest.setNumeroExpediente(numeroExpediente);
            expedienteId = expRestDefensoriaDAO.create(expedienteRest);
            logger.debug("ExpedienteRestDefensoria");
        }
        return new ExpedienteDTO(expedienteId);
    }

    public String formatoUltimoNumero(String ultimoNumero) {
        String incrementoString = null;
        if (ultimoNumero != null) {
            String consecutivo = ultimoNumero.substring(
                    ultimoNumero.length() - 5, ultimoNumero.length());

            incrementoString = ConsecutivosUtil.incrementarConsecutivo(
                    consecutivo, 6);
        } else {
            logger.debug("Comienza el incremento");
            incrementoString = ConsecutivosUtil.incrementarConsecutivo(null, 6);
        }

        // rellenar con ceros a la izq
        StringUtils.leftPad(incrementoString, 5, "0");

        return incrementoString;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public synchronized ExpedienteDTO asignarNumeroExpediente(TurnoDTO turno)
            throws NSJPNegocioException {

        logger.debug("turno en asignarNumeroExpediente(TurnoDTO turno):: " + turno);
        if (turno.getUsuario() == null
                || turno.getUsuario().getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        // se crea el caso como paliativo, debe existir un bot�n que genere el caso
        final CasoDTO casoReq = new CasoDTO();
        casoReq.setFechaApertura(new Date());
        casoReq.setEstatus(EstatusCaso.INVESTIGACION);
        //final  CasoDTO niuCaso = this.casoService.asignarNumeroCaso(casoReq,obtenerFuncionario());
        CasoDTO niuCaso = new CasoDTO();
        
        ExpedienteDTO expParam = new ExpedienteDTO();
        expParam.setFechaApertura(new Date());
        expParam.setUsuario(turno.getUsuario());
        expParam.setArea(turno.getExpediente().getArea());
        /*if(turno.getTipoTurno().toString() == "ADMINISTRATIVO") {
            expParam.setCasoDTO(null);
        }
        else{
            niuCaso = this.casoService.asignarNumeroCaso(casoReq,obtenerFuncionario());
            expParam.setCasoDTO(niuCaso);
        }*/
        logger.debug("Antes de llamar a asignarNumeroExpediente(expParam)");
        ExpedienteDTO expNuevo = asignarNumeroExpediente(expParam);
        logger.debug("Despues de llamar a asignarNumeroExpediente(expParam)");

        Turno tnoBD = this.turnoDao.read(turno.getTurnoId());
        tnoBD.setExpediente(new Expediente(expNuevo.getExpedienteId()));
        tnoBD.setUsuario(new Usuario(turno.getUsuario().getIdUsuario()));
        tnoBD.setFechaAtencion(new Date());
        tnoBD.setEstatus(new Valor(EstatusTurno.ATENDIDO.getValorId()));

        this.turnoDao.update(tnoBD);
        expNuevo.setCasoDTO(niuCaso);
        return expNuevo;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public synchronized ExpedienteDTO asignarNumeroExpedientePenal(TurnoDTO turno)
            throws NSJPNegocioException {

        logger.debug("turno en asignarNumeroExpediente(TurnoDTO turno):: " + turno);
        if (turno.getUsuario() == null
                || turno.getUsuario().getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        // se crea el caso como paliativo, debe existir un bot�n que genere el caso
        final CasoDTO casoReq = new CasoDTO();
        casoReq.setFechaApertura(new Date());
        casoReq.setEstatus(EstatusCaso.INVESTIGACION);
        CasoDTO niuCaso = this.casoService.asignarNumeroCaso(casoReq,obtenerFuncionario());

        ExpedienteDTO expParam = new ExpedienteDTO();
        expParam.setFechaApertura(new Date());
        expParam.setUsuario(turno.getUsuario());
        expParam.setArea(turno.getExpediente().getArea());
        expParam.setCasoDTO(niuCaso);
        logger.debug("Antes de llamar a asignarNumeroExpediente(expParam)");
        ExpedienteDTO expNuevo = asignarNumeroExpediente(expParam);
        logger.debug("Despues de llamar a asignarNumeroExpediente(expParam)");

        Turno tnoBD = this.turnoDao.read(turno.getTurnoId());
        tnoBD.setExpediente(new Expediente(expNuevo.getExpedienteId()));
        tnoBD.setUsuario(new Usuario(turno.getUsuario().getIdUsuario()));
        tnoBD.setFechaAtencion(new Date());
        tnoBD.setEstatus(new Valor(EstatusTurno.ATENDIDO.getValorId()));

        this.turnoDao.update(tnoBD);
        expNuevo.setCasoDTO(niuCaso);
        return expNuevo;
    }
    
    private FuncionarioDTO obtenerFuncionario() {     
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		DepartamentoDTO departamento = new DepartamentoDTO();
		departamento.setDepartamentoId(13L);//Robos
		AreaDTO area = new AreaDTO();		
		area.setAreaId(1L);// Atenci�n temprana administrativa 
		departamento.setArea(area);
		funcionarioDTO.setDepartamento(departamento);
		return funcionarioDTO;
	}

    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService#asignarNumeroExpedienteASolicitud(java.lang.Long, java.lang.Long)
     */
	@Override
	public void asignarNumeroExpedienteASolicitud(Long numeroExpedienteId,
			Long solicitudId) throws NSJPNegocioException{
		Solicitud sol = solicitudDAO.read(solicitudId);
		if(sol != null){
			sol.setNumeroExpediente(new NumeroExpediente(numeroExpedienteId));
			solicitudDAO.saveOrUpdate(sol);
		}
	}
	

	 @Override
	 @Transactional(isolation = Isolation.READ_COMMITTED)
	 public synchronized List<ExpedienteDTO> asignarNumeroExpedienteAuditoria(List<ExpedienteDTO> listaNumeroExpedienteAuditados) throws NSJPNegocioException{
		 List<ExpedienteDTO> numeroAuditoriaNuevosDTO = new ArrayList<ExpedienteDTO>();

		 if (listaNumeroExpedienteAuditados== null || listaNumeroExpedienteAuditados.isEmpty())
	        	throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		 
		 for (ExpedienteDTO expedienteDTO : listaNumeroExpedienteAuditados) {
			 //Se requiere: Area, Clave del Funcionario Visitador, IdNumeroExpediente a Auditar.  
			 if(expedienteDTO==null || expedienteDTO.getNumeroExpedienteId()==null || expedienteDTO.getNumeroExpedienteId()<0 ||
					 expedienteDTO.getArea()==null || expedienteDTO.getArea().getAreaId()==null ||
					 expedienteDTO.getUsuario()==null || expedienteDTO.getUsuario().getFuncionario()==null || 
					 expedienteDTO.getUsuario().getFuncionario().getClaveFuncionario()==null || 
					 expedienteDTO.getTipoExpediente()==null || expedienteDTO.getTipoExpediente().getIdCampo()==null)
				 throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			 
			 
			//Se requiere consultar el expediente Id para generar la actividad nueva.
			Expediente expediente = expedienteDAO.consultarExpedientePorIdNumerExpediente(expedienteDTO.getNumeroExpedienteId());
			
			String numeroExpediente = obtenerNumeroExpediente(expedienteDTO.getArea().getAreaId(), expedienteDTO.getUsuario());
			expediente.setNumeroExpediente(numeroExpediente);
			
			ExpedienteDTO nuevoExp = new ExpedienteDTO();
			//Generar Actividad
			ActividadDTO actividadDTO = new ActividadDTO();
			actividadDTO.setTipoActividad(Actividades.NOTIFICAR_AUDITORIA);
			expedienteDTO.setExpedienteId( expediente.getExpedienteId());
			actividadDTO = adminActividadesService.generarActividadAExpediente(expedienteDTO, actividadDTO);
			nuevoExp.setActividadActual(actividadDTO);
			
			NumeroExpediente noExpBD = new NumeroExpediente();
	        noExpBD.setNumeroExpediente(numeroExpediente);
	        noExpBD.setFechaApertura(new Date());
            noExpBD.setExpediente(new Expediente( expediente.getExpedienteId()));
            noExpBD.setJerarquiaOrganizacional(new JerarquiaOrganizacional(expedienteDTO.getArea().getAreaId()));
            noExpBD.setFuncionario( new Funcionario( expedienteDTO.getUsuario().getFuncionario().getClaveFuncionario()));
            noExpBD.setTipoExpediente(new Valor (expedienteDTO.getTipoExpediente().getIdCampo()));
            
            nuevoExp.setExpedienteId(expediente.getExpedienteId());
            
            Long idNoExpNuevo = this.noExpDao.create(noExpBD);

            //Registrar en la relaci�n de NumExpedienteAuditoria
            RelNumExpedienteAuditoria auditoria = new RelNumExpedienteAuditoria();
            auditoria.setNumeroExpediente(new NumeroExpediente(expedienteDTO.getNumeroExpedienteId()));
    		auditoria.setNumeroAuditoriaId(idNoExpNuevo);
    		
    		Long idAuditoria = relNumExpedienteAuditoriaDAO.create(auditoria);
    		logger.info(" IdAuditoria Creado:"+ idAuditoria);
            
    		nuevoExp.setExpedienteId(expediente.getExpedienteId());
            nuevoExp.setNumeroExpediente(numeroExpediente);
            nuevoExp.setNumeroExpedienteId(idNoExpNuevo);
            nuevoExp.setFechaApertura(expedienteDTO.getFechaApertura());
            
            Valor tipoExp = new Valor();
            
            tipoExp = valorDAO.read(expedienteDTO.getTipoExpediente().getIdCampo());
            
            if(tipoExp != null){
            	
            	ValorDTO valorTipo = new ValorDTO();
            	
            	if(tipoExp.getValor() != null){
            		valorTipo.setValor(tipoExp.getValor());
            	}
            	if(tipoExp.getValorId() != null){
            		valorTipo.setIdCampo(tipoExp.getValorId());
            	}
            	 nuevoExp.setTipoExpediente(valorTipo);
            }
            //Se agrega a la lista
            numeroAuditoriaNuevosDTO.add(nuevoExp);
		 }
		 
		 return numeroAuditoriaNuevosDTO;
	 }

	 
	 @Override
	 @Transactional
	 public ExpedienteDTO asignarNumeroExpedienteCarpetaEjecucion(Long expedienteId) throws NSJPNegocioException{
		 logger.info("Inicia - asignarNumeroExpedienteCarpetaEjecucion("+ expedienteId+")");
		 if (expedienteId== null || expedienteId<0)
	        	throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		 
		//Se obtiene el expediente completo
		NumeroExpediente numeroExpCausa = noExpDao.consultarNumeroExpedienteXExpedienteId(expedienteId);
		if(numeroExpCausa==null | numeroExpCausa.getJerarquiaOrganizacional()==null || 
				numeroExpCausa.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId()==null ||
				numeroExpCausa.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId() < 0 ||
				numeroExpCausa.getFuncionario()==null || numeroExpCausa.getFuncionario().getClaveFuncionario()==null ||
				numeroExpCausa.getFuncionario().getClaveFuncionario() < 0 ||
				numeroExpCausa.getNumeroExpedienteId()==null || numeroExpCausa.getNumeroExpedienteId()<0 )
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		//Que se necesita: Area, ID=null, CausaPadre (numeroExpedienteID), TipoExpediente, Estatus y Funcionario
		ExpedienteDTO expedienteDTOGenerar = new ExpedienteDTO();
		expedienteDTOGenerar.setArea(new AreaDTO(
				numeroExpCausa.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId()));
		expedienteDTOGenerar.setExpedienteId(null);
		
		ExpedienteDTO causaPadre = new ExpedienteDTO(expedienteId);
		causaPadre.setNumeroExpedienteId(numeroExpCausa.getNumeroExpedienteId());
		causaPadre.setNumeroExpediente(numeroExpCausa.getNumeroExpediente());
		expedienteDTOGenerar.setCausaPadre(causaPadre);

		if( numeroExpCausa.getExpediente()!= null && numeroExpCausa.getExpediente().getCaso()!= null){
			logger.info("Caso Id#" + numeroExpCausa.getExpediente().getCaso().getCasoId());
			expedienteDTOGenerar.setCasoDTO(new CasoDTO(numeroExpCausa.getExpediente().getCaso().getCasoId()));
		}
		
		expedienteDTOGenerar.setTipoExpediente(new ValorDTO(TipoExpediente.CARPETA_DE_EJECUCION.getValorId()));
		expedienteDTOGenerar.setEstatus(new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setFuncionario(new FuncionarioDTO( numeroExpCausa.getFuncionario().getClaveFuncionario()));
		expedienteDTOGenerar.setUsuario(usuarioDTO);
        
		//Generar el Expediente, N�mero de Expediente y Actividad
		ExpedienteDTO carpetaEjecucion = asignarNumeroExpediente(expedienteDTOGenerar);
		
		//Se requiere consultar el expediente Id para generar la actividad nueva.
		//Generar Actividad
//		ActividadDTO actividadDTO = new ActividadDTO();
//		actividadDTO.setTipoActividad(Actividades.VINCULAR_EXPEDIENTE_A_CASO);
		logger.info("El expediente recien creado es " + carpetaEjecucion.getExpedienteId());
       	
        return carpetaEjecucion;
	 }
	 
}

/**
* Nombre del Programa : AdministrarNumeroExpedienteServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/06/2011
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
import java.util.GregorianCalendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.ConsecutivosUtil;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.caso.AsignarNumeroCasoService;
import mx.gob.segob.nsjp.service.delito.GuardarDelitoService;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.institucion.JerarquiaOrganizacionalService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Este caso de uso permitirá al solicitante del servicio crear un número de expediente
 *  y asignarlo a un expediente, o en su caso crear un nuevo expediente en el sistema.
 *  La información que tendrá e nuevo número de expediente es:
 *    - Usuario firmado.
 *    
 *  La información que tendrá el nuevo expediente es:
 *    - Número de expediente.
 *    - Usuario firmado.
 */
@Service
@Transactional
public class AdministrarNumeroExpedienteServiceImpl implements AdministrarNumeroExpedienteService {
	/**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(AdministrarNumeroExpedienteServiceImpl.class);
	
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    @Autowired
    private CasoDAO casoDAO;
    @Autowired
    private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
    @Autowired
    private SolicitudDAO solicitudDAO; 
    @Autowired
    private JerarquiaOrganizacionalService jerarquiaOrganizacionalService; 
    @Autowired
    private UsuarioDAO usuarioDAO;    
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
	@Autowired
	private DelitoDAO delitoDAO;
	@Autowired
	private GuardarDelitoService guardarDelitoService;
    @Autowired
    private AsignarNumeroCasoService casoService;

    
    
    @Override
    @Transactional 
    public ExpedienteDTO crearAsignarNumeroExpediente(UsuarioDTO usuarioDTO, ExpedienteDTO expedienteDTO, InstitucionDTO institucionDTO ) throws NSJPNegocioException{
    	boolean existeExpediente = false;
    	String prefijo= "";
    	String consecutivo = "";
    	String anio = "";
    	String ultimoNumeroExpediente = null;
    	
    	if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para crear y asignar un numero de expediente****/");
		
    	//Validar Parametros del CU
		if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null
				|| usuarioDTO == null
				|| usuarioDTO.getIdUsuario() == null
				|| usuarioDTO.getUsuario().getIdUsuario()==null
				|| institucionDTO == null
				|| institucionDTO.getInstitucionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
    	//1.- Verificar si existe el Expediente, al que se le va a asignar el número de expediente.
		existeExpediente = this.validarSiExisteExpediente(expedienteDTO.getNumeroExpedienteId());
    	if (!existeExpediente){
	    	//FA1 .- Si no existe el Expediente se crea uno nuevo
    		expedienteDTO = this.crearExpediente();
    	}
    	
    	//2.- Consultar el ultimo numero consecutivo asignado a un expediente para la institución.
    	//Si es nuevo el Expediente, la consulta regresara null
    	ultimoNumeroExpediente = this.consultarUltimoNumeroExpediente(institucionDTO.getInstitucionId());    	
    	if (ultimoNumeroExpediente == null){
    		
    		//FA2.- Si no existe un número de expediente asignado dentro de la institución-área a la que pertenece el usuario.
    		prefijo = jerarquiaOrganizacionalService.consultarPrefijo(institucionDTO);
    		
    		//2. El sistema asigna a la parte del número consecutivo del número de expediente un cero
    		//Sufijo en A-00000
    		consecutivo = ConsecutivosUtil.CONSECUTIVO_INICIAL;
    	}else{
    		//FIXME GBP MOCK Se tiene en Harcode un número de expediente 
    		//Este mock es para construir un numero de expediente de acuerdo al desarrollo del CU Administracion de numero de expedientes
    		ultimoNumeroExpediente = "000/PR/15/RBO/2011/B-99999";

    		//Se descarta los 4 caracteres del año incluyendo el separador, por esta razón se resta 5
    		prefijo = ultimoNumeroExpediente.substring(0,ultimoNumeroExpediente.lastIndexOf(ConsecutivosUtil.SEPARADOR_PREFIJOS) - 5 );
    		    		
    		//3.- Asignar nuevo expediente a través del último número + 1
    		consecutivo = ultimoNumeroExpediente.substring(ultimoNumeroExpediente.lastIndexOf(ConsecutivosUtil.SEPARADOR_PREFIJOS)+1 );
        	consecutivo= this.generarConsecutivoNumeroExpediente(consecutivo);
    	}

    	//Manejo del año
    	Calendar fechaActual = new GregorianCalendar();
    	anio = "/" + fechaActual.get(Calendar.YEAR) + "/";
    	
    	ultimoNumeroExpediente = prefijo + anio + consecutivo;
    	
    	logger.info("Nuevo Número Expedeinte:"+ ultimoNumeroExpediente);
    	
    	expedienteDTO.setNumeroExpediente(ultimoNumeroExpediente);
    	
    	//4.- Asociar número de expediente al expediente y al usuario.
    	expedienteDTO.setUsuario(usuarioDTO);
    	
    	//TODO GBP En la siguiente operacion realiza el registro en BD  
    	//this.asociarNumExpediente(expedienteDTO);
    	
		return expedienteDTO;
    }
    
	
	@Override
	public String consultarUltimoNumeroExpediente(Long idInstitucion) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar el último Numero de expediente ****/");
		
		if( idInstitucion==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		String numExpediente= expedienteDAO.obtenerUltimoNumero(idInstitucion);
				 
		return ( numExpediente==null || numExpediente.trim().isEmpty())? null:numExpediente ;
	}

	@Override
	@Transactional
	public ExpedienteDTO crearExpediente () throws NSJPNegocioException{
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para crear un expediente ****/");
		Expediente expediente = new Expediente();
		
		expediente.setFechaCreacion(new Date());
		expediente.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
		
		Long idExpediente = expedienteDAO.create(expediente);
		
		return new ExpedienteDTO (idExpediente);
	}
	
	private ExpedienteDTO crearExpediente(Long idCaso) throws NSJPNegocioException{
        if (logger.isDebugEnabled())
            logger.debug("/**** Servicio para crear un expediente ****/");
        Expediente expediente = new Expediente();
        expediente.setCaso(new Caso(idCaso));
        expediente.setFechaCreacion(new Date());
        expediente.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
        
        Long idExpediente = expedienteDAO.create(expediente);
        
        return new ExpedienteDTO (idExpediente);
    }

    @Override
    @Transactional
    public void asociarNumExpediente(ExpedienteDTO expedienteDto) throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug(" Servicio que asocia un Numero de Expediente con un Expediente y a un usuario");
        }
		if (expedienteDto == null || expedienteDto.getExpedienteId() == null
				|| expedienteDto.getNumeroExpediente() == null
				|| expedienteDto.getNumeroExpediente().trim().isEmpty() 
				|| expedienteDto.getUsuario()==null
				|| expedienteDto.getUsuario().getIdUsuario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
        //Obtener el expediente de BD al cual se le asociara el Numero Expediente nuevo 
        Expediente expediente = expedienteDAO.read(expedienteDto.getExpedienteId()) ;
        if ( expediente==null )
        	throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
        //Obtener el usuario de la DB para asociar el Funcionario
        Usuario usuario = usuarioDAO.read(expedienteDto.getUsuario().getIdUsuario());
        if ( usuario==null || usuario.getFuncionario()==null )
        	throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
        //Asociar al Número de Expediente al Expediente
        NumeroExpediente numeroExpediente= new NumeroExpediente();
        numeroExpediente.setExpediente(expediente);
        if( expedienteDto.getEtapa()!= null && expedienteDto.getEtapa().getIdCampo()!= null )
        	numeroExpediente.setEtapa(new Valor(expedienteDto.getEtapa().getIdCampo()));
        
        numeroExpediente.setNumeroExpediente(expedienteDto.getNumeroExpediente() );
        numeroExpediente.setFechaApertura(new Date());
        numeroExpediente.setFuncionario(usuario.getFuncionario());
        
        numeroExpediente.setJerarquiaOrganizacional(usuario.getFuncionario().getInstitucion());
        numeroExpedienteDAO.create(numeroExpediente);
    }
	
    @Override
    public boolean asociarNumCarpetaASolicitud( ExpedienteDTO expedienteDTO, Long idSolicitud)throws NSJPNegocioException{
    	if (logger.isDebugEnabled())
			logger.debug("Servicio para relacionar un expediente a una solicitud");
		
		if (expedienteDTO==null || expedienteDTO.getNumeroExpediente()==null || idSolicitud==null) 			
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);	
    	
		Solicitud solicitud = solicitudDAO.read(idSolicitud);
		
		if(solicitud==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		logger.info("Solicitud:"+solicitud );
		logger.info("Solicitud NmExp:"+solicitud.getNumeroExpediente() );
		
		NumeroExpediente numeroExpediente = numeroExpedienteDAO.obtenerNumeroExpediente(expedienteDTO.getNumeroExpediente(),null);
		
		if(numeroExpediente==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.info("numeroExpediente:"+numeroExpediente );
		logger.info("NumeroExpediente:"+numeroExpediente.getNumeroExpediente() );
		

		solicitud.setNumeroExpediente(numeroExpediente);
		
		solicitudDAO.update(solicitud);
		
    	return true;
    }
    
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService#buscarOCrearExpedienteDeInstitucionExterna(java.lang.String)
     */
	@Override
	public ExpedienteDTO buscarOCrearExpedienteDeInstitucionExterna(
			String numeroCaso) throws NSJPNegocioException{
		ExpedienteDTO expediente = null;
		//Buscar caso
		List<Caso> casos = casoDAO.consultarCasosPorNumero(numeroCaso);
		Caso caso = !casos.isEmpty()?casos.get(0):null;
		Expediente exp = caso!=null&&!caso.getExpedientes().isEmpty()
		&& caso.getExpedientes().size() == 1?caso.getExpedientes().iterator().next():null;
		NumeroExpediente numExp = exp!=null&&exp.getNumeroExpedientes().size()==1?exp.getNumeroExpedientes().iterator().next():null;
		
		if(numExp == null){
			//Crear expediente y número
			expediente = crearExpediente();
			expediente = ExpedienteTransformer.transformaExpediente(expedienteDAO.read(expediente.getExpedienteId()));
			expediente.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_DEFENSORIA));
			expediente.setUsuario(new UsuarioDTO());
			expediente.getUsuario().setFuncionario(new FuncionarioDTO(1L));
			
			asignarNumeroExpedienteService.asignarNumeroExpediente(expediente);
		} else {
		    if (exp!=null && exp.getNumeroExpedientes().size()>1) {
		           //Crear expediente y número
	            expediente = caso!=null ? crearExpediente():crearExpediente(caso.getCasoId());
	            expediente = ExpedienteTransformer.transformaExpediente(expedienteDAO.read(expediente.getExpedienteId()));
	            expediente.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_DEFENSORIA));
	            expediente.setUsuario(new UsuarioDTO());
	            expediente.getUsuario().setFuncionario(new FuncionarioDTO(1L));
	            
	            asignarNumeroExpedienteService.asignarNumeroExpediente(expediente); 
		    }
		}
		return expediente;
	}
	
	/**
	 * Operación que realiza la funcionalidad de validar si existe un expediente 
	 * Recibe el número del expediente a validar
	 * Devuelve un valor boleano, verdadero en caso de que exista, falso en caso contrario
	 * 
	 * @param numeroExpediente
	 * @return boolean si existe o no el expediente
	 * @throws NSJPNegocioException
	 */
	private boolean validarSiExisteNumeroExpediente(String numeroExpediente)throws NSJPNegocioException{
		
		if( numeroExpediente== null || numeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		 NumeroExpediente nExpediente = numeroExpedienteDAO.obtenerNumeroExpediente(numeroExpediente,null);
		
		return(nExpediente != null);
	}
	
	/**
	 * Operación que realiza la funcionalidad de validar si existe un expediente 
     * Recibe el id del expediente
     * Devuelve un valor boleano, verdadero en caso de que exista, falso en caso contrario
	 * 
	 * @param expedienteId
	 * @return boolean si existe o no el expediente
	 * @throws NSJPNegocioException
	 */
	private boolean validarSiExisteExpediente(Long expedienteId)throws NSJPNegocioException{
	
		if( expedienteId== null || expedienteId <= 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
		 Expediente expediente = expedienteDAO.read(expedienteId);
		 		
		return(expediente != null);
		
	}
	
	/**
	 * 
	 * Operación que realiza la funcionalidad de asignar el nuevo número de expediente.
	 * recibe el último número del expediente registrado.Lo asigna a través del último número +1.
	 * Regresa el número de expediente de tipo cadena, en caso contrario devuelve null.
	 * 
	 * El formato del consecutivo del numero de Expediente es:
	 * 	C-DDDDD
	 * donde:
	 * 	C: Cáracter {A-Z}
	 *  D: Dígito {0-9} 
	 * Ejemplo: 
	 * 	C-12345
	 *
	 * @param ExpedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	private String generarConsecutivoNumeroExpediente( String consecutivoNumeroExpediente) throws NSJPNegocioException{
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para generar el consecutivo de Numero expediente ****/");
		
		if( consecutivoNumeroExpediente == null || consecutivoNumeroExpediente.trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		//Validar el consecutivo que tenga el formato especificado 
		if( !consecutivoNumeroExpediente.matches("[A-Z]-\\d{5}") )
    		throw new NSJPNegocioException(CodigoError.FORMATO);
		
		String valorNuevo = ConsecutivosUtil.incrementarConsecutivoNumeroExpediente(consecutivoNumeroExpediente);
		logger.info(" valorNuevo:" +valorNuevo);
		String consecutivoNumeroExpedienteNuevo = consecutivoNumeroExpediente.substring(0, consecutivoNumeroExpediente.lastIndexOf('/') + 1) + valorNuevo;
		return consecutivoNumeroExpedienteNuevo;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService#consultarNumeroExpedientePorCaso(java.lang.String)
	 */
	@Override
	public List<ExpedienteDTO> consultarNumeroExpedientePorCasoDeSolicitud(
			Long solicitudId) {
		List<ExpedienteDTO> resultado = new ArrayList<ExpedienteDTO>();
		List<NumeroExpediente> expedientes = numeroExpedienteDAO.consultarNumeroExpedientePorCasoDeSolicitud(solicitudId);
		ExpedienteDTO expDto = null;
		for(NumeroExpediente exp:expedientes){
			expDto = new ExpedienteDTO();
			expDto.setExpedienteId(exp.getExpediente().getExpedienteId());
			expDto.setNumeroExpediente(exp.getNumeroExpediente());
			expDto.setNumeroExpedienteId(exp.getNumeroExpedienteId());
			expDto.setFechaApertura(exp.getFechaApertura());
			expDto.setUsuario(new UsuarioDTO());
			expDto.getUsuario().setFuncionario(FuncionarioTransformer.transformarFuncionario(exp.getFuncionario()));
			
			resultado.add(expDto);
		}
		return resultado;
	}
	
	public void asociarExpedientesAFuncionario(List<Long> idsNumsExpedientes, Long idFuncionario) throws NSJPNegocioException{
			logger.debug("/**** Servicio actualizar el responsable de un expediente ****/");
		
		if( idsNumsExpedientes == null || idsNumsExpedientes.size() == 0 || idFuncionario == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		for (Long idNumExp : idsNumsExpedientes) {
			logger.debug("/**** expediente ****/"+idNumExp + " idsNumsFuncionario:: "+idFuncionario);
			NumeroExpediente loNumExp = numeroExpedienteDAO.read(idNumExp);
			loNumExp.setFuncionario(new Funcionario(idFuncionario));	
			numeroExpedienteDAO.update(loNumExp);
		}
		
	}
	
	@Override
	@Transactional
	public ExpedienteDTO generarNuevoExpedienteUAVD(DelitoPersonaDTO delitoPersonaDTO) throws NSJPNegocioException{
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para crear un expediente en UAVD by Gamasoft ****/");
		
		if( delitoPersonaDTO == null || delitoPersonaDTO.getVictima() == null || delitoPersonaDTO.getVictima().getElementoId() == null ||
				delitoPersonaDTO.getProbableResponsable() == null || delitoPersonaDTO.getProbableResponsable().getElementoId() == null ||
				delitoPersonaDTO.getDelito() == null || delitoPersonaDTO.getDelito().getDelitoId() == null ||
				delitoPersonaDTO.getUsuario() == null || delitoPersonaDTO.getUsuario().getIdUsuario() == null || delitoPersonaDTO.getUsuario().getIdUsuario() <= 0 || 
				delitoPersonaDTO.getUsuario().getFuncionario() == null || delitoPersonaDTO.getUsuario().getFuncionario().getClaveFuncionario() == null || 
				delitoPersonaDTO.getUsuario().getFuncionario().getClaveFuncionario() <= 0
			)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		ExpedienteDTO expParam = new ExpedienteDTO();
        ExpedienteDTO expBD = null;
        expParam.setFechaApertura(new Date());
        //Se sette al usuario coordinadorUAVD
        expParam.setUsuario(delitoPersonaDTO.getUsuario());    
        expParam.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_VICTIMAS));
        //Es necesario para generar el Numero de Expediente
        expBD = asignarNumeroExpedienteService.asignarNumeroExpediente(expParam);
        guardarInvolucradoEnExpediente(expBD,delitoPersonaDTO.getVictima(),Calidades.VICTIMA_PERSONA);
       	guardarInvolucradoEnExpediente(expBD,delitoPersonaDTO.getProbableResponsable(),Calidades.PROBABLE_RESPONSABLE_PERSONA);
       	//Permite consultar el Delito
       	DelitoDTO loDelitoBD =  DelitoTransfromer.transformarDelito(delitoDAO.obtenerDelitoPorId(delitoPersonaDTO.getDelito().getDelitoId()));
       	//Arma el nuevo Delito y lo guarda
       	loDelitoBD.setDelitoId(null);
       	loDelitoBD.setExpedienteDTO(expBD);
       	List<DelitoDTO> loLista = new ArrayList<DelitoDTO>();
       	loLista.add(loDelitoBD);
       	logger.info("El expediente recien creado es " + expBD.getExpedienteId());
       	guardarDelitoService.guardarDelito(loLista, new ExpedienteDTO(expBD.getExpedienteId()));       	
        return expBD;
	}


	public InvolucradoDTO guardarInvolucradoEnExpediente(ExpedienteDTO expBD,
			InvolucradoDTO aoInvolucrado, Calidades aoCalidad) throws NSJPNegocioException {
        //Permite asosciar la info del Involucrado
        InvolucradoDTO loInvolucrado = consultarIndividuoService.obtenerInvolucrado(aoInvolucrado);
        loInvolucrado.setElementoId(null);
        loInvolucrado.setExpedienteDTO(expBD);
        
        if(aoInvolucrado.getValorSituacionJuridica()!= null &&  aoInvolucrado.getValorSituacionJuridica().getIdCampo()!= null){
        	logger.info(" Situacion Juridica:"+ aoInvolucrado.getValorSituacionJuridica());
        	loInvolucrado.setValorSituacionJuridica(new ValorDTO(aoInvolucrado.getValorSituacionJuridica().getIdCampo()));
        }
        	
        if( loInvolucrado.getCalidadDTO()!= null && loInvolucrado.getCalidadDTO().getValorIdCalidad()!= null 
        		&& loInvolucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()!= null ){
        		logger.info(" FIX CAlidades:"+ loInvolucrado.getCalidadDTO().getCalidades());
        		logger.info(" FIX CAlidad:"+ loInvolucrado.getCalidadDTO().getValorIdCalidad().getIdCampo());
        		loInvolucrado.getCalidadDTO().setCalidades(Calidades.getByValor(loInvolucrado.getCalidadDTO().getValorIdCalidad().getIdCampo()));
        		logger.info(" FIX CAlidades:"+ loInvolucrado.getCalidadDTO().getCalidades());
        }else {
        
	        if(aoInvolucrado.getCalidadDTO() != null ){
	            if(aoInvolucrado.getCalidadDTO().getCalidades().equals(Calidades.PROBABLE_RESPONSABLE_PERSONA))
	                loInvolucrado.getCalidadDTO().setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
	            else        	
	                loInvolucrado.getCalidadDTO().setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
	            
	            if(aoInvolucrado.getCalidadDTO().getCalidades().equals(Calidades.VICTIMA_PERSONA))
	                loInvolucrado.getCalidadDTO().setCalidades(Calidades.VICTIMA_PERSONA);
	            
	        }else
	            loInvolucrado.getCalidadDTO().setCalidades(aoCalidad);
        }
        
        if(loInvolucrado.getOrganizacionDTO() != null){
        	CalidadDTO loCalidadDTO = new CalidadDTO();
        	loCalidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
        	loInvolucrado.getOrganizacionDTO().setCalidadDTO(loCalidadDTO);
        	if(loInvolucrado.getOrganizacionDTO().getDomicilioDTO() != null){
        		CalidadDTO loCalidadDTODomicilio = new CalidadDTO();
        		loCalidadDTODomicilio.setCalidades(Calidades.DOMICILIO);
            	loInvolucrado.getOrganizacionDTO().getDomicilioDTO().setCalidadDTO(loCalidadDTODomicilio);
        	}
        }
        
        if(loInvolucrado.getDomicilio() != null){
    		//Calidad de Domicilio
    		CalidadDTO calidadDomicilio = new CalidadDTO();
    		//calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
    		calidadDomicilio.setCalidades(Calidades.DOMICILIO);			
    		loInvolucrado.getDomicilio().setCalidadDTO(calidadDomicilio);
        }
		
		if(loInvolucrado.getDomicilioNotificacion() != null ){
			//Calidad de Domicilio de Notificaciones
			CalidadDTO calidadDomicilioNot = new CalidadDTO();
			//calidadDomicilioNot.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadDomicilioNot.setCalidades(Calidades.DOMICILIO);
			loInvolucrado.getDomicilioNotificacion().setCalidadDTO(calidadDomicilioNot);
		}		
        eliminarIdentificadoresDeInvolucrado(loInvolucrado);
        Long idInvolucradoNuevo = ingresarIndividuoService.ingresarIndividuo(loInvolucrado);        
        loInvolucrado.setElementoId(idInvolucradoNuevo);
        return loInvolucrado;
	}


	private void eliminarIdentificadoresDeInvolucrado(InvolucradoDTO loVictima) {	
		
		for (AliasInvolucradoDTO loAlias : loVictima.getAliasInvolucradoDTO()) {
			loAlias.setAliasInvolucradoId(null);
		}
		
		if(loVictima.getMediaFiliacionDTO() != null ){
			loVictima.getMediaFiliacionDTO().setMediaFiliacionId(null);
			if(loVictima.getMediaFiliacionDTO().getSeniaParticularDTO() != null)
				loVictima.getMediaFiliacionDTO().getSeniaParticularDTO().setSeniaParticularId(null) ;
		}
		
		for (TelefonoDTO loTelefono : loVictima.getTelefonosDTO()) {
			loTelefono.setMedioDeContadoId(null);
		}
		
		for (CorreoElectronicoDTO loCorreos : loVictima.getCorreosDTO()) {
			loCorreos.setCorreoElectronicoId(null);
		}		
	}
	
	
	@Override
	@Transactional
	public ExpedienteDTO generarNuevoExpedienteConCaso(ExpedienteDTO expedienteDTO) throws NSJPNegocioException{
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para crear un expediente, numero Expediente y un caso by Gamasoft ****/");		
		
		if (expedienteDTO == null
				|| expedienteDTO.getUsuario() == null
				|| expedienteDTO.getUsuario().getFuncionario() == null
				|| expedienteDTO.getUsuario().getFuncionario()
						.getJerarquiaOrganizacional() == null
				|| expedienteDTO.getUsuario().getFuncionario()
						.getJerarquiaOrganizacional()
						.getJerarquiaOrganizacionalId() == null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		
		
		CasoDTO casoDTO = new CasoDTO();
		
		if (expedienteDTO.getUsuario().getFuncionario()
				.getJerarquiaOrganizacional().getJerarquiaOrganizacionalId() != Areas.AGENCIA_DEL_MINISTERIO_PUBLICO
				.ordinal()) {

			// Se genera el caso para ser asociado al Expediente

			casoDTO.setFechaApertura(new Date());
			casoDTO.setEstatus(EstatusCaso.INVESTIGACION);
			casoDTO = casoService.asignarNumeroCaso(casoDTO, expedienteDTO
					.getUsuario().getFuncionario());
		}
		
		ExpedienteDTO expParam = new ExpedienteDTO();
        ExpedienteDTO expBD = null;
        expParam.setFechaApertura(new Date());
        //Se sette al usuario coordinador
        expParam.setUsuario(expedienteDTO.getUsuario());
        expParam.setArea(expedienteDTO.getUsuario().getArea());
        expParam.setCasoDTO(casoDTO);

        //Es necesario para generar el Numero de Expediente
        expBD = asignarNumeroExpedienteService.asignarNumeroExpediente(expParam);
        expBD.setCasoDTO(casoDTO);
        return expBD;
	}

}

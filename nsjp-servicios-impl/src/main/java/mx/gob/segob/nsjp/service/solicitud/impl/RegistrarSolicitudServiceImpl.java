/**
 * Nombre del Programa : RegistrarSolicitudServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementaci�n para el registro de una solicitud
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAdjuntosDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudAdjuntosId;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.GenerarRelacionDocumentoElementoService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudAudienciaTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementaci�n para el registro de una solicitud de una manera generica para
 * que pueda reusar.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service("registrarSolicitudService")
@Transactional
public class RegistrarSolicitudServiceImpl implements RegistrarSolicitudService {

    /**
	 * 
	 */
    public final static Logger LOGGER = Logger
            .getLogger(RegistrarSolicitudServiceImpl.class);

    @Autowired
    private SolicitudDAO solicitudDAO;
    @Autowired
    private SolicitudAudienciaDAO solicitudAudienciaDAO;
    @Autowired
    private SolicitudTranscricpionAudienciaDAO solicitudTranscricpionAudienciaDAO;
    @Autowired
    private SolicitudAdjuntosDAO solicitudAdjuntosDAO;
    @Autowired
    private GenerarRelacionDocumentoElementoService generarRelacionService;
    @Autowired
    private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
    @Autowired
    private AudienciaDAO audienciaDAO;
    @Autowired
    private InvolucradoAudienciaDAO involucradoAudienciaDAO;
    @Autowired
    private ProcuraduriaClienteService procuraduriaClienteService; 
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;
	@Autowired
	private DefensaInvolucradoDAO defensaInvolucradoDAO;
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private AvisoDetencionDAO avisoDetencionDAO;
	@Autowired
	private ConfInstitucionDAO confInstitucionDAO;
	@Autowired
	private DocumentoDAO documentoDAO;	
	
    
    @Override
    public SolicitudDTO registrarSolicitud(SolicitudDTO input)
            throws NSJPNegocioException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("/**** SERVICIO PARA REGISTRAR UNA SOLICITUD ****/ "
                    + input);
        }
        if (input.getTipoSolicitudDTO() == null)
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
        if(input.getExpedienteDTO() == null || (input.getExpedienteDTO() != null && input.getExpedienteDTO().getNumeroExpedienteId() == null)){
        	String warning = "";
        	warning += "*************************************************************";
        	warning += "\nTODA SOLICITUD DEBE DE ESTAR ASOCIADA A UN NUMERO DE EXPEDIENTE";
        	warning += "\n*************************************************************";
        	LOGGER.warn(warning);
        }
        
//        
//		input.setTipoSolicitudDTO(tipoSolicitudDTO);
//		input.setExpedienteDTO(expedienteDTO);	
//		input.setFechaCreacion(fechaHora);
//		input.setInstitucion(confInstitucionDTO);
//		input.setNombreSolicitante(solicitante);
//        
        Solicitud solicitud = SolicitudTransformer.solicitudTransformer(input);
		solicitud.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        Long idSolicitud = input.getDocumentoId();
        String generarFolioSolicitud = "";
        if(idSolicitud != null && idSolicitud > 0){
            if (solicitud.getTipoSolicitud().getValorId().compareTo(TiposSolicitudes.REINSERCION_SOCIAL.getValorId())==0) {
            	solicitud = guardarSolicitudReinsercionSocial(solicitud);
            } else {
            	solicitudDAO.update(solicitud);
            }
        }
        else{
            generarFolioSolicitud = generarFolioSolicitudService.generarFolioSolicitud();
            input.setFolioSolicitud(generarFolioSolicitud);
            solicitud.setFolioSolicitud(generarFolioSolicitud);
            solicitud.setForma(new Forma(Formas.SOLICITUD.getValorId()));
            solicitud.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
            solicitud.setFechaCreacion(Calendar.getInstance().getTime());
            solicitud.setNombreDocumento("SOLICITUD_DE_"+TiposSolicitudes.getByValor(solicitud.getTipoSolicitud().getValorId()).name());
            if (solicitud.getTipoSolicitud().getValorId().compareTo(TiposSolicitudes.REINSERCION_SOCIAL.getValorId())==0) {
            	solicitud =	guardarSolicitudReinsercionSocial(solicitud);
            } else {
            	idSolicitud = solicitudDAO.create(solicitud);
            }
        }
        solicitud.setDocumentoId(idSolicitud);
        solicitud.setFolioSolicitud(generarFolioSolicitud);
        switch (TiposSolicitudes.getByValor(input.getTipoSolicitudDTO()
                .getIdCampo())) {
            case EVIDENCIA :

                for (ElementoDTO ele : input.getElementos()) {
                    generarRelacionService.generarRelacion(solicitud,
                            ele.getElementoId(),
                            Relaciones.EVIDENCIA_EN_SOLICITUD);
                }

                break;

            default :
                break;
        }

        LOGGER.debug("idNuevaSol :: " + idSolicitud);
        return new SolicitudDTO(idSolicitud);
    }

	/**
	 * @param solicitud
	 * @param idSolicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
	private Solicitud guardarSolicitudReinsercionSocial(Solicitud solicitud) throws NSJPNegocioException {
		
//		Documento documento = documentoDAO.consultarDocumentoPorId(solicitud.getDocumentoId());            	
//		solicitud = SolicitudTransformer.combinarDocumentoConSolicitud(documento, solicitud);
		Solicitud tmp = solicitudDAO.consultarSolicitudPorId(solicitud.getDocumentoId());
		if(tmp == null){
			String generarFolioSolicitud = generarFolioSolicitudService.generarFolioSolicitud();
            solicitud.setFolioSolicitud(generarFolioSolicitud);
		} else {
			solicitud.setFolioSolicitud(tmp.getFolioSolicitud());
		}
		solicitudDAO.crearSolicitudConDocumentoExistente(solicitud);
		return solicitud;
	}

    @Autowired
    public IngresarIndividuoService ingresarIndividuoService;
    
	public SolicitudDTO registrarSolicitudOrdenDeDetencion(
			SolicitudDTO ordenDetencion, Long idDocumentoAnexo)throws NSJPNegocioException {
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setFechaApertura(Calendar.getInstance().getTime());
		expedienteDTO = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);

		ordenDetencion.setExpedienteDTO(expedienteDTO);
		ordenDetencion.getInvolucradoDTO().setExpedienteDTO(expedienteDTO);
		Long idIndividuo = ingresarIndividuoService.ingresarIndividuo(ordenDetencion.getInvolucradoDTO());
		ordenDetencion.getInvolucradoDTO().setElementoId(idIndividuo);

		ordenDetencion.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.ORDEN_DETENCION.getValorId()));
		ordenDetencion.setInvolucradoDTO(new InvolucradoDTO(1646L));
		ordenDetencion.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		ordenDetencion.setFechaCreacion(new Date());
		ordenDetencion.setFechaLimite(null);
		ordenDetencion.setFechaCierre(null);
		ordenDetencion.setFechaModificacion(null);
		ordenDetencion.setEsUrgente(false);
		ordenDetencion.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
		ordenDetencion.setFormaDTO(new FormaDTO(1L));
		ordenDetencion.setNombreDocumento("Orden de Detenci�n");
		
		Solicitud solicitud = SolicitudTransformer.solicitudTransformer(ordenDetencion);
		
        solicitud.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		
        Long idSolicitud = solicitudDAO.create(solicitud);
		
		SolicitudAdjuntos adjunto = new SolicitudAdjuntos();
		SolicitudAdjuntosId id = new SolicitudAdjuntosId();
		id.setArchivoDigitalId(idDocumentoAnexo);
		id.setSolicitudId(idSolicitud);
		adjunto.setId(id);
		
		solicitudAdjuntosDAO.create(adjunto);
		
		return null;
	}
	
	@Override
	public SolicitudAudienciaDTO registrarSolicitudAudiencia(
			SolicitudAudienciaDTO solicitud) throws NSJPNegocioException {
		
		if(solicitud.getExpedienteDTO() == null){
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			AreaDTO area = new AreaDTO();
			area.setAreaId(Instituciones.PJ.getValorId());
			expedienteDTO.setArea(area);
			expedienteDTO.setUsuario(solicitud.getUsuario());
			expedienteDTO = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);
			solicitud.setExpedienteDTO(expedienteDTO);
		}
		
		solicitud.getAudiencia().setFechaSolicitud(Calendar.getInstance().getTime());
		SolicitudAudiencia sol = SolicitudAudienciaTransformer.transformarSolicitud(solicitud);
		sol.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		if(solicitud.getFormaDTO() != null){
			sol.setForma(new Forma(solicitud.getFormaDTO().getFormaId()));
		}else{
			sol.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		}
		
		sol.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		sol.setFechaCreacion(Calendar.getInstance().getTime());
		sol.setNombreDocumento("SOLICITUD_DE_AUDIENCIA");
		sol.getAudiencia().setNumeroExpediente(new NumeroExpediente());
		sol.getAudiencia().getNumeroExpediente().setNumeroExpedienteId(solicitud.getExpedienteDTO().getNumeroExpedienteId());
		sol.getAudiencia().setConsecutivo(Short.decode("1")); //TODO EH
		
		audienciaDAO.create(sol.getAudiencia());
		solicitud.setDocumentoId(solicitudAudienciaDAO.create(sol));
		if(solicitud.getAudiencia().getInvolucrados() != null ){
			InvolucradoAudiencia invBD = null;
			for(InvolucradoDTO invDTO:solicitud.getAudiencia().getInvolucrados()){
				invBD = new InvolucradoAudiencia();
				invBD.setAudiencia(sol.getAudiencia());
				invBD.setInvolucrado(new Involucrado(invDTO.getElementoId()));
				invBD.setId(new InvolucradoAudienciaId(sol.getAudiencia().getAudienciaId(), invDTO.getElementoId()));
				involucradoAudienciaDAO.create(invBD);
			}
		}
		return solicitud;
	}
	
	@Override
	public SolicitudDTO registrarSolicitudCarpetaInvestigacion(
			Long idNumeroExpediente, UsuarioDTO usuario)
			throws NSJPNegocioException {
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		SolicitudDTO solicitud = new SolicitudDTO();
		List<InvolucradoDTO> involucrados = null;
		InvolucradoDTO imputado = null;
		Expediente expediente = null;
		String numeroGeneralCaso = "";
		Caso caso =null;
		Long catDiscriminante=0L;
		Long idFuncionarioSolicitante= 0L;

		
		if(idNumeroExpediente == null || usuario == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
				
		Solicitud sol = solicitudDAO.consultarSolicitudesPorExpedienteYTipo(idNumeroExpediente, TiposSolicitudes.CARPETA_DE_INVESTIGACION);
		if(sol != null){
			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
		}
		expediente = expedienteDAO.consultarExpedientePorIdNumerExpediente(idNumeroExpediente);
		expedienteDTO.setExpedienteId(expediente.getExpedienteId());
		//Involucrado defendido = defensaInvolucradoDAO.consultarInvolucradoPGNumeroExpedienteDefensoria(idNumeroExpediente);
		List<Involucrado> defendidos = defensaInvolucradoDAO.consultarInvolucradosDEFNumeroExpedienteDefensoriaPorCalidad(expediente.getExpedienteId());
		if(defendidos != null && !defendidos.isEmpty() && defendidos.get(0).getElementoId() != null){
			
			//Agregado para distritos
			LOGGER.info("idNumeroExpediente: " + idNumeroExpediente);
			List <AvisoDetencion> avisosDetencion =  avisoDetencionDAO.consultarAvisosDetencionPorExpediente(expediente.getExpedienteId());
			LOGGER.info("Se encontraron "+avisosDetencion.size() + " Avisos de detencion");
			LOGGER.info("defendido.getElementoId(): " + defendidos.get(0).getElementoId());
			

			for(AvisoDetencion avisoDetencion : avisosDetencion){
				LOGGER.info("avisoDetencion.getDocumentoId(): " + avisoDetencion.getDocumentoId());
				if(avisoDetencion.getDetencion() != null && avisoDetencion.getDetencion().getInvolucrado() != null
						&& avisoDetencion.getDetencion().getInvolucrado().getElementoId() != null){
					LOGGER.info("getInvolucrado().getElementoId()" + avisoDetencion.getDetencion().getInvolucrado().getElementoId());
					if(avisoDetencion.getDetencion().getInvolucrado().getElementoId() ==  defendidos.get(0).getElementoId()){						
						//catDiscriminante= avisoDetencion.getExpediente().getDiscriminante().getCatDiscriminanteId();
						idFuncionarioSolicitante = avisoDetencion.getIdFuncionarioSolicitante();
						LOGGER.info("El id del idFuncionarioSolicitante asignado es: " + idFuncionarioSolicitante);
					}else
						LOGGER.info("No coniciden los idos");
				}else
					LOGGER.info("El involucrado de la detencion es NULL>>>>>>>>>>>>");
			}
			
			imputado = InvolucradoTransformer.transformarInvolucrado(defendidos.get(0));
		}else{
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}
		
		caso = expediente.getCaso();
		
		if(caso != null){
			numeroGeneralCaso = caso.getNumeroGeneralCaso();
		}
		solicitud.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId()));
		LOGGER.info("===> Numero Geeral de Caso "+numeroGeneralCaso);
		solicitud.setNumeroCasoAsociado(numeroGeneralCaso);
		solicitud.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		solicitud.setNombreSolicitante(usuario.getFuncionario().getNombreCompleto());
		
		sol = SolicitudTransformer.solicitudTransformer(solicitud);
		sol.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		sol.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		sol.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		sol.setFechaCreacion(Calendar.getInstance().getTime());
		sol.setNombreDocumento("SOLICITUD_DE_CARPETA_DE_INVESTIGACION");
		sol.setNumeroExpediente(new NumeroExpediente(idNumeroExpediente));
		sol.setConfInstitucion(new ConfInstitucion(Instituciones.DEF.getValorId()));
		//Permitira consultarlas el rol agente mp en Fiscalia
		sol.setAreaOrigen(usuario.getAreaActual().getAreaId());
		
		sol.setFuncionarioSolicitante(new Funcionario(usuario.getFuncionario().getClaveFuncionario()));
		
		solicitud.setNombreSolicitanteExternoInterno(solicitud.getNombreSolicitante());
		solicitud.setInvolucradoDTO(imputado);
		solicitud.setIdFuncionarioSolicitante(idFuncionarioSolicitante);
		
		try{
			LOGGER.info("Antes de invocar al web services.");
			LOGGER.info(solicitud);
			procuraduriaClienteService.solicitarCopiaCarpetaDeInvestigacion(solicitud,catDiscriminante);
		}catch(Exception e){
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION);
		}
		LOGGER.info("SE ENVIO LA SOLICITUD DE CARPETA DE INVESTIGACION CON FOLIO "+solicitud.getFolioSolicitud());
		solicitud.setNombreSolicitanteExternoInterno("");
		LOGGER.info("A PUNTO DE CREAR LA SOLICITUD DE CARPETA DE INVESTIGACION");
		Long id = solicitudDAO.create(sol);
		LOGGER.info("SE CREO LA SOLICITUD DE CARPETA DE INVESTIGACION CON ID "+id);
		solicitud.setDocumentoId(id);

		return solicitud;
	}
	
	@Override
	public SolicitudTranscripcionAudienciaDTO registrarSolicitudTranscripcionAudiencia(
			SolicitudTranscripcionAudienciaDTO solicitud) throws NSJPNegocioException {
		SolicitudTranscripcionAudiencia sol = SolicitudAudienciaTransformer.transformarSolicitud(solicitud);
		if(solicitud.getFolioSolicitud() == null || (solicitud.getFolioSolicitud() != null && solicitud.getFolioSolicitud().trim().equals("")))
			sol.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		sol.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		
		sol.setNombreDocumento("SOLICITUD_DE");
		//
		
		Long idSolicitud = solicitudTranscricpionAudienciaDAO.create(sol);
		solicitud.setDocumentoId(idSolicitud);
		//Recupera el folio asignado y lo asigna a la solicitud
		solicitud.setFolioSolicitud(solicitudDAO.obtenerFolioDeSolicitud(idSolicitud));
		//Se asigna el confInstitucion
		solicitud.setInstitucion(new ConfInstitucionDTO(confInstitucionDAO.consultarInsitucionActual().getConfInstitucionId()));
		return solicitud;
	}


}

/**
 * Nombre del Programa : ConsultarAudienciaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio para consultar Audiencias
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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaEvidenciaDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.AudienciaEvidencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudAudienciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para consultar Audiencias.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarAudienciaServiceImpl implements ConsultarAudienciaService {

    private final static Logger logger = Logger
            .getLogger(ConsultarAudienciaServiceImpl.class);

    @Autowired
    private AudienciaDAO audDao;

    @Autowired
    private ConsultarIndividuoService consultarIndividuoService;

    @Autowired
    private AudienciaEvidenciaDAO objetoDAO;

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    
    @Autowired
    private PJClienteService pjClienteService;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
    @Autowired
    private SolicitudAudienciaDAO solicitudAudienciaDAO;
    @Autowired
    private ExpedienteDAO expedienteDAO;
    
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService#
     * buscarAudiencias(mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO)
     */
    @Override
    public List<AudienciaDTO> buscarAudiencias(FiltroAudienciaDTO filtro)
            throws NSJPNegocioException {
        final Long usrId = filtro.getUsuario() != null ? filtro.getUsuario().getIdUsuario() : null;
                	
    	/*
         * Usado para setear el distrito y consultar por distritos de PJ
         */
        long discriminanteId = 0L; 
        
       
		if (filtro.getUsuario() != null
				&& filtro.getUsuario().getFuncionario() != null
				&& filtro.getUsuario().getFuncionario().getDiscriminante() != null
				&& filtro.getUsuario().getFuncionario().getDiscriminante()
						.getCatDiscriminanteId() != null) {

			discriminanteId = filtro.getUsuario().getFuncionario()
					.getDiscriminante().getCatDiscriminanteId();
		}
            
        NumeroExpediente ne = new NumeroExpediente();
        if(filtro.getNumeroExpediente() != null && !filtro.getNumeroExpediente().isEmpty()){
            ne = numeroExpedienteDAO.obtenerNumeroExpediente(filtro.getNumeroExpediente(),discriminanteId);        	
        }
        
        List<AudienciaDTO> resp = new ArrayList<AudienciaDTO>();
        List<Audiencia> fromBd = new ArrayList<Audiencia>();
        if (filtro.getNumeroExpediente()!=null) {
        	logger.debug("/**** SERVICIO PARA OBTENER AUDIENCIAS DE UNA CAUSA ***/");
        	fromBd = this.audDao.buscarAudienciaByCausa(filtro.getNumeroExpediente(),discriminanteId);
        } else {
        	logger.debug("/**** BUSCAR AUDIENCIAS ***/" + filtro.getNumeroExpedienteId());
        	fromBd = this.audDao.buscarAudiencias(usrId,
                    ne.getNumeroExpedienteId(), filtro.getFechaInicial(),
                    filtro.getFechaFinal(), filtro.isDiaCompleto(), filtro.getTipoAudiencia(), filtro.isCausa(),discriminanteId, filtro.getIdDistritoFiltroAudiencias());
        }               
        
        for (Audiencia row : fromBd) {
        	AudienciaDTO audienciaActual = EventoTransformer.transformarAudienciaBasico(row);
        	audienciaActual.setSolicitud(SolicitudAudienciaTransformer.transformarSolicitudBasico(row.getSolicitud()));
            resp.add(audienciaActual);
        }

        return resp;
    }
    
    @Override
    public AudienciaDTO obtenerAudiencia(AudienciaDTO audIn)
            throws NSJPNegocioException {

        logger.debug("audIn.getId() :: " + audIn.getId());

        final Audiencia pojo = this.audDao.read(audIn.getId());

        final AudienciaDTO auDto = EventoTransformer.transformarAudienciaCompleto(pojo);
        auDto.setSolicitud(SolicitudAudienciaTransformer.transformarSolicitudBasico(pojo.getSolicitud()));
        if (pojo.getExpediente()!= null && pojo.getExpediente().getCaso() != null) {
            final CasoDTO cas = new CasoDTO(pojo.getExpediente().getCaso()
                    .getCasoId(), pojo.getExpediente().getCaso()
                    .getNumeroGeneralCaso());
            auDto.getExpediente().setCasoDTO(cas);
        }

        List<AudienciaEvidencia> objetosExpediente = objetoDAO
                .consultarObjetosByAudiencia(audIn.getId());

        if (objetosExpediente != null) {
            ObjetoDTO objDto = null;
            for (AudienciaEvidencia objeto : objetosExpediente) {
            	if(objeto.getEvidencia() != null){
	                objDto = ObjetoTransformer.transformarObjeto(objeto.getEvidencia().getObjeto());
	                objDto.setFechaRecepcion(objeto.getFechaRecepcion());
	                auDto.addEvidencia(objDto);
            	}
            }
        }

        // TODO VAP Codificar método.
        return auDto;
    }
    
    @Override
    public AudienciaDTO obtenerAudiencia(SolicitudAudienciaDTO solAudIn)
            throws NSJPNegocioException {
        logger.debug("audIn.getDocumentoId() :: " + solAudIn.getDocumentoId());
        final Long idAud = this.audDao.obtenerIdAudienciaByIdSolicitud(solAudIn
                .getDocumentoId());
        final AudienciaDTO temp = new AudienciaDTO();
        temp.setId(idAud);
        return this.obtenerAudiencia(temp);
    }

	@Override
	public Boolean validarExistenciaPruebas(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA VALIDAR SI UNA AUDIENCIA TIENE PRUEBAS ASOCIADAS ****/");
		
		/*Verificación de parámetros*/
		if(audienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<AudienciaEvidencia> objetos = objetoDAO.consultarObjetosByAudiencia(audienciaDTO.getId());
		Boolean res;
		if(objetos!=null){
			res=(objetos.size()>0)?true:false;
		}else
			res=false;

		return res;
	}

	public List<AudienciaDTO> buscarAudienciasSinTranscripcionResolutivos()throws NSJPNegocioException{
		
		List<Audiencia> audiencias = audDao.buscarAudienciasSinResolutivos();
		List<AudienciaDTO> result = new LinkedList<AudienciaDTO>();
		for(Audiencia audiencia : audiencias){
			result.add(EventoTransformer.transformarAudienciaBasico(audiencia));
		}
		
		return result;
	}

	@Override
	public List<AudienciaDTO> consultarAudienciasByTipoYFecha(AudienciaDTO audienciaDTO, 
				TipoAudiencia tipoAudiencia,UsuarioDTO usuario) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AUDICENCIA POR TIPO Y FECHA DE AUDIENCIA ****/");
		
		if (audienciaDTO==null || audienciaDTO.getFechaEvento()==null
				|| tipoAudiencia==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);	
		/*
		* Usado para obtener el discriminante Id
		*/
		  long discriminanteId = 0L; 
				

		if (usuario != null
				&& usuario.getFuncionario() != null
				&& usuario.getFuncionario().getDiscriminante() != null
				&& usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuario.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
		
		List<Audiencia> listAudiencias = audDao.consultarAudienciasByTipoYFecha(audienciaDTO.getFechaEvento(), tipoAudiencia.getValorId(),discriminanteId);
		
		List<AudienciaDTO> listRespuesta = new ArrayList<AudienciaDTO>();
		for (Audiencia audiencia : listAudiencias) {
			AudienciaDTO audiRespuesta = AudienciaTransformer.transformarDTO(audiencia);
			
			NumeroExpediente causa = new NumeroExpediente();
			NumeroExpediente tocaOrCarpeta = new NumeroExpediente();
			//Se obtiene la causa y la toca o carpeta de ejecucion
			if (audiencia.getNumeroExpediente()!=null) {
				tocaOrCarpeta = numeroExpedienteDAO.read(audiencia.getNumeroExpediente().getNumeroExpedienteId());
				audiRespuesta.setTocaOrCarpeta(ExpedienteTransformer.transformarExpedienteBasico(tocaOrCarpeta));
			
				if (audiencia.getNumeroExpediente().getNumeroExpedientePadre()!=null) {
					causa = numeroExpedienteDAO.read(audiencia.getNumeroExpediente().getNumeroExpedientePadre().getNumeroExpedienteId());
					audiRespuesta.setCausa(ExpedienteTransformer.transformarExpedienteBasico(causa));
				}
			}
			 
			//Obtener sentenciado			
			for (InvolucradoAudiencia sentenciado : audiencia.getInvlucradoAudiencias()) {
				if (sentenciado.getInvolucrado().getSituacionJuridica().getValorId().equals(SituacionJuridica.SENTENCIADO.getValorId())) {
					logger.debug("/**** SE OBTUVO SENTENCIADO ****/");
					audiRespuesta.setSentenciado(InvolucradoTransformer.transformarInvolucradoBasico(sentenciado.getInvolucrado()));
				}
			}
			
			listRespuesta.add(audiRespuesta);
		}
				
		return listRespuesta;
	}

	@Override
	public AudienciaDTO obtenerAudienciaByNumeroAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER UNA AUDIENCIA POR NUMERO DE AUDIENCIA ****/");
		
		if (audienciaDTO==null || audienciaDTO.getConsecutivo()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Audiencia audiencia = audDao.obtenerAudienciaByNumeroAudiencia(audienciaDTO.getConsecutivo()); 
		
		AudienciaDTO audRespuesta = AudienciaTransformer.transformarDTO(audiencia);
		
		return audRespuesta;
	}

	@Override
	public List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYEstatus(
			ExpedienteDTO expedienteDTO, EstatusAudiencia estatusAudiencia) throws NSJPNegocioException {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER UNA AUDIENCIA ASOCIADAS A UN NUMERO EXPEDIENTE Y SE ENCUENTRAN EN UN ESTATUS DETERMINADO ****/");
		
		if (expedienteDTO==null || expedienteDTO.getNumeroExpedienteId()==null 
				|| estatusAudiencia==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Audiencia> audienciasAsociadas = audDao.consultarAudienciaByNumeroExpedienteYEstatus(expedienteDTO.getNumeroExpedienteId(), estatusAudiencia.getValorId());
		
		List<AudienciaDTO> audienciasRetorno = new ArrayList<AudienciaDTO>();
		for (Audiencia audiencia : audienciasAsociadas) {
			audienciasRetorno.add(AudienciaTransformer.transformarDTO(audiencia));
		}
		
		return audienciasRetorno;
	}
	
	@Override
	public List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYListaEstatus(
			ExpedienteDTO expedienteDTO, EstatusAudiencia[] estatusAudiencia) throws NSJPNegocioException {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER UNA AUDIENCIA ASOCIADAS A UN NUMERO EXPEDIENTE Y SE ENCUENTRAN EN UN ESTATUS DETERMINADO ****/");
		
		if (expedienteDTO==null || expedienteDTO.getNumeroExpedienteId()==null 
				|| estatusAudiencia==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Audiencia> audienciasAsociadas = audDao.consultarAudienciaByNumeroExpedienteYListaEstatus(expedienteDTO.getNumeroExpedienteId(), estatusAudiencia);
		
		List<AudienciaDTO> audienciasRetorno = new ArrayList<AudienciaDTO>();
		for (Audiencia audiencia : audienciasAsociadas) {
			audienciasRetorno.add(AudienciaTransformer.transformarDTO(audiencia));
		}
		
		return audienciasRetorno;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService#consultarAudienciasConSolicitudesPorTipoYEstado(mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes[], mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud[])
	 */
	@Override
	public List<AudienciaDTO> consultarAudienciasConSolicitudesPorTipoYEstado(
			TiposSolicitudes[] tipos, EstatusSolicitud[] estados,UsuarioDTO usuario) throws NSJPNegocioException{
		
		/*
		* Usado para obtener el discriminante Id
		*/
		  long discriminanteId = 0L; 
				

		if (usuario != null
				&& usuario.getFuncionario() != null
				&& usuario.getFuncionario().getDiscriminante() != null
				&& usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuario.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
		
		List<Audiencia> audiencias = audDao.consultarAudienciasConSolicitudesPorTipoYEstado(tipos, estados,discriminanteId);
		List<AudienciaDTO> resultado = new ArrayList<AudienciaDTO>();
		for(Audiencia au:audiencias){
			resultado.add(AudienciaTransformer.transformarDTO(au));
		}
		return resultado;
		
	}

	@Override
	public List<AudienciaDTO> consultarAudienciasByTipoYEstatus(
			TipoAudiencia tipoAudiencia, EstatusAudiencia estatusAudiencia)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AUDIENCIAS POR TIPO Y ESTATUS ****/");
		
		List<Audiencia> audiencias = audDao.consultarAudienciasByTipoYEstatus(tipoAudiencia.getValorId(), estatusAudiencia.getValorId());
		
		List<AudienciaDTO> audRetorno = new ArrayList<AudienciaDTO>();
		for (Audiencia audiencia : audiencias) {
			audRetorno.add(AudienciaTransformer.transformarDTO(audiencia));
		}
		
		return audRetorno;
	}

	@Override
	public List<AudienciaDTO> consultarAudienciasByFechasyEstatus(AudienciaDTO audiencia) throws NSJPNegocioException{
		return pjClienteService.consultarAudienciasByFechasYEstatus(audiencia);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService#consultarORegistrarSiguienteAudiencia(java.lang.Long)
	 */
	@Override
	public AudienciaDTO consultarORegistrarSiguienteAudiencia(
			Long audienciaActualId) throws NSJPNegocioException {
		
 		Audiencia audienciaActual = audDao.read(audienciaActualId);
		Audiencia audienciaSiguiente = null;
		if(audienciaActual == null){
			logger.error("No se encontró la audiencia actual");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		//Buscar audiencia si programación de sala
		List<Audiencia> audienciasNoProgramadas = audDao.consultarAudienciaByNumeroExpedienteYEstatus(audienciaActual.getNumeroExpediente().getNumeroExpedienteId(),
				EstatusAudiencia.SOLICITADA.getValorId());
		if(audienciasNoProgramadas != null && !audienciasNoProgramadas.isEmpty()){
			audienciaSiguiente = audienciasNoProgramadas.get(0);
		}
		//No existe audiencia siguiente solicitada, crear audiencia siguiente copia de la actual
		if(audienciaSiguiente == null){
			audienciaSiguiente = new Audiencia();
			SolicitudAudiencia solAud = new SolicitudAudiencia();
			audienciaSiguiente.setSolicitud(solAud);
			
			solAud.setFechaCreacion(new Date());
			solAud.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
			solAud.setForma(new Forma(Formas.SOLICITUD.getValorId()));
			Funcionario juezAudiencia = obtenerJuezDeAudiencia(audienciaActual);
			solAud.setFuncionarioSolicitante(juezAudiencia);
			solAud.setMotivo(null);
			solAud.setNombreDocumento("AUDIENCIA");
			solAud.setNombreSolicitante(juezAudiencia != null?juezAudiencia.getNombreCompleto():null);
			solAud.setNumeroExpediente(audienciaActual.getNumeroExpediente());
			solAud.setTipoSolicitud(new Valor(TiposSolicitudes.AUDIENCIA.getValorId()));
			solAud.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
			solAud.setVersion(1.0);
			solAud.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
			solAud.setAudiencia(audienciaSiguiente);
			audienciaSiguiente.setEstatus(new Valor(EstatusAudiencia.SOLICITADA.getValorId()));
			audienciaSiguiente.setFolioAudiencia(generarFolioSolicitudService.generarFolioAudiencia());
			audienciaSiguiente.setNumeroExpediente(audienciaActual.getNumeroExpediente());
			
			
			audDao.create(audienciaSiguiente);	
			solicitudAudienciaDAO.create(solAud);
			
		}
		
		
		
		
		return AudienciaTransformer.transformarDTO(audienciaSiguiente);
	}
	/**
	 * Obtiene el primer juez de la audiencia
	 * @param audienciaActual Audiencia de la cuál obtener el juez
	 * @return Juez encontrado, null en caso de no contar con ningún juez en la audiencia
	 */
	private Funcionario obtenerJuezDeAudiencia(Audiencia audienciaActual) {
		for(FuncionarioAudiencia fa:audienciaActual.getFuncionarioAudiencias()){
			if(fa.getFuncionario() != null && fa.getFuncionario().getTipoEspecialidad() != null && 
			fa.getFuncionario().getTipoEspecialidad().getValorId().equals(TipoEspecialidad.JUEZ.getValorId())		
			){
				return fa.getFuncionario();
			}
		}
		return null;
	}
}

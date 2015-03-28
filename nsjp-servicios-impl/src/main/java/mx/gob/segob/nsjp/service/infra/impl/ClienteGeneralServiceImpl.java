/**
 * Nombre del Programa : ClienteGeneralServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 Aug 2011
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
package mx.gob.segob.nsjp.service.infra.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.namespace.QName;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.service.infra.ClienteGeneralService;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.CriterioConsultaFuncionarioExternoWSDTOTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.CatDiscriminanteWSDTOTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.FuncionarioWSDTOTransformer;
import mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.ConsultarFuncionarioExternoExporter;
import mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.ConsultarFuncionarioExternoExporterImplService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviardocumento.DocumentoWSDTOTransformer;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;
import mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal.ConsultarFuncionariosXTribunalExporter;
import mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal.ConsultarFuncionariosXTribunalExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.CatDiscriminanteWSDTO;
import mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.ConsultarTribunalesPorDistritoServiceExporter;
import mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.ConsultarTribunalesPorDistritoServiceExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion.DocumentoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion.EnviarDocumentoAInstitucionExporter;
import mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion.EnviarDocumentoAInstitucionExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.AudienciaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.NotificacionWSDTO;
import mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.RecibirNotificacionAudienciaExporter;
import mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.RecibirNotificacionAudienciaExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.CalidadWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.CasoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.DelitoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.InvolucradoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.NSJPNegocioException_Exception;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.NombreDemograficoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.RegistrarReplicaCasoExporter;
import mx.gob.segob.nsjp.ws.cliente.registarreplicacaso.RegistrarReplicaCasoExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaExporterImpl;
import mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO;

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
@Service
@Transactional
public class ClienteGeneralServiceImpl implements ClienteGeneralService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ClienteGeneralServiceImpl.class);
    @Autowired
    private ConfInstitucionDAO institucionDao;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.infra.ClienteGeneralService#enviarAcuseRecibo
     * (java.lang.Long, mx.gob.segob.nsjp.comun.enums.institucion.Instituciones)
     */
    @Override
    public void enviarAcuseRecibo(Long idSolicitud, Instituciones target)
            throws NSJPNegocioException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.infra.ClienteGeneralService#enviarReplicaCaso
     * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO,
     * mx.gob.segob.nsjp.comun.enums.institucion.Instituciones)
     */
    @Override
    public void enviarReplicaCaso(ExpedienteDTO expConCaso,
            ConfInstitucion target) throws NSJPNegocioException {
        URL ep;
        try {
            ep = new URL(target.getUrlInst()
                    + "/RegistrarReplicaCasoExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarReplicaCasoExporterImplService");
            RegistrarReplicaCasoExporterImplService ss = new RegistrarReplicaCasoExporterImplService(
                    ep, SERVICE_NAME);
            RegistrarReplicaCasoExporter port = ss
                    .getRegistrarReplicaCasoExporterImplPort();
            logger.debug("Port obtenido....");
            CasoWSDTO toSend = new CasoWSDTO();
            
            toSend.setNumeroGeneralCaso(expConCaso.getCasoDTO().getNumeroGeneralCaso());
            toSend.setFechaApertura(WsTransformer.transformFecha(expConCaso.getCasoDTO().getFechaApertura()));
        
            List<InvolucradoWSDTO> invos2s = new ArrayList<InvolucradoWSDTO>();
            List<DelitoWSDTO> delis2s = null;
            List<String> alias2s = null;
            List<NombreDemograficoWSDTO> noms2s = null;
            
            InvolucradoWSDTO invo=null;
            DelitoWSDTO deli =null;
            CalidadWSDTO cali=null;
            NombreDemograficoWSDTO nomb=null;
            
            
            List<InvolucradoDTO> invoFuente = recuperarInvolucrados(expConCaso);
            
            for (InvolucradoDTO dto : invoFuente) {
                invo = new InvolucradoWSDTO();
                cali = new CalidadWSDTO();
                cali.setValorIdCalidad(dto.getCalidadDTO().getValorIdCalidad().getIdCampo());
                cali.setDescripcionEstadoFisico(dto.getCalidadDTO().getDescripcionEstadoFisico());
                invo.setCalidad(cali);
                invo.setTipoPersona(dto.getTipoPersona());
                invo.setFolioElemento(dto.getFolioElemento());
                invo.setDesconocido(dto.getDesconocido());
                noms2s = new ArrayList<NombreDemograficoWSDTO>();
                for (NombreDemograficoDTO nomDto: dto.getNombresDemograficoDTO()) {
                    nomb = new NombreDemograficoWSDTO();
                    nomb.setNombre(nomDto.getNombre());
                    nomb.setApellidoPaterno(nomDto.getApellidoPaterno());
                    nomb.setApellidoMaterno(nomDto.getApellidoMaterno());
                    noms2s.add(nomb);
                }
                
                delis2s = new ArrayList<DelitoWSDTO>();
                for (DelitoDTO delCometidoDto : dto.getDelitosCometidos()) {
                    deli = new DelitoWSDTO();
                    deli.setIdCatDelito(delCometidoDto.getCatDelitoDTO().getCatDelitoId());
                    deli.setEsPrincipal(delCometidoDto.getEsPrincipal());
                    delis2s.add(deli);
                }
                invo.setDelitosCometidos(delis2s);
                
                alias2s = new ArrayList<String>();
                for (AliasInvolucradoDTO aliasDto: dto.getAliasInvolucradoDTO()) {
                    alias2s.add(aliasDto.getAlias());
                }
                
                invo.setNombresDemograficos(noms2s);
                //invo.setDelitosCometidos(delis2s);
                invo.setAliasInvolucrado(alias2s);
                invos2s.add(invo);
            }
            toSend.setInvolucradosDTO(invos2s);
        
            logger.debug("Invocando registrarReplicaCaso...");

            port.registrarReplicaCaso(toSend);

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }

    /**
     * @param expConCaso
     * @return
     */
    private List<InvolucradoDTO> recuperarInvolucrados(ExpedienteDTO expConCaso) {
        final List<InvolucradoDTO> invoFuente = new ArrayList<InvolucradoDTO>();
        invoFuente.addAll(expConCaso.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA));
        invoFuente.addAll(expConCaso.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA));
        invoFuente.addAll(expConCaso.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION));
        
        List<InvolucradoDTO> tempDenunciante = expConCaso.getInvolucradoByCalidad(Calidades.DENUNCIANTE);
        if (tempDenunciante!=null) {
            for (InvolucradoDTO invoDen:tempDenunciante) {
                if (invoDen.getCondicion()!=null && invoDen.getCondicion().intValue()==1) {
                    invoFuente.add(invoDen);
                }
            }
        }

        tempDenunciante = expConCaso.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION);
        if (tempDenunciante!=null) {
            for (InvolucradoDTO invoDen:tempDenunciante) {
                if (invoDen.getCondicion()!=null && invoDen.getCondicion().intValue()==1) {
                    invoFuente.add(invoDen);
                }
            }
        }
        return invoFuente;
    }

    @Override
    public Boolean enviarNotificacionAudienciaFuncionarioExtenor(
            Audiencia audiencia, String nombreFuncionario,
            Instituciones target,
            String folioNotificacion, String consecutivoNotificacion) throws NSJPNegocioException {
        URL ep;
        Boolean resp = null;
        logger.info("Inicia - enviarNotificacionAudienciaFuncionarioExtenor(...)");
        try {
            ConfInstitucion destino = this.institucionDao.read(target.getValorId());
            ep = new URL(destino.getUrlInst()
                    + "/RecibirNotificacionAudienciaExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RecibirNotificacionAudienciaExporterImplService");
        RecibirNotificacionAudienciaExporterImplService ss = new RecibirNotificacionAudienciaExporterImplService(ep, SERVICE_NAME);
        RecibirNotificacionAudienciaExporter port = ss.getRecibirNotificacionAudienciaExporterImplPort();  
        
        SolicitudAudienciaBasicoWSDTO toSend = new SolicitudAudienciaBasicoWSDTO(); 
        toSend.setNombreSolicitante(nombreFuncionario);
        AudienciaWSDTO aud = new AudienciaWSDTO();
        if (audiencia.getNumeroExpediente().getExpediente().getCaso()!=null) {
            aud.setNumeroGeneralCaso(audiencia.getNumeroExpediente().getExpediente().getCaso().getNumeroGeneralCaso());
        }
        if (audiencia.getSalaAudiencia()!=null) {
        	logger.debug(audiencia.getSalaAudiencia());
            logger.debug(audiencia.getSalaAudiencia().getNombreSala());
            aud.setNombreSala(audiencia.getSalaAudiencia().getNombreSala());
            aud.setDomicilioSala(audiencia.getSalaAudiencia().getDomicilioSala());
            aud.setUbicacionSala(audiencia.getSalaAudiencia().getUbicacionSala());
        } else {
            aud.setNombreSala("Temporal");
            aud.setDomicilioSala(audiencia.getSalaTemporal().getDomicilioSala());
            aud.setUbicacionSala(audiencia.getSalaTemporal().getUbicacionSala()); 
        }

        aud.setDuracionEstimada(audiencia.getDuracionEstimada());
        aud.setFechaHoraAudiencia(WsTransformer.transformFecha(audiencia.getFechaAudiencia()));
        Calendar auxFin = Calendar.getInstance();
        auxFin.setTime(audiencia.getFechaAudiencia());
        auxFin.add(Calendar.MINUTE, audiencia.getDuracionEstimada());
        aud.setFechaFin(WsTransformer.transformFecha(auxFin.getTime()));
        aud.setFechaInicio(WsTransformer.transformFecha(audiencia.getFechaAudiencia()));
        aud.setFolioAudiencia(audiencia.getFolioAudiencia());
        aud.setTipoAudienciaId(audiencia.getTipo().getValorId());
        
        NotificacionWSDTO notiws = new NotificacionWSDTO();
        notiws.setConsecutivoNotificacion(consecutivoNotificacion);
        notiws.setFolioNotificacion(folioNotificacion);
        aud.setNotificaion(notiws);
        
        List<mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.InvolucradoWSDTO> invos = new ArrayList<mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.InvolucradoWSDTO>();
       for (InvolucradoAudiencia ia : audiencia.getInvlucradoAudiencias()){
           mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.InvolucradoWSDTO invoWs= new mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.InvolucradoWSDTO();
           mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.CalidadWSDTO cali = new mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.CalidadWSDTO();
           cali.setValorIdCalidad(ia.getInvolucrado().getCalidad().getTipoCalidad().getValorId());
           invoWs.setFolioElemento(ia.getInvolucrado().getFolioElemento());
           logger.debug("Enviando los siguientes involucrados :: " + ia.getInvolucrado().getFolioElemento());
           invoWs.setCalidad(cali);
           invos.add(invoWs);
       }
       toSend.setInvolucradosDTO(invos);
        
        toSend.setConfInstitucionId(this.institucionDao.consultarInsitucionActual().getConfInstitucionId());
        
        toSend.setAudiencia(aud);
        resp =port.recibirNotificacionAudiencia(toSend);
        logger.info("Fin - enviarNotificacionAudienciaFuncionarioExtenor(...)");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.notificacionaudiencia.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
        return resp;
    }
    
    
    
    @Override
    public List<FuncionarioDTO> consultarFuncionariosXTribunal(
   		 Long catDiscriminanteId,Instituciones target) throws NSJPNegocioException {
        URL ep;
        List<mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal.FuncionarioWSDTO> resp = null;
        List<FuncionarioDTO> loFuncionarioDTO = new ArrayList<FuncionarioDTO>();
        logger.info("Inicia - consultarFuncionariosXTribunal(...)");
        try {
            ConfInstitucion destino = this.institucionDao.read(target.getValorId());
            ep = new URL(destino.getUrlInst()
                    + "/ConsultarFuncionariosXTribunalExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "ConsultarFuncionariosXTribunalExporterImplService");
            
        ConsultarFuncionariosXTribunalExporterImplService ss = new ConsultarFuncionariosXTribunalExporterImplService(ep, SERVICE_NAME);
        ConsultarFuncionariosXTribunalExporter port = ss.getConsultarFuncionariosXTribunalExporterImplPort();

        resp = port.consultarFuncionariosXTribunal(catDiscriminanteId);
        
        for (mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal.FuncionarioWSDTO funcionarioWSDTO : resp) {
        	loFuncionarioDTO.add(FuncionarioWSDTOTransformer.transformarFuncionario(funcionarioWSDTO));
		}
        
        logger.info("Fin - consultarFuncionariosXTribunal(...)");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
        return loFuncionarioDTO;
    }


    @Override
    public List<CatDiscriminanteDTO> consultarTribunalesPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException {
    	
    	logger.info("Inicia - consultarTribunalesPorDistrito(...)");

    	List<CatDiscriminanteDTO> discriminantesDTO = new ArrayList<CatDiscriminanteDTO>();
		URL ep;
		try {
			// Configuración de WS
			ConfInstitucion destino = this.institucionDao.read(target
					.getValorId());
			ep = new URL(
					destino.getUrlInst()
							+ "/ConsultarTribunalesPorDistritoServiceExporterImplService?wsdl");

			final QName SERVICE_NAME = new QName(
					"http://impl.ws.service.nsjp.segob.gob.mx/",
					"ConsultarTribunalesPorDistritoServiceExporterImplService");

			ConsultarTribunalesPorDistritoServiceExporterImplService ss = new ConsultarTribunalesPorDistritoServiceExporterImplService(
					ep, SERVICE_NAME);

			ConsultarTribunalesPorDistritoServiceExporter port = ss
					.getConsultarTribunalesPorDistritoServiceExporterImplPort();

			// Invocación del WS
			List<CatDiscriminanteWSDTO> discriminantesWSDTO = port
					.consultarTribunalesPorDistrito(distritoId);

			logger.info("Respuesta - consultarTribunalesPorDistrito:"
					+ discriminantesWSDTO);

			if (discriminantesWSDTO != null && !discriminantesWSDTO.isEmpty()) {
				logger.info("discriminantesWSDTO.size(): "
						+ discriminantesWSDTO.size());
				for (CatDiscriminanteWSDTO catDiscriminanteWSDTO : discriminantesWSDTO) {
					CatDiscriminanteDTO discriminanteDTO = CatDiscriminanteWSDTOTransformer
							.transformarCatDiscriminante(catDiscriminanteWSDTO);
					logger.info(" DiscriminanteDTO : " + discriminantesDTO);
					discriminantesDTO.add(discriminanteDTO);
				}
			}
			logger.info("Fin - consultarTribunalesPorDistrito(...)");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.NSJPNegocioException_Exception e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}

		return discriminantesDTO;
    }
    

    @Override
	public Long enviarDocumentoAInstitucion(DocumentoDTO documentoDTO,String numeroDeCaso, Instituciones target)
			throws NSJPNegocioException {
		
		   logger.debug("ClienteGeneralServiceImpl - enviarDocumentoAInstitucion ");
		   logger.debug("ClienteGeneralServiceImpl - documento = " + documentoDTO);
		   logger.debug("ClienteGeneralServiceImpl - numeroExpediente = " + numeroDeCaso);

		   if(documentoDTO!= null )
			   logger.debug("ClienteGeneralServiceImpl - documento - ArchivoDigital = " + documentoDTO.getArchivoDigital());		   
		   URL ep;
	    	 
		try {
			
			// Configuracion para acceder al web service
			ConfInstitucion destino = this.institucionDao.read(target
					.getValorId());
			ep = new URL(
					destino.getUrlInst()
							+ "/EnviarDocumentoAInstitucionExporterImplService?wsdl");
			
			final QName SERVICE_NAME = new QName(
					"http://impl.ws.service.nsjp.segob.gob.mx/",
					"EnviarDocumentoAInstitucionExporterImplService");	   
			
			EnviarDocumentoAInstitucionExporterImplService ss =
				new EnviarDocumentoAInstitucionExporterImplService(ep, SERVICE_NAME);
			
			EnviarDocumentoAInstitucionExporter port =
				ss.getEnviarDocumentoAInstitucionExporterImplPort();
			// Hasta aqui finaliza la configuracion para acceder al web service

			// Aqui inicia el proceso de transformar los dto en wsDto
			logger.info(" DocumentoDTO:" + documentoDTO);
			if(documentoDTO!= null )
				   logger.debug("PJCLienteService - documento - ArchivoDigital Before Transformer = " + documentoDTO.getArchivoDigital());
			
			DocumentoWSDTO documentoIncumplimientoWSDTO = DocumentoWSDTOTransformer.transformarWSDTO(documentoDTO);
			logger.info(" DocumentoWSDTO:" + documentoIncumplimientoWSDTO);
			if(documentoIncumplimientoWSDTO!= null )
				   logger.debug("ClienteGeneralServiceImpl - documento - ArchivoDigital After Transformer = " + documentoIncumplimientoWSDTO.getArchivoDigital());
			
			
			Long idDocumento = port.enviarDocumentoAInstitucion(documentoIncumplimientoWSDTO, numeroDeCaso);
			logger.debug("----------------------------------------ClienteGeneralServiceImpl: DocumentoEnviado: idDocumento = " + idDocumento);
			
			return idDocumento;

		} catch (mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion.NSJPNegocioException_Exception e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}		
		
	}
	
    @Override
    public List<CatDiscriminanteDTO> consultarAgenciasPorDistrito( Long distritoId, Instituciones target) throws NSJPNegocioException {
    
    	logger.info("Inicia - consultarAgenciasPorDistrito(...)");

    	List<CatDiscriminanteDTO> discriminantesDTO = new ArrayList<CatDiscriminanteDTO>();
		URL ep;
		try {
			// Configuración de WS
			ConfInstitucion destino = this.institucionDao.read(target
					.getValorId());
			ep = new URL(
					destino.getUrlInst()
							+ "/ConsultarTribunalesPorDistritoServiceExporterImplService?wsdl");

			final QName SERVICE_NAME = new QName(
					"http://impl.ws.service.nsjp.segob.gob.mx/",
					"ConsultarTribunalesPorDistritoServiceExporterImplService");

			ConsultarTribunalesPorDistritoServiceExporterImplService ss = new ConsultarTribunalesPorDistritoServiceExporterImplService(
					ep, SERVICE_NAME);

			ConsultarTribunalesPorDistritoServiceExporter port = ss
					.getConsultarTribunalesPorDistritoServiceExporterImplPort();

			// Invocación del WS
			List<CatDiscriminanteWSDTO> discriminantesWSDTO = port
					.consultarAgenciasPorDistrito(distritoId);

			logger.info("Respuesta - consultarAgenciasPorDistrito:"
					+ discriminantesWSDTO);

			if (discriminantesWSDTO != null && !discriminantesWSDTO.isEmpty()) {
				logger.info("discriminantesWSDTO.size(): "
						+ discriminantesWSDTO.size());
				for (CatDiscriminanteWSDTO catDiscriminanteWSDTO : discriminantesWSDTO) {
					CatDiscriminanteDTO discriminanteDTO = CatDiscriminanteWSDTOTransformer
							.transformarCatDiscriminante(catDiscriminanteWSDTO);
					logger.info(" DiscriminanteDTO : " + discriminantesDTO);
					discriminantesDTO.add(discriminanteDTO);
}
			}
			logger.info("Fin - consultarAgenciasPorDistrito(...)");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.NSJPNegocioException_Exception e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}

		return discriminantesDTO;
    }

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.infra.ClienteGeneralService#consultarFuncionariosXCriterio(mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO, mx.gob.segob.nsjp.comun.enums.institucion.Instituciones)
	 */
	@Override
	public List<FuncionarioDTO> consultarFuncionariosXCriterio(
			CriterioConsultaFuncionarioExternoDTO criterioConsultaFuncionarioExternoDTO,
			Instituciones target) throws NSJPNegocioException {
		URL ep;
        List<mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.FuncionarioWSDTO> resp = null;
        List<FuncionarioDTO> loFuncionarioDTO = new ArrayList<FuncionarioDTO>();
        logger.info("Inicia - consultarFuncionariosXCriterio(...)");
        try {
            ConfInstitucion destino = this.institucionDao.read(target.getValorId());
            ep = new URL(destino.getUrlInst()
                    + "/ConsultarFuncionarioExternoExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "ConsultarFuncionarioExternoExporterImplService");
            
        ConsultarFuncionarioExternoExporterImplService ss = new ConsultarFuncionarioExternoExporterImplService(ep, SERVICE_NAME);
        ConsultarFuncionarioExternoExporter port = ss.getConsultarFuncionarioExternoExporterImplPort();

        resp = port.consultarFuncionariosXCriterio(CriterioConsultaFuncionarioExternoWSDTOTransformer.transformarWSDTOCliente(
        		criterioConsultaFuncionarioExternoDTO));
        
        for (mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.FuncionarioWSDTO funcionarioWSDTO : resp) {
        	loFuncionarioDTO.add(FuncionarioWSDTOTransformer.transformarFuncionario(funcionarioWSDTO));
		}
        
        logger.info("Fin - consultarFuncionariosXCriterio(...)");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno.NSJPNegocioException_Exception e) {
        	logger.error(e.getMessage());
        	throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} 
        return loFuncionarioDTO;
	}

	@Override
	public Boolean enviarSentencia(SentenciaDTO sentenciaDTO,
			Instituciones institucion) throws NSJPNegocioException {
		boolean resultado = false;
    	logger.info("Inicia - consultarAgenciasPorDistrito(...)");

		URL ep;
		try {
			// Configuraci?n de WS
			ConfInstitucion destino = this.institucionDao.read(institucion.getValorId());
			ep = new URL(destino.getUrlInst()
							+ "/SentenciaExporterImplService?wsdl");

			final QName SERVICE_NAME = new QName(
					"http://impl.ws.service.nsjp.segob.gob.mx/", 
					"SentenciaExporterImplService");
			
			SentenciaWSDTO sentenciaWSDTO = SentenciaTransformer.transformarDTO2ClienteWSDTO(sentenciaDTO);
			
	        SentenciaExporterImplService ss = new SentenciaExporterImplService(ep, SERVICE_NAME);
	        SentenciaExporterImpl port = ss.getSentenciaExporterImplPort();  
	        
	        port.crearSentencia(sentenciaWSDTO);
			
			logger.info("Fin - consultarAgenciasPorDistrito(...)");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (mx.gob.segob.nsjp.ws.cliente.sentencia.NSJPNegocioException_Exception e) {
			logger.error(e.getMessage(), e);
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
		return resultado;
	}

    
}

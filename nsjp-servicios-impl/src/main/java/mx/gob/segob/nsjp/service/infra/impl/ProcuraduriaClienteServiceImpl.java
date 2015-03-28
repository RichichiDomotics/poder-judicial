/**
 * Nombre del Programa : ProcuraduriaClienteServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del cliente del ws
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
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarinformepolicialhomologado.InformePolicialHomologadoWSDTOTransformer;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrint;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrintIPH;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.AvisoHechoDelictivoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.CalidadWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.DomicilioWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.HechoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.InvolucradoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.NombreDemograficoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.RecibirAvisoHDelictivoExporter;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.RecibirAvisoHDelictivoExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.TiempoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avisodetencion.AvisoDetencionWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avisodetencion.DetencionWSDTO;
import mx.gob.segob.nsjp.ws.cliente.avisodetencion.RegistrarAvisoDetencionDeAreaExternaExporter;
import mx.gob.segob.nsjp.ws.cliente.avisodetencion.RegistrarAvisoDetencionDeAreaExternaExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EnviarInformePolicialHomologadoExporter;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EnviarInformePolicialHomologadoExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.InformePolicialHomologadoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.NSJPNegocioException_Exception;
import mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitarCopiaCarpetaDeInvestigacionExporter;
import mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitarCopiaCarpetaDeInvestigacionExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitudAudienciaBasicoWSDTO;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del cliente del ws.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class ProcuraduriaClienteServiceImpl
        implements
            ProcuraduriaClienteService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ProcuraduriaClienteServiceImpl.class);
    @Autowired
    private ConfInstitucionDAO confInsDao;
    
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService#
     * enviarSolicitudAudiencia(mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO,
     * mx.gob.segob.nsjp.dto.caso.CasoDTO)
     */
    @Override
    public SolicitudAudienciaDTO enviarSolicitudAudiencia(SolicitudDTO input,
            CasoDTO noCaso) throws NSJPNegocioException {
        URL ep;
        if (logger.isDebugEnabled()) {
            logger.debug("input :: " + input);
            logger.debug("noCaso :: " + noCaso);
        }
        try {
            ep = new URL(confInsDao.read(Instituciones.PGJ.getValorId())
                    .getUrlInst()

            + "/SolicitarCopiaCarpetaDeInvestigacionExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "SolicitarCopiaCarpetaDeInvestigacionExporterImplService");
            SolicitarCopiaCarpetaDeInvestigacionExporterImplService ss = new SolicitarCopiaCarpetaDeInvestigacionExporterImplService(
                    ep, SERVICE_NAME);
            SolicitarCopiaCarpetaDeInvestigacionExporter port = ss
                    .getSolicitarCopiaCarpetaDeInvestigacionExporterImplPort();

            SolicitudAudienciaBasicoWSDTO toSend = new SolicitudAudienciaBasicoWSDTO();
            

            // TODO Mapear el objeto a enviar
            toSend.setFechaLimite(WsTransformer.transformFecha(input.getFechaLimite()));

            SolicitudAudienciaBasicoWSDTO resp = port
                    .solicitarCopiaCarpetaDeInvestigacion(toSend,null);
            return null;
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }
    
    @Override
	public SolicitudDTO solicitarCopiaCarpetaDeInvestigacion(
			SolicitudDTO solicitudDTO,Long catDiscriminanteOrigen)
			throws NSJPNegocioException {
		
		try {
			URL url = new URL(confInsDao.read(Instituciones.PGJ.getValorId())
			        .getUrlInst() + "/SolicitarCopiaCarpetaDeInvestigacionExporterImplService?wsdl");
			final QName SERVICE_NAME = new QName(
	                "http://impl.ws.service.nsjp.segob.gob.mx/",
	                "SolicitarCopiaCarpetaDeInvestigacionExporterImplService");
			
			SolicitarCopiaCarpetaDeInvestigacionExporterImplService ss = new SolicitarCopiaCarpetaDeInvestigacionExporterImplService(url, SERVICE_NAME);
			
			SolicitarCopiaCarpetaDeInvestigacionExporter port = ss.getSolicitarCopiaCarpetaDeInvestigacionExporterImplPort();
			
			mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitudAudienciaBasicoWSDTO toSend = new mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitudAudienciaBasicoWSDTO();
			
			logger.info("solicitudDTO.getNumeroCasoAsociado(): " + solicitudDTO.getNumeroCasoAsociado());
			logger.info("solicitudDTO.getNombreSolicitante(): " + solicitudDTO.getNombreSolicitante());
			logger.info("solicitudDTO.getInvolucradoDTO().getNombreCompleto(): " + (solicitudDTO.getInvolucradoDTO() != null &&
					solicitudDTO.getInvolucradoDTO().getNombreCompleto() != null? solicitudDTO.getInvolucradoDTO().getNombreCompleto(): "-"));
			logger.info("solicitudDTO.getFolioSolicitud(): " + solicitudDTO.getFolioSolicitud());

			
			
			if (solicitudDTO.getNumeroCasoAsociado()!=null && 
							solicitudDTO.getNombreSolicitante()!=null) {
				
				toSend.setNumeroCasoAsociado(solicitudDTO.getNumeroCasoAsociado());
				toSend.setNombreSolicitante(solicitudDTO.getNombreSolicitante());
				toSend.setNombreInvolucrado(solicitudDTO.getInvolucradoDTO().getNombreCompleto());
				toSend.setFolioSolicitud(solicitudDTO.getFolioSolicitud());
				toSend.setIdFuncionarioSolicitante(solicitudDTO.getIdFuncionarioSolicitante());
				toSend.setAreaDestino(Areas.UNIDAD_INVESTIGACION.parseLong());
				
				
			}
				
			//Se debe modificar la firma agregando la agencia
			mx.gob.segob.nsjp.ws.cliente.solicitudcarpeta.SolicitudAudienciaBasicoWSDTO respuesta = port.solicitarCopiaCarpetaDeInvestigacion(toSend,catDiscriminanteOrigen);				
			
			SolicitudDTO solDTO = new SolicitudDTO();
			solDTO.setDocumentoId(respuesta.getSolicitudId());
			
			return solDTO;
			
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		} catch (NSJPNegocioException_Exception e) {
			logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}			
	}
    
    
    @Override
    public AvisoDetencionDTO enviarAvisoDetencion(DetencionDTO input, AvisoDetencionDTO aviso,
            CasoDTO noCaso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException {
        URL ep;
        if (logger.isDebugEnabled()) {
            logger.debug("input :: " + input);
        }
        try {
            ep = new URL(
                    confInsDao.read(Instituciones.PGJ.getValorId())
                    .getUrlInst()
                    +  "/RegistrarAvisoDetencionDeAreaExternaExporterImplService?wsdl");

            final QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RegistrarAvisoDetencionDeAreaExternaExporterImplService");

            RegistrarAvisoDetencionDeAreaExternaExporterImplService ss = new RegistrarAvisoDetencionDeAreaExternaExporterImplService(
                    ep, SERVICE_NAME);
            logger.debug("Creando cliente para " + ep);
            logger.debug("getFechaRecepcionDetencion :: " + input.getFechaRecepcionDetencion());
            RegistrarAvisoDetencionDeAreaExternaExporter port = ss
                    .getRegistrarAvisoDetencionDeAreaExternaExporterImplPort();

            AvisoDetencionWSDTO toSend = new AvisoDetencionWSDTO();

            toSend.setNumeroCasoAsociado(noCaso.getNumeroGeneralCaso());
            toSend.setConfInstitucionId(this.confInsDao.consultarInsitucionActual().getConfInstitucionId());
            InvolucradoDTO imputado = input.getInvolucradoDTO();
            
            List<mx.gob.segob.nsjp.ws.cliente.avisodetencion.DelitoWSDTO> adDels = new LinkedList<mx.gob.segob.nsjp.ws.cliente.avisodetencion.DelitoWSDTO>();
            DetencionWSDTO detencion = new DetencionWSDTO();
            mx.gob.segob.nsjp.ws.cliente.avisodetencion.DelitoWSDTO del = null;
            if (imputado.getDelitosCometidos()!=null){
            for(DelitoDTO delito : imputado.getDelitosCometidos()){
            	del = new mx.gob.segob.nsjp.ws.cliente.avisodetencion.DelitoWSDTO();
        		del.setEsPrincipal(delito.getEsPrincipal());
        		del.setEsProbable(delito.getEsProbable());
        		del.setNombreDelito(delito.getCatDelitoDTO().getNombre());
        		toSend.getDelitos().add(del);
            	
            }
            }
            GregorianCalendar c = (GregorianCalendar) GregorianCalendar.getInstance();
//            detencion.setFolioElemento(imputado.getFolioElemento());
            detencion.setDetenido(imputado.getNombreCompleto());
            NombreDemograficoDTO ndDTO = imputado.getNombresDemograficoDTO().get(0);
            detencion.setNombreDetenido(ndDTO.getNombre());
            detencion.setApellidoPaternoDetenido(ndDTO.getApellidoPaterno());
            detencion.setApellidoMaternoDetenido(ndDTO.getApellidoMaterno());
            if (input.getLugarDetencionDTO()!=null) {
            detencion.setLugarDetencion(input.getLugarDetencionDTO().getDescripcion());}
            detencion.setLugarDetencionProvisional(input.getLugarDetencionProvicional());
            detencion.setMotivoDetencion(input.getMotivoDetencion());
            detencion.setObservaciones(input.getObservaciones());
            try{
            	if(input.getFechaFinDetencion() != null){
	            	c.setTime(input.getFechaFinDetencion());
	            detencion.setFechaFinDetencion(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            	}
            	if(input.getFechaInicioDetencion() != null){
	            	c.setTime(input.getFechaInicioDetencion());
	            detencion.setFechaInicioDetencion(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            	}
            	if(input.getFechaRecepcionDetencion() != null){
	            	c.setTime(input.getFechaRecepcionDetencion());
	            detencion.setFechaRecepcionDetecion(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            	}
            }catch(DatatypeConfigurationException e){
            	throw new NSJPNegocioException(CodigoError.ERROR_TRANSORMACION_FECHAS);
            }
            
            toSend.setDetencion(detencion);
            toSend.setFolioNotificacion(aviso.getFolioNotificacion());
            
            logger.debug("Invocando registrarAvisoDetencion...");
            AvisoDetencionWSDTO resp = port.registrarAvisoDetencion(toSend,idAgencia,claveAgencia,idFuncionarioSolicitante);

            AvisoDetencionDTO response = new AvisoDetencionDTO();
            response.setDocumentoId(resp.getAvisoDetencionId());

            return response;

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.avisodetencion.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }
    
    public InformePolicialHomologadoDTO enviarInformePolicialHomologado(InformePolicialHomologadoDTO iPHomologadoDTO, Long idAgencia)throws NSJPNegocioException{
    	 URL ep;
    	 logger.debug("enviarInformePolicialHomologado = " + iPHomologadoDTO);
    	 logger.debug("Id de la Agencia(Fiscalia) a la cual se enviara el IPH = " + idAgencia);
    	 
    	 
         try {
             ep = new URL(confInsDao.read(Instituciones.PGJ.getValorId()).getUrlInst()
//         	ep = new URL("http://localhost:8084/nsjp-web"	
                     + "/EnviarInformePolicialHomologadoExporterImplService?wsdl");

             final QName SERVICE_NAME = new QName(
                     "http://impl.ws.service.nsjp.segob.gob.mx/",
                     "EnviarInformePolicialHomologadoExporterImplService");
             EnviarInformePolicialHomologadoExporterImplService ss =
                     new EnviarInformePolicialHomologadoExporterImplService(ep, SERVICE_NAME);
             EnviarInformePolicialHomologadoExporter clienteEnviarIPH =
                     ss.getEnviarInformePolicialHomologadoExporterImplPort();
             // Hasta aqui finaliza la configuracion para acceder al web service
             
             // Aqui inicia el proceso de transformar los dto en wsDto
             ExpedientePrint.imprimirDatosExpediente(iPHomologadoDTO.getExpediente());
             InformePolicialHomologadoWSDTO informePolicialHomologadoWSDTO = InformePolicialHomologadoWSDTOTransformer.transformarWSDTO(iPHomologadoDTO);

             ExpedientePrintIPH.imprimirDatosExpediente(informePolicialHomologadoWSDTO.getExpediente());
             logger.debug("Enviando a traves de :: " + ep);
             Long id = clienteEnviarIPH.enviarInformePolicialHomologado(informePolicialHomologadoWSDTO,idAgencia);
             logger.debug("----------------------------------------id = " + id);
             InformePolicialHomologadoDTO iPH = new InformePolicialHomologadoDTO();
             iPH.setInformePolicialHomologadoId(id);
             
             return iPH;
         } catch (mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.NSJPNegocioException_Exception e) {
             logger.error(e.getMessage());
             throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
         } catch (MalformedURLException e) {
             logger.error(e.getMessage());
             throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
         }
    }

    @Override
    public AvisoHechoDelictivoDTO enviarAvisoHechoDelictivo(
            AvisoHechoDelictivoDTO avisoDto) throws NSJPNegocioException {
        URL ep;
        try {
            ep = new URL(confInsDao.read(Instituciones.PGJ.getValorId())
                    .getUrlInst()
                    + "/RecibirAvisoHDelictivoExporterImplService?wsdl");
            QName SERVICE_NAME = new QName(
                    "http://impl.ws.service.nsjp.segob.gob.mx/",
                    "RecibirAvisoHDelictivoExporterImplService");
            RecibirAvisoHDelictivoExporterImplService ss = new RecibirAvisoHDelictivoExporterImplService(
                    ep, SERVICE_NAME);
            RecibirAvisoHDelictivoExporter port = ss
                    .getRecibirAvisoHDelictivoExporterImplPort();
            AvisoHechoDelictivoWSDTO toSend = new AvisoHechoDelictivoWSDTO();
            
            toSend.setEsAnonimo(avisoDto.getEsAnonimo());
            toSend.setFolioNotificacion(avisoDto.getFolioNotificacion());
            toSend.setFolioDocumento(avisoDto.getFolioDocumento());
            toSend.setConsecutivoNotificacion(avisoDto.getConsecutivoNotificacion());
            toSend.setFechaAtencion(WsTransformer.transformFecha(avisoDto.getFechaAtencion()));
            if (avisoDto.getCatDelito()!=null) {
                toSend.setCatDelitoId(avisoDto.getCatDelito().getCatDelitoId()); 
            }
            toSend.setFechaAtencion(WsTransformer.transformFecha(avisoDto.getFechaAtencion()));
            if (BooleanUtils.isFalse(avisoDto.getEsAnonimo())) {
                InvolucradoWSDTO invo = new InvolucradoWSDTO();
                NombreDemograficoWSDTO nomDemo = new NombreDemograficoWSDTO();
                nomDemo.setNombre(avisoDto.getNombreImplicado());
                nomDemo.setApellidoPaterno(avisoDto.getApellidoPatImplicado());
                nomDemo.setApellidoMaterno(avisoDto.getApellidoMatImplicado());
                invo.addNombreDemografico(nomDemo);
                CalidadWSDTO cal = new CalidadWSDTO();
                cal.setValorIdCalidad(avisoDto.getCalidadImplicado().getIdCampo());
                invo.setCalidad(cal);
                logger.debug("invo :: " + invo);
                toSend.setImplicado(invo);
            }
            
            if (avisoDto.getHechoDTO()!=null) {
                HechoWSDTO heWs = new HechoWSDTO();
                heWs.setDescNarrativa(avisoDto.getHechoDTO().getDescNarrativa());
                if (avisoDto.getHechoDTO().getTiempo()!=null) {
                    TiempoWSDTO taima =new TiempoWSDTO();
                    taima.setDescripcion(avisoDto.getHechoDTO().getTiempo().getDescripcion());
                    taima.setFechaFin(WsTransformer.transformFecha(avisoDto.getHechoDTO().getTiempo().getFechaFin()));
                    taima.setFechaInicio(WsTransformer.transformFecha(avisoDto.getHechoDTO().getTiempo().getFechaInicio()));
                    taima.setTipoRegistro(avisoDto.getHechoDTO().getTiempo().getTipoRegistro().getIdCampo());
                    logger.debug("taima :: " + taima);
                    heWs.setTiempo(taima);
                }
                
                
                if (avisoDto.getHechoDTO().getLugar() != null) {
                    if (avisoDto.getHechoDTO().getLugar() instanceof DomicilioDTO) {
                        DomicilioDTO lug = (DomicilioDTO) avisoDto
                                .getHechoDTO().getLugar();
                        DomicilioWSDTO domWs = new DomicilioWSDTO();
                        domWs.setAlias(lug.getAlias());
                        if (lug.getAsentamientoDTO() != null) {
                            domWs.setAsentamientoId(lug.getAsentamientoDTO()
                                    .getAsentamientoId());
                        }
                        if (lug.getCalidadDTO() != null
                                && lug.getCalidadDTO().getValorIdCalidad() != null) {
                            CalidadWSDTO cal = new CalidadWSDTO();
                            cal.setValorIdCalidad(lug.getCalidadDTO()
                                    .getValorIdCalidad().getIdCampo());
                            domWs.setCalidad(cal);
                        }
                        domWs.setCalle(lug.getCalle());
                        if (lug.getCiudadDTO() != null) {
                            domWs.setCiudadId(lug.getCiudadDTO().getCiudadId());
                        }
                        domWs.setDescripcion(lug.getDescripcion());
                        domWs.setEdificio(lug.getEdificio());
                        if (lug.getEntidadDTO() != null) {
                            domWs.setEntidadId(lug.getEntidadDTO()
                                    .getEntidadFederativaId());
                        }
                        domWs.setEntreCalle1(lug.getEntreCalle1());
                        domWs.setEntreCalle2(lug.getEntreCalle2());
                        domWs.setFolioElemento(lug.getFolioElemento());
                        if (lug.getMunicipioDTO() != null) {
                            domWs.setMunicipioId(lug.getMunicipioDTO()
                                    .getMunicipioId());
                        }
                        domWs.setNumeroExterior(lug.getNumeroExterior());
                        domWs.setNumeroInterior(lug.getNumeroInterior());
                        domWs.setNumeroLote(lug.getNumeroLote());
                        domWs.setReferencias(lug.getReferencias());
                        if (lug.getValorCalleId() != null) {
                            domWs.setValorCalleId(lug.getValorCalleId()
                                    .getIdCampo());
                        }
                        logger.debug("domWs :: " + domWs);
                        heWs.setLugar(domWs);
                    }
                }
                logger.debug("heWs :: " + heWs);
                toSend.setHecho(heWs);
            }
            
            logger.debug("Enviando AHD a través de " + ep);
            
            port.recibirAvisoHDelictivoService(toSend);
            logger.debug("Envio [OK]");
            return avisoDto;
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        } catch (mx.gob.segob.nsjp.ws.cliente.avidohdelictivo.NSJPNegocioException_Exception e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
        }
    }
    
    
    
    
    
    
    
    
    
}

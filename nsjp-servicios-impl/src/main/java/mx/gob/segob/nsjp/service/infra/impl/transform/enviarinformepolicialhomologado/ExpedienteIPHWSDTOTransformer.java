package mx.gob.segob.nsjp.service.infra.impl.transform.enviarinformepolicialhomologado;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.InvolucradoWSDTOTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetosIPHWSDTOTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetosWSDTOTransformer;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ActividadWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.DelitoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.DocumentoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.DomicilioWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ExpedienteWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.HechoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.LugarWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ObjetoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.TiempoWSDTO;

import org.apache.log4j.Logger;

/**
 * Clase de transformación para el Expediente y sus relaciones.
 * Transforma de un Expediente WSDTO a un Expediente DTO y viceversa.
 * Para enviarinformepolicialhomologado
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class ExpedienteIPHWSDTOTransformer {

    private final static Logger logger = Logger.getLogger(ExpedienteIPHWSDTOTransformer.class);

    /**
     * Transforma un expedienteDto en un ExpedienteWSDTO.
     * @param expedienteDto
     * @return
     */
    public static ExpedienteWSDTO transformarWSDTO(ExpedienteDTO expedienteDto) {
        ExpedienteWSDTO expedienteWsdto = new ExpedienteWSDTO();;
        
        if(expedienteDto == null )
        	return expedienteWsdto;
        
        transforma(expedienteDto, expedienteWsdto);
        //Transformar Objetos
        if(expedienteDto.getObjetosDTO()!= null ){
	        List<ObjetoWSDTO> objetosWSDTO = new ArrayList<ObjetoWSDTO>();
	        for (ObjetoDTO objetoDTO : expedienteDto.getObjetosDTO()) {
	        	ObjetoWSDTO objetoWSDTO = new ObjetoWSDTO();
	        	objetoWSDTO = ObjetosIPHWSDTOTransformer.transformarObjeto(objetoDTO);
	        	objetosWSDTO.add(objetoWSDTO);
			}
	        expedienteWsdto.getObjetosDTO().addAll(objetosWSDTO);
        }

        //Hecho
        if(expedienteDto.getHechoDTO()!= null){
        	HechoWSDTO hechoWSDTO = transforma(expedienteDto.getHechoDTO());
        	expedienteWsdto.setHechoDTO(hechoWSDTO);
        }
        
        //Delito
        DelitoDTO delitoPrincipal = expedienteDto.getDelitoPrincipal();
        if (delitoPrincipal != null) {
            expedienteWsdto.setDelitoPrincipal(transforma(delitoPrincipal));
        }
        
        //Involucrado en el expediente
        if(expedienteDto.getInvolucradosDTO()!= null && !expedienteDto.getInvolucradosDTO().isEmpty()){
        	for (InvolucradoDTO involucradoDTO : expedienteDto.getInvolucradosDTO()) {
        		expedienteWsdto.getInvolucradosDTO().add(
					InvolucradoIPHWSDTOTransformer.transforma(involucradoDTO) ); 
			}
        }
        
        List<DelitoDTO> delitosDto = expedienteDto.getDelitos();
        List<DelitoWSDTO> delitosWsdto = expedienteWsdto.getDelitos();
        for (DelitoDTO delitoDTO : delitosDto) {
            delitosWsdto.add(transforma(delitoDTO));
        }
        List<DocumentoDTO> documentosDTO = expedienteDto.getDocumentosDTO();
        List<DocumentoWSDTO> documentosWsDto = expedienteWsdto.getDocumentosDTO();
        if (documentosDTO != null) {
            for (DocumentoDTO documentoDTO : documentosDTO) {
                documentosWsDto.add(transforma(documentoDTO));
            }
        }
        expedienteWsdto.setFechaApertura(WsTransformer.
                transformFecha(expedienteDto.getFechaApertura()));
        expedienteWsdto.setFechaCierre(WsTransformer.
                transformFecha(expedienteDto.getFechaCierre()));
        return expedienteWsdto;
    }

    public static DocumentoWSDTO transforma(DocumentoDTO documentoDto){
        DocumentoWSDTO documentoWSDTO = new DocumentoWSDTO();
        transforma(documentoDto, documentoWSDTO);
        
        if (documentoDto.getTipoDocumentoDTO()!= null )
        	documentoWSDTO.setTipoDocumentoDTO(documentoDto.getTipoDocumentoDTO().getIdCampo());
        
        if (documentoDto.getArchivoDigital() != null) {
        	ArchivoDigitalDTO archivoDigitalDto = documentoDto.getArchivoDigital();
        	ArchivoDigitalWSDTO archivoDigital = transforma(archivoDigitalDto);
        	archivoDigital.setContenido(archivoDigitalDto.getContenido());
            documentoWSDTO.setArchivoDigital(archivoDigital);
        }
        if (documentoDto.getFechaCreacion() != null)
        	documentoWSDTO.setFechaCreacion(WsTransformer.transformFecha(documentoDto.getFechaCreacion()));
        else
        	documentoWSDTO.setFechaCreacion(WsTransformer.transformFecha(new Date()));
        	
        if (documentoDto.getFormaDTO() != null) {
            documentoWSDTO.setFormaId(documentoDto.getFormaDTO().getFormaId());
        }
        if (documentoDto.getActividadDTO()!=null) {
            ActividadWSDTO act = new ActividadWSDTO();
            
            act.setNombre(documentoDto.getActividadDTO().getNombre());
            act.setTipoActividadId(documentoDto.getActividadDTO().getTipoActividad().getValorId());
            act.setFechaCreacion(WsTransformer.transformFecha(documentoDto.getActividadDTO().getFechaCreacion()));
            documentoWSDTO.setActividad(act);
        }
        return documentoWSDTO;
    }

    public static ArchivoDigitalWSDTO transforma(ArchivoDigitalDTO archivoDigitalDto){
        ArchivoDigitalWSDTO archivoDigitalWSDTO = new ArchivoDigitalWSDTO();
        transforma(archivoDigitalDto, archivoDigitalWSDTO);
        archivoDigitalWSDTO.setContenido(archivoDigitalDto.getContenido());
        return archivoDigitalWSDTO;
    }

    public static HechoWSDTO transforma(HechoDTO hechoDto){
        HechoWSDTO hechoWSDTO = new HechoWSDTO();
        
        hechoWSDTO.setDescNarrativa(hechoDto.getDescNarrativa());
    	hechoWSDTO.setFechaDeArribo(WsTransformer.transformFecha(hechoDto.getFechaDeArribo()));
        //Tiempo
        if(hechoDto.getTiempo()!= null){
        	TiempoWSDTO tiempoWSDTO = new TiempoWSDTO();
        	if(hechoDto.getTiempo().getTipoRegistro()!= null && hechoDto.getTiempo().getTipoRegistro().getIdCampo()!=null)
        		tiempoWSDTO.setTipoRegistro(hechoDto.getTiempo().getTipoRegistro().getIdCampo());
        	tiempoWSDTO.setDescripcion( hechoDto.getTiempo().getDescripcion());
        	tiempoWSDTO.setFechaInicio( WsTransformer.transformFecha(hechoDto.getTiempo().getFechaInicio()));
        	tiempoWSDTO.setFechaFin( WsTransformer.transformFecha(hechoDto.getTiempo().getFechaFin()));
        	hechoWSDTO.setTiempo(tiempoWSDTO);
        }
        //Lugar
        if(hechoDto.getLugar()!= null){
        	LugarWSDTO lugarWSDTO = null;
        	if(hechoDto.getLugar() instanceof DomicilioDTO){
	        	DomicilioDTO domicilioDTO = (DomicilioDTO) hechoDto.getLugar();
	        	DomicilioWSDTO domicilioWSDTO =  InvolucradoIPHWSDTOTransformer.transforma(domicilioDTO);
	        	lugarWSDTO = domicilioWSDTO;
	        }
        	if(lugarWSDTO!= null)
        		transforma(hechoDto.getLugar(), lugarWSDTO);
        	
        	hechoWSDTO.setLugar(lugarWSDTO);
        }
        
        if(hechoDto.getDomicilio()!= null){
        	DomicilioWSDTO domicilioWSDTO =  InvolucradoIPHWSDTOTransformer.transforma(hechoDto.getDomicilio());
        	hechoWSDTO.setDomicilio(domicilioWSDTO);
        }
        return hechoWSDTO;
    }
    
    public static DelitoWSDTO transforma(DelitoDTO delitoDTO) {
        DelitoWSDTO delitoWSDTO = new DelitoWSDTO();
        transforma(delitoDTO, delitoWSDTO);
        delitoWSDTO.setEsPrincipal(delitoDTO.getEsPrincipal());
        delitoWSDTO.setEsProbable(delitoDTO.getEsProbable());
        if(delitoDTO.getCatDelitoDTO()!= null && delitoDTO.getCatDelitoDTO().getClaveDelito()!= null ){
        	delitoWSDTO.setClaveDelito(delitoDTO.getCatDelitoDTO().getClaveDelito());
        }
        return delitoWSDTO;
    }

    public static ExpedienteDTO expedienteWsdto2ExpedienteDto(mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO expedienteWSDTO){
    	return transforma(expedienteWSDTO);
    }

    /**
     * Transforma un ExpedienteWSDTO a ExpedienteDTO
     * @param expedienteWSDTO
     * @return
     */
    public static ExpedienteDTO transforma(mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO expedienteWSDTO) {
        ExpedienteDTO expedienteDto = new ExpedienteDTO();
        
        if(expedienteWSDTO == null )
        	return expedienteDto;
        
        transforma(expedienteWSDTO, expedienteDto);

        //Transformar Objetos
        if(expedienteWSDTO.getObjetosDTO()!= null ){
	        List<ObjetoDTO> objetosDTO = new ArrayList<ObjetoDTO>();
	        for (mx.gob.segob.nsjp.dto.objeto.ObjetoWSDTO objetoWSDTO : expedienteWSDTO.getObjetosDTO()) {
	        	ObjetoDTO objetoDTO = new ObjetoDTO();
	        	objetoDTO = ObjetosWSDTOTransformer.transformarObjeto(objetoWSDTO);
	        	objetosDTO.add(objetoDTO);
			}
	        expedienteDto.setObjetosDTO(objetosDTO);
        }
        //Hecho
        if(expedienteWSDTO.getHechoDTO()!= null){
        	HechoDTO hechoWSDTO = transforma(expedienteWSDTO.getHechoDTO());
        	expedienteDto.setHechoDTO(hechoWSDTO);
        }

        //Delito
        mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO delitoPrincipal = expedienteWSDTO.getDelitoPrincipal();
        if (delitoPrincipal != null) {
            expedienteDto.setDelitoPrincipal(transforma(delitoPrincipal));
        }
        
        //Involucrado 
        if(expedienteWSDTO.getInvolucradosDTO()!= null && !expedienteWSDTO.getInvolucradosDTO().isEmpty()){
        	List<InvolucradoDTO> involucradosDTO = new ArrayList<InvolucradoDTO>();
        	for (InvolucradoWSDTO involucradoWSDTO : expedienteWSDTO.getInvolucradosDTO()) {
				involucradosDTO.add(InvolucradoWSDTOTransformer.transforma(involucradoWSDTO));
			}
        	expedienteDto.setInvolucradosDTO(involucradosDTO);
        }
        
        if ( expedienteWSDTO.getDelitos()!= null && !expedienteWSDTO.getDelitos().isEmpty()){
	        List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
	        for (mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO delitoWSDTO : expedienteWSDTO.getDelitos()) {
	            delitosDTO.add(transforma(delitoWSDTO));
	        }
	        expedienteDto.setDelitos(delitosDTO);
        }
        if ( expedienteWSDTO.getDocumentosDTO()!= null && !expedienteWSDTO.getDocumentosDTO().isEmpty()){
	        List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
            for (mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO documentoWSDTO : expedienteWSDTO.getDocumentosDTO()) {
            	documentosDTO.add(transforma(documentoWSDTO));
            }
            expedienteDto.setDocumentosDTO(documentosDTO);
        }
        expedienteDto.setFechaApertura ( expedienteWSDTO.getFechaApertura());
        expedienteDto.setFechaCierre(expedienteWSDTO.getFechaCierre());
       
        return expedienteDto;
    }

    public static DocumentoDTO transforma(mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO documentoWSDto){
        DocumentoDTO documentoDTO = new DocumentoDTO();
        transforma(documentoWSDto, documentoDTO);
        
        if (documentoWSDto.getTipoDocumentoDTO()!= null)
        	documentoDTO.setTipoDocumentoDTO(new ValorDTO(documentoWSDto.getTipoDocumentoDTO()));        
        
        if (documentoWSDto.getArchivoDigital() != null) {
        	mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO archivoDigitalWSDto = documentoWSDto.getArchivoDigital();
        	ArchivoDigitalDTO archivoDigital = transforma(archivoDigitalWSDto);
        	archivoDigital.setContenido(archivoDigitalWSDto.getContenido());
            documentoDTO.setArchivoDigital(archivoDigital);
        }
        if(documentoWSDto.getFechaCreacion()!= null)
        	documentoDTO.setFechaCreacion(documentoWSDto.getFechaCreacion());
        else
        	documentoDTO.setFechaCreacion(new Date());
        
        if (documentoWSDto.getFormaId()!= null ){
        	documentoDTO.setFormaDTO(new FormaDTO(documentoWSDto.getFormaId()));
        }
        if (documentoWSDto.getActividad()!=null) {
            ActividadDTO act = new ActividadDTO();
            act.setFechaCreacion(documentoWSDto.getActividad().getFechaCreacion());
            act.setNombre(documentoWSDto.getActividad().getNombre());
            act.setTipoActividad(Actividades.getByValor(documentoWSDto.getActividad().getTipoActividadId()));
            documentoDTO.setActividadDTO(act);
        }
        return documentoDTO;
    }

    public static ArchivoDigitalDTO transforma(mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO archivoDigitalWSDTO){
        ArchivoDigitalDTO archivoDigitalDTO = new ArchivoDigitalDTO();
        transforma(archivoDigitalWSDTO, archivoDigitalDTO);
        archivoDigitalDTO.setContenido(archivoDigitalWSDTO.getContenido());
        return archivoDigitalDTO;
    }
    
    public static HechoDTO transforma(mx.gob.segob.nsjp.dto.hecho.HechoWSDTO hechoWSDto){
        HechoDTO hechoDTO = new HechoDTO();
        
        hechoDTO.setDescNarrativa(hechoWSDto.getDescNarrativa());
        //Tiempo
        if(hechoWSDto.getTiempo()!= null){
        	TiempoDTO tiempoDTO = new TiempoDTO();
        	if(hechoWSDto.getTiempo().getTipoRegistro()!= null && hechoWSDto.getTiempo().getTipoRegistro()!=null)
        		tiempoDTO.setTipoRegistro(new ValorDTO(hechoWSDto.getTiempo().getTipoRegistro()));
        	tiempoDTO.setDescripcion( hechoWSDto.getTiempo().getDescripcion());
        	tiempoDTO.setFechaInicio( hechoWSDto.getTiempo().getFechaInicio());
        	tiempoDTO.setFechaFin( hechoWSDto.getTiempo().getFechaFin());
        	hechoDTO.setTiempo(tiempoDTO);
        }
        
        //Lugar
        if(hechoWSDto.getLugar()!= null){
        	LugarDTO lugarDTO = null;
        	if(hechoWSDto.getLugar() instanceof mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO){
				mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO domicilioWSDTO = (mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO) hechoWSDto
						.getLugar();
				DomicilioDTO domicilioDTO = InvolucradoIPHWSDTOTransformer.transforma(domicilioWSDTO);
				lugarDTO = domicilioDTO;
        	}
        	if(lugarDTO!= null)
        		transforma(hechoWSDto.getLugar(), lugarDTO);
        	hechoDTO.setLugar(lugarDTO);
        }
        
        if(hechoWSDto.getDomicilio()!= null){
        	DomicilioDTO domicilioDTO =  InvolucradoIPHWSDTOTransformer.transforma(hechoWSDto.getDomicilio());
        	hechoDTO.setDomicilio(domicilioDTO);
        }
        hechoDTO.setFechaDeArribo(hechoWSDto.getFechaDeArribo());
        return hechoDTO;
    }
    
    public static DelitoDTO transforma(mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO delitoWSDTO){
        DelitoDTO delitoDTO = new DelitoDTO();
        transforma(delitoWSDTO, delitoDTO);
        delitoDTO.setEsPrincipal(delitoWSDTO.isEsPrincipal());
        delitoDTO.setEsProbable(delitoWSDTO.isEsProbable());
        if(delitoWSDTO.getClaveDelito()!= null && delitoWSDTO.getClaveDelito().matches("(\\d)+") ){
        	delitoDTO.setCatDelitoDTO(new CatDelitoDTO(Long.parseLong( delitoWSDTO.getClaveDelito())));
        }
        return delitoDTO;
    }

    /**
     * Transforma las propiedades de un objeto a otro.
     * Este metodo solo sirva para transformar objetos ExpedienteWSDTO <->
     * ExpedienteDTO y InvolucradoWSDTO <-> InvolucradoDTO.
     * @param origen El objeto del que se tomaran sus propiedades.
     * @param destino El objeto a que se le hara set de las propiedades del
     * objeto origen.
     * Si el get de origen es un objeto del tipo ValorDTO se hara un set
     * en destino con el id del ValorDto.
     * Si el set de destino es un ValorDto se creara un nuevo ValorDto con el
     * valor del get de "origen".
     */
    public static void transforma(Object origen, Object destino) {
        if (origen == null || destino == null) {
            return;
        }
        // Buscamos los campos del objeto actualizacion.
        Method[] metodos = origen.getClass().getMethods();
        for (Method metodo : metodos) {
            metodo.setAccessible(true);
            try {
                if (!metodo.getName().startsWith("getClass")
                        && metodo.getName().startsWith("get")
                        && metodo.getParameterTypes().length == 0) {
                    // Obtenemos el valor del campo
                    Object objetoObtenido = metodo.invoke(origen);
                    // Si el valor del campo es distinto de null
                    if (objetoObtenido != null) {
                        String nombreCampo = metodo.getName().substring(3);
                        /**
                         * Validamos si el tipo del objeto obtenido es uno
                         * de los basicos que podemos procesar.
                         */
                        if (esTipoBasico(objetoObtenido)) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("invocando setter del campo = " + nombreCampo);
                            }
                            Method setter = destino.getClass().getMethod("set"
                                    + nombreCampo, objetoObtenido.getClass());
                            if (logger.isDebugEnabled()) {
                                logger.debug("setter obtenido = " + setter);
                            }
                            /**
                             * Validamos si el tipo que recibe el setter
                             * es un ValorDto, en cuyo caso intentamos
                             * crear un nuevo ValorDto con el objeto obtenido
                             * al que se le intenta hacer un cast a long.
                             */
                            if (setter.getParameterTypes().length == 1
                                    && setter.getParameterTypes()[0].getClass().equals(ValorDTO.class)) {
                                if (logger.isDebugEnabled()) {
                                    logger.debug("setteando un VALORDTO = " + setter);
                                }
                                try {
                                    ValorDTO valorDTO = new ValorDTO((Long) objetoObtenido);
                                } catch (ClassCastException cae) {
                                    logger.error("No se puede hacer cast de "
                                            + objetoObtenido + " a long", cae);
                                }
                            } else {
                                /**
                                 * Si no es un ValorDto intentamos hacer un
                                 * set con el valor obtenido.
                                 */
                                setter.invoke(destino, objetoObtenido);
                                if (logger.isDebugEnabled()) {
                                    logger.debug("setter ejecutado = " + "");
                                }
                            }
                        }
                        if (objetoObtenido.getClass().equals(ValorDTO.class)) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("invocando setter del campo ValorDTO = " + nombreCampo);
                            }
                            Method setter = destino.getClass().getMethod("set"
                                    + nombreCampo, Long.class);
                            if (logger.isDebugEnabled()) {
                                logger.debug("setter obtenido = " + setter);
                            }
                            ValorDTO valorDto = (ValorDTO) objetoObtenido;
                            setter.invoke(destino, valorDto.getIdCampo());
                            if (logger.isDebugEnabled()) {
                                logger.debug("setter del valorDto ejecutado correctamente = ");
                            }
                        }
                    }
                }
            } catch (InvocationTargetException ex) {
                logger.debug(ex);
            } catch (NoSuchMethodException ex) {
//                logger.debug(ex);
            } catch (SecurityException ex) {
                logger.debug(ex);
            } catch (IllegalArgumentException ex) {
                logger.debug(ex);
            } catch (IllegalAccessException ex) {
                logger.debug(ex);
            }
        }
    }

    /**
     * Validad los tipos basicos que se procesaran del objeto origen hacia
     * destino.
     * @param objetoEnActualizacion
     * @return
     */
    private static boolean esTipoBasico(Object objetoEnActualizacion) {
        return objetoEnActualizacion.getClass().equals(String.class)
                || objetoEnActualizacion.getClass().equals(Integer.class)
                || objetoEnActualizacion.getClass().equals(Long.class)
                || objetoEnActualizacion.getClass().equals(Boolean.class)
                || objetoEnActualizacion.getClass().equals(Double.class)
                || objetoEnActualizacion.getClass().equals(Short.class)
                || objetoEnActualizacion.getClass().equals(Date.class);
    }
}

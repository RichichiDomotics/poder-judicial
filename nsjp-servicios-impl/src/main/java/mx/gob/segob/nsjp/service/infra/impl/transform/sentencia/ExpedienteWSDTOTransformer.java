package mx.gob.segob.nsjp.service.infra.impl.transform.sentencia;

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
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.sentencia.ObjetosWSDTOTransformer;
import mx.gob.segob.nsjp.ws.cliente.sentencia.ActividadWSDTO;
import mx.gob.segob.nsjp.ws.cliente.sentencia.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.sentencia.DelitoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.sentencia.DocumentoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.sentencia.ExpedienteWSDTO;
import mx.gob.segob.nsjp.ws.cliente.sentencia.ObjetoWSDTO;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author rgama
 */
public class ExpedienteWSDTOTransformer {

    private final static Logger logger = Logger.getLogger(ExpedienteWSDTOTransformer.class);

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
	        	objetoWSDTO = mx.gob.segob.nsjp.service.infra.impl.transform.sentencia.ObjetosWSDTOTransformer.transformarObjeto(objetoDTO);
	        	objetosWSDTO.add(objetoWSDTO);
			}
	        expedienteWsdto.getObjetosDTO().addAll(objetosWSDTO);
        }
        //Hechos
        //TODO GBP si se requiere tranformar los hechos para carpeta de Investigacion.Ver ExpedienteIPHWSDTOTransformer. 
        
        //Delito
        DelitoDTO delitoPrincipal = expedienteDto.getDelitoPrincipal();
        if (delitoPrincipal != null) {
            expedienteWsdto.setDelitoPrincipal(transforma(delitoPrincipal));
        }
        
        //Involucrado en el expediente
        if(expedienteDto.getInvolucradosDTO()!= null && !expedienteDto.getInvolucradosDTO().isEmpty()){
        	for (InvolucradoDTO involucradoDTO : expedienteDto.getInvolucradosDTO()) {
        		expedienteWsdto.getInvolucradosDTO().add(
        				InvolucradoWSDTOTransformer.transforma2Send(involucradoDTO)
					); 
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
                if (documentoDTO!=null){
                documentosWsDto.add(transforma(documentoDTO));
            }}
        }
        expedienteWsdto.setFechaApertura(WsTransformer.
                transformFecha(expedienteDto.getFechaApertura()));
        expedienteWsdto.setFechaCierre(WsTransformer.
                transformFecha(expedienteDto.getFechaCierre()));
        return expedienteWsdto;
    }

    public static DocumentoWSDTO transforma(DocumentoDTO documentoDto){
        if (documentoDto==null){
            return null;
        }
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
        if (documentoWSDto==null) {
            return null;
        }
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
    public static DelitoDTO transforma(mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO delitoWSDTO){
        DelitoDTO delitoDTO = new DelitoDTO();
        transforma(delitoWSDTO, delitoDTO);
        delitoDTO.setEsPrincipal(delitoWSDTO.isEsPrincipal());
        delitoDTO.setEsProbable(delitoWSDTO.isEsProbable());
        if(delitoWSDTO.getClaveDelito()!= null && delitoWSDTO.getClaveDelito().matches("(\\d)+") ){
        	delitoDTO.setCatDelitoDTO(new CatDelitoDTO(Long.parseLong( delitoWSDTO.getClaveDelito())));
        }
//        if (delitoDTO.getCatDelitoDTO()==null) {
//            delitoDTO.setCatDelitoDTO(new CatDelitoDTO(delitoWSDTO.getIdCatDelito()));
//        }
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
                         * Validamos si el tipo del objeto obtenido es uno de
                         * los basicos que podemos procesar.
                         */
                        if (esTipoBasico(objetoObtenido)) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("invocando setter del campo = "
                                        + nombreCampo);
                            }
                            Method setter = MethodUtils.getAccessibleMethod(
                                    destino.getClass(), "set"+StringUtils.capitalize(nombreCampo),
                                    objetoObtenido.getClass());
                            if (logger.isDebugEnabled()) {
                                logger.debug("setter obtenido = " + setter);
                            }
                            /**
                             * Validamos si el tipo que recibe el setter es un
                             * ValorDto, en cuyo caso intentamos crear un nuevo
                             * ValorDto con el objeto obtenido al que se le
                             * intenta hacer un cast a long.
                             */
                            if (setter == null) {
                                setter = MethodUtils.getAccessibleMethod(
                                        destino.getClass(), "set"+StringUtils.capitalize(nombreCampo),
                                        ValorDTO.class);
                                if (logger.isDebugEnabled()) {
                                    logger.debug("setteando un VALORDTO = "
                                            + setter);
                                }
                                if (setter != null) {
                                    try {
                                        ValorDTO valorDTO = new ValorDTO(
                                                (Long) objetoObtenido);
                                        setter.invoke(destino, valorDTO);
                                    } catch (ClassCastException cae) {
                                        logger.error(
                                                "No se puede hacer cast de "
                                                        + objetoObtenido
                                                        + " a long", cae);
                                    }
                                }
                            } else {
                                /**
                                 * Si no es un ValorDto intentamos hacer un set
                                 * con el valor obtenido.
                                 */
                                setter.invoke(destino, objetoObtenido);
                                if (logger.isDebugEnabled()) {
                                    logger.debug("setter ejecutado = " + "");
                                }
                            }
                        }
                        if (objetoObtenido.getClass().equals(ValorDTO.class)) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("invocando setter del campo ValorDTO = "
                                        + nombreCampo);
                            }
                            Method setter = destino.getClass().getMethod(
                                    "set" + nombreCampo, Long.class);
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
                logger.error(ex.getMessage(), ex);
            } catch (NoSuchMethodException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (SecurityException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (IllegalArgumentException ex) {
                logger.error(ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
                logger.error(ex.getMessage(), ex);
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

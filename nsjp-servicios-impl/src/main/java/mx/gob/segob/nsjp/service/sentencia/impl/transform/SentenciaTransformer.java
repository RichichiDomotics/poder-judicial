/**
 * Nombre del Programa : ProgramaTransformer.java
 * Autor                            : AntonioBV
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.service.sentencia.impl.transform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.RemisionDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaWSDTO;
import mx.gob.segob.nsjp.model.ActoBuenaConducta;
import mx.gob.segob.nsjp.model.AsignacionCentroDetencion;
import mx.gob.segob.nsjp.model.AsignacionMedidaAlterna;
import mx.gob.segob.nsjp.model.AsignacionPrograma;
import mx.gob.segob.nsjp.model.Remision;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.sentencia.ExpedienteWSDTOTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.sentencia.InvolucradoWSDTOTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.programa.impl.transform.AsignacionProgramaTransformer;
import mx.gob.segob.nsjp.service.reinsercion.impl.transform.InventarioPertenenciaTransformer;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author AntonioBV
 * 
 */
public class SentenciaTransformer {

	private final static Logger LOG = Logger.getLogger(SentenciaTransformer.class);
	
	public static SentenciaDTO transformar(Sentencia sentencia) {
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null) {
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			sentenciaDTO.setBcumplida(sentencia.getBcumplida());
			sentenciaDTO.setBlesionado(sentencia.getBlesionado());
			sentenciaDTO.setCnumeroCausa(sentencia.getCnumeroCausa());
			sentenciaDTO.setCnus(sentencia.getCnus());
			sentenciaDTO.setDfechaInicioPena(sentencia.getDfechaInicioPena());
			sentenciaDTO.setDfechaFinPena(sentencia.getDfechaFinPena());
			if(sentencia.getInvolucrado() != null) {
				sentenciaDTO.setInvolucradoDTO(InvolucradoTransformer
					.transformarInvolucrado(sentencia.getInvolucrado()));
			}
			sentenciaDTO.setIpuntosPorAcumular(sentencia
					.getIpuntosPorAcumular());
			if(sentencia.getNumeroExpediente() != null) {
				sentenciaDTO.setNumeroExpedienteDTO(ExpedienteTransformer
					.transformarExpediente(sentencia.getNumeroExpediente()));
			}
			sentenciaDTO.setValorDTO(new ValorDTO(sentencia.getValor()
					.getValorId(), sentencia.getValor().getValor()));

			List<AsignacionCentroDetencionDTO> lstAsignacionCentroDetencionDTO = new ArrayList<AsignacionCentroDetencionDTO>();
			if (sentencia.getAsignacionCentroDetencions() != null) {
				for (AsignacionCentroDetencion tmpEntity : sentencia
						.getAsignacionCentroDetencions()) {
					AsignacionCentroDetencionDTO tmpDTO = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpEntity);
					lstAsignacionCentroDetencionDTO.add(tmpDTO);
				}
			}

			List<AsignacionMedidaAlternaDTO> lstAsignacionMedidaAlternaDTO = new ArrayList<AsignacionMedidaAlternaDTO>();
			if (sentencia.getAsignacionMedidaAlternas() != null) {
				for (AsignacionMedidaAlterna tmpEntity : sentencia
						.getAsignacionMedidaAlternas()) {
					AsignacionMedidaAlternaDTO tmpDTO = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpEntity);
					lstAsignacionMedidaAlternaDTO.add(tmpDTO);
				}
			}

			List<AsignacionProgramaDTO> lstAsignacionProgramaDTO = new ArrayList<AsignacionProgramaDTO>();
			for (AsignacionPrograma tmpEntity : sentencia
					.getAsignacionProgramas()) {
				AsignacionProgramaDTO tmpDTO = AsignacionProgramaTransformer
						.transformarSinSentencia(tmpEntity);
				lstAsignacionProgramaDTO.add(tmpDTO);
			}

			List<RemisionDTO> lstRemisionDTO = new ArrayList<RemisionDTO>();
			if (sentencia.getRemisions() != null) {
				for (Remision tmpEntity : sentencia.getRemisions()) {
					RemisionDTO tmpDTO = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpEntity);
					lstRemisionDTO.add(tmpDTO);
				}
			}
			
			List<ActoBuenaConductaDTO> lstActoBuenaConductaDTO = new ArrayList<ActoBuenaConductaDTO>();
			if (sentencia.getActoBuenaConductas() != null) {
				for (ActoBuenaConducta tmpEntity : sentencia.getActoBuenaConductas()) {
					ActoBuenaConductaDTO tmpDTO = AsignacionProgramaTransformer.transformarSinSentencia(tmpEntity);
					lstActoBuenaConductaDTO.add(tmpDTO);
				}
			}
			
			if (sentencia.getInventarioPertenencia() != null) {
					sentenciaDTO.setInventarioPertenenciaDTO(
							InventarioPertenenciaTransformer.transformarSinSentencia(
									sentencia.getInventarioPertenencia(), 
									InventarioPertenenciaTransformer.CON_PERTENENCIAS));
			}

			sentenciaDTO.setAsignacionCentroDetencions(lstAsignacionCentroDetencionDTO);
			sentenciaDTO.setAsignacionMedidaAlternas(lstAsignacionMedidaAlternaDTO);
			sentenciaDTO.setAsignacionProgramas(lstAsignacionProgramaDTO);
			sentenciaDTO.setRemisions(lstRemisionDTO);
			sentenciaDTO.setActoBuenaConductas(lstActoBuenaConductaDTO);
		}
		return sentenciaDTO;
	}

	public static Sentencia transformar(SentenciaDTO sentenciaDTO) {
		Sentencia sentencia = null;
		if (sentenciaDTO != null) {
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			sentencia.setBcumplida(sentenciaDTO.getBcumplida());
			sentencia.setBlesionado(sentenciaDTO.getBlesionado());
			sentencia.setCnumeroCausa(sentenciaDTO.getCnumeroCausa());
			sentencia.setCnus(sentenciaDTO.getCnus());
			sentencia.setDfechaInicioPena(sentenciaDTO.getDfechaInicioPena());
			sentencia.setDfechaFinPena(sentenciaDTO.getDfechaFinPena());
			if(sentenciaDTO.getInvolucradoDTO() != null) {
				sentencia.setInvolucrado(InvolucradoTransformer
					.transformarInvolucrado(sentenciaDTO.getInvolucradoDTO()));
			}
			sentencia.setIpuntosPorAcumular(sentenciaDTO
					.getIpuntosPorAcumular());
			if(sentenciaDTO.getNumeroExpedienteDTO() != null) {
				sentencia.setNumeroExpediente(ExpedienteTransformer
					.obtenerDeExpedienteDTO(sentenciaDTO
							.getNumeroExpedienteDTO()));
			}
			if (sentenciaDTO.getValorDTO() != null) {
				sentencia.setValor(new Valor(sentenciaDTO.getValorDTO()
						.getIdCampo()));
			} else {
				sentencia.setValor(null);
			}

			List<AsignacionCentroDetencion> lstAsignacionCentroDetencion = new ArrayList<AsignacionCentroDetencion>();
			if (sentenciaDTO.getAsignacionCentroDetencions() != null) {
				for (AsignacionCentroDetencionDTO tmpDTO : sentenciaDTO
						.getAsignacionCentroDetencions()) {
					AsignacionCentroDetencion tmpEntity = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpDTO);
					lstAsignacionCentroDetencion.add(tmpEntity);
				}
			}
			Set<AsignacionCentroDetencion> asignacionCentroDetencion = new HashSet<AsignacionCentroDetencion>(
					lstAsignacionCentroDetencion);

			List<AsignacionMedidaAlterna> lstAsignacionMedidaAlterna = new ArrayList<AsignacionMedidaAlterna>();
			if (sentenciaDTO.getAsignacionMedidaAlternas() != null) {
				for (AsignacionMedidaAlternaDTO tmpDTO : sentenciaDTO
						.getAsignacionMedidaAlternas()) {
					AsignacionMedidaAlterna tmpEntity = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpDTO);
					lstAsignacionMedidaAlterna.add(tmpEntity);
				}
			}
			Set<AsignacionMedidaAlterna> asignacionMedidaAlterna = new HashSet<AsignacionMedidaAlterna>(
					lstAsignacionMedidaAlterna);

			List<AsignacionPrograma> lstAsignacionPrograma = new ArrayList<AsignacionPrograma>();
			if (sentenciaDTO.getAsignacionProgramas() != null) {
				for (AsignacionProgramaDTO tmpDTO : sentenciaDTO
						.getAsignacionProgramas()) {
					AsignacionPrograma tmpEntity = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpDTO);
					lstAsignacionPrograma.add(tmpEntity);
				}
			}
			Set<AsignacionPrograma> asignacionProgramas = new HashSet<AsignacionPrograma>(
					lstAsignacionPrograma);

			List<Remision> lstRemision = new ArrayList<Remision>();
			if (sentenciaDTO.getRemisions() != null) {
				for (RemisionDTO tmpDTO : sentenciaDTO.getRemisions()) {
					Remision tmpEntity = AsignacionProgramaTransformer
							.transformarSinSentencia(tmpDTO);
					lstRemision.add(tmpEntity);
				}
			}
			Set<Remision> remisions = new HashSet<Remision>(lstRemision);
			
			List<ActoBuenaConducta> lstActoBuenaConduca = new ArrayList<ActoBuenaConducta>();
			if (sentenciaDTO.getActoBuenaConductas() != null) {
				for (ActoBuenaConductaDTO tmpDTO : sentenciaDTO.getActoBuenaConductas()) {
					ActoBuenaConducta tmpEntity = AsignacionProgramaTransformer.transformarSinSentencia(tmpDTO);
					lstActoBuenaConduca.add(tmpEntity);
				}
			}
			Set<ActoBuenaConducta> actoBuenaConductas = new HashSet<ActoBuenaConducta>(lstActoBuenaConduca);
			
			if (sentenciaDTO.getInventarioPertenenciaDTO() != null){
				sentencia.setInventarioPertenencia(
						InventarioPertenenciaTransformer.transformar(
								sentenciaDTO.getInventarioPertenenciaDTO(), 
								InventarioPertenenciaTransformer.SIN_PERTENENCIAS));
			}

			sentencia.setAsignacionCentroDetencions(asignacionCentroDetencion);
			sentencia.setAsignacionMedidaAlternas(asignacionMedidaAlterna);
			sentencia.setAsignacionProgramas(asignacionProgramas);
			sentencia.setRemisions(remisions);
			sentencia.setActoBuenaConductas(actoBuenaConductas);
		}
		return sentencia;
	}

	public static Sentencia transformarLocalWSDTO2Entity(SentenciaWSDTO sentenciaWSDTO){
		SentenciaDTO sentenciaDTO = new SentenciaDTO();
		
		transforma(sentenciaWSDTO, sentenciaDTO);
		
		sentenciaDTO.setValorDTO(new ValorDTO(sentenciaWSDTO.getTipoDeSentenciaId()));
		
		sentenciaDTO.setInvolucradoDTO(InvolucradoWSDTOTransformer.involucradoWsdto2InvolucradoDto(sentenciaWSDTO.getInvolucradoDTO()));

		sentenciaDTO.setNumeroExpedienteDTO(ExpedienteWSDTOTransformer.expedienteWsdto2ExpedienteDto(sentenciaWSDTO.getNumeroExpedienteDTO()));
		
		return transformar(sentenciaDTO);
	}
	
	public static SentenciaWSDTO transformarDTO2LocalWSDTO(SentenciaDTO sentenciaDTO){
		SentenciaWSDTO sentenciaWSDTO = new SentenciaWSDTO();
		
		transforma(sentenciaDTO, sentenciaWSDTO);
		
		if(sentenciaDTO.getValorDTO() != null) {
			sentenciaWSDTO.setTipoDeSentenciaId(sentenciaDTO.getValorDTO().getIdCampo());
		}
		return sentenciaWSDTO;
	}

	public static SentenciaDTO transformarClienteWSDTO2DTO(mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO sentenciaWSDTO){
		SentenciaDTO sentenciaDTO = new SentenciaDTO();
		
		transforma(sentenciaWSDTO, sentenciaDTO);
		sentenciaDTO.setValorDTO(new ValorDTO(sentenciaWSDTO.getTipoDeSentenciaId()));		
		return sentenciaDTO;
	}

	public static Sentencia transformarClienteWSDTO2Entity(mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO sentenciaWSDTO){
		SentenciaDTO sentenciaDTO = new SentenciaDTO();
		
		transforma(sentenciaWSDTO, sentenciaDTO);
		
		sentenciaDTO.setValorDTO(new ValorDTO(sentenciaWSDTO.getTipoDeSentenciaId()));
		
		return transformar(sentenciaDTO);
	}

	public static mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO transformarDTO2ClienteWSDTO(SentenciaDTO sentenciaDTO){
		
		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		
		mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO sentenciaWSDTO = new mx.gob.segob.nsjp.ws.cliente.sentencia.SentenciaWSDTO();
		
		transforma(sentenciaDTO, sentenciaWSDTO);
		
		if(sentenciaDTO.getValorDTO() != null) {
			sentenciaWSDTO.setTipoDeSentenciaId(sentenciaDTO.getValorDTO().getIdCampo());
		}
		try {
			if(sentenciaDTO.getDfechaInicioPena() != null){			      
		    	gcal.setTime(sentenciaDTO.getDfechaInicioPena());
				XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
				        .newXMLGregorianCalendar(gcal);	
				sentenciaWSDTO.setDfechaInicioPena(xgcal);
			}		
		
			if(sentenciaDTO.getDfechaFinPena() != null){		      
				gcal.setTime(sentenciaDTO.getDfechaFinPena());
				XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
					        .newXMLGregorianCalendar(gcal);
				sentenciaWSDTO.setDfechaFinPena(xgcal);
			}
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(sentenciaDTO.getInvolucradoDTO() != null) {
			sentenciaWSDTO.setInvolucradoDTO(InvolucradoWSDTOTransformer.transforma2Send(sentenciaDTO.getInvolucradoDTO()));
		}
		
		if(sentenciaDTO.getNumeroExpedienteDTO() != null) {
			sentenciaWSDTO.setNumeroExpedienteDTO(ExpedienteWSDTOTransformer.transformarWSDTO(sentenciaDTO.getNumeroExpedienteDTO()));
		}
		
		return sentenciaWSDTO;
	}

    public static Sentencia quitarIDs(Sentencia sentencia){
    	sentencia.setSentenciaId(null);
    	if(sentencia.getInvolucrado() != null) {
    		sentencia.getInvolucrado().setElementoId(null);
    	}
    	
    	if(sentencia.getNumeroExpediente() != null) {
    		sentencia.getNumeroExpediente().setNumeroExpedienteId(null);
    	}
    	return sentencia; 
    }	
	
	
    /**
     * Transforma las propiedades de un objeto a otro.
     * Este metodo solo sirva para transformar objetos SentenciaWSDTO <-> SentenciaDTO
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
                        && ( 
                        		metodo.getName().startsWith("get") ||
                        		metodo.getName().startsWith("is")
                        	)
                        && metodo.getParameterTypes().length == 0) {
                    // Obtenemos el valor del campo
                    Object objetoObtenido = metodo.invoke(origen);
                    // Si el valor del campo es distinto de null
                    if (objetoObtenido != null) {
                        String nombreCampo = "";
                        
                        if(metodo.getName().startsWith("get")){
                        	nombreCampo = metodo.getName().substring(3);
                        } else if (metodo.getName().startsWith("is")) {
                        	nombreCampo = metodo.getName().substring(2);
                        }
                        	
                        /**
                         * Validamos si el tipo del objeto obtenido es uno de
                         * los basicos que podemos procesar.
                         */
                        if (esTipoBasico(objetoObtenido)) {
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("invocando setter del campo = "
                                        + nombreCampo);
                            }
                            Method setter = MethodUtils.getAccessibleMethod(
                                    destino.getClass(), "set"+StringUtils.capitalize(nombreCampo),
                                    objetoObtenido.getClass());
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("setter obtenido = " + setter);
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
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("setteando un VALORDTO = "
                                            + setter);
                                }
                                if (setter != null) {
                                    try {
                                        ValorDTO valorDTO = new ValorDTO(
                                                (Long) objetoObtenido);
                                        setter.invoke(destino, valorDTO);
                                    } catch (ClassCastException cae) {
                                        LOG.error(
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
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("setter ejecutado = " + "");
                                }
                            }
                        }
                        if (objetoObtenido.getClass().equals(ValorDTO.class)) {
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("invocando setter del campo ValorDTO = "
                                        + nombreCampo);
                            }
                            Method setter = destino.getClass().getMethod(
                                    "set" + nombreCampo, Long.class);
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("setter obtenido = " + setter);
                            }
                            ValorDTO valorDto = (ValorDTO) objetoObtenido;
                            setter.invoke(destino, valorDto.getIdCampo());
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("setter del valorDto ejecutado correctamente = ");
                            }
                        }
                    }
                }
            } catch (InvocationTargetException ex) {
                LOG.error(ex.getMessage(), ex);
            } catch (NoSuchMethodException ex) {
                LOG.error(ex.getMessage(), ex);
            } catch (SecurityException ex) {
                LOG.error(ex.getMessage(), ex);
            } catch (IllegalArgumentException ex) {
                LOG.error(ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
                LOG.error(ex.getMessage(), ex);
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

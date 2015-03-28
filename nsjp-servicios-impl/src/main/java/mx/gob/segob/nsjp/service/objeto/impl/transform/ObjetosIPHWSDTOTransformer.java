/**
 * Nombre del Programa : ObjetosWSDTOTransformer.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/08/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de transformación para los Objetos asociados al expediente.
 * Transforma de un Objeto WSDTO a un Objeto DTO y viceversa.
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import javax.xml.datatype.XMLGregorianCalendar;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.InmuebleDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.service.eslabon.impl.EslabonTransformer;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarinformepolicialhomologado.ExpedienteIPHWSDTOTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.IngresarNumerarioServiceImpl;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.AeronaveWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.AnimalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ArmaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.CadenaDeCustodiaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.CalidadWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.DocumentoOficialWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EmbarcacionWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EquipoComputoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EslabonWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.EvidenciaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ExplosivoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.InmuebleWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.JoyaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.NumerarioWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ObjetoWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.ObraArteWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.SustanciaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.TelefoniaWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.VegetalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.VehiculoWSDTO;

import org.apache.log4j.Logger;

/**
 * Clase de transformación para los Objetos asociados al expediente.
 * Transforma de un Objeto WSDTO a un Objeto DTO y viceversa.
 * Para enviarinformepolicialhomologado
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class ObjetosIPHWSDTOTransformer {

	private final static Logger logger = Logger.getLogger(IngresarNumerarioServiceImpl.class);
	
    public static ObjetoWSDTO transformarObjeto(ObjetoDTO src) {
        ObjetoWSDTO destino = new ObjetoWSDTO();
        if (src != null) {
        	if (src instanceof VehiculoDTO) {
        		destino = new VehiculoWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((VehiculoDTO)src, destino);
            } else if (src instanceof EquipoComputoDTO) {
            	destino = new EquipoComputoWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((EquipoComputoDTO)src, destino);
            } else if (src instanceof TelefoniaDTO) {
            	destino = new TelefoniaWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((TelefoniaDTO)src, destino);
            } else if (src instanceof ArmaDTO) {
            	destino = new ArmaWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((ArmaDTO)src, destino);
            } else if (src instanceof ExplosivoDTO) {
            	destino = new ExplosivoWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((ExplosivoDTO)src, destino);
            } else if (src instanceof AeronaveDTO) {
            	destino = new AeronaveWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((AeronaveDTO)src, destino);
            } else if (src instanceof AnimalDTO) {
            	destino = new AnimalWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((AnimalDTO)src, destino);
            } else if (src instanceof DocumentoOficialDTO) {
            	destino = new DocumentoOficialWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((DocumentoOficialDTO)src, destino);
            } else if (src instanceof EmbarcacionDTO) {
            	destino = new EmbarcacionWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((EmbarcacionDTO)src, destino);
            } else if (src instanceof InmuebleDTO) {
            	destino = new InmuebleWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((InmuebleDTO)src, destino);
            } else if (src instanceof JoyaDTO) {
            	destino = new JoyaWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((JoyaDTO)src, destino);
            } else if (src instanceof NumerarioDTO) {
            	destino = new NumerarioWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((NumerarioDTO)src, destino);
            } else if (src instanceof ObraArteDTO) {
            	destino = new ObraArteWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((ObraArteDTO)src, destino);
            } else if (src instanceof SustanciaDTO) {
            	destino = new SustanciaWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((SustanciaDTO)src, destino);
            } else if (src instanceof VegetalDTO) {
            	destino = new VegetalWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((VegetalDTO)src, destino);
            } else {
            	destino = new ObjetoWSDTO();
                ExpedienteIPHWSDTOTransformer.transforma((ObjetoDTO)src, destino);
            }

        	//Parametros propios del Objeto
        	//Verificar si es necesario
        	if(src.getValorByCondicionFisicaVal()!=null)
            	destino.setValorByCondicionFisicaVal(src.getValorByCondicionFisicaVal().getIdCampo());
            if(src.getTipoObjeto()!=null)
            	destino.setTipoObjeto(src.getTipoObjeto().getValorId());
        	
        	destino.setDescripcion(src.getDescripcion());
//        	destino.setEsPertenencia(src.getEsPertenencia());

        	//Parametros propios de Elemento
            if (src.getCalidadDTO()!= null) {
            	CalidadWSDTO calidadWSDTO = new CalidadWSDTO();
            	ExpedienteIPHWSDTOTransformer.transforma(src.getCalidadDTO(), calidadWSDTO);
            	if(src.getCalidadDTO().getValorIdCalidad()!= null)
            		calidadWSDTO.setValorIdCalidad( src.getCalidadDTO().getValorIdCalidad().getIdCampo());
           		calidadWSDTO.setCalidades(src.getCalidadDTO().getValorIdCalidad().getIdCampo());
            	
            	destino.setCalidad(calidadWSDTO);
            }

            if(src.getValorIdElemento()!= null)
            	destino.setValorIdElemento(src.getValorIdElemento().getIdCampo());
            destino.setStrDescripcionRelacionarElemento(src.getStrDescripcionRelacionarElemento());
            if(src.getFotoDelElemento()!= null)        		
            	destino.setFotoDelElemento( ExpedienteIPHWSDTOTransformer.transforma(src.getFotoDelElemento()));
            
            if(src.getInstitucionPresenta()!= null)
            	destino.setInstitucionPresenta(src.getInstitucionPresenta().getConfInstitucionId());
        }

        if(src.getEvidencia() != null){
        	destino.setEvidencia(transformarEvidencia(src.getEvidencia()));
        }
        
        return destino;
    }

    public static ObjetoDTO transformarObjeto(mx.gob.segob.nsjp.dto.objeto.ObjetoWSDTO src) {
        ObjetoDTO destino = new ObjetoDTO();
        if (src != null) {
        	if (src instanceof mx.gob.segob.nsjp.dto.objeto.VehiculoWSDTO) {
        		destino = new VehiculoDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.VehiculoWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.EquipoComputoWSDTO) {
            	destino = new EquipoComputoDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.EquipoComputoWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.TelefoniaWSDTO) {
            	destino = new TelefoniaDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.TelefoniaWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.ArmaWSDTO) {
            	destino = new ArmaDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.ArmaWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.ExplosivoWSDTO) {
            	destino = new ExplosivoDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.ExplosivoWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.AeronaveWSDTO) {
            	destino = new AeronaveDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.AeronaveWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.AnimalWSDTO) {
            	destino = new AnimalDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.AnimalWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.DocumentoOficialWSDTO) {
            	destino = new DocumentoOficialDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.DocumentoOficialWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.EmbarcacionWSDTO) {
            	destino = new EmbarcacionDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.EmbarcacionWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.InmuebleWSDTO) {
            	destino = new InmuebleDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.InmuebleWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.JoyaWSDTO) {
            	destino = new JoyaDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.JoyaWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.NumerarioWSDTO) {
            	destino = new NumerarioDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.NumerarioWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.ObraArteWSDTO) {
            	destino = new ObraArteDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.ObraArteWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.SustanciaWSDTO) {
            	destino = new SustanciaDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.SustanciaWSDTO)src, destino);
            } else if (src instanceof mx.gob.segob.nsjp.dto.objeto.VegetalWSDTO) {
            	destino = new VegetalDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.VegetalWSDTO)src, destino);
            } else {
            	destino = new ObjetoDTO();
                ExpedienteIPHWSDTOTransformer.transforma((mx.gob.segob.nsjp.dto.objeto.ObjetoWSDTO)src, destino);
            }

        	//Parametros propios del Objeto
        	//Verificar si es necesario
        	if(src.getValorByCondicionFisicaVal()!=null)
            	destino.setValorByCondicionFisicaVal(new ValorDTO(src.getValorByCondicionFisicaVal()));
            if(src.getTipoObjeto()!=null)
            	destino.setTipoObjeto(Objetos.getByValor(src.getTipoObjeto()));
        	
        	destino.setDescripcion(src.getDescripcion());

        	//Parametros propios de Elemento
            if (src.getCalidad()!= null) {
            	CalidadDTO calidadDTO = new CalidadDTO();
            	ExpedienteIPHWSDTOTransformer.transforma(src.getCalidad(), calidadDTO);
            	calidadDTO.setValorIdCalidad(new ValorDTO(src.getCalidad().getValorIdCalidad()));
            	calidadDTO.setCalidades(Calidades.getByValor(src.getCalidad().getValorIdCalidad()));
            	destino.setCalidadDTO(calidadDTO);
            }

            if(src.getValorIdElemento()!= null)
            	destino.setValorIdElemento(new ValorDTO(src.getValorIdElemento()));
            destino.setStrDescripcionRelacionarElemento(src.getStrDescripcionRelacionarElemento());
            if(src.getFotoDelElemento()!= null){
            	mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO archivoDigitalWSDTO = src.getFotoDelElemento();
            	destino.setFotoDelElemento( ExpedienteIPHWSDTOTransformer.transforma(archivoDigitalWSDTO));
            }
            if(src.getInstitucionPresenta()!= null){
            	destino.setInstitucionPresenta(new ConfInstitucionDTO( src.getInstitucionPresenta()));
            }
            
            if(src.getEvidencia() != null){
            	destino.setEvidencia(EvidenciaTransformer.transformarEvidencia(src.getEvidencia()));
            }
            
        }
        return destino;
    }
    
    public static EvidenciaWSDTO transformarEvidencia(EvidenciaDTO input) {
    	EvidenciaWSDTO evidencia = null;
    	evidencia = new EvidenciaWSDTO();
		
		evidencia.setCadenaDeCustodia(transformarCadenaDeCustodia(input.getCadenaDeCustodia()));
		evidencia.setCantidad(input.getCantidad());
		evidencia.setCodigoBarras(input.getCodigoBarras());
		evidencia.setDescripcion(input.getDescripcion());
		if(input.getDestinoLegal() != null){
			evidencia.setIdDestinoLegal(input.getDestinoLegal().getIdCampo());
		}
		if(input.getEslabones() != null){
			for(EslabonDTO eslabon : input.getEslabones()){
				evidencia.getEslabones().add(transformarEslabon(eslabon));
			}
		}
		if(input.getEstatus()!=null){
			evidencia.setIdEstatus(input.getEstatus().getIdCampo());
		}
		evidencia.setFechaLevantamiento( WsTransformer.transformFecha(input.getFechaLevantamiento()));
		evidencia.setMotivoBaja(input.getMotivoBaja());
		evidencia.setNumeroEvidencia(input.getNumeroEvidencia());
		evidencia.setOrigenEvidencia(input.getOrigenEvidencia());
		if(input.getUltimoEslabon() != null){
			evidencia.setUltimoEslabon(transformarEslabon(input.getUltimoEslabon()));
		}
		
		return evidencia;
	}
    
    public static CadenaDeCustodiaWSDTO transformarCadenaDeCustodia(CadenaDeCustodiaDTO input){
    	CadenaDeCustodiaWSDTO cadena = new CadenaDeCustodiaWSDTO();
    	
		cadena.setFechaIntercambio(WsTransformer.transformFecha(input.getFechaIntercambio()));
		cadena.setFechaLevantamiento(WsTransformer.transformFecha(input.getFechaLevantamiento()));
		cadena.setFolio(input.getFolio());
		cadena.setObservacion(input.getObservacion());
		cadena.setQuienEmbala(input.getQuienEmbala());
		cadena.setQuienEntrega(input.getQuienEntrega());
		cadena.setQuienRecibe(input.getQuienRecibe());
		cadena.setQuienTransporta(input.getQuienTransporta());
		
    	return cadena;
    }

    public static EslabonWSDTO transformarEslabon(EslabonDTO input){
    	EslabonWSDTO eslabon = new EslabonWSDTO();
    	
		eslabon.setDocumento(ExpedienteIPHWSDTOTransformer.transforma(input.getDocumentoDTO()));
		eslabon.setFechaEntrega(WsTransformer.transformFecha(input.getFechaFinMovimiento()));
		eslabon.setFechaRecibe(WsTransformer.transformFecha(input.getFechaInicioMovimiento()));
		eslabon.setNumeroEslabon(input.getNumeroEslabon());
		eslabon.setObservacion(input.getObservacion());
		eslabon.setPosicion(input.getPosicion());
		eslabon.setIdTpoEslabon(input.getTipoEslabon().getIdCampo());
		eslabon.setUbicacionFisica(input.getUbicacionFisica());
    	
    	return eslabon;
    }
    
    
}

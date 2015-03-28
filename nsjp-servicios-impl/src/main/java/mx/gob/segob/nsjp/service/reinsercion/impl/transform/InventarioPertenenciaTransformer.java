/**
* Nombre del Programa 		: InventarioPertenenciaTransformer.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 28 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.reinsercion.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.model.InventarioPertenencia;
import mx.gob.segob.nsjp.model.Pertenencia;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.service.detencion.impl.transform.PertenenciaTransformer;
import mx.gob.segob.nsjp.service.detencion.transform.CentroDetencionTransformer;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class InventarioPertenenciaTransformer {
	
	public static final boolean CON_PERTENENCIAS = Boolean.TRUE;
	public static final boolean SIN_PERTENENCIAS = Boolean.FALSE;

	
	/**
	 * M&eacute;todo de transformaci&oacute;n que se encarga de llevar a cabo la
	 * conversi&oacute;n de un InventarioPertenenciaDTO a un InventarioPertenencia.
	 * @param inventarioPertenenciaDTO - El DTO a transformar
	 * @param conPertenencias - bandera que indica si las pertenencias se deben de
	 * 							considerar en la transformaci&oacute;n
	 * @return inventarioPertenencia - El Entity transformado
	 */
	public static InventarioPertenencia transformar (InventarioPertenenciaDTO inventarioPertenenciaDTO, boolean conPertenencias){
		InventarioPertenencia invPer = null;
		if (inventarioPertenenciaDTO != null){
			invPer = new InventarioPertenencia();
			
			if (inventarioPertenenciaDTO.getCentroDetencionDTO() != null){
				invPer.setCentroDetencion(CentroDetencionTransformer.transformar(inventarioPertenenciaDTO.getCentroDetencionDTO()));				
			}
			
			invPer.setDefinitivo(inventarioPertenenciaDTO.getDefinitivo());
			invPer.setEntregado(inventarioPertenenciaDTO.getEntregado());
			invPer.setFechaEntrega(inventarioPertenenciaDTO.getFechaEntrega());
			invPer.setFechaIngreso(inventarioPertenenciaDTO.getFechaIngreso());
			invPer.setInventarioPertenenciaId(inventarioPertenenciaDTO.getInventarioPertenenciaId());
			
			if (conPertenencias){
				List<PertenenciaDTO> pertenenciasDTO = inventarioPertenenciaDTO.getPertenenciasDTO();
				if (pertenenciasDTO != null && !pertenenciasDTO.isEmpty()){
					Set<Pertenencia> pertenencias = new HashSet<Pertenencia>();
					for (PertenenciaDTO pertenencia : pertenenciasDTO){
						pertenencias.add(PertenenciaTransformer.transformarDto(pertenencia));
					}
					invPer.setPertenencias(pertenencias);
				}
			}
			
			if (inventarioPertenenciaDTO.getSentenciaDTO() != null 
					&& inventarioPertenenciaDTO.getSentenciaDTO().getSentenciaId() != null){
				Sentencia sent = new Sentencia();
				sent.setSentenciaId(inventarioPertenenciaDTO.getSentenciaDTO().getSentenciaId());
				invPer.setSentencia(sent);
			}
		}
		return invPer;
	}
	
	public static InventarioPertenenciaDTO transformar (InventarioPertenencia inventarioPertenencia, boolean conPertenencias){
		InventarioPertenenciaDTO invPerDTO = null;
		if (inventarioPertenencia != null){
			invPerDTO = new InventarioPertenenciaDTO();
			
			if (inventarioPertenencia.getCentroDetencion() != null){
				invPerDTO.setCentroDetencionDTO(CentroDetencionTransformer.transformar(inventarioPertenencia.getCentroDetencion()));				
			}
			
			invPerDTO.setDefinitivo(inventarioPertenencia.getDefinitivo());
			invPerDTO.setEntregado(inventarioPertenencia.getEntregado());
			invPerDTO.setFechaEntrega(inventarioPertenencia.getFechaEntrega());
			invPerDTO.setFechaIngreso(inventarioPertenencia.getFechaIngreso());
			invPerDTO.setInventarioPertenenciaId(inventarioPertenencia.getInventarioPertenenciaId());
			
			if (conPertenencias){
				Set<Pertenencia> pertenencias = inventarioPertenencia.getPertenencias();
				if (pertenencias != null && !pertenencias.isEmpty()){
					List<PertenenciaDTO> pertenenciasDTO = new ArrayList<PertenenciaDTO>();
					for (Pertenencia pertenencia : pertenencias){
						pertenenciasDTO.add(PertenenciaTransformer.transformarEntity(pertenencia));
					}
					invPerDTO.setPertenenciasDTO(pertenenciasDTO);
				}
			}
			
			if (inventarioPertenencia.getSentencia() != null 
					&& inventarioPertenencia.getSentencia().getSentenciaId() != null){
				invPerDTO.setSentenciaDTO(SentenciaTransformer.transformar(inventarioPertenencia.getSentencia()));
			}
		}
		return invPerDTO;
	}
	
	public static InventarioPertenenciaDTO transformarSinSentencia (InventarioPertenencia inventarioPertenencia, boolean conPertenencias){
		InventarioPertenenciaDTO invPerDTO = null;
		if (inventarioPertenencia != null){
			invPerDTO = new InventarioPertenenciaDTO();
			
			if (inventarioPertenencia.getCentroDetencion() != null){
				invPerDTO.setCentroDetencionDTO(CentroDetencionTransformer.transformar(inventarioPertenencia.getCentroDetencion()));				
			}
			
			invPerDTO.setDefinitivo(inventarioPertenencia.getDefinitivo());
			invPerDTO.setEntregado(inventarioPertenencia.getEntregado());
			invPerDTO.setFechaEntrega(inventarioPertenencia.getFechaEntrega());
			invPerDTO.setFechaIngreso(inventarioPertenencia.getFechaIngreso());
			invPerDTO.setInventarioPertenenciaId(inventarioPertenencia.getInventarioPertenenciaId());
			
			if (conPertenencias){
				Set<Pertenencia> pertenencias = inventarioPertenencia.getPertenencias();
				if (pertenencias != null && !pertenencias.isEmpty()){
					List<PertenenciaDTO> pertenenciasDTO = new ArrayList<PertenenciaDTO>();
					for (Pertenencia pertenencia : pertenencias){
						pertenenciasDTO.add(PertenenciaTransformer.transformarEntity(pertenencia));
					}
					invPerDTO.setPertenenciasDTO(pertenenciasDTO);
				}
			}
			
			if (inventarioPertenencia.getSentencia() != null 
					&& inventarioPertenencia.getSentencia().getSentenciaId() != null){
				SentenciaDTO sentenciaDTO = new SentenciaDTO();
				sentenciaDTO.setSentenciaId(inventarioPertenencia.getInventarioPertenenciaId());
				invPerDTO.setSentenciaDTO(sentenciaDTO);
			}
		}
		return invPerDTO;
	}
}

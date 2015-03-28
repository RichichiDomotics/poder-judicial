/**
* Nombre del Programa : VegetalTansfomer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transformer de objetos vegetal
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vegetal;

import org.apache.log4j.Logger;

/**
 * Transformer de objetos vegetal
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class VegetalTransformer {
	
	private final static Logger logger = Logger.getLogger(VegetalTransformer.class);
	
  
	
	public static Vegetal transformarVegetal(VegetalDTO vegetalDto){
		Vegetal vg = new Vegetal();
		
		if(vegetalDto.getTipoVegetal()!=null && vegetalDto.getTipoVegetal().getIdCampo()!=null )
			vg.setTipoVegetal(new Valor(vegetalDto.getTipoVegetal().getIdCampo()));
	
		if(vegetalDto.getUnidadMedida()!=null && vegetalDto.getUnidadMedida().getIdCampo()!=null)
			vg.setUnidadMedida(new Valor(vegetalDto.getUnidadMedida().getIdCampo()));
		vg.setFolioElemento(vegetalDto.getFolioElemento());
	    vg.setCantidad(vegetalDto.getCantidad());
		vg.setDescripcion(vegetalDto.getDescripcion());
	
		if(vegetalDto.getValorByCondicionFisicaVal()!=null && vegetalDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			vg.setValorByCondicionFisicaVal(new Valor(vegetalDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(vegetalDto.getExpedienteDTO()!=null){
			vg.setExpediente(new Expediente(vegetalDto.getExpedienteDTO().getExpedienteId()));
			vg.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en VEgetalDTO");
		
		vg.setEsActivo(vegetalDto.getEsActivo());
		return vg;
	}
	
	
	/**
	 * Tranforma un objeto del modelo del tipo <code>Vegetal</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static VegetalDTO transformarVegetal(Vegetal src){
		VegetalDTO dest = null;
		
		if(src != null){
			dest = new VegetalDTO();
			if(src.getTipoVegetal() != null){
				dest.setTipoVegetal(new ValorDTO(src.getTipoVegetal().getValorId(),src.getTipoVegetal().getValor()));
			}
			if(src.getUnidadMedida() != null){
				dest.setUnidadMedida(new ValorDTO(src.getUnidadMedida().getValorId(),src.getUnidadMedida().getValor()));
			}
			dest.setCantidad(src.getCantidad());
			dest.setEsActivo(src.getEsActivo());
		}
		return dest;
	}
}

/**
* Nombre del Programa : JoyaTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transformer de los objetos Joya.
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
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Joya;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de los objetos Joya
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class JoyaTransformer {
	
	private final static Logger logger = Logger.getLogger(JoyaTransformer.class);
	
	public static Joya trasnformarJoya(JoyaDTO joyaDto){

		Joya joya = new Joya();
		
		if(joyaDto.getTipoJoya()!=null && joyaDto.getTipoJoya().getIdCampo()!=null)
			joya.setTipoJoya(new Valor(joyaDto.getTipoJoya().getIdCampo()));
		joya.setFolioElemento(joyaDto.getFolioElemento());		
		joya.setCantidad(joyaDto.getCantidad());
		joya.setMaterial(joyaDto.getMaterial());
		joya.setDescripcion(joyaDto.getDescripcion());

		if(joyaDto.getValorByCondicionFisicaVal()!=null && joyaDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			joya.setValorByCondicionFisicaVal(new Valor(joyaDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(joyaDto.getExpedienteDTO()!=null){
			joya.setExpediente(new Expediente(joyaDto.getExpedienteDTO().getExpedienteId()));
			joya.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en ArmaDTO");

		joya.setEsActivo(joyaDto.getEsActivo());

		return joya;
	}
	/**
	 * Tranforma un objeto del modelo del tipo <code>Joya</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static JoyaDTO transformarJoya(Joya src){

		JoyaDTO dest =  null;
		if(src != null){
			dest = new JoyaDTO();
			if(src.getTipoJoya() != null){
				dest.setTipoJoya(new ValorDTO(src.getTipoJoya().getValorId(),src.getTipoJoya().getValor()));
			}
			dest.setCantidad(src.getCantidad());
			dest.setMaterial(src.getMaterial());
			dest.setEsActivo(src.getEsActivo());			
		}
		return dest;
	}
}

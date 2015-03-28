/**
* Nombre del Programa : ObraArteTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16 May 2011
* Marca de cambio        : N/A
* Descripcion General    : transformer de los objetos sustancia
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
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.ObraArte;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * transformer de los objetos sustancia
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ObraArteTransformer {
	
	private final static Logger logger = Logger.getLogger(ObraArteTransformer.class);
	
	public static ObraArte transformarObraArte(ObraArteDTO obraArteDto){
		ObraArte obArt= new ObraArte();
		obArt.setFolioElemento(obraArteDto.getFolioElemento());
		if(obraArteDto.getTipoObraArte()!=null && obraArteDto.getTipoObraArte().getIdCampo()!=null )
			obArt.setTipoObraArte(new Valor(obraArteDto.getTipoObraArte().getIdCampo()));

					    
		obArt.setCantidad(obraArteDto.getCantidad());
		obArt.setDescripcion(obraArteDto.getDescripcion());

		
		if(obraArteDto.getValorByCondicionFisicaVal()!=null && obraArteDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			obArt.setValorByCondicionFisicaVal(new Valor(obraArteDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(obraArteDto.getExpedienteDTO()!=null){
			obArt.setExpediente(new Expediente(obraArteDto.getExpedienteDTO().getExpedienteId()));
			obArt.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en obArtDTO");
		
		obArt.setEsActivo(obraArteDto.getEsActivo());
		
		return obArt;
	}
	
	/**
	 * Tranforma un objeto del modelo del tipo <code>ObraArte</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static ObraArteDTO transformarObraArte(ObraArte src){
		ObraArteDTO dest = null;
		
		if(src != null){
			dest = new ObraArteDTO();
			if(src.getTipoObraArte() != null){
				dest.setTipoObraArte(new ValorDTO(src.getTipoObraArte().getValorId(),src.getTipoObraArte().getValor()));
			}
			dest.setCantidad(src.getCantidad());
			dest.setEsActivo(src.getEsActivo());
		}
		return dest;
		
		
	}
}

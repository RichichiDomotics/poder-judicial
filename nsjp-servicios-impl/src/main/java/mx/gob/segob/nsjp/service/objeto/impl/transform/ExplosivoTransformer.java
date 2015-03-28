/**
* Nombre del Programa : ExplosivoTransformer.java
* Autor                            : Tattva-IT
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 May 2011
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de objetos Explosivo
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ExplosivoTransformer {
	
	private final static Logger logger = Logger.getLogger(ExplosivoTransformer.class);
	
	public static Explosivo tranformarExplosivo(ExplosivoDTO explosivoDto){
		
		Explosivo exp = new Explosivo();
		
			    
		    if(explosivoDto.getTipoExplosivo()!=null&& explosivoDto.getTipoExplosivo().getIdCampo()!=null)
		    	exp.setTipoExplosivo(new Valor(explosivoDto.getTipoExplosivo().getIdCampo()));
		    
		    if(explosivoDto.getUnidadMedida()!=null&& explosivoDto.getUnidadMedida().getIdCampo()!=null)
		    	exp.setUnidadMedida(new Valor(explosivoDto.getUnidadMedida().getIdCampo()));
		    exp.setFolioElemento(explosivoDto.getFolioElemento());
		    exp.setCantidad(explosivoDto.getCantidad());
		    exp.setDescripcion(explosivoDto.getDescripcion());

		
		    if(explosivoDto.getValorByCondicionFisicaVal()!=null && explosivoDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
		    	exp.setValorByCondicionFisicaVal(new Valor(explosivoDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		    if(explosivoDto.getExpedienteDTO()!=null){
		    	exp.setExpediente(new Expediente(explosivoDto.getExpedienteDTO().getExpedienteId()));
		    	exp.setFechaCreacionElemento(new Date());
			
		    }else
		    	logger.debug("Sin expediente en el explosivo");

		    exp.setEsActivo(explosivoDto.getEsActivo());
		return exp;
	}
	
	/**
	 * Tranforma un objeto del modelo del tipo <code>Explosivo</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static ExplosivoDTO transformarExplosivo(Explosivo src){
		
		ExplosivoDTO dest = null;
		if(src != null){
			dest = new ExplosivoDTO();
			if(src.getTipoExplosivo() != null){
				dest.setTipoExplosivo(new ValorDTO(src.getTipoExplosivo().getValorId(),src.getTipoExplosivo().getValor()));
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

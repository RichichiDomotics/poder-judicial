/**
* Nombre del Programa : EmbarcacionTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transformer de los objetos Aeronave
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
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de los objetos Embarcacion
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class EmbarcacionTransformer {
	private final static Logger logger = Logger.getLogger(EmbarcacionTransformer.class);
	
	public static Embarcacion transformarEmbarcacion(EmbarcacionDTO embarcacionDTO){
		Embarcacion emb = new Embarcacion();
		emb.setFolioElemento(embarcacionDTO.getFolioElemento());
		if(embarcacionDTO.getMarca()!=null && embarcacionDTO.getMarca().getIdCampo()!=null )
			emb.setMarca(new Valor(embarcacionDTO.getMarca().getIdCampo()));
		
		if(embarcacionDTO.getTipoEmbarcacion()!=null && embarcacionDTO.getTipoEmbarcacion().getIdCampo()!=null)
			emb.setTipoEmbarcacion(new Valor(embarcacionDTO.getTipoEmbarcacion().getIdCampo()));
		
		if(embarcacionDTO.getPaisOrigen()!=null && embarcacionDTO.getPaisOrigen().getIdCampo()!=null )
			emb.setPaisOrigen(new Valor(embarcacionDTO.getPaisOrigen().getIdCampo()));
		
		if(embarcacionDTO.getSubMarca()!=null && embarcacionDTO.getSubMarca().getIdCampo()!=null)
			emb.setSubMarca(new Valor(embarcacionDTO.getSubMarca().getIdCampo()));
		
		if(embarcacionDTO.getColor()!=null && embarcacionDTO.getColor().getIdCampo()!=null)
			emb.setColor(new Valor(embarcacionDTO.getColor().getIdCampo()));
		
		emb.setNombreEmbarcacion(embarcacionDTO.getNombreEmbarcacion());
	    emb.setMatricula(embarcacionDTO.getMatricula());
		emb.setNoSerie(embarcacionDTO.getNoSerie());
		emb.setNoMotor(embarcacionDTO.getNoMotor());
		emb.setDescripcion(embarcacionDTO.getDescripcion());

		
		if(embarcacionDTO.getValorByCondicionFisicaVal()!=null && embarcacionDTO.getValorByCondicionFisicaVal().getIdCampo()!=null)
			emb.setValorByCondicionFisicaVal(new Valor(embarcacionDTO.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(embarcacionDTO.getExpedienteDTO()!=null){
			emb.setExpediente(new Expediente(embarcacionDTO.getExpedienteDTO().getExpedienteId()));
			emb.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en EmbarcacionDTO");

		emb.setEsActivo(embarcacionDTO.getEsActivo());
		return emb;
	}
	/**
	 * Tranforma un objeto del modelo del tipo <code>Embarcacion</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static EmbarcacionDTO transformarEmbarcacion(Embarcacion src){
		EmbarcacionDTO dest = null;
		
		if(src != null){
			dest = new EmbarcacionDTO();
			if(src.getMarca() != null){
				dest.setMarca(new ValorDTO(src.getMarca().getValorId(),src.getMarca().getValor()));
			}
			if(src.getTipoEmbarcacion() != null){
				dest.setTipoEmbarcacion(new ValorDTO(src.getTipoEmbarcacion().getValorId(),src.getTipoEmbarcacion().getValor()));
			}
			if(src.getPaisOrigen() != null){
				dest.setPaisOrigen(new ValorDTO(src.getPaisOrigen().getValorId(),src.getPaisOrigen().getValor()));
			}
			if(src.getSubMarca() != null){
				dest.setSubMarca(new ValorDTO(src.getSubMarca().getValorId(),src.getSubMarca().getValor()));
			}
			if(src.getColor() != null){
				dest.setColor(new ValorDTO(src.getColor().getValorId(),src.getColor().getValor()));
			}
			
			dest.setNombreEmbarcacion(src.getNombreEmbarcacion());
			dest.setMatricula(src.getMatricula());
			dest.setNoSerie(src.getNoSerie());
			dest.setNoMotor(src.getNoMotor());
			dest.setEsActivo(src.getEsActivo());			
		}
		return dest;
	}
}

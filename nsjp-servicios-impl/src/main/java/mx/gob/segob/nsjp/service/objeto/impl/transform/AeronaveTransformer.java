/**
* Nombre del Programa : AeronaveTransformer.java
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
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de los objetos Aeronave
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AeronaveTransformer {
	private final static Logger logger = Logger.getLogger(AeronaveTransformer.class);
	
	public static Aeronave transformarAeronave(AeronaveDTO aeronaveDto){
		Aeronave aero = new Aeronave();
		aero.setFolioElemento(aeronaveDto.getFolioElemento());
		if(aeronaveDto.getMarca()!=null && aeronaveDto.getMarca().getIdCampo()!=null )
			aero.setMarca(new Valor(aeronaveDto.getMarca().getIdCampo()));
	
		if(aeronaveDto.getTipoAeroNave()!=null && aeronaveDto.getTipoAeroNave().getIdCampo()!=null)
			aero .setTipoAeroNave(new Valor(aeronaveDto.getTipoAeroNave().getIdCampo()));
		
		if(aeronaveDto.getPaisProcedencia()!=null && aeronaveDto.getPaisProcedencia().getIdCampo()!=null )
			aero.setPaisProcedencia(new Valor(aeronaveDto.getPaisProcedencia().getIdCampo()));
	
		if(aeronaveDto.getSubMarca()!=null && aeronaveDto.getSubMarca().getIdCampo()!=null)
			aero.setSubMarca(new Valor(aeronaveDto.getSubMarca().getIdCampo()));
		
		if(aeronaveDto.getColor()!=null && aeronaveDto.getColor().getIdCampo()!=null)
			aero.setColor(new Valor(aeronaveDto.getColor().getIdCampo()));
		
	    aero.setModelo(aeronaveDto.getModelo());
		aero.setMatricula(aeronaveDto.getMatricula());
		aero.setNoSerie(aeronaveDto.getNoSerie());
		aero.setNoMotor(aeronaveDto.getNoMotor());
		aero.setDescripcion(aeronaveDto.getDescripcion());

		
		if(aeronaveDto.getValorByCondicionFisicaVal()!=null && aeronaveDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			aero.setValorByCondicionFisicaVal(new Valor(aeronaveDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(aeronaveDto.getExpedienteDTO()!=null){
			aero.setExpediente(new Expediente(aeronaveDto.getExpedienteDTO().getExpedienteId()));
			aero.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en AeronaveDTO");

		aero.setEsActivo(aeronaveDto.getEsActivo());
		
		return aero;
	}
	/**
	 * Transforma un objeto del modelo <code>Aeronave</code> en su correspondiente DTO
	 * del tipo <code>AeronaveDTO</code>
	 * @param src Datos fuente
	 * @return DTO transformado
	 */
	public static AeronaveDTO transformarAeronave(Aeronave src){
		AeronaveDTO destino = null;
		if(src != null){
			destino = new AeronaveDTO();
			if(src.getMarca() != null){
				destino.setMarca(new ValorDTO(src.getMarca().getValorId(),src.getMarca().getValor()));
			}
			
			if(src.getTipoAeroNave() != null){
				destino.setTipoAeroNave(new ValorDTO(src.getTipoAeroNave().getValorId(),src.getTipoAeroNave().getValor()));
			}
			
			if(src.getPaisProcedencia() != null){
				destino.setPaisProcedencia(new ValorDTO(src.getPaisProcedencia().getValorId(),src.getPaisProcedencia().getValor()));
			}
			
			if(src.getSubMarca() != null){
				destino.setSubMarca(new ValorDTO(src.getSubMarca().getValorId(),src.getSubMarca().getValor()));
			}
			
			if(src.getColor() != null){
				destino.setColor(new ValorDTO(src.getColor().getValorId(),src.getColor().getValor()));
			}
			
			destino.setModelo(src.getModelo());
			destino.setMatricula(src.getMatricula());
			destino.setNoSerie(src.getNoSerie());
			destino.setNoMotor(src.getNoMotor());
			destino.setEsActivo(src.getEsActivo());
		}
		return destino;
	}
}

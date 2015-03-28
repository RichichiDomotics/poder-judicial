/**
* Nombre del Programa : SustanciaTransformer.java
* Autor                            : Tattva-IT
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 May 2011
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
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * transformer de los objetos sustancia
 * @version 1.0
 * @author Tattva-IT
 *
 */


public class SustanciaTransformer {
	
	private final static Logger logger = Logger.getLogger(SustanciaTransformer.class);
	
	public static Sustancia transformarSustancia(SustanciaDTO sustDto){
		Sustancia sus = new Sustancia();
	
		if(sustDto.getTipoSustancia()!=null && sustDto.getTipoSustancia().getIdCampo()!=null )
			sus.setTipoSustancia(new Valor(sustDto.getTipoSustancia().getIdCampo()));
	
		if(sustDto.getEmpaque()!=null && sustDto.getEmpaque().getIdCampo()!=null )
			sus.setEmpaque(new Valor(sustDto.getEmpaque().getIdCampo()));
		
		if(sustDto.getUnidadMedida()!=null && sustDto.getUnidadMedida().getIdCampo()!=null )
			sus.setUnidadMedida(new Valor(sustDto.getUnidadMedida().getIdCampo()));

		sus.setFolioElemento(sustDto.getFolioElemento());	    
	    sus.setCantidad(sustDto.getCantidad());
		sus.setDescripcion(sustDto.getDescripcion());

		
		if(sustDto.getValorByCondicionFisicaVal()!=null && sustDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			sus.setValorByCondicionFisicaVal(new Valor(sustDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(sustDto.getExpedienteDTO()!=null){
			sus.setExpediente(new Expediente(sustDto.getExpedienteDTO().getExpedienteId()));
			sus.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en SustanciaDTO");

		sus.setEsActivo(sustDto.getEsActivo());
		
		return sus;
	}
	/**
	 * Tranforma un objeto del modelo del tipo <code>Sustancia</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static SustanciaDTO transformarSustancia(Sustancia src){
		SustanciaDTO dest = null;
		if(src != null){
			dest = new SustanciaDTO();
			if(src.getTipoSustancia() != null){
				dest.setTipoSustancia(new ValorDTO(src.getTipoSustancia().getValorId(),src.getTipoSustancia().getValor()));
			}
			if(src.getEmpaque() != null){
				dest.setEmpaque(new ValorDTO(src.getEmpaque().getValorId(),src.getEmpaque().getValor()));
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

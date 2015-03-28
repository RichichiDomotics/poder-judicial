/**
* Nombre del Programa : EquipoComputoTransformer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : tranformer de los objetos equipo de computo                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.model.EquipoComputo;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * tranformer de los objetos equipo de computo
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class EquipoComputoTransformer {
	
	private final static Logger logger = Logger.getLogger(EquipoComputoTransformer.class);
	
	public static EquipoComputo transformarEquipoComputo(EquipoComputoDTO equipoComputoDto){
		
		EquipoComputo eqCom = new EquipoComputo();
		eqCom.setElementoId(equipoComputoDto.getElementoId());
		eqCom.setFolioElemento(equipoComputoDto.getFolioElemento());
		if(equipoComputoDto.getColor()!=null && equipoComputoDto.getColor().getIdCampo()!=null )
				eqCom.setColor(new Valor(equipoComputoDto.getColor().getIdCampo()));
		
		if(equipoComputoDto.getMarca()!=null && equipoComputoDto.getMarca().getIdCampo()!=null)
				eqCom.setMarca(new Valor(equipoComputoDto.getMarca().getIdCampo()));
		
		if(equipoComputoDto.getTipoEquipo()!=null && equipoComputoDto.getTipoEquipo()!=null)
				eqCom.setTipoEquipo(new Valor(equipoComputoDto.getTipoEquipo().getIdCampo()));
		
		if(equipoComputoDto.getValorByCondicionFisicaVal()!=null && equipoComputoDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			eqCom.setValorByCondicionFisicaVal(new Valor(equipoComputoDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		eqCom.setNoSerie(equipoComputoDto.getNoSerie());
		eqCom.setModelo(equipoComputoDto.getModelo());
		eqCom.setDescripcion(equipoComputoDto.getDescripcion());
		
		if(equipoComputoDto.getExpedienteDTO()!=null){
			eqCom.setExpediente(new Expediente(equipoComputoDto.getExpedienteDTO().getExpedienteId()));
			eqCom.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en EquipoComputoDTO");

		eqCom.setEsActivo(equipoComputoDto.getEsActivo());
		
		return eqCom;
	}
	
	

	/**
	 * Tranforma un objeto del modelo del tipo <code>EquipoComputo</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static EquipoComputoDTO transformarEquipoComputo(EquipoComputo src){
		EquipoComputoDTO dest = null;
		
		if(src != null){
			dest = new EquipoComputoDTO();
			
			if(src.getColor() != null){
				dest.setColor(new ValorDTO(src.getColor().getValorId(),src.getColor().getValor()));
			}
			
			if(src.getMarca() != null){
				dest.setMarca(new ValorDTO(src.getMarca().getValorId(),src.getMarca().getValor()));
			}
			if(src.getTipoEquipo() != null){
				dest.setTipoEquipo(new ValorDTO(src.getTipoEquipo().getValorId(),src.getTipoEquipo().getValor()));
			}
				
			
					
			dest.setNoSerie(src.getNoSerie());
			dest.setModelo(src.getModelo());
			dest.setEsActivo(src.getEsActivo());		
		}
		return dest;
	}
}

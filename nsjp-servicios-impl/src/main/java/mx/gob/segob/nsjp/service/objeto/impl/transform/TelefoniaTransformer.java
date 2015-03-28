/**
* Nombre del Programa : TelefoniaTransformer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Transfomer de objetos Telefonia                      
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
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Telefonia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.involucrado.impl.IngresarIndividuoServiceImpl;

import org.apache.log4j.Logger;

/**
 * Transfomer de objetos Telefonia
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class TelefoniaTransformer {
	private final static Logger logger = Logger.getLogger(IngresarIndividuoServiceImpl.class);
	
	public static Telefonia transformarTelefonia(TelefoniaDTO telefoniaDto){
		
		Telefonia telf = new Telefonia();
		
		if(telefoniaDto.getTipoTelefono()!=null && telefoniaDto.getTipoTelefono().getIdCampo()!=null)
			telf.setTipoTelefono(new Valor(telefoniaDto.getTipoTelefono().getIdCampo()));
		
		if(telefoniaDto.getMarca()!=null && telefoniaDto.getMarca().getIdCampo()!=null)
			telf.setMarca(new Valor(telefoniaDto.getMarca().getIdCampo()));
		
		if(telefoniaDto.getValorByCondicionFisicaVal()!=null && telefoniaDto.getValorByCondicionFisicaVal().getIdCampo()!=null){
			telf.setValorByCondicionFisicaVal(new Valor(telefoniaDto.getValorByCondicionFisicaVal().getIdCampo()));
		}
		telf.setFolioElemento(telefoniaDto.getFolioElemento());
		telf.setCantidad(telefoniaDto.getCantidad());
		telf.setModelo(telefoniaDto.getModelo());
		telf.setDescripcion(telefoniaDto.getDescripcion());
		
		if(telefoniaDto.getExpedienteDTO()!=null){
			telf.setExpediente(new Expediente(telefoniaDto.getExpedienteDTO().getExpedienteId()));
			telf.setFechaCreacionElemento(new Date());
		}else
			 logger.debug("Sin expediente en EquipoComputoDTO");
		
		telf.setEsActivo(telefoniaDto.getEsActivo());
		return telf;
	}
	
	/**
	 * Tranforma un objeto del modelo del tipo <code>Telefonia</code> en su 
	 * correspondiente DTO
	 * @param src Datos de origen
	 * @return DTO transformado
	 */
	public static TelefoniaDTO transformarTelefonia(Telefonia src){
		
		TelefoniaDTO dest = null;
		if(src != null){
			dest = new TelefoniaDTO();
			if(src.getTipoTelefono() != null){
				dest.setTipoTelefono(new ValorDTO(src.getTipoTelefono().getValorId(),src.getTipoTelefono().getValor()));
			}
			if(src.getMarca() != null){
				dest.setMarca(new ValorDTO(src.getMarca().getValorId(),src.getMarca().getValor()));
			}
			dest.setCantidad(src.getCantidad());
			dest.setModelo(src.getModelo());
			dest.setEsActivo(src.getEsActivo());		
		}
		return dest;
	}
}

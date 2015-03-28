/**
* Nombre del Programa : InvolucradoOcupacionTransfromer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 29/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integración xxxxxxxxxxx                      
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
package mx.gob.segob.nsjp.service.involucrado.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class InvolucradoOcupacionTransfromer {
	
	public static ValorDTO transformarInvolucradoOcupacion(InvolucradoOcupacion involucradoocupacion){
		ValorDTO valorDto = new ValorDTO();
		
		valorDto.setIdCampo(involucradoocupacion.getValor().getValorId());
		valorDto.setValor(involucradoocupacion.getValor().getValor());
		
		return valorDto;
		
	}
	
	public static InvolucradoOcupacion transformarInvolucradoOcupacion(ValorDTO involucradoOcupacionDTO){
		InvolucradoOcupacion involucradoOcupacion = new InvolucradoOcupacion();
		Valor valor = new Valor();
		
		valor.setValorId(involucradoOcupacionDTO.getIdCampo());
		//valor.setValor(involucradoOcupacionDTO.getValor().getValor());
		
		involucradoOcupacion.setValor(valor);
		
		return involucradoOcupacion;
		
	}

}

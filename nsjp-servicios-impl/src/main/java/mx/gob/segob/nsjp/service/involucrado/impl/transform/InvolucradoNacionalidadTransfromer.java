/**
* Nombre del Programa : InvolucradoNacionalidadTransfromer.java                                    
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
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class InvolucradoNacionalidadTransfromer {
	
	public static ValorDTO transformarInvolucradoNacionalidad(InvolucradoNacionalidad invnac){
		ValorDTO valorDto = new ValorDTO();
		
		valorDto.setIdCampo(invnac.getValor().getValorId());
		valorDto.setValor(invnac.getValor().getValor());

		
		return valorDto;
	}
	
	
	public static InvolucradoNacionalidad transformarInvolucradoNacionalidad(ValorDTO invNacDTO){
		InvolucradoNacionalidad invNacionalidad = new InvolucradoNacionalidad();
		Valor valor = new Valor();
		
		valor.setValorId(invNacDTO.getIdCampo());
		//valor.setValor(invNacDTO.getValor().getValor());

		invNacionalidad.setValor(valor);
		
		return invNacionalidad;
	}

}

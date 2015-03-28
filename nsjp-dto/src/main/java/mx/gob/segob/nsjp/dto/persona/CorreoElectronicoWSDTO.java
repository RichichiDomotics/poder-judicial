/**
*
* Nombre del Programa : CorreoElectronicoWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Correo Electronico.                      
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
package mx.gob.segob.nsjp.dto.persona;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Correo Electronico
 * @author GustavoBP
 * @version 1.0
 */
public class CorreoElectronicoWSDTO extends GenericWSDTO {
	
	private String direccionElectronica;

	
	/**
	 * @param direccionElectronica
	 */
	public CorreoElectronicoWSDTO(String direccionElectronica) {
		super();		
		this.direccionElectronica = direccionElectronica;
	}

	public CorreoElectronicoWSDTO() {
		super();				
	}
	
	/**
	 * @return the direccionElectronica
	 */
	public String getDireccionElectronica() {
		return direccionElectronica;
	}

	/**
	 * @param direccionElectronica the direccionElectronica to set
	 */
	public void setDireccionElectronica(String direccionElectronica) {
		this.direccionElectronica = direccionElectronica;
	}

}

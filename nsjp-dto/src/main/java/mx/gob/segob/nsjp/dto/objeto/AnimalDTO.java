/**
* Nombre del Programa : AnimalDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto animal.                     
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
package mx.gob.segob.nsjp.dto.objeto;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;



/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto animal.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AnimalDTO extends ObjetoDTO {
	
	private ValorDTO tipoAnimal;
	private String estadoAnimal;
	private String razaAnimal;
	/**
	 * M�todo de acceso al campo tipoAnimal.
	 * @return El valor del campo tipoAnimal
	 */
	public ValorDTO getTipoAnimal() {
		return tipoAnimal;
	}
	/**
	 * Asigna el valor al campo tipoAnimal.
	 * @param tipoAnimal el valor tipoAnimal a asignar
	 */
	public void setTipoAnimal(ValorDTO tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	/**
	 * M�todo de acceso al campo estadoAnimal.
	 * @return El valor del campo estadoAnimal
	 */
	public String getEstadoAnimal() {
		return estadoAnimal;
	}
	/**
	 * Asigna el valor al campo estadoAnimal.
	 * @param estadoAnimal el valor estadoAnimal a asignar
	 */
	public void setEstadoAnimal(String estadoAnimal) {
		this.estadoAnimal = estadoAnimal;
	}
	/**
	 * M�todo de acceso al campo razaAnimal.
	 * @return El valor del campo razaAnimal
	 */
	public String getRazaAnimal() {
		return razaAnimal;
	}
	/**
	 * Asigna el valor al campo razaAnimal.
	 * @param razaAnimal el valor razaAnimal a asignar
	 */
	public void setRazaAnimal(String razaAnimal) {
		this.razaAnimal = razaAnimal;
	}
}

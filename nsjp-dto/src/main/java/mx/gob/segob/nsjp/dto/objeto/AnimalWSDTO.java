/**
* Nombre del Programa : AnimalWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Animal.                       
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


/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Animal.
 * @author GustavoBP
 * @version 1.0
 */
public class AnimalWSDTO extends ObjetoWSDTO {
	
	private Long tipoAnimal;
	private String estadoAnimal;
	private String razaAnimal;
	
	/**
	 * Mï¿½todo de acceso al campo tipoAnimal.
	 * @return El valor del campo tipoAnimal
	 */
	public Long getTipoAnimal() {
		return tipoAnimal;
	}
	/**
	 * Asigna el valor al campo tipoAnimal.
	 * @param tipoAnimal el valor tipoAnimal a asignar
	 */
	public void setTipoAnimal(Long tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	/**
	 * Mï¿½todo de acceso al campo estadoAnimal.
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
	 * Mï¿½todo de acceso al campo razaAnimal.
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

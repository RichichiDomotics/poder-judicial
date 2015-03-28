/**
* Nombre del Programa : VegetalWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Vegetal.                     
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Vegetal.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class VegetalWSDTO extends ObjetoWSDTO {
	
	
	private Short cantidad;
    private Long tipoVegetal;
    private Long unidadMedida;
	/**
	 * Mï¿½todo de acceso al campo cantidad.
	 * @return El valor del campo cantidad
	 */
	public Short getCantidad() {
		return cantidad;
	}
	/**
	 * Asigna el valor al campo cantidad.
	 * @param cantidad el valor cantidad a asignar
	 */
	public void setCantidad(Short cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Mï¿½todo de acceso al campo tipoVegetal.
	 * @return El valor del campo tipoVegetal
	 */
	public Long getTipoVegetal() {
		return tipoVegetal;
	}
	/**
	 * Asigna el valor al campo tipoVegetal.
	 * @param tipoVegetal el valor tipoVegetal a asignar
	 */
	public void setTipoVegetal(Long tipoVegetal) {
		this.tipoVegetal = tipoVegetal;
	}
	/**
	 * Mï¿½todo de acceso al campo unidadMedida.
	 * @return El valor del campo unidadMedida
	 */
	public Long getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * Asigna el valor al campo unidadMedida.
	 * @param unidadMedida el valor unidadMedida a asignar
	 */
	public void setUnidadMedida(Long unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

    
}

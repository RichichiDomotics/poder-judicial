/**
* Nombre del Programa : JoyaWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de una Joya.                     
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de una Joya.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class JoyaWSDTO extends ObjetoWSDTO {
	
    private Short cantidad;
    private Long tipoJoya;
    private String material;
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
	 * Mï¿½todo de acceso al campo tipoJoya.
	 * @return El valor del campo tipoJoya
	 */
	public Long getTipoJoya() {
		return tipoJoya;
	}
	/**
	 * Asigna el valor al campo tipoJoya.
	 * @param tipoJoya el valor tipoJoya a asignar
	 */
	public void setTipoJoya(Long tipoJoya) {
		this.tipoJoya = tipoJoya;
	}
	/**
	 * Mï¿½todo de acceso al campo material.
	 * @return El valor del campo material
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * Asigna el valor al campo material.
	 * @param material el valor material a asignar
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}

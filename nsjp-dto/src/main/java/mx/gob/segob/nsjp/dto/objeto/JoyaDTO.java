/**
* Nombre del Programa : JoyaDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto joya                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto joya
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class JoyaDTO extends ObjetoDTO {
	
    private Long cantidad;
    private ValorDTO tipoJoya;
    private String material;
	/**
	 * M�todo de acceso al campo cantidad.
	 * @return El valor del campo cantidad
	 */
	public Long getCantidad() {
		return cantidad;
	}
	/**
	 * Asigna el valor al campo cantidad.
	 * @param cantidad el valor cantidad a asignar
	 */
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * M�todo de acceso al campo tipoJoya.
	 * @return El valor del campo tipoJoya
	 */
	public ValorDTO getTipoJoya() {
		return tipoJoya;
	}
	/**
	 * Asigna el valor al campo tipoJoya.
	 * @param tipoJoya el valor tipoJoya a asignar
	 */
	public void setTipoJoya(ValorDTO tipoJoya) {
		this.tipoJoya = tipoJoya;
	}
	/**
	 * M�todo de acceso al campo material.
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

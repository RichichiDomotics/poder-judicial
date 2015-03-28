/**
* Nombre del Programa : VegetalDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto Vegetal                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Vegetal.
 * 
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class VegetalDTO extends ObjetoDTO {
	
	
	private Long cantidad;
    private ValorDTO tipoVegetal;
    private ValorDTO unidadMedida;
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
	 * M�todo de acceso al campo tipoVegetal.
	 * @return El valor del campo tipoVegetal
	 */
	public ValorDTO getTipoVegetal() {
		return tipoVegetal;
	}
	/**
	 * Asigna el valor al campo tipoVegetal.
	 * @param tipoVegetal el valor tipoVegetal a asignar
	 */
	public void setTipoVegetal(ValorDTO tipoVegetal) {
		this.tipoVegetal = tipoVegetal;
	}
	/**
	 * M�todo de acceso al campo unidadMedida.
	 * @return El valor del campo unidadMedida
	 */
	public ValorDTO getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * Asigna el valor al campo unidadMedida.
	 * @param unidadMedida el valor unidadMedida a asignar
	 */
	public void setUnidadMedida(ValorDTO unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

    
}

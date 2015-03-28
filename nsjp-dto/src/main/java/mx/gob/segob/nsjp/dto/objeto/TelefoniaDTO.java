/**
* Nombre del Programa : TelefoniaDAO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto telefonia.                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto telefonia.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class TelefoniaDTO extends ObjetoDTO {
	
    private Long cantidad;
    private ValorDTO marca;
    private ValorDTO tipoTelefono;
    private String modelo;
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
	 * M�todo de acceso al campo marca.
	 * @return El valor del campo marca
	 */
	public ValorDTO getMarca() {
		return marca;
	}
	/**
	 * Asigna el valor al campo marca.
	 * @param marca el valor marca a asignar
	 */
	public void setMarca(ValorDTO marca) {
		this.marca = marca;
	}
	/**
	 * M�todo de acceso al campo tipoTelefono.
	 * @return El valor del campo tipoTelefono
	 */
	public ValorDTO getTipoTelefono() {
		return tipoTelefono;
	}
	/**
	 * Asigna el valor al campo tipoTelefono.
	 * @param tipoTelefono el valor tipoTelefono a asignar
	 */
	public void setTipoTelefono(ValorDTO tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	/**
	 * M�todo de acceso al campo modelo.
	 * @return El valor del campo modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Asigna el valor al campo modelo.
	 * @param modelo el valor modelo a asignar
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


}

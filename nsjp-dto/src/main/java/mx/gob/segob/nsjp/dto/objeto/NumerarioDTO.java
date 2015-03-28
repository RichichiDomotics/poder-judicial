/**
* Nombre del Programa : NumerarioDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto numerario                      
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

import java.util.Date;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto numerario
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class NumerarioDTO extends ObjetoDTO {
	
    private Date fechaDeposito;
    private String ctaTesoreria;
    private Long cantidad;
    private String moneda;
	/**
	 * M�todo de acceso al campo fechaDeposito.
	 * @return El valor del campo fechaDeposito
	 */
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	/**
	 * Asigna el valor al campo fechaDeposito.
	 * @param fechaDeposito el valor fechaDeposito a asignar
	 */
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	/**
	 * M�todo de acceso al campo ctaTesoreria.
	 * @return El valor del campo ctaTesoreria
	 */
	public String getCtaTesoreria() {
		return ctaTesoreria;
	}
	/**
	 * Asigna el valor al campo ctaTesoreria.
	 * @param ctaTesoreria el valor ctaTesoreria a asignar
	 */
	public void setCtaTesoreria(String ctaTesoreria) {
		this.ctaTesoreria = ctaTesoreria;
	}
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
	 * M�todo de acceso al campo moneda.
	 * @return El valor del campo moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * Asigna el valor al campo moneda.
	 * @param moneda el valor moneda a asignar
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}

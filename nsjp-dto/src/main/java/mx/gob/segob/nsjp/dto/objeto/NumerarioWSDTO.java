/**
* Nombre del Programa : NumerarioWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Numerario.                    
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Numerario.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class NumerarioWSDTO extends ObjetoWSDTO {
	
    private String fechaDeposito;
    private String ctaTesoreria;
    private Long cantidad;
    private String moneda;
	/**
	 * Mï¿½todo de acceso al campo fechaDeposito.
	 * @return El valor del campo fechaDeposito
	 */
	public String getFechaDeposito() {
		return fechaDeposito;
	}
	/**
	 * Asigna el valor al campo fechaDeposito.
	 * @param fechaDeposito el valor fechaDeposito a asignar
	 */
	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	/**
	 * Mï¿½todo de acceso al campo ctaTesoreria.
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
	 * Mï¿½todo de acceso al campo cantidad.
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
	 * Mï¿½todo de acceso al campo moneda.
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

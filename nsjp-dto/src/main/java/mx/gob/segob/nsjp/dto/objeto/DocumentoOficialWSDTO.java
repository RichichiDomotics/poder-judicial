/**
* Nombre del Programa : DocumentoOficialWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un DocumentoOficial.                      
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un DocumentoOficial.
 * @author GustavoBP
 * @version 1.0
 */
public class DocumentoOficialWSDTO extends ObjetoWSDTO {
	
    private Short cantidad;
    private Long tipoDocumento;
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
	 * Mï¿½todo de acceso al campo tipoDocumento.
	 * @return El valor del campo tipoDocumento
	 */
	public Long getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * Asigna el valor al campo tipoDocumento.
	 * @param tipoDocumento el valor tipoDocumento a asignar
	 */
	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}

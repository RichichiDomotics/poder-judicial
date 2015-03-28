/**
* Nombre del Programa : ExplosivoWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Explosivo.                     
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Explosivo.
 * @author GustavoBP
 * @version 1.0
 */
public class ExplosivoWSDTO extends ObjetoWSDTO {
    private Short cantidad;
    private Long tipoExplosivo;
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
	 * Mï¿½todo de acceso al campo tipoExplosivo.
	 * @return El valor del campo tipoExplosivo
	 */
	public Long getTipoExplosivo() {
		return tipoExplosivo;
	}
	/**
	 * Asigna el valor al campo tipoExplosivo.
	 * @param tipoExplosivo el valor tipoExplosivo a asignar
	 */
	public void setTipoExplosivo(Long tipoExplosivo) {
		this.tipoExplosivo = tipoExplosivo;
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

/**
* Nombre del Programa : SustanciaWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de una Sustancia.                     
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de una Sustancia.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class SustanciaWSDTO extends ObjetoWSDTO {
	
    private Short cantidad;
    private Long tipoSustancia;
    private Long empaque;
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
	 * Mï¿½todo de acceso al campo tipoSustancia.
	 * @return El valor del campo tipoSustancia
	 */
	public Long getTipoSustancia() {
		return tipoSustancia;
	}
	/**
	 * Asigna el valor al campo tipoSustancia.
	 * @param tipoSustancia el valor tipoSustancia a asignar
	 */
	public void setTipoSustancia(Long tipoSustancia) {
		this.tipoSustancia = tipoSustancia;
	}
	/**
	 * Mï¿½todo de acceso al campo empaque.
	 * @return El valor del campo empaque
	 */
	public Long getEmpaque() {
		return empaque;
	}
	/**
	 * Asigna el valor al campo empaque.
	 * @param empaque el valor empaque a asignar
	 */
	public void setEmpaque(Long empaque) {
		this.empaque = empaque;
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

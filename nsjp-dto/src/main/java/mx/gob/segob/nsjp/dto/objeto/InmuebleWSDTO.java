/**
* Nombre del Programa : InmuebleWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Inmueble.
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.objeto;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Inmueble.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class InmuebleWSDTO extends ObjetoWSDTO {

	private Integer superficie;
	private Integer mConstruccion;
	private Long tipoInmueble;
	private Long tipoConstruccion;
	private Long unidadMedidaTerreno;
	private Long unidadMedidaConstruccion;
	/**
	 * Método de acceso al campo superficie.
	 * @return El valor del campo superficie
	 */
	public Integer getSuperficie() {
		return superficie;
	}
	/**
	 * Asigna el valor al campo superficie.
	 * @param superficie el valor superficie a asignar
	 */
	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	/**
	 * Método de acceso al campo mConstruccion.
	 * @return El valor del campo mConstruccion
	 */
	public Integer getmConstruccion() {
		return mConstruccion;
	}
	/**
	 * Asigna el valor al campo mConstruccion.
	 * @param mConstruccion el valor mConstruccion a asignar
	 */
	public void setmConstruccion(Integer mConstruccion) {
		this.mConstruccion = mConstruccion;
	}
	/**
	 * Método de acceso al campo tipoInmueble.
	 * @return El valor del campo tipoInmueble
	 */
	public Long getTipoInmueble() {
		return tipoInmueble;
	}
	/**
	 * Asigna el valor al campo tipoInmueble.
	 * @param tipoInmueble el valor tipoInmueble a asignar
	 */
	public void setTipoInmueble(Long tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}
	/**
	 * Método de acceso al campo tipoConstruccion.
	 * @return El valor del campo tipoConstruccion
	 */
	public Long getTipoConstruccion() {
		return tipoConstruccion;
	}
	/**
	 * Asigna el valor al campo tipoConstruccion.
	 * @param tipoConstruccion el valor tipoConstruccion a asignar
	 */
	public void setTipoConstruccion(Long tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}
	/**
	 * Método de acceso al campo unidadMedidaTerreno.
	 * @return El valor del campo unidadMedidaTerreno
	 */
	public Long getUnidadMedidaTerreno() {
		return unidadMedidaTerreno;
	}
	/**
	 * Asigna el valor al campo unidadMedidaTerreno.
	 * @param unidadMedidaTerreno el valor unidadMedidaTerreno a asignar
	 */
	public void setUnidadMedidaTerreno(Long unidadMedidaTerreno) {
		this.unidadMedidaTerreno = unidadMedidaTerreno;
	}
	/**
	 * Método de acceso al campo unidadMedidaConstruccion.
	 * @return El valor del campo unidadMedidaConstruccion
	 */
	public Long getUnidadMedidaConstruccion() {
		return unidadMedidaConstruccion;
	}
	/**
	 * Asigna el valor al campo unidadMedidaConstruccion.
	 * @param unidadMedidaConstruccion el valor unidadMedidaConstruccion a asignar
	 */
	public void setUnidadMedidaConstruccion(Long unidadMedidaConstruccion) {
		this.unidadMedidaConstruccion = unidadMedidaConstruccion;
	}
	
}

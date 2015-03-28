/**
* Nombre del Programa : InmuebleDTO.java
* Autor                            : Emigdio Hernández 
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/06/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la entidad Inmueble
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

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * DTO para la entidad inmueble
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class InmuebleDTO extends ObjetoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer superficie;
	Integer mConstruccion;
	ValorDTO tipoInmueble;
	ValorDTO tipoConstruccion;
	ValorDTO unidadMedidaTerreno;
	ValorDTO unidadMedidaConstruccion;
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
	public ValorDTO getTipoInmueble() {
		return tipoInmueble;
	}
	/**
	 * Asigna el valor al campo tipoInmueble.
	 * @param tipoInmueble el valor tipoInmueble a asignar
	 */
	public void setTipoInmueble(ValorDTO tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}
	/**
	 * Método de acceso al campo tipoConstruccion.
	 * @return El valor del campo tipoConstruccion
	 */
	public ValorDTO getTipoConstruccion() {
		return tipoConstruccion;
	}
	/**
	 * Asigna el valor al campo tipoConstruccion.
	 * @param tipoConstruccion el valor tipoConstruccion a asignar
	 */
	public void setTipoConstruccion(ValorDTO tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}
	/**
	 * Método de acceso al campo unidadMedidaTerreno.
	 * @return El valor del campo unidadMedidaTerreno
	 */
	public ValorDTO getUnidadMedidaTerreno() {
		return unidadMedidaTerreno;
	}
	/**
	 * Asigna el valor al campo unidadMedidaTerreno.
	 * @param unidadMedidaTerreno el valor unidadMedidaTerreno a asignar
	 */
	public void setUnidadMedidaTerreno(ValorDTO unidadMedidaTerreno) {
		this.unidadMedidaTerreno = unidadMedidaTerreno;
	}
	/**
	 * Método de acceso al campo unidadMedidaConstruccion.
	 * @return El valor del campo unidadMedidaConstruccion
	 */
	public ValorDTO getUnidadMedidaConstruccion() {
		return unidadMedidaConstruccion;
	}
	/**
	 * Asigna el valor al campo unidadMedidaConstruccion.
	 * @param unidadMedidaConstruccion el valor unidadMedidaConstruccion a asignar
	 */
	public void setUnidadMedidaConstruccion(ValorDTO unidadMedidaConstruccion) {
		this.unidadMedidaConstruccion = unidadMedidaConstruccion;
	}
	
}

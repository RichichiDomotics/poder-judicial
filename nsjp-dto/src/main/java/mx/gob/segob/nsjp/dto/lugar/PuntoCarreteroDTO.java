/**
* Nombre del Programa : PuntoCarreteroDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto PuntoCarretero 
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
package mx.gob.segob.nsjp.dto.lugar;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto PuntoCarretero.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class PuntoCarreteroDTO extends LugarDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112735386033125389L;
	
	private ValorDTO valorTipoCarretera;
	private String numCarretera;
	private String nombreCarretera;
	private Short kilometro;
	private String nombreParaje;
	private String nombreTramo;
	private String poblacionesVecinas;
	
	/**
	 * Método de acceso al campo valorTipoCarretera.
	 * @return El valor del campo valorTipoCarretera
	 */
	public ValorDTO getValorTipoCarretera() {
		return valorTipoCarretera;
	}
	/**
	 * Asigna el valor al campo valorTipoCarretera.
	 * @param valorTipoCarretera el valor valorTipoCarretera a asignar
	 */
	public void setValorTipoCarretera(ValorDTO valorTipoCarretera) {
		this.valorTipoCarretera = valorTipoCarretera;
	}
	/**
	 * Método de acceso al campo numCarretera.
	 * @return El valor del campo numCarretera
	 */
	public String getNumCarretera() {
		return numCarretera;
	}
	/**
	 * Asigna el valor al campo numCarretera.
	 * @param numCarretera el valor numCarretera a asignar
	 */
	public void setNumCarretera(String numCarretera) {
		this.numCarretera = numCarretera;
	}
	/**
	 * Método de acceso al campo nombreCarretera.
	 * @return El valor del campo nombreCarretera
	 */
	public String getNombreCarretera() {
		return nombreCarretera;
	}
	/**
	 * Asigna el valor al campo nombreCarretera.
	 * @param nombreCarretera el valor nombreCarretera a asignar
	 */
	public void setNombreCarretera(String nombreCarretera) {
		this.nombreCarretera = nombreCarretera;
	}
	/**
	 * Método de acceso al campo kilometro.
	 * @return El valor del campo kilometro
	 */
	public Short getKilometro() {
		return kilometro;
	}
	/**
	 * Asigna el valor al campo kilometro.
	 * @param kilometro el valor kilometro a asignar
	 */
	public void setKilometro(Short kilometro) {
		this.kilometro = kilometro;
	}
	/**
	 * Método de acceso al campo nombreParaje.
	 * @return El valor del campo nombreParaje
	 */
	public String getNombreParaje() {
		return nombreParaje;
	}
	/**
	 * Asigna el valor al campo nombreParaje.
	 * @param nombreParaje el valor nombreParaje a asignar
	 */
	public void setNombreParaje(String nombreParaje) {
		this.nombreParaje = nombreParaje;
	}
	/**
	 * Método de acceso al campo nombreTramo.
	 * @return El valor del campo nombreTramo
	 */
	public String getNombreTramo() {
		return nombreTramo;
	}
	/**
	 * Asigna el valor al campo nombreTramo.
	 * @param nombreTramo el valor nombreTramo a asignar
	 */
	public void setNombreTramo(String nombreTramo) {
		this.nombreTramo = nombreTramo;
	}
	/**
	 * Método de acceso al campo poblacionesVecinas.
	 * @return El valor del campo poblacionesVecinas
	 */
	public String getPoblacionesVecinas() {
		return poblacionesVecinas;
	}
	/**
	 * Asigna el valor al campo poblacionesVecinas.
	 * @param poblacionesVecinas el valor poblacionesVecinas a asignar
	 */
	public void setPoblacionesVecinas(String poblacionesVecinas) {
		this.poblacionesVecinas = poblacionesVecinas;
	}	

}

/**
* Nombre del Programa : CamposFormaDTO.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/06/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para transportar los campos configurables de una Forma
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
package mx.gob.segob.nsjp.dto.forma;

/**
 * DTO para transportar los campos configurables de una Forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class CamposFormaDTO {

	private Long camposFormaId;
    private String nombreNegocio;
    private String descripcion;
    private String rutaDato;
	/**
	 * Método de acceso al campo camposFormaId.
	 * @return El valor del campo camposFormaId
	 */
	public Long getCamposFormaId() {
		return camposFormaId;
	}
	/**
	 * Asigna el valor al campo camposFormaId.
	 * @param camposFormaId el valor camposFormaId a asignar
	 */
	public void setCamposFormaId(Long camposFormaId) {
		this.camposFormaId = camposFormaId;
	}
	/**
	 * Método de acceso al campo nombreNegocio.
	 * @return El valor del campo nombreNegocio
	 */
	public String getNombreNegocio() {
		return nombreNegocio;
	}
	/**
	 * Asigna el valor al campo nombreNegocio.
	 * @param nombreNegocio el valor nombreNegocio a asignar
	 */
	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Asigna el valor al campo descripcion.
	 * @param descripcion el valor descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Método de acceso al campo rutaDato.
	 * @return El valor del campo rutaDato
	 */
	public String getRutaDato() {
		return rutaDato;
	}
	/**
	 * Asigna el valor al campo rutaDato.
	 * @param rutaDato el valor rutaDato a asignar
	 */
	public void setRutaDato(String rutaDato) {
		this.rutaDato = rutaDato;
	} 
	
	
}

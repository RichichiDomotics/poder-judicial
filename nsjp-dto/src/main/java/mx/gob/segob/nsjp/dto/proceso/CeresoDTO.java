/**
* Nombre del Programa : CeresoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/01/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.proceso;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CeresoDTO {

	private Long id;
	private String cNombreCereso;
	/**
	 * Método de acceso al campo id.
	 * @return El valor del campo id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Asigna el valor al campo id.
	 * @param id el valor id a asignar
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Método de acceso al campo cNombreCereso.
	 * @return El valor del campo cNombreCereso
	 */
	public String getcNombreCereso() {
		return cNombreCereso;
	}
	/**
	 * Asigna el valor al campo cNombreCereso.
	 * @param cNombreCereso el valor cNombreCereso a asignar
	 */
	public void setcNombreCereso(String cNombreCereso) {
		this.cNombreCereso = cNombreCereso;
	}
	
}
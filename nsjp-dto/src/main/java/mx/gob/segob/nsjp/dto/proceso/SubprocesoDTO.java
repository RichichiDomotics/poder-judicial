/**
* Nombre del Programa : SubprocesoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class SubprocesoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3079303761153739384L;
	private Long id;
	private ProcesoDTO procesoDTO;
	private String cNombre;
	private String cDescripcion;
	private String cForward;
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
	 * Método de acceso al campo procesoDTO.
	 * @return El valor del campo procesoDTO
	 */
	public ProcesoDTO getProcesoDTO() {
		return procesoDTO;
	}
	/**
	 * Asigna el valor al campo procesoDTO.
	 * @param procesoDTO el valor procesoDTO a asignar
	 */
	public void setProcesoDTO(ProcesoDTO procesoDTO) {
		this.procesoDTO = procesoDTO;
	}
	/**
	 * Método de acceso al campo cNombre.
	 * @return El valor del campo cNombre
	 */
	public String getcNombre() {
		return cNombre;
	}
	/**
	 * Asigna el valor al campo cNombre.
	 * @param cNombre el valor cNombre a asignar
	 */
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	/**
	 * Método de acceso al campo cDescripcion.
	 * @return El valor del campo cDescripcion
	 */
	public String getcDescripcion() {
		return cDescripcion;
	}
	/**
	 * Asigna el valor al campo cDescripcion.
	 * @param cDescripcion el valor cDescripcion a asignar
	 */
	public void setcDescripcion(String cDescripcion) {
		this.cDescripcion = cDescripcion;
	}
	/**
	 * Método de acceso al campo cForward.
	 * @return El valor del campo cForward
	 */
	public String getcForward() {
		return cForward;
}
	/**
	 * Asigna el valor al campo cForward.
	 * @param cForward el valor cForward a asignar
	 */
	public void setcForward(String cForward) {
		this.cForward = cForward;
	}
		
}
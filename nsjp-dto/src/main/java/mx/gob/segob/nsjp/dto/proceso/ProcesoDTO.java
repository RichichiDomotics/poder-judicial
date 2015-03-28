/**
* Nombre del Programa : ProcesoDTO.java
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

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ProcesoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7750606708893001834L;

	private Long id;
	private String cNombre;
	private String cDescripcion;
	private List<SubprocesoDTO> lstSubprocesos;
	private List<RolDTO> lstRoles;
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
	 * Método de acceso al campo lstSubprocesos.
	 * @return El valor del campo lstSubprocesos
	 */
	public List<SubprocesoDTO> getLstSubprocesos() {
		return lstSubprocesos;
	}
	/**
	 * Asigna el valor al campo lstSubprocesos.
	 * @param lstSubprocesos el valor lstSubprocesos a asignar
	 */
	public void setLstSubprocesos(List<SubprocesoDTO> lstSubprocesos) {
		this.lstSubprocesos = lstSubprocesos;
	}
	/**
	 * Método de acceso al campo lstRoles.
	 * @return El valor del campo lstRoles
	 */
	public List<RolDTO> getLstRoles() {
		return lstRoles;
	}
	/**
	 * Asigna el valor al campo lstRoles.
	 * @param lstRoles el valor lstRoles a asignar
	 */
	public void setLstRoles(List<RolDTO> lstRoles) {
		this.lstRoles = lstRoles;
	}
}

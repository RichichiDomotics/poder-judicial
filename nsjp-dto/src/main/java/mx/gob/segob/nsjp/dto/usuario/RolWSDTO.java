/**
* Nombre del Programa 		: RolWSDTO.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 10 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: DTO para web service de la entidad rol.
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.usuario;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class RolWSDTO extends GenericWSDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 340243548171044601L;

	private Long rolId;
	private String nombreRol;
	private String descripcionRol;
	
	/**
	 * Método de acceso al campo rolId.
	 * @return El valor del campo rolId
	 */
	public Long getRolId() {
		return rolId;
	}
	
	/**
	 * Asigna el valor al campo rolId.
	 * @param rolId el valor rolId a asignar
	 */
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	
	/**
	 * Método de acceso al campo nombreRol.
	 * @return El valor del campo nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	
	/**
	 * Asigna el valor al campo nombreRol.
	 * @param nombreRol el valor nombreRol a asignar
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	/**
	 * Método de acceso al campo descripcionRol.
	 * @return El valor del campo descripcionRol
	 */
	public String getDescripcionRol() {
		return descripcionRol;
	}
	
	/**
	 * Asigna el valor al campo descripcionRol.
	 * @param descripcionRol el valor descripcionRol a asignar
	 */
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	
}

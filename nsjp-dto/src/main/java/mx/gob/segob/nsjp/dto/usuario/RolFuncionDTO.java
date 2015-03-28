/**
* Nombre del Programa : RolFuncionDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de transferencia para las Funciones que tiene asignadas un Usuario
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
package mx.gob.segob.nsjp.dto.usuario;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferencia para las Funciones que tiene asignadas un Usuario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class RolFuncionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8704540237342303875L;

	private Long id;
	private FuncionDTO funcion;
	private RolDTO rol;
	
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
	 * Método de acceso al campo funcion.
	 * @return El valor del campo funcion
	 */
	public FuncionDTO getFuncion() {
		return funcion;
	}
	/**
	 * Asigna el valor al campo funcion.
	 * @param funcion el valor funcion a asignar
	 */
	public void setFuncion(FuncionDTO funcion) {
		this.funcion = funcion;
	}
	/**
	 * Método de acceso al campo rol.
	 * @return El valor del campo rol
	 */
	public RolDTO getRol() {
		return rol;
	}
	/**
	 * Asigna el valor al campo rol.
	 * @param rol el valor rol a asignar
	 */
	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
	
	
}

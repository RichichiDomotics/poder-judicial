/**
* Nombre del Programa : CatCategoriaCursoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/02/2012
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
package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatCategoriaCursoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -712300990138811814L;

	private Long catCategoriaCursoId;
	
	private String descripcion;

	/**
	 * Método de acceso al campo catCategoriaCursoId.
	 * @return El valor del campo catCategoriaCursoId
	 */
	public Long getCatCategoriaCursoId() {
		return catCategoriaCursoId;
	}

	/**
	 * Asigna el valor al campo catCategoriaCursoId.
	 * @param catCategoriaCursoId el valor catCategoriaCursoId a asignar
	 */
	public void setCatCategoriaCursoId(Long catCategoriaCursoId) {
		this.catCategoriaCursoId = catCategoriaCursoId;
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

}

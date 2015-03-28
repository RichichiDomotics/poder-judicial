/**
* Nombre del Programa : CategoriaElementoDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la consulta del Catálago de Categoría de Elementos
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
package mx.gob.segob.nsjp.dto.bitacora;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para la consulta del Catálago de Categoría de Elementos
 * @version 1.0
 * @author GustavoBP
 */
public class CategoriaElementoDTO extends GenericDTO {

	private Long categoriaElementoId;
	private String nombre;

	public CategoriaElementoDTO(){
	}

	/**
	 * @param categoriaElementoId
	 */
	public CategoriaElementoDTO(Long categoriaElementoId) {
		super();
		this.categoriaElementoId = categoriaElementoId;
	}

	/**
	 * Método de acceso al campo categoriaElementoId.
	 * @return El valor del campo categoriaElementoId
	 */
	public Long getCategoriaElementoId() {
		return categoriaElementoId;
	}

	/**
	 * Asigna el valor al campo categoriaElementoId.
	 * @param categoriaElementoId el valor categoriaElementoId a asignar
	 */
	public void setCategoriaElementoId(Long categoriaElementoId) {
		this.categoriaElementoId = categoriaElementoId;
	}

	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

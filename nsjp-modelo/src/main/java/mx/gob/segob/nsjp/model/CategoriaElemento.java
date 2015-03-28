/**
* Nombre del Programa : CategoríaElemento.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Catálogo de las Categorias de Elementos.
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
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Catálogo de las Categorias de Elementos
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Entity
@Table(name = "CategoriaElemento")
public class CategoriaElemento {

	private Long categoriaElementoId;
	private String nombre;
	
	public CategoriaElemento(){
	}

	/**
	 * @param categoriaElementoId
	 */
	public CategoriaElemento(Long categoriaElementoId) {
		this.categoriaElementoId = categoriaElementoId;
	}

	@Id
	@Column(name = "CategoriaElemento_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCategoriaElementoId() {
		return categoriaElementoId;
	}

	public void setCategoriaElementoId(Long categoriaElementoId) {
		this.categoriaElementoId = categoriaElementoId;
	}

	@Column(name = "cNombre", length = 150)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

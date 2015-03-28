package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

// 1. Externo
// 2, Interno
/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoTrabajoExternoDTO extends GenericDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5104932076628157313L;

	private Long catTipoExternoId;
	
	private	String descripcion;

	/**
	 * Método de acceso al campo catTipoExternoId.
	 * @return El valor del campo catTipoExternoId
	 */
	public Long getCatTipoExternoId() {
		return catTipoExternoId;
	}

	/**
	 * Asigna el valor al campo catTipoExternoId.
	 * @param catTipoExternoId el valor catTipoExternoId a asignar
	 */
	public void setCatTipoExternoId(Long catTipoExternoId) {
		this.catTipoExternoId = catTipoExternoId;
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
/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoClasificacionDTO extends GenericDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4770650312382769161L;
	private Long catDelitoClasificacionId;
    private String descripcion;
    
	public CatDelitoClasificacionDTO(Long catDelitoClasificacionId,
			String descripcion) {
		super();
		this.catDelitoClasificacionId = catDelitoClasificacionId;
		this.descripcion = descripcion;
	}
	public CatDelitoClasificacionDTO() {
		super();
	}
	public Long getCatDelitoClasificacionId() {
		return catDelitoClasificacionId;
	}
	public void setCatDelitoClasificacionId(Long catDelitoClasificacionId) {
		this.catDelitoClasificacionId = catDelitoClasificacionId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}

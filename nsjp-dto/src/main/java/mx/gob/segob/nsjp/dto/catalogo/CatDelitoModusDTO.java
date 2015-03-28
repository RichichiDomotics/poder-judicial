/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoModusDTO extends GenericDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7667999814476609115L;
	private Long catDelitoModusId;
	private String claveModus;
    private String descripcion;
    
	public CatDelitoModusDTO(Long catDelitoModusId, String claveModus,
			String descripcion) {
		super();
		this.catDelitoModusId = catDelitoModusId;
		this.claveModus = claveModus;
		this.descripcion = descripcion;
	}

	public CatDelitoModusDTO() {
		super();
	}

	public Long getCatDelitoModusId() {
		return catDelitoModusId;
	}
	public void setCatDelitoModusId(Long catDelitoModusId) {
		this.catDelitoModusId = catDelitoModusId;
	}

	public String getClaveModus() {
		return claveModus;
	}
	public void setClaveModus(String claveModus) {
		this.claveModus = claveModus;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

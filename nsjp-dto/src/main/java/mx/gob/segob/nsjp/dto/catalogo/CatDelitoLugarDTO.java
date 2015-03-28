/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoLugarDTO extends GenericDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6973761940852877272L;
	private Long catDelitoLugarId;
	private String claveLugar;
    private String descripcion;
    
	public CatDelitoLugarDTO(Long catDelitoLugarId, String claveLugar,
			String descripcion) {
		super();
		this.catDelitoLugarId = catDelitoLugarId;
		this.claveLugar = claveLugar;
		this.descripcion = descripcion;
	}

	public CatDelitoLugarDTO() {
		super();
	}

	public Long getCatDelitoLugarId() {
		return catDelitoLugarId;
	}
	public void setCatDelitoLugarId(Long catDelitoLugarId) {
		this.catDelitoLugarId = catDelitoLugarId;
	}

	public String getClaveLugar() {
		return claveLugar;
	}
	public void setClaveLugar(String claveLugar) {
		this.claveLugar = claveLugar;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

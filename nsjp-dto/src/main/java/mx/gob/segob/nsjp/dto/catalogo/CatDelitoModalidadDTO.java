/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoModalidadDTO extends GenericDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1125030639076305735L;
	private Long catDelitoModalidadId;
	private String claveModalidad;
    private String descripcion;
    
	public CatDelitoModalidadDTO(Long catDelitoModalidadId, String claveModalidad,
			String descripcion) {
		super();
		this.catDelitoModalidadId = catDelitoModalidadId;
		this.claveModalidad = claveModalidad;
		this.descripcion = descripcion;
	}

	public CatDelitoModalidadDTO() {
		super();
	}

	public Long getCatDelitoModalidadId() {
		return catDelitoModalidadId;
	}
	public void setCatDelitoModalidadId(Long catDelitoModalidadId) {
		this.catDelitoModalidadId = catDelitoModalidadId;
	}

	public String getClaveModalidad() {
		return claveModalidad;
	}
	public void setClaveModalidad(String claveModalidad) {
		this.claveModalidad = claveModalidad;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

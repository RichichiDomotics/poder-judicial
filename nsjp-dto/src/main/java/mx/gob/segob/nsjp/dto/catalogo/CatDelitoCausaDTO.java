/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author EduardoAD
 *
 */
public class CatDelitoCausaDTO extends GenericDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1653752585170274970L;
	private Long catDelitoCausaId;
	private String claveCausa;
    private String descripcion;
    
	public CatDelitoCausaDTO(Long catDelitoCausaId, String claveCausa,
			String descripcion) {
		super();
		this.catDelitoCausaId = catDelitoCausaId;
		this.claveCausa = claveCausa;
		this.descripcion = descripcion;
	}

	public CatDelitoCausaDTO() {
		super();
	}

	public Long getCatDelitoCausaId() {
		return catDelitoCausaId;
	}
	public void setCatDelitoCausaId(Long catDelitoCausaId) {
		this.catDelitoCausaId = catDelitoCausaId;
	}

	public String getClaveCausa() {
		return claveCausa;
	}
	public void setClaveCausa(String claveCausa) {
		this.claveCausa = claveCausa;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

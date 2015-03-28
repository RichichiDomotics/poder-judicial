/**
 * 
 */
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author EduardoAD
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "catDelitoCausa")
public class CatDelitoCausa implements java.io.Serializable{
	// Fields
	private Long catDelitoCausaId;
	private String claveCausa;
    private String descripcion;
    
    // Constructors
    /** default constructor */
	public CatDelitoCausa() {
	}
	/** minimal constructor */

	public CatDelitoCausa(Long catDelitoCausaId, String claveCausa,
			String descripcion) {
		super();
		this.catDelitoCausaId = catDelitoCausaId;
		this.claveCausa = claveCausa;
		this.descripcion = descripcion;
	}
	
	// Getters - Setters
	/**
	 * Método de acceso al campo catDelitoCausaId.
	 * @return El valor del campo catDelitoCausaId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDelitoCausa_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoCausaId() {
		return catDelitoCausaId;
	}
	public void setCatDelitoCausaId(Long catDelitoCausaId) {
		this.catDelitoCausaId = catDelitoCausaId;
	}
	
	@Column(name = "cClaveDelitoCausa", nullable = false, length = 10)
	public String getClaveCausa() {
		return claveCausa;
	}
	public void setClaveCausa(String claveCausa) {
		this.claveCausa = claveCausa;
	}
	
	@Column(name = "cDescripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

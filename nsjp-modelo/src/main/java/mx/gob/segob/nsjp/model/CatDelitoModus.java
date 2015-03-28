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
@Table(name = "catDelitoModus")
public class CatDelitoModus implements java.io.Serializable{
	// Fields
	private Long catDelitoModusId;
	private String claveModus;
    private String descripcion;
    
    // Constructors
    /** default constructor */
	public CatDelitoModus() {
	}
	/** minimal constructor */

	public CatDelitoModus(Long catDelitoModusId, String claveModus,
			String descripcion) {
		super();
		this.catDelitoModusId = catDelitoModusId;
		this.claveModus = claveModus;
		this.descripcion = descripcion;
	}
	
	// Getters - Setters
	/**
	 * Método de acceso al campo catDelitoModusId.
	 * @return El valor del campo catDelitoModusId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDelitoModus_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoModusId() {
		return catDelitoModusId;
	}
	public void setCatDelitoModusId(Long catDelitoModusId) {
		this.catDelitoModusId = catDelitoModusId;
	}
	
	@Column(name = "cClaveDelitoModus", nullable = false, length = 10)
	public String getClaveModus() {
		return claveModus;
	}
	public void setClaveModus(String claveModus) {
		this.claveModus = claveModus;
	}
	
	@Column(name = "cDescripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

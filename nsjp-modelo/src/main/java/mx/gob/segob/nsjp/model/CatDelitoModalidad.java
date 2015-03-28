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
@Table(name = "catDelitoModalidad")
public class CatDelitoModalidad implements java.io.Serializable{
	// Fields
	private Long catDelitoModalidadId;
	private String claveModalidad;
    private String descripcion;
    
    // Constructors
    /** default constructor */
	public CatDelitoModalidad() {
	}
	/** minimal constructor */

	public CatDelitoModalidad(Long catDelitoModalidadId, String claveModalidad,
			String descripcion) {
		super();
		this.catDelitoModalidadId = catDelitoModalidadId;
		this.claveModalidad = claveModalidad;
		this.descripcion = descripcion;
	}
	
	// Getters - Setters
	/**
	 * Método de acceso al campo catDelitoModalidadId.
	 * @return El valor del campo catDelitoModalidadId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDelitoModalidad_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoModalidadId() {
		return catDelitoModalidadId;
	}
	public void setCatDelitoModalidadId(Long catDelitoModalidadId) {
		this.catDelitoModalidadId = catDelitoModalidadId;
	}
	
	@Column(name = "cClaveDelitoModalidad", nullable = false, length = 10)
	public String getClaveModalidad() {
		return claveModalidad;
	}
	public void setClaveModalidad(String claveModalidad) {
		this.claveModalidad = claveModalidad;
	}
	
	@Column(name = "cDescripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

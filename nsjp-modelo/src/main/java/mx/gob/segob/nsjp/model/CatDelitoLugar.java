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
@Table(name = "catDelitoLugar")
public class CatDelitoLugar implements java.io.Serializable{
	// Fields
	private Long catDelitoLugarId;
	private String claveLugar;
    private String descripcion;
    
    // Constructors
    /** default constructor */
	public CatDelitoLugar() {
	}
	/** minimal constructor */

	public CatDelitoLugar(Long catDelitoLugarId, String claveLugar,
			String descripcion) {
		super();
		this.catDelitoLugarId = catDelitoLugarId;
		this.claveLugar = claveLugar;
		this.descripcion = descripcion;
	}
	
	// Getters - Setters
	/**
	 * Método de acceso al campo catDelitoLugarId.
	 * @return El valor del campo catDelitoLugarId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDelitoLugar_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoLugarId() {
		return catDelitoLugarId;
	}
	public void setCatDelitoLugarId(Long catDelitoLugarId) {
		this.catDelitoLugarId = catDelitoLugarId;
	}
	
	@Column(name = "cClaveDelitoLugar", nullable = false, length = 10)
	public String getClaveLugar() {
		return claveLugar;
	}
	public void setClaveLugar(String claveLugar) {
		this.claveLugar = claveLugar;
	}
	
	@Column(name = "cDescripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

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
@Table(name = "catDelitoClasificacion")
public class CatDelitoClasificacion implements java.io.Serializable{
	// Fields

	private Long catDelitoClasificacionId;
    private String descripcion;
    
    // Constructors

    /** default constructor */
    public CatDelitoClasificacion() {
    }

    /** minimal constructor */
    public CatDelitoClasificacion(Long catDelitoClasificacion_id, String cDescripcion) {
        this.catDelitoClasificacionId = catDelitoClasificacion_id;
        this.descripcion = cDescripcion;
    }

	/**
	 * Método de acceso al campo catDelitoClasificacionId.
	 * @return El valor del campo catDelitoClasificacionId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catDelitoClasificacion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoClasificacionId() {
		return catDelitoClasificacionId;
	}
	
	/**
	 * Asigna el valor al campo catDelitoClasificacionId.
	 * @param catDelitoId el valor catDelitoClasificacionId a asignar
	 */
	public void setCatDelitoClasificacionId(Long catDelitoClasificacionId) {
		this.catDelitoClasificacionId = catDelitoClasificacionId;
	}
	
	@Column(name = "cDescripcion", nullable = false, length = 150)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String cdescripcion) {
        this.descripcion = cdescripcion;
    }
}

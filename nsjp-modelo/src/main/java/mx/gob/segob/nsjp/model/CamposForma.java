package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CamposForma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CamposForma")
public class CamposForma implements java.io.Serializable {

    // Fields
    private Long CamposFormaId;
    private String nombreNegocio;
    private String descripcion;
    private String rutaDato; 


    // Constructors
    
    /** default constructor */
    public CamposForma() {
    }

	// Property accessors
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CamposForma_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCamposFormaId() {
		return this.CamposFormaId;
	}

	public void setCamposFormaId(Long CamposFormaId) {
		this.CamposFormaId = CamposFormaId;
	}
	
    @Column(name = "cNombreNegocio", length = 100)
	public String getNombreNegocio() {
		return nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	
    @Column(name = "cDescripcion", length = 100)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "cRutaDato", length = 200)
	public String getRutaDato() {
		return rutaDato;
	}

	public void setRutaDato(String rutaDato) {
		this.rutaDato = rutaDato;
	}	

}

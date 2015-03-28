package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Persona entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DefensorPrivado")
public class DefensorPrivado implements java.io.Serializable {

	private static final long serialVersionUID = -2683263987219416539L;
	// Fields
	private Long defensorPrivadoId;
    private String nombreDefensor;
    private String apellidoPaternoDefensor;
    private String apellidoMaternoDefensor;
    private String cedulaDefensor;
    
    private Set<MedioDeContacto> medioDeContactos = new HashSet<MedioDeContacto>(0);


    // Constructors

    /** default constructor */
    public DefensorPrivado() {
    }

    /**
	 * Método de acceso al campo defensorPrivadoId.
	 * @return El valor del campo defensorPrivadoId
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DefensorPrivado_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getDefensorPrivadoId() {
		return defensorPrivadoId;
	}



	/**
	 * Asigna el valor al campo defensorPrivadoId.
	 * @param defensorPrivadoId el valor defensorPrivadoId a asignar
	 */
	public void setDefensorPrivadoId(Long defensorPrivadoId) {
		this.defensorPrivadoId = defensorPrivadoId;
	}



	/**
	 * Método de acceso al campo nombreDefensor.
	 * @return El valor del campo nombreDefensor
	 */
	@Column(name = "cNombreDefensor", nullable = false, length = 50)
	public String getNombreDefensor() {
		return nombreDefensor;
	}



	/**
	 * Asigna el valor al campo nombreDefensor.
	 * @param nombreDefensor el valor nombreDefensor a asignar
	 */
	public void setNombreDefensor(String nombreDefensor) {
		this.nombreDefensor = nombreDefensor;
	}



	/**
	 * Método de acceso al campo apellidoPaternoDefensor.
	 * @return El valor del campo apellidoPaternoDefensor
	 */
	@Column(name = "cApellidoPaternoDefensor", nullable = false, length = 50)
	public String getApellidoPaternoDefensor() {
		return apellidoPaternoDefensor;
	}



	/**
	 * Asigna el valor al campo apellidoPaternoDefensor.
	 * @param apellidoPaternoDefensor el valor apellidoPaternoDefensor a asignar
	 */
	public void setApellidoPaternoDefensor(String apellidoPaternoDefensor) {
		this.apellidoPaternoDefensor = apellidoPaternoDefensor;
	}



	/**
	 * Método de acceso al campo apellidoMaternoDefensor.
	 * @return El valor del campo apellidoMaternoDefensor
	 */
	@Column(name = "cApellidoMaternoDefensor", nullable = false, length = 50)
	public String getApellidoMaternoDefensor() {
		return apellidoMaternoDefensor;
	}



	/**
	 * Asigna el valor al campo apellidoMaternoDefensor.
	 * @param apellidoMaternoDefensor el valor apellidoMaternoDefensor a asignar
	 */
	public void setApellidoMaternoDefensor(String apellidoMaternoDefensor) {
		this.apellidoMaternoDefensor = apellidoMaternoDefensor;
	}



	/**
	 * Método de acceso al campo cedulaDefensor.
	 * @return El valor del campo cedulaDefensor
	 */
	@Column(name = "cCedulaDefensor", nullable = false, length = 50)
	public String getCedulaDefensor() {
		return cedulaDefensor;
	}



	/**
	 * Asigna el valor al campo cedulaDefensor.
	 * @param cedulaDefensor el valor cedulaDefensor a asignar
	 */
	public void setCedulaDefensor(String cedulaDefensor) {
		this.cedulaDefensor = cedulaDefensor;
	}



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "defensorPrivado")
    public Set<MedioDeContacto> getMedioDeContactos() {
        return this.medioDeContactos;
    }

    public void setMedioDeContactos(Set<MedioDeContacto> medioDeContactos) {
        this.medioDeContactos = medioDeContactos;
    }
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Implicado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Implicado")
@Inheritance(strategy = InheritanceType.JOINED)
public class Implicado implements java.io.Serializable {

    // Fields

    private Long implicadoId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Set<QuejaCiudadana> quejaCiudadanasForAfectadoId = new HashSet<QuejaCiudadana>(
            0);
    private Set<QuejaCiudadana> quejaCiudadanasForDenunciadoId = new HashSet<QuejaCiudadana>(
            0);
    private Set<QuejaCiudadana> quejaCiudadanasForQuejosoId = new HashSet<QuejaCiudadana>(
            0);
    private Set<MedioDeContacto> medioDeContactos = new HashSet<MedioDeContacto>(
            0);
    private Valor tipoCalidad;
    private Set<AvisoHechoDelictivo> avisoHechoDelictivos = new HashSet<AvisoHechoDelictivo>(0);
    
    // Constructors

    /** default constructor */
    public Implicado() {
    }

    /** minimal constructor */
    public Implicado(Long implicadoId) {
        this.implicadoId = implicadoId;
    }

    /** full constructor */
    public Implicado(Long implicadoId, String cnombre, String capellidoPaterno,
            String capellidoMaterno,
            Set<QuejaCiudadana> quejaCiudadanasForAfectadoId,
            Set<QuejaCiudadana> quejaCiudadanasForDenunciadoId,
            Set<QuejaCiudadana> quejaCiudadanasForQuejosoId) {
        this.implicadoId = implicadoId;
        this.nombre = cnombre;
        this.apellidoPaterno = capellidoPaterno;
        this.apellidoMaterno = capellidoMaterno;
        this.quejaCiudadanasForAfectadoId = quejaCiudadanasForAfectadoId;
        this.quejaCiudadanasForDenunciadoId = quejaCiudadanasForDenunciadoId;
        this.quejaCiudadanasForQuejosoId = quejaCiudadanasForQuejosoId;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Implicado_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getImplicadoId() {
        return this.implicadoId;
    }

    public void setImplicadoId(Long implicadoId) {
        this.implicadoId = implicadoId;
    }

    @Column(name = "cNombre", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String cnombre) {
        this.nombre = cnombre;
    }

    @Column(name = "cApellidoPaterno", length = 50)
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String capellidoPaterno) {
        this.apellidoPaterno = capellidoPaterno;
    }

    @Column(name = "cApellidoMaterno", length = 50)
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String capellidoMaterno) {
        this.apellidoMaterno = capellidoMaterno;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "afectado")
    public Set<QuejaCiudadana> getQuejaCiudadanasForAfectadoId() {
        return this.quejaCiudadanasForAfectadoId;
    }

    public void setQuejaCiudadanasForAfectadoId(
            Set<QuejaCiudadana> quejaCiudadanasForAfectadoId) {
        this.quejaCiudadanasForAfectadoId = quejaCiudadanasForAfectadoId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "denunciado")
    public Set<QuejaCiudadana> getQuejaCiudadanasForDenunciadoId() {
        return this.quejaCiudadanasForDenunciadoId;
    }

    public void setQuejaCiudadanasForDenunciadoId(
            Set<QuejaCiudadana> quejaCiudadanasForDenunciadoId) {
        this.quejaCiudadanasForDenunciadoId = quejaCiudadanasForDenunciadoId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quejoso")
    public Set<QuejaCiudadana> getQuejaCiudadanasForQuejosoId() {
        return this.quejaCiudadanasForQuejosoId;
    }

    public void setQuejaCiudadanasForQuejosoId(
            Set<QuejaCiudadana> quejaCiudadanasForQuejosoId) {
        this.quejaCiudadanasForQuejosoId = quejaCiudadanasForQuejosoId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "implicado")
    public Set<MedioDeContacto> getMedioDeContactos() {
        return medioDeContactos;
    }

    public void setMedioDeContactos(Set<MedioDeContacto> medioDeContactos) {
        this.medioDeContactos = medioDeContactos;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoCalidad_val", nullable = false)
    public Valor getTipoCalidad() {
        return this.tipoCalidad;
    }

    public void setTipoCalidad(Valor valor) {
        this.tipoCalidad = valor;
    }
    
    /**
	 * @return the avisoHechoDelictivos
	 */
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "implicado")
	public Set<AvisoHechoDelictivo> getAvisoHechoDelictivos() {
		return avisoHechoDelictivos;
	}

	/**
	 * @param avisoHechoDelictivos the avisoHechoDelictivos to set
	 */
	public void setAvisoHechoDelictivos(
			Set<AvisoHechoDelictivo> avisoHechoDelictivos) {
		this.avisoHechoDelictivos = avisoHechoDelictivos;
	}
}
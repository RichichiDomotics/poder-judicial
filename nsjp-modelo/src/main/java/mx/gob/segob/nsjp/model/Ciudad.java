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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ciudad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Ciudad")
public class Ciudad implements java.io.Serializable {

    // Fields

    private Long ciudadId;
    private EntidadFederativa entidadFederativa;
    private String abreviacion;
    private String nombreCiudad;
    private Set<Asentamiento> asentamientos = new HashSet<Asentamiento>(0);

    // Constructors

    /** default constructor */
    public Ciudad() {
    }

    public Ciudad(Long idCd, String nombre) {
        this.ciudadId = idCd;
        this.nombreCiudad = nombre;
    }
    /**
     * 
     * @param ciudadId
     */
    public Ciudad(Long ciudadId) {
    	this.ciudadId = ciudadId;
	}

    
	// Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ciudad_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getCiudadId() {
        return this.ciudadId;
    }

    public void setCiudadId(Long ciudadId) {
        this.ciudadId = ciudadId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntidadFederativa_id", nullable = false)
    public EntidadFederativa getEntidadFederativa() {
        return this.entidadFederativa;
    }

    public void setEntidadFederativa(EntidadFederativa valor) {
        this.entidadFederativa = valor;
    }

    @Column(name = "cAbreviacion", length = 10)
    public String getAbreviacion() {
        return this.abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    @Column(name = "cNombreCiudad", length = 50)
    public String getNombreCiudad() {
        return this.nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ciudad")
    public Set<Asentamiento> getAsentamientos() {
        return this.asentamientos;
    }

    public void setAsentamientos(Set<Asentamiento> asentamientos) {
        this.asentamientos = asentamientos;
    }

}

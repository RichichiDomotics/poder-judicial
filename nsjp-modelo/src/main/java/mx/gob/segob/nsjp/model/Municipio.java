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
 * Municipio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Municipio")
public class Municipio implements java.io.Serializable {

    // Fields

    private Long municipioId;
    private EntidadFederativa entidadFederativa;
    private String nombreMunicipio;
    private Set<Asentamiento> asentamientos = new HashSet<Asentamiento>(0);

    // Constructors

    /** default constructor */
    public Municipio() {
    }
    public Municipio(Long idMpio, String nombre) {
        this.municipioId = idMpio;
        this.nombreMunicipio = nombre;
    }
    /**
     * 
     * @param municipioId
     */
    public Municipio(Long municipioId) {
    	this.municipioId = municipioId;
	}
	
    
    
    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Municipio_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getMunicipioId() {
        return this.municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntidadFederativa_id", nullable = false)
    public EntidadFederativa getEntidadFederativa() {
        return this.entidadFederativa;
    }

    public void setEntidadFederativa(EntidadFederativa valor) {
        this.entidadFederativa = valor;
    }

    @Column(name = "cNombreMunicipio", length = 50)
    public String getNombreMunicipio() {
        return this.nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "municipio")
    public Set<Asentamiento> getAsentamientos() {
        return this.asentamientos;
    }

    public void setAsentamientos(Set<Asentamiento> asentamientos) {
        this.asentamientos = asentamientos;
    }

}

package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Asentamiento entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Asentamiento")
public class Asentamiento implements java.io.Serializable {

    // Fields

    private Long asentamientoId;
    private Municipio municipio;
    private CatTipoAsentamiento catTipoAsentamiento;
    private Ciudad ciudad;
    private String codigoPostal;
    private String nombreAsentamiento;
    private Set<Domicilio> domicilios = new HashSet<Domicilio>(0);

    // Constructors

    /** default constructor */
    public Asentamiento() {
    }
    /**
     * 
     * @param asentamientoId2
     */
    public Asentamiento(Long asentamientoId2) {
        this.asentamientoId = asentamientoId2;
    }

    /**
     * 
     * @param asentamientoId2
     */
    public Asentamiento(Long asentamientoId2, String nombre) {
        this.asentamientoId = asentamientoId2;
        this.nombreAsentamiento =  nombre;
    }

    
    // Property accessors
    @Id
    @Column(name = "Asentamiento_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getAsentamientoId() {
        return this.asentamientoId;
    }

    public void setAsentamientoId(Long asentamientoId) {
        this.asentamientoId = asentamientoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Municipio_id", nullable = false)
    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ciudad_id")
    public Ciudad getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "cCodigoPostal", length = 10)
    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Column(name = "cNombreAsentamiento", length = 100)
    public String getNombreAsentamiento() {
        return this.nombreAsentamiento;
    }

    public void setNombreAsentamiento(String nombreAsentamiento) {
        this.nombreAsentamiento = nombreAsentamiento;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "asentamiento")
    public Set<Domicilio> getDomicilios() {
        return this.domicilios;
    }

    public void setDomicilios(Set<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CatTipoAsentamiento_id", nullable = false)
    public CatTipoAsentamiento getCatTipoAsentamiento() {
        return this.catTipoAsentamiento;
    }

    public void setCatTipoAsentamiento(CatTipoAsentamiento catTipoAsentamiento) {
        this.catTipoAsentamiento = catTipoAsentamiento;
    }
}

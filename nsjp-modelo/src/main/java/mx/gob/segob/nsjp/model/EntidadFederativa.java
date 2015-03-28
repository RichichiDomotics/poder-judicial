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
 * EntidadFederativa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EntidadFederativa")
public class EntidadFederativa implements java.io.Serializable {

    // Fields

    private Long entidadFederativaId;
    private Valor pais;
    private String nombre;
    private String abreviacion;
    private String zonaGeografica;
    private Set<Municipio> municipios = new HashSet<Municipio>(0);
    private String monograma;

    // Constructors

    /** default constructor */
    public EntidadFederativa() {
    }
    
    public EntidadFederativa(Long entidadFederativaId) {	
		this.entidadFederativaId = entidadFederativaId;
	}



	/** minimal constructor */
    public EntidadFederativa(Long entidadFederativaId, Valor valor,
            String cnombre) {
        this.entidadFederativaId = entidadFederativaId;
        this.pais = valor;
        this.nombre = cnombre;
    }

    /** full constructor */
    public EntidadFederativa(Long entidadFederativaId, String cnombre) {
        this.entidadFederativaId = entidadFederativaId;
        this.nombre = cnombre;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntidadFederativa_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getEntidadFederativaId() {
        return this.entidadFederativaId;
    }

    public void setEntidadFederativaId(Long entidadFederativaId) {
        this.entidadFederativaId = entidadFederativaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_val", nullable = false)
    public Valor getPais() {
        return this.pais;
    }

    public void setPais(Valor valor) {
        this.pais = valor;
    }

    @Column(name = "cNombre", nullable = false, length = 20)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String cnombre) {
        this.nombre = cnombre;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entidadFederativa")
    public Set<Municipio> getMunicipios() {
        return this.municipios;
    }

    public void setMunicipios(Set<Municipio> municipios) {
        this.municipios = municipios;
    }

    /**
     * Método de acceso al campo abreviacion.
     * 
     * @return El valor del campo abreviacion
     */
    @Column(name = "cAbreviacion", nullable = true, length = 10)
    public String getAbreviacion() {
        return abreviacion;
    }

    /**
     * Asigna el valor al campo abreviacion.
     * 
     * @param abreviacion
     *            el valor abreviacion a asignar
     */
    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    /**
     * Método de acceso al campo zonaGeografica.
     * 
     * @return El valor del campo zonaGeografica
     */
    @Column(name = "cZonaGeografica", nullable = true, length = 20)
    public String getZonaGeografica() {
        return zonaGeografica;
    }

    /**
     * Asigna el valor al campo zonaGeografica.
     * 
     * @param zonaGeografica
     *            el valor zonaGeografica a asignar
     */
  
    public void setZonaGeografica(String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    /**
     * Método de acceso al campo monograma.
     * @return El valor del campo monograma
     */
    @Column(name = "cMonograma", nullable = true, length = 3)
    public String getMonograma() {
        return monograma;
    }

    /**
     * Asigna el valor al campo monograma.
     * @param monograma el valor monograma a asignar
     */
    public void setMonograma(String monograma) {
        this.monograma = monograma;
    }

}
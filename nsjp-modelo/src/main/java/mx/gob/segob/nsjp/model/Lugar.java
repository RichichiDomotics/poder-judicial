package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Lugar entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Lugar")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Lugar_id")
public class Lugar extends Elemento {

    // Fields

    private String descripcion;
    private Set<CoordenadaGeografica> coordenadaGeograficas = new HashSet<CoordenadaGeografica>(
            0);
    private Set<Inmediacion> inmediacions = new HashSet<Inmediacion>(0);
    private Set<Detencion> detencions = new HashSet<Detencion>(0);    

    // Constructors

    /** default constructor */
    public Lugar() {
    }

    public Lugar(Long elementoId) {
    	super();
    	setElementoId(elementoId);
	}

	@Column(name = "cDescripcion", length = 200)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugar")
    public Set<CoordenadaGeografica> getCoordenadaGeograficas() {
        return this.coordenadaGeograficas;
    }

    public void setCoordenadaGeograficas(
            Set<CoordenadaGeografica> coordenadaGeograficas) {
        this.coordenadaGeograficas = coordenadaGeograficas;
    }   

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugar")
    public Set<Inmediacion> getInmediacions() {
        return this.inmediacions;
    }

    public void setInmediacions(Set<Inmediacion> inmediacions) {
        this.inmediacions = inmediacions;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lugar")
    public Set<Detencion> getDetencions() {
        return this.detencions;
    }

    public void setDetencions(Set<Detencion> detencions) {
        this.detencions = detencions;
    }    

}

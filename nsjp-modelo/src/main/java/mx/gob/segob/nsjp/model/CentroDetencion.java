package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CentroDetencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CentroDetencion")
public class CentroDetencion implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 162854042536938843L;
	private Long centroDetencionId;
	private Direccion direccion;
	private String nombre;
	private String nombreDirector;
	private Set<CatPrograma> catProgramas = new HashSet<CatPrograma>(0);
	private Set<Involucrado> involucrados = new HashSet<Involucrado>(0);
	private Valor tipo;

	// Constructors

	/** default constructor */
	public CentroDetencion() {
	}

	/** minimal constructor */
	public CentroDetencion(Long centroDetencionId, Direccion direccion,
			String cnombre, String cnombreDirector) {
		this.centroDetencionId = centroDetencionId;
		this.direccion = direccion;
		this.nombre = cnombre;
		this.nombreDirector = cnombreDirector;
	}

	/** full constructor */
	public CentroDetencion(Long centroDetencionId, Valor valor,
			Direccion direccion, String cnombre, String cnombreDirector,
			Set<Sitio> sitios, Set<CatPrograma> catProgramas,
			Set<Involucrado> involucrados) {
		this.centroDetencionId = centroDetencionId;
		this.direccion = direccion;
		this.catProgramas = catProgramas;
		this.involucrados = involucrados;
		this.nombre = cnombre;
		this.nombreDirector = cnombreDirector;
		this.involucrados = involucrados;
	}

	// Property accessors
	@Id
	@Column(name = "CentroDetencion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCentroDetencionId() {
		return this.centroDetencionId;
	}

	public void setCentroDetencionId(Long centroDetencionId) {
		this.centroDetencionId = centroDetencionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Direccion_id", nullable = false)
	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Column(name = "cNombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String cnombre) {
		this.nombre = cnombre;
	}

	@Column(name = "cNombreDirector", nullable = false, length = 100)
	public String getNombreDirector() {
		return this.nombreDirector;
	}

	public void setNombreDirector(String cnombreDirector) {
		this.nombreDirector = cnombreDirector;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "centroDetencion")
	public Set<Involucrado> getInvolucrados() {
		return this.involucrados;
	}

	public void setInvolucrados(Set<Involucrado> involucrados) {
		this.involucrados = involucrados;
	}

    /**
     * Método de acceso al campo tipo.
     * @return El valor del campo tipo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tipo_val")
	public Valor getTipo() {
        return tipo;
    }

    /**
     * Asigna el valor al campo tipo.
     * @param tipo el valor tipo a asignar
     */
    public void setTipo(Valor tipo) {
        this.tipo = tipo;
    }

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CentroDetencionPrograma", 
			joinColumns = { @JoinColumn(name = "CentroDetencion_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "CatPrograma_id", nullable = false, updatable = false) })
	public Set<CatPrograma> getCatProgramas() {
		return this.catProgramas;
	}

	public void setCatProgramas(Set<CatPrograma> catProgramas) {
		this.catProgramas = catProgramas;
	}
    
    
    
}
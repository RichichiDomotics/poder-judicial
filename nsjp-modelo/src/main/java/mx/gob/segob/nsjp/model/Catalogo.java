package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catalogo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Catalogo" )
public class Catalogo implements java.io.Serializable {

	// Fields

	private Long catalogoId;
	private String nombre;
	private String descripcion;
	private Boolean esSistema;
	private Set<CampoCatalogo> campoCatalogos = new HashSet<CampoCatalogo>(0);

	// Constructors

	/** default constructor */
	public Catalogo() {
	}
	
	public Catalogo(Long cataogoId){
		this.catalogoId = cataogoId;
	}

	/** minimal constructor */
	public Catalogo(Long cataogoId, String nombre, String descripcion,
			Boolean esSistema) {
		this.catalogoId = cataogoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.esSistema = esSistema;
	}

	/** full constructor */
	public Catalogo(Long cataogoId, String nombre, String descripcion,
			Boolean esSistema, Set<CampoCatalogo> campoCatalogos) {
		this.catalogoId = cataogoId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.esSistema = esSistema;
		this.campoCatalogos = campoCatalogos;
	}

	// Property accessors
	@Id
	@Column(name = "Catalogo_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatalogoId() {
		return this.catalogoId;
	}

	public void setCatalogoId(Long cataogoId) {
		this.catalogoId = cataogoId;
	}

	@Column(name = "cNombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "bEsSistema", nullable = false, precision = 1, scale = 0)
	public Boolean getEsSistema() {
		return this.esSistema;
	}

	public void setEsSistema(Boolean esSistema) {
		this.esSistema = esSistema;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogo")
	public Set<CampoCatalogo> getCampoCatalogos() {
		return this.campoCatalogos;
	}

	public void setCampoCatalogos(Set<CampoCatalogo> campoCatalogos) {
		this.campoCatalogos = campoCatalogos;
	}

}

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
 * CatTipoAsentamiento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoAsentamiento")
public class CatTipoAsentamiento implements java.io.Serializable {

	// Fields

	private Long catTipoAsentamientoId;
	private String nombre;
	private Set<Asentamiento> asentamientos = new HashSet<Asentamiento>(0);

	// Constructors

	/** default constructor */
	public CatTipoAsentamiento() {
	}

	/** minimal constructor */
	public CatTipoAsentamiento(Long catTipoAsentamientoId, String cnombre) {
		this.catTipoAsentamientoId = catTipoAsentamientoId;
		this.nombre = cnombre;
	}

	/** full constructor */
	public CatTipoAsentamiento(Long catTipoAsentamientoId, String cnombre,
			Set<Asentamiento> asentamientos) {
		this.catTipoAsentamientoId = catTipoAsentamientoId;
		this.nombre = cnombre;
		this.asentamientos = asentamientos;
	}

	// Property accessors
	@Id
	@Column(name = "CatTipoAsentamiento_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoAsentamientoId() {
		return this.catTipoAsentamientoId;
	}

	public void setCatTipoAsentamientoId(Long catTipoAsentamientoId) {
		this.catTipoAsentamientoId = catTipoAsentamientoId;
	}

	@Column(name = "cNombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String cnombre) {
		this.nombre = cnombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catTipoAsentamiento")
	public Set<Asentamiento> getAsentamientos() {
		return this.asentamientos;
	}

	public void setAsentamientos(Set<Asentamiento> asentamientos) {
		this.asentamientos = asentamientos;
	}

}
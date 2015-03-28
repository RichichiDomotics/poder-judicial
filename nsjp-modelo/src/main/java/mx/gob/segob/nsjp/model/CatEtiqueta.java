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
 * CatEtiqueta entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatEtiqueta")
public class CatEtiqueta implements java.io.Serializable {

	// Fields

	private Long catEtiquetaId;
	private String nombre;
	private Set<EtiquetaJerarquia> etiquetaJerarquias = new HashSet<EtiquetaJerarquia>(
			0);

	// Constructors

	/** default constructor */
	public CatEtiqueta() {
	}

	/** minimal constructor */
	public CatEtiqueta(Long catEtiquetaId) {
		this.catEtiquetaId = catEtiquetaId;
	}

	/** full constructor */
	public CatEtiqueta(Long catEtiquetaId, String cnombre,
			Set<EtiquetaJerarquia> etiquetaJerarquias) {
		this.catEtiquetaId = catEtiquetaId;
		this.nombre = cnombre;
		this.etiquetaJerarquias = etiquetaJerarquias;
	}

	// Property accessors
	@Id
	@Column(name = "CatEtiqueta_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatEtiquetaId() {
		return this.catEtiquetaId;
	}

	public void setCatEtiquetaId(Long catEtiquetaId) {
		this.catEtiquetaId = catEtiquetaId;
	}

	@Column(name = "cNombre", length = 150)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String cnombre) {
		this.nombre = cnombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catEtiqueta")
	public Set<EtiquetaJerarquia> getEtiquetaJerarquias() {
		return this.etiquetaJerarquias;
	}

	public void setEtiquetaJerarquias(Set<EtiquetaJerarquia> etiquetaJerarquias) {
		this.etiquetaJerarquias = etiquetaJerarquias;
	}

}
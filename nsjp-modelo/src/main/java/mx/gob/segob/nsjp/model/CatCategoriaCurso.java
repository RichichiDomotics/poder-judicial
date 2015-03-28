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
 * CatCategoriaCurso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatCategoriaCurso")
public class CatCategoriaCurso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938583847379240832L;

	// Fields

	private Long categoriaCursoId;
	private String cdescripcion;
	private Set<CatCurso> catCursos = new HashSet<CatCurso>(0);

	// Constructors

	/** default constructor */
	public CatCategoriaCurso() {
	}

	/** minimal constructor */
	public CatCategoriaCurso(Long categoriaCursoId, String cdescripcion) {
		this.categoriaCursoId = categoriaCursoId;
		this.cdescripcion = cdescripcion;
	}

	/** full constructor */
	public CatCategoriaCurso(Long categoriaCursoId, String cdescripcion,
			Set<CatCurso> catCursos) {
		this.categoriaCursoId = categoriaCursoId;
		this.cdescripcion = cdescripcion;
		this.catCursos = catCursos;
	}

	// Property accessors
	@Id
	@Column(name = "CategoriaCurso_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCategoriaCursoId() {
		return this.categoriaCursoId;
	}

	public void setCategoriaCursoId(Long categoriaCursoId) {
		this.categoriaCursoId = categoriaCursoId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catCategoriaCurso")
	public Set<CatCurso> getCatCursos() {
		return this.catCursos;
	}

	public void setCatCursos(Set<CatCurso> catCursos) {
		this.catCursos = catCursos;
	}

}
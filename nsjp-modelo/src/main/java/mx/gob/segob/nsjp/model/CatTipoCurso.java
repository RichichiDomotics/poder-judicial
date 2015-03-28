package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatTipoCurso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoCurso")
public class CatTipoCurso implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7183555349282064195L;
	private Long catTipoCursoId;
	private String cdescripcion;

	// Constructors

	/** default constructor */
	public CatTipoCurso() {
	}

	/** minimal constructor */
	public CatTipoCurso(Long catTipoCursoId, String cdescripcion) {
		this.catTipoCursoId = catTipoCursoId;
		this.cdescripcion = cdescripcion;
	}


	// Property accessors
	@Id
	@Column(name = "CatTipoCurso_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoCursoId() {
		return this.catTipoCursoId;
	}

	public void setCatTipoCursoId(Long catTipoCursoId) {
		this.catTipoCursoId = catTipoCursoId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
}
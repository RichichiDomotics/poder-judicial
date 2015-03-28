package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatTipoNivelAcademico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTipoNivelAcademico")
public class CatTipoNivelAcademico implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3204460578960872987L;
	private Long catTipoNivelAcademicoId;
	private String cdescripcion;

	// Constructors

	/** default constructor */
	public CatTipoNivelAcademico() {
	}

	/** minimal constructor */
	public CatTipoNivelAcademico(Long catTipoNivelAcademicoId,
			String cdescripcion) {
		this.catTipoNivelAcademicoId = catTipoNivelAcademicoId;
		this.cdescripcion = cdescripcion;
	}


	// Property accessors
	@Id
	@Column(name = "CatTipoNivelAcademico_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTipoNivelAcademicoId() {
		return this.catTipoNivelAcademicoId;
	}

	public void setCatTipoNivelAcademicoId(Long catTipoNivelAcademicoId) {
		this.catTipoNivelAcademicoId = catTipoNivelAcademicoId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
}
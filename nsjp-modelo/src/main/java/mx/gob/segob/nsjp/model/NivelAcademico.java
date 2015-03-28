package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NivelAcademico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NivelAcademico")
public class NivelAcademico implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835087586586471164L;
	private Long nivelAcademicoId;
	private CatTipoNivelAcademico catTipoNivelAcademico;
	private String cotros;
	private String cobservaciones;

	// Constructors

	/** default constructor */
	public NivelAcademico() {
	}

	/** full constructor */
	public NivelAcademico(Long nivelAcademicoId,
			CatTipoNivelAcademico catTipoNivelAcademico, String cotros,
			String cobservaciones) {
		this.nivelAcademicoId = nivelAcademicoId;
		this.catTipoNivelAcademico = catTipoNivelAcademico;
		this.cotros = cotros;
		this.cobservaciones = cobservaciones;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NivelAcademico_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getNivelAcademicoId() {
		return this.nivelAcademicoId;
	}

	public void setNivelAcademicoId(Long nivelAcademicoId) {
		this.nivelAcademicoId = nivelAcademicoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTipoNivelAcademico_id", nullable = false)
	public CatTipoNivelAcademico getCatTipoNivelAcademico() {
		return this.catTipoNivelAcademico;
	}

	public void setCatTipoNivelAcademico(
			CatTipoNivelAcademico catTipoNivelAcademico) {
		this.catTipoNivelAcademico = catTipoNivelAcademico;
	}

	@Column(name = "cOtros", nullable = false, length = 200)
	public String getCotros() {
		return this.cotros;
	}

	public void setCotros(String cotros) {
		this.cotros = cotros;
	}

	@Column(name = "cObservaciones", nullable = false, length = 200)
	public String getCobservaciones() {
		return this.cobservaciones;
	}

	public void setCobservaciones(String cobservaciones) {
		this.cobservaciones = cobservaciones;
	}

}
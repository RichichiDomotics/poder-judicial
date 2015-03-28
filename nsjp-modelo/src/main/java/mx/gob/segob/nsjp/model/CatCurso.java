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
 * CatCurso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatCurso")
public class CatCurso implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 756589953921607896L;
	private Long catCursoId;
	private CatTipoNivelAcademico catTipoNivelAcademico;
	private CatCategoriaCurso catCategoriaCurso;
	private CatTipoCurso catTipoCurso;
	private Long ipuntos;
	private String cnombre;
	private String cdescripcion;
	private String cduracion;
	private Boolean bActivo;

	// Constructors

	/** default constructor */
	public CatCurso() {
	}

	/** minimal constructor */
	public CatCurso(Long catCursoId,
			CatTipoNivelAcademico catTipoNivelAcademico,
			CatCategoriaCurso catCategoriaCurso, CatTipoCurso catTipoCurso,
			Long ipuntos, String cnombre, String cdescripcion, String cduracion, Boolean bActivo) {
		this.catCursoId = catCursoId;
		this.catTipoNivelAcademico = catTipoNivelAcademico;
		this.catCategoriaCurso = catCategoriaCurso;
		this.catTipoCurso = catTipoCurso;
		this.ipuntos = ipuntos;
		this.cnombre = cnombre;
		this.cdescripcion = cdescripcion;
		this.cduracion = cduracion;
		this.bActivo = bActivo;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatCurso_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatCursoId() {
		return this.catCursoId;
	}

	public void setCatCursoId(Long catCursoId) {
		this.catCursoId = catCursoId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoriaCurso_id", nullable = false)
	public CatCategoriaCurso getCatCategoriaCurso() {
		return this.catCategoriaCurso;
	}

	public void setCatCategoriaCurso(CatCategoriaCurso catCategoriaCurso) {
		this.catCategoriaCurso = catCategoriaCurso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTipoCurso_id", nullable = false)
	public CatTipoCurso getCatTipoCurso() {
		return this.catTipoCurso;
	}

	public void setCatTipoCurso(CatTipoCurso catTipoCurso) {
		this.catTipoCurso = catTipoCurso;
	}

	@Column(name = "iPuntos", nullable = false, precision = 18, scale = 0)
	public Long getIpuntos() {
		return this.ipuntos;
	}

	public void setIpuntos(Long ipuntos) {
		this.ipuntos = ipuntos;
	}

	@Column(name = "cNombre", nullable = false, length = 200)
	public String getCnombre() {
		return this.cnombre;
	}

	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

	@Column(name = "cDuracion", nullable = false, length = 200)
	public String getCduracion() {
		return this.cduracion;
	}

	public void setCduracion(String cduracion) {
		this.cduracion = cduracion;
	}

	@Column(name = "bActivo", nullable = false, precision = 1, scale = 0)
	public Boolean getBActivo() {
		return this.bActivo;
	}

	public void setBActivo(Boolean bActivo) {
		this.bActivo = bActivo;
	}

}
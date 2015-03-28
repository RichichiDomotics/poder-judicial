package mx.gob.segob.nsjp.model;


import java.util.Date;

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
 * Curso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Curso")
public class Curso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2101348701712940863L;
	// Fields

	private Long cursoId;
	private CatCurso catCurso;
	private Programa programa;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private Long ipuntosObtenidos;
	private Boolean bcompletado;

	// Constructors

	/** default constructor */
	public Curso() {
	}

	/** minimal constructor */
	public Curso(Long cursoId, CatCurso catCurso, Programa programa,
			Date dfechaIngreso, Boolean bcompletado) {
		this.cursoId = cursoId;
		this.catCurso = catCurso;
		this.programa = programa;
		this.dfechaIngreso = dfechaIngreso;
		this.bcompletado = bcompletado;
	}

	/** full constructor */
	public Curso(Long cursoId, CatCurso catCurso, Programa programa,
			Date dfechaIngreso, Date dfechaTermino,
			Long ipuntosObtenidos, Boolean bcompletado) {
		this.cursoId = cursoId;
		this.catCurso = catCurso;
		this.programa = programa;
		this.dfechaIngreso = dfechaIngreso;
		this.dfechaTermino = dfechaTermino;
		this.ipuntosObtenidos = ipuntosObtenidos;
		this.bcompletado = bcompletado;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Curso_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCursoId() {
		return this.cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatCurso_Id", nullable = false)
	public CatCurso getCatCurso() {
		return this.catCurso;
	}

	public void setCatCurso(CatCurso catCurso) {
		this.catCurso = catCurso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Programa_Id", nullable = false)
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	@Column(name = "dFechaIngreso", nullable = false, length = 23)
	public Date getDfechaIngreso() {
		return this.dfechaIngreso;
	}

	public void setDfechaIngreso(Date dfechaIngreso) {
		this.dfechaIngreso = dfechaIngreso;
	}

	@Column(name = "dFechaTermino", length = 23)
	public Date getDfechaTermino() {
		return this.dfechaTermino;
	}

	public void setDfechaTermino(Date dfechaTermino) {
		this.dfechaTermino = dfechaTermino;
	}

	@Column(name = "iPuntosObtenidos", precision = 18, scale = 0)
	public Long getIpuntosObtenidos() {
		return this.ipuntosObtenidos;
	}

	public void setIpuntosObtenidos(Long ipuntosObtenidos) {
		this.ipuntosObtenidos = ipuntosObtenidos;
	}

	@Column(name = "bCompletado", nullable = false, precision = 1, scale = 0)
	public Boolean getBcompletado() {
		return this.bcompletado;
	}

	public void setBcompletado(Boolean bcompletado) {
		this.bcompletado = bcompletado;
	}

}
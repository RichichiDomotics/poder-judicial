package mx.gob.segob.nsjp.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Programa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Programa")
public class Programa implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7263413538426727257L;
	// Fields

	private Long programaId;
	private CatPrograma catPrograma;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private Set<Curso> cursos = new HashSet<Curso>(0);
	private Set<Trabajo> trabajos = new HashSet<Trabajo>(0);
	private Set<AsignacionPrograma> asignacionProgramas = new HashSet<AsignacionPrograma>(0);

	// Constructors

	/** default constructor */
	public Programa() {
	}

	/** minimal constructor */
	public Programa(Long programaId, CatPrograma catPrograma,
			Date dfechaIngreso) {
		this.programaId = programaId;
		this.catPrograma = catPrograma;
		this.dfechaIngreso = dfechaIngreso;
	}

	/** full constructor */
	public Programa(Long programaId, CatPrograma catPrograma,
			Date dfechaIngreso, Date dfechaTermino,
			Set<Curso> cursos, Set<Trabajo> trabajos,
			Set<AsignacionPrograma> asignacionProgramas) {
		this.programaId = programaId;
		this.catPrograma = catPrograma;
		this.dfechaIngreso = dfechaIngreso;
		this.dfechaTermino = dfechaTermino;
		this.cursos = cursos;
		this.trabajos = trabajos;
		this.asignacionProgramas = asignacionProgramas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Programa_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getProgramaId() {
		return this.programaId;
	}

	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatPrograma_Id", nullable = false)
	public CatPrograma getCatPrograma() {
		return this.catPrograma;
	}

	public void setCatPrograma(CatPrograma catPrograma) {
		this.catPrograma = catPrograma;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programa")
	public Set<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programa")
	public Set<Trabajo> getTrabajos() {
		return this.trabajos;
	}

	public void setTrabajos(Set<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programa")
	public Set<AsignacionPrograma> getAsignacionProgramas() {
		return this.asignacionProgramas;
	}

	public void setAsignacionProgramas(
			Set<AsignacionPrograma> asignacionProgramas) {
		this.asignacionProgramas = asignacionProgramas;
	}

}
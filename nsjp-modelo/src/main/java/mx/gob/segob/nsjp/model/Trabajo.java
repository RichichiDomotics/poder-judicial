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
 * Trabajo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Trabajo")
public class Trabajo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679831741983364543L;
	// Fields

	private Long trabajoId;
	private CatTrabajo catTrabajo;
	private Programa programa;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private Long ipuntosObtenidos;
	private Boolean bcompletado;

	// Constructors

	/** default constructor */
	public Trabajo() {
	}

	/** minimal constructor */
	public Trabajo(Long trabajoId, CatTrabajo catTrabajo, Programa programa,
			Date dfechaIngreso, Boolean bcompletado) {
		this.trabajoId = trabajoId;
		this.catTrabajo = catTrabajo;
		this.programa = programa;
		this.dfechaIngreso = dfechaIngreso;
		this.bcompletado = bcompletado;
	}

	/** full constructor */
	public Trabajo(Long trabajoId, CatTrabajo catTrabajo, Programa programa,
			Date dfechaIngreso, Date dfechaTermino,
			Long ipuntosObtenidos, Boolean bcompletado) {
		this.trabajoId = trabajoId;
		this.catTrabajo = catTrabajo;
		this.programa = programa;
		this.dfechaIngreso = dfechaIngreso;
		this.dfechaTermino = dfechaTermino;
		this.ipuntosObtenidos = ipuntosObtenidos;
		this.bcompletado = bcompletado;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Trabajo_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getTrabajoId() {
		return this.trabajoId;
	}

	public void setTrabajoId(Long trabajoId) {
		this.trabajoId = trabajoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTrabajo_Id", nullable = false)
	public CatTrabajo getCatTrabajo() {
		return this.catTrabajo;
	}

	public void setCatTrabajo(CatTrabajo catTrabajo) {
		this.catTrabajo = catTrabajo;
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
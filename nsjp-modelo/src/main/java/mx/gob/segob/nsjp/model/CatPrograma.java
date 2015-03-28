package mx.gob.segob.nsjp.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CatPrograma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatPrograma")
public class CatPrograma implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7557502368041313261L;
	private Long catProgramaId;
	private CatTipoPrograma catTipoPrograma;
	private Date dfechaInicioPrograma;
	private Date dfechaFinPrograma;
	private String cnombre;
	private String cdescripcion;
	private Set<CatTrabajo> catTrabajos = new HashSet<CatTrabajo>(0);
	private Set<CentroDetencion> centroDetencions = new HashSet<CentroDetencion>(
			0);
	private Set<CatCurso> catCursos = new HashSet<CatCurso>(0);
	private Boolean bActivo;
	// Constructors

	/** default constructor */
	public CatPrograma() {
	}

	/** minimal constructor */
	public CatPrograma(Long catProgramaId, CatTipoPrograma catTipoPrograma,
			Timestamp dfechaInicioPrograma, Timestamp dfechaFinPrograma,
			String cnombre, String cdescripcion, Boolean bActivo) {
		this.catProgramaId = catProgramaId;
		this.catTipoPrograma = catTipoPrograma;
		this.dfechaInicioPrograma = dfechaInicioPrograma;
		this.dfechaFinPrograma = dfechaFinPrograma;
		this.cnombre = cnombre;
		this.cdescripcion = cdescripcion;
		this.bActivo = bActivo;
	}

	/** full constructor */
	public CatPrograma(Long catProgramaId, CatTipoPrograma catTipoPrograma,
			Timestamp dfechaInicioPrograma, Timestamp dfechaFinPrograma,
			String cnombre, String cdescripcion, Boolean bActivo, Set<CatTrabajo> catTrabajos,
			Set<CentroDetencion> centroDetencions, Set<CatCurso> catCursos) {
		this.catProgramaId = catProgramaId;
		this.catTipoPrograma = catTipoPrograma;
		this.dfechaInicioPrograma = dfechaInicioPrograma;
		this.dfechaFinPrograma = dfechaFinPrograma;
		this.cnombre = cnombre;
		this.cdescripcion = cdescripcion;
		this.bActivo = bActivo;
		this.catTrabajos = catTrabajos;
		this.centroDetencions = centroDetencions;
		this.catCursos = catCursos;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatPrograma_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatProgramaId() {
		return this.catProgramaId;
	}

	public void setCatProgramaId(Long catProgramaId) {
		this.catProgramaId = catProgramaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTipoPrograma_id", nullable = false)
	public CatTipoPrograma getCatTipoPrograma() {
		return this.catTipoPrograma;
	}

	public void setCatTipoPrograma(CatTipoPrograma catTipoPrograma) {
		this.catTipoPrograma = catTipoPrograma;
	}

	@Column(name = "dFechaInicioPrograma", nullable = false, length = 23)
	public Date getDfechaInicioPrograma() {
		return this.dfechaInicioPrograma;
	}

	public void setDfechaInicioPrograma(Date dfechaInicioPrograma) {
		this.dfechaInicioPrograma = dfechaInicioPrograma;
	}

	@Column(name = "dFechaFinPrograma", nullable = false, length = 23)
	public Date getDfechaFinPrograma() {
		return this.dfechaFinPrograma;
	}

	public void setDfechaFinPrograma(Date dfechaFinPrograma) {
		this.dfechaFinPrograma = dfechaFinPrograma;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CatTrabajoCatPrograma",  
			joinColumns = { @JoinColumn(name = "CatPrograma_id", updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "CatTrabajo_id", updatable = true) })
	public Set<CatTrabajo> getCatTrabajos() {
		return this.catTrabajos;
	}

	public void setCatTrabajos(Set<CatTrabajo> catTrabajos) {
		this.catTrabajos = catTrabajos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CentroDetencionPrograma",  
			joinColumns = { @JoinColumn(name = "CatPrograma_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "CentroDetencion_id") })
	public Set<CentroDetencion> getCentroDetencions() {
		return this.centroDetencions;
	}

	public void setCentroDetencions(Set<CentroDetencion> centroDetencions) {
		this.centroDetencions = centroDetencions;
	}

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CatCursoCatPrograma",  
			joinColumns = { @JoinColumn(name = "CatPrograma_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "CatCurso_id") })
	public Set<CatCurso> getCatCursos() {
		return this.catCursos;
	}

	public void setCatCursos(Set<CatCurso> catCursos) {
		this.catCursos = catCursos;
	}

	@Column(name = "bActivo", nullable = false, precision = 1, scale = 0)
	public Boolean getBActivo() {
		return this.bActivo;
	}

	public void setBActivo(Boolean bActivo) {
		this.bActivo = bActivo;
	}
		
}
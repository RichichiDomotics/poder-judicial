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
 * AsignacionCentroDetencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AsignacionCentroDetencion")
public class AsignacionCentroDetencion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4177458914296458829L;
	// Fields

	private Long asignacionCentroDetencionId;
	private CentroDetencion centroDetencion;
	private Sentencia sentencia;
	private Date dfechaIngreso;
	private Date dfechaSalida;
	private Boolean barraigado;
	private String cmotivo;

	// Constructors

	/** default constructor */
	public AsignacionCentroDetencion() {
		super();
	}

	/** minimal constructor */
	public AsignacionCentroDetencion(Long asignacionCentroDetencionId,
			CentroDetencion centroDetencion, Sentencia sentencia,
			Date dfechaIngreso, Date dfechaSalida, Boolean barraigado) {
		this.asignacionCentroDetencionId = asignacionCentroDetencionId;
		this.centroDetencion = centroDetencion;
		this.sentencia = sentencia;
		this.dfechaIngreso = dfechaIngreso;
		this.dfechaSalida = dfechaSalida;
		this.barraigado = barraigado;
	}

	/** full constructor */
	public AsignacionCentroDetencion(Long asignacionCentroDetencionId,
			CentroDetencion centroDetencion, Sentencia sentencia,
			Date dfechaIngreso, Date dfechaSalida,
			Boolean barraigado, String cmotivo) {
		this.asignacionCentroDetencionId = asignacionCentroDetencionId;
		this.centroDetencion = centroDetencion;
		this.sentencia = sentencia;
		this.dfechaIngreso = dfechaIngreso;
		this.dfechaSalida = dfechaSalida;
		this.barraigado = barraigado;
		this.cmotivo = cmotivo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AsignacionCentroDetencion_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAsignacionCentroDetencionId() {
		return this.asignacionCentroDetencionId;
	}

	public void setAsignacionCentroDetencionId(Long asignacionCentroDetencionId) {
		this.asignacionCentroDetencionId = asignacionCentroDetencionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CentroDetencion_Id", nullable = false)
	public CentroDetencion getCentroDetencion() {
		return this.centroDetencion;
	}

	public void setCentroDetencion(CentroDetencion centroDetencion) {
		this.centroDetencion = centroDetencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_Id", nullable = true, updatable = false)
	public Sentencia getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}

	@Column(name = "dFechaIngreso", nullable = false, length = 23)
	public Date getDfechaIngreso() {
		return this.dfechaIngreso;
	}

	public void setDfechaIngreso(Date dfechaIngreso) {
		this.dfechaIngreso = dfechaIngreso;
	}

	@Column(name = "dFechaSalida", nullable = false, length = 23)
	public Date getDfechaSalida() {
		return this.dfechaSalida;
	}

	public void setDfechaSalida(Date dfechaSalida) {
		this.dfechaSalida = dfechaSalida;
	}

	@Column(name = "bArraigado", nullable = false, precision = 1, scale = 0)
	public Boolean getBarraigado() {
		return this.barraigado;
	}

	public void setBarraigado(Boolean barraigado) {
		this.barraigado = barraigado;
	}

	@Column(name = "cMotivo", length = 200)
	public String getCmotivo() {
		return this.cmotivo;
	}

	public void setCmotivo(String cmotivo) {
		this.cmotivo = cmotivo;
	}

}
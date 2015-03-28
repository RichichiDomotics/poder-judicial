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
 * AsignacionMedidaAlterna entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AsignacionMedidaAlterna")
public class AsignacionMedidaAlterna implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2008326755945954320L;
	private Long asignacionMedidaAlternaId;
	private Sentencia sentencia;
	private MedidaAlterna medidaAlterna;
	private Date dfechaInicio;
	private Date dfechaFin;
	private Boolean bcumplida;

	// Constructors

	/** default constructor */
	public AsignacionMedidaAlterna() {
	}

	/** full constructor */
	public AsignacionMedidaAlterna(Long asignacionMedidaAlternaId,
			Sentencia sentencia, MedidaAlterna medidaAlterna,
			Date dfechaInicio, Date dfechaFin, Boolean bcumplida) {
		this.asignacionMedidaAlternaId = asignacionMedidaAlternaId;
		this.sentencia = sentencia;
		this.medidaAlterna = medidaAlterna;
		this.dfechaInicio = dfechaInicio;
		this.dfechaFin = dfechaFin;
		this.bcumplida = bcumplida;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AsignacionMedidaAlterna_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAsignacionMedidaAlternaId() {
		return this.asignacionMedidaAlternaId;
	}

	public void setAsignacionMedidaAlternaId(Long asignacionMedidaAlternaId) {
		this.asignacionMedidaAlternaId = asignacionMedidaAlternaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_Id", nullable = true, updatable = false)
	public Sentencia getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MedidaAlterna_Id", nullable = false)
	public MedidaAlterna getMedidaAlterna() {
		return this.medidaAlterna;
	}

	public void setMedidaAlterna(MedidaAlterna medidaAlterna) {
		this.medidaAlterna = medidaAlterna;
	}

	@Column(name = "dFechaInicio", nullable = false, length = 23)
	public Date getDfechaInicio() {
		return this.dfechaInicio;
	}

	public void setDfechaInicio(Date dfechaInicio) {
		this.dfechaInicio = dfechaInicio;
	}

	@Column(name = "dFechaFin", nullable = false, length = 23)
	public Date getDfechaFin() {
		return this.dfechaFin;
	}

	public void setDfechaFin(Date dfechaFin) {
		this.dfechaFin = dfechaFin;
	}

	@Column(name = "bCumplida", nullable = false, precision = 1, scale = 0)
	public Boolean getBcumplida() {
		return this.bcumplida;
	}

	public void setBcumplida(Boolean bcumplida) {
		this.bcumplida = bcumplida;
	}
}
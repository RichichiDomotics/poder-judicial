package mx.gob.segob.nsjp.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AudienciaEvidencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AudienciaEvidencia")
public class AudienciaEvidencia implements java.io.Serializable {

	// Fields

	private AudienciaEvidenciaId id;
	private Evidencia evidencia;
	private Audiencia audiencia;
	private Date fechaRecepcion;

	// Constructors

	/** default constructor */
	public AudienciaEvidencia() {
	}

	/** minimal constructor */
	public AudienciaEvidencia(AudienciaEvidenciaId id, Evidencia evidencia,
			Audiencia audiencia) {
		this.id = id;
		this.evidencia = evidencia;
		this.audiencia = audiencia;
	}

	/** full constructor */
	public AudienciaEvidencia(AudienciaEvidenciaId id, Evidencia evidencia,
			Audiencia audiencia, Timestamp dfechaRecepcion) {
		this.id = id;
		this.evidencia = evidencia;
		this.audiencia = audiencia;
		this.fechaRecepcion = dfechaRecepcion;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "audienciaId", column = @Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "evidenciaId", column = @Column(name = "Evidencia_id", nullable = false, precision = 18, scale = 0)) })
	public AudienciaEvidenciaId getId() {
		return this.id;
	}

	public void setId(AudienciaEvidenciaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Evidencia_id", nullable = false, insertable = false, updatable = false)
	public Evidencia getEvidencia() {
		return this.evidencia;
	}

	public void setEvidencia(Evidencia evidencia) {
		this.evidencia = evidencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Audiencia_id", nullable = false, insertable = false, updatable = false)
	public Audiencia getAudiencia() {
		return this.audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	@Column(name = "dFechaRecepcion", length = 23)
	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date dfechaRecepcion) {
		this.fechaRecepcion = dfechaRecepcion;
	}

}
package mx.gob.segob.nsjp.model;

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
 * SolicitudDefensorDelito entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SolicitudDefensorDelito")
public class SolicitudDefensorDelito implements java.io.Serializable {

	// Fields

	private SolicitudDefensorDelitoId id;
	private Delito delito;
	private SolicitudDefensor solicitudDefensor;

	// Constructors

	/** default constructor */
	public SolicitudDefensorDelito() {
	}

	/** full constructor */
	public SolicitudDefensorDelito(SolicitudDefensorDelitoId id, Delito delito,
			SolicitudDefensor solicitudDefensor) {
		this.id = id;
		this.delito = delito;
		this.solicitudDefensor = solicitudDefensor;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "delitoId", column = @Column(name = "Delito_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "solicitudDefensorId", column = @Column(name = "SolicitudDefensor_id", nullable = false, precision = 18, scale = 0)) })
	public SolicitudDefensorDelitoId getId() {
		return this.id;
	}

	public void setId(SolicitudDefensorDelitoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Delito_id", nullable = false, insertable = false, updatable = false)
	public Delito getDelito() {
		return this.delito;
	}

	public void setDelito(Delito delito) {
		this.delito = delito;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SolicitudDefensor_id", nullable = false, insertable = false, updatable = false)
	public SolicitudDefensor getSolicitudDefensor() {
		return this.solicitudDefensor;
	}

	public void setSolicitudDefensor(SolicitudDefensor solicitudDefensor) {
		this.solicitudDefensor = solicitudDefensor;
	}

}
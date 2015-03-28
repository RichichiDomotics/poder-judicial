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
 * AvisoDetencionDelito entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AvisoDetencionDelito")
public class AvisoDetencionDelito implements java.io.Serializable {

	// Fields

	private AvisoDetencionDelitoId id;
	private Delito delito;
	private AvisoDetencion avisoDetencion;

	// Constructors

	/** default constructor */
	public AvisoDetencionDelito() {
	}

	/** full constructor */
	public AvisoDetencionDelito(AvisoDetencionDelitoId id, Delito delito,
			AvisoDetencion avisoDetencion) {
		this.id = id;
		this.delito = delito;
		this.avisoDetencion = avisoDetencion;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "delitoId", column = @Column(name = "Delito_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "avisoDetencionId", column = @Column(name = "AvisoDetencion_id", nullable = false, precision = 18, scale = 0)) })
	public AvisoDetencionDelitoId getId() {
		return this.id;
	}

	public void setId(AvisoDetencionDelitoId id) {
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
	@JoinColumn(name = "AvisoDetencion_id", nullable = false, insertable = false, updatable = false)
	public AvisoDetencion getAvisoDetencion() {
		return this.avisoDetencion;
	}

	public void setAvisoDetencion(AvisoDetencion avisoDetencion) {
		this.avisoDetencion = avisoDetencion;
	}

}
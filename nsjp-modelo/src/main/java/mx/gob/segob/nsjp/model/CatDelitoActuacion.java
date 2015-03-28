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
 * CatDelitoOcupacion entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CatDelitoActuacion")
public class CatDelitoActuacion implements java.io.Serializable {

	// Fields

	private CatDelitoActuacionId id;
	
	private CatDelito catDelito;
	private Valor actuacion;

	// Constructors

	/** default constructor */
	public CatDelitoActuacion() {
	}

	/** full constructor */
	public CatDelitoActuacion(CatDelitoActuacionId id,
			CatDelito catDelito, Valor actuacion) {
		this.id = id;
		this.catDelito = catDelito;
		this.actuacion = actuacion;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "actuacionId", column = @Column(name = "Actuacion_val", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "catDelitoId", column = @Column(name = "CatDelito_id", nullable = false, precision = 18, scale = 0)) })
	public CatDelitoActuacionId getId() {
		return this.id;
	}

	public void setId(CatDelitoActuacionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatDelito_id", nullable = false, insertable = false, updatable = false)
	public CatDelito getCatDelito() {
		return this.catDelito;
	}

	public void setCatDelito(CatDelito catDelito) {
		this.catDelito = catDelito;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Actuacion_val", nullable = false, insertable = false, updatable = false)
	public Valor getActuacion() {
		return this.actuacion;
	}

	public void setActuacion(Valor actuacion) {
		this.actuacion = actuacion;
	}
	
}

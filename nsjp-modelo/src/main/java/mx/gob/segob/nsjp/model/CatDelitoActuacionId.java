package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ActuacionOcupacionId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class CatDelitoActuacionId implements java.io.Serializable {

	// Fields

	private Long catDelitoId;
	private Long actuacionId;

	// Constructors

	/** default constructor */
	public CatDelitoActuacionId() {
	}

	/** full constructor */
	public CatDelitoActuacionId(Long catDelitoId, Long actuacionId) {
		this.catDelitoId = catDelitoId;
		this.actuacionId = actuacionId;
	}

	// Property accessors

	@Column(name = "CatDelito_id", nullable = false, precision = 18, scale = 0)
	public Long getcatDelitoId() {
		return this.catDelitoId;
	}

	public void setcatDelitoId(Long catDelitoId) {
		this.catDelitoId = catDelitoId;
	}

	@Column(name = "Actuacion_val", nullable = false, precision = 18, scale = 0)
	public Long getActuacionId() {
		return this.actuacionId;
	}

	public void setActuacionId(Long actuacionId) {
		this.actuacionId = actuacionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatDelitoActuacionId))
			return false;
		CatDelitoActuacionId castOther = (CatDelitoActuacionId) other;

		return ((this.getcatDelitoId() == castOther.getcatDelitoId()) || (this
				.getcatDelitoId() != null
				&& castOther.getcatDelitoId() != null && this.getcatDelitoId().equals(
				castOther.getcatDelitoId())))
				&& ((this.getActuacionId() == castOther.getActuacionId()) || (this
						.getActuacionId() != null
						&& castOther.getActuacionId() != null && this
						.getActuacionId()
						.equals(castOther.getActuacionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getcatDelitoId() == null ? 0 : this.getcatDelitoId().hashCode());
		result = 37
				* result
				+ (getActuacionId() == null ? 0 : this.getActuacionId()
						.hashCode());
		return result;
	}

}
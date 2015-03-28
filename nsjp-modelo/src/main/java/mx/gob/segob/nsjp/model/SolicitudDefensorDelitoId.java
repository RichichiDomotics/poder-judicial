package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SolicitudDefensorDelitoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SolicitudDefensorDelitoId implements java.io.Serializable {

	// Fields

	private Long delitoId;
	private Long solicitudDefensorId;

	// Constructors

	/** default constructor */
	public SolicitudDefensorDelitoId() {
	}

	/** full constructor */
	public SolicitudDefensorDelitoId(Long delitoId, Long solicitudDefensorId) {
		this.delitoId = delitoId;
		this.solicitudDefensorId = solicitudDefensorId;
	}

	// Property accessors

	@Column(name = "Delito_id", nullable = false, precision = 18, scale = 0)
	public Long getDelitoId() {
		return this.delitoId;
	}

	public void setDelitoId(Long delitoId) {
		this.delitoId = delitoId;
	}

	@Column(name = "SolicitudDefensor_id", nullable = false, precision = 18, scale = 0)
	public Long getSolicitudDefensorId() {
		return this.solicitudDefensorId;
	}

	public void setSolicitudDefensorId(Long solicitudDefensorId) {
		this.solicitudDefensorId = solicitudDefensorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SolicitudDefensorDelitoId))
			return false;
		SolicitudDefensorDelitoId castOther = (SolicitudDefensorDelitoId) other;

		return ((this.getDelitoId() == castOther.getDelitoId()) || (this
				.getDelitoId() != null
				&& castOther.getDelitoId() != null && this.getDelitoId()
				.equals(castOther.getDelitoId())))
				&& ((this.getSolicitudDefensorId() == castOther
						.getSolicitudDefensorId()) || (this
						.getSolicitudDefensorId() != null
						&& castOther.getSolicitudDefensorId() != null && this
						.getSolicitudDefensorId().equals(
								castOther.getSolicitudDefensorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDelitoId() == null ? 0 : this.getDelitoId().hashCode());
		result = 37
				* result
				+ (getSolicitudDefensorId() == null ? 0 : this
						.getSolicitudDefensorId().hashCode());
		return result;
	}

}
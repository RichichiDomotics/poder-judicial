package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AvisoDetencionDelitoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AvisoDetencionDelitoId implements java.io.Serializable {

	// Fields

	private Long delitoId;
	private Long avisoDetencionId;

	// Constructors

	/** default constructor */
	public AvisoDetencionDelitoId() {
	}

	/** full constructor */
	public AvisoDetencionDelitoId(Long delitoId, Long avisoDetencionId) {
		this.delitoId = delitoId;
		this.avisoDetencionId = avisoDetencionId;
	}

	// Property accessors

	@Column(name = "Delito_id", nullable = false, precision = 18, scale = 0)
	public Long getDelitoId() {
		return this.delitoId;
	}

	public void setDelitoId(Long delitoId) {
		this.delitoId = delitoId;
	}

	@Column(name = "AvisoDetencion_id", nullable = false, precision = 18, scale = 0)
	public Long getAvisoDetencionId() {
		return this.avisoDetencionId;
	}

	public void setAvisoDetencionId(Long avisoDetencionId) {
		this.avisoDetencionId = avisoDetencionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AvisoDetencionDelitoId))
			return false;
		AvisoDetencionDelitoId castOther = (AvisoDetencionDelitoId) other;

		return ((this.getDelitoId() == castOther.getDelitoId()) || (this
				.getDelitoId() != null
				&& castOther.getDelitoId() != null && this.getDelitoId()
				.equals(castOther.getDelitoId())))
				&& ((this.getAvisoDetencionId() == castOther
						.getAvisoDetencionId()) || (this.getAvisoDetencionId() != null
						&& castOther.getAvisoDetencionId() != null && this
						.getAvisoDetencionId().equals(
								castOther.getAvisoDetencionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDelitoId() == null ? 0 : this.getDelitoId().hashCode());
		result = 37
				* result
				+ (getAvisoDetencionId() == null ? 0 : this
						.getAvisoDetencionId().hashCode());
		return result;
	}

}
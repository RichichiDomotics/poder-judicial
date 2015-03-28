package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AudienciaEvidenciaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AudienciaEvidenciaId implements java.io.Serializable {

	// Fields

	private Long audienciaId;
	private Long evidenciaId;

	// Constructors

	/** default constructor */
	public AudienciaEvidenciaId() {
	}

	/** full constructor */
	public AudienciaEvidenciaId(Long audienciaId, Long evidenciaId) {
		this.audienciaId = audienciaId;
		this.evidenciaId = evidenciaId;
	}

	// Property accessors

	@Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)
	public Long getAudienciaId() {
		return this.audienciaId;
	}

	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	@Column(name = "Evidencia_id", nullable = false, precision = 18, scale = 0)
	public Long getEvidenciaId() {
		return this.evidenciaId;
	}

	public void setEvidenciaId(Long evidenciaId) {
		this.evidenciaId = evidenciaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AudienciaEvidenciaId))
			return false;
		AudienciaEvidenciaId castOther = (AudienciaEvidenciaId) other;

		return ((this.getAudienciaId() == castOther.getAudienciaId()) || (this
				.getAudienciaId() != null
				&& castOther.getAudienciaId() != null && this.getAudienciaId()
				.equals(castOther.getAudienciaId())))
				&& ((this.getEvidenciaId() == castOther.getEvidenciaId()) || (this
						.getEvidenciaId() != null
						&& castOther.getEvidenciaId() != null && this
						.getEvidenciaId().equals(castOther.getEvidenciaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAudienciaId() == null ? 0 : this.getAudienciaId()
						.hashCode());
		result = 37
				* result
				+ (getEvidenciaId() == null ? 0 : this.getEvidenciaId()
						.hashCode());
		return result;
	}

}
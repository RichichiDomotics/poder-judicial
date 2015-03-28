package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * InvolucradoOcupacionId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class InvolucradoAudienciaId implements java.io.Serializable {

	// Fields

	private Long audienciaId;
	private Long involucradoId;

	// Constructors

	/** default constructor */
	public InvolucradoAudienciaId() {
	}

	/** full constructor */
	public InvolucradoAudienciaId(Long audienciaId, Long involucradoId) {
		this.audienciaId = audienciaId;
		this.involucradoId = involucradoId;
	}

	// Property accessors

	@Column(name = "audiencia_id", nullable = false, precision = 18, scale = 0)
	public Long getaudienciaId() {
		return this.audienciaId;
	}

	public void setaudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	@Column(name = "Involucrado_id", nullable = false, precision = 18, scale = 0)
	public Long getInvolucradoId() {
		return this.involucradoId;
	}

	public void setInvolucradoId(Long involucradoId) {
		this.involucradoId = involucradoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InvolucradoAudienciaId))
			return false;
		InvolucradoAudienciaId castOther = (InvolucradoAudienciaId) other;

		return ((this.getaudienciaId() == castOther.getaudienciaId()) || (this
				.getaudienciaId() != null
				&& castOther.getaudienciaId() != null && this.getaudienciaId().equals(
				castOther.getaudienciaId())))
				&& ((this.getInvolucradoId() == castOther.getInvolucradoId()) || (this
						.getInvolucradoId() != null
						&& castOther.getInvolucradoId() != null && this
						.getInvolucradoId()
						.equals(castOther.getInvolucradoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getaudienciaId() == null ? 0 : this.getaudienciaId().hashCode());
		result = 37
				* result
				+ (getInvolucradoId() == null ? 0 : this.getInvolucradoId()
						.hashCode());
		return result;
	}

}
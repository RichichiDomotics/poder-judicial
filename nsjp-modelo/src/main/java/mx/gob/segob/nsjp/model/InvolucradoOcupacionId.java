package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * InvolucradoOcupacionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class InvolucradoOcupacionId implements java.io.Serializable {

	// Fields

	private Long valorId;
	private Long involucradoId;

	// Constructors

	/** default constructor */
	public InvolucradoOcupacionId() {
	}

	/** full constructor */
	public InvolucradoOcupacionId(Long valorId, Long involucradoId) {
		this.valorId = valorId;
		this.involucradoId = involucradoId;
	}

	// Property accessors

	@Column(name = "Valor_id", nullable = false, precision = 18, scale = 0)
	public Long getValorId() {
		return this.valorId;
	}

	public void setValorId(Long valorId) {
		this.valorId = valorId;
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
		if (!(other instanceof InvolucradoOcupacionId))
			return false;
		InvolucradoOcupacionId castOther = (InvolucradoOcupacionId) other;

		return ((this.getValorId() == castOther.getValorId()) || (this
				.getValorId() != null
				&& castOther.getValorId() != null && this.getValorId().equals(
				castOther.getValorId())))
				&& ((this.getInvolucradoId() == castOther.getInvolucradoId()) || (this
						.getInvolucradoId() != null
						&& castOther.getInvolucradoId() != null && this
						.getInvolucradoId()
						.equals(castOther.getInvolucradoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getValorId() == null ? 0 : this.getValorId().hashCode());
		result = 37
				* result
				+ (getInvolucradoId() == null ? 0 : this.getInvolucradoId()
						.hashCode());
		return result;
	}

}
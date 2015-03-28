package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FuncionarioAudienciaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FuncionarioAudienciaId implements java.io.Serializable {

	// Fields

	private Long audienciaId;
	private Long claveFuncionario;

	// Constructors

	/** default constructor */
	public FuncionarioAudienciaId() {
	}

	/** full constructor */
	public FuncionarioAudienciaId(Long audienciaId, Long iclaveFuncionario) {
		this.audienciaId = audienciaId;
		this.claveFuncionario = iclaveFuncionario;
	}

	// Property accessors

	@Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)
	public Long getAudienciaId() {
		return this.audienciaId;
	}

	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	@Column(name = "iClaveFuncionario", nullable = false, precision = 18, scale = 0)
	public Long getClaveFuncionario() {
		return this.claveFuncionario;
	}

	public void setClaveFuncionario(Long iclaveFuncionario) {
		this.claveFuncionario = iclaveFuncionario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FuncionarioAudienciaId))
			return false;
		FuncionarioAudienciaId castOther = (FuncionarioAudienciaId) other;

		return ((this.getAudienciaId() == castOther.getAudienciaId()) || (this
				.getAudienciaId() != null
				&& castOther.getAudienciaId() != null && this.getAudienciaId()
				.equals(castOther.getAudienciaId())))
				&& ((this.getClaveFuncionario() == castOther
						.getClaveFuncionario()) || (this
						.getClaveFuncionario() != null
						&& castOther.getClaveFuncionario() != null && this
						.getClaveFuncionario().equals(
								castOther.getClaveFuncionario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAudienciaId() == null ? 0 : this.getAudienciaId()
						.hashCode());
		result = 37
				* result
				+ (getClaveFuncionario() == null ? 0 : this
						.getClaveFuncionario().hashCode());
		return result;
	}

}
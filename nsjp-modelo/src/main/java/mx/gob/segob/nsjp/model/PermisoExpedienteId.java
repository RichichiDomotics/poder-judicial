package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PermisoExpedienteId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PermisoExpedienteId implements java.io.Serializable {

	// Fields

	private Long numeroExpedienteId;
	private Long iclaveFuncionario;

	// Constructors

	/** default constructor */
	public PermisoExpedienteId() {
	}

	/** full constructor */
	public PermisoExpedienteId(Long numeroExpedienteId, Long iclaveFuncionario) {
		this.numeroExpedienteId = numeroExpedienteId;
		this.iclaveFuncionario = iclaveFuncionario;
	}

	// Property accessors

	@Column(name = "NumeroExpediente_id", nullable = false, precision = 18, scale = 0)
	public Long getNumeroExpedienteId() {
		return this.numeroExpedienteId;
	}

	public void setNumeroExpedienteId(Long numeroExpedienteId) {
		this.numeroExpedienteId = numeroExpedienteId;
	}

	@Column(name = "iClaveFuncionario", nullable = false, precision = 18, scale = 0)
	public Long getIclaveFuncionario() {
		return this.iclaveFuncionario;
	}

	public void setIclaveFuncionario(Long iclaveFuncionario) {
		this.iclaveFuncionario = iclaveFuncionario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PermisoExpedienteId))
			return false;
		PermisoExpedienteId castOther = (PermisoExpedienteId) other;

		return ((this.getNumeroExpedienteId() == castOther
				.getNumeroExpedienteId()) || (this.getNumeroExpedienteId() != null
				&& castOther.getNumeroExpedienteId() != null && this
				.getNumeroExpedienteId().equals(
						castOther.getNumeroExpedienteId())))
				&& ((this.getIclaveFuncionario() == castOther
						.getIclaveFuncionario()) || (this
						.getIclaveFuncionario() != null
						&& castOther.getIclaveFuncionario() != null && this
						.getIclaveFuncionario().equals(
								castOther.getIclaveFuncionario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getNumeroExpedienteId() == null ? 0 : this
						.getNumeroExpedienteId().hashCode());
		result = 37
				* result
				+ (getIclaveFuncionario() == null ? 0 : this
						.getIclaveFuncionario().hashCode());
		return result;
	}

}
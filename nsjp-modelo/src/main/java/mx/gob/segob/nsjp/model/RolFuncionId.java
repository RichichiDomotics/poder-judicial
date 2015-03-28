package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolFuncionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RolFuncionId implements java.io.Serializable {

	// Fields

	private Long rolId;
	private Long funcionId;

	// Constructors

	/** default constructor */
	public RolFuncionId() {
	}

	/** full constructor */
	public RolFuncionId(Long rolId, Long funcionId) {
		this.rolId = rolId;
		this.funcionId = funcionId;
	}

	// Property accessors

	@Column(name = "Rol_id", nullable = false, precision = 18, scale = 0)
	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	@Column(name = "Funcion_id", nullable = false, precision = 18, scale = 0)
	public Long getFuncionId() {
		return this.funcionId;
	}

	public void setFuncionId(Long funcionId) {
		this.funcionId = funcionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolFuncionId))
			return false;
		RolFuncionId castOther = (RolFuncionId) other;

		return ((this.getRolId() == castOther.getRolId()) || (this.getRolId() != null
				&& castOther.getRolId() != null && this.getRolId().equals(
				castOther.getRolId())))
				&& ((this.getFuncionId() == castOther.getFuncionId()) || (this
						.getFuncionId() != null
						&& castOther.getFuncionId() != null && this
						.getFuncionId().equals(castOther.getFuncionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRolId() == null ? 0 : this.getRolId().hashCode());
		result = 37 * result
				+ (getFuncionId() == null ? 0 : this.getFuncionId().hashCode());
		return result;
	}

}
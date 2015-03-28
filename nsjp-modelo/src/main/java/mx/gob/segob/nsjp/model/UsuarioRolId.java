package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsuarioRolId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UsuarioRolId implements java.io.Serializable {

	// Fields

	private Long usuarioId;
	private Long rolId;

	// Constructors

	/** default constructor */
	public UsuarioRolId() {
	}

	/** full constructor */
	public UsuarioRolId(Long usuarioId, Long rolId) {
		this.usuarioId = usuarioId;
		this.rolId = rolId;
	}

	// Property accessors

	@Column(name = "Usuario_id", nullable = false, precision = 18, scale = 0)
	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Column(name = "Rol_id", nullable = false, precision = 18, scale = 0)
	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsuarioRolId))
			return false;
		UsuarioRolId castOther = (UsuarioRolId) other;

		return ((this.getUsuarioId() == castOther.getUsuarioId()) || (this
				.getUsuarioId() != null
				&& castOther.getUsuarioId() != null && this.getUsuarioId()
				.equals(castOther.getUsuarioId())))
				&& ((this.getRolId() == castOther.getRolId()) || (this
						.getRolId() != null
						&& castOther.getRolId() != null && this.getRolId()
						.equals(castOther.getRolId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsuarioId() == null ? 0 : this.getUsuarioId().hashCode());
		result = 37 * result
				+ (getRolId() == null ? 0 : this.getRolId().hashCode());
		return result;
	}

}
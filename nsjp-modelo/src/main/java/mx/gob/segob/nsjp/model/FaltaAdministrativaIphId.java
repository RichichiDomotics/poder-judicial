package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FaltaAdministrativaIphId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FaltaAdministrativaIphId implements java.io.Serializable {

	// Fields

	private Long informePolicialHomologadoId;
	private Long catFaltaAdministrativaId;

	// Constructors

	/** default constructor */
	public FaltaAdministrativaIphId() {
	}

	/** full constructor */
	public FaltaAdministrativaIphId(Long informePolicialHomologadoId,
			Long catFaltaAdministrativaId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
		this.catFaltaAdministrativaId = catFaltaAdministrativaId;
	}

	// Property accessors

	@Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)
	public Long getInformePolicialHomologadoId() {
		return this.informePolicialHomologadoId;
	}

	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}

	@Column(name = "CatFaltaAdministrativa_id", nullable = false, precision = 18, scale = 0)
	public Long getCatFaltaAdministrativaId() {
		return this.catFaltaAdministrativaId;
	}

	public void setCatFaltaAdministrativaId(Long catFaltaAdministrativaId) {
		this.catFaltaAdministrativaId = catFaltaAdministrativaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FaltaAdministrativaIphId))
			return false;
		FaltaAdministrativaIphId castOther = (FaltaAdministrativaIphId) other;

		return ((this.getInformePolicialHomologadoId() == castOther
				.getInformePolicialHomologadoId()) || (this
				.getInformePolicialHomologadoId() != null
				&& castOther.getInformePolicialHomologadoId() != null && this
				.getInformePolicialHomologadoId().equals(
						castOther.getInformePolicialHomologadoId())))
				&& ((this.getCatFaltaAdministrativaId() == castOther
						.getCatFaltaAdministrativaId()) || (this
						.getCatFaltaAdministrativaId() != null
						&& castOther.getCatFaltaAdministrativaId() != null && this
						.getCatFaltaAdministrativaId().equals(
								castOther.getCatFaltaAdministrativaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInformePolicialHomologadoId() == null ? 0 : this
						.getInformePolicialHomologadoId().hashCode());
		result = 37
				* result
				+ (getCatFaltaAdministrativaId() == null ? 0 : this
						.getCatFaltaAdministrativaId().hashCode());
		return result;
	}

}
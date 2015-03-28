package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ArmaExplosivoInvolucradoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ArmaExplosivoInvolucradoId implements java.io.Serializable {

	// Fields

	private Long informePolicialHomologadoId;
	private Long objetoId;

	// Constructors

	/** default constructor */
	public ArmaExplosivoInvolucradoId() {
	}

	/** full constructor */
	public ArmaExplosivoInvolucradoId(Long informePolicialHomologadoId,
			Long objetoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
		this.objetoId = objetoId;
	}

	// Property accessors

	@Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)
	public Long getInformePolicialHomologadoId() {
		return this.informePolicialHomologadoId;
	}

	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}

	@Column(name = "Objeto_id", nullable = false, precision = 18, scale = 0)
	public Long getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(Long objetoId) {
		this.objetoId = objetoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ArmaExplosivoInvolucradoId))
			return false;
		ArmaExplosivoInvolucradoId castOther = (ArmaExplosivoInvolucradoId) other;

		return ((this.getInformePolicialHomologadoId() == castOther
				.getInformePolicialHomologadoId()) || (this
				.getInformePolicialHomologadoId() != null
				&& castOther.getInformePolicialHomologadoId() != null && this
				.getInformePolicialHomologadoId().equals(
						castOther.getInformePolicialHomologadoId())))
				&& ((this.getObjetoId() == castOther.getObjetoId()) || (this
						.getObjetoId() != null
						&& castOther.getObjetoId() != null && this
						.getObjetoId().equals(castOther.getObjetoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInformePolicialHomologadoId() == null ? 0 : this
						.getInformePolicialHomologadoId().hashCode());
		result = 37 * result
				+ (getObjetoId() == null ? 0 : this.getObjetoId().hashCode());
		return result;
	}

}
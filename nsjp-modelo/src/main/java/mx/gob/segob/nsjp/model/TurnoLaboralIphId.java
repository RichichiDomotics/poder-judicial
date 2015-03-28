package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TurnoLaboralIphId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TurnoLaboralIphId implements java.io.Serializable {

	// Fields

	private Long informePolicialHomologadoId;
	private Long turnoLaboralId;

	// Constructors

	/** default constructor */
	public TurnoLaboralIphId() {
	}

	/** full constructor */
	public TurnoLaboralIphId(Long informePolicialHomologadoId,
			Long turnoLaboralId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
		this.turnoLaboralId = turnoLaboralId;
	}

	// Property accessors

	@Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)
	public Long getInformePolicialHomologadoId() {
		return this.informePolicialHomologadoId;
	}

	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}

	@Column(name = "TurnoLaboral_id", nullable = false, precision = 18, scale = 0)
	public Long getTurnoLaboralId() {
		return this.turnoLaboralId;
	}

	public void setTurnoLaboralId(Long turnoLaboralId) {
		this.turnoLaboralId = turnoLaboralId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TurnoLaboralIphId))
			return false;
		TurnoLaboralIphId castOther = (TurnoLaboralIphId) other;

		return ((this.getInformePolicialHomologadoId() == castOther
				.getInformePolicialHomologadoId()) || (this
				.getInformePolicialHomologadoId() != null
				&& castOther.getInformePolicialHomologadoId() != null && this
				.getInformePolicialHomologadoId().equals(
						castOther.getInformePolicialHomologadoId())))
				&& ((this.getTurnoLaboralId() == castOther.getTurnoLaboralId()) || (this
						.getTurnoLaboralId() != null
						&& castOther.getTurnoLaboralId() != null && this
						.getTurnoLaboralId().equals(
								castOther.getTurnoLaboralId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInformePolicialHomologadoId() == null ? 0 : this
						.getInformePolicialHomologadoId().hashCode());
		result = 37
				* result
				+ (getTurnoLaboralId() == null ? 0 : this.getTurnoLaboralId()
						.hashCode());
		return result;
	}

}
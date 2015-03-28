package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DelitoIphId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DelitoIphId implements java.io.Serializable {

	// Fields

	private Long informePolicialHomologadoId;
	private Long catDelitoId;

	// Constructors

	/** default constructor */
	public DelitoIphId() {
	}

	/** full constructor */
	public DelitoIphId(Long informePolicialHomologadoId, Long catDelitoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
		this.catDelitoId = catDelitoId;
	}

	// Property accessors

	@Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)
	public Long getInformePolicialHomologadoId() {
		return this.informePolicialHomologadoId;
	}

	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}

	@Column(name = "CatDelito_id", nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoId() {
		return this.catDelitoId;
	}

	public void setCatDelitoId(Long catDelitoId) {
		this.catDelitoId = catDelitoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DelitoIphId))
			return false;
		DelitoIphId castOther = (DelitoIphId) other;

		return ((this.getInformePolicialHomologadoId() == castOther
				.getInformePolicialHomologadoId()) || (this
				.getInformePolicialHomologadoId() != null
				&& castOther.getInformePolicialHomologadoId() != null && this
				.getInformePolicialHomologadoId().equals(
						castOther.getInformePolicialHomologadoId())))
				&& ((this.getCatDelitoId() == castOther.getCatDelitoId()) || (this
						.getCatDelitoId() != null
						&& castOther.getCatDelitoId() != null && this
						.getCatDelitoId().equals(castOther.getCatDelitoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getInformePolicialHomologadoId() == null ? 0 : this
						.getInformePolicialHomologadoId().hashCode());
		result = 37
				* result
				+ (getCatDelitoId() == null ? 0 : this.getCatDelitoId()
						.hashCode());
		return result;
	}

}
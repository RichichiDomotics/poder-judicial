package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SubprocesoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SubprocesoId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2466513831116154205L;
	private Long subprocesoId;
	private Long procesoId;

	// Constructors

	/** default constructor */
	public SubprocesoId() {
	}

	/** full constructor */
	public SubprocesoId(Long subprocesoId, Long procesoId) {
		this.subprocesoId = subprocesoId;
		this.procesoId = procesoId;
	}

	// Property accessors

	@Column(name = "Subproceso_id", nullable = false, precision = 18, scale = 0)
	public Long getSubprocesoId() {
		return this.subprocesoId;
	}

	public void setSubprocesoId(Long subprocesoId) {
		this.subprocesoId = subprocesoId;
	}

	@Column(name = "Proceso_id", nullable = false, precision = 18, scale = 0)
	public Long getProcesoId() {
		return this.procesoId;
	}

	public void setProcesoId(Long procesoId) {
		this.procesoId = procesoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SubprocesoId))
			return false;
		SubprocesoId castOther = (SubprocesoId) other;

		return ((this.getSubprocesoId() == castOther.getSubprocesoId()) || (this
				.getSubprocesoId() != null
				&& castOther.getSubprocesoId() != null && this
				.getSubprocesoId().equals(castOther.getSubprocesoId())))
				&& ((this.getProcesoId() == castOther.getProcesoId()) || (this
						.getProcesoId() != null
						&& castOther.getProcesoId() != null && this
						.getProcesoId().equals(castOther.getProcesoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSubprocesoId() == null ? 0 : this.getSubprocesoId()
						.hashCode());
		result = 37 * result
				+ (getProcesoId() == null ? 0 : this.getProcesoId().hashCode());
		return result;
	}

}
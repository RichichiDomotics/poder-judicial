package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MandamientoAdjuntosId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MandamientoAdjuntosId implements java.io.Serializable {

	// Fields

	private Long mandamientoId;
	private Long archivoDigitalId;

	// Constructors

	/** default constructor */
	public MandamientoAdjuntosId() {
	}

	/** full constructor */
	public MandamientoAdjuntosId(Long mandamientoId, Long archivoDigitalId) {
		this.mandamientoId = mandamientoId;
		this.archivoDigitalId = archivoDigitalId;
	}

	// Property accessors

	@Column(name = "Mandamiento_id", nullable = false, precision = 18, scale = 0)
	public Long getMandamientoId() {
		return this.mandamientoId;
	}

	public void setMandamientoId(Long mandamientoId) {
		this.mandamientoId = mandamientoId;
	}

	@Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0)
	public Long getArchivoDigitalId() {
		return this.archivoDigitalId;
	}

	public void setArchivoDigitalId(Long archivoDigitalId) {
		this.archivoDigitalId = archivoDigitalId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MandamientoAdjuntosId))
			return false;
		MandamientoAdjuntosId castOther = (MandamientoAdjuntosId) other;

		return ((this.getMandamientoId() == castOther.getMandamientoId()) || (this
				.getMandamientoId() != null
				&& castOther.getMandamientoId() != null && this
				.getMandamientoId().equals(castOther.getMandamientoId())))
				&& ((this.getArchivoDigitalId() == castOther
						.getArchivoDigitalId()) || (this.getArchivoDigitalId() != null
						&& castOther.getArchivoDigitalId() != null && this
						.getArchivoDigitalId().equals(
								castOther.getArchivoDigitalId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMandamientoId() == null ? 0 : this.getMandamientoId()
						.hashCode());
		result = 37
				* result
				+ (getArchivoDigitalId() == null ? 0 : this
						.getArchivoDigitalId().hashCode());
		return result;
	}

}
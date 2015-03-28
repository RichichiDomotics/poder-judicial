package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SolicitudAdjuntosId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SolicitudAdjuntosId implements java.io.Serializable {

	// Fields

	private Long solicitudId;
	private Long archivoDigitalId;

	// Constructors

	/** default constructor */
	public SolicitudAdjuntosId() {
	}

	/** full constructor */
	public SolicitudAdjuntosId(Long solicitudId, Long archivoDigitalId) {
		this.solicitudId = solicitudId;
		this.archivoDigitalId = archivoDigitalId;
	}

	// Property accessors

	@Column(name = "Solicitud_id", nullable = false, precision = 18, scale = 0)
	public Long getSolicitudId() {
		return this.solicitudId;
	}

	public void setSolicitudId(Long solicitudId) {
		this.solicitudId = solicitudId;
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
		if (!(other instanceof SolicitudAdjuntosId))
			return false;
		SolicitudAdjuntosId castOther = (SolicitudAdjuntosId) other;

		return ((this.getSolicitudId() == castOther.getSolicitudId()) || (this
				.getSolicitudId() != null
				&& castOther.getSolicitudId() != null && this.getSolicitudId()
				.equals(castOther.getSolicitudId())))
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
				+ (getSolicitudId() == null ? 0 : this.getSolicitudId()
						.hashCode());
		result = 37
				* result
				+ (getArchivoDigitalId() == null ? 0 : this
						.getArchivoDigitalId().hashCode());
		return result;
	}

}
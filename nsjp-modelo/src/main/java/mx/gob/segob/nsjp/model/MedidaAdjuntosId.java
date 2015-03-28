package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MedidaAdjuntosId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class MedidaAdjuntosId implements java.io.Serializable {

	// Fields

	private Long medidaId;
	private Long archivoDigitalId;

	// Constructors

	/** default constructor */
	public MedidaAdjuntosId() {
	}

	/** full constructor */
	public MedidaAdjuntosId(Long medidaId, Long archivoDigitalId) {
		this.medidaId = medidaId;
		this.archivoDigitalId = archivoDigitalId;
	}

	// Property accessors

	@Column(name = "Medida_id", nullable = false, precision = 18, scale = 0)
	public Long getMedidaId() {
		return this.medidaId;
	}

	public void setMedidaId(Long medidaId) {
		this.medidaId = medidaId;
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
		if (!(other instanceof MedidaAdjuntosId))
			return false;
		MedidaAdjuntosId castOther = (MedidaAdjuntosId) other;

		return ((this.getMedidaId() == castOther.getMedidaId()) || (this
				.getMedidaId() != null
				&& castOther.getMedidaId() != null && this.getMedidaId()
				.equals(castOther.getMedidaId())))
				&& ((this.getArchivoDigitalId() == castOther
						.getArchivoDigitalId()) || (this.getArchivoDigitalId() != null
						&& castOther.getArchivoDigitalId() != null && this
						.getArchivoDigitalId().equals(
								castOther.getArchivoDigitalId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMedidaId() == null ? 0 : this.getMedidaId().hashCode());
		result = 37
				* result
				+ (getArchivoDigitalId() == null ? 0 : this
						.getArchivoDigitalId().hashCode());
		return result;
	}

}
package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MandamientoAdjuntos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MandamientoAdjuntos")
public class MandamientoAdjuntos implements java.io.Serializable {

	// Fields

	private MandamientoAdjuntosId id;
	private Mandamiento mandamiento;
	private ArchivoDigital archivoDigital;

	// Constructors

	/** default constructor */
	public MandamientoAdjuntos() {
	}

	/** full constructor */
	public MandamientoAdjuntos(MandamientoAdjuntosId id,
			Mandamiento mandamiento, ArchivoDigital archivoDigital) {
		this.id = id;
		this.mandamiento = mandamiento;
		this.archivoDigital = archivoDigital;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "mandamientoId", column = @Column(name = "Mandamiento_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "archivoDigitalId", column = @Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0)) })
	public MandamientoAdjuntosId getId() {
		return this.id;
	}

	public void setId(MandamientoAdjuntosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Mandamiento_id", nullable = false, insertable = false, updatable = false)
	public Mandamiento getMandamiento() {
		return this.mandamiento;
	}

	public void setMandamiento(Mandamiento mandamiento) {
		this.mandamiento = mandamiento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ArchivoDigital_id", nullable = false, insertable = false, updatable = false)
	public ArchivoDigital getArchivoDigital() {
		return this.archivoDigital;
	}

	public void setArchivoDigital(ArchivoDigital archivoDigital) {
		this.archivoDigital = archivoDigital;
	}

}
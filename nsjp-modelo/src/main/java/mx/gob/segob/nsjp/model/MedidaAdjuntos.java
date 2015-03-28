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
 * MedidaAdjuntos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MedidaAdjuntos")
public class MedidaAdjuntos implements java.io.Serializable {

	// Fields

	private MedidaAdjuntosId id;
	private Medida medida;
	private ArchivoDigital archivoDigital;

	// Constructors

	/** default constructor */
	public MedidaAdjuntos() {
	}

	/** full constructor */
	public MedidaAdjuntos(MedidaAdjuntosId id, Medida medida,
			ArchivoDigital archivoDigital) {
		this.id = id;
		this.medida = medida;
		this.archivoDigital = archivoDigital;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "medidaId", column = @Column(name = "Medida_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "archivoDigitalId", column = @Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0)) })
	public MedidaAdjuntosId getId() {
		return this.id;
	}

	public void setId(MedidaAdjuntosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Medida_id", nullable = false, insertable = false, updatable = false)
	public Medida getMedida() {
		return this.medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
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
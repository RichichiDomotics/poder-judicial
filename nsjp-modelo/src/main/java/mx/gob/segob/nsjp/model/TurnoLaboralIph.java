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
 * TurnoLaboralIph entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TurnoLaboralIPH")
public class TurnoLaboralIph implements java.io.Serializable {

	// Fields

	private TurnoLaboralIphId id;
	private TurnoLaboral turnoLaboral;
	private InformePolicialHomologado informePolicialHomologado;

	// Constructors

	/** default constructor */
	public TurnoLaboralIph() {
	}

	/** full constructor */
	public TurnoLaboralIph(TurnoLaboralIphId id, TurnoLaboral turnoLaboral,
			InformePolicialHomologado informePolicialHomologado) {
		this.id = id;
		this.turnoLaboral = turnoLaboral;
		this.informePolicialHomologado = informePolicialHomologado;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "informePolicialHomologadoId", column = @Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "turnoLaboralId", column = @Column(name = "TurnoLaboral_id", nullable = false, precision = 18, scale = 0)) })
	public TurnoLaboralIphId getId() {
		return this.id;
	}

	public void setId(TurnoLaboralIphId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TurnoLaboral_id", nullable = false, insertable = false, updatable = false)
	public TurnoLaboral getTurnoLaboral() {
		return this.turnoLaboral;
	}

	public void setTurnoLaboral(TurnoLaboral turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InformePolicialHomologado_id", nullable = false, insertable = false, updatable = false)
	public InformePolicialHomologado getInformePolicialHomologado() {
		return this.informePolicialHomologado;
	}

	public void setInformePolicialHomologado(
			InformePolicialHomologado informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}

}
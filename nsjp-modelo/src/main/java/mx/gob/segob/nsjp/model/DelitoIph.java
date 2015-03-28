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
 * DelitoIph entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DelitoIPH")
public class DelitoIph implements java.io.Serializable {

	// Fields

	private DelitoIphId id;
	private CatDelito catDelito;
	private InformePolicialHomologado informePolicialHomologado;

	// Constructors

	/** default constructor */
	public DelitoIph() {
	}

	/** full constructor */
	public DelitoIph(DelitoIphId id, CatDelito catDelito,
			InformePolicialHomologado informePolicialHomologado) {
		this.id = id;
		this.catDelito = catDelito;
		this.informePolicialHomologado = informePolicialHomologado;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "informePolicialHomologadoId", column = @Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "catDelitoId", column = @Column(name = "CatDelito_id", nullable = false, precision = 18, scale = 0)) })
	public DelitoIphId getId() {
		return this.id;
	}

	public void setId(DelitoIphId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatDelito_id", nullable = false, insertable = false, updatable = false)
	public CatDelito getCatDelito() {
		return this.catDelito;
	}

	public void setCatDelito(CatDelito catDelito) {
		this.catDelito = catDelito;
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
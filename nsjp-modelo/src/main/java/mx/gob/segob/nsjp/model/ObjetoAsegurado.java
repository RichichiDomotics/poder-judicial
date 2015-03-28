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
 * ObjetoAsegurado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ObjetoAsegurado")
public class ObjetoAsegurado implements java.io.Serializable {

	// Fields

	private ObjetoAseguradoId id;
	private Objeto objeto;
	private InformePolicialHomologado informePolicialHomologado;

	// Constructors

	/** default constructor */
	public ObjetoAsegurado() {
	}

	/** full constructor */
	public ObjetoAsegurado(ObjetoAseguradoId id, Objeto objeto,
			InformePolicialHomologado informePolicialHomologado) {
		this.id = id;
		this.objeto = objeto;
		this.informePolicialHomologado = informePolicialHomologado;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "informePolicialHomologadoId", column = @Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "objetoId", column = @Column(name = "Objeto_id", nullable = false, precision = 18, scale = 0)) })
	public ObjetoAseguradoId getId() {
		return this.id;
	}

	public void setId(ObjetoAseguradoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Objeto_id", nullable = false, insertable = false, updatable = false)
	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
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
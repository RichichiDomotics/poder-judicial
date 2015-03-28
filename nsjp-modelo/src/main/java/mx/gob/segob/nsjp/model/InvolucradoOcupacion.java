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
 * InvolucradoOcupacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "InvolucradoOcupacion")
public class InvolucradoOcupacion implements java.io.Serializable {

	// Fields

	private InvolucradoOcupacionId id;
	private Involucrado involucrado;
	private Valor valor;

	// Constructors

	/** default constructor */
	public InvolucradoOcupacion() {
	}

	/** full constructor */
	public InvolucradoOcupacion(InvolucradoOcupacionId id,
			Involucrado involucrado, Valor valor) {
		this.id = id;
		this.involucrado = involucrado;
		this.valor = valor;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "valorId", column = @Column(name = "Valor_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "involucradoId", column = @Column(name = "Involucrado_id", nullable = false, precision = 18, scale = 0)) })
	public InvolucradoOcupacionId getId() {
		return this.id;
	}

	public void setId(InvolucradoOcupacionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false, insertable = false, updatable = false)
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Valor_id", nullable = false, insertable = false, updatable = false)
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

}
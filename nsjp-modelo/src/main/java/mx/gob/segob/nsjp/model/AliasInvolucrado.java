package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AliasInvolucrado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AliasInvolucrado" )
public class AliasInvolucrado implements java.io.Serializable {

	// Fields

	private Long aliasInvolucradoId;
	private Involucrado involucrado;
	private String alias;

	// Constructors

	/** default constructor */
	public AliasInvolucrado() {
	}

	/** full constructor */
	public AliasInvolucrado(Long aliasInvolucradoId, Involucrado involucrado,
			String alias) {
		this.aliasInvolucradoId = aliasInvolucradoId;
		this.involucrado = involucrado;
		this.alias = alias;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "AliasInvolucrado_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAliasInvolucradoId() {
		return this.aliasInvolucradoId;
	}

	public void setAliasInvolucradoId(Long aliasInvolucradoId) {
		this.aliasInvolucradoId = aliasInvolucradoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)	
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@Column(name = "cAlias", nullable = false, length = 60)
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}

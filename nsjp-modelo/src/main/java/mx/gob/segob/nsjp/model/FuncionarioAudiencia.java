package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FuncionarioAudiencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FuncionarioAudiencia")
public class FuncionarioAudiencia implements java.io.Serializable {

	// Fields

	private FuncionarioAudienciaId id;
	private Funcionario funcionario;
	private Audiencia audiencia;	
	private Boolean esPresente;
	private Boolean esTitular;
	// Constructors

	/** default constructor */
	public FuncionarioAudiencia() {
	}

	/** full constructor */
	public FuncionarioAudiencia(FuncionarioAudienciaId id,
			Funcionario funcionario, Audiencia audiencia) {
		this.id = id;
		this.funcionario = funcionario;
		this.audiencia = audiencia;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "audienciaId", column = @Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "claveFuncionario", column = @Column(name = "iClaveFuncionario", nullable = false, precision = 18, scale = 0)) })
	public FuncionarioAudienciaId getId() {
		return this.id;
	}

	public void setId(FuncionarioAudienciaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario", nullable = false, insertable = false, updatable = false)
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Audiencia_id", nullable = false, insertable = false, updatable = false)
	public Audiencia getAudiencia() {
		return this.audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}  

    /**
     * Método de acceso al campo esPresente.
     * @return El valor del campo esPresente
     */
    @Column(name = "bEsPresente", precision = 1, scale = 0)
    public Boolean getEsPresente() {
        return esPresente;
    }

    /**
     * Asigna el valor al campo esPresente.
     * @param esPresente el valor esPresente a asignar
     */
    public void setEsPresente(Boolean esPresente) {
        this.esPresente = esPresente;
    }

    /**
     * Método de acceso al campo esTitular.
     * @return El valor del campo esTitular
     */
    @Column(name = "bEsTitular", precision = 1, scale = 0)
    public Boolean getEsTitular() {
        return esTitular;
    }

    /**
     * Asigna el valor al campo esTitular.
     * @param esTitular el valor esTitular a asignar
     */
    public void setEsTitular(Boolean esTitular) {
        this.esTitular = esTitular;
    }
}
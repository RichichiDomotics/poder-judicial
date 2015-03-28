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
@SuppressWarnings("serial")
@Entity
@Table(name = "InvolucradoAudiencia")
public class InvolucradoAudiencia implements java.io.Serializable {

	// Fields

	private InvolucradoAudienciaId id;
	private Involucrado involucrado;
	private Audiencia audiencia;	
	private Boolean esPresente;
	// Constructors

	/** default constructor */
	public InvolucradoAudiencia() {
	}

	/** full constructor */
	public InvolucradoAudiencia(InvolucradoAudienciaId id,
			Involucrado involucrado, Audiencia audiencia) {
		this.id = id;
		this.involucrado = involucrado;
		this.audiencia = audiencia;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "audienciaId", column = @Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "involucradoId", column = @Column(name = "Involucrado_id", nullable = false, precision = 18, scale = 0)) })
	public InvolucradoAudienciaId getId() {
		return this.id;
	}

	public void setId(InvolucradoAudienciaId id) {
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
	
	
	
}

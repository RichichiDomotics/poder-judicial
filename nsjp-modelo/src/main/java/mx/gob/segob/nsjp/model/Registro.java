package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Registro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Registro" )
public class Registro implements java.io.Serializable {

	// Fields

	private Long registroId;
	private Boolean esActivo;
	private Set<Valor> valors = new HashSet<Valor>(0);

	// Constructors

	/** default constructor */
	public Registro() {
	}

	/** minimal constructor */
	public Registro(Long registroId) {
		this.registroId = registroId;
	}

	/** full constructor */
	public Registro(Long registroId, Boolean esActivo, Set<Valor> valors) {
		this.registroId = registroId;
		this.esActivo = esActivo;
		this.valors = valors;
	}

	// Property accessors
	@Id
	@Column(name = "Registro_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRegistroId() {
		return this.registroId;
	}

	public void setRegistroId(Long registroId) {
		this.registroId = registroId;
	}

	@Column(name = "bEsActivo", precision = 1, scale = 0)
	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registro")
	public Set<Valor> getValors() {
		return this.valors;
	}

	public void setValors(Set<Valor> valors) {
		this.valors = valors;
	}

}

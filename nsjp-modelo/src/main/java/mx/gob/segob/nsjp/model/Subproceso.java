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
 * Subproceso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Subproceso")
public class Subproceso implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804431446278652596L;
	private SubprocesoId id;
	private Proceso proceso;
	private String cnombreSubproceso;
	private String cdescripcionSubroceso;
	private String cforward;

	// Constructors

	/** default constructor */
	public Subproceso() {
	}

	/** full constructor */
	public Subproceso(SubprocesoId id, Proceso proceso,
			String cnombreSubproceso, String cdescripcionSubroceso,
			String cforward) {
		this.id = id;
		this.proceso = proceso;
		this.cnombreSubproceso = cnombreSubproceso;
		this.cdescripcionSubroceso = cdescripcionSubroceso;
		this.cforward = cforward;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "subprocesoId", column = @Column(name = "Subproceso_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "procesoId", column = @Column(name = "Proceso_id", nullable = false, precision = 18, scale = 0)) })
	public SubprocesoId getId() {
		return this.id;
	}

	public void setId(SubprocesoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proceso_id", nullable = false, insertable = false, updatable = false)
	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	@Column(name = "cNombreSubproceso", nullable = false, length = 50)
	public String getCnombreSubproceso() {
		return this.cnombreSubproceso;
	}

	public void setCnombreSubproceso(String cnombreSubproceso) {
		this.cnombreSubproceso = cnombreSubproceso;
	}

	@Column(name = "cDescripcionSubroceso", nullable = false, length = 200)
	public String getCdescripcionSubroceso() {
		return this.cdescripcionSubroceso;
	}

	public void setCdescripcionSubroceso(String cdescripcionSubroceso) {
		this.cdescripcionSubroceso = cdescripcionSubroceso;
	}

	@Column(name = "cForward", nullable = false, length = 50)
	public String getCforward() {
		return this.cforward;
	}

	public void setCforward(String cforward) {
		this.cforward = cforward;
	}

}
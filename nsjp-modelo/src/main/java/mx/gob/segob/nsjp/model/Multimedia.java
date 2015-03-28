package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Multimedia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Multimedia")
public class Multimedia implements java.io.Serializable {

	// Fields

	private Long multimediaId;
	private InformePolicialHomologado informePolicialHomologado;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Multimedia() {
	}

	/** full constructor */
	public Multimedia(Long multimediaId,
			InformePolicialHomologado informePolicialHomologado,
			String cdescripcion) {
		this.multimediaId = multimediaId;
		this.informePolicialHomologado = informePolicialHomologado;
		this.descripcion = cdescripcion;
	}

	// Property accessors
	@Id
	@Column(name = "Multimedia_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getMultimediaId() {
		return this.multimediaId;
	}

	public void setMultimediaId(Long multimediaId) {
		this.multimediaId = multimediaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InformePolicialHomologado_id", nullable = false)
	public InformePolicialHomologado getInformePolicialHomologado() {
		return this.informePolicialHomologado;
	}

	public void setInformePolicialHomologado(
			InformePolicialHomologado informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}

	@Column(name = "cDescripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

}
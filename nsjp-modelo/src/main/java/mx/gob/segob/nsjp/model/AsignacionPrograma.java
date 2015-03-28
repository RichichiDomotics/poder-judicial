package mx.gob.segob.nsjp.model;

import javax.persistence.CascadeType;
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
 * AsignacionPrograma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AsignacionPrograma")
public class AsignacionPrograma implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1690537236307969855L;
	private Long asignacionProgramaId;
	private Sentencia sentencia;
	private Programa programa;
	private Boolean baceptado;

	// Constructors

	/** default constructor */
	public AsignacionPrograma() {
	}

	/** full constructor */
	public AsignacionPrograma(Long asignacionProgramaId, Sentencia sentencia,
			Programa programa, Boolean baceptado) {
		this.asignacionProgramaId = asignacionProgramaId;
		this.sentencia = sentencia;
		this.programa = programa;
		this.baceptado = baceptado;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AsignacionPrograma_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAsignacionProgramaId() {
		return this.asignacionProgramaId;
	}

	public void setAsignacionProgramaId(Long asignacionProgramaId) {
		this.asignacionProgramaId = asignacionProgramaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_Id", nullable = true, updatable = false)
	public Sentencia getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "Programa_Id", nullable = true, updatable = false)
	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	@Column(name = "bAceptado", nullable = false, precision = 1, scale = 0)
	public Boolean getBaceptado() {
		return this.baceptado;
	}

	public void setBaceptado(Boolean baceptado) {
		this.baceptado = baceptado;
	}

}
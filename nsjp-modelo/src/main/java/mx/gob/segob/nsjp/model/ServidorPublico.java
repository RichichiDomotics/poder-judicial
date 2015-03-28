package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ServidorPublico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ServidorPublico")
public class ServidorPublico implements java.io.Serializable {

	// Fields

	private Long servidorPublicoId;
	private Involucrado involucrado;
	private Valor tipoDependencia;
	private Valor nivelDependencia;
	private String dependencia;
	private String puesto;
	private String numeroEmpleado;

	// Constructors

	/** default constructor */
	public ServidorPublico() {
	}

	/** minimal constructor */
	public ServidorPublico(Long servidorPublicoId, Involucrado involucrado) {
		this.servidorPublicoId = servidorPublicoId;
		this.involucrado = involucrado;
	}

	/** full constructor */
	public ServidorPublico(Long servidorPublicoId, Involucrado involucrado,
			Valor valorByTipoDependenciaVal, Valor valorByNivelDependenciaVal,
			String cdependencia, String cpuesto, String cnumeroEmpleado) {
		this.servidorPublicoId = servidorPublicoId;
		this.involucrado = involucrado;
		this.tipoDependencia = valorByTipoDependenciaVal;
		this.nivelDependencia = valorByNivelDependenciaVal;
		this.dependencia = cdependencia;
		this.puesto = cpuesto;
		this.numeroEmpleado = cnumeroEmpleado;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "ServidorPublico_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getServidorPublicoId() {
		return this.servidorPublicoId;
	}

	public void setServidorPublicoId(Long servidorPublicoId) {
		this.servidorPublicoId = servidorPublicoId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id",  unique = true, nullable = false)
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoDependencia_val")
	public Valor getTipoDependencia() {
		return this.tipoDependencia;
	}

	public void setTipoDependencia(Valor valorByTipoDependenciaVal) {
		this.tipoDependencia = valorByTipoDependenciaVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NivelDependencia_val")
	public Valor getNivelDependencia() {
		return this.nivelDependencia;
	}

	public void setNivelDependencia(Valor valorByNivelDependenciaVal) {
		this.nivelDependencia = valorByNivelDependenciaVal;
	}

	@Column(name = "cDependencia", length = 100)
	public String getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(String cdependencia) {
		this.dependencia = cdependencia;
	}

	@Column(name = "cPuesto", length = 100)
	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String cpuesto) {
		this.puesto = cpuesto;
	}

	@Column(name = "cNumeroEmpleado", length = 30)
	public String getNumeroEmpleado() {
		return this.numeroEmpleado;
	}

	public void setNumeroEmpleado(String cnumeroEmpleado) {
		this.numeroEmpleado = cnumeroEmpleado;
	}

}
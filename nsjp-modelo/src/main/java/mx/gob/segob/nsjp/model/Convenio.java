package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * AcuerdoRestaurativo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Convenio")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Convenio_id")
public class Convenio extends Documento{

	// Fields
	private Long numeroConvenio;
	private Date fechaInicio;
	private Date fechaFin;
	private Double monto;
	
	private Involucrado involucradoPR;
	private Involucrado involucradoVictima;
	private Funcionario funcionario;
	private Valor periodicidad;
	private CompromisoPeriodico compromisoPeriodico;
	private NumeroExpediente numeroExpediente;
	private Valor tipoConvenio;
	

	
	// Constructors

	/** default constructor */
	public Convenio() {
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario", nullable = false)
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Periodicidad_val")
	public Valor getPeriodicidad() {
		return this.periodicidad;
	}

	public void setPeriodicidad(Valor valor) {
		this.periodicidad = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getInvolucradoPR() {
		return this.involucradoPR;
	}

	public void setInvolucradoPR(Involucrado involucrado) {
		this.involucradoPR = involucrado;
	}


	@Column(name = "iNumeroConvenio", precision = 18, scale = 0)
	public Long getNumeroMediacion() {
		return this.numeroConvenio;
	}

	public void setNumeroMediacion(Long inumeroMediacion) {
		this.numeroConvenio = inumeroMediacion;
	}

	@Column(name = "dFechaInicio", length = 23)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date dfechaInicio) {
		this.fechaInicio = dfechaInicio;
	}

	@Column(name = "dFechaFin", length = 23)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date dfechaFin) {
		this.fechaFin = dfechaFin;
	}

	@Column(name = "dcMonto", precision = 5)
	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double dcMonto) {
		this.monto = dcMonto;
	}
	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * @return the numeroExpediente
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NumeroExpediente_id", nullable = false)
	public NumeroExpediente getNumeroExpediente() {
		return numeroExpediente;
	}


	/**
	 * @param compromisoPeriodico the compromisoPeriodico to set
	 */
	public void setCompromisoPeriodico(CompromisoPeriodico compromisoPeriodico) {
		this.compromisoPeriodico = compromisoPeriodico;
	}


	/**
	 * @return the compromisoPeriodico
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CompromisoPeriodico_id", nullable=true)
	public CompromisoPeriodico getCompromisoPeriodico() {
		return compromisoPeriodico;
	}


	/**
	 * Método de acceso al campo tipoConvenio.
	 * @return El valor del campo tipoConvenio
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TipoConvenio_val")
	public Valor getTipoConvenio() {
		return tipoConvenio;
	}


	/**
	 * Asigna el valor al campo tipoConvenio.
	 * @param tipoConvenio el valor tipoConvenio a asignar
	 */
	public void setTipoConvenio(Valor tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}



	/**
	 * Método de acceso al campo involucradoVictima.
	 * @return El valor del campo involucradoVictima
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InvolucradoVictima_id", nullable = false)
	public Involucrado getInvolucradoVictima() {
		return involucradoVictima;
	}


	/**
	 * Asigna el valor al campo involucradoVictima.
	 * @param involucradoVictima el valor involucradoVictima a asignar
	 */
	public void setInvolucradoVictima(Involucrado involucradoVictima) {
		this.involucradoVictima = involucradoVictima;
	}
	
	
	
	
	
}
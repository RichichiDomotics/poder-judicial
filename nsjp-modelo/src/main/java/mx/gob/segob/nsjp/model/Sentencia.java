package mx.gob.segob.nsjp.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Sentencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Sentencia")
public class Sentencia implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 46315515441431591L;
	private Long sentenciaId;
	private NumeroExpediente numeroExpediente;
	private Valor valor;
	private Involucrado involucrado;
	private String cnus;
	private String cnumeroCausa;
	private Boolean blesionado;
	private Date dfechaInicioPena;
	private Date dfechaFinPena;
	private Long ipuntosPorAcumular;
	private Boolean bcumplida;
	private Set<AsignacionPrograma> asignacionProgramas = new HashSet<AsignacionPrograma>(0);
	private Set<AsignacionMedidaAlterna> asignacionMedidaAlternas = new HashSet<AsignacionMedidaAlterna>(0);
	private Set<Remision> remisions = new HashSet<Remision>(0);
	private Set<AsignacionCentroDetencion> asignacionCentroDetencions = new HashSet<AsignacionCentroDetencion>(0);
	private Set<ActoBuenaConducta> actoBuenaConductas = new HashSet<ActoBuenaConducta>(0);
	private InventarioPertenencia inventarioPertenencia;

	// Constructors

	/** default constructor */
	public Sentencia() {
	}

	/** minimal constructor */
	public Sentencia(Long sentenciaId, NumeroExpediente numeroExpediente,
			Valor valor, Involucrado involucrado, String cnus,
			String cnumeroCausa, Date dfechaInicioPena,
			Date dfechaFinPena, Long ipuntosPorAcumular, Boolean bcumplida) {
		this.sentenciaId = sentenciaId;
		this.numeroExpediente = numeroExpediente;
		this.valor = valor;
		this.involucrado = involucrado;
		this.cnus = cnus;
		this.cnumeroCausa = cnumeroCausa;
		this.dfechaInicioPena = dfechaInicioPena;
		this.dfechaFinPena = dfechaFinPena;
		this.ipuntosPorAcumular = ipuntosPorAcumular;
		this.bcumplida = bcumplida;
	}

	/** full constructor */
	public Sentencia(Long sentenciaId, NumeroExpediente numeroExpediente,
			Valor valor, Involucrado involucrado, String cnus,
			String cnumeroCausa, Boolean blesionado,
			Date dfechaInicioPena, Date dfechaFinPena,
			Long ipuntosPorAcumular, Boolean bcumplida,
			Set<AsignacionPrograma> asignacionProgramas,
			Set<AsignacionMedidaAlterna> asignacionMedidaAlternas,
			Set<Remision> remisions,
			Set<AsignacionCentroDetencion> asignacionCentroDetencions,
			Set<ActoBuenaConducta> actoBuenaConductas) {
		this.sentenciaId = sentenciaId;
		this.numeroExpediente = numeroExpediente;
		this.valor = valor;
		this.involucrado = involucrado;
		this.cnus = cnus;
		this.cnumeroCausa = cnumeroCausa;
		this.blesionado = blesionado;
		this.dfechaInicioPena = dfechaInicioPena;
		this.dfechaFinPena = dfechaFinPena;
		this.ipuntosPorAcumular = ipuntosPorAcumular;
		this.bcumplida = bcumplida;
		this.asignacionProgramas = asignacionProgramas;
		this.asignacionMedidaAlternas = asignacionMedidaAlternas;
		this.remisions = remisions;
		this.asignacionCentroDetencions = asignacionCentroDetencions;
		this.actoBuenaConductas = actoBuenaConductas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Sentencia_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSentenciaId() {
		return this.sentenciaId;
	}

	public void setSentenciaId(Long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NumeroExpediente_id", nullable = false)
	public NumeroExpediente getNumeroExpediente() {
		return this.numeroExpediente;
	}

	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoSentencia_val", nullable = false)
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@Column(name = "cNUS", nullable = false, length = 200)
	public String getCnus() {
		return this.cnus;
	}

	public void setCnus(String cnus) {
		this.cnus = cnus;
	}

	@Column(name = "cNumeroCausa", nullable = false, length = 200)
	public String getCnumeroCausa() {
		return this.cnumeroCausa;
	}

	public void setCnumeroCausa(String cnumeroCausa) {
		this.cnumeroCausa = cnumeroCausa;
	}

	@Column(name = "bLesionado", precision = 1, scale = 0)
	public Boolean getBlesionado() {
		return this.blesionado;
	}

	public void setBlesionado(Boolean blesionado) {
		this.blesionado = blesionado;
	}

	@Column(name = "dFechaInicioPena", nullable = false, length = 23)
	public Date getDfechaInicioPena() {
		return this.dfechaInicioPena;
	}

	public void setDfechaInicioPena(Date dfechaInicioPena) {
		this.dfechaInicioPena = dfechaInicioPena;
	}

	@Column(name = "dFechaFinPena", nullable = false, length = 23)
	public Date getDfechaFinPena() {
		return this.dfechaFinPena;
	}

	public void setDfechaFinPena(Date dfechaFinPena) {
		this.dfechaFinPena = dfechaFinPena;
	}

	@Column(name = "iPuntosPorAcumular", nullable = false, precision = 18, scale = 0)
	public Long getIpuntosPorAcumular() {
		return this.ipuntosPorAcumular;
	}

	public void setIpuntosPorAcumular(Long ipuntosPorAcumular) {
		this.ipuntosPorAcumular = ipuntosPorAcumular;
	}

	@Column(name = "bCumplida", nullable = false, precision = 1, scale = 0)
	public Boolean getBcumplida() {
		return this.bcumplida;
	}

	public void setBcumplida(Boolean bcumplida) {
		this.bcumplida = bcumplida;
	}

	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "sentencia")
	public Set<AsignacionPrograma> getAsignacionProgramas() {
		return this.asignacionProgramas;
	}

	public void setAsignacionProgramas(
			Set<AsignacionPrograma> asignacionProgramas) {
		this.asignacionProgramas = asignacionProgramas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sentencia")
	public Set<AsignacionMedidaAlterna> getAsignacionMedidaAlternas() {
		return this.asignacionMedidaAlternas;
	}

	public void setAsignacionMedidaAlternas(
			Set<AsignacionMedidaAlterna> asignacionMedidaAlternas) {
		this.asignacionMedidaAlternas = asignacionMedidaAlternas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sentencia")
	public Set<Remision> getRemisions() {
		return this.remisions;
	}

	public void setRemisions(Set<Remision> remisions) {
		this.remisions = remisions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sentencia")
	public Set<AsignacionCentroDetencion> getAsignacionCentroDetencions() {
		return this.asignacionCentroDetencions;
	}

	public void setAsignacionCentroDetencions(
			Set<AsignacionCentroDetencion> asignacionCentroDetencions) {
		this.asignacionCentroDetencions = asignacionCentroDetencions;
	}

	/**
	 * Método de acceso al campo actoBuenaConductas.
	 * @return El valor del campo actoBuenaConductas
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sentencia")
	public Set<ActoBuenaConducta> getActoBuenaConductas() {
		return actoBuenaConductas;
	}

	/**
	 * Asigna el valor al campo actoBuenaConductas.
	 * @param actoBuenaConductas el valor actoBuenaConductas a asignar
	 */
	public void setActoBuenaConductas(Set<ActoBuenaConducta> actoBuenaConductas) {
		this.actoBuenaConductas = actoBuenaConductas;
	}

	/**
	 * Método de acceso al campo inventarioPertenencia.
	 * @return El valor del campo inventarioPertenencia
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "sentencia")
	public InventarioPertenencia getInventarioPertenencia() {
		return inventarioPertenencia;
	}

	/**
	 * Asigna el valor al campo inventarioPertenencia.
	 * @param inventarioPertenencia el valor inventarioPertenencia a asignar
	 */
	public void setInventarioPertenencia(InventarioPertenencia inventarioPertenencia) {
		this.inventarioPertenencia = inventarioPertenencia;
	}

}
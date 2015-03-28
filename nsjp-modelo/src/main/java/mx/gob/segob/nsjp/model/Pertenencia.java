package mx.gob.segob.nsjp.model;

import java.util.Date;

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
 * Pertenencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Pertenencia")
public class Pertenencia implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513287506743722329L;
	
	private Long pertenenciaId;
	private Valor condicionFisica;
	private Detencion detencion;
	private Valor tipoPertenencia;
	private String descripcion;
	private Short cantidad;
	private Boolean esDevuelto;
	private Date fechaDevolucion;
	private Valor unidadMedida;
	private InventarioPertenencia inventarioPertenencia;

	// Constructors

	/** default constructor */
	public Pertenencia() {
	}

	/** minimal constructor */
	public Pertenencia(Long pertenenciaId, Valor valorByCondicionFisicaVal,
			Detencion detencion, Valor valorByTipoPertenenciaVal,
			String cdescripcion) {
		this.pertenenciaId = pertenenciaId;
		this.condicionFisica = valorByCondicionFisicaVal;
		this.detencion = detencion;
		this.tipoPertenencia = valorByTipoPertenenciaVal;
		this.descripcion = cdescripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pertenencia_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getPertenenciaId() {
		return this.pertenenciaId;
	}

	public void setPertenenciaId(Long pertenenciaId) {
		this.pertenenciaId = pertenenciaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CondicionFisica_val", nullable = false)
	public Valor getCondicionFisica() {
		return this.condicionFisica;
	}

	public void setCondicionFisica(Valor valorByCondicionFisicaVal) {
		this.condicionFisica = valorByCondicionFisicaVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Detencion_id", nullable = false)
	public Detencion getDetencion() {
		return this.detencion;
	}

	public void setDetencion(Detencion detencion) {
		this.detencion = detencion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoPertenencia_val", nullable = false)
	public Valor getTipoPertenencia() {
		return this.tipoPertenencia;
	}

	public void setTipoPertenencia(Valor valorByTipoPertenenciaVal) {
		this.tipoPertenencia = valorByTipoPertenenciaVal;
	}

	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

	@Column(name = "iCantidad", precision = 4, scale = 0)
	public Short getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Short icantidad) {
		this.cantidad = icantidad;
	}

	@Column(name = "bEsDevuelto", precision = 1, scale = 0)
	public Boolean getEsDevuelto() {
		return this.esDevuelto;
	}

	public void setEsDevuelto(Boolean besDevuelto) {
		this.esDevuelto = besDevuelto;
	}

	@Column(name = "dFechaDevolucion", length = 23)
	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}

	public void setFechaDevolucion(Date dfechaDevolucion) {
		this.fechaDevolucion = dfechaDevolucion;
	}

	/**
	 * Método de acceso al campo unidadMedida.
	 * @return El valor del campo unidadMedida
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UnidadMedida_val", nullable = true)
	public Valor getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * Asigna el valor al campo unidadMedida.
	 * @param unidadMedida el valor unidadMedida a asignar
	 */
	public void setUnidadMedida(Valor unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * Método de acceso al campo inventarioPertenencia.
	 * @return El valor del campo inventarioPertenencia
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InventarioPertenencia_id", nullable = true)
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
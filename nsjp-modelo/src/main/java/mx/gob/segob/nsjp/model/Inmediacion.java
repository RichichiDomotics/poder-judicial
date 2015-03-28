package mx.gob.segob.nsjp.model;

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
 * Inmediacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Inmediacion" )
public class Inmediacion implements java.io.Serializable {

	// Fields

	private Long inmediacionId;
	private Lugar lugar;
	private Valor valor;
	private String descripcion;

	// Constructors

	/** default constructor */
	public Inmediacion() {
	}

	/** minimal constructor */
	public Inmediacion(Long inmediacionId, Lugar lugar, Valor valor) {
		this.inmediacionId = inmediacionId;
		this.lugar = lugar;
		this.valor = valor;
	}

	/** full constructor */
	public Inmediacion(Long inmediacionId, Lugar lugar, Valor valor,
			String descripcion) {
		this.inmediacionId = inmediacionId;
		this.lugar = lugar;
		this.valor = valor;
		this.descripcion = descripcion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Inmediacion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getNmediacionId() {
		return this.inmediacionId;
	}

	public void setNmediacionId(Long inmediacionId) {
		this.inmediacionId = inmediacionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Lugar_id", nullable = false)
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoInmediacion_val", nullable = false)
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	@Column(name = "cDescripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

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
import javax.persistence.UniqueConstraint;

/**
 * Tarea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Tarea", uniqueConstraints = @UniqueConstraint(columnNames = "EventoCita_id"))
public class Tarea implements java.io.Serializable {

	// Fields

	private Long tareaId;
	private Valor valor;
	private EventoCita eventoCita;
	private Short ntiempoReal;

	// Constructors

	/** default constructor */
	public Tarea() {
	}

	/** minimal constructor */
	public Tarea(Long tareaId, Valor valor, EventoCita eventoCita) {
		this.tareaId = tareaId;
		this.valor = valor;
		this.eventoCita = eventoCita;
	}

	/** full constructor */
	public Tarea(Long tareaId, Valor valor, EventoCita eventoCita,
			Short ntiempoReal) {
		this.tareaId = tareaId;
		this.valor = valor;
		this.eventoCita = eventoCita;
		this.ntiempoReal = ntiempoReal;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Tarea_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getTareaId() {
		return this.tareaId;
	}

	public void setTareaId(Long tareaId) {
		this.tareaId = tareaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTarea_val", nullable = false)
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EventoCita_id", unique = true, nullable = false)
	public EventoCita getEventoCita() {
		return this.eventoCita;
	}

	public void setEventoCita(EventoCita eventoCita) {
		this.eventoCita = eventoCita;
	}

	@Column(name = "nTiempoReal", precision = 4, scale = 0)
	public Short getNtiempoReal() {
		return this.ntiempoReal;
	}

	public void setNtiempoReal(Short ntiempoReal) {
		this.ntiempoReal = ntiempoReal;
	}

}
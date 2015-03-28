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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * AgendaFuncionario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AgendaFuncionario", uniqueConstraints = @UniqueConstraint(columnNames = "iClaveFuncionario"))
public class AgendaFuncionario implements java.io.Serializable {

	// Fields

	private Long agendaFuncionarioId;
	private Funcionario funcionario;
	private Date dinicioAgenda;
	private Date dfinAgenda;
	private Set<EventoCita> eventoCitas = new HashSet<EventoCita>(0);

	// Constructors

	/** default constructor */
	public AgendaFuncionario() {
	}

	/** minimal constructor */
	public AgendaFuncionario(Long agendaFuncionarioId, Funcionario funcionario,
			Date dinicioAgenda) {
		this.agendaFuncionarioId = agendaFuncionarioId;
		this.funcionario = funcionario;
		this.dinicioAgenda = dinicioAgenda;
	}

	/** full constructor */
	public AgendaFuncionario(Long agendaFuncionarioId, Funcionario funcionario,
			Date dinicioAgenda, Date dfinAgenda,
			Set<EventoCita> eventoCitas) {
		this.agendaFuncionarioId = agendaFuncionarioId;
		this.funcionario = funcionario;
		this.dinicioAgenda = dinicioAgenda;
		this.dfinAgenda = dfinAgenda;
		this.eventoCitas = eventoCitas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AgendaFuncionario_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAgendaFuncionarioId() {
		return this.agendaFuncionarioId;
	}

	public void setAgendaFuncionarioId(Long agendaFuncionarioId) {
		this.agendaFuncionarioId = agendaFuncionarioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario", unique = true, nullable = false)
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Column(name = "dInicioAgenda", nullable = false, length = 23)
	public Date getDinicioAgenda() {
		return this.dinicioAgenda;
	}

	public void setDinicioAgenda(Date dinicioAgenda) {
		this.dinicioAgenda = dinicioAgenda;
	}

	@Column(name = "dfinAgenda", length = 23)
	public Date getDfinAgenda() {
		return this.dfinAgenda;
	}

	public void setDfinAgenda(Date dfinAgenda) {
		this.dfinAgenda = dfinAgenda;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agendaFuncionario")
	public Set<EventoCita> getEventoCitas() {
		return this.eventoCitas;
	}

	public void setEventoCitas(Set<EventoCita> eventoCitas) {
		this.eventoCitas = eventoCitas;
	}

}
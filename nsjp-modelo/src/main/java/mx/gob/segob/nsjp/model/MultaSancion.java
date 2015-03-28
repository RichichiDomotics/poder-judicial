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
 * MultaSancion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MultaSancion")
public class MultaSancion implements java.io.Serializable {

	// Fields

	private Long multaSancionId;
	private Funcionario funcionarioMultado;
	private Funcionario funcionarioRegistra;
	private Valor estatus;
	private Inspeccion inspeccion;
	private String folioMultaSancion;
	private Date fechaRegistro;
	private String descripcion;
	private String motivo;

	// Constructors

	/** default constructor */
	public MultaSancion() {
	}

	/**
	 * 
	 * @param msId
	 */
	public MultaSancion(Long msId) {
	    this.multaSancionId = msId;
    }


    // Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MultaSancion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getMultaSancionId() {
		return this.multaSancionId;
	}

	public void setMultaSancionId(Long multaSancionId) {
		this.multaSancionId = multaSancionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionarioMultado", nullable = false)
	public Funcionario getFuncionarioMultado() {
		return this.funcionarioMultado;
	}

	public void setFuncionarioMultado(
			Funcionario funcionarioByIclaveFuncionarioMultado) {
		this.funcionarioMultado = funcionarioByIclaveFuncionarioMultado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionarioRegistra", nullable = false)
	public Funcionario getFuncionarioRegistra() {
		return this.funcionarioRegistra;
	}

	public void setFuncionarioRegistra(
			Funcionario funcionarioByIclaveFuncionarioRegistra) {
		this.funcionarioRegistra = funcionarioByIclaveFuncionarioRegistra;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Estatus_val", nullable = false)
	public Valor getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Valor valor) {
		this.estatus = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Inspeccion_id")
	public Inspeccion getInspeccion() {
		return this.inspeccion;
	}

	public void setInspeccion(Inspeccion inspeccion) {
		this.inspeccion = inspeccion;
	}

	@Column(name = "cFolioMultaSancion", nullable = false, length = 15)
	public String getFolioMultaSancion() {
		return this.folioMultaSancion;
	}

	public void setFolioMultaSancion(String cfolioMultaSancion) {
		this.folioMultaSancion = cfolioMultaSancion;
	}

	@Column(name = "dFechaRegistro", nullable = false, length = 23)
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date dfechaRegistro) {
		this.fechaRegistro = dfechaRegistro;
	}

	@Column(name = "cDescripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

	@Column(name = "cMotivo", nullable = false, length = 50)
	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String cmotivo) {
		this.motivo = cmotivo;
	}

}
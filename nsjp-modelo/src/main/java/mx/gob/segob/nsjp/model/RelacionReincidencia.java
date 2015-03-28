package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Elemento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RelacionReincidencia")
@Inheritance(strategy=InheritanceType.JOINED)
public class RelacionReincidencia implements java.io.Serializable {

	private static final long serialVersionUID = 6571880416627901175L;
	
	// Fields
	private Long relacionReincidenciaId;
	private java.util.Date fechaRelacion;	
	//Caso relacionado
	private Caso caso;
	//Funcionario relaciona
    private Funcionario funcionario;
    private Elemento elemento;
    

	// Constructors

	/** default constructor */
	public RelacionReincidencia() {
	}	
	
	/**
	 * @param relacionReincidenciaId
	 */
	public RelacionReincidencia(Long relacionReincidenciaId) {
		super();
		this.relacionReincidenciaId = relacionReincidenciaId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RelacionReincidencia_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelacionReincidenciaId() {
		return this.relacionReincidenciaId;
	}

	public void setRelacionReincidenciaId(Long relacionReincidenciaId) {
		this.relacionReincidenciaId = relacionReincidenciaId;
	}

	/**
	 * Método de acceso al campo fechaRelacion.
	 * @return El valor del campo fechaRelacion
	 */
	@Column(name = "dFechaRelacion", nullable = false, length = 23)
	public java.util.Date getFechaRelacion() {
		return fechaRelacion;
	}

	/**
	 * Asigna el valor al campo fechaRelacion.
	 * @param fechaRelacion el valor fechaRelacion a asignar
	 */
	public void setFechaRelacion(java.util.Date fechaRelacion) {
		this.fechaRelacion = fechaRelacion;
	}

	/**
	 * Método de acceso al campo caso.
	 * @return El valor del campo caso
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Caso_id", nullable = false)
	public Caso getCaso() {
		return caso;
	}

	/**
	 * Asigna el valor al campo caso.
	 * @param caso el valor caso a asignar
	 */
	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	/**
	 * Método de acceso al campo funcionario.
	 * @return El valor del campo funcionario
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Funcionario_id", nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * Asigna el valor al campo funcionario.
	 * @param funcionario el valor funcionario a asignar
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Método de acceso al campo elemento.
	 * @return El valor del campo elemento
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Elemento_id", nullable = false)
	public Elemento getElemento() {
		return elemento;
	}

	/**
	 * Asigna el valor al campo elemento.
	 * @param elemento el valor elemento a asignar
	 */
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	
	
}

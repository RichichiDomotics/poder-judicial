/**
 * Nombre del Programa : Interviniente.java
 * Autor                            : LuisMG
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 07/10/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Interviniente")
public class Interviniente implements java.io.Serializable {

	private Long intervinienteId;
	private Boolean esAceptado;
	private Boolean esParteFiscalia;
	private Boolean esSuperVeniente;
	private Boolean esPresente;
	private Boolean esTitular;

	private Involucrado involucrado;
	private Funcionario funcionario;
	private Audiencia audiencia;
	
	/**
	 * default constructor
	 */

	// Constructors
	public Interviniente() {
	}

	/**
	 * minimal constructor
	 * 
	 * @param intervinienteId
	 */

	public Interviniente(Long intervinienteId) {
		this.intervinienteId = intervinienteId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Interviniente_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getIntervinienteId() {
		return intervinienteId;
	}

	public void setIntervinienteId(Long intervinienteId) {
		this.intervinienteId = intervinienteId;
	}






	
	
	@Column(name = "bEsAceptado", precision = 1, scale = 0)
		public Boolean getEsAceptado() {
		return esAceptado;
	}

	public void setEsAceptado(Boolean esAceptado) {
		this.esAceptado = esAceptado;
	}
	@Column(name = "bEsParteFiscalia", precision = 1, scale = 0)
		public Boolean getEsParteFiscalia() {
		return esParteFiscalia;
	}

	public void setEsParteFiscalia(Boolean esParteFiscalia) {
		this.esParteFiscalia = esParteFiscalia;
	}
	@Column(name = "bEsSuperveniente", precision = 1, scale = 0)
		public Boolean getEsSuperVeniente() {
		return esSuperVeniente;
	}

	public void setEsSuperVeniente(Boolean esSuperVeniente) {
		this.esSuperVeniente = esSuperVeniente;
	}

	@Column(name = "bEsPresente", precision = 1, scale = 0)
		public Boolean getEsPresente() {
		return esPresente;
	}

	public void setEsPresente(Boolean esPresente) {
		this.esPresente = esPresente;
	}
	
	@Column(name = "bEsTitular", precision = 1, scale = 0)
	public Boolean getEsTitular() {
		return esTitular;
	}

	public void setEsTitular(Boolean esTitular) {
		this.esTitular = esTitular;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id")
	public Involucrado getInvolucrado() {
		return involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario")
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Audiencia_id", nullable=false)
	public Audiencia getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}
	

	
}

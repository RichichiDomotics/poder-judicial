package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Biometrico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Biometrico" )
public class Biometrico implements java.io.Serializable {

	// Fields

	private Long biometricoId;
	private Involucrado involucrado;
	private String rasgoBiometrico;

	// Constructors

	/** default constructor */
	public Biometrico() {
	}

	/** minimal constructor */
	public Biometrico(Long biometricoId, Involucrado involucrado) {
		this.biometricoId = biometricoId;
		this.involucrado = involucrado;
	}

	/** full constructor */
	public Biometrico(Long biometricoId, Involucrado involucrado,
			String rasgoBiometrico) {
		this.biometricoId = biometricoId;
		this.involucrado = involucrado;
		this.rasgoBiometrico = rasgoBiometrico;
	}

	// Property accessors
	@Id
	@Column(name = "Biometrico_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getBiometricoId() {
		return this.biometricoId;
	}

	public void setBiometricoId(Long biometricoId) {
		this.biometricoId = biometricoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@Column(name = "cRasgoBiometrico", length = 50)
	public String getRasgoBiometrico() {
		return this.rasgoBiometrico;
	}

	public void setRasgoBiometrico(String rasgoBiometrico) {
		this.rasgoBiometrico = rasgoBiometrico;
	}

}

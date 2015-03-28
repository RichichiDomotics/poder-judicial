package mx.gob.segob.nsjp.model;

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

/**
 * Direccion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Direccion")
public class Direccion implements java.io.Serializable {

	// Fields

	private Long direccionId;
	private Asentamiento asentamiento;
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String numeroLote;
	private String referencias;
	private String entreCalle1;
	private String entreCalle2;
	private Set<CentroDetencion> centroDetencions = new HashSet<CentroDetencion>(
			0);

	// Constructors

	/** default constructor */
	public Direccion() {
	}

	/** minimal constructor */
	public Direccion(Long direccionId, Asentamiento asentamiento,
			String ccalle, String cnumeroExterior) {
		this.direccionId = direccionId;
		this.asentamiento = asentamiento;
		this.calle = ccalle;
		this.numeroExterior = cnumeroExterior;
	}

	/** full constructor */
	public Direccion(Long direccionId, Asentamiento asentamiento,
			String ccalle, String cnumeroExterior, String cnumeroInterior,
			String cnumeroLote, String creferencias, String centreCalle1,
			String centreCalle2, Set<CentroDetencion> centroDetencions) {
		this.direccionId = direccionId;
		this.asentamiento = asentamiento;
		this.calle = ccalle;
		this.numeroExterior = cnumeroExterior;
		this.numeroInterior = cnumeroInterior;
		this.numeroLote = cnumeroLote;
		this.referencias = creferencias;
		this.entreCalle1 = centreCalle1;
		this.entreCalle2 = centreCalle2;
		this.centroDetencions = centroDetencions;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Direccion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getDireccionId() {
		return this.direccionId;
	}

	public void setDireccionId(Long direccionId) {
		this.direccionId = direccionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Asentamiento_id", nullable = false)
	public Asentamiento getAsentamiento() {
		return this.asentamiento;
	}

	public void setAsentamiento(Asentamiento asentamiento) {
		this.asentamiento = asentamiento;
	}

	@Column(name = "cCalle", nullable = false, length = 50)
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String ccalle) {
		this.calle = ccalle;
	}

	@Column(name = "cNumeroExterior", nullable = false, length = 15)
	public String getNumeroExterior() {
		return this.numeroExterior;
	}

	public void setNumeroExterior(String cnumeroExterior) {
		this.numeroExterior = cnumeroExterior;
	}

	@Column(name = "cNumeroInterior", length = 15)
	public String getNumeroInterior() {
		return this.numeroInterior;
	}

	public void setNumeroInterior(String cnumeroInterior) {
		this.numeroInterior = cnumeroInterior;
	}

	@Column(name = "cNumeroLote", length = 10)
	public String getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(String cnumeroLote) {
		this.numeroLote = cnumeroLote;
	}

	@Column(name = "cReferencias", length = 60)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String creferencias) {
		this.referencias = creferencias;
	}

	@Column(name = "cEntreCalle1", length = 60)
	public String getEntreCalle1() {
		return this.entreCalle1;
	}

	public void setEntreCalle1(String centreCalle1) {
		this.entreCalle1 = centreCalle1;
	}

	@Column(name = "cEntreCalle2", length = 60)
	public String getEntreCalle2() {
		return this.entreCalle2;
	}

	public void setEntreCalle2(String centreCalle2) {
		this.entreCalle2 = centreCalle2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "direccion")
	public Set<CentroDetencion> getCentroDetencions() {
		return this.centroDetencions;
	}

	public void setCentroDetencions(Set<CentroDetencion> centroDetencions) {
		this.centroDetencions = centroDetencions;
	}

}
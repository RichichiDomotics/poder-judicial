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
 * CatTrabajo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatTrabajo")
public class CatTrabajo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7536857600153820228L;
	private Long catTrabajoId;
	private CatTipoTrabajoExterno catTipoTrabajoExterno;
	private String cnombre;
	private String cdescripcion;
	private Boolean besExterno;
	private String cnumeroConvenio;
	private Long ipuntos;
	private Boolean bActivo;

	// Constructors

	/** default constructor */
	public CatTrabajo() {
	}

	/** minimal constructor */
	public CatTrabajo(Long catTrabajoId,
			CatTipoTrabajoExterno catTipoTrabajoExterno, String cnombre, String cdescripcion,
			Boolean besExterno, String cnumeroConvenio, Long ipuntos, Boolean bActivo) {
		this.catTrabajoId = catTrabajoId;
		this.catTipoTrabajoExterno = catTipoTrabajoExterno;
		this.cnombre = cnombre;
		this.cdescripcion = cdescripcion;
		this.besExterno = besExterno;
		this.cnumeroConvenio = cnumeroConvenio;
		this.ipuntos = ipuntos;
		this.bActivo = bActivo;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatTrabajo_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatTrabajoId() {
		return this.catTrabajoId;
	}

	public void setCatTrabajoId(Long catTrabajoId) {
		this.catTrabajoId = catTrabajoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTipoTrabajoExterno_id", nullable = true)
	public CatTipoTrabajoExterno getCatTipoTrabajoExterno() {
		return this.catTipoTrabajoExterno;
	}

	public void setCatTipoTrabajoExterno(
			CatTipoTrabajoExterno catTipoTrabajoExterno) {
		this.catTipoTrabajoExterno = catTipoTrabajoExterno;
	}

	@Column(name = "cNombre", nullable = false, length = 200)
	public String getCnombre() {
		return this.cnombre;
	}

	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}	
	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

	@Column(name = "bEsExterno", nullable = false, precision = 1, scale = 0)
	public Boolean getBesExterno() {
		return this.besExterno;
	}

	public void setBesExterno(Boolean besExterno) {
		this.besExterno = besExterno;
	}

	@Column(name = "cNumeroConvenio", nullable = true, length = 200)
	public String getCnumeroConvenio() {
		return this.cnumeroConvenio;
	}

	public void setCnumeroConvenio(String cnumeroConvenio) {
		this.cnumeroConvenio = cnumeroConvenio;
	}

	@Column(name = "iPuntos", nullable = false, precision = 18, scale = 0)
	public Long getIpuntos() {
		return this.ipuntos;
	}

	public void setIpuntos(Long ipuntos) {
		this.ipuntos = ipuntos;
	}
	
	@Column(name = "bActivo", nullable = false, precision = 1, scale = 0)
	public Boolean getBActivo() {
		return this.bActivo;
	}

	public void setBActivo(Boolean bActivo) {
		this.bActivo = bActivo;
	}
	
}
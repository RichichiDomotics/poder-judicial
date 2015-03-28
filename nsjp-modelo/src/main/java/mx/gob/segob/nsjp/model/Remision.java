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
 * Remision entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Remision")
public class Remision implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3434281466206894533L;
	private Long remisionId;
	private CatTipoRemision catTipoRemision;
	private Sentencia sentencia;
	private Long imontoDanioReparado;
	private Long idiasAcreditados;

	// Constructors

	/** default constructor */
	public Remision() {
	}

	/** minimal constructor */
	public Remision(Long remisionId, CatTipoRemision catTipoRemision,
			Sentencia sentencia) {
		this.remisionId = remisionId;
		this.catTipoRemision = catTipoRemision;
		this.sentencia = sentencia;
	}

	/** full constructor */
	public Remision(Long remisionId, CatTipoRemision catTipoRemision,
			Sentencia sentencia, Long imontoDanioReparado, Long idiasAcreditados) {
		this.remisionId = remisionId;
		this.catTipoRemision = catTipoRemision;
		this.sentencia = sentencia;
		this.imontoDanioReparado = imontoDanioReparado;
		this.idiasAcreditados = idiasAcreditados;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Remision_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRemisionId() {
		return this.remisionId;
	}

	public void setRemisionId(Long remisionId) {
		this.remisionId = remisionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatTipoRemision_Id", nullable = false)
	public CatTipoRemision getCatTipoRemision() {
		return this.catTipoRemision;
	}

	public void setCatTipoRemision(CatTipoRemision catTipoRemision) {
		this.catTipoRemision = catTipoRemision;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_Id", nullable = true, updatable = false)
	public Sentencia getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}

	@Column(name = "iMontoDanioReparado", precision = 18, scale = 0)
	public Long getImontoDanioReparado() {
		return this.imontoDanioReparado;
	}

	public void setImontoDanioReparado(Long imontoDanioReparado) {
		this.imontoDanioReparado = imontoDanioReparado;
	}

	@Column(name = "iDiasAcreditados", precision = 18, scale = 0)
	public Long getIdiasAcreditados() {
		return this.idiasAcreditados;
	}

	public void setIdiasAcreditados(Long idiasAcreditados) {
		this.idiasAcreditados = idiasAcreditados;
	}

}
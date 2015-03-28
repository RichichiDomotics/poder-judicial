package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EtiquetaJerarquia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EtiquetaJerarquia")
public class EtiquetaJerarquia implements java.io.Serializable {

	// Fields

	private Long catEtiquetaId;
	private CatEtiqueta catEtiqueta;
	private JerarquiaOrganizacional jerarquiaOrganizacional;
	private String valor;

	// Constructors

	/** default constructor */
	public EtiquetaJerarquia() {
	}

	/** minimal constructor */
	public EtiquetaJerarquia(Long catEtiquetaId, CatEtiqueta catEtiqueta,
			JerarquiaOrganizacional jerarquiaOrganizacional) {
		this.catEtiquetaId = catEtiquetaId;
		this.catEtiqueta = catEtiqueta;
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	/** full constructor */
	public EtiquetaJerarquia(Long catEtiquetaId, CatEtiqueta catEtiqueta,
			JerarquiaOrganizacional jerarquiaOrganizacional, String cvalor) {
		this.catEtiquetaId = catEtiquetaId;
		this.catEtiqueta = catEtiqueta;
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
		this.valor = cvalor;
	}

	// Property accessors
	@Id
	@Column(name = "CatEtiqueta_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatEtiquetaId() {
		return this.catEtiquetaId;
	}

	public void setCatEtiquetaId(Long catEtiquetaId) {
		this.catEtiquetaId = catEtiquetaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatEtiqueta_id", unique = true, nullable = false, insertable = false, updatable = false)
	public CatEtiqueta getCatEtiqueta() {
		return this.catEtiqueta;
	}

	public void setCatEtiqueta(CatEtiqueta catEtiqueta) {
		this.catEtiqueta = catEtiqueta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JerarquiaOrganizacional_id", nullable = false)
	public JerarquiaOrganizacional getJerarquiaOrganizacional() {
		return this.jerarquiaOrganizacional;
	}

	public void setJerarquiaOrganizacional(
			JerarquiaOrganizacional jerarquiaOrganizacional) {
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	@Column(name = "cValor", length = 150)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String cvalor) {
		this.valor = cvalor;
	}

}
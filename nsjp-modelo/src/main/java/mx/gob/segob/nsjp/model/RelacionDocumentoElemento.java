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
 * RelacionDocumentoElemento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RelacionDocumentoElemento")
public class RelacionDocumentoElemento implements java.io.Serializable {

	// Fields

	private Long relacionDocumentoElementoId;
	private Elemento elemento;
	private CatRelacion catRelacion;
	private Documento documento;

	// Constructors

	/** default constructor */
	public RelacionDocumentoElemento() {
	}

	/** full constructor */
	public RelacionDocumentoElemento(Long relacionDocumentoElementoId,
			Elemento elemento, CatRelacion catRelacion, Documento documento) {
		this.relacionDocumentoElementoId = relacionDocumentoElementoId;
		this.elemento = elemento;
		this.catRelacion = catRelacion;
		this.documento = documento;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RelacionDocumentoElemento_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelacionDocumentoElementoId() {
		return this.relacionDocumentoElementoId;
	}

	public void setRelacionDocumentoElementoId(Long relacionDocumentoElementoId) {
		this.relacionDocumentoElementoId = relacionDocumentoElementoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "Elemento_id", nullable = false)
	public Elemento getElemento() {
		return this.elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatRelacion_id", nullable = false)
	public CatRelacion getCatRelacion() {
		return this.catRelacion;
	}

	public void setCatRelacion(CatRelacion catRelacion) {
		this.catRelacion = catRelacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Documento_id", nullable = false)
	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
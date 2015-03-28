package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RelacionDocumento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RelacionDocumento")
public class RelacionDocumento implements java.io.Serializable {

	// Fields

	private Long relacionId;
	private Documento documentoPrincipal;
	private Documento documentoRelacionado;
	private Boolean esActivo;

	// Constructors

	/** default constructor */
	public RelacionDocumento() {
	}

	/** minimal constructor */
	public RelacionDocumento(Long relacionId,
			Documento documentoByDocumentoPrincipalId,
			Documento documentoByDocumentoRelacionadoId) {
		this.relacionId = relacionId;
		this.documentoPrincipal = documentoByDocumentoPrincipalId;
		this.documentoRelacionado = documentoByDocumentoRelacionadoId;
	}

	/** full constructor */
	public RelacionDocumento(Long relacionId,
			Documento documentoByDocumentoPrincipalId,
			Documento documentoByDocumentoRelacionadoId, Boolean besActivo) {
		this.relacionId = relacionId;
		this.documentoPrincipal = documentoByDocumentoPrincipalId;
		this.documentoRelacionado = documentoByDocumentoRelacionadoId;
		this.esActivo = besActivo;
	}

	// Property accessors
	@Id
	@Column(name = "Relacion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelacionId() {
		return this.relacionId;
	}

	public void setRelacionId(Long relacionId) {
		this.relacionId = relacionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DocumentoPrincipal_id", nullable = false)
	public Documento getDocumentoPrincipal() {
		return this.documentoPrincipal;
	}

	public void setDocumentoPrincipal(
			Documento documentoByDocumentoPrincipalId) {
		this.documentoPrincipal = documentoByDocumentoPrincipalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DocumentoRelacionado_id", nullable = false)
	public Documento getDocumentoRelacionado() {
		return this.documentoRelacionado;
	}

	public void setDocumentoRelacionado(
			Documento documentoByDocumentoRelacionadoId) {
		this.documentoRelacionado = documentoByDocumentoRelacionadoId;
	}

	@Column(name = "bEsActivo", precision = 1, scale = 0)
	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean besActivo) {
		this.esActivo = besActivo;
	}

}
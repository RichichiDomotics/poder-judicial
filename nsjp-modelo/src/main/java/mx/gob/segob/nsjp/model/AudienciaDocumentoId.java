package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AudienciaDocumentoId implements java.io.Serializable{
	
	// Fields
	
	private Long audienciaId;
	private Long documentoId;
	
	// Constructors
	
	public AudienciaDocumentoId() {

	}
	
	public AudienciaDocumentoId(Long audienciaId, Long documentoId) {
		this.audienciaId = audienciaId;
		this.documentoId = documentoId;
	}

	@Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)
	public Long getAudienciaId() {
		return audienciaId;
	}

	
	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	@Column(name = "Documento_id", nullable = false, precision = 18, scale = 0)
	public Long getDocumentoId() {
		return documentoId;
	}

	
	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}	
}

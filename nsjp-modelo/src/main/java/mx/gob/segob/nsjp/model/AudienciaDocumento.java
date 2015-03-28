/**
 * 
 */
package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author AlejandroGA
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "AudienciaDocumento")
public class AudienciaDocumento implements java.io.Serializable{
	
	private AudienciaDocumentoId id;
	private Audiencia audiencia;
	private Documento documento;
	
	public AudienciaDocumento() {
		
	}
	
	public AudienciaDocumento(AudienciaDocumentoId id, Audiencia audiencia,
			Documento documento) {
		
		this.id = id;
		this.audiencia = audiencia;
		this.documento = documento;
	}


	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "audienciaId", column = @Column(name = "Audiencia_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "documentoId", column = @Column(name = "Documento_id", nullable = false, precision = 18, scale = 0)) })
	public AudienciaDocumentoId getId() {
		return id;
	}

	public void setId(AudienciaDocumentoId id) {
		this.id = id;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audiencia_id", nullable = false, insertable = false, updatable = false)
	public Audiencia getAudiencia() {
		return audiencia;
	}
	
	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id", nullable = false, insertable = false, updatable = false)
	public Documento getDocumento() {
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}

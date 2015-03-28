/**
 * 
 */
package mx.gob.segob.nsjp.dto.involucrado;

/**
 * @author EduardoAD
 *
 */
public class InvolucradoAudienciaIdDTO {
	private Long audienciaId;
	private Long involucradoId;
	
	
	public InvolucradoAudienciaIdDTO(Long audienciaId, Long involucradoId) {
		super();
		this.audienciaId = audienciaId;
		this.involucradoId = involucradoId;
	}
	
	public Long getAudienciaId() {
		return audienciaId;
	}
	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}
	public Long getInvolucradoId() {
		return involucradoId;
	}
	public void setInvolucradoId(Long involucradoId) {
		this.involucradoId = involucradoId;
	}
}

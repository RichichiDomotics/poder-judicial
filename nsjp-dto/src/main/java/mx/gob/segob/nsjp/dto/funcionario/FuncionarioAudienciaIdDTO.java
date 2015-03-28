/**
 * 
 */
package mx.gob.segob.nsjp.dto.funcionario;

/**
 * @author EduardoAD
 *
 */
public class FuncionarioAudienciaIdDTO {
	private Long audienciaId;
	private Long claveFuncionario;
	
	public FuncionarioAudienciaIdDTO(){
		super();
	}
	
	public FuncionarioAudienciaIdDTO(Long audienciaId, Long claveFuncionario) {
		super();
		this.audienciaId = audienciaId;
		this.claveFuncionario = claveFuncionario;
	}
	
	public Long getAudienciaId() {
		return audienciaId;
	}
	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}
	public Long getClaveFuncionario() {
		return claveFuncionario;
	}
	public void setClaveFuncionario(Long claveFuncionario) {
		this.claveFuncionario = claveFuncionario;
	}
}

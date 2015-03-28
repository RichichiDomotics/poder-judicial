package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class InvolucradoIPHIdDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long informePolicialHomologadoId;
	private Long involucradoId;
			
	public InvolucradoIPHIdDTO() {
		super();
	}
	
	public InvolucradoIPHIdDTO(Long informePolicialHomologadoId, Long involucradoId) {
		super();
		this.informePolicialHomologadoId = informePolicialHomologadoId;
		this.involucradoId = involucradoId;
	}

	public Long getInformePolicialHomologadoId() {
		return informePolicialHomologadoId;
	}
	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}
	public Long getInvolucradoId() {
		return involucradoId;
	}
	public void setInvolucradoId(Long involucradoId) {
		this.involucradoId = involucradoId;
	}
	
	

}

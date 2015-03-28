package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class DelitoIphIdDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long informePolicialHomologadoIdDTO;
	private Long catDelitoIdDTO;
	
	public DelitoIphIdDTO(Long informePolicialHomologadoIdDTO,
			Long catDelitoIdDTO) {
		super();
		this.informePolicialHomologadoIdDTO = informePolicialHomologadoIdDTO;
		this.catDelitoIdDTO = catDelitoIdDTO;
	}
				
	public DelitoIphIdDTO() {
	}

	public Long getInformePolicialHomologadoIdDTO() {
		return informePolicialHomologadoIdDTO;
	}
	public void setInformePolicialHomologadoIdDTO(
			Long informePolicialHomologadoIdDTO) {
		this.informePolicialHomologadoIdDTO = informePolicialHomologadoIdDTO;
	}
	public Long getCatDelitoIdDTO() {
		return catDelitoIdDTO;
	}
	public void setCatDelitoIdDTO(Long catDelitoIdDTO) {
		this.catDelitoIdDTO = catDelitoIdDTO;
	}
	
}

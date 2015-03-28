package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class FaltaAdministrativaIphIdDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long informePolicialHomologadoIdDTO;
	private Long catFaltaAdministrativaIdDTO;
	
	
	public FaltaAdministrativaIphIdDTO(Long informePolicialHomologadoIdDTO,
			Long catFaltaAdministrativaIdDTO) {
		super();
		this.informePolicialHomologadoIdDTO = informePolicialHomologadoIdDTO;
		this.catFaltaAdministrativaIdDTO = catFaltaAdministrativaIdDTO;
	}
	
	public FaltaAdministrativaIphIdDTO() {
		super();
	}
	public Long getInformePolicialHomologadoIdDTO() {
		return informePolicialHomologadoIdDTO;
	}
	public void setInformePolicialHomologadoIdDTO(
			Long informePolicialHomologadoIdDTO) {
		this.informePolicialHomologadoIdDTO = informePolicialHomologadoIdDTO;
	}
	public Long getCatFaltaAdministrativaIdDTO() {
		return catFaltaAdministrativaIdDTO;
	}
	public void setCatFaltaAdministrativaIdDTO(Long catFaltaAdministrativaIdDTO) {
		this.catFaltaAdministrativaIdDTO = catFaltaAdministrativaIdDTO;
	}
	
	

}

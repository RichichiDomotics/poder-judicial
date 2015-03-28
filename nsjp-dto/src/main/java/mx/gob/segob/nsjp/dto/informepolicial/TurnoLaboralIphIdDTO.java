package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class TurnoLaboralIphIdDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long informePolicialHomologadoId;
	private Long turnoLaboralId;
	public Long getInformePolicialHomologadoId() {
		return informePolicialHomologadoId;
	}
	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}
	public Long getTurnoLaboralId() {
		return turnoLaboralId;
	}
	public void setTurnoLaboralId(Long turnoLaboralId) {
		this.turnoLaboralId = turnoLaboralId;
	}
	
	

}

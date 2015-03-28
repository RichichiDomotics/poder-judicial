package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;

public class TurnoLaboralIphDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TurnoLaboralIphIdDTO id;
	private TurnoLaboralDTO turnoLaboral;
	private InformePolicialHomologadoDTO informePolicialHomologado;
	
	public TurnoLaboralIphDTO(TurnoLaboralIphIdDTO id,
			TurnoLaboralDTO turnoLaboral,
			InformePolicialHomologadoDTO informePolicialHomologado) {
		super();
		this.id = id;
		this.turnoLaboral = turnoLaboral;
		this.informePolicialHomologado = informePolicialHomologado;
	}

	public TurnoLaboralIphDTO() {
		super();
	}

	public TurnoLaboralIphIdDTO getId() {
		return id;
	}

	public void setId(TurnoLaboralIphIdDTO id) {
		this.id = id;
	}

	public TurnoLaboralDTO getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(TurnoLaboralDTO turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public InformePolicialHomologadoDTO getInformePolicialHomologado() {
		return informePolicialHomologado;
	}

	public void setInformePolicialHomologado(
			InformePolicialHomologadoDTO informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}
	
	
	
}

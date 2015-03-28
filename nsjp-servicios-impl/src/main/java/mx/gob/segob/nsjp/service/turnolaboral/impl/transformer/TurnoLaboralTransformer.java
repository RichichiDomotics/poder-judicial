package mx.gob.segob.nsjp.service.turnolaboral.impl.transformer;

import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;
import mx.gob.segob.nsjp.model.TurnoLaboral;

public class TurnoLaboralTransformer {

	public static TurnoLaboralDTO transormarTurnoLaboral(TurnoLaboral turnoLaboral)
	{
		TurnoLaboralDTO turnoLaboralDTO = new TurnoLaboralDTO();
		turnoLaboralDTO.setTurnoLaboralId(turnoLaboral.getTurnoLaboralId());
		turnoLaboralDTO.setNombreTurno(turnoLaboral.getNombreTurno());
		turnoLaboralDTO.setHoraInicioTurno(turnoLaboral.getHoraInicioTurno());
		turnoLaboralDTO.setHoraFinTurno(turnoLaboral.getHoraFinTurno());
		
		return turnoLaboralDTO;
		
	}

}

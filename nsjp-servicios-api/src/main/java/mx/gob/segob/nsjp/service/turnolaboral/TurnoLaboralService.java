package mx.gob.segob.nsjp.service.turnolaboral;

import java.util.List;

import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;

/**
 * Contrato del servicio para realizar las consultas a TurnoLaboral
 * @version 1.0
 * @author mgallardo
 *
 */
public interface TurnoLaboralService {
	List<TurnoLaboralDTO> ConsultarCatalogoTurnoLaboral();
}

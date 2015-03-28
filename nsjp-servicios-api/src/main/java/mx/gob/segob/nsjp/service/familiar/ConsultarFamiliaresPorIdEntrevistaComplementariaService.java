package mx.gob.segob.nsjp.service.familiar;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarFamiliaresPorIdEntrevistaComplementariaService {
	
	/**
	 * Consulta todos los familiares asociados a una Entrevista complementaria
	 * @param aoEntrevistaComplementariaDTO
	 * 	 Obligatorio <b>entrevistaComplementaria.sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    List<FamiliarDTO> consultarFamiliaresPorIdEntrevistaComplementaria(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
            throws NSJPNegocioException;

}

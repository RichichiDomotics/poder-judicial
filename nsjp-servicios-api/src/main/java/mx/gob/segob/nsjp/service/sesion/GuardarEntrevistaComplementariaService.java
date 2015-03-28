package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;

/**
 * @author rgama
 *
 */
public interface GuardarEntrevistaComplementariaService {
	
	/**
	 * Permite guardar/actualizar la informacion de una EntrevistaComplementaria
	 * @param aoEntrevistaComplementariaDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return EntrevistaComplementariaDTO
	 * @throws NSJPNegocioException
	 */
	EntrevistaComplementariaDTO guardarEntrevistaComplementaria(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
		throws NSJPNegocioException;

}

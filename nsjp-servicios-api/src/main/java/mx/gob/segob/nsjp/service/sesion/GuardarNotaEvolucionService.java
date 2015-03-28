package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;

/**
 * @author rgama
 *
 */
public interface GuardarNotaEvolucionService {
	
	/**
	 * Permite guardar/actualizar la informacion de una NotaEvolucion
	 * @param aoNotaEvolucionDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return NotaEvolucionDTO
	 * @throws NSJPNegocioException
	 */
	NotaEvolucionDTO guardarNotaEvolucion(NotaEvolucionDTO aoNotaEvolucionDTO)
		throws NSJPNegocioException;

}

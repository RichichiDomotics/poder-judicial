package mx.gob.segob.nsjp.service.sesion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;

/**
 * @author rgama
 *
 */
public interface GuardarEntrevistaInicialService {
	
	/**
	 * Permite guardar/actualizar la informacion de una EntrevistaInicial
	 * @param aoEntrevistaInicialDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return EntrevistaInicialDTO
	 * @throws NSJPNegocioException
	 */
	EntrevistaInicialDTO guardarEntrevistaInicial(EntrevistaInicialDTO aoEntrevistaInicialDTO)
		throws NSJPNegocioException;

}

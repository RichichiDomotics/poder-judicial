package mx.gob.segob.nsjp.service.convenio;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;

/**
 * @author rgama
 *
 */
public interface ConsultarPagoService {

	/**
	 * Operación que permite consultar un pago de convenio por id
	 * @param fechaCompromisoId (Obligatorio)
	 * @return FechaCompromisoDTO
	 * @throws NSJPNegocioException
	 */
	public FechaCompromisoDTO consultarPagoXId(FechaCompromisoDTO aoFechaCompromiso)throws NSJPNegocioException;

}

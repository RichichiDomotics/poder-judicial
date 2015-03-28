/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;

/**
 * @author adrian
 * 
 */
public interface ConsultarControversiasResueltasService {

	/**
	 * Operación que realiza la funcionalidad de consultar las controversias
	 * resueltas en justicia alternativa.
	 * 
	 * @param idTipoDocumento
	 *            : Controversia es en Tipos "Carta de cumplimiento de acuerdo"
	 * @return Los datos que se obtienen son: - Número de caso - Identificador
	 *         de la controversia resuelta - Nombre completo (nombre, apellido
	 *         paterno, apellido materno) del fiscal que llevó a cabo la
	 *         controversia - Nombre del documento - Bandera si ya ha sido leída
	 *         la controversia - Fecha de envío de la misma.
	 * @throws NSJPNegocioException
	 */
	List<CartaCumplimientoDTO> consultarControversiasResueltas(
			Long idTipoDocumento) throws NSJPNegocioException;

}

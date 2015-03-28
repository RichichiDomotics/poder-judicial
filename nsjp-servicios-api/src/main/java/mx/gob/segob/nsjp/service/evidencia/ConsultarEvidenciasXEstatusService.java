/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * @author adrian
 *
 */
public interface ConsultarEvidenciasXEstatusService {

	/**
	 * Operación que realiza la funcionalidad de consultar las evidencias asociadas al folio de una cadena de custodia en función de la disponibilidad solicitada(estatus).
	 * @param custodiaDTO: folioCadena
	 * @param estatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasXCadenaCustodiaYEstatus(
			CadenaDeCustodiaDTO custodiaDTO, Long estatus)throws NSJPNegocioException;

	/**
	 * Operación que permite consultar el detalle de una evidencia
	 * @param idEvidencia
	 * @return
	 * @throws NSJPNegocioException
	 */
    EvidenciaDTO consultaEvidencia(Long idEvidencia)throws NSJPNegocioException;
    
	/**
	 * Operación que realiza la funcionalidad de consultar evidencia por el folio de la solicitud
	 * @author Marco Gallardo
	 * @param folioSolicitud: Folio de la solicitud
	 */
    List<EvidenciaDTO> consultarEvidenciasXIdSolicitud(Long idSolicitud);
}

/**
* Nombre del Programa : ConsultarSolicitudDiligenciaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servici para consultar las solicitudes diligencia
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;

/**
 * Contrato del servici para consultar las solicitudes diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarSolicitudDiligenciaService {

	/**
	 * Consulta las solicitudes de diligencia relacioadas al expediente enviado como parametro
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDiligenciaDTO> consultarDiligenciasDelExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	/**
	 * Obtiene el detalle de la diligencia requerida
	 * @author cesarAgustin
	 * @param solicitudDiligenciaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudDiligenciaDTO obtenerDiligenciaById(
			SolicitudDiligenciaDTO solicitudDiligenciaDTO) throws NSJPNegocioException;

}

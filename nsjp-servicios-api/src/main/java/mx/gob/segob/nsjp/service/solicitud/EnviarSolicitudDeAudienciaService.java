/**
 * Nombre del Programa : EnviarSolicitudDeAudienciaService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 14-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface EnviarSolicitudDeAudienciaService {

	/**
     * Servicio para enviar la solicitud de la audiencia hacia PJ. 
     * 
	 * @param solicitudAudienciaDto
	 * @param expedienteDTO
	 * @param idDistrito
	 * @param idTribunal
	 * @param idClaveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
    SolicitudAudienciaDTO enviarSolicitudDeAudiencia(
            SolicitudAudienciaDTO solicitudAudienciaDto, ExpedienteDTO expedienteDTO,
            Long idDistrito, Long idTribunal, Long idClaveFuncionario)
            throws NSJPNegocioException;

}

/**
 * Nombre del Programa : ActuacionesDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 08-ago-2011
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
package mx.gob.segob.nsjp.delegate.actuaciones;


import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Actuaciones
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ActuacionesDelegate {
    /**
     * 
     * @param solicitudAudienciaDto
     * @param expedienteDTO
     * @param idDistrito
     * @param idTribunal
     * @param idClaveFuncionario
     * @return
     * @throws NSJPNegocioException
     */
    SolicitudAudienciaDTO enviarSolicitudDeAudiencia(SolicitudAudienciaDTO
            solicitudAudienciaDto, ExpedienteDTO expedienteDTO, Long idDistrito, Long idTribunal, Long idClaveFuncionario) throws NSJPNegocioException;
}

/**
 * Nombre del Programa : ActuacionesDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.actuaciones.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.actuaciones.ActuacionesDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudDeAudienciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("actuacionesDelegate")
public class ActuacionesDelegateImpl implements ActuacionesDelegate {

    @Autowired
    private EnviarSolicitudDeAudienciaService enviarSolicitudDeAudienciaService;

    @Override
    public SolicitudAudienciaDTO enviarSolicitudDeAudiencia(SolicitudAudienciaDTO
            solicitudAudienciaDto, ExpedienteDTO expedienteDTO, Long idDistrito, Long idTribunal, Long idClaveFuncionario) throws NSJPNegocioException {
        return enviarSolicitudDeAudienciaService.enviarSolicitudDeAudiencia(solicitudAudienciaDto, expedienteDTO, idDistrito, idTribunal, idClaveFuncionario);
    }


}

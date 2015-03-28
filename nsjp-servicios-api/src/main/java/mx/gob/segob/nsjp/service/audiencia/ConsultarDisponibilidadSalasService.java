/**
 * Nombre del Programa : ConsultarDisponibilidadSalasService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.DiaDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.MesDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ConsultarDisponibilidadSalasService {
    /**
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    MesDisponibilidadDTO consultarDisponibilidadSalas(
            MesDisponibilidadDTO filtro) throws NSJPNegocioException;
    /**
     * COnsulta la disponibilida de un día.
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    DiaDisponibilidadDTO consultarDisponibilidadDiaSalas(
            DiaDisponibilidadDTO filtro) throws NSJPNegocioException;
    
    /**
     * Obtiene le número de salas.
     * @param usr
     * @return
     * @throws NSJPNegocioException
     */
    List<SalaAudienciaDTO> obtenerNombresSalas(UsuarioDTO usr) throws NSJPNegocioException;
}

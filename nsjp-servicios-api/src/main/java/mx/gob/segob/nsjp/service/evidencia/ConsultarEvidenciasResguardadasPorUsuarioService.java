/**
 * Nombre del Programa : ConsultarEvidenciasResguardadasPorUsarioService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
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
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ConsultarEvidenciasResguardadasPorUsuarioService {
    /**
     * Consulta las evidencias resguardadas por un perito, es decir, donde el
     * último eslabón es el usuario (firmado) quien recibe.
     * 
     * @param usuario
     *            Requerido <b>idUsuario</b>
     * @return Lista de evidencias
     */
    List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsuario(
            UsuarioDTO usuario) throws NSJPNegocioException;

    /**
     * 
     * @param usuario
     *            Requerido <b>idUsuario</b>
     * @param cadena
     *            Requerido <b>cadenaDeCustodiaId</b>
     * @return
     * @throws NSJPNegocioException
     */
    List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsuario(
            UsuarioDTO usuario, CadenaDeCustodiaDTO cadena)
            throws NSJPNegocioException;
}

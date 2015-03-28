/**
 * Nombre del Programa : ObtenerObjetoService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface de servicio que obtiene un objeto
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
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * Interface de servicio que obtiene un objeto.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ObtenerObjetoService {
    /**
     * Recupera un objeto de la BD.
     * 
     * @param input
     *            requerido <b>elementoId</b>.
     * @return
     * @throws NSJPNegocioException
     */
    ObjetoDTO obtenerObjeto(ObjetoDTO input) throws NSJPNegocioException;
}

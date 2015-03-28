/**
 * Nombre del Programa : ValidarExisteDelitoGraveService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 04-jul-2011
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
package mx.gob.segob.nsjp.service.delito;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ValidarExisteDelitoGraveService {

    /**
     * Valida si existe algún delito grave dentro de un listado de delitos.
     * @param delitos Delitos a verificar.
     * @return {@code true} en caso que alguno de los delitos sea grave,
     * {@code false} en caso contrario.
     * @throws NSJPNegocioException En caso que la lista de delitos sea nula.
     */
    boolean validarExisteDelitoGrave(List<DelitoDTO> delitos)
            throws NSJPNegocioException;

}

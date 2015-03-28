/**
 * Nombre del Programa : ValidarCadenaCustodiaEnAlmacenService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 03-ago-2011
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
package mx.gob.segob.nsjp.service.almacen;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ValidarCadenaCustodiaEnAlmacenService {

    /**
     * 
     * @param cadenaDeCustodiaDto
     * @param almacenDto
     * @return
     * @throws NSJPNegocioException
     */
    boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodiaDTO cadenaDeCustodiaDto,
            AlmacenDTO almacenDto) throws NSJPNegocioException;
}

/**
 * Nombre del Programa : ActualizarEtapaCasoService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 02-ago-2011
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
package mx.gob.segob.nsjp.service.caso;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ActualizarEtapaCasoService {

    /**
     * 
     * @param casoDto
     * @param nuevaEtapaDto
     * @throws NSJPNegocioException
     */
    void actualizarEtapaCaso(CasoDTO casoDto, ValorDTO nuevaEtapaDto)
            throws NSJPNegocioException;
}

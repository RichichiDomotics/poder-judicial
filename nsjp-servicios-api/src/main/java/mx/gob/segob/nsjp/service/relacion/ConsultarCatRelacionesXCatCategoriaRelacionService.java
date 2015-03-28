/**
 * Nombre del Programa : ConsultarRelacionesXCategoriaService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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
package mx.gob.segob.nsjp.service.relacion;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarCatRelacionesXCatCategoriaRelacionService {

    /**
     * Consulta los CatRelacion de una CatCategoriaRelacionDTO.
     * @throws NSJPNegocioException En caso que se cumpla alguno de:
     * <ol>
     * <li> {@code catCategoriaRelacionDTO == null}
     * <li> {@code catCategoriaRelacionDTO.getCatCategoriaRelacionId() == null}
     * </ol>
     */
    List<CatRelacionDTO> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacionDTO catCategoriaRelacionDTO)
            throws NSJPNegocioException;

}

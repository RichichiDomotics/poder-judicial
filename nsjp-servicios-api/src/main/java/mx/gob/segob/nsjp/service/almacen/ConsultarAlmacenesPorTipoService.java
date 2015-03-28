/**
 * Nombre del Programa : ConsultarAlmacenesPorTipoService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarAlmacenesPorTipoService {

    /**
     *
     * @param idTipoAlmacen
     * @param estatus
     * @param casoDto
     * @return
     * @throws NSJPNegocioException
     */
    public List<AlmacenDTO> consultarAlmacenesPorTipo(Long idTipoAlmacen,
            Boolean estatus, CasoDTO casoDto) throws NSJPNegocioException;

}

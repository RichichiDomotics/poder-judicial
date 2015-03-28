/**
 * Nombre del Programa : ConsultarElementosXActividadService.java
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
package mx.gob.segob.nsjp.service.elemento;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarElementosXActividadService {

    /**
     * Consulta los Elementos asociados al expediente de la actividad con id
     * {@code idActividad}
     * @param idActividad El id de la activad de la que se obtendra su
     * expediente y a su vez de este se obtendran los elementos asociados.
     * @return Una lista que contiene los elementos del expediente asociado
     * a la actividad. Regresa la lista vacia en caso que no existan elementos
     * asociados al expediente.
     * @throws NSJPNegocioException En caso que {@code idActividad} sea nulo.
     */
    List<ElementoDTO> consultarElementosXActividad(Long idActividad)
            throws NSJPNegocioException;

}

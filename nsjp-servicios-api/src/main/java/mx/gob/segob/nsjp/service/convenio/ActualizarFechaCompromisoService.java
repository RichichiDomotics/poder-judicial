/**
 * Nombre del Programa : ActualizarFechaCompromisoService.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 09-09-2011
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
package mx.gob.segob.nsjp.service.convenio;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;

/**
 * 
 * @version 1.0
 * @author rgama
 */
public interface ActualizarFechaCompromisoService {

    /**
     * Actualiza los datos de una Fecha Compromiso. Actualiza la fecha compromiso con los parametros
     * distintos de {@code null} que vengan dentro de la fecha compromiso DTO
     * @throws NSJPNegocioException En caso que "{@code fechaCompromisoDTO}" o
     * "{@code loFechaCompromisoDTO.fechaCompromisoId}" sean {@code null}.|
     */
    public void actualizarFechaCompromido(FechaCompromisoDTO loFechaCompromisoDTO) throws NSJPNegocioException;

}

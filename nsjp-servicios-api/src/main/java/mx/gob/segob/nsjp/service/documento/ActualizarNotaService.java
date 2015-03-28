/**
 * Nombre del Programa : ActualizarNotaService.java
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ActualizarNotaService {

    /**
     * Actualiza los datos de una nota. Actualiza la nota con los parametros
     * distintos de {@code null} que vengan dentro de la nota y asigna actualiza
     * automaticamente la nota con la fecha actual del sistema.
     * @throws NSJPNegocioException En caso que "{@code notaDto}" o
     * "{@code notaDto.idNota}" sean {@code null}.
     */
    void actualizarNota(NotaDTO notaDto) throws NSJPNegocioException;

}

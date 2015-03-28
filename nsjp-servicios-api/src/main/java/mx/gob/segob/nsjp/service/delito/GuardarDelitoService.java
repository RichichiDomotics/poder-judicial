/**
 * Nombre del Programa : GuardarDelitoService.java
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
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface GuardarDelitoService {

    /**
     * Guarda el(los) delitos en el expediente.
     * Hace una asociacion en la base de datos de cada delito con el expediente.
     * @throws NSJPNegocioException En caso que alguno se cumpla alguna de las
     * siguiente condiciones:
     * <ol>
     * <li> {@code delitosDto == null}
     * <li> {@code expedienteDto == null}
     * </ol>
     * Tambien se lanza la esta excepcion si alguno de los elementos de los
     * delitosDto cumple alguna de las siguientes condiciones:
     * <ol>
     * <li> {@code delitoDto.esProbable == null}
     * <li> {@code delitoDto.esPrincipal == null}
     * </ol>
     */
    void guardarDelito(List<DelitoDTO> delitosDto, ExpedienteDTO expedienteDto)
            throws NSJPNegocioException;

}

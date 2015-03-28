/**
 * Nombre del Programa : IngresarJoyaService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para ingresar Joya.
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
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;

/**
 * Contrato del servicio para ingresar Joya.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarJoyaService {

    /**
     * Guarda o actualiza una joya.
     * Para guardar una nueva joya se debe indicar
     * {@code joyaDto.elementoId == null} o
     * {@code joyaDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar una nueva joya son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un joya se debe indicar
     * {@code joyaDto.elementoId != null} y
     * {@code joyaDto.elementoId > 0}.
     * @param joyaDto La joya a guardar o actualizar.
     * @return El id de la joya guardada o actualizada.
     * @throws NSJPNegocioException Siempre que joyaDto == null.<br/>
     * En caso de guardar una nueva joya se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code joyaDto.getExpedienteDTO() == null}
     * <li> {@code joyaDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code joyaDto.elementoId < 0}.
     */
    Long ingresarJoya(JoyaDTO joyaDto) throws NSJPNegocioException;
}

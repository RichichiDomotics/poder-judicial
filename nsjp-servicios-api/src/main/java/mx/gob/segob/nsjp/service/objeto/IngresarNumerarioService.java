/**
 * Nombre del Programa : IngresarNumerarioService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar un numerario de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;

/**
 * Contrato del servicio para consultar un numerario de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarNumerarioService {

    /**
     * Guarda o actualiza un numerario.
     * Para guardar un nuevo numerario se debe indicar
     * {@code numerarioDto.elementoId == null} o
     * {@code numerarioDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar un nuevo numerario son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un numerario se debe indicar
     * {@code numerarioDto.elementoId != null} y
     * {@code numerarioDto.elementoId > 0}.
     * Si el elementoId no existe en base de datos no se ejecuta actualizacion
     * ni registro alguno.
     * @param numerarioDto El numerario a guardar o actualizar.
     * @return El id del numerario guardado o actualizado.
     * @throws NSJPNegocioException Siempre que numerarioDto == null.<br/>
     * En caso de guardar un nuevo numerario se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code numerarioDto.getExpedienteDTO() == null}
     * <li> {@code numerarioDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code numerarioDto.elementoId < 0}.
     */
    Long ingresarNumerario(NumerarioDTO numerarioDTO) throws NSJPNegocioException;
}

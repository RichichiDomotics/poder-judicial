/**
 * Nombre del Programa : IngresarExplosivoService.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar un explosivo de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;

/**
 * Contrato del servicio para consultar un explosivo de acuerdo a ciertos
 * parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarExplosivoService {

    /**
     * Guarda o actualiza un explosivo.
     * Para guardar un nuevo explosivo se debe indicar
     * {@code explosivoDto.elementoId == null} o
     * {@code explosivoDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar un nuevo explosivo son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un explosivo se debe indicar
     * {@code explosivoDto.elementoId != null} y
     * {@code explosivoDto.elementoId > 0}.
     * @param explosivoDto El explosivo a guardar o actualizar.
     * @return El id del explosivo guardado o actualizado.
     * @throws NSJPNegocioException Siempre que explosivoDto == null.<br/>
     * En caso de guardar un nuevo explosivo se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code explosivoDto.getExpedienteDTO() == null}
     * <li> {@code explosivoDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code explosivoDto.elementoId < 0}.
     */
    Long ingresarExplosivo(ExplosivoDTO explosivoDto) throws NSJPNegocioException;
}

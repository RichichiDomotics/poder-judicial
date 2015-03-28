/**
 * Nombre del Programa : IngresarSustanciaService.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 10 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar una sustancia de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;

/**
 * Contrato del servicio para consultar una sustancia de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarSustanciaService {

    /**
     * Guarda o actualiza una sustancia.
     * Para guardar una nueva sustancia se debe indicar
     * {@code sustanciaDto.elementoId == null} o
     * {@code sustanciaDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar una nueva sustancia son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un sustancia se debe indicar
     * {@code sustanciaDto.elementoId != null} y
     * {@code sustanciaDto.elementoId > 0}.
     * @param sustanciaDto La sustancia a guardar o actualizar.
     * @return El id de la sustancia guardada o actualizada.
     * @throws NSJPNegocioException Siempre que sustanciaDto == null.<br/>
     * En caso de guardar una nueva sustancia se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code sustanciaDto.getExpedienteDTO() == null}
     * <li> {@code sustanciaDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code sustanciaDto.elementoId < 0}.
     */
    Long ingresarSustancia(SustanciaDTO sustanciaDto) throws NSJPNegocioException;
}

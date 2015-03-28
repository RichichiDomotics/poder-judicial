/**
 * Nombre del Programa : IngresarEmbarcacionService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar una embarcacion de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;

/**
 * Contrato del servicio para consultar una embarcacion de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarEmbarcacionService {

    /**
     * Guarda o actualiza una embarcacion.
     * Para guardar una nueva embarcacion se debe indicar
     * {@code embarcacionDto.elementoId == null} o
     * {@code embarcacionDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar una nueva embarcacion son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un embarcacion se debe indicar
     * {@code embarcacionDto.elementoId != null} y
     * {@code embarcacionDto.elementoId > 0}.
     * @param embarcacionDto La embarcacion a guardar o actualizar.
     * @return El id de la embarcacion guardada o actualizada.
     * @throws NSJPNegocioException Siempre que embarcacionDto == null.<br/>
     * En caso de guardar una nueva embarcacion se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code embarcacionDto.getExpedienteDTO() == null}
     * <li> {@code embarcacionDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code embarcacionDto.elementoId < 0}.
     */
    Long ingresarEmbarcacion(EmbarcacionDTO embarcacionDto) throws NSJPNegocioException;
}

/**
 * Nombre del Programa : IngresarAeronaveService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar una aeronave de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;

/**
 * Contrato del servicio para consultar una aeronave de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarAeronaveService {

    /**
     * Guarda o actualiza una aeronave.
     * Para guardar una nueva aeronave se debe indicar
     * {@code aeronaveDto.elementoId == null} o
     * {@code aeronaveDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar una nueva aeronave son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un aeronave se debe indicar
     * {@code aeronaveDto.elementoId != null} y
     * {@code aeronaveDto.elementoId > 0}.
     * @param aeronaveDto La aeronave a guardar o actualizar.
     * @return El id de la aeronave guardada o actualizada.
     * @throws NSJPNegocioException Siempre que aeronaveDto == null.<br/>
     * En caso de guardar una nueva aeronave se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code aeronaveDto.getExpedienteDTO() == null}
     * <li> {@code aeronaveDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code aeronaveDto.elementoId < 0}.
     */
    Long ingresarAeronave(AeronaveDTO aeronaveDto) throws NSJPNegocioException;
}

/**
 * Nombre del Programa : IngresarVegetalService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar un vegetalde acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;

/**
 * Contrato del servicio para consultar un vegetal de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarVegetalService {

    /**
     * Guarda o actualiza un vegetal.
     * Para guardar un nuevo vegetal se debe indicar
     * {@code vegetalDto.elementoId == null} o
     * {@code vegetalDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar un nuevo vegetal son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un vegetal se debe indicar
     * {@code vegetalDto.elementoId != null} y
     * {@code vegetalDto.elementoId > 0}.
     * Si el elementoId no existe en base de datos no se ejecuta actualizacion
     * ni registro alguno.
     * @param vegetalDto El vegetal a guardar o actualizar.
     * @return El id del vegetal guardado o actualizado.
     * @throws NSJPNegocioException Siempre que vegetalDto == null.<br/>
     * En caso de guardar un nuevo vegetal se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code vegetalDto.getExpedienteDTO() == null}
     * <li> {@code vegetalDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code vegetalDto.elementoId < 0}.
     */
    Long ingresarVegetalService(VegetalDTO vegetalDto) throws NSJPNegocioException;
}

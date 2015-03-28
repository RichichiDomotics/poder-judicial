/**
 * Nombre del Programa : IngresarAnimalService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar un animal de acuerdo a ciertos parametros de busqueda.
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
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;

/**
 * Contrato del servicio para consultar un animal de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarAnimalService {

    /**
     * Guarda o actualiza un animal.
     * Para guardar un nuevo animal se debe indicar
     * {@code animalDto.elementoId == null} o
     * {@code animalDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar un nuevo animal son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un animal se debe indicar
     * {@code animalDto.elementoId != null} y
     * {@code animalDto.elementoId > 0}.
     * @param animalDto El animal a guardar o actualizar.
     * @return El id del animal guardado o actualizado.
     * @throws NSJPNegocioException Siempre que animalDto == null.<br/>
     * En caso de guardar un nuevo animal se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code animalDto.getExpedienteDTO() == null}
     * <li> {@code animalDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code animalDto.elementoId < 0}.
     */
    Long ingresarAnimal(AnimalDTO animalDto) throws NSJPNegocioException;
}

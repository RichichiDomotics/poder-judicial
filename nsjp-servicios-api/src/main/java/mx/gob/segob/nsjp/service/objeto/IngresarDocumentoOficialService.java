/**
 * Nombre del Programa : IngresarDocumentoOficial.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para ingresar Documento Oficial.
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
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;

/**
 * Contrato del servicio para ingresar DocumentoOficial.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarDocumentoOficialService {

    /**
     * Guarda o actualiza un documentoOficial.
     * Para guardar un nuevo documentoOficial se debe indicar
     * {@code documentoOficialDto.elementoId == null} o
     * {@code documentoOficialDto.elementoId == 0}.<br/>
     * Los parametros requeridos para guardar un nuevo documentoOficial son:
     * <ol>
     * <li>
     * </ol>
     * Para actualizar un documentoOficial se debe indicar
     * {@code documentoOficialDto.elementoId != null} y
     * {@code documentoOficialDto.elementoId > 0}.
     * Si el elementoId no existe en base de datos no se ejecuta actualizacion
     * ni registro alguno.
     * @param documentoOficialDto El documentoOficial a guardar o actualizar.
     * @return El id del documentoOficial guardado o actualizado.
     * @throws NSJPNegocioException Siempre que documentoOficialDto == null.<br/>
     * En caso de guardar un nuevo documentoOficial se lanza cuando se cumple alguna de
     * las siguientes condiciones:
     * <ol>
     * <li> {@code documentoOficialDto.getExpedienteDTO() == null}
     * <li> {@code documentoOficialDto.getExpedienteDTO().getExpedienteId() == null}
     * </ol>
     * En el caso de actualizar se lanza cuando
     * {@code documentoOficialDto.elementoId < 0}.
     */
    Long ingresarDocumentoOficial(DocumentoOficialDTO documentoOficialDto) throws NSJPNegocioException;
}

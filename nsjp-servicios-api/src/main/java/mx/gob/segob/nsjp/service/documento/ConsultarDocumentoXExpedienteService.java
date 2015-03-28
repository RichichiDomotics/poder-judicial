/**
 * Nombre del Programa : ConsultarDocumentoXExpedienteService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarDocumentoXExpedienteService {

    /**
     * Consulta un documento del tipo {@code tipoDocumento} asociado al numero
     * de expediente indicado por {@code expedienteDto.numeroExpediente}.
     * @param expedienteDto Indica el numero de expediente del que buscamos
     * su documento asociado.
     * @param tipoDocumento El tipo del documento que estamos buscando.
     * @return El documento asociado a los parametros de busqueda o {@code null}
     * en caso de no existir un documento asociado del tipo indicado.
     * @throws NSJPNegocioException En caso que alguno de los siguientes sea
     * nulo:
     * <ol>
     * <li> {@code expedienteDto}
     * <li> {@code expedienteDto.numeroExpediente}
     * <li> {@code tipoDocumento}
     * </ol>
     */
    DocumentoDTO consultarDocumentoXExpediente(ExpedienteDTO expedienteDto,
            Long tipoDocumento) throws NSJPNegocioException;
    /**
     * Consulta los documentos asociados el número de expediente que tengan asociado un archivo digital
     * sin importar el tipo de forma o tipo de documento
     * @param expediente Contiene el número de expediente a buscar
     * @return Lista de documentos encontrados
     */
    List<DocumentoDTO> consultarDocumentosPorNumeroExpediente(ExpedienteDTO expediente);
}

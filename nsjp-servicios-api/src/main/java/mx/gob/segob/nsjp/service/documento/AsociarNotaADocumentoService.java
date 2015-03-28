/**
 * Nombre del Programa : AsociarNotaADocumentoService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 04-jul-2011
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface AsociarNotaADocumentoService {

    /**
     * Asocia una nueva nota a un documento. La nota NO DEBE TENER un id
     * asignado y el documento debe existir en el sistema.<p/>
     * El sistema asigna automaticamente una fecha de creacion y una fecha de
     * actualizacion con la fecha del sistema.
     * @param notaDto Una nueva nota que sera asociada a un documento ya
     * existente.
     * @param documentoDto Un documento ya existente en el sistema al cual se
     * le asociara una nueva nota.<p/>
     * @throws NSJPNegocioException Si se cumple cualquiera de los siguientes
     * casos:<br/>
     * "{@code notaDto == null}", "{@code notaDto.idNota != null}",
     * "{@code documentoDto == null}" o "{@code documentoDto.documentoId == null}"
     */
    void asociarNotaADocumento(NotaDTO notaDto, DocumentoDTO documentoDto)
            throws NSJPNegocioException;

}

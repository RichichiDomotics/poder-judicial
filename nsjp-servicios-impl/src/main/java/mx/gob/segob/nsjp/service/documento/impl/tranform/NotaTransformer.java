/**
 * Nombre del Programa : NotaTransformer.java
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
package mx.gob.segob.nsjp.service.documento.impl.tranform;

import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Nota;

/**
 * Realiza las funciones de conversion entre Nota y NotaDTO.
 * @version 1.0
 * @author Jacob Lobaco
 */
public class NotaTransformer {

    /**
     * Transforma un Nota en un NotaDTO.
     * @param nota Un Nota basico a tranformar.
     * @return Un NotaDTO.
     */
    public static NotaDTO transformarNota(Nota nota){
        NotaDTO notaDTO = new NotaDTO();
        
        notaDTO.setIdNota(nota.getIdNota());
        notaDTO.setNombreNota(nota.getNombreNota());
        notaDTO.setDescripcion(nota.getDescripcion());
        notaDTO.setFechaCreacion(nota.getFechaCreacion());
        notaDTO.setFechaActualizacion(nota.getFechaActualizacion());
        if ( nota.getDocumento()!=null){
        	notaDTO.setDocumento( DocumentoTransformer.transformarDocumento(nota.getDocumento()));
        }
        return notaDTO;
    }

    /**
     * Transforma un NotaDTO en un Nota basico.
     * @param notaDTO El DTO a transformar.
     * @return Un objeto de tipo Nota
     */
    public static Nota transformarNota(NotaDTO notaDTO){
        Nota nota = new Nota();
        nota.setDescripcion(notaDTO.getDescripcion());
        DocumentoDTO documentoDto = notaDTO.getDocumento();
        if(documentoDto != null){
            Documento documento =
                    DocumentoTransformer.transformarDocumentoDTO(documentoDto);
            nota.setDocumento(documento);
        }
        nota.setFechaActualizacion(notaDTO.getFechaActualizacion());
        nota.setFechaCreacion(notaDTO.getFechaCreacion());
        nota.setIdNota(notaDTO.getIdNota());
        nota.setNombreNota(notaDTO.getNombreNota());
        return nota;
    }
}

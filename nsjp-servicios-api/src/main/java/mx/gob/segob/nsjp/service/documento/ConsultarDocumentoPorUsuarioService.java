/**
 * Nombre del Programa : ConsultarDocumentoPorUsuarioService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarDocumentoPorUsuarioService {

    /**
     * Consulta los Documentos que estén asociados a un Usuario
     * @param usuarioDto El usuario del que se consultan sus documentos
     * @param tipoDocumento El tipo de documento por el que se filtraran los
     * documentos para la consultar. (Dictamen / Informe)
     * asociados.
     * @return El listado de documentos asociados al Usuario. Si el usuarioDto
     * no existe en la base de datos o si no tiene documentos asociados, se
     * regresa la lista vacia.
     * @throws NSJPNegocioException En caso que alguno de: "{@code usuarioDto}",
     * "{@code tipoDocumento}", "{@code usuarioDto.funcionario}" o
     * "{@code usuarioDto.funcionario.claveFuncionario}" sean {@code null}.
     */
    List<DocumentoDTO> consultarDocumentoPorUsuario(
            UsuarioDTO usuarioDto,Long tipoDocumento)
            throws NSJPNegocioException;
}

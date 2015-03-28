/**
 * Nombre del Programa : ConsultarDocumentoPorUsuarioServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoPorUsuarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarDocumentoPorUsuarioServiceImplTest
    extends BaseTestServicios<ConsultarDocumentoPorUsuarioService> {

    public void testConsultarDocumentoPorUsuarioService(){
        try {
            logger.info("Probando el servicio de: ConsultarDocumentoPorUsuarioService");
            assert service != null;
            UsuarioDTO usuarioDto = new UsuarioDTO();
            FuncionarioDTO funcionarioDto = new FuncionarioDTO(1L);
            usuarioDto.setFuncionario(funcionarioDto);
            List<DocumentoDTO> documentosPorUsuario =
                    service.consultarDocumentoPorUsuario(usuarioDto, 1780L);
            assertNotNull("documentosPorUsuario", documentosPorUsuario);
            assertTrue(documentosPorUsuario.size() > 0);
            // Consulta sin resultados
            funcionarioDto.setClaveFuncionario(-1L);
            documentosPorUsuario = service.consultarDocumentoPorUsuario(
                    usuarioDto, 1780L);
            assertNotNull("documentosPorUsuario", documentosPorUsuario);
            assertTrue(documentosPorUsuario.isEmpty());
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarDocumentoPorUsuarioService");
        }
    }
   
}

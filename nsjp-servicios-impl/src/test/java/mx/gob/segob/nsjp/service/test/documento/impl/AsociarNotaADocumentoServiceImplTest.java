/**
 * Nombre del Programa : AsociarNotaADocumentoServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.service.documento.AsociarNotaADocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class AsociarNotaADocumentoServiceImplTest
    extends BaseTestServicios<AsociarNotaADocumentoService> {

    public void testAsociarNotaADocumentoService(){
        try {
            logger.info("Probando el servicio de: AsociarNotaADocumentoService");
            assert service != null;
            DocumentoDTO documentoDto = new DocumentoDTO();
            documentoDto.setDocumentoId(8L);
            NotaDTO notaDto = new NotaDTO();
            notaDto.setDescripcion("Descripcion cosme fulanito...");
            notaDto.setNombreNota("Voy para alla....");
            service.asociarNotaADocumento(notaDto, documentoDto);
            notaDto.setIdNota(12L);
            try {
                service.asociarNotaADocumento(notaDto, documentoDto);
            } catch (NSJPNegocioException nSJPNegocioException) {
                if (logger.isDebugEnabled()) {
                    logger.debug("caso de excepcion probado correctamente");
                }
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test AsociarNotaADocumentoService");
        }
    }
   
}

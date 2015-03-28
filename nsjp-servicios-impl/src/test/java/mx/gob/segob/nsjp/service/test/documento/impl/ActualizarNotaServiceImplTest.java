/**
 * Nombre del Programa : ActualizarNotaServiceImplTest.java
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
import mx.gob.segob.nsjp.service.documento.ActualizarNotaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ActualizarNotaServiceImplTest
    extends BaseTestServicios<ActualizarNotaService> {

    public void testActualizarNotaService(){
        try {
            logger.info("Probando el servicio de: ActualizarNotaService");
            assert service != null;
            NotaDTO notaDto = new NotaDTO();
            notaDto.setIdNota(4L);
            DocumentoDTO documentoDto = new DocumentoDTO();
            documentoDto.setDocumentoId(9L);
            notaDto.setDocumento(documentoDto);
            notaDto.setDescripcion("Cambio nueva descripcion cosme fulanito" + Math.random());
            service.actualizarNota(notaDto);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ActualizarNotaService");
        }
    }
   
}

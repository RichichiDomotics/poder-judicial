/**
 * Nombre del Programa : ConsultarDocumentoXExpedienteServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoXExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarDocumentoXExpedienteServiceImplTest
    extends BaseTestServicios<ConsultarDocumentoXExpedienteService> {

    public void testConsultarDocumentoXExpedienteService(){
        try {
            logger.info("Probando el servicio de: ConsultarDocumentoXExpedienteService");
            assert service != null;
            ExpedienteDTO expedienteDto = new ExpedienteDTO();
            expedienteDto.setNumeroExpediente("NSJYUCPROC20113333D");
            DocumentoDTO documento = service.consultarDocumentoXExpediente(expedienteDto, 82L);
            assertNotNull("documento", documento);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarDocumentoXExpedienteService");
        }
    }
    
    public void testConsultaDocumentosPorNumeroExpediente(){
    	try {
            logger.info("Probando el servicio de: ConsultarDocumentoXExpedienteService");
            assert service != null;
            ExpedienteDTO expedienteDto = new ExpedienteDTO();
            expedienteDto.setNumeroExpedienteId(47L);
            List<DocumentoDTO> docs = service.consultarDocumentosPorNumeroExpediente(expedienteDto);
            assertNotNull("documento", docs);
            logger.debug("Documentos:"+ docs.size());
            
        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarDocumentoXExpedienteService");
        }
    }
   
}

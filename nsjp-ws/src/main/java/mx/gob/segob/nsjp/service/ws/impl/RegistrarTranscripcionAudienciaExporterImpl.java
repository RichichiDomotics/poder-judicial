/**
 * Nombre del Programa : RegistrarTranscripcionAudienciaExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.service.documento.RegistrarTranscripcionAudienciaService;
import mx.gob.segob.nsjp.service.ws.RegistrarTranscripcionAudienciaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarTranscripcionAudienciaExporter")
public class RegistrarTranscripcionAudienciaExporterImpl
        implements
            RegistrarTranscripcionAudienciaExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarTranscripcionAudienciaExporterImpl.class);
    RegistrarTranscripcionAudienciaService service = null;
    @Override
    public void registrarTranscripcionAudiencia(String folioSolicitud,
            DocumentoWSDTO transcripcion) throws NSJPNegocioException {
        logger.info("Inicia - registrarTranscripcionAudiencia(...)");
        service = (RegistrarTranscripcionAudienciaService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("registrarTranscripcionAudienciaService");

        service.registrarTranscripcionAudiencia(folioSolicitud, transcripcion);
        logger.info("Fin - registrarTranscripcionAudiencia(...)");
    }

}

/**
 * Nombre del Programa : RecibirNotificacionAudienciaExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Sep 2011
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.service.audiencia.RecibirNotificacionAudienciaService;
import mx.gob.segob.nsjp.service.ws.RecibirNotificacionAudienciaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RecibirNotificacionAudienciaExporter")
public class RecibirNotificacionAudienciaExporterImpl
        implements
            RecibirNotificacionAudienciaExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RecibirNotificacionAudienciaExporterImpl.class);

    private RecibirNotificacionAudienciaService service;
    @Override
    public Boolean recibirNotificacionAudiencia(
            SolicitudAudienciaBasicoWSDTO notificacion)
            throws NSJPNegocioException {
        logger.info("Inicia - recibirNotificacionAudiencia(...)");
        service = (RecibirNotificacionAudienciaService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("recibirNotificacionAudienciaService");
        return service.recibirNotificacionAudiencia(notificacion);

    }

}

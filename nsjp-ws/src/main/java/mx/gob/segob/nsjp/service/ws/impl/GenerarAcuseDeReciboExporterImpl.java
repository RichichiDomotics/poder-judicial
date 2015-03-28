/**
 * Nombre del Programa : GenerarAcuseDeReciboExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Jul 2011
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
import mx.gob.segob.nsjp.service.documento.GenerarAcuseDeReciboService;
import mx.gob.segob.nsjp.service.ws.GenerarAcuseDeReciboExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.GenerarAcuseDeReciboExporter")
public class GenerarAcuseDeReciboExporterImpl
        implements
            GenerarAcuseDeReciboExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(GenerarAcuseDeReciboExporterImpl.class);
    private GenerarAcuseDeReciboService servicio;

    @Override
    public void generarAcuseDeRecibo(String folio) throws NSJPNegocioException {
        logger.info("Inicia - generarAcuseDeRecibo(...)");
        servicio = (GenerarAcuseDeReciboService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("generarAcuseDeReciboService");
        servicio.generarAcuseDeRecibo(folio);
    }

}

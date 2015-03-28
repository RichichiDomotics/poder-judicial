/**
 * Nombre del Programa : RecibirReplicaCasoExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 Aug 2011
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
import mx.gob.segob.nsjp.dto.caso.CasoWSDTO;
import mx.gob.segob.nsjp.service.caso.RegistrarReplicaCasoService;
import mx.gob.segob.nsjp.service.ws.RegistrarReplicaCasoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarReplicaCasoExporter")
public class RegistrarReplicaCasoExporterImpl
        implements
            RegistrarReplicaCasoExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarReplicaCasoExporterImpl.class);
    private RegistrarReplicaCasoService service;

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.ws.RecibirReplicaCasoExporter#recibirReplicaCaso
     * (mx.gob.segob.nsjp.dto.caso.CasoWSDTO)
     */
    @Override
    public void registrarReplicaCaso(CasoWSDTO casoWs)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarReplicaCaso(...)");
        service = (RegistrarReplicaCasoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("registrarReplicaCasoService");
        service.registraReplicaCaso(casoWs);
    }

}

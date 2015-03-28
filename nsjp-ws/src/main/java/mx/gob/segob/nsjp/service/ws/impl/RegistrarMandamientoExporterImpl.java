/**
 * Nombre del Programa : RegistrarMandamientoExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Sep 2011
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
import mx.gob.segob.nsjp.dto.documento.MandamientoWSDTO;
import mx.gob.segob.nsjp.service.documento.RegistrarMandamientoService;
import mx.gob.segob.nsjp.service.ws.RegistrarMandamientoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarMandamientoExporter")
public class RegistrarMandamientoExporterImpl
        implements
            RegistrarMandamientoExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarMandamientoExporterImpl.class);

    /**
     * Spring bean
     */
    private RegistrarMandamientoService service;

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.documento.RegistrarMandamientoService#
     * registrarMandamiento(mx.gob.segob.nsjp.dto.documento.MandamientoWSDTO)
     */
    @Override
    public void registrarMandamiento(MandamientoWSDTO manda)
            throws NSJPNegocioException {
        logger.info("Inicia - registrarMandamiento(...)");
        service = (RegistrarMandamientoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("registrarMandamientoService");
        service.registrarMandamiento(manda);
        logger.info("Fin - registrarMandamiento(...)");
    }

}

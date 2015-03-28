/**
 * Nombre del Programa : ActualizarSeguimientoMandamientoMedidaExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Sep 2011
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
import mx.gob.segob.nsjp.dto.documento.SeguimientoMandamientoMedidaWSDTO;
import mx.gob.segob.nsjp.service.documento.ActualizarSeguimientoMandamientoMedidaService;
import mx.gob.segob.nsjp.service.ws.ActualizarSeguimientoMandamientoMedidaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.ActualizarSeguimientoMandamientoMedidaExporter")
public class ActualizarSeguimientoMandamientoMedidaExporterImpl
        implements
            ActualizarSeguimientoMandamientoMedidaExporter {
    private final static Logger logger = Logger
            .getLogger(ActualizarSeguimientoMandamientoMedidaExporterImpl.class);

    private ActualizarSeguimientoMandamientoMedidaService service;
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.documento.
     * ActualizarSeguimientoMandamientoMedidaService
     * #actualizarSeguimiento(mx.gob
     * .segob.nsjp.dto.documento.SeguimientoMandamientoMedidaWSDTO)
     */
    @Override
    public void actualizarSeguimiento(SeguimientoMandamientoMedidaWSDTO input)
            throws NSJPNegocioException {
        logger.info("Inicia - actualizarSeguimiento(...)");
        service = (ActualizarSeguimientoMandamientoMedidaService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("actualizarSeguimientoMandamientoMedidaService");
        service.actualizarSeguimiento(input);
        logger.info("Fin - actualizarSeguimiento(...)");
    }

}

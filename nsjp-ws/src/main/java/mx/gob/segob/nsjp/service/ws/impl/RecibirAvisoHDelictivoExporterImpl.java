/**
 * Nombre del Programa : RecibirAvisoHDelictivoExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implmentación de web service para exponer el servicio que recibe el aviso de hecho
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
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoWSDTO;
import mx.gob.segob.nsjp.service.documento.RecibirAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.ws.RecibirAvisoHDelictivoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Implmentación de web service para exponer el servicio que recibe el aviso de
 * hecho.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RecibirAvisoHDelictivoExporter")
public class RecibirAvisoHDelictivoExporterImpl
        implements
            RecibirAvisoHDelictivoExporter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(RecibirAvisoHDelictivoExporterImpl.class);
    private RecibirAvisoHDelictivoService service;
    @Override
    public void recibirAvisoHDelictivoService(AvisoHechoDelictivoWSDTO aviso)
            throws NSJPNegocioException {
        logger.info("Inicia - recibirAvisoHDelictivoService(...)");
        service = (RecibirAvisoHDelictivoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("recibirAvisoHDelictivoService");
        service.recibirAvisoHDelictivoService(aviso);

    }

}

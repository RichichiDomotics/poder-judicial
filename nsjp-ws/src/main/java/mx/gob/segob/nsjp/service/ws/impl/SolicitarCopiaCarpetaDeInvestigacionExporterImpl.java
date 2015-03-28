/**
 * Nombre del Programa : SolicitarCopiaCarpetaDeInvestigacionExporterImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
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
import mx.gob.segob.nsjp.service.solicitud.SolicitarCopiaCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.ws.SolicitarCopiaCarpetaDeInvestigacionExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.SolicitarCopiaCarpetaDeInvestigacionExporter")
public class SolicitarCopiaCarpetaDeInvestigacionExporterImpl
        implements
            SolicitarCopiaCarpetaDeInvestigacionExporter {

    private final static Logger logger = Logger
            .getLogger(SolicitarCopiaCarpetaDeInvestigacionExporterImpl.class);
    private SolicitarCopiaCarpetaDeInvestigacionService service;
    @Override
    public SolicitudAudienciaBasicoWSDTO solicitarCopiaCarpetaDeInvestigacion(
            SolicitudAudienciaBasicoWSDTO solicitudAudienciaBasicoWSDTO, Long catDiscriminanteOrigen)
            throws NSJPNegocioException {
        logger.info("Inicia - solicitarCopiaCarpetaDeInvestigacion(...)");
        service = (SolicitarCopiaCarpetaDeInvestigacionService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
                .getBean("solicitarCopiaCarpetaDeInvestigacionService");
        return service
                .solicitarCopiaCarpetaDeInvestigacion(solicitudAudienciaBasicoWSDTO,catDiscriminanteOrigen);
    }

}

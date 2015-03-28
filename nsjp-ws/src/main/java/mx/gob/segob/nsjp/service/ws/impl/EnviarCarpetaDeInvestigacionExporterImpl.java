package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;
import mx.gob.segob.nsjp.service.expediente.EnviarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.ws.EnviarCarpetaDeInvestigacionExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;
import org.apache.log4j.Logger;

/**
 * Esta es la interfaz que expone los servicios como web services.
 * @author Jacob Lobaco
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.EnviarCarpetaDeInvestigacionExporter")
public class EnviarCarpetaDeInvestigacionExporterImpl implements
        EnviarCarpetaDeInvestigacionExporter {

    /**
     * Logger.
     */
    private final static Logger logger =
            Logger.getLogger(EnviarCarpetaDeInvestigacionExporterImpl.class);
    private EnviarCarpetaDeInvestigacionService enviarCarpetaDeInvestigacionService;

    @Override
    public Long enviarCarpetaDeInvestigacion(ExpedienteWSDTO expedienteWSDTO, String numeroGeneralCaso, String folioSolicitud)
            throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("EnviarCarpetaDeInvestigacionExporterImpl.enviarCarpetaDeInvestigacion Expediente = " + expedienteWSDTO);
            logger.debug("EnviarCarpetaDeInvestigacionExporterImpl.enviarCarpetaDeInvestigacion numeroGeneralCaso = " + numeroGeneralCaso);
            logger.debug("EnviarCarpetaDeInvestigacionExporterImpl.enviarCarpetaDeInvestigacion folioSolicitud = " + folioSolicitud);
            logger.debug("ApplicationContextAwareNSJPImpl.springApplicationContext = " +
                    ApplicationContextAwareWSNSJPImpl.springApplicationContext);
        }
        enviarCarpetaDeInvestigacionService = (EnviarCarpetaDeInvestigacionService)
                ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
                "enviarCarpetaDeInvestigacionService");
        return enviarCarpetaDeInvestigacionService.
                enviarCarpetaDeInvestigacion(expedienteWSDTO, numeroGeneralCaso, folioSolicitud);
    }
}

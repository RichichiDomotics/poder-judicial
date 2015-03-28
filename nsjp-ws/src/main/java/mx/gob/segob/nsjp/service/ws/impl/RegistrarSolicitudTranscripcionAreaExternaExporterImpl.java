/**
 * 
 */
package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionWSDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTranscripcionAreaExternaService;
import mx.gob.segob.nsjp.service.ws.RegistrarSolicitudTranscripcionAreaExternaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * @author rgama
 * 
 */															  
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarSolicitudTranscripcionAreaExternaExporter")
public class RegistrarSolicitudTranscripcionAreaExternaExporterImpl implements
	RegistrarSolicitudTranscripcionAreaExternaExporter {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(RegistrarSolicitudTranscripcionAreaExternaExporterImpl.class);

	private RegistrarSolicitudTranscripcionAreaExternaService service;


	@Override
	public SolicitudTranscripcionWSDTO registrarSolicitudTranscripcion(
			SolicitudTranscripcionWSDTO solicitud) throws NSJPNegocioException {
		logger.info("Inicia - registrarSolicitudTranscripcion(...)");
		service = (RegistrarSolicitudTranscripcionAreaExternaService)ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("registrarSolicitudTranscripcionAreaExternaService");
		return service.registrarSolicitudTranscripcion(solicitud);
	}

}

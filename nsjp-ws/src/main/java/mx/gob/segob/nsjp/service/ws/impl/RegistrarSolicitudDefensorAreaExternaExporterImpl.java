/**
 * 
 */
package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDefensorAreaExternaService;
import mx.gob.segob.nsjp.service.ws.RegistrarSolicitudDefensorAreaExternaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarSolicitudDefensorAreaExternaExporter")
public class RegistrarSolicitudDefensorAreaExternaExporterImpl implements
		RegistrarSolicitudDefensorAreaExternaExporter {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(RegistrarSolicitudDefensorAreaExternaExporterImpl.class);

	private RegistrarSolicitudDefensorAreaExternaService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.solicitud.
	 * RegistrarSolicitudDefensorAreaExternaService
	 * #registrarSolicitudDefensor(mx
	 * .gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO)
	 */
	@Override
	public SolicitudDefensorWSDTO registrarSolicitudDefensor(
			SolicitudDefensorWSDTO solicitud) throws NSJPNegocioException {
		logger.info("Inicia - registrarSolicitudDefensor(...)");
		service = (RegistrarSolicitudDefensorAreaExternaService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("registrarSolicitudDefensorAreaExternaService");
		return service.registrarSolicitudDefensor(solicitud);
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.service.audiencia.SolicitarAudienciaBasicoService;
import mx.gob.segob.nsjp.service.ws.SolicitarAudienciaBasicoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.SolicitarAudienciaBasicoExporter")
public class SolicitarAudienciaBasicoExporterImpl implements
		SolicitarAudienciaBasicoExporter {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(SolicitarAudienciaBasicoExporterImpl.class);
	private SolicitarAudienciaBasicoService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.audiencia.SolicitarAudienciaBasicoService#
	 * registrarSolicitudAudienciaBasico
	 * (mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO)
	 */
	@Override
	public Long registrarSolicitudAudienciaBasico(
			SolicitudAudienciaBasicoWSDTO solicitud)
			throws NSJPNegocioException {
		logger.info("Inicia - registrarSolicitudAudienciaBasico(...)");
		service = (SolicitarAudienciaBasicoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("solicitarAudienciaBasicoService");
		return service.registrarSolicitudAudienciaBasico(solicitud);
	}

}

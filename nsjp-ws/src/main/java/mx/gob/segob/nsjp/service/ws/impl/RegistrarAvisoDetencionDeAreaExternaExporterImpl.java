/**
 * 
 */
package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO;
import mx.gob.segob.nsjp.service.avisodetencion.RegistrarAvisoDetencionDeAreaExterna;
import mx.gob.segob.nsjp.service.ws.RegistrarAvisoDetencionDeAreaExternaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * @author vaguirre
 * 
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarAvisoDetencionDeAreaExternaExporter")
public class RegistrarAvisoDetencionDeAreaExternaExporterImpl implements
		RegistrarAvisoDetencionDeAreaExternaExporter {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(RegistrarAvisoDetencionDeAreaExternaExporterImpl.class);
	private RegistrarAvisoDetencionDeAreaExterna service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.avisodetencion.RegistrarAvisoDetencionDeAreaExterna
	 * #
	 * registrarAvisoDetencion(mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO
	 * )
	 */
	@Override
	public AvisoDetencionWSDTO registrarAvisoDetencion(AvisoDetencionWSDTO aviso, Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante)
			throws NSJPNegocioException {
		logger.info("Inicia - registrarAvisoDetencion(...)");
		service = (RegistrarAvisoDetencionDeAreaExterna) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("registrarAvisoDetencionDeAreaExterna");
		return service.registrarAvisoDetencion(aviso,idAgencia,claveAgencia,idFuncionarioSolicitante);
	}

}

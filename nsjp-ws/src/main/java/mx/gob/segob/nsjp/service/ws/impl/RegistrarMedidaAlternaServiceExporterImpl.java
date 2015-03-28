package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaWSDTO;
import mx.gob.segob.nsjp.service.medida.RegistrarMedidaAlternaService;
import mx.gob.segob.nsjp.service.ws.RegistrarMedidaAlternaServiceExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarMedidaAlternaServiceExporter")
public class RegistrarMedidaAlternaServiceExporterImpl implements
		RegistrarMedidaAlternaServiceExporter {

	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(RegistrarMedidaAlternaServiceExporterImpl.class);
	private RegistrarMedidaAlternaService service;
	
	@Override
	public void registrarMedidaAlterna(MedidaAlternaWSDTO medidaCautelar)
			throws NSJPNegocioException {
		logger.info("Inicia - registrarMedidaAlterna(...)");
		service = (RegistrarMedidaAlternaService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("registrarMedidaAlternaService");
		service.registrarMedidaAlterna(medidaCautelar);
	}

}

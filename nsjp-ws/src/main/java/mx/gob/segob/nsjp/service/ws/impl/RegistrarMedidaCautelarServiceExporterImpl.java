package mx.gob.segob.nsjp.service.ws.impl;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaCautelarWSDTO;
import mx.gob.segob.nsjp.service.medida.RegistrarMedidaCautelarService;
import mx.gob.segob.nsjp.service.ws.RegistrarMedidaCautelarServiceExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.RegistrarMedidaCautelarServiceExporter")
public class RegistrarMedidaCautelarServiceExporterImpl implements
		RegistrarMedidaCautelarServiceExporter {

	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(RegistrarMedidaCautelarServiceExporterImpl.class);
	private RegistrarMedidaCautelarService service;
	
	@Override
	public void registrarMedidaCautelar(MedidaCautelarWSDTO medidaCautelar)
			throws NSJPNegocioException {
		logger.info("Inicia - recibirMedidaCautelarService(...)");
		service = (RegistrarMedidaCautelarService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("registrarMedidaCautelarService");
		service.registrarMedidaCautelar(medidaCautelar);
	}

}

package mx.gob.segob.nsjp.dao.test.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.expediente.ConvenioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Convenio;

public class ConvenioDAOImplTest extends BaseTestPersistencia<ConvenioDAO> {

	public void testConsultarConveniosPorExpediente() {
		List<Convenio> acuerdos = daoServcice.consultarConveniosPorExpediente(1L);
		assertNotNull(acuerdos);
		logger.info("Existen "+acuerdos.size()+" acuerdos");
		for (Convenio acu : acuerdos) {
			logger.info("---------------------------------");
			logger.info("ID: "+acu.getDocumentoId());
			logger.info("NumConv: "+acu.getNumeroMediacion());
			String strMonto=(acu.getMonto()!=null)?acu.getMonto().toString():"sin monto";
			logger.info("montoTotal: "+strMonto);
			logger.info("FechaIncio: "+acu.getFechaInicio());
			logger.info("FechaFianl: "+acu.getFechaFin());
			logger.info("Periodicidad: "+acu.getPeriodicidad());
//		- Cantidad por cada pago.
//		- Fecha del último
		}
	}

}

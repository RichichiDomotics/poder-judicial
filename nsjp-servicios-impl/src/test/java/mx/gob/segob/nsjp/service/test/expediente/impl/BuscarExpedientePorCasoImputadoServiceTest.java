package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedientePorCasoImputadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class BuscarExpedientePorCasoImputadoServiceTest extends
		BaseTestServicios<BuscarExpedientePorCasoImputadoService> {

	public void testBuscarExpedientePorCasoImputado(){
		
		String nombre = "Vladimir Aguirre Piedragil";
		String numero = "YUC/PJ/XX/PGE/2011/AA-00000";
		
		ExpedienteDTO expediente = service.buscarExpedientePorCasoImputado(numero, nombre);
		assertNotNull("Error la buscar",expediente);
	}
	
}

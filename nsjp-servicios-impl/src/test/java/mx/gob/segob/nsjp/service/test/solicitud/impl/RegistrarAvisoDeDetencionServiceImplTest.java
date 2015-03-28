package mx.gob.segob.nsjp.service.test.solicitud.impl;

import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarAvisoDeDetencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarAvisoDeDetencionServiceImplTest extends
		BaseTestServicios<RegistrarAvisoDeDetencionService> {

	
	public void testRegistrarAvisoDeDetencion(){
		Long imputadoID = 1L;
		CasoDTO noCaso =  new CasoDTO();
		noCaso.setNumeroGeneralCaso("");
		
//		service.registrarAvisoDeDetencion(imputadoID, noCaso);
		
		
	}
	
}

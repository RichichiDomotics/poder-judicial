package mx.gob.segob.nsjp.service.test.solicitud.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class GenerarFolioSolicitudServiceImplTest extends BaseTestServicios<GenerarFolioSolicitudService> {

	public void testGenerarFolioSolicitud() throws NSJPNegocioException{
		for(int i = 0; i<5; i++){
			logger.debug("NUEVO FOLIO ==> "+service.generarFolioSolicitud());
		}
	}
	
}

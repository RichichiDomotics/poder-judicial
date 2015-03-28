package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.service.documento.AsociarArchivosDigitalesASolicitudService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class AsociarArchivoDigitalASolicitudServiceTest 
extends BaseTestServicios<AsociarArchivosDigitalesASolicitudService>{

	 public void testAsociarArchivosDigitalesASolicitud() throws Exception
	 {
		logger.debug("Ejecutando test del servicio asociar archivos digitales a solicitud");
		Boolean respuesta;
		respuesta = service.asociarArchivosDigitalesASolicitud(75L, "35,36,37");
		logger.debug("La respuesta es: " +respuesta);
	 }
}

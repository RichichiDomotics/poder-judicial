package mx.gob.segob.nsjp.service.test.leycodigo.imp;

import java.io.ByteArrayOutputStream;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.leycodigo.LeyCodigoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class LeyCodigoServiceTest extends BaseTestServicios<LeyCodigoService> {
	
	public void testObtenerNormasCategoria() throws NSJPNegocioException{
		
		ByteArrayOutputStream baf = service.leerLeyCodigo(1L);
		
		logger.info("== TAMAÑO DEL ARCHIVO :: "+baf.size());
		
	}

}

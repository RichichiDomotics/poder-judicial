/**
 * 
 */
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.FuncionService;

/**
 * @author LuisMG
 * 
 */
public class FuncionServiceImplTest extends BaseTestServicios<FuncionService> {

	public void testConsultarFunciones() throws IOException {
		try {
			List<String> ext = new ArrayList<String>();
			ext.add(".jsp");
			String path="C:/Proyectos/Eclipse/SEGOB/Seguridad/v1.22/nsjp/nsjp-web/target/nsjp-web/WEB-INF";
			List<FuncionDTO> respuesta = service.inventariarFunciones(new FuncionDTO(path),ext);
			if (respuesta != null) {
				//for (int i = 0; i < respuesta.size(); i++) {
				//	logger.info(i+" )" + respuesta.get(i).getNombreFuncion());
				//}
				logger.info("Total: " + respuesta.size() + " archivos");
			}else{
				logger.info("Total: 0");
			}
				
		//	assertTrue("La lista debe tener minimo un registro : ",
			//		respuesta.size() > 0);
		//	logger.info("La lista debe tener minimo un registro : "
			//		+ respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
}

}

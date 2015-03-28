/**
 * 
 */
package mx.gob.segob.nsjp.service.test.elemento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.elemento.AnularElementoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author MelitonBC
 *
 */
public class AnularElementoServiceImplTest extends BaseTestServicios<AnularElementoService>{
	

	public void testConsultarRelacionesElemento() {
		Long idElemento = 9117L;
//		Long idElemento = 9120L;
//		Long idElemento = 9123L;
		try {
			
			List<String> relacionesExistentes = service.consultarRelacionesElemento(idElemento);
			logger.info("####### Numero de relaciones:" + relacionesExistentes.size());
			for (String relacion : relacionesExistentes) {
				logger.info( relacion);
			}
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
		
	}

	public void testAnularElementoService(){
		logger.info("Probando el servicio de: AnularElementoService");
		Long idElemento = 9117L;
//		Long idElemento = 9120L;
//		Long idElemento = 9123L;
		try {
			service.anularElemento(idElemento);
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
		
	}
}

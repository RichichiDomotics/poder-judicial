/**
 * 
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosNoEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarObjetosNoEvidenciaServiceImplTest extends
		BaseTestServicios<ConsultarObjetosNoEvidenciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.objeto.impl.ConsultarObjetosNoEvidenciaServiceImpl#consultarObjetosNoEvidencia(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}.
	 */
	public void testConsultarObjetosNoEvidencia() {
		try {
			List<ObjetoDTO> objetos = service.consultarObjetosNoEvidencia(new ExpedienteDTO(60L));
			assertNotNull(objetos);
			logger.info("Existen "+objetos.size()+" objetos sin asignar");
			for (ObjetoDTO obj : objetos) {
				logger.info("--------------------");	
				logger.info("Id: "+obj.getElementoId());	
				logger.info("Desc: "+obj.getDescripcion());	
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

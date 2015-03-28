/**
 * 
 */
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDistritoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author AlineGS
 *
 */
public class ConsultarDistritoServiceImplTest extends BaseTestServicios<ConsultarDistritoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDistritoServiceImpl#consultarDistritos()}.
	 */
	public void testConsultarDistritos() {
		try {
			List<CatDistritoDTO> distritos = service.consultarDistritos();
			assertNotNull(distritos);
			logger.info("Existen "+distritos.size()+" distritos");
			for (CatDistritoDTO dto : distritos) {
				logger.info("----------------------------------");
				logger.info("Id: "+dto.getCatDistritoId());
				logger.info("Clave: "+dto.getClaveDistrito());
				logger.info("Nombre: "+dto.getNombreDist());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDistritoServiceImpl#consultarDistritoXId(java.lang.Long)}.
	 */
	public void testConsultarDistritoXId() {
		try {
			 CatDistritoDTO dto = service.consultarDistritoXId(2L);
			assertNotNull(dto);
				logger.info("Id: "+dto.getCatDistritoId());
				logger.info("Clave: "+dto.getClaveDistrito());
				logger.info("Nombre: "+dto.getNombreDist());
				if(dto.getDiscriminantes()!=null){
					logger.info("Existen "+dto.getDiscriminantes().size()+ " discriminantes");
					for (CatDiscriminanteDTO discDTO : dto.getDiscriminantes()) {
						logger.info("-------------------------------");
						logger.info("$Id: "+discDTO.getCatDiscriminanteId());
						logger.info("$Clave: "+discDTO.getClave());
						logger.info("$Nombre: "+discDTO.getNombre());
						logger.info("$Tipo: "+discDTO.getTipo());
						
					}
				}
				
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

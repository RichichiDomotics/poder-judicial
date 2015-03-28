/**
 * 
 */
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDistritoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author AlineGS
 *
 */
public class RegistrarDistritoServiceImplTest extends BaseTestServicios<RegistrarDistritoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.RegistrarDistritoServiceImpl#registrarDistrito(mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO)}.
	 */
	public void testRegistrarDistrito() {
//		CatDistritoDTO distritoDTO=new CatDistritoDTO(2L, "002", "Distrito 002");
		CatDistritoDTO distritoDTO=null;
		try {
			Long idDist = service.registrarDistrito(distritoDTO);
			assertNotNull(idDist);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testRegistrarDistritoConFantasma(){
//		CatDistritoDTO distritoDTO=new CatDistritoDTO(null, "02", "Distrito 02");
		CatDistritoDTO distritoDTO=null;
		try {
			Long idDist = service.registrarDistritoConFantasma(distritoDTO);
			assertNotNull(idDist);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

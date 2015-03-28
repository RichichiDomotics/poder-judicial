/**
 * 
 */
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDiscriminanteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author AlineGS
 *
 */
public class RegistrarDiscriminanteServiceImplTest extends BaseTestServicios<RegistrarDiscriminanteService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.RegistrarDiscriminanteServiceImpl#registrarDiscriminante(mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO)}.
	 */
	public void testRegistrarDiscriminante() {
		CatDistritoDTO catDistrito=new CatDistritoDTO(1L);
		CatDiscriminanteDTO dto=new CatDiscriminanteDTO(21L, catDistrito, "066", "Agencia Procu",(short) TipoDiscriminante.AGENCIA.ordinal());
		try {
			Long idDisc = service.registrarDiscriminante(dto);
			assertNotNull(idDisc);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.objeto.AsociarObjetoAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class AsociarObjetoAlmacenServiceImplTest extends BaseTestServicios<AsociarObjetoAlmacenService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.objeto.impl.AsociarObjetoAlmacenServiceImpl#asociarObjetoAlmacen(mx.gob.segob.nsjp.dto.objeto.ObjetoDTO, mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)}.
	 */
	public void testAsociarObjetoAlmacen() {
		ObjetoDTO objetoDTO=new ObjetoDTO(1175L);
		AlmacenDTO almacenDTO=new AlmacenDTO(1L);
		try {
			ObjetoDTO ojbRes = service.asociarObjetoAlmacen(objetoDTO, almacenDTO);
			assertNotNull(ojbRes);
			logger.info("Id Obj: "+ojbRes.getElementoId());
			logger.info("Id Alm: "+ojbRes.getAlmacen().getIdentificadorAlmacen());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

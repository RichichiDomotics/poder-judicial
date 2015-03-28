/**
 * 
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.AsociarDestinoLegalService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class AsociarDestinoLegalServiceImplTest extends BaseTestServicios<AsociarDestinoLegalService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.evidencia.impl.AsociarDestinoLegalServiceImpl#asociarDestinoLegal(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO, java.lang.Long)}.
	 */
	public void testAsociarDestinoLegal() {
		EvidenciaDTO evidenciaDTO= new EvidenciaDTO(1L);
		try {
			service.asociarDestinoLegal(evidenciaDTO, 1L);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

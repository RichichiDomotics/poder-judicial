/**
 * 
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.CambiarEstatusEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class CambiarEstatusEvidenciaServiceImplTest extends
		BaseTestServicios<CambiarEstatusEvidenciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.evidencia.impl.CambiarEstatusEvidenciaServiceImpl#cambiarEstatusEvidencia(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)}.
	 */
	public void testCambiarEstatusEvidencia() {
		EvidenciaDTO dto=new EvidenciaDTO(107L);
		dto.setEstatus(new ValorDTO(EstatusEvidencia.EN_ALMACEN.getValorId()));
		try {
			Long id = service.cambiarEstatusEvidencia(dto);
			assertNotNull(id);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}

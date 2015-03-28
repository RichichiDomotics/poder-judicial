/**
 * 
 */
package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarLineaInvestigacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarLineaInvestigacionServiceImplTest extends
		BaseTestServicios<GuardarLineaInvestigacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.GuardarLineaInvestigacionServiceImpl#guardarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)}.
	 */
	public void testGuardarLineaInvestigacion() {
		LineaInvestigacionDTO dto=new LineaInvestigacionDTO();
		
		dto.setLineaInvestigacionId(null);
		dto.setFechaCreacion(new Date());
		dto.setNumComentarios(null);
		dto.setFechaUltimoComentario(null);
		dto.setEsImpreso(null);
		dto.setDescripcion("Descripción de la Linea");
		dto.setTipoLineaInvestigacioValorDTO(new ValorDTO(1L));
		
		dto.setSeguimientoLIDTO(new SeguimientoLIDTO(1L));
		
		try {
			Long idLinea = service.guardarLineaInvestigacion(dto);
			assertNotNull(idLinea);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testActualizarLineaConImpreso(){
		try {
			service.actualizarLineaConImpreso(4L);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}

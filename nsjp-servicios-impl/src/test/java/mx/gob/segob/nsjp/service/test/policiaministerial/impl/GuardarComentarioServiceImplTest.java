/**
 * 
 */
package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarComentarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarComentarioServiceImplTest extends BaseTestServicios<GuardarComentarioService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.GuardarComentarioServiceImpl#guardarComentario(mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO)}.
	 */
	public void testGuardarComentario() {
		LineaInvestigacionDTO lineaDTO=new LineaInvestigacionDTO(1L);
		FuncionarioDTO funcDTO=new FuncionarioDTO(2L);
		ComentarioDTO dto=new ComentarioDTO();
		
		dto.setComentarioId(null);
		dto.setFechaCreacion(new Date());
		dto.setDescripcion("Descripción de Comentario tres de LI 1");
		
		
		dto.setLineaInvestigacionDTO(lineaDTO);
		dto.setFuncionarioDTO(funcDTO);
		
		try {
			Long idComent = service.guardarComentario(dto);
			assertNotNull(idComent);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

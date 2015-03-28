package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarComentarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class ConsultarComentarioServiceImplTest extends BaseTestServicios<ConsultarComentarioService> {

	public void testConsultarComentario() {
		try {
			ComentarioDTO comentarioDTO = service.consultarComentario(new ComentarioDTO(4L));
			assertNotNull(comentarioDTO);
			logger.info("CC--------------------------------------");
			logger.info("comentarioDTO: "+comentarioDTO);
			logger.info("comentarioDTO: "+comentarioDTO.getComentarioId());
			logger.info("comentarioDTO: "+comentarioDTO.getDescripcion());
			logger.info("comentarioDTO: "+comentarioDTO.getFechaCreacion());
			logger.info("comentarioDTO: "+comentarioDTO.getArchivoDigitalDTOs());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testConsultarComentariosXLinea() {
		try {
			List<ComentarioDTO> comentarios = service.consultarComentariosXLinea(new LineaInvestigacionDTO(3L));
			assertNotNull(comentarios);
			logger.info("Existen "+comentarios.size()+" comentarios");
			for (ComentarioDTO dto : comentarios) {
				logger.info("-------------------------------");
				logger.info("Comentario: "+dto);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

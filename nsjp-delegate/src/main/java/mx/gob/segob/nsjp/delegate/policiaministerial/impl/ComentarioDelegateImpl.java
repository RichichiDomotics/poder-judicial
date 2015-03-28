/**
 * 
 */
package mx.gob.segob.nsjp.delegate.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.policiaministerial.ComentarioDelegate;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarComentarioService;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service("comentarioDelegate")
public class ComentarioDelegateImpl implements ComentarioDelegate {

	@Autowired
	private GuardarComentarioService guardarComentarioService;
	@Autowired
	private ConsultarComentarioService consultarComentarioService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.ComentarioDelegate#guardarComentario(mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO)
	 */
	@Override
	public Long guardarComentario(ComentarioDTO comentarioDTO)
			throws NSJPNegocioException {
		return guardarComentarioService.guardarComentario(comentarioDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.ComentarioDelegate#consultarComentario(mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO)
	 */
	@Override
	public ComentarioDTO consultarComentario(ComentarioDTO comentarioDTO)
			throws NSJPNegocioException {
		return consultarComentarioService.consultarComentario(comentarioDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.ComentarioDelegate#consultarComentariosXLinea(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public List<ComentarioDTO> consultarComentariosXLinea(
			LineaInvestigacionDTO investigacionDTO) throws NSJPNegocioException {
		return consultarComentarioService.consultarComentariosXLinea(investigacionDTO);
	}

}

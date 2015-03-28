/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.policiaministerial.ComentarioDAO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.model.Comentario;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarComentarioService;
import mx.gob.segob.nsjp.service.policiaministerial.impl.transformer.ComentarioTransformer;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarComentarioServiceImpl implements
		ConsultarComentarioService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarComentarioServiceImpl.class);
	
	@Autowired
	private ComentarioDAO comentarioDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarComentarioService#consultarComentario(mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO)
	 */
	@Override
	public ComentarioDTO consultarComentario(ComentarioDTO comentarioDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN COMENTARIO ****/");
		
		if(comentarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(comentarioDTO.getComentarioId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Comentario comentario = comentarioDAO.read(comentarioDTO.getComentarioId());
		
		return ComentarioTransformer.transformarComentario(comentario);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarComentarioService#consultarComentariosXLinea(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public List<ComentarioDTO> consultarComentariosXLinea(
			LineaInvestigacionDTO investigacionDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN COMENTARIO POR LINEA DE INVESTIGACIÓN****/");
		
		if(investigacionDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(investigacionDTO.getLineaInvestigacionId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Comentario> comentarios=comentarioDAO.consultarComentariosXLinea(investigacionDTO.getLineaInvestigacionId());
		List<ComentarioDTO> comentariosDTO=new ArrayList<ComentarioDTO>();
		for (Comentario com : comentarios) {
			comentariosDTO.add(ComentarioTransformer.transformarComentario(com));
		}
				
		return comentariosDTO;
	}

}

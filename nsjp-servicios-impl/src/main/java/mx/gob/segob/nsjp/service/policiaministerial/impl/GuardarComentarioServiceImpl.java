/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.policiaministerial.ComentarioDAO;
import mx.gob.segob.nsjp.dao.policiaministerial.LineaInvestigacionDAO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.model.Comentario;
import mx.gob.segob.nsjp.model.LineaInvestigacion;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarComentarioService;
import mx.gob.segob.nsjp.service.policiaministerial.impl.transformer.ComentarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class GuardarComentarioServiceImpl implements GuardarComentarioService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarComentarioServiceImpl.class);
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	@Autowired
	private LineaInvestigacionDAO investigacionDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.GuardarComentarioService#guardarComentario(mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO)
	 */
	@Override
	public Long guardarComentario(ComentarioDTO comentarioDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UN COMENTARIO ****/");
		
		if(comentarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(comentarioDTO.getLineaInvestigacionDTO()==null||comentarioDTO.getFuncionarioDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Transformación*/
		Comentario comentario=ComentarioTransformer.transformarComentarioDTO(comentarioDTO);
		
		
		/*Operación*/
		Long idComent=comentario.getComentarioId();
		if(comentario.getComentarioId()==null|| comentario.getComentarioId().equals(0))
			idComent=comentarioDAO.create(comentario);
		else
			comentarioDAO.update(comentario);
		
		/*Actualiza Linea de Investigación*/
		LineaInvestigacion linea = investigacionDAO.read(comentarioDTO.getLineaInvestigacionDTO().getLineaInvestigacionId());
		Long numCom = 1L;
		if(linea.getNumComentarios()!=null)
			numCom=linea.getNumComentarios()+1;
		linea.setNumComentarios(numCom);
		linea.setFechaUltimoComentario(comentario.getFechaCreacion());
		investigacionDAO.update(linea);
		
		return idComent;
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Comentario;

/**
 * @author adrian
 *
 */
public interface ComentarioDAO extends GenericDao<Comentario, Long> {

	List<Comentario> consultarComentariosXLinea(Long lineaInvestigacionId);

}

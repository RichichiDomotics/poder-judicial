/**
 * 
 */
package mx.gob.segob.nsjp.dao.policiaministerial.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.policiaministerial.ComentarioDAO;
import mx.gob.segob.nsjp.model.Comentario;

/**
 * @author adrian
 *
 */
@Repository
public class ComentarioDAOImpl extends GenericDaoHibernateImpl<Comentario, Long> implements
		ComentarioDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> consultarComentariosXLinea(Long lineaInvestigacionId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" FROM Comentario co");
		sb.append(" WHERE co.lineaInvestigacion="+lineaInvestigacionId);
		
		Query query = super.getSession().createQuery(sb.toString());
		return query.list();
	}

}

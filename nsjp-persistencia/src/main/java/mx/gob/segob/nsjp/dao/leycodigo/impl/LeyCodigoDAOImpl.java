package mx.gob.segob.nsjp.dao.leycodigo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.leycodigo.LeyCodigoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.LeyCodigo;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LeyCodigoDAOImpl extends GenericDaoHibernateImpl<LeyCodigo, Long> implements LeyCodigoDAO {

	@Override
	public List<Valor> obtenerCatalogoNormas() throws NSJPNegocioException {
		//FIXME DJIMENEZ: ¿Y la funcionalidad?¿Se empléa este método?
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeyCodigo> obtenerNormasCategoria(Long id)
			throws NSJPNegocioException {
		final StringBuffer query = new StringBuffer();

		query.append(" FROM LeyCodigo l");
		query.append(" WHERE l.tipoNorma.valorId = "+id);
		logger.debug("query :: " + query);
//		Query hbq = super.getSession().createQuery(query.toString());
//		return hbq.list();
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		logger.debug("pag :: " + pag);
		return super.ejecutarQueryPaginado(query, pag);

		
	}

}

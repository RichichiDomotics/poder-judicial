/**
 * 
 */
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.CatDiscriminante;

/**
 * @author AlineGS
 *
 */
@Repository
public class CatDiscriminateDAOImpl extends GenericDaoHibernateImpl<CatDiscriminante, Long>
		implements CatDiscriminateDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDiscriminante> consultarDiscriminantesXDistrito(
			Long distritoID,Integer tipo) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" FROM CatDiscriminante dis");
		queryStr.append(" WHERE dis.distrito="+distritoID);
		if(tipo!=null)
			queryStr.append(" AND dis.clasificacion="+tipo);
		queryStr.append(" order by dis.nombre");
		
		
		return super.getHibernateTemplate().find(queryStr.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatDiscriminante> consultarDiscriminantesXTipo(int ordinal) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" FROM CatDiscriminante dis");
		queryStr.append(" WHERE dis.clasificacion="+ordinal);
		
        logger.debug("queryStr :: " + queryStr); 
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append("dis.clave");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("2")) {
                queryStr.append(" order by ");
                queryStr.append("dis.nombre");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }
        return super.ejecutarQueryPaginado(queryStr, pag);

	}
	
	
    @SuppressWarnings("unchecked")
	public List<CatDelito> consultarTodos() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append(" FROM CatDiscriminante dis ");
        queryStr.append(" order by dis.clave");
        
        logger.debug("queryStr :: " + queryStr); 
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append("dis.clave");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("2")) {
                queryStr.append(" order by ");
                queryStr.append("dis.nombre");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);
    }

	@Override
	public CatDiscriminante consultarDiscriminantePorId(Long dicriminanteId) {

		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" FROM CatDiscriminante c ");
		queryStr.append(" WHERE c.catDiscriminanteId = "+dicriminanteId);

		Query qry = super.getSession().createQuery(queryStr.toString());
		return (CatDiscriminante) qry.uniqueResult();
}
    public String consultarClaveDiscrimiante(String dicriminanteId) {

        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("SELECT c.claveDiscriminante");
        queryStr.append(" FROM CatDiscriminante c ");
        queryStr.append(" WHERE c.catDiscriminanteId = "+dicriminanteId);

        Query qry = super.getSession().createQuery(queryStr.toString());
        return (String) qry.uniqueResult();
    }


}

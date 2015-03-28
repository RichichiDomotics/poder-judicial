package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatFaltaAdministrativaDAO;
import mx.gob.segob.nsjp.model.CatFaltaAdministrativa;

import org.springframework.stereotype.Repository;

@Repository
public class CatFaltaAdministrativaDAOImpl 
       extends GenericDaoHibernateImpl<CatFaltaAdministrativa, Long> 
       implements CatFaltaAdministrativaDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatFaltaAdministrativa> consultarCatalogoFaltaAdministrativa() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" from CatFaltaAdministrativa c ");		
		queryStr.append(" order by c.nombreFalta");
		
		
        List<CatFaltaAdministrativa> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;				
	}

	
}

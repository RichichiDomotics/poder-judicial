package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.DiaInhabilDAO;
import mx.gob.segob.nsjp.model.DiaInhabil;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DiaInhabilDAOImipl extends GenericDaoHibernateImpl<DiaInhabil, Long>
		implements DiaInhabilDAO {

	@Override
	public List<DiaInhabil> consultarDiasInhabiles()
			throws NSJPNegocioException {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DiaInhabil di ");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();

	}

	@Override
	public List<DiaInhabil> consultarDiasInhabilesPorMes(Short mes)
			throws NSJPNegocioException {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DiaInhabil di where mes=" + mes);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
}

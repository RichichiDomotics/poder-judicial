package mx.gob.segob.nsjp.dao.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InvolucradoAudienciaDAOImpl extends GenericDaoHibernateImpl<InvolucradoAudiencia, InvolucradoAudienciaId> implements InvolucradoAudienciaDAO {
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO#consultarAudienciasDeInvolucrado(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> consultarAudienciasDeInvolucrado(Long involucradoId) {
		
		return getHibernateTemplate().find("select inva.audiencia from InvolucradoAudiencia inva where inva.id.involucradoId = ? " +
				"order by inva.audiencia.fechaAudiencia desc",
				involucradoId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO#actualizarIndicadorPresenteInvolucrado(java.lang.Long, java.lang.Long, boolean)
	 */
	@Override
	public void actualizarIndicadorPresenteInvolucrado(Long involucradoId,
			Long audienciaId, boolean presente) {
		
		getSession().createQuery("update InvolucradoAudiencia ia set ia.esPresente = ? where  "+
				" ia.id.audienciaId = ? and ia.id.involucradoId = ? ").
				setParameter(0, presente).
				setParameter(1, audienciaId).
				setParameter(2, involucradoId).
				executeUpdate();
		
	}

	@Override
	public Long obtenerMaximoIdAudienciaInvolucrado(Long idInvolucrado){
		final StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(IA.audiencia.audienciaId) FROM  InvolucradoAudiencia IA WHERE IA.involucrado.elementoId = " + idInvolucrado);
		Query hbq = super.getSession().createQuery(query.toString());
		return (Long) hbq.uniqueResult();
	}
	
	@Override
	public InvolucradoAudiencia consultarIndicadorPresenteInvolucradoAudiencia(Long involucradoAudienciaId, Long audienciaId){
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT ia FROM InvolucradoAudiencia ia WHERE ");
		queryStr.append(" ia.id.audienciaId = ").append(audienciaId);
		queryStr.append(" AND ia.id.involucradoId = ").append(involucradoAudienciaId);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (InvolucradoAudiencia) qry.uniqueResult();
	}
}

/**
 * Nombre del Programa : DelitoPersonaDAO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos de accesos a datos de la entidad Delito Persona
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.persona.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de accesos a datos de la entidad Delito Persona.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class DelitoPersonaDAOImpl extends
		GenericDaoHibernateImpl<DelitoPersona, Long> implements
		DelitoPersonaDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<DelitoPersona> consultarDelitoPerByInvolucrado(
			Long involucradoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoPersona dp ")
				.append("WHERE (dp.victima.elementoId=").append(involucradoId)
				.append(" OR ").append("dp.probableResponsable.elementoId=")
				.append(involucradoId).append(")");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<DelitoPersona> consultarDelitosPorImputado(Long involucradoId,
			Long expedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoPersona dp ")
				.append("WHERE dp.victima.elementoId=").append(involucradoId)
				.append(" OR ").append("dp.probableResponsable.elementoId=")
				.append(involucradoId)
				.append("and dp.delito.expediente.expedienteId=")
				.append(expedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelitoPersona> consultarDelitosPorImputadoResponsable(
			Long idInvolucrado, Long idExpediente) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoPersona dp WHERE");
		queryString.append(" dp.probableResponsable.elementoId="
				+ idInvolucrado);
		queryString.append(" AND dp.delito.expediente.expedienteId="
				+ idExpediente);
		queryString.append(" AND dp.esActivo = true");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelitoPersona> consultarVictimaImputadoPorDelito(Long idDelito,
			Long idExpediente) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoPersona dp WHERE");
		queryString.append(" dp.delito.expediente.expedienteId=" + idExpediente);
		if (idDelito != null)
			queryString.append(" AND dp.delito=" + idDelito);
			queryString.append(" AND dp.esActivo = true");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelitoPersona> consultarDelitosPersonaPorExpedienteIdsDelito(List<Long> idsDelitos,
			Long idExpediente) {
		
		String cadenaIdsDelitos = "";
		if (idsDelitos != null && !idsDelitos.isEmpty())
			cadenaIdsDelitos = idsDelitos.toString().substring(1,
					idsDelitos.toString().length() - 1);
				
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoPersona dp WHERE");
		queryString.append(" dp.delito.expediente.expedienteId=" + idExpediente);
		if (cadenaIdsDelitos != null && !cadenaIdsDelitos.trim().isEmpty())
			queryString.append(" AND dp.delito in (").append( cadenaIdsDelitos ).append( ")");
		
		queryString.append(" AND dp.esActivo = true");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	@Override
	public void eliminarRelacionPorDelito(Delito delPersist) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("DELETE FROM DelitoPersona dp");
		queryString.append(" WHERE dp.delito.delitoId="+delPersist.getDelitoId());
		Query query=super.getSession().createQuery(queryString.toString());
		query.executeUpdate();
		
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO#consultarDelitosPorImputadoResponsable(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DelitoPersona> consultarDelitosPorImputadoResponsable(
			Long idInvolucrado) {
		
		//return getHibernateTemplate().find("from DelitoPersona dp where dp.probableResponsable.elementoId=? AND dp.esActivo = true",idInvolucrado);
		
		StringBuffer query = new StringBuffer();
		query.append("from DelitoPersona dp where dp.probableResponsable.elementoId = ");
		query.append(idInvolucrado);
		query.append(" AND dp.esActivo = true");
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
		
	}
	
	@Override
	public void desactivarDelitoPersona(Long delitoPersonaId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("UPDATE FROM DelitoPersona dp");
		queryString.append(" SET dp.esActivo = false " );
		queryString.append(" WHERE dp.delitoPersonaId="+ delitoPersonaId);
		Query query=super.getSession().createQuery(queryString.toString());
		query.executeUpdate();
		
	}

	@Override
	public Long obtenerDetenidosPorMesYDelito(Date fechaInicio,
			Date fechaFin, Long catDelito) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT COUNT(*) FROM DelitoPersona dp")
					.append(" WHERE dp.probableResponsable.esDetenido=").append(true)
					.append(" AND dp.delito.catDelito=").append(catDelito).append(" AND ")
					.append("CONVERT(VARCHAR, dp.delito.expediente.fechaCreacion ,112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaInicio)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFin));
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();
	}

	@Override
	public Boolean existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(Long idDelito, Long idProbableResponsable, Long idVictima, Long idFormaParticipacion) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT dp FROM DelitoPersona dp")
					.append(" WHERE dp.probableResponsable.elementoId=").append(idProbableResponsable)
					.append(" AND dp.delito.delitoId =").append(idDelito).append(" AND ")
					.append(" dp.victima.elementoId= ").append(idVictima).append(" AND ")
					.append(" dp.formaParticipacion.valorId = ").append(idFormaParticipacion);
		Query query = super.getSession().createQuery(queryString.toString());
		return !(query.list().isEmpty());
	}
}

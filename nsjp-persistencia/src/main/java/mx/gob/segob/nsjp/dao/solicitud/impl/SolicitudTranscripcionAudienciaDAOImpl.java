package mx.gob.segob.nsjp.dao.solicitud.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudTranscripcionAudienciaDAOImpl extends GenericDaoHibernateImpl<SolicitudTranscripcionAudiencia, Long> implements
		SolicitudTranscricpionAudienciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTranscripcionAudiencia> consultarSolicitudesTrascripcionAudienciaPendientes() {
        final StringBuffer query = new StringBuffer();
        query.append("from SolicitudTranscripcionAudiencia s");
        query.append(" where s.estatus = "+EstatusSolicitud.ABIERTA.getValorId());
        logger.debug(query);
        Query hbq = super.getSession().createQuery(query.toString());

        return hbq.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTranscripcionAudiencia> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(
			Long idAudiencia, Long tipoId, Long estatusId) {
		
		final StringBuffer query = new StringBuffer();
        query.append("from SolicitudTranscripcionAudiencia s")
        .append(" where s.estatus = "+ estatusId)
        .append(" and s.tipoSolicitud = " + tipoId)
        .append(" and s.audiencia.audienciaId = " + idAudiencia)
        .append(" and s.nombreSolicitante is not null");
        logger.debug(query);
        Query hbq = super.getSession().createQuery(query.toString());

        return hbq.list();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTranscripcionAudiencia> consultarSolicitudMaster(
			Long idAudiencia, Long idTipo) {
		final StringBuffer query = new StringBuffer();
        query.append("from SolicitudTranscripcionAudiencia s")        
        .append(" where s.tipoSolicitud = " + idTipo)
        .append(" and s.audiencia.audienciaId = " + idAudiencia)
        .append(" and s.nombreSolicitante is null");
        logger.debug(query);
        Query hbq = super.getSession().createQuery(query.toString());

        return hbq.list();
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO#consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudTranscripcionAudiencia> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(
			Long tipoId, Long estatusId,Long dif,Long discriminanteId, Date fechaIni, Date fechaFin) {
		
		StringBuffer query = new StringBuffer();
        query.append("from SolicitudTranscripcionAudiencia s")
        .append(" where s.estatus.valorId =")
        .append(estatusId)
        .append(" and s.tipoSolicitud.valorId =")
        .append(tipoId);
        query.append(" and s.audiencia.numeroExpediente.expediente.discriminante.catDiscriminanteId=")
        .append(discriminanteId);
        
        if(fechaIni!=null && fechaFin!=null ){
        	query.append(" AND CONVERT (nvarchar, s.fechaCreacion, 112) between ");
        	query.append(DateUtils.formatearBD(fechaIni));
        	query.append(" AND ").append(DateUtils.formatearBD(fechaFin));
        }
        else if (fechaIni != null) {
				query.append(" AND CONVERT (nvarchar, s.fechaCreacion, 112) = ");
				query.append(DateUtils.formatearBD(fechaIni));
        }
        
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
       // return getHibernateTemplate().find(query.toString(),estatusId,tipoId);
       	
	}

}

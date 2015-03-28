/**
 * Nombre del Programa : AudienciaDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.audiencia.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Resolutivo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class AudienciaDAOImpl extends GenericDaoHibernateImpl<Audiencia, Long>
		implements AudienciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> consultarAudienciasPendientesNotificacion(
			BandejaNotificador tipoBandeja) {
		final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		final StringBuffer qryStr = new StringBuffer();
		qryStr.append("from Audiencia as a");
		qryStr.append(" where a.fechaAsignacionSala is not null");
		switch (tipoBandeja) {
		case NUEVO:
			qryStr.append(" AND CONVERT (nvarchar, a.fechaAsignacionSala, 112) = ");
			qryStr.append(formato.format(new Date()));
			break;
		case SEGUIMIENTO:
			qryStr.append(" AND CONVERT (nvarchar, a.fechaAudiencia, 112) >= ");
			qryStr.append(formato.format(new Date()));
			qryStr.append(" AND CONVERT (nvarchar, a.fechaAsignacionSala, 112) != ");
			qryStr.append(formato.format(new Date()));
			break;

		default:
			break;
		}
		logger.debug("qryStr :: " + qryStr);

		return super.getHibernateTemplate().find(qryStr.toString());

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> consultarAudienciasporExpediente(
			Long numeroExpedienteId) {
		final StringBuffer qryStr = new StringBuffer();
		qryStr.append("from Audiencia as a");
		qryStr.append(" where a.numeroExpediente.numeroExpedienteId = ");
		qryStr.append(numeroExpedienteId);
		logger.debug("qryStr :: " + qryStr);

		return super.getHibernateTemplate().find(qryStr.toString());

	}

	@Override
	public Audiencia consultarAudienciaById(Long id) {
		final StringBuffer query = new StringBuffer();
		query.append("from Audiencia as a where a.audienciaId = " + id);
		Query hbq = super.getSession().createQuery(query.toString());
		return (Audiencia) hbq.uniqueResult();
	}

//	@Override
@SuppressWarnings("unchecked")
	//	public List<Audiencia> buscarAudiencias(Long usrId, Long noExpId,
//			Date fecIni, Date fecFin, boolean diaCompleto) {
    @Override
    public List<Audiencia> buscarAudiencias(Long usrId, Long noExpId,
            Date fecIni, Date fecFin, boolean diaCompleto, Long tipoAudiencia, boolean isCausa,Long discriminanteId, Long idDistrito) {
    	final StringBuffer qs = new StringBuffer();
		qs.append(" from Audiencia a left outer join fetch a.solicitud ");
		qs.append(" where  1=1 ");

		//SE COMENTA TEMPORALMENTE PARA PROBAR FUNCIONALIDAD
		//if (usrId != null) {
		//qs.append(" and a.salaAudiencia.encargado.claveFuncionario = ");
		//qs.append(usrId);
		//}

		if (noExpId != null) {
			qs.append(" and a.numeroExpediente.numeroExpedienteId = ");
			qs.append(noExpId);
			if(isCausa){
				qs.append(" or a.numeroExpediente.numeroExpedientePadre.numeroExpedienteId = ");
				qs.append(noExpId);
				
			}
		}
		        
        if (tipoAudiencia != null) {
            qs.append(" and a.tipo.valorId = ");
            qs.append(tipoAudiencia);
        }

		if (fecFin != null) {
			qs.append(" AND CONVERT (nvarchar, a.fechaAudiencia, 112) between ");
			qs.append(DateUtils.formatearBD(fecIni));
			qs.append(" AND ").append(DateUtils.formatearBD(fecFin));

		} else {
			if (fecIni != null) {
				qs.append(" AND CONVERT (nvarchar, a.fechaAudiencia, 112) = ");
				qs.append(DateUtils.formatearBD(fecIni));
				if (!diaCompleto) {
					final SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy/dd/MM HH:mm:ss.SSS");
					qs.append(" AND a.fechaAudiencia >= ('");
					qs.append(sdf.format(fecIni));
					qs.append("')");
				}
			}
		}
		
		if(discriminanteId != null){
			qs.append(" AND a.numeroExpediente.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		}
		
		if(idDistrito != null){
			qs.append(" AND a.numeroExpediente.expediente.discriminante.distrito.catDistritoId=").append(idDistrito);
		}
		
		

		// TODO VAP construir query (encargado sala)

		logger.debug("qs :: " + qs);
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("expediente")) {
            	qs.append(" order by ");
            	qs.append("a.numeroExpediente");
            	qs.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("fechaAudiencia")) {
            	qs.append(" order by ");
            	qs.append("a.fechaAudiencia");
            	qs.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(qs, pag);

		//return super.getHibernateTemplate().find(qs.toString());
	}

	@Override
	public Long obtenerIdAudienciaByIdSolicitud(Long solId) {
		final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		final StringBuffer qryStr = new StringBuffer();
		qryStr.append("select a.audienciaId from Audiencia as a");
		qryStr.append(" where a.solicitud.documentoId = ");
		qryStr.append(solId);
		logger.debug("qryStr :: " + qryStr);
		final Query qry = super.getSession().createQuery(qryStr.toString());
		return (Long) qry.uniqueResult();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO#consultarAudienciasPorEstatus
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> consultarAudienciasPorEstatus(Long estatus) {

		final StringBuffer qryStr = new StringBuffer();


		qryStr.append(" from Audiencia as a");
		qryStr.append(" where a.estatus=" + estatus);

		logger.debug("qryStr :: " + qryStr);

		return super.getHibernateTemplate().find(qryStr.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resolutivo> consultarResolutivosAudiencia(Long idAudiencia) {
		final StringBuffer qryStr = new StringBuffer();

		qryStr.append("SELECT r");
		qryStr.append(" from Resolutivo as r");
		qryStr.append(" where r.audiencia.audienciaId=" + idAudiencia);

		logger.debug("qryStr :: " + qryStr);

		List hbq = super.getHibernateTemplate().find(qryStr.toString());
		
		return hbq;
	}

	@SuppressWarnings("unchecked")
	public List<Audiencia> buscarAudienciasSinResolutivos() {
		final StringBuffer qryStr = new StringBuffer();

		qryStr.append(" from Audiencia as a");
		qryStr.append(" where a.fechaHoraFin != null");
		qryStr.append(" and a.conResolutivos = false");

		logger.debug("qryStr :: " + qryStr);

		return super.getHibernateTemplate().find(qryStr.toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciasByTipoYFecha(Date fechaEvento,
			Long tipoAudiencia,Long discriminanteId) {
		String fechaString = DateUtils.formatearBD(fechaEvento);
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a ")
					.append("WHERE CONVERT (nvarchar, a.fechaAudiencia, 112)>=")
					.append(fechaString).append(" AND a.tipo=")
					.append(tipoAudiencia);
		queryString.append(" AND a.numeroExpediente.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		
		Query query = super.getSession().createQuery(queryString.toString());
		logger.debug("Query :: " + queryString);
		return query.list();
	}

	@Override
	public Audiencia obtenerAudienciaByNumeroAudiencia(Short consecutivo) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a WHERE ")
					.append("a.consecutivo=").append(consecutivo);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Audiencia)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> buscarAudienciaByCausa(String numeroExpediente,Long discriminanteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT au  FROM Audiencia au ")
					.append("WHERE au.numeroExpediente.numeroExpediente=?");
		queryString.append(" AND au.numeroExpediente.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		
		return getHibernateTemplate().find(queryString.toString(),numeroExpediente);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciaByNumeroExpedienteYEstatus(
			Long numeroExpedienteId, Long estatusAudiencia) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT au FROM Audiencia au ")
					.append("WHERE au.numeroExpediente=")
					.append(numeroExpedienteId).append(" AND ")
					.append("au.estatus=").append(estatusAudiencia);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciaByNumeroExpedienteYListaEstatus(Long numeroExpedienteId,
			EstatusAudiencia[] estatusAudiencia) {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT au FROM Audiencia au ")
			 .append(" WHERE au.numeroExpediente=")
			 .append(numeroExpedienteId).append(" AND ")
			 .append(" au.estatus.valorId in (");
		
				for(int i=0;i<estatusAudiencia.length;i++){
					if(i>0){
						query.append(",");
					}
					query.append(estatusAudiencia[i].getValorId());
				}
				
		query.append(")");
		query.append(" order by au.fechaAudiencia desc");
		
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
        
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciasByFechasYEstatus(
			Long estatusAudiencia, Date fechaInicio, Date fechaFin) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a WHERE ")
					.append("a.estatus=").append(estatusAudiencia);
					
		if (fechaInicio!=null && fechaFin!=null) {
			queryString.append(" AND CONVERT (nvarchar, a.fechaAudiencia, 112) BETWEEN ")
						.append(DateUtils.formatearBD(fechaInicio)).append(" AND ")
						.append(DateUtils.formatearBD(fechaFin));
		} else {
			Calendar calTemp = Calendar.getInstance();
			calTemp.setTime(new Date());
			queryString.append(" AND CONVERT (nvarchar, a.fechaAudiencia, 112)>=")
						.append(DateUtils.formatearBD(calTemp.getTime()));
		}
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO#consultarAudienciasConSolicitudesPorTipoYEstado(mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes[], mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Audiencia> consultarAudienciasConSolicitudesPorTipoYEstado(
			TiposSolicitudes[] tipos, EstatusSolicitud[] estados,Long discriminanteId) {
		
		StringBuffer query = new StringBuffer();
		query.append("from Audiencia au where au.audienciaId in ( ");
		query.append("select sol.audiencia.audienciaId from SolicitudTranscripcionAudiencia sol where ");
		query.append("sol.tipoSolicitud.valorId in (");
		for(int i=0;i<tipos.length;i++){
			if(i>0){
				query.append(",");
			}
			query.append(tipos[i].getValorId());
		}
		query.append(") and sol.estatus.valorId in (");
		for(int i=0;i<estados.length;i++){
			if(i>0){
				query.append(",");
			}
			query.append(estados[i].getValorId());
		}
		query.append(") )");
		
		//TODO checar query 
		query.append(" AND au.numeroExpediente.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		query.append(" order by au.fechaAudiencia desc");
		
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
		//return getHibernateTemplate().find(query.toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciasByTipoYEstatus(Long tipoAudiencia,
			Long estatusAudiencia) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a WHERE ")
					.append("a.tipo=").append(tipoAudiencia)
					.append(" AND a.estatus=").append(estatusAudiencia);
		
//		Query query = super.getSession().createQuery(queryString.toString());
//		return query.list();
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		logger.debug("pag :: " + pag);
		return super.ejecutarQueryPaginado(queryString, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerAudienciasJudicializadasPorMes(Date fechaInicial, Date fechaFinal) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH(a.numeroExpediente.fechaApertura), COUNT(*) FROM Audiencia a")
					.append(" WHERE (a.tipo=").append(TipoAudiencia.IMPUTACION.getValorId()).append(" OR ")
					.append("a.tipo=").append(TipoAudiencia.CONTROL.getValorId()).append(") AND ")
					.append(" CONVERT (varchar, a.numeroExpediente.fechaApertura, 112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaInicial)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFinal)).append(" GROUP BY MONTH(a.numeroExpediente.fechaApertura)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO#obtenerUltimoFolioAudiencia()
	 */
	@Override
	public String obtenerUltimoFolioAudiencia() {
		final StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(d.folioAudiencia) ");
		query.append("FROM Audiencia d ");
		query.append("WHERE  ");
		query.append(" d.folioAudiencia like '%"+Calendar.getInstance().get(Calendar.YEAR)+"%'");
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (String) hbq.uniqueResult();
	}

	@Override
	public Audiencia obtnerAudienciaByFolio(String folioAudiecia) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a WHERE ")
					.append("a.folioAudiencia ='").append(folioAudiecia)
					.append("'");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Audiencia)query.uniqueResult();
	}
	
	@Override
	public Funcionario obtenerFuncionarioDeNumExpedienteDeAudiencia(Long audienciaId){
		StringBuffer queryString = new StringBuffer();
		queryString.append("Select A.numeroExpediente.funcionario FROM Audiencia A, NumeroExpediente N, Funcionario F, Usuario U ").
			append(" where A.audienciaId= ").
			append(audienciaId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Funcionario)query.uniqueResult();
	}
	
	@Override
	public Boolean consultarAudienciasByFechaAudienciaYSala(Date fechaInicio,Date fechaFin, Long salaId) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Audiencia a WHERE");
		
		if (fechaInicio != null && fechaFin != null){
			
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");
			
			queryString.append(" a.fechaAudiencia BETWEEN ('");
			queryString.append(sdf.format(fechaInicio));
			queryString.append("')");
			queryString.append(" AND ('");
			queryString.append(sdf.format(fechaFin));
			queryString.append("')");
			queryString.append(" AND a.salaAudiencia.salaAudienciaId=")
			.append(salaId);
		}
		Query query = super.getSession().createQuery(queryString.toString());
		
		logger.debug("Query :: " + queryString);
		if(query.list() != null && query.list().size() > 0){
			return false;
		}
		else{
			return true;
		}
	}
}

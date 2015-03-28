/**
 * Nombre del Programa : SolicitudDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 16 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del objeto de acceso a datos para la entidad Solicitud.
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
package mx.gob.segob.nsjp.dao.solicitud.impl;

import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del objeto de acceso a datos para la entidad Solicitud.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class SolicitudDAOImpl extends GenericDaoHibernateImpl<Solicitud, Long>
		implements SolicitudDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesPorExpediente(
			Long numeroExpedienteId) {
		final StringBuffer query = new StringBuffer();
		query.append("from Solicitud s");
		query.append(" where s.numeroExpediente.numeroExpedienteId = ");
		query.append(numeroExpedienteId);
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());

		return hbq.list();
	}

	@Override
	public Solicitud consultarSolicitudPorDocumentoId(long solicitudId) {
		final StringBuffer query = new StringBuffer();
		query.append("from Solicitud s");
		query.append(" where s.documentoId = ");
		query.append(solicitudId);
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (Solicitud) hbq.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudXEstatus(Long estatusSolicitud,
			Long tipoSolicitud) {
		final StringBuffer query = new StringBuffer();

		query.append(" FROM Solicitud s");
		query.append(" WHERE s.estatus = ");
		query.append(estatusSolicitud);
		if (tipoSolicitud != null) {
			query.append(" AND s.tipoSolicitud =");
			query.append(tipoSolicitud);
		}

		final PaginacionDTO pag = PaginacionThreadHolder.get();
		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				query.append(" order by ");
				query.append("s.folioSolicitud");
				query.append(" ").append(pag.getDirOrd());
			}
		}
		logger.debug("query :: " + query);
		final Query queryStr = super.getSession().createQuery(query.toString());
		if (pag != null && pag.getPage() != null) {
			queryStr.setFirstResult(pag.getFirstResult());
			if (pag.getRows() != null & pag.getRows() > 0) {
				queryStr.setMaxResults(pag.getRows());
			} else {
				queryStr.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
			}
		}

		final List<Solicitud> resp = queryStr.list();
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		if (pag != null && pag.getPage() != null) {
			queryStr.setFirstResult(0);
			queryStr.setMaxResults(-1);
			final List<Solicitud> temp = queryStr.list();
			logger.debug("temp.size() :: " + temp.size());
			
			pag.setTotalRegistros((long)temp.size());
			PaginacionThreadHolder.set(pag);
		}

		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudXEstatusYPuesto(
			Long estatusSolicitud, Long tipoSolicitud, Long puestoId) {

		final StringBuffer query = new StringBuffer();

		query.append(" FROM Solicitud s");
		query.append(" WHERE s.estatus = ");
		query.append(estatusSolicitud);
		if (tipoSolicitud != null) {
			query.append(" AND s.tipoSolicitud =");
			query.append(tipoSolicitud);
		}
		query.append(" AND s.destinatario.puesto.valorId = " + puestoId);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				query.append(" order by ");
				query.append("s.folioSolicitud");
				query.append(" ").append(pag.getDirOrd());
			}
		}
		logger.debug("query :: " + query);
		final Query queryStr = super.getSession().createQuery(query.toString());
		if (pag != null && pag.getPage() != null) {
			queryStr.setFirstResult(pag.getFirstResult());
			if (pag.getRows() != null & pag.getRows() > 0) {
				queryStr.setMaxResults(pag.getRows());
			} else {
				queryStr.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
			}
		}

		final List<Solicitud> resp = queryStr.list();
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		if (pag != null && pag.getPage() != null) {
			queryStr.setFirstResult(0);
			queryStr.setMaxResults(-1);
			final List<Solicitud> temp = queryStr.list();
			logger.debug("temp.size() :: " + temp.size());
			pag.setTotalRegistros((long)temp.size());
			PaginacionThreadHolder.set(pag);
		}

		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudAudiencia> consultarSolicitudAudienciaXEstatus(
			Long estatusSolicitud, Long tipoSolicitud) {
		final StringBuffer query = new StringBuffer();

		query.append(" FROM SolicitudAudiencia s");
		query.append(" WHERE s.estatus = ");
		query.append(estatusSolicitud);
		if (tipoSolicitud != null) {
			query.append(" AND s.tipoSolicitud =");
			query.append(tipoSolicitud);
		}

		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());

		return hbq.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesPorNumeroExpedienteYTipo(
			Long numeroExpedienteId, TiposSolicitudes tipo) {
		final StringBuffer query = new StringBuffer();

		query.append("from Solicitud s");
		query.append(" where s.numeroExpediente.numeroExpedienteId = ? and ");
		query.append(" s.tipoSolicitud.valorId = ?");
		return getHibernateTemplate().find(query.toString(),
				numeroExpedienteId, tipo.getValorId());
	}

	@Override
	public Solicitud consultarSolicitudesPorExpedienteYTipo(Long idExpediente,
			TiposSolicitudes carpetaInvestigacion) {
		final StringBuffer query = new StringBuffer();
		query.append("from Solicitud s");
		query.append(" where s.numeroExpediente.numeroExpedienteId = ");
		query.append(idExpediente);
		query.append(" and s.tipoSolicitud.valorId = "
				+ carpetaInvestigacion.getValorId());
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());

		return (Solicitud) hbq.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO#
	 * consultarNumeroExpedienteDeSolicitud(java.lang.Long)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Long consultarNumeroExpedienteDeSolicitud(Long solicitudId) {
		List resultado = getHibernateTemplate().find(
				"select sol.numeroExpediente.numeroExpedienteId from Solicitud sol where "
						+ "sol.documentoId = ?", solicitudId);
		if (resultado != null && resultado.size() > 0) {
			return (Long) (resultado.get(0));
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public Solicitud consultarSolicitudPorFolio(String folioSolicitud) {

		List resultado = getHibernateTemplate().find(
				"select sol from Solicitud sol where "
						+ "sol.folioSolicitud = ?", folioSolicitud);
		return (resultado != null && !resultado.isEmpty()) ? (Solicitud) (resultado
				.get(0)) : new Solicitud();
	}

	@SuppressWarnings("rawtypes")
	public Expediente consultarExpedienteDeNumeroExpedienteSolicitud(
			Long solicitudId) {
		List resultado = getHibernateTemplate().find(
				"select sol.numeroExpediente.expediente from Solicitud sol where "
						+ "sol.documentoId = ?", solicitudId);
		return (resultado != null && !resultado.isEmpty()) ? (Expediente) (resultado
				.get(0)) : new Expediente();
	}

	@Override
	public NumeroExpediente consultarNumeroExpedienteSolicitud(Long solicitudId) {
		List resultado = getHibernateTemplate().find(
				"select sol.numeroExpediente from Solicitud sol where "
						+ "sol.documentoId = ?", solicitudId);
		return (resultado != null && !resultado.isEmpty()) ? (NumeroExpediente) (resultado
				.get(0)) : new NumeroExpediente();
	}

    public String obtenerUltimoFolioSolicitud() throws NSJPNegocioException {
        final StringBuffer query = new StringBuffer();
        query.append("SELECT MAX(s.folioSolicitud) ");
        query.append("FROM Solicitud s ");
        query.append("WHERE s.folioSolicitud like '%");
        query.append(super.consultarInsitucionActual().getMonograma());
        query.append("%");
        query.append(Calendar.getInstance().get(Calendar.YEAR));
        query.append("%'");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return (String) hbq.uniqueResult();
    }

	@Override
	public Solicitud obtenerSolicitudPorFolio(String folio)
			throws NSJPNegocioException {
		final StringBuffer query = new StringBuffer();
		query.append("FROM Solicitud s ");
		query.append("WHERE s.folioSolicitud like '%" + folio + "%'");
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (Solicitud) hbq.uniqueResult();
	}

	@Override
	public Solicitud consultarSolicitudPorId(Long idSolicitud)
			throws NSJPNegocioException {
		final StringBuffer query = new StringBuffer();
		query.append("FROM Solicitud s ");
		query.append("WHERE s.documentoId = " + idSolicitud);
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (Solicitud) hbq.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesGeneradas(
			List<Long> idEstatusSolicitud, List<Long> idTipoSolicitud,
			Long idAreaOrigen, Long idFuncionarioSolicitante) {
		return consultarSolicitudesGeneradasPorNumeroExpediente(
				idEstatusSolicitud, idTipoSolicitud, idAreaOrigen,
				idFuncionarioSolicitante, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesGeneradasPorNumeroExpediente(
			List<Long> idEstatusSolicitud, List<Long> idTipoSolicitud,
			Long idAreaOrigen, Long idFuncionarioSolicitante,
			String numeroExpediente) {
		String cadenaIdES = "";
		String cadenaIdTS = "";

		if (idEstatusSolicitud != null && !idEstatusSolicitud.isEmpty())
			cadenaIdES = idEstatusSolicitud.toString().substring(1,
					idEstatusSolicitud.toString().length() - 1);

		if (idTipoSolicitud != null && !idTipoSolicitud.isEmpty())
			cadenaIdTS = idTipoSolicitud.toString().substring(1,
					idTipoSolicitud.toString().length() - 1);

		logger.info("cadenaIdES:" + cadenaIdES);

		final StringBuffer query = new StringBuffer();

		query.append(" FROM Solicitud s");
		query.append(" WHERE 1=1 ");

		if (idFuncionarioSolicitante != null && idFuncionarioSolicitante > 0) {
			query.append(" AND s.funcionarioSolicitante.claveFuncionario = ");
			query.append(idFuncionarioSolicitante);
		}

		if (!cadenaIdES.trim().isEmpty()) {
			query.append(" AND s.estatus in ( ");
			query.append(cadenaIdES);
			query.append(" ) ");
		}

		if (!cadenaIdTS.trim().isEmpty()) {
			query.append(" AND s.tipoSolicitud in (");
			query.append(cadenaIdTS);
			query.append(" ) ");
		}

		if (idAreaOrigen != null && idAreaOrigen > 0) {
			query.append(" AND s.areaOrigen = ");
			query.append(idAreaOrigen);
		}
		if (numeroExpediente != null && !numeroExpediente.trim().isEmpty()) {
			query.append(" AND s.numeroExpediente.numeroExpediente like '%");
			query.append(numeroExpediente);
			query.append("%' ");
		}

		query.append(" order by s.fechaCreacion asc");

		logger.debug("query :: " + query);
//		Query hbq = super.getSession().createQuery(query.toString());
//		return hbq.list();
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesParaAtender(
			List<Long> idEstatusSolicitud, List<Long> idTipoSolicitud,
			Long idAreaDestino, Long idFuncionarioDestinatario, Long idCatDiscriminante) {
		String cadenaIdES = "";
		String cadenaIdTS = "";

		if (idEstatusSolicitud != null && !idEstatusSolicitud.isEmpty())
			cadenaIdES = idEstatusSolicitud.toString().substring(1,
					idEstatusSolicitud.toString().length() - 1);

		if (idTipoSolicitud != null && !idTipoSolicitud.isEmpty())
			cadenaIdTS = idTipoSolicitud.toString().substring(1,
					idTipoSolicitud.toString().length() - 1);

		logger.info("cadenaIdES:" + cadenaIdES);
		logger.info("cadenaIdTS:" + cadenaIdTS);

		final StringBuffer query = new StringBuffer();

		query.append(" FROM Solicitud s");
		query.append(" WHERE 1=1 ");

		if (idFuncionarioDestinatario != null) {
			query.append(" AND s.destinatario.claveFuncionario = ");
			query.append(idFuncionarioDestinatario);
		}

		if (!cadenaIdES.trim().isEmpty()) {
			query.append(" AND s.estatus in ( ");
			query.append(cadenaIdES);
			query.append(" ) ");
		}

		if (!cadenaIdTS.trim().isEmpty()) {
			query.append(" AND s.tipoSolicitud in (");
			query.append(cadenaIdTS);
			query.append(" ) ");
		}

		if (idAreaDestino != null && idAreaDestino > 0) {
			query.append(" AND s.areaDestino = ");
			query.append(idAreaDestino);
		}
			
		query.append(" order by s.fechaCreacion asc");

		logger.debug("query :: " + query);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Solicitud> consultarSolicitudesPorTipoYEstatus(Long tipo,
			Long estatus, Long claveFuncionario,Long discriminanteId) {
		StringBuffer queryString = new StringBuffer();

		queryString.append("FROM Solicitud s")
				.append(" WHERE s.tipoSolicitud="+tipo);
		if (claveFuncionario != null)
			queryString.append(" AND s.funcionarioSolicitante="+claveFuncionario);
		queryString.append(" AND s.estatus="+estatus);
		
		if(discriminanteId != null){
			queryString.append(" AND s.numeroExpediente.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		}

		Query query = super.getSession().createQuery(queryString.toString());
		logger.debug("query :: " + queryString);
		return query.list();
	}

	@Override
	public List<Solicitud> consultarSolicitudesPorTipoYNoEstatus(
			Long tipoSolicitud, Long estatusSolicitud, Long claveFuncionario)
			throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();

		queryString.append("FROM Solicitud s")
				.append(" WHERE s.tipoSolicitud=").append(tipoSolicitud)
				.append(" AND s.funcionarioSolicitante=")
				.append(claveFuncionario).append(" AND s.estatus <> ")
				.append(estatusSolicitud);

		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO#consultarSolicitudesPorFolio
	 * (java.lang.String)
	 */
	@Override
	public List<Solicitud> consultarSolicitudesPorFolio(String folioSolicitud) {
		return getHibernateTemplate().find(
				"from Solicitud s where s.folioSolicitud = ?", folioSolicitud);
	}

	public String obtenerFolioDeSolicitud(Long idSolicitud)
			throws NSJPNegocioException {
		final StringBuffer query = new StringBuffer();
		query.append("SELECT s.folioSolicitud ");
		query.append("FROM Solicitud s ");
		query.append("WHERE s.confInstitucion.esInstalacionActual = true ");
		query.append("AND s.documentoId = ").append(idSolicitud);
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (String) hbq.uniqueResult();
	}

	/**
	 * M&acutee;todo que crea una solicitud con un DocumentoId ya existente
	 * @param Solicitud: Datos de la solicitud
	 * @return idSolicitud: Identificador de la solicitud
	 * @throws NSJPNegocioException
	 */
	public Long crearSolicitudConDocumentoExistente(Solicitud solicitud) throws NSJPNegocioException {
		
		final Funcionario solicitante = solicitud.getFuncionarioSolicitante() != null ? solicitud.getFuncionarioSolicitante() : new Funcionario();
		final Funcionario destinatario = solicitud.getDestinatario() != null ? solicitud.getDestinatario() : new Funcionario();
		final NumeroExpediente numeroExpediente = solicitud.getNumeroExpediente() != null ? solicitud.getNumeroExpediente() : new NumeroExpediente();
		final Valor tipoSolicitud = solicitud.getTipoSolicitud() != null ? solicitud.getTipoSolicitud() : new Valor();
		final Valor estatus = solicitud.getEstatus() != null ? solicitud.getEstatus() : new Valor();
		final Involucrado involucradoSolicitante = solicitud.getInvolucradoSolicitante() != null ? solicitud.getInvolucradoSolicitante() : new Involucrado();
		final StringBuffer query = new StringBuffer();
		
		query.append(" INSERT INTO Solicitud ");
		query.append(" (TipoSolicitud_val, Solicitud_id, NumeroExpediente_id, ");
		query.append(" cNumeroCasoAsociado, cMotivo, Estatus_val, dFechaLimite, ");
		query.append(" dFechaModificacion, dFechaCierre, iFuncionarioSolicitante, ");
		query.append(" iSolicitanteExterno, cNombreSolicitante, bEsUrgente, ");
		query.append(" InvolucradoSolicitante_id, iFuncionarioDestinatario, ");
		query.append(" cFolioSolicitud, cAsuntoSolicitud, cObservaciones, ");
		query.append(" iAreaOrigen, iAreaDestino) ");
		query.append(" values ( ");
		query.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
		query.append(" ) ");
		
		
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createSQLQuery(query.toString());
		
		hbq.setLong(0,  tipoSolicitud.getValorId());
		hbq.setLong(1,  solicitud.getDocumentoId());
		hbq.setLong(2,  numeroExpediente.getNumeroExpedienteId());
		hbq.setString(3,  solicitud.getNumeroCasoAsociado());
		hbq.setText(4,  solicitud.getMotivo());
		hbq.setLong(5,  estatus.getValorId());
		hbq.setDate(6,  solicitud.getFechaLimite());
		hbq.setDate(7,  solicitud.getFechaModificacion());
		hbq.setDate(8,  solicitud.getFechaCierre());
		hbq.setLong(9,  solicitante.getClaveFuncionario());
		hbq.setParameter(10,  solicitud.getSolicitanteExterno());
		hbq.setString(11, solicitud.getNombreSolicitante());
		hbq.setBoolean(12, solicitud.getEsUrgente());
		hbq.setParameter(13, involucradoSolicitante.getElementoId());
		hbq.setLong(14, destinatario.getClaveFuncionario());
		hbq.setString(15, solicitud.getFolioSolicitud());
		hbq.setString(16, solicitud.getAsuntoSolicitud());
		hbq.setText(17, solicitud.getObservaciones());
		hbq.setLong(18, solicitud.getAreaOrigen());
		hbq.setLong(19, solicitud.getAreaDestino());
		
		hbq.executeUpdate();
		
		return solicitud.getDocumentoId();
	}	
		

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitud> consultarSolicitudesConCriterios (
			Solicitud solicitud,
			List<Long> lstIdEstatusSolicitud, List<Long> lstIdTipoSolicitud,
			String tipoConsulta
			) throws NSJPNegocioException {

		String opcionConsulta = tipoConsulta != null ? tipoConsulta : Solicitud.TODAS;
		String cadenaIdES = "";
		String cadenaIdTS = "";

		if (lstIdEstatusSolicitud != null && !lstIdEstatusSolicitud.isEmpty())
			cadenaIdES = lstIdEstatusSolicitud.toString().substring(1,
					lstIdEstatusSolicitud.toString().length() - 1);

		if (lstIdTipoSolicitud != null && !lstIdTipoSolicitud.isEmpty())
			cadenaIdTS = lstIdTipoSolicitud.toString().substring(1,
					lstIdTipoSolicitud.toString().length() - 1);
		
		final StringBuffer query = new StringBuffer();

		query.append(" FROM Solicitud s");
		query.append(" WHERE 1=1 ");

		if (!lstIdEstatusSolicitud.isEmpty()) {
			query.append(" AND s.estatus in ( " );
			query.append(cadenaIdES);
			query.append(" ) ");
		}

		if (!lstIdTipoSolicitud.isEmpty()) {
			query.append(" AND s.tipoSolicitud in ( " );
			query.append(cadenaIdTS);
			query.append(" ) ");
		}
		if (solicitud != null){
			
			if (solicitud.getAreaOrigen() != null && solicitud.getAreaOrigen() > 0) {
				query.append(" AND s.areaOrigen = ");
				query.append(solicitud.getAreaOrigen());
			}
			
			NumeroExpediente numeroExpediente = solicitud.getNumeroExpediente();
			if ( numeroExpediente != null && numeroExpediente.getNumeroExpediente() != null && !numeroExpediente.getNumeroExpediente().equals("") ) {
				query.append(" AND s.numeroExpediente.numeroExpediente like '%");
				query.append(numeroExpediente.getNumeroExpediente());
				query.append("%' ");
			}
			
			if(opcionConsulta.equals(Solicitud.GENERADAS)) {
				Funcionario solicitante = solicitud.getFuncionarioSolicitante();
				if (solicitante != null) {
					query.append(" AND s.funcionarioSolicitante.claveFuncionario = ");
					query.append(solicitante.getClaveFuncionario());
				}
			} else if (opcionConsulta.equals(Solicitud.POR_ATENDER)){
				Funcionario destinatario = solicitud.getDestinatario();
				if (destinatario != null) {
					query.append(" AND s.destinatario.claveFuncionario = ");
					query.append(destinatario.getClaveFuncionario());
				}				
			} else if (opcionConsulta.equals(Solicitud.NUMEROCASOASOCIADO)){
					
				if (solicitud.getNumeroExpediente() != null){
					if(solicitud.getNumeroExpediente().getExpediente() != null){
						if(solicitud.getNumeroExpediente().getExpediente().getCaso() != null){
							query.append(" AND s.numeroExpediente.expediente.caso.numeroGeneralCaso = ");
							query.append("'" + solicitud.getNumeroExpediente().getExpediente().getCaso()+"'");			
						}
					}
				}							
			}
		}

		query.append(" order by s.fechaCreacion asc");

		logger.debug("query :: " + query);
//		Query hbq = super.getSession().createQuery(query.toString());
//		return hbq.list();
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
	}

}

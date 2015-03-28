/**
* Nombre del Programa : SolicitudPericialDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 3 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacio de metodos de acceso a datos para la entidad SolicitudPericialDAO
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
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudPericial;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


/**
 * Implementacio de metodos de acceso a datos para la entidad SolicitudPericialDAO.
 * @version 1.0
 * @author rgama
 *
 */
@Repository
public class SolicitudPericialDAOImpl extends
		GenericDaoHibernateImpl<SolicitudPericial, Long> implements
		SolicitudPericialDAO {
	
	@SuppressWarnings("unchecked")
	public List<SolicitudPericial> consultarSolicitudesPericiales(Long tipoSolicitud, Long estatus, String puestoUsuarioSolicitante) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT sp ")
					.append(" FROM SolicitudPericial sp ")
					.append(" WHERE sp.estatus.valorId = :estatus")
					.append(" AND sp.tipoSolicitud.valorId = :tipoSolicitud")
					.append(" AND sp.puestoUsuarioSolicitante LIKE :puestoUsuarioSolicitante");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("estatus", estatus);
		query.setParameter("tipoSolicitud", tipoSolicitud);
		query.setParameter("puestoUsuarioSolicitante", puestoUsuarioSolicitante);

		return query.list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Solicitud> consultarSolicitudesPericialPorTipoEstatusYUsuario(
			Long tipoSol, Long estatusSol, Long idUsuario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudPericial sp WHERE ")
					.append("sp.tipoSolicitud=").append(tipoSol)
					.append(" AND sp.estatus=").append(estatusSol)
					.append(" AND sp.destinatario=").append(idUsuario);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append("sp.folioSolicitud");
            	queryString.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryString);
        final Query query = super.getSession().createQuery(queryString.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<Solicitud> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<Solicitud> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO#consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes, mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud, mx.gob.segob.nsjp.comun.enums.funcionario.Puestos)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudPericial> consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(
			TiposSolicitudes tipo, EstatusSolicitud estado, Puestos puesto)
			throws NSJPNegocioException {
	    
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudPericial sp WHERE ")
					.append("sp.estatus.valorId=").append(estado.getValorId())
					.append(" AND sp.tipoSolicitud.valorId=").append(tipo.getValorId())
					.append(" AND sp.destinatario.puesto.valorId=").append(puesto.getValorId());

		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append("sp.numeroExpediente");
            	queryString.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryString);
        final Query query = super.getSession().createQuery(queryString.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<SolicitudPericial> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<SolicitudPericial> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO#consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes, mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud, mx.gob.segob.nsjp.comun.enums.funcionario.Puestos)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudPericial> consultarSolicitudesPericialesPorTipoEstatusAreaDestinatario(
			TiposSolicitudes tipo, EstatusSolicitud estado, Long area)
			throws NSJPNegocioException {
	    
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM SolicitudPericial sp WHERE ")
					.append("sp.estatus.valorId=").append(estado.getValorId())
					.append(" AND sp.tipoSolicitud.valorId=").append(tipo.getValorId())
					//.append(" AND sp.destinatario.area.jerarquiaOrganizacionalId=").append(area);
					.append(" AND sp.areaDestino=").append(area);

		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append(" sp.numeroExpediente");
            	queryString.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("queryString :: " + queryString);
        final Query query = super.getSession().createQuery(queryString.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<SolicitudPericial> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<SolicitudPericial> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO#consultarSolicitudesPericialesPorEstatusYDestinatario(mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud, mx.gob.segob.nsjp.model.Funcionario)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudPericial> consultarSolicitudesPericialesPorEstatusYDestinatario(
			EstatusSolicitud estatus, Funcionario destinatario)
			throws NSJPNegocioException {
		return getHibernateTemplate().find("from SolicitudPericial sp where sp.estatus.valorId = ? and "+
				" sp.destinatario.claveFuncionario = ?",
				estatus.getValorId(),destinatario.getClaveFuncionario());
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO#consultarDictamenIdDeSolicitudPericial(java.lang.Long)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Long consultarDictamenIdDeSolicitudPericial(Long solicitudPericialId)
			throws NSJPNegocioException {
		List dictamens= getHibernateTemplate().
		find("select d.documentoId from Dictamen d where d.solicitudPericial.documentoId = ? ",solicitudPericialId);
		if(dictamens.size()>0){
			return (Long)dictamens.get(0);
		}
		return null;
	}


	//FIXME ¿Y la funcionalidad?
	@Override
	public List<SolicitudPericial> actualizarStatusSolicitudPericial(
			SolicitudPericial solicitudPericial) {
		logger.debug("Ejecutando servicio para actualizar solicitud pericial");
		StringBuffer queryString = new StringBuffer();		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO#consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(mx.gob.segob.nsjp.comun.enums.funcionario.Puestos, mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud[], java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudPericial> consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(
			Puestos puesto, EstatusSolicitud[] estados, Long numeroExpedienteId) {
		StringBuffer query = new StringBuffer();
				query.append("from SolicitudPericial sp where sp.funcionarioSolicitante.puesto.valorId = ? ");
		if(estados != null){
			for(EstatusSolicitud estado:estados){
				query.append(" and sp.estatus.valorId =  " + estado.getValorId() + " ");
			}
		}
		query.append(" and sp.numeroExpediente.numeroExpedienteId = ?");
		
		
		return getHibernateTemplate().find(query.toString(),puesto.getValorId(),numeroExpedienteId);
	}


}

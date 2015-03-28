/**
*
* Nombre del Programa : InvolucradoDAOImpl.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 05/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementación para el DAO de la entidad Narrativa                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author CesarAgustin
 * @version 1.0
 */
@Repository
public class InvolucradoDAOImpl extends GenericDaoHibernateImpl<Involucrado, Long>
		implements InvolucradoDAO {
	
	@Autowired
    private RegistroBitacoraDAO  registroBitacoraDao;

	@SuppressWarnings("unchecked")
	public List<Involucrado> obtenerInvolucradosAll() {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM Involucrado ");	 			
		Query query = super.getSession().createQuery(queryStr.toString());	
		return query.list();	
	}

	
	public Expediente obtenerExpediente(Integer expedienteId) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT e ")
				.append("FROM Expediente e ")
				.append("WHERE e.expedienteId=:expedienteId");		
		Query query = super.getSession().createQuery(queryStr.toString());	
		query.setParameter("expedienteId", expedienteId);
		return (Expediente)query.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarInvolucradosByExpediente(
			Long expedienteId) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT i ")
					.append( "FROM Involucrado i ")
					.append(" LEFT JOIN i.nombreDemograficos as nd ")
					.append(" WHERE i.expediente.expedienteId=").append(expedienteId)
					.append(" and i.esActivo = true");

		final PaginacionDTO pag = PaginacionThreadHolder.get();

		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				queryString.append(" order by ");
				queryString.append(" nd.nombre");
				queryString.append(" ").append(pag.getDirOrd());
			}
			if (pag.getCampoOrd().equals("2")) {
				queryString.append(" order by ");
				queryString.append(" i.calidad.tipoCalidad.valor");
				queryString.append(" ").append(pag.getDirOrd());
			}
		}
		return super.ejecutarQueryPaginado(queryString, pag);   
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Involucrado> obtenerInvolucradosPorExpedienteYCalidades(Long expedienteId, Calidades[] calidades) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Involucrado i ")
					.append(" WHERE i.expediente = ").append(expedienteId);
					//.append(" AND i.esActivo = true");
		if(calidades!= null && calidades.length>0){
			queryString.append(" and i.calidad.tipoCalidad.valorId in ( ");
			for(int iCal = 0; iCal<calidades.length;iCal++)
				queryString.append(iCal>0?","+calidades[iCal].getValorId():calidades[iCal].getValorId());
			queryString.append(")");
		}
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarExpedientesByAlias(
			String aliasInvolucrado) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT i ")
					.append("FROM Involucrado i, AliasInvolucrado a ")
					.append("WHERE a.alias=:aliasInvolucrado ")
					.append("AND a.involucrado.elementoId=i.elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("aliasInvolucrado", aliasInvolucrado);
				
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarExpedientesByAliasLike(
			String aliasInvolucrado) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT i ")
					.append("FROM Involucrado i, AliasInvolucrado a ")
					.append("WHERE a.alias like :aliasInvolucrado ")
					.append("AND a.involucrado.elementoId=i.elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("aliasInvolucrado", aliasInvolucrado);
				
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarExpedientesByNombre(
			String nombre, String apellidoPat, String apellidoMat) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT i ")
					.append("FROM Involucrado i LEFT JOIN i.nombreDemograficos nd ")
					.append("WHERE ");					
		if (!apellidoPat.equals("%%") && !apellidoMat.equals("%%") && !nombre.equals("%%")) {
			queryString.append("nd.nombre like '").append(nombre).append("' AND ")
						.append("nd.apellidoPaterno like '").append(apellidoPat).append("' AND ")
						.append("nd.apellidoMaterno like '").append(apellidoMat).append("'");
		} else if (!apellidoPat.equals("%%") && !nombre.equals("%%")) {
			queryString.append("nd.nombre like '").append(nombre).append("' AND ")
						.append("nd.apellidoPaterno like '").append(apellidoPat).append("'");
		} else if (!apellidoMat.equals("%%") && !nombre.equals("%%")) {
			queryString.append("nd.nombre like '").append(nombre).append("' AND ")
						.append("nd.apellidoMaterno like '").append(apellidoMat).append("'");
		} else if (!apellidoPat.equals("%%") && !apellidoMat.equals("%%")) {
			queryString.append("nd.apellidoPaterno like '").append(apellidoPat).append("' AND ")
						.append("nd.apellidoMaterno like '").append(apellidoMat).append("'");
		}else if (!nombre.equals("%%")) {
			queryString.append("nd.nombre like '").append(nombre).append("'");
		} else if (!apellidoMat.equals("%%")) {
			queryString.append("nd.apellidoMaterno like '").append(apellidoMat).append("'");
		} else if (!apellidoPat.equals("%%")) {
			queryString.append("nd.apellidoPaterno like '").append(apellidoPat).append("'");
		}
								
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Involucrado> obtenerInvByIdExpAndCalidad(Long idExpediente,
			Long calidadId, Boolean esDetenido) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" SELECT i ")
					.append(" FROM Involucrado i ")
					.append(" WHERE i.expediente.expedienteId="+idExpediente)
					.append(" AND i.calidad.tipoCalidad.valorId="+calidadId)
					.append(" and i.esActivo = true");

		if(esDetenido!=null){
			queryString.append(" AND i.esDetenido="+esDetenido);
		}
		Query query = super.getSession().createQuery(queryString.toString());
		final List<Involucrado> resp =  query.list();
		logger.debug("resp.size() :: "+resp.size());
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Involucrado> obtenerInvolucradosByAudiencia(Long audienciaId, Long calidadId) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT ia.involucrado ");
		queryString.append("FROM InvolucradoAudiencia ia ");
//		queryString.append("WHERE ia.id.audienciaId=:audienciaId");
		queryString.append("WHERE ia.id.audienciaId="+audienciaId);
		if(calidadId != -1){
			queryString.append(" AND ia.involucrado.calidad.tipoCalidad.valorId=:calidadId");
		}
		Query query = super.getSession().createQuery(queryString.toString());
//		query.setParameter("audienciaId", audienciaId);
		if(calidadId != -1){
			query.setParameter("calidadId", calidadId);
		}
		return query.list();
	}

	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO#obtenerInvolucradosByCasoYCalidades(java.lang.String, mx.gob.segob.nsjp.comun.enums.calidad.Calidades[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Involucrado> obtenerInvolucradosByCasoYCalidades(
			String numeroCaso, Calidades[] calidades) {
		StringBuilder query = new StringBuilder();
		
		query.append("from Involucrado inv where inv.expediente.caso.numeroGeneralCaso = ? " +
				" and inv.calidad.tipoCalidad.valorId in ( ");
		for(int iCal = 0; iCal<calidades.length;iCal++){
			query.append(iCal>0?","+calidades[iCal].getValorId():calidades[iCal].getValorId());
		}
		query.append(")");
		
		return getHibernateTemplate().find(query.toString(),numeroCaso);
	}

	@SuppressWarnings("unchecked")
	public List<Involucrado> consultarInvolucradosByIds(
			List<Long> idInvolucrados) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT i ")
					.append("FROM Involucrado i ");					
		String lista = idInvolucrados.toString();

		if(!lista.trim().isEmpty() && lista.indexOf('[')!= -1){
			lista= lista.substring(1, lista.length()-1);
			logger.info("Lista:"+ lista);
			queryString.append(" WHERE i.elementoId in (")
			.append( lista + ")" );
		}
		logger.info("Query:" + queryString);
		Query query = super.getSession().createQuery(queryString.toString());
//		query.setParameter("expedienteId", expedienteId);
		return query.list();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerExpedientesPorCondicionDetencionInvYMes(
			Date fechaInicio, Date fechaFin, Boolean condicionDet) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH(i.expediente.fechaCreacion), COUNT(*) FROM Involucrado i ")
					.append("WHERE i.esDetenido=").append(condicionDet)
					.append(" AND CONVERT(VARCHAR, i.expediente.fechaCreacion ,112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaInicio)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFin)).append("GROUP BY MONTH(i.expediente.fechaCreacion)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}


	@Override
	public Involucrado consultarInvolucradoPorFolioElemento(String folioElemento) {
		final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("SELECT i ")
				.append("FROM Involucrado i ")
				.append("WHERE i.folioElemento = '"+folioElemento+"'");

		Query query = super.getSession().createQuery(hqlQuery.toString());
		return (Involucrado)query.uniqueResult();
	}

	@Override
	public Involucrado obtenerInvolucradoByFolioElemento(String folioInvolucrado) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Involucrado i WHERE ")
					.append("i.folioElemento='").append(folioInvolucrado)
					.append("'");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Involucrado)query.uniqueResult();
//		return (Involucrado) getHibernateTemplate().find(queryString.toString(), folioInvolucrado);
	}
	
	
}

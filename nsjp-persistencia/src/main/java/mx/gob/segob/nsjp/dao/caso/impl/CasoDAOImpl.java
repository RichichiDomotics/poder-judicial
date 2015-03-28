/**
 *
 * Nombre del Programa : DomicilioDAOImpl.java                                    
 * Autor                            : cesarAgustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para el DAO de la entidad Caso                      
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

package mx.gob.segob.nsjp.dao.caso.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Vehiculo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author cesarAgustin
 * @version 1.0
 * 
 */

@Repository
public class CasoDAOImpl extends GenericDaoHibernateImpl<Caso, Long> implements
		CasoDAO {

	@Override
	public String recuperarUltimoNumero(String monogramaInstitucion) {
		final StringBuffer queryStr = new StringBuffer();
		logger.debug(monogramaInstitucion+" monograma de la institucion");
		queryStr.append("SELECT c.numeroGeneralCaso ");
		queryStr.append("FROM Caso c where c.casoId = ( ");
		queryStr.append(" SELECT MAX(obj.casoId) FROM Caso obj ");
		/*if( monogramaInstitucion!=null && !monogramaInstitucion.trim().isEmpty()){
			queryStr.append(" WHERE obj.numeroGeneralCaso LIKE '____").
				append(monogramaInstitucion).
				append("%' ");
		}*/
		queryStr.append(" )");
		
		Query qry = super.getSession().createQuery(queryStr.toString());
		String valorRegreso  =  (String) qry.uniqueResult();
		
		//@deprecated  URG-006 Versión que incluye el área del usuario y ordena por el numero de caso
//		queryStr.append(" SELECT obj.casoId FROM Caso obj ");
//		if( monogramaInstitucion!=null && !monogramaInstitucion.trim().isEmpty() && unidad!= null && !unidad.trim().isEmpty()){
//			queryStr.append(" WHERE obj.numeroGeneralCaso LIKE '____").
//				append(monogramaInstitucion).
//				append("%____").
//				append(unidad).
//				append("%' ORDER BY  obj.numeroGeneralCaso  DESC");
//		}
//		logger.info(queryStr.toString());
//		Query qry = super.getSession().createQuery(queryStr.toString());
//		qry.setMaxResults(1);  // Se configura para obtener solo el primer resultado en la lista.
//		
//		List<Object> lista = qry.list();
//		String valorRegreso = "";
//		if( !lista.isEmpty()){
//			String valorId = lista.get(0) != null ? lista.get(0) .toString() : "";
//					
//			final StringBuffer queryStrCompleto = new StringBuffer();
//			queryStrCompleto.append("SELECT c.numeroGeneralCaso ");
//			queryStrCompleto.append("FROM Caso c where c.casoId = ").
//				append(valorId);
//			logger.info(queryStrCompleto.toString());
//			qry = super.getSession().createQuery(queryStrCompleto.toString());
//			valorRegreso  =  (String) qry.uniqueResult();
//		}
		return valorRegreso;
	}
	

	@SuppressWarnings("unchecked")
	public List<Caso> consultarCasosPorNumero(String numeroCaso) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT c ").append("FROM Caso c ")
				.append("WHERE c.numeroGeneralCaso like '%"+numeroCaso+"%'");
		Query query = super.getSession().createQuery(queryStr.toString());

		return query.list();
	}

	@Override
	public List<Caso> consultarCasos(Long idUsuario) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select  new Caso(v.casoId, v.numeroGeneralCaso, v.estatus) ");
		queryStr.append(" from Caso v "); // TODO VAP agregar la condicion del usr
		queryStr.append(" order by v.numeroGeneralCaso desc");
		
		super.getHibernateTemplate().setMaxResults(100);
		@SuppressWarnings("unchecked")
		List<Caso> resp = getHibernateTemplate()
				.find(queryStr.toString());
		getHibernateTemplate().setMaxResults(0);
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<Caso> consultarCasosPorFecha(Date fechaCreacionInicio,
			Date fechaCreacionFin) {
		SimpleDateFormat formato= new SimpleDateFormat("yyyyMMdd");
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT c ")
					.append("FROM Caso c ")
					.append("WHERE CONVERT (nvarchar, c.fechaApertura, 112) ")
					.append("BETWEEN :fechaCreacionInicio AND :fechaCreacionFin");	
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("fechaCreacionInicio", formato.format(fechaCreacionInicio));
		query.setParameter("fechaCreacionFin", formato.format(fechaCreacionFin));
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Caso> consultarCasosPorDelito(String delito) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT e.caso ")
					.append("FROM Expediente e LEFT JOIN e.delitos ds ")
					.append("WHERE  ds.catDelito.nombre like '" + delito + "'");
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		logger.debug("pag :: " + pag);
		return super.ejecutarQueryPaginado(queryString, pag); 
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.caso.CasoDAO#consultarCasosConEventosHistoricos(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> consultarCasosConEventosHistoricos(Long usuarioId)
			throws NSJPNegocioException {
		
		//TODO: VAP agregar la constante de tiempo de consultas históricas hacia atrás
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.setTime(new Date());
		fechaInicio.add(Calendar.DATE, ConstantesGenerales.DIAS_ATRAS_CONSULTAS_HISTORICAS*-1);
		
		String query = "Select caso  from Caso caso where exists (" +
				"select audiencia.audienciaId from Audiencia audiencia where " +
				"audiencia.numeroExpediente.expediente.caso = caso and " +
				"audiencia.fechaAudiencia between ? and ? "
				+
				") ";
		
		return getHibernateTemplate().find(query,fechaInicio.getTime(),new Date());
		
	}

	@Override
	public Caso consultarCasoPorExpediente(Long expedienteId) {
		final StringBuffer queryString = new StringBuffer();
//		queryString.append("FROM Caso c WHERE c.expedientes="+expedienteId);
//		queryString.append("FROM Caso c WHERE c.casoId in (");
		queryString.append(" SELECT e.caso FROM Expediente e");
		queryString.append(" WHERE e.expedienteId="+expedienteId);
//		queryString.append(" )");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Caso) query.uniqueResult();
	}

	@Override
	public Caso obtenerCasoByNumeroGeneral(String numeroCasoAsociado) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Caso c WHERE c.numeroGeneralCaso = '")
					.append(numeroCasoAsociado).append("'");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Caso) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Caso> consultarReincidenciasDeElementos(Elemento elemento){
		final StringBuffer queryString = new StringBuffer();
		Vehiculo vehiculo =  null;
		Involucrado involucrado =  null;
		Query query = null;
		
		if(elemento instanceof Vehiculo){
			vehiculo = (Vehiculo)elemento;
			logger.debug("DAO getNoSerie" + vehiculo.getNoSerie());
    		logger.debug("DAO getPlaca" + vehiculo.getPlaca());
			
			queryString.append("SELECT DISTINCT v.expediente.caso FROM ").append(elemento.getClass().getSimpleName()).append(" v WHERE 1 = 1");
			if(vehiculo.getNoSerie()!= null && !vehiculo.getNoSerie().isEmpty())
				queryString.append(" AND v.noSerie like :noSerie ");
			if(vehiculo.getPlaca() != null && !vehiculo.getPlaca().isEmpty())
				queryString.append(" AND v.placa like :noPlaca ");
			queryString.append(" AND  v.expediente.caso.casoId NOT IN ( SELECT rr.caso.casoId FROM RelacionReincidencia rr ");
			queryString.append(" WHERE rr.elemento.elementoId = "+vehiculo.getElementoId().longValue());
			queryString.append(" )");
			
			query = super.getSession().createQuery(queryString.toString());
			if(vehiculo.getNoSerie()!= null && !vehiculo.getNoSerie().isEmpty())
				query.setParameter("noSerie", vehiculo.getNoSerie());
			if(vehiculo.getPlaca() != null && !vehiculo.getPlaca().isEmpty())
				query.setParameter("noPlaca", vehiculo.getPlaca());
		}   
		
		if(elemento instanceof Involucrado){
			involucrado = (Involucrado)elemento;
			NombreDemografico loNombre = involucrado.getNombreDemograficos().iterator().hasNext()?involucrado.getNombreDemograficos().iterator().next():null;
					
			queryString.append("SELECT DISTINCT v.expediente.caso FROM ").append(elemento.getClass().getSimpleName()).append(" v LEFT JOIN v.nombreDemograficos nd");
			queryString.append(" WHERE 1 = 1");
			if(loNombre.getNombre()!= null && !loNombre.getNombre().isEmpty())
				queryString.append(" AND nd.nombre like :nombre ");
			if(loNombre.getApellidoPaterno()!= null && !loNombre.getApellidoPaterno().isEmpty())
				queryString.append(" AND nd.apellidoPaterno like :apellidoPaterno ");
			if(loNombre.getApellidoMaterno()!= null && !loNombre.getApellidoMaterno().isEmpty())
				queryString.append(" AND nd.apellidoMaterno like :apellidoMaterno ");
			
			if(involucrado!=null && involucrado.getElementoId()!=null){
				queryString.append(" AND v.expediente.caso.casoId NOT IN ( SELECT rr.caso.casoId FROM RelacionReincidencia rr ");
				queryString.append(" WHERE rr.elemento.elementoId = "+involucrado.getElementoId());
				queryString.append(" )");
			}	
			
			/*if(loNombre.getNombre()!= null && !loNombre.getNombre().isEmpty() ||
			   loNombre.getApellidoPaterno()!= null && !loNombre.getApellidoPaterno().isEmpty() ||
			   loNombre.getNombre()!= null && !loNombre.getNombre().isEmpty())
			{
				queryString.append(" AND  v.expediente.caso.casoId NOT IN ( ");
				queryString.append(" SELECT rr.caso.casoId FROM RelacionReincidencia rr, NombreDemografico nd ");
				queryString.append(" WHERE rr.elemento.elementoId = nd.persona.elementoId ");
				if(loNombre.getNombre()!= null && !loNombre.getNombre().isEmpty())
					queryString.append(" AND nd.nombre like :nombre ");
				if(loNombre.getApellidoPaterno()!= null && !loNombre.getApellidoPaterno().isEmpty())
					queryString.append(" AND nd.apellidoPaterno like :apellidoPaterno ");
				if(loNombre.getApellidoMaterno()!= null && !loNombre.getApellidoMaterno().isEmpty())
					queryString.append(" AND nd.apellidoMaterno like :apellidoMaterno ");			
				queryString.append(" )");
			}*/
			query = super.getSession().createQuery(queryString.toString());
			if(loNombre.getNombre()!= null && !loNombre.getNombre().isEmpty())
				query.setParameter("nombre", loNombre.getNombre());
			if(loNombre.getApellidoPaterno()!= null && !loNombre.getApellidoPaterno().isEmpty())
				query.setParameter("apellidoPaterno", loNombre.getApellidoPaterno());
			if(loNombre.getApellidoMaterno()!= null && !loNombre.getApellidoMaterno().isEmpty())
				query.setParameter("apellidoMaterno", loNombre.getApellidoMaterno());
		} 
		return query.list();
	}

	@Override
	public Long consultarIdXNumeroCaso(String numeroGeneralCaso) {
		final StringBuffer queryString = new StringBuffer();
		
		queryString.append("SELECT c.casoId FROM Caso c")
			.append(" WHERE c.numeroGeneralCaso ='"+numeroGeneralCaso+"'");

		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();
	}
	
}

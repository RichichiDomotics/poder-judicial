/**
 *
 * Nombre del Programa : ExpedienteDAOImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 30/03/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación para el DAO de la entidad Expediente                      
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

package mx.gob.segob.nsjp.dao.expediente.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.QueryUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 * 
 */

@Repository
public class ExpedienteDAOImpl extends
		GenericDaoHibernateImpl<Expediente, Long> implements ExpedienteDAO {

	@Autowired
	private InvolucradoDAO involucradoDao;

	@Autowired
	private DelitoPersonaDAO delitoPersonaDao;

	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDao;

	@Autowired
	private UsuarioDAO usuarioDao;

	@SuppressWarnings("unchecked")
	public List<Expediente> buscarExpedientes(String numeroExpediente,
			Long areaId,Long discriminanteId) {
		if (logger.isDebugEnabled()) {
			logger.debug("numeroExpediente :: [" + numeroExpediente
					+ "] y areaId :: " + areaId);
		}
		logger.info("numeroExpediente :: [" + numeroExpediente
				+ "] y areaId :: " + areaId);
		
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select e FROM Expediente e left join e.numeroExpedientes n");
		queryStr.append(" WHERE n.numeroExpediente like '");
		queryStr.append(numeroExpediente);
		if(areaId!= null && areaId > 0)
			queryStr.append("' and n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = " + areaId);
		else {
			queryStr.append("'");
		}
		if(discriminanteId != null && !discriminanteId.equals(0L)){
			queryStr.append(" and n.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		}
		
		
		
		Query query = super.getSession().createQuery(queryStr.toString());
//		query.setParameter("numeroExpediente", numeroExpediente);
		logger.info("QUERY:"+ query.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> buscarNumeroExpedientes(Long expedienteID,
			Long areaId) {
		if (logger.isDebugEnabled()) {
			logger.debug("expedienteID :: [" + expedienteID
					+ "] y areaId :: " + areaId);
		}
		logger.info("expedienteID :: [" + expedienteID
				+ "] y areaId :: " + areaId);
		
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select n FROM NumeroExpediente n ");
		queryStr.append(" WHERE n.expediente.expedienteId in ( ");
		queryStr.append(expedienteID );
		queryStr.append(" ) and n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
		queryStr.append(areaId);
		Query query = super.getSession().createQuery(queryStr.toString());
		logger.info("QUERY:"+ query.toString());
		return query.list();
	}
	
	@Override
	public String obtenerUltimoNumero(Long area) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select e.numeroExpediente ");
		queryStr.append(" from NumeroExpediente e where e.numeroExpedienteId =");
		queryStr.append(" (select MAX(obj.numeroExpedienteId) from NumeroExpediente obj where obj.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
		//queryStr.append(" (select MAX(obj.numeroExpedienteId) from NumeroExpediente obj ");
		queryStr.append(area);
		queryStr.append(" )");
		queryStr.append(" and e.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
		queryStr.append(area);
		logger.debug("queryStr :: " + queryStr);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (String) qry.uniqueResult();
	}

	@Override
	public Expediente buscarUltimoNumeroPorExpedienteIdAreaId(Long expedienteId,
			Long areaId) {
		logger.debug("expedienteId :: [" + expedienteId
				+ "] y areaId :: " + areaId);
		
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Expediente(e.expedienteId, n.numeroExpediente, n.numeroExpedienteId) ");
		queryStr.append(" FROM Expediente e left join e.numeroExpedientes n");
		queryStr.append(" WHERE e.expedienteId = ");
		queryStr.append(" :expedienteId ");
		
		queryStr.append(" AND n.numeroExpedienteId = ");
		queryStr.append(" (select MAX(obj.numeroExpedienteId) from NumeroExpediente obj where 1=1");
		if(areaId != null){
			queryStr.append(" AND obj.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
			queryStr.append(" :areaId ");			
		}
		queryStr.append(" AND obj.expediente.expedienteId =  ");
		queryStr.append(" :expedienteId ");
		queryStr.append(" )");
		if(areaId != null)
			queryStr.append(" AND n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = :areaId");
		
		Query query = super.getSession().createQuery(queryStr.toString());
		query.setParameter("expedienteId", expedienteId);
		if(areaId != null)
			query.setLong("areaId", areaId);
		return (Expediente) query.uniqueResult();
	}
	
	@Override
	public List<Expediente> consultarExpedientesPorIdCaso(Long idCaso,
			Long areaId) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Expediente(v.expedienteId, n.numeroExpediente, n.numeroExpedienteId)");
		queryStr.append(" from Expediente v ");
		queryStr.append(" left join v.numeroExpedientes n");
		queryStr.append(" where v.caso.casoId = ");
		queryStr.append(idCaso);
		if (areaId != null) {
			queryStr.append(" and n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
			queryStr.append(areaId);
		}
		queryStr.append(" order by n.numeroExpediente");
		@SuppressWarnings("unchecked")
		List<Expediente> resp = super.getHibernateTemplate().find(
				queryStr.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}
	
	@Override
	public List<Expediente> consultarExpedientesPorId(Long idCaso,
			Long areaId) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append(" from Expediente v ");
		queryStr.append(" where v.caso.casoId = ");
		queryStr.append(idCaso);
		if (areaId != null) {
			queryStr.append(" and n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
			queryStr.append(areaId);
		}
		@SuppressWarnings("unchecked")
		List<Expediente> resp = super.getHibernateTemplate().find(
				queryStr.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<Long> consultarExpedientesPorEvidencia(String nombreEvidencia,
			List<String> palabrasClave) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT ").append(nombreEvidencia)
				.append(".expediente.expedienteId FROM ")
				.append(nombreEvidencia).append(" ").append(nombreEvidencia);
		// .append(" WHERE (");

		List<Field> atrString = QueryUtils.obtenerParametrosQuery(
				nombreEvidencia, String.class);
		List<Field> atrValor = QueryUtils.obtenerParametrosQuery(
				nombreEvidencia, Valor.class);

		for (Field field : atrValor) {

			queryString.append(" left outer join ").append(nombreEvidencia)
					.append(".");
			queryString.append(field.getName()).append(" as ");
			queryString.append(field.getName());
		}

		queryString.append(" where ");

		boolean isFirstCondition = true;
		for (String palabra : palabrasClave) {
			if (StringUtils.isNotBlank(palabra)) {
				if (isFirstCondition) {
					isFirstCondition = false;
				} else {
					queryString.append(" OR ");
				}
				queryString.append(nombreEvidencia + ".descripcion like '%");
				queryString.append(palabra).append("%'");
			}
		}

		for (String palabra : palabrasClave) {
			if (StringUtils.isNotBlank(palabra)) {
				for (Field field : atrValor) {
					queryString.append(" OR ");
					queryString.append(field.getName());
					queryString.append(".valor like '%").append(palabra)
							.append("%'");
				}
			}
		}

		for (String palabra : palabrasClave) {
			if (StringUtils.isNotBlank(palabra)) {
				for (Field field : atrString) {
					queryString.append(" OR ");
					queryString.append(nombreEvidencia + "." + field.getName()
							+ " like '%");
					queryString.append(palabra).append("%'");
				}
			}
		}

		logger.debug("queryString :: " + queryString);

		Query query = super.getSession().createQuery(queryString.toString());

		return query.list();
	}

	@Override
	public String obtenerUltimoNumTipoExp(String nomExpediente) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT e.numeroExpediente ").append("FROM ")
				.append(nomExpediente).append(" e ")
				.append("WHERE e.expedienteId = (SELECT MAX(ex.expedienteId) ")
				.append("FROM ").append(nomExpediente).append(" ex)");
		Query query = super.getSession().createQuery(queryString.toString());
		return (String) query.uniqueResult();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Expediente> consultarExpedientesPorActividadActual(
			Long actividad, Long estatusExp) {
		final StringBuffer queryString = new StringBuffer();

		queryString.append("FROM Expediente e LEFT JOIN e.actividads da ")
				.append("WHERE  da.actividadId like :actividad");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("actividad", actividad);
		List resp = query.list();

		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarExpedientesActividadAreaAnio(
			Long areaId, Long actividadId, Long anio,Long discriminanteId,Long cveFuncionario) {
		
		logger.debug("DISCRIMINANTE ID::::::::::::::::::::::::::::::::: " + discriminanteId);
		
		final StringBuffer queryString = new StringBuffer();
		queryString
				.append("SELECT ne FROM Expediente e ")
				.append("LEFT JOIN e.actividads ac LEFT JOIN e.numeroExpedientes ne WHERE ");
				
				if (actividadId.equals(Actividades.RECIBIR_CANALIZACION_JAR.getValorId())) {
					queryString.append("ne.jerarquiaOrganizacional IN (")
					.append(areaId).append(", ").append(Areas.UNIDAD_INVESTIGACION.ordinal())
					.append(")");
				} else {
					queryString.append("ne.jerarquiaOrganizacional=")
					.append(areaId);
				}
							
				queryString.append(" AND ").append("ac.tipoActividad=")
				.append(actividadId).append(" AND ")
				.append("YEAR (ac.fechaCreacion)=").append(anio);
				if(cveFuncionario != null){
					queryString.append(" AND ").append("ac.funcionario.claveFuncionario=").append(cveFuncionario);
				}
				if(discriminanteId != null && discriminanteId > 0){
					queryString.append(" AND e.discriminante.catDiscriminanteId=").append(discriminanteId);
				}
				
		final PaginacionDTO pag = PaginacionThreadHolder.get();

		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				queryString.append(" order by ");
				queryString.append("e.caso.numeroGeneralCaso");
				queryString.append(" ").append(pag.getDirOrd());
			}
			if (pag.getCampoOrd().equals("2")) {
				queryString.append(" order by ");
				queryString.append("e.fechaCreacion");
				queryString.append(" ").append(pag.getDirOrd());
			}
			if (pag.getCampoOrd().equals("6")) {
				queryString.append(" order by ");
				queryString.append("ne.estatus.valor");
				queryString.append(" ").append(pag.getDirOrd());
			}
		}
		return super.ejecutarQueryPaginado(queryString, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarExpedientesPorEtapa(
			EtapasExpediente etapa, Long usuarioId, Long areaId) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM NumeroExpediente ne WHERE ")
				.append("ne.funcionario=").append(usuarioId)
				.append(" AND ne.etapa=").append(etapa.getValorId());
		if (areaId!=null) {
			queryStr.append(" AND ne.jerarquiaOrganizacional=")
					.append(areaId);
		}	
		final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if(pag!=null && pag.getCampoOrd() != null){
	    	queryStr.append(" ORDER BY ");
	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
	    	switch(orden){
		    	case 2002: //Numero General de Caso
		    		queryStr.append(" ne.expediente.caso.numeroGeneralCaso");
		    		break;
		    	case 2003: // Expediente
		    		queryStr.append(" ne.expediente.numeroExpediente");
		    		break;
		    	case 2009: // Imputado FIXME DAJV poner la ruta de el imputado
		    		queryStr.append(" ne");
		    		break;
		    	case 2005: // Detenido FIXME DAJV poner la ruta de el detenido
		    		queryStr.append(" ne");
		    		break;
		    	case 2038: // fecha hora limite de atencion Detenido FIXME DAJV poner la ruta de fecha limite atencion
		    		queryStr.append(" ne");
		    		break;
		    	case 2039: // fecha hora de designacion Detenido FIXME DAJV poner la ruta de fecha de designacion
		    		queryStr.append(" ne");
		    		break;
		    	case 2040: // Institucion
		    		queryStr.append(" ne");
		    		break;
		    	case 2017: // TipoAudiencia
		    		queryStr.append(" ne");
		    		break;
		    	case 2029: // Sala
		    		queryStr.append(" ne");
		    		break;
		    	case 2018: // Fecha audiencia
		    		queryStr.append(" ne");
		    		break;
		    		//FIXME DAJV poner la ruta de ordenamientos
		    	default:
		    		queryStr.append(" ne");
		    		break;
	    	}
	    	queryStr.append(" "+pag.getDirOrd());
	    }
	    
	    return super.ejecutarQueryPaginado(queryStr, pag);
//		Query query = super.getSession().createQuery(queryStr.toString());
//		return query.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expediente consultarDetalleExpediente(Expediente expediente,
			Usuario usuario) {
		Expediente detalleExpediente = new Expediente();
		Expediente consultado = read(expediente.getExpedienteId());
		detalleExpediente.setExpedienteId(consultado.getExpedienteId());
		detalleExpediente.setCaso(consultado.getCaso());
		Usuario usuarioCompleto = usuarioDao.read(usuario.getUsuarioId());
		NumeroExpediente numeroExpediente = numeroExpedienteDao
				.obtenerNumeroExpediente(consultado.getExpedienteId(),
						usuarioCompleto.getFuncionario().getArea()
								.getJerarquiaOrganizacionalId());
		detalleExpediente.setNumeroExpediente(numeroExpediente
				.getNumeroExpediente());
		// consultamos los involucrados de este expediente
		List<Involucrado> involucrados = involucradoDao
				.consultarInvolucradosByExpediente(expediente.getExpedienteId());
		Involucrado imputado = null;
		for (Involucrado involucrado : involucrados) {
			long valorId = involucrado.getCalidad().getTipoCalidad()
					.getValorId();
			/*
			 * verificamos si alguno de los involucrados es un probable
			 * responsable persona u organizacion.
			 */
			if (valorId == Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()
					|| valorId == Calidades.PROBABLE_RESPONSABLE_ORGANIZACION
							.getValorId()) {
				imputado = involucrado;
				break;
			}
		}
		if (imputado == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No pudimos encontrar el imputado para el "
						+ "expediente = " + expediente);
				logger.debug("Los involucrados del expediente son: "
						+ involucrados.size());
				for (Involucrado involucrado : involucrados) {
					logger.debug("involucrado.getElementoId(): "
							+ involucrado.getElementoId());
				}
			}
		}
		detalleExpediente.getElementos().add(imputado);
		// Consultamos los delitos del imputado
		if (imputado != null) {
			List<DelitoPersona> delitosImputado = delitoPersonaDao
					.consultarDelitosPorImputado(imputado.getElementoId(),
							expediente.getExpedienteId());
			Set<Delito> delitosExpediente = detalleExpediente.getDelitos();
			for (DelitoPersona delitoPersona : delitosImputado) {
				delitosExpediente.add(delitoPersona.getDelito());
			}
		}
		return detalleExpediente;
	}

	@Override
	public Long consultarExpedientePorNumeroExpediente(String numExp) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT e.numeroExpedienteId ")
				.append(" FROM NumeroExpediente e  ")
				.append(" WHERE e.numeroExpediente like '%").append(numExp)
				.append("%'");
		Query query = super.getSession().createQuery(queryString.toString());
//		return (Long) query.uniqueResult();
		if(query.list().isEmpty())
			return null;
		return (Long) query.list().get(0);
		

	}

	@Override
	public Long consultarExpedienteIdPorNumeroExpediente(String numExp) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT e.expediente.expedienteId ")
				.append(" FROM NumeroExpediente e  ")
				.append(" WHERE e.numeroExpediente like '%").append(numExp)
				.append("%'");
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();

	}
	
	@Override
	public List<Expediente> buscarExpedientesRemitentes(Long areaReceptora, Long areaOrigen) {
		// TODO Falta definición de la bitácora de expedientes
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expediente> consultarExpedientesPorIdCasoConfInstitucion(Long idCaso,
			Long idConfInstitucion) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Expediente(v.expedienteId, n.numeroExpediente, n.numeroExpedienteId)");
		queryStr.append(" from Expediente v ");
		queryStr.append(" left join v.numeroExpedientes n");
		queryStr.append(" where v.caso.casoId = ");
		queryStr.append(idCaso);
		if (idConfInstitucion != null) {
			queryStr.append(" and v.pertenceConfInst.confInstitucionId = ");
			queryStr.append(idConfInstitucion);
		}
		Query query = super.getSession().createQuery(queryStr.toString());
		return query.list();
	}
		
	@SuppressWarnings("unchecked")
	public List<Expediente> consultarCausasPorIdCaso(Long idCaso) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new Expediente(v.expedienteId, n.numeroExpediente, n.numeroExpedienteId) ");
		queryStr.append(" from Expediente v left join v.numeroExpedientes n");
		queryStr.append(" where n.tipoExpediente.valorId = ");
		queryStr.append(TipoExpediente.CAUSA.getValorId());
		queryStr.append(" AND v.caso.casoId = ");
		queryStr.append(idCaso);
		Query query = super.getSession().createQuery(queryStr.toString());
		return query.list();
	}

	@Override
	public Expediente buscarExpedientePorCasoCalidadNombreImputado(String numeroGeneralCaso,
			String nombre, String aPaterno, String aMaterno, Long idCalidad) throws NSJPNegocioException {
		StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("SELECT i.expediente");
		hqlQuery.append(" FROM Involucrado i LEFT JOIN i.nombreDemograficos n");
		hqlQuery.append(" WHERE i.calidad.tipoCalidad.valorId = "+idCalidad);
		hqlQuery.append(" AND i.expediente.caso.numeroGeneralCaso= '"+numeroGeneralCaso+"'");
		hqlQuery.append(" AND n.nombre = '"+nombre+"'");
		hqlQuery.append(" AND n.apellidoPaterno = '"+aPaterno+"'");
		hqlQuery.append(" AND n.apellidoMaterno = '"+aMaterno+"'");
		Query query = super.getSession().createQuery(hqlQuery.toString());	
		return (Expediente)query.uniqueResult();
	}
	
	@Override
	public Expediente buscarExpedientePorCasoFolioInvolucrado(String numeroGeneralCaso, String folioInvolucrado) throws NSJPNegocioException {
		StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("SELECT i.expediente");
		hqlQuery.append(" FROM Involucrado i ");
		hqlQuery.append(" WHERE i.folioElemento = '"+folioInvolucrado+"'");
		hqlQuery.append(" AND i.expediente.caso.numeroGeneralCaso= '"+numeroGeneralCaso+"'");
		Query query = super.getSession().createQuery(hqlQuery.toString());	
		return (Expediente)query.uniqueResult();
	}
	
	@Override
	public Expediente buscarExpedientePorCasoImputado(String numeroCaso,
			String imputado, Long valorId) {
		StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("SELECT i.expediente");
		hqlQuery.append(" FROM Involucrado i LEFT JOIN i.nombreDemograficos n");
		hqlQuery.append(" AND i.calidad.tipoCalidad.valorId = "+valorId);		
		hqlQuery.append(" AND i.expediente.caso.numeroGeneralCaso= '"+numeroCaso+"'");	
		hqlQuery.append(" AND n.nombre+' '+ n.apellidoPaterno");
		hqlQuery.append("+' '+n.apellidoMaterno like '"+imputado+"'");
		Query query = super.getSession().createQuery(hqlQuery.toString());	
		return (Expediente)query.uniqueResult();
	}

	@Override
	public Expediente consultarExpedientePorIdNumerExpediente(
			Long idNumeroExpediente) throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT numero.expediente ")
				.append(" FROM NumeroExpediente numero  ")
				.append(" WHERE numero.numeroExpedienteId = "+idNumeroExpediente);
		logger.debug("queryString :: "+ queryString);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Expediente) query.uniqueResult();
	}
	
    @Override
    public Long obtenerExpedienteIdPorIdNumerExpediente(
            Long idNumeroExpediente) throws NSJPNegocioException {
        StringBuffer queryString = new StringBuffer();
        queryString.append("SELECT numero.expediente.expedienteId ")
                .append(" FROM NumeroExpediente numero  ")
                .append(" WHERE numero.numeroExpedienteId = "+idNumeroExpediente);
        Query query = super.getSession().createQuery(queryString.toString());
        return (Long) query.uniqueResult();
    }	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerExpedientesPorMes (Date fechaIni, Date fechaFin) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH (e.fechaCreacion), COUNT(*) FROM Expediente e ")
					.append(" WHERE CONVERT (varchar, e.fechaCreacion, 112) BETWEEN ").append(DateUtils.formatearBD(fechaIni))
					.append(" AND ").append(DateUtils.formatearBD(fechaFin))
					.append(" GROUP BY MONTH (e.fechaCreacion)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Expediente> consultarExpedientesPorNumeroCaso(String numeroExpediente)
 throws NSJPNegocioException {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append(" from Expediente e");
        queryStr.append(" where e.caso.numeroGeneralCaso like '%");
        queryStr.append(numeroExpediente);
        queryStr.append("%'");
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("caso")) {
                queryStr.append(" order by ");
                queryStr.append("e.caso.numeroGeneralCaso");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("fecha")) {
                queryStr.append(" order by ");
                queryStr.append("e.caso.fechaApertura");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }
        return super.ejecutarQueryPaginado(queryStr, pag);
    }
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String obtenerUltimoNumeroDeExpediente(Long area) {
		final StringBuffer queryStr = new StringBuffer();
		//Se obtienen los ultimos cinco digitos del maximo en la cadena de los numero des expediente
		//queryStr.append(" select MAX(substring(obj.numeroExpediente,len(obj.numeroExpediente)-4,5)) from NumeroExpediente obj where 1=1 ");
		queryStr.append(" select MAX(obj.numeroExpediente) from NumeroExpediente obj where 1=1 ");
		if(area != null){
			queryStr.append(" AND obj.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");			
			queryStr.append(area);		
		}	
		logger.debug("queryStr :: " + queryStr+" query completo");
		Query qry = super.getSession().createQuery(queryStr.toString());
		String maximoDeLosConsecutivos = (String) qry.uniqueResult();
		
		//Se prepara la segunda consulta la cual permite obtener el numero de Expdiente id
		queryStr.delete(0, queryStr.length());
		
		queryStr.append(" select obj.numeroExpedienteId from NumeroExpediente obj where 1= 1 ");
		if(area != null){
			queryStr.append(" AND obj.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");			
			queryStr.append(area);		
		}	
		queryStr.append(" and obj.numeroExpediente like '%" + maximoDeLosConsecutivos + "%'");
		logger.debug("queryStr :: " + queryStr);
		qry = super.getSession().createQuery(queryStr.toString());
		
		List<Long> idsNumerosExpediente = (List<Long>)qry.list();
		Long idNumeroExpediente = null;
		if(idsNumerosExpediente != null && idsNumerosExpediente.size() > 0)
			idNumeroExpediente = idsNumerosExpediente.get(0);
		
		//Se prepara la segunda consulta la cual permite obtener el numero de Expdiente id	
		queryStr.delete(0, queryStr.length());
		
		queryStr.append("select e.numeroExpediente ");
		queryStr.append(" from NumeroExpediente e where e.numeroExpedienteId =" + idNumeroExpediente);
		if(area != null){
			queryStr.append(" and e.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
			queryStr.append(area);
		}
		
		logger.debug("queryStr :: " + queryStr);
		qry = super.getSession().createQuery(queryStr.toString());
		return (String) qry.uniqueResult();
	}

	@Override
	public JerarquiaOrganizacional consultarOrigendeExpediente(Long expedienteId) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append(" SELECT ne.jerarquiaOrganizacional");
		queryStr.append(" FROM NumeroExpediente ne");
		queryStr.append(" WHERE ne.expediente = "+expedienteId);
		queryStr.append(" ORDER BY ne.fechaApertura");
		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (JerarquiaOrganizacional) qry.list().get(0);
	}
	
	@Override
	public List<Expediente> consultarExpedientesPorIdCaso(Long idCaso) {
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from Expediente v ");
		queryStr.append(" where v.caso.casoId = ");
		queryStr.append(idCaso);		
		@SuppressWarnings("unchecked")
		List<Expediente> resp = super.getHibernateTemplate().find(
				queryStr.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("resp.size() :: " + resp.size());
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Expediente> buscadorDeExpedientes(String numeroExpediente,
			Long areaId,Long discriminanteId,Long institucionId) {
		if (logger.isDebugEnabled()) {
			logger.debug("numeroExpediente :: [" + numeroExpediente
					+ "] y areaId :: " + areaId);
		}
		logger.info("numeroExpediente :: [" + numeroExpediente
				+ "] y areaId :: " + areaId);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append("SELECT DISTINCT new Expediente( e.expedienteId, n.numeroExpediente, n.numeroExpedienteId, e.caso) FROM Expediente e LEFT JOIN e.numeroExpedientes n");
		queryStr.append(" WHERE n.numeroExpediente like '");
		queryStr.append(numeroExpediente);
		queryStr.append("'");
		if(areaId!= null && !areaId.equals(0L)){
			queryStr.append(" and n.jerarquiaOrganizacional.jerarquiaOrganizacionalId = " + areaId);
		}
		if(discriminanteId != null && !discriminanteId.equals(0L)){
			queryStr.append(" and n.expediente.discriminante.catDiscriminanteId = ").append(discriminanteId);
		}
//		if(institucionId != null && !institucionId.equals(0L)){
//			queryStr.append(" and e.pertenceConfInst.confInstitucionId = ").append(institucionId);
//		}
		queryStr.append(" ORDER BY n.numeroExpediente ASC");
		//queryStr.append(")");
		return super.ejecutarQueryPaginado(queryStr, pag);
	}
}

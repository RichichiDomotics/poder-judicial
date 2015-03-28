/**
 * Nombre del Programa : NumeroExpedienteDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para el DAO del numero del expediente
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
package mx.gob.segob.nsjp.dao.expediente.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO del numero del expediente.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository("numeroExpedienteDAO")
public class NumeroExpedienteDAOImpl
        extends GenericDaoHibernateImpl<NumeroExpediente, Long>
        implements
        NumeroExpedienteDAO {

    @Autowired
    private ExpedienteDAO expedienteDao;
    @Autowired
    private RegistroBitacoraDAO  registroBitacoraDao;
    @Override
    public NumeroExpediente obtenerNumeroExpediente(Long expedienteId,
            Long areaId) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new NumeroExpediente(v.id, v.numeroExpediente, v.fechaApertura, v.funcionario.claveFuncionario)");
        queryStr.append(" from NumeroExpediente v ");
        queryStr.append(" where v.expediente.expedienteId = ");
        queryStr.append(expedienteId);
        queryStr.append(" and v.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
        queryStr.append(areaId);
        logger.info("Query:"+ queryStr);
        return (NumeroExpediente) super.getSession().
                createQuery(queryStr.toString()).uniqueResult();
    }

    @Override
    public List<NumeroExpediente> consultarNumeroExpedientes(Long expedienteId,
            Long areaId) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new NumeroExpediente(v.id, v.numeroExpediente, v.fechaApertura, v.funcionario.claveFuncionario)");
        queryStr.append(" from NumeroExpediente v ");
        queryStr.append(" where v.expediente.expedienteId = ");
        queryStr.append(expedienteId);
        queryStr.append(" and v.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
        queryStr.append(areaId);
        logger.info("Query:"+ queryStr);
        return super.getSession().createQuery(queryStr.toString()).list();
    }
    
    @Override
    public List<NumeroExpediente> consultarNumeroExpedientesXIdExpAreaDiscriminante(Long expedienteId,
            Long areaId,Long discriminante) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new NumeroExpediente(v.id, v.numeroExpediente, v.fechaApertura, v.funcionario.claveFuncionario)");
        queryStr.append(" from NumeroExpediente v ");
        queryStr.append(" where v.expediente.expedienteId = ");
        queryStr.append(expedienteId);
        
        if(discriminante != null){
        	 queryStr.append(" and v.expediente.discriminante.catDiscriminanteId = ");
            queryStr.append(discriminante);
        }
        if(areaId != null){
        	queryStr.append(" and v.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ");
            queryStr.append(areaId);
        }
        
        logger.info("Query:"+ queryStr);
        return super.getSession().createQuery(queryStr.toString()).list();
    }
    
    @Override
    public NumeroExpediente obtenerNumeroExpediente(String numeroExpediente,Long discriminanteId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ne FROM NumeroExpediente ne ").
                append("WHERE ne.numeroExpediente = :numeroExpediente");
        if(discriminanteId != null && !discriminanteId.equals(0L) ){
        	sb.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
        }
        	
        
        Query query = super.getSession().createQuery(sb.toString());
        query.setParameter("numeroExpediente", numeroExpediente);
        List res = query.list();
        if(!res.isEmpty()){
        	return (NumeroExpediente)res.get(0);
        }
        
        logger.info("Query:"+ sb);
        return null;
    }

    @Override
    public void asociarNumExpediente(Expediente expediente, Usuario usuario) {
        if (logger.isDebugEnabled()) {
            logger.debug("\n Asociando el expediente = " + expediente);
            logger.debug("Con el usuario = " + usuario + "\n\n");
            logger.debug("Buscando el expediente = " + expediente.getNumeroExpediente());
        }
//        TODO cbeltran donde se usa el método, en qué proceso? si el funcionario es not null
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ne FROM NumeroExpediente ne ").
                append("WHERE ne.numeroExpediente = :numeroExpediente");
        Session session = super.getSession();
        Query query = session.createQuery(sb.toString());
        query.setParameter("numeroExpediente", expediente.getNumeroExpediente());
        NumeroExpediente numeroExpediente = (NumeroExpediente) query.uniqueResult();
        numeroExpediente.setFuncionario(usuario.getFuncionario());
//        session.close();
        if (logger.isDebugEnabled()) {
            logger.debug("salvando numeroExpediente = " + numeroExpediente);
            logger.debug("numeroExpediente.getNumeroExpedienteId() = " +
                    numeroExpediente.getNumeroExpedienteId());
        }
        saveOrUpdate(numeroExpediente);
        if (logger.isDebugEnabled()) {
            logger.debug("numeroExpediente guardado");
            logger.debug("Salvado cambios del expediente = " + expediente);
            logger.debug("expediente.getExpedienteId() = " + expediente.getExpedienteId());
        }
        expedienteDao.saveOrUpdate(expediente);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<NumeroExpediente> consultarTOCAPorCausa(Long padre) {
		final StringBuffer queryStr = new StringBuffer();
			
		queryStr.append("FROM NumeroExpediente ne")
				.append(" WHERE ne.numeroExpedientePadre =" + padre);
			
		List<NumeroExpediente> numeros= super.getHibernateTemplate().find(queryStr.toString());
		return numeros;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO#consultarUltimaParidadAsignadaDeNumeroExpediente()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Boolean consultarUltimaParidadAsignadaDeNumeroExpediente() throws NSJPNegocioException {
		Boolean esPar = null;
		List resExpId = getHibernateTemplate().find("Select max(numeroExpedienteId) " +
				"from NumeroExpediente ne where ne.esPar is not null");
		if(resExpId != null && !resExpId.isEmpty() && resExpId.get(0) != null){
			esPar = (Boolean)getHibernateTemplate().find("Select ne.esPar from NumeroExpediente ne where ne.numeroExpedienteId = ?",
					resExpId.get(0)).get(0);
		}
		
		return esPar;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO#consultarExpedientesConEventosHistorico(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NumeroExpediente> consultarExpedientesConEventosHistorico(
			Long casoId,Long usuarioId) {
		//TODO: VAP agregar la constante de tiempo de consultas históricas hacia atrás
		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.setTime(new Date());
		fechaInicio.add(Calendar.DATE, ConstantesGenerales.DIAS_ATRAS_CONSULTAS_HISTORICAS*-1);
		
		String query = "Select nExp from NumeroExpediente nExp where exists (" +
				"select audiencia.audienciaId from Audiencia audiencia where " +
				"audiencia.numeroExpediente  = nExp and " +
				"audiencia.fechaAudiencia between ? and ? and " +
				"audiencia.numeroExpediente.expediente.caso.casoId = ?"+
				") ";
		
		return getHibernateTemplate().find(query,fechaInicio.getTime(),new Date(),casoId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarCausasHistorico(Calendar fechaInicio,Long discriminanteId) {
		Calendar fechaFin = Calendar.getInstance();
		fechaFin.setTime(new Date());
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT ne FROM NumeroExpediente ne ")
					.append("WHERE  ne.fechaApertura BETWEEN ? ")
					.append(" AND ? ").append(" AND ne.tipoExpediente=")
					.append(TipoExpediente.CAUSA.getValorId())
					//Usado para distritos
					.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
					
		queryString.append(" AND ne.numeroExpedienteId IN (")
					.append("SELECT neP.numeroExpedientePadre FROM NumeroExpediente neP WHERE ")
					.append("neP.tipoExpediente=").append(TipoExpediente.CARPETA_DE_EJECUCION.getValorId())
					.append(" AND neP.estatus=").append(EstatusExpediente.CERRADO.getValorId()).append(")");
		
		logger.debug("Query:: " + queryString);
		
		return getHibernateTemplate().find(queryString.toString(),fechaInicio.getTime(), new Date());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarCarpetasEjecucionPorCausa(
			Long idNumeroExpediente) throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.numeroExpedientePadre=").append(idNumeroExpediente)
					.append(" AND ne.tipoExpediente=").append(TipoExpediente.CARPETA_DE_EJECUCION.getValorId());
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarNumeroExpedienteByEstatus(
			TipoExpediente tipoExpediente, EstatusExpediente estatusExpediente)
			throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.tipoExpediente=").append(tipoExpediente.getValorId())
					.append(" AND ne.estatus=").append(estatusExpediente.getValorId());
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarByUsuarioArea(Long idUsuario,
			Long areaId, Long estatusExpediente, Long discriminanteId) {
		
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.funcionario=").append(idUsuario);

		if(areaId != null && areaId > 0)
						queryString.append(" AND ne.jerarquiaOrganizacional=").append(areaId);
		
		if(estatusExpediente!=null) {
			queryString.append(" AND ( ne.estatus="+estatusExpediente);
			if(estatusExpediente.equals(EstatusExpediente.ABIERTO.getValorId())){
				queryString.append(" OR ne.estatus="+EstatusExpediente.ABIERTO_EJECUCION.getValorId());
				queryString.append(" OR ne.estatus="+EstatusExpediente.ABIERTO_INTEGRACION.getValorId());
				queryString.append(" OR ne.estatus="+EstatusExpediente.ABIERTO_RESTAURATIVA.getValorId());
				queryString.append(" OR ne.estatus="+EstatusExpediente.ABIERTO_TECNICA_CON_CARPETA.getValorId());
				queryString.append(" OR ne.estatus="+EstatusExpediente.ABIERTO_TECNICA_SIN_CARPETA.getValorId());
			}
			queryString.append(" )");
		
			//consulta por agencias
			if(discriminanteId > 0)
				queryString.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
			
			
		}
		logger.debug("queryString :: " + queryString);
		//Query query = super.getSession().createQuery(queryString.toString());
		//return query.list();
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        return super.ejecutarQueryPaginado(queryString, pag);
	}

	
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> buscarNumeroExpedienteAbieroPorIdFuncionario(
			Long claveFuncionario) throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.funcionario.claveFuncionario=").append(claveFuncionario)
					.append(" AND ne.estatus=").append(EstatusExpediente.ABIERTO.getValorId());
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NumeroExpediente> consultarNumeroExpedientePorCasoDeSolicitud(Long solicitudId) {
		
		return getHibernateTemplate().find("from NumeroExpediente ne " +
				"where ne.expediente.caso.numeroGeneralCaso in " +
				"(select s.numeroCasoAsociado from Solicitud s where s.documentoId=?) ",solicitudId);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarHistoricoCausasExpediente(Date fechaHistorico) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT(ne) FROM NumeroExpediente ne LEFT JOIN ne.numeroExpedientes nes WHERE ")
					.append("CONVERT (nvarchar, ne.fechaApertura ,112)>=")
					.append(DateUtils.formatearBD(fechaHistorico)).append(" AND ")
					.append("ne.tipoExpediente=").append(TipoExpediente.CAUSA.getValorId())
					.append(" AND nes.tipoExpediente=").append(TipoExpediente.CARPETA_DE_EJECUCION.getValorId())
					.append(" AND nes.estatus=").append(EstatusExpediente.CERRADO.getValorId());

		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NumeroExpediente> consultarNumeroExpedientePorFiltro(
			Date fechaInicio, Date fechaFin, Funcionario usuario,
			TipoExpediente tipo, Long numeroExpedientePadreId) {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer query = new StringBuffer();
		
		query.append("from NumeroExpediente ne where 1=1 ");
		
		if(usuario.getDiscriminante() != null && usuario.getDiscriminante().getCatDiscriminanteId() != null){
			query.append(" and ne.expediente.discriminante.catDiscriminanteId = ");
			query.append(usuario.getDiscriminante().getCatDiscriminanteId());
		}
		
		if(fechaInicio != null && fechaFin != null){
			query.append(" AND CONVERT (nvarchar, ne.fechaApertura, 112)  BETWEEN ").append(DateUtils.formatearBD(fechaInicio))
			.append(" AND ").append(DateUtils.formatearBD(fechaFin));
		}
		else{
			if(fechaInicio != null){
				query.append(" and CONVERT (nvarchar, ne.fechaApertura, 112) >=  ");
				query.append(DateUtils.formatearBD(fechaInicio));
			}
			if(fechaFin != null){
				query.append(" and CONVERT (nvarchar, ne.fechaApertura, 112)  <= ");
				query.append(DateUtils.formatearBD(fechaFin));
			}		
		}
		
		if(usuario.getClaveFuncionario() != null){
			query.append(" and ne.funcionario.claveFuncionario = ");
			query.append(usuario.getClaveFuncionario());
			
		}
		
		if(tipo != null){
			query.append(" and ne.tipoExpediente.valorId =  ");
			query.append(tipo.getValorId());
			
		}
		if(numeroExpedientePadreId != null){
			query.append(" and ne.numeroExpedientePadre.numeroExpedienteId =  ");
			query.append(numeroExpedientePadreId);
			
		}
		query.append(" order by ne.fechaApertura desc");
				
//		final PaginacionDTO pag = PaginacionThreadHolder.get();
//	    logger.debug("pag :: " + pag);
//	    if(pag!=null && pag.getCampoOrd() != null){
//         	query.append(" ORDER BY ");
//	    	int orden = NumberUtils.toInt(pag.getCampoOrd(), 0);
//	    	switch(orden){
//		    	case 2025: //Tipo Defensa
//		    		query.append(" f.tipoEspecialidad ");
//		    		break;
//		    	case 2027: // Especialidad
//		    		query.append(" f.especialidad ");
//		    		break;
//		    	case 2026: // Nombre 
//		    	default:
//		    		query.append(" f.nombreFuncionario asc, ");
//		    		query.append(" f.apellidoPaternoFuncionario asc, ");
//		    		query.append(" f.apellidoMaternoFuncionario asc");
//		    		break;		    		
//		    	}
//	    	query.append(" "+pag.getDirOrd());
//	    }
//	    logger.debug("query :: " + query);
//	    return super.ejecutarQueryPaginado(query, pag);
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        return super.ejecutarQueryPaginado(query, pag);
		
		
	}

	@Override
	public NumeroExpediente obtenerNumeroExpedienteXExpediente(
			Long expedienteId) {
		StringBuilder queryString = new StringBuilder();
		
		queryString.append(" FROM NumeroExpediente ne");
		queryString.append(" WHERE ne.expediente.expedienteId="+expedienteId);
		
		Query query = super.getSession().createQuery(queryString.toString());
		
//		return (NumeroExpediente) query.uniqueResult();
		return (NumeroExpediente) query.list().get(0);
	}
	
    @Override
    public List<NumeroExpediente> consultarNumeroExpedientesXExpediente(
            Long expedienteId) {
        StringBuilder queryString = new StringBuilder();
        
        queryString.append(" FROM NumeroExpediente ne");
        queryString.append(" WHERE ne.expediente.expedienteId="+expedienteId);
        
        Query query = super.getSession().createQuery(queryString.toString());
        
//      return (NumeroExpediente) query.uniqueResult();
        return query.list();
    }	
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO#consultarNumeroExpedientePorNumeroCaso(java.lang.String)
	 */
	@Override
	public List<NumeroExpediente> consultarNumeroExpedientePorNumeroCaso(
			String caso) {
		
		return getHibernateTemplate().find("from NumeroExpediente ne where ne.expediente.caso.numeroGeneralCaso = ?",caso);
		
		
	}

	@Override
	public NumeroExpediente obtenerCarpetaEjecucionByCaso(String numeroCaso,
			Long tipoNumExpediente) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.expediente.caso.numeroGeneralCaso = '")
					.append(numeroCaso).append("' AND ne.tipoExpediente=")
					.append(tipoNumExpediente);
		Query query = super.getSession().createQuery(queryString.toString());
		return (NumeroExpediente) query.uniqueResult();
	}

	@Override
	public NumeroExpediente obtenerCausaByExpediente(Long expedienteId) {		
		StringBuilder queryString = new StringBuilder();
		
		queryString.append(" FROM NumeroExpediente ne")
					.append(" WHERE ne.expediente.expedienteId=").append(expedienteId)
					.append(" AND ne.tipoExpediente=").append(TipoExpediente.CAUSA.getValorId());	;
			
		Query query = super.getSession().createQuery(queryString.toString());
		return (NumeroExpediente) query.uniqueResult();		
	}

	@Override
	public List<NumeroExpediente> consultarNumeroExpedienteByTipoYEstatus(
            Long tipoExp, Long estatusExp,Long discriminanteId) {
        StringBuffer queryString = new StringBuffer();

        queryString.append(" FROM NumeroExpediente ne").append(" WHERE 1=1 ");
        if (tipoExp != null) {
            queryString.append(" AND ne.tipoExpediente=").append(tipoExp);
        }
        if (estatusExp != null) {
            queryString.append(" AND ne.estatus=").append(estatusExp);
        }
        if(!discriminanteId.equals(0L)){
        	queryString.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
        }
        logger.debug("queryString :: " + queryString);
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        return super.ejecutarQueryPaginado(queryString, pag);
    }

	@Override
	public List<NumeroExpediente> consultarnumExpedienteHijos(
			Long numeroExpedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE")
					.append(" ne.numeroExpedientePadre=").append(numeroExpedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	public NumeroExpediente consultarNumeroExpedienteXExpedienteId(
			Long expedienteId) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE")
					.append(" ne.expediente.expedienteId=").append(expedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (NumeroExpediente) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obtenerNumExpPorEstatusYMes(Date fechaInicial,
			Date fechaFinal, EstatusExpediente estatusExpediente) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT MONTH(ne.fechaApertura), COUNT(*) FROM NumeroExpediente ne")
					.append(" WHERE ne.estatus=").append(estatusExpediente.getValorId())
					.append(" AND CONVERT (varchar, ne.fechaApertura, 112) BETWEEN ")
					.append(DateUtils.formatearBD(fechaInicial)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFinal))
					.append("GROUP BY MONTH(ne.fechaApertura)");
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	public NumeroExpediente obtenerCarpetaEjecucionPorInvolucrado(
			Long idInvolucrado) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT s.numeroExpediente ")
					.append("FROM Sentencia s ")
					.append("WHERE s.involucrado=").append(idInvolucrado);
		Query query = super.getSession().createQuery(queryString.toString());		
		return (NumeroExpediente)query.uniqueResult();
	}
	
	@Override
	public NumeroExpediente buscarNumeroExpedientePorCasoFolioImputado(
			String numeroCaso, String folioImputado, Long claveFuncionario) {
	    final StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append("select ne from NumeroExpediente ne inner join ne.funcionario f inner join ne.expediente e inner join e.caso c left outer join e.elementos ele");
		hqlQuery.append(" WHERE ele.folioElemento = '"+folioImputado+"'");
		hqlQuery.append(" AND ele.calidad.tipoCalidad.valorId = "+Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
		hqlQuery.append(" AND c.numeroGeneralCaso= '"+numeroCaso+"'");
		if(claveFuncionario != null){
			hqlQuery.append(" AND f.claveFuncionario = "+claveFuncionario);
		}
		logger.debug("hqlQuery :: " + hqlQuery);
		Query query = super.getSession().createQuery(hqlQuery.toString());	
		return (NumeroExpediente)query.uniqueResult();
	}

    @Override
    public NumeroExpediente obtenerExpedienteDefensaPorCasoFolioImputado(
            String numeroCaso, String folioImputado, Long claveFuncionario) {
        final StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("select ne from NumeroExpediente ne inner join ne.funcionario f inner join ne.expediente e inner join e.caso c inner join ne.defensaInvolucrado d inner join d.involucrado i");
        hqlQuery.append(" WHERE i.folioElemento = '"+folioImputado+"'");
        hqlQuery.append(" AND i.calidad.tipoCalidad.valorId = "+Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
        hqlQuery.append(" AND c.numeroGeneralCaso= '"+numeroCaso+"'");
        if(claveFuncionario != null){
            hqlQuery.append(" AND f.claveFuncionario = "+claveFuncionario);
        }
        logger.debug("hqlQuery :: " + hqlQuery);
        Query query = super.getSession().createQuery(hqlQuery.toString());  
        return (NumeroExpediente)query.uniqueResult();
    }

	@Override
	public NumeroExpediente consultarNumExpPorFuncionarioYNumExp(
			Long claveFuncionario, Long numExpId) {
		Calendar diaActual = Calendar.getInstance();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT ne FROM NumeroExpediente ne LEFT JOIN ne.permisoExpedientes nep ")
					.append("WHERE (nep.funcionario=").append(claveFuncionario)
					.append(" AND CONVERT (varchar, nep.fechaLimite, 112)>=").append(DateUtils.formatearBD(diaActual.getTime()))
					.append(") OR (ne.funcionario=").append(claveFuncionario).append(") ")			
					.append(" AND ne.numeroExpedienteId=").append(numExpId);
		Query query = super.getSession().createQuery(queryString.toString());		
		return (NumeroExpediente) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarNumExpPorFuncionario(
			Long claveFuncionario) {
		Calendar diaActual = Calendar.getInstance();
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT ne FROM NumeroExpediente ne LEFT JOIN ne.permisoExpedientes nep ")
					.append("WHERE (nep.funcionario=").append(claveFuncionario)
					.append(" AND CONVERT (varchar, nep.fechaLimite, 112)>=").append(DateUtils.formatearBD(diaActual.getTime()))
					.append(") OR (ne.funcionario=").append(claveFuncionario).append(")");
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		return ejecutarQueryPaginado(queryString, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarExpedientesDelFuncionario(
			Long claveFuncionario, Long agenciaId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne WHERE ")
					.append("ne.funcionario=").append(claveFuncionario);
		if(!agenciaId.equals(0L)){
			//Se corrige la consulta a la nueva versión 
			//queryString.append(" AND ne.expediente.discriminante.valorId=").append(agenciaId);
			queryString.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(agenciaId);
		}
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		return ejecutarQueryPaginado(queryString, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> consultarExpedientesPorFiltroST(
			Date fechaInicio, Date fechaFin, Long Area,
			Long estatusExpediente,Long discriminanteId) {

		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM NumeroExpediente ne")
		.append(" WHERE 1=1 ");
		
		queryStr.append(" AND ne.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ").append(Area);
		queryStr.append(" AND ne.estatus=").append(estatusExpediente);
		
		if(fechaInicio != null && fechaFin != null){
			queryStr.append(" AND CONVERT (varchar, ne.fechaApertura, 112)  BETWEEN ").append(DateUtils.formatearBD(fechaInicio))
			.append(" AND ").append(DateUtils.formatearBD(fechaFin));
		}
//		else{
//			Calendar fechaActual = Calendar.getInstance();
//			fechaActual.setTime(new Date());
//			
//			queryStr.append(" AND CONVERT (varchar, ne.fechaApertura, 112) =").append(DateUtils.formatearBD(fechaActual));
//		}
		
		if(discriminanteId != null){
			queryStr.append(" AND ne.expediente.discriminante.catDiscriminanteId=").append(discriminanteId);
		}
		
		
		logger.info("Query:" + queryStr);
		
		final PaginacionDTO pag = PaginacionThreadHolder.get();
		return ejecutarQueryPaginado(queryStr, pag);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NumeroExpediente> obtenerNumExpPorFuncionarioYEstatus(
			Long idFuncionario, Long estus) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM NumeroExpediente ne ")
					.append("WHERE ne.funcionario=").append(idFuncionario)
					.append(" AND ne.estatus=").append(estus);
		
		Query query = super.getSession().createQuery(queryString.toString());
		
		return query.list();
	}	
		
}

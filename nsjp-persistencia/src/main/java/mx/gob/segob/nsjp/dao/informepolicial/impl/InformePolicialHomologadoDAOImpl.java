package mx.gob.segob.nsjp.dao.informepolicial.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.informepolicial.InformePolicialHomologadoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InformePolicialHomologadoDAOImpl extends GenericDaoHibernateImpl<InformePolicialHomologado,Long> 
			implements InformePolicialHomologadoDAO {


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> ObtenerFolioIPH() {		
		Date d = new Date();
		int anio = d.getYear()+1900;	
		StringBuffer queryString = new StringBuffer();
		queryString.append("Select count(folioIPH), max(folioIPH) FROM InformePolicialHomologado ");
		queryString.append("where folioIPH like '" + anio + "%'");
		Query query = super.getSession().createQuery(queryString.toString());		
		List<Object[]> lst  = query.list();	
		return lst;
	}

	@Override
	public InformePolicialHomologado consultaInformePorFolio(Long folio) {
		InformePolicialHomologado iphResult = new InformePolicialHomologado();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM InformePolicialHomologado iph ")
		.append("where iph.folioIPH = ")
		.append(folio);
		//.append(" and ex.expedienteId = iph.expediente.expedienteId");
		Query query = super.getSession().createQuery(queryString.toString());
		iphResult = (InformePolicialHomologado) query.uniqueResult(); 
		return iphResult;
	}

	@Override
	public InformePolicialHomologado consultarInformePorId(Long Id) {
		InformePolicialHomologado iphResult = new InformePolicialHomologado();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM InformePolicialHomologado iph ")
		.append("where iph.informePolicialHomologadoId = ")
		.append(Id);
		Query query = super.getSession().createQuery(queryString.toString());
		iphResult = (InformePolicialHomologado) query.uniqueResult(); 
		return iphResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelitoIph> consultarDelitosDeIPH(Long idInforme) {
		List<DelitoIph> delitos = new ArrayList<DelitoIph>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM DelitoIph dIph ")
		.append("where dIph.informePolicialHomologado.informePolicialHomologadoId = ")
		.append(idInforme);
		Query query = super.getSession().createQuery(queryString.toString());
		delitos = query.list();
		return delitos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaltaAdministrativaIph> consultarFaltaAdministrativaDeIPH(
			Long idInforme) {
		List<FaltaAdministrativaIph> faltaAdmin = new ArrayList<FaltaAdministrativaIph>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM FaltaAdministrativaIph dIph ")
		.append("where dIph.informePolicialHomologado.informePolicialHomologadoId = ")
		.append(idInforme);
		Query query = super.getSession().createQuery(queryString.toString());
		faltaAdmin = query.list();
		return faltaAdmin;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InformePolicialHomologado> consultarInformes(){
		//List<InformePolicialHomologado> resp = new ArrayList<InformePolicialHomologado> ();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM InformePolicialHomologado iph ");
		//Query query = super.getSession().createQuery(queryString.toString());
		
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	queryString.append(" order by ");
            	queryString.append("iph.folioIPH");
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
        
        final List<InformePolicialHomologado> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<InformePolicialHomologado> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}

	@Override
	public Long obtenerIPHPorFechas(Date fechaInicio, Date fechaFin,
			Boolean condicion) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT COUNT(*) FROM InformePolicialHomologado iph ")
					.append("LEFT JOIN iph.involucradoIphs inv WHERE inv.esDetenido=")
					.append(condicion).append(" AND CONVERT(VARCHAR, iph.fechaCaptura ,112)")
					.append(" BETWEEN ").append(DateUtils.formatearBD(fechaInicio)).append(" AND ")
					.append(DateUtils.formatearBD(fechaFin));
		Query query = super.getSession().createQuery(queryString.toString());
		return (Long) query.uniqueResult();
	}

	
	
}

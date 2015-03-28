package mx.gob.segob.nsjp.dao.evidencia.imp;

import java.util.Collections;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.Solicitud;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EvidenciaDaoImp extends GenericDaoHibernateImpl<Evidencia, Long>
        implements
            EvidenciaDAO {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Evidencia> consultarEvidenciasXCadenaCustodia(
            CadenaDeCustodia cadenaDeCustodia) {
        List<Evidencia> evidencias = Collections.EMPTY_LIST;
        if (cadenaDeCustodia != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT cdc.evidencias FROM CadenaDeCustodia cdc");
            sb.append("    WHERE cdc.cadenaDeCustodiaId = :cadenaDeCustodiaId");

    		final PaginacionDTO pag = PaginacionThreadHolder.get();
            logger.debug("pag :: " + pag);
            if (pag != null && pag.getCampoOrd() != null) {
                if (pag.getCampoOrd().equals("1")) {
                	sb.append(" order by ");
                	sb.append("cdc.folio");
                	sb.append(" ").append(pag.getDirOrd());
                }
            }
            logger.debug("query :: " + sb);
            final Query queryStr = super.getSession().createQuery(sb.toString());
            if (pag != null && pag.getPage() != null) {
            	queryStr.setFirstResult(pag.getFirstResult());
                if (pag.getRows() != null & pag.getRows() > 0) {
                	queryStr.setMaxResults(pag.getRows());
                } else {
                	queryStr.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
                }
            }

            queryStr.setParameter("cadenaDeCustodiaId", cadenaDeCustodia.getCadenaDeCustodiaId());
            
            evidencias = queryStr.list();
            if (logger.isDebugEnabled()) {
                logger.debug("resp.size() :: " + evidencias.size());
            }
            if (pag != null && pag.getPage() != null) {
            	queryStr.setFirstResult(0);
            	queryStr.setMaxResults(-1);
                final List<Eslabon> temp = queryStr.list();
                logger.debug("temp.size() :: " + temp.size());
                pag.setTotalRegistros(new Long(temp.size()));
                PaginacionThreadHolder.set(pag);
            }
    		
        }
        return evidencias;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Eslabon> consultarEvidenciasResguardadasXUsario(Long surId, Long idCadenaCus) {
        List<Eslabon> evidencias = Collections.EMPTY_LIST;
        if (surId != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT es FROM Evidencia ev");
            sb.append(" LEFT OUTER JOIN ev.eslabones es ");
            sb.append(" WHERE es.funcionarioRecibe.claveFuncionario = ");
            sb.append(surId);
            sb.append(" AND es.eslabonId =");
            sb.append(" ( select max(es2.eslabonId) from Eslabon es2 where es2.evidencia=ev)");
            if (idCadenaCus!=null) {
                sb.append(" AND ev.cadenaDeCustodia.cadenaDeCustodiaId = ");
                sb.append(idCadenaCus);
            }
            
    		final PaginacionDTO pag = PaginacionThreadHolder.get();
            logger.debug("pag :: " + pag);
            if (pag != null && pag.getCampoOrd() != null) {
                if (pag.getCampoOrd().equals("1")) {
                	sb.append(" order by ");
                	sb.append("ev.numeroEvidencia");
                	sb.append(" ").append(pag.getDirOrd());
                }
            }
            logger.debug("query :: " + sb);
            final Query queryStr = super.getSession().createQuery(sb.toString());
            if (pag != null && pag.getPage() != null) {
            	queryStr.setFirstResult(pag.getFirstResult());
                if (pag.getRows() != null & pag.getRows() > 0) {
                	queryStr.setMaxResults(pag.getRows());
                } else {
                	queryStr.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
                }
            }
            
            evidencias = queryStr.list();
            if (logger.isDebugEnabled()) {
                logger.debug("resp.size() :: " + evidencias.size());
            }
            if (pag != null && pag.getPage() != null) {
            	queryStr.setFirstResult(0);
            	queryStr.setMaxResults(-1);
                final List<Eslabon> temp = queryStr.list();
                logger.debug("temp.size() :: " + temp.size());
                pag.setTotalRegistros(new Long(temp.size()));
                PaginacionThreadHolder.set(pag);
            }
    		
        }
        return evidencias;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Evidencia> consultarEvidenciasXCadenaYEstatus(
			Long cadenaDeCustodiaId, Long estatus) {
		StringBuilder cadenaQuery=new StringBuilder();
		
		cadenaQuery.append("FROM Evidencia ev");
		cadenaQuery.append(" WHERE ev.cadenaDeCustodia="+cadenaDeCustodiaId);
		if(estatus!=null)
		cadenaQuery.append(" AND ev.estatus="+estatus);
		
		Query query= super.getSession().createQuery(cadenaQuery.toString());
		return query.list();
	}

    @SuppressWarnings("unchecked")
	@Override
    public List<Evidencia> consultarEvidenciaPorAlmacen(Almacen almacen) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM Almacen a").
                append(" INNER JOIN a.objetos obs").
                append(" INNER JOIN obs.evidencia e").
                append(" WHERE a = ").append(almacen.getIdentificadorAlmacen());
        Query q = getSession().createQuery(sb.toString());
        return q.list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Evidencia> consultarEvidenciasXCadena(Long cadenaId) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM Evidencia e")
        .append(" where e.cadenaDeCustodia = " )
        .append(cadenaId)
        .append(" ORDER BY e.cadenaDeCustodia.cadenaDeCustodiaId");
        
        
         
        Query q = getSession().createQuery(sb.toString());
        return q.list();		
	}

	@Override
	public Evidencia consultarEvidenciaXObjetoId(Long IdObjeto) {

		StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM Evidencia e")
        .append(" where e.objeto.elementoId = " )
        .append(IdObjeto);        
                         
        Query q = getSession().createQuery(sb.toString());
        return (Evidencia) q.uniqueResult();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evidencia> consultarevidenciaXAlmacenXEstatus(
			Long identificadorAlmacen, Long estatusEv, Long idCaso) {
		final StringBuffer queryString = new StringBuffer();
        
		queryString.append(" FROM Evidencia ev");
		queryString.append(" WHERE ev.objeto.almacen="+identificadorAlmacen);
		if(estatusEv!=null){
			if(!estatusEv.equals(-1L))
				queryString.append(" AND ev.estatus="+estatusEv);
		}else
			queryString.append(" AND ev.estatus IS NOT NULL");
		if(idCaso!=null)
			queryString.append(" AND ev.cadenaDeCustodia.expediente.caso="+idCaso);

	      final PaginacionDTO pag = PaginacionThreadHolder.get();

			logger.debug("pag :: " + pag);
			if (pag != null && pag.getCampoOrd() != null) {
				if (pag.getCampoOrd().equals("6")) {
					queryString.append(" order by ");
					queryString.append("ev.evidenciaId");
					queryString.append(" ").append(pag.getDirOrd());
				}
				if (pag.getCampoOrd().equals("2")) {
					queryString.append(" order by ");
					queryString.append("ev.estatus.valorId");
					queryString.append(" ").append(pag.getDirOrd());
				}
				if (pag.getCampoOrd().equals("3")) {
					queryString.append(" order by ");
					queryString.append("ev.cadenaDeCustodia.folio");
					queryString.append(" ").append(pag.getDirOrd());
				}
				if (pag.getCampoOrd().equals("1")) {
					queryString.append(" order by ");
					queryString.append("ev.cadenaDeCustodia.expediente.caso.numeroGeneralCaso");
					queryString.append(" ").append(pag.getDirOrd());
				}
			}
			return super.ejecutarQueryPaginado(queryString, pag);   
	}

}

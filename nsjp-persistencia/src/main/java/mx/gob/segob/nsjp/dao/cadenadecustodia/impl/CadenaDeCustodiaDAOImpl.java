/**
 * Nombre del Programa : ConsultarCadenaDeCustodiaXFolioDAOImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.cadenadecustodia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Expediente;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Jacob Lobaco
 */
@Repository("cadenaDeCustodiaDAO")
public class CadenaDeCustodiaDAOImpl
        extends GenericDaoHibernateImpl<CadenaDeCustodia, Long>
        implements CadenaDeCustodiaDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public CadenaDeCustodia consultarCadenaDeCustodiaXFolio(String folio) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT cc ").
                append(" FROM CadenaDeCustodia cc ").
                append(" WHERE cc.folio = :folio");
        Query query = super.getSession().createQuery(queryString.toString());
        query.setParameter("folio", folio);
        return (CadenaDeCustodia) query.uniqueResult();
    }

    @Override
    public CadenaDeCustodia consultarCadenaDeCustodiaPorFolioExpediente(String folio, Long idExpediente) throws NSJPNegocioException{
        StringBuilder hqlQuery = new StringBuilder();
        hqlQuery.append("SELECT cc ").
        		append(" FROM CadenaDeCustodia cc ").
                append(" WHERE cc.folio = '"+folio+"'").
        		append(" AND cc.expediente = "+idExpediente);
        logger.debug("hqlQuery :: " + hqlQuery);
        Query query = super.getSession().createQuery(hqlQuery.toString());
        return (CadenaDeCustodia) query.uniqueResult();
    }
    
    /**
     * {@inheritDoc}
     */
//    @SuppressWarnings("unchecked")
//	@Override
//    public List<CadenaDeCustodia> consultarCadenaCustodiaXExpediente(Expediente expediente) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT cc FROM CadenaDeCustodia cc ").
//                append(" WHERE cc.expediente = ").
//                append(expediente.getExpedienteId());
//        Query query = super.getSession().createQuery(sb.toString());
//        return query.list();
//    }
    
    @SuppressWarnings("unchecked")
    @Override
	public List<CadenaDeCustodia> consultarCadenaCustodiaXExpediente(Expediente expediente) {        
	    StringBuffer queryStr = new StringBuffer();
	            queryStr.append("FROM CadenaDeCustodia cc ").append("WHERE cc.expediente =")
	                            .append(expediente.getExpedienteId());
	            //Query query = super.getSession().createQuery(queryStr.toString());
	            //return query.list();
	    final PaginacionDTO pag = PaginacionThreadHolder.get();
	    logger.debug("pag :: " + pag);
	    if (pag != null && pag.getCampoOrd() != null) {
	        if (pag.getCampoOrd().equals("idS")) {
	            queryStr.append(" order by ");
	            queryStr.append("cc.cadenaDeCustodiaId");
	            queryStr.append(" ").append(pag.getDirOrd());
	        }
	        if (pag.getCampoOrd().equals("cadenaCustodia")) {
	            queryStr.append(" order by ");
	            queryStr.append("cc.folio");
	            queryStr.append(" ").append(pag.getDirOrd());
	        }
	    }
	
	    return super.ejecutarQueryPaginado(queryStr, pag);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CadenaDeCustodia> consultarCadenaCustodiaXNumeroExpediente(
			Long expedienteId,String folioCadena) {
        
		StringBuilder sb = new StringBuilder();
        sb.append("SELECT cc FROM CadenaDeCustodia cc ");
        
        if(folioCadena==null)
        {
        	sb.append(" WHERE cc.expediente.expedienteId = ")
        	.append(expedienteId);
        }else
        {
        	sb.append(" WHERE cc.expediente.expedienteId = ")
        	.append(expedienteId)
        	.append(" AND cc.folio = '" + folioCadena + "'");
        }
        
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
            	sb.append(" order by ");
            	sb.append("cc.folio");
            	sb.append(" ").append(pag.getDirOrd());
            }
        }
        logger.debug("sb :: " + sb);
        final Query query = super.getSession().createQuery(sb.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        
        final List<CadenaDeCustodia> resp = query.list();
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<CadenaDeCustodia> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
		
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CadenaDeCustodia> consultarCadenaCustodiaXAlmacen(
			Long identificadorAlmacen, Long idCaso) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ev.cadenaDeCustodia FROM Evidencia ev ").
        append(" WHERE ev.objeto.almacen = "+identificadorAlmacen);
		if(idCaso!=null)
			sb.append(" AND ev.cadenaDeCustodia.expediente.caso="+idCaso);
        
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
	}
	
	public CadenaDeCustodia consultarFolioDeCadenaXObjetoId(Long idObjeto, Long numeroExpedienteId)  throws NSJPNegocioException{
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select new CadenaDeCustodia(cc.cadenaDeCustodiaId, cc.folio)");
		queryStr.append(" from CadenaDeCustodia cc LEFT JOIN cc.evidencias e");
		queryStr.append(" where e.objeto.elementoId = ");
		queryStr.append(idObjeto);
		queryStr.append(" AND cc.expediente.expedienteId = (select ne.expediente.expedienteId FROM NumeroExpediente ne WHERE ne.numeroExpedienteId = ");
		queryStr.append(numeroExpedienteId).append(")");
        Query query = super.getSession().createQuery(queryStr.toString());
        return (CadenaDeCustodia) query.uniqueResult();
	}
    
}

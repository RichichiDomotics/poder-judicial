/**
* Nombre del Programa : ActoBuenaConductaDAOImpl.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Mar 2012
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
package mx.gob.segob.nsjp.dao.programa.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.programa.ActoBuenaConductaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.ActoBuenaConducta;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public class ActoBuenaConductaDAOImpl extends GenericDaoHibernateImpl<ActoBuenaConducta, Long>
		implements ActoBuenaConductaDAO {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.programa.ActoBuenaConductaDAO#consultarActoBuenaConductaPorId(mx.gob.segob.nsjp.model.ActoBuenaConducta)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ActoBuenaConducta consultarActoBuenaConductaPorId(
			ActoBuenaConducta actoBuenaConducta) {
		
		ActoBuenaConducta resp = null;
		if (actoBuenaConducta != null &&
				actoBuenaConducta.getActoBuenaConductaId() != null &&
				actoBuenaConducta.getActoBuenaConductaId() > 0L){
			
			
	    	StringBuilder hqlQuery = new StringBuilder ( " SELECT abc ")
	    										.append( " FROM ActoBuenaConducta AS abc ")
	    										.append( " WHERE abc.actoBuenaConductaId = ? ");

	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, actoBuenaConducta.getActoBuenaConductaId());
	        
			List<ActoBuenaConducta> actos = (List<ActoBuenaConducta>) query.list();
			if(!actos.isEmpty()){
				resp = actos.get(0);
	        }
		}
		
		return resp;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.programa.ActoBuenaConductaDAO#consultarActosBuenaConductaPorSentencia(mx.gob.segob.nsjp.model.Sentencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ActoBuenaConducta> consultarActosBuenaConductaPorSentencia(
			Sentencia sentencia) throws NSJPNegocioException {
		if (sentencia != null &&
				sentencia.getSentenciaId() != null &&
				sentencia.getSentenciaId() > 0L){
			
	    	StringBuffer hqlQuery = new StringBuffer();
	        hqlQuery.append(" SELECT abc ")
	        		.append(" FROM ActoBuenaConducta as abc ")
	        		.append(" JOIN abc.sentencia as s")
	        		.append(" WHERE s.sentenciaId = ? ");
	        
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        if (pag != null && pag.getCampoOrd() != null) {
	            hqlQuery.append(" order by ");
	            hqlQuery.append(pag.getCampoOrd());
	            hqlQuery.append(" ").append(pag.getDirOrd());
	        }
	        
	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, sentencia.getSentenciaId());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(pag.getFirstResult());
	            if (pag.getRows() != null & pag.getRows() > 0) {
	                query.setMaxResults(pag.getRows());
	            } else {
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
	            }
	        }
	        logger.debug("query :: " + query);
			List<ActoBuenaConducta> resp = (List<ActoBuenaConducta>)query.list();
	        logger.debug("resp.size() :: " + resp.size());

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<ActoBuenaConducta> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        return resp;
		}else{
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.programa.ActoBuenaConductaDAO#consultarActosBuenaConductaSinAcumular(mx.gob.segob.nsjp.model.Sentencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ActoBuenaConducta> consultarActosBuenaConductaSinAcumular(
			Sentencia sentencia) throws NSJPNegocioException {
		if (sentencia != null &&
				sentencia.getSentenciaId() != null &&
				sentencia.getSentenciaId() > 0L){
			
	    	StringBuffer hqlQuery = new StringBuffer();
	        hqlQuery.append(" SELECT abc ")
	        		.append(" FROM ActoBuenaConducta as abc ")
	        		.append(" JOIN abc.sentencia as s")
	        		.append(" WHERE s.sentenciaId = ? ")
	        		.append(" AND abc.periodoAcumulacionPuntos is null ");
	        
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        if (pag != null && pag.getCampoOrd() != null) {
	            hqlQuery.append(" order by ");
	            hqlQuery.append(pag.getCampoOrd());
	            hqlQuery.append(" ").append(pag.getDirOrd());
	        }
	        
	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, sentencia.getSentenciaId());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(pag.getFirstResult());
	            if (pag.getRows() != null & pag.getRows() > 0) {
	                query.setMaxResults(pag.getRows());
	            } else {
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
	            }
	        }
	        logger.debug("query :: " + query);
			List<ActoBuenaConducta> resp = (List<ActoBuenaConducta>)query.list();
	        logger.debug("resp.size() :: " + resp.size());

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<ActoBuenaConducta> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        return resp;
		}else{
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
	}
}

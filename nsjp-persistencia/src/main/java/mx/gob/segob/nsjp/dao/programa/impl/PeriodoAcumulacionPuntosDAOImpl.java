/**
* Nombre del Programa : PeriodoAcumulacionPuntosDAOImpl.java
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
import mx.gob.segob.nsjp.dao.programa.PeriodoAcumulacionPuntosDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.PeriodoAcumulacionPuntos;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public class PeriodoAcumulacionPuntosDAOImpl extends
		GenericDaoHibernateImpl<PeriodoAcumulacionPuntos, Long> implements PeriodoAcumulacionPuntosDAO {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.programa.PeriodoAcumulacionPuntosDAO#consultarPeriodoAcumulacionPuntosPorId(mx.gob.segob.nsjp.model.PeriodoAcumulacionPuntos)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PeriodoAcumulacionPuntos consultarPeriodoAcumulacionPuntosPorId(
			PeriodoAcumulacionPuntos periodoAcumulacionPuntos) {
		
		PeriodoAcumulacionPuntos resp = null;
		
		if (periodoAcumulacionPuntos != null &&
				periodoAcumulacionPuntos.getPeriodoAcumulacionPuntosId() != null &&
				periodoAcumulacionPuntos.getPeriodoAcumulacionPuntosId() > 0L){
				
	    	StringBuilder hqlQuery = new StringBuilder ( " SELECT pap ")
	    										.append( " FROM PeriodoAcumulacionPuntos AS pap ")
	    										.append( " WHERE pap.periodoAcumulacionPuntosId = ? ");

	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, periodoAcumulacionPuntos.getPeriodoAcumulacionPuntosId());
	        
			List<PeriodoAcumulacionPuntos> periodos = (List<PeriodoAcumulacionPuntos>) query.list();
			if(!periodos.isEmpty()){
				resp = periodos.get(0);
	        }
		}
		
		return resp;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.programa.PeriodoAcumulacionPuntosDAO#consultarPeriodosAcumulacionPuntosPorSentencia(mx.gob.segob.nsjp.model.Sentencia)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PeriodoAcumulacionPuntos> consultarPeriodosAcumulacionPuntosPorSentencia(
			Sentencia sentencia) throws NSJPNegocioException {
		
		if (sentencia != null &&
				sentencia.getSentenciaId() != null &&
				sentencia.getSentenciaId() > 0L){
			
	    	StringBuffer hqlQuery = new StringBuffer();
	        hqlQuery.append(" SELECT pap ")
	        		.append(" FROM PeriodoAcumulacionPuntos as pap ")
	        		.append(" JOIN pap.actosBuenaConducta as abc")
	        		.append(" WHERE abc.sentencia.sentenciaId = ? ");
	        
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
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT);
	            }
	        }
	        
	        logger.debug("query :: " + query);
			List<PeriodoAcumulacionPuntos> resp = (List<PeriodoAcumulacionPuntos>)query.list();
	        logger.debug("resp.size() :: " + resp.size());

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<PeriodoAcumulacionPuntos> temp = query.list();
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

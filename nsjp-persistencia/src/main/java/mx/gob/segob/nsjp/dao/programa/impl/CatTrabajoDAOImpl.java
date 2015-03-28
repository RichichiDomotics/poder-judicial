/**
* Nombre del Programa : CatTrabajoDAOImpl.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.programa.CatTrabajoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatTrabajo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de CatTrabajo
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public class CatTrabajoDAOImpl extends GenericDaoHibernateImpl<CatTrabajo, Long> implements CatTrabajoDAO {

	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return Lista de CatTrabajo
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public List<CatTrabajo> consultarCatTrabajo()throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM CatTrabajo ct WHERE bActivo = 1 ");
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        if (pag != null && pag.getCampoOrd() != null) {
            hqlQuery.append(" order by ");
            hqlQuery.append(pag.getCampoOrd());
            hqlQuery.append(" ").append(pag.getDirOrd());
        }
        Query query = super.getSession().createQuery(hqlQuery.toString());
        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
            }
        }
        logger.debug("query :: " + query);
        List<CatTrabajo> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<CatTrabajo> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
	
	/**
	 * M&eacute;todo que consulta un trabajo por id
	 * @return CatTrabajo
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public CatTrabajo consultarCatTrabajoPorId(CatTrabajo catTrabajo)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM CatTrabajo cc WHERE bActivo = 1 ");
        hqlQuery.append(" AND CatTrabajo_id = ").append(catTrabajo.getCatTrabajoId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<CatTrabajo> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
}
        
        return null;
    }

	
	/**
	 * M&eacute;todo que elimina un trabajo por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarTrabajoPorId(CatTrabajo catTrabajo) throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" UPDATE CatTrabajo ct SET bActivo = ? WHERE catTrabajoId = ? ");
//        hqlQuery.append(" AND catTrabajoId NOT IN( ");
//        hqlQuery.append(" SELECT trabs.catTrabajoId FROM CatPrograma cp JOIN cp.catTrabajos trabs ");
//        hqlQuery.append(" WHERE trabs.catTrabajoId = ct.catTrabajoId ");
//        hqlQuery.append(" )");
        int  res = super.getSession().createQuery(hqlQuery.toString()).
        							  setParameter(0, Boolean.FALSE).
        							  setParameter(1, catTrabajo.getCatTrabajoId()).
        							  executeUpdate();
        if(res == 0){
        	return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }		
	
	
}

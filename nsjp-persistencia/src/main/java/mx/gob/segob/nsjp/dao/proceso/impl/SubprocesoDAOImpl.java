/**
* Nombre del Programa : SubprocesoDAOImpl.java
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
package mx.gob.segob.nsjp.dao.proceso.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.proceso.SubprocesoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Subproceso;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */

@Repository
public class SubprocesoDAOImpl extends GenericDaoHibernateImpl<Subproceso, Long>  implements SubprocesoDAO{
	
	/**
	 * M&eacute;todo que consulta todos los subprocesos
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	   
	@SuppressWarnings("unchecked")
	public List<Subproceso> consultarSubprocesos() throws NSJPNegocioException {
        StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM Subproceso sp ");
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
        List<Subproceso> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<Subproceso> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }

	/**
	 * M&eacute;todo que consulta todos los subprocesos de un Proceso
	 * @param proceso: id del proceso  
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	
	@SuppressWarnings("unchecked")
	public List<Subproceso> consultarSubprocesosPorProceso(Proceso proceso)
			throws NSJPNegocioException {
		List<Subproceso> lstProcesos = new ArrayList<Subproceso>();
		String hqlQuery = " SELECT sp FROM Subproceso sp WHERE sp.proceso.id = :proceso  " +
				" ) ";
		Query query = super.getSession().createQuery(hqlQuery.toString());
		query.setParameter("proceso", proceso.getId());

		logger.debug("query :: " + query);
        lstProcesos = query.list();
        logger.debug("resp.size() :: " + lstProcesos.size());
		
		return lstProcesos;
	}

}

/**
* Nombre del Programa : ProcesoDAOImpl.java
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
import mx.gob.segob.nsjp.dao.proceso.ProcesoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Rol;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */

@Repository
public class ProcesoDAOImpl extends GenericDaoHibernateImpl<Proceso, Long> implements ProcesoDAO {
	
	/**
	 * M&eacute;todo que consulta todos los procesos
	 * @return Lista de Procesos
	 * @throws NSJPNegocioException
	 */
    @SuppressWarnings("unchecked")
	public List<Proceso> consultarProcesos() throws NSJPNegocioException {
        StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM Proceso p ");
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
        List<Proceso> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<Proceso> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
    
	/**
	 * M&eacute;todo que consulta todos los procesos pertenecientes a un Rol
	 * @param Rol: Id del rol a consultar 
	 * @return Lista de Procesos
	 * @throws NSJPNegocioException
	 */
    
    @SuppressWarnings("unchecked")
	public List<Proceso> consultarProcesosPorRol(Rol rol)
			throws NSJPNegocioException {
		
		List<Proceso> lstProcesos = new ArrayList<Proceso>();
		String hqlQuery = " SELECT DISTINCT (p) FROM Proceso p JOIN p.roles rs WHERE p.id in ( " +
				" SELECT pr.id FROM Proceso pr join pr.roles r WHERE r.id = :rol  " +
				" ) ";
		Query query = super.getSession().createQuery(hqlQuery.toString());
		query.setParameter("rol", rol.getRolId());

		logger.debug("query :: " + query);
        lstProcesos = query.list();
        logger.debug("resp.size() :: " + lstProcesos.size());
		
		return lstProcesos;
	}
}

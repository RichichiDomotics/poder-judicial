/**
 * Nombre del Programa  : RolDAOImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 27 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Acceso a la información de Roles
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.usuario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.usuario.RolDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.model.Rol;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Acceso a la información de Roles
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Repository
public class RolDAOImpl extends GenericDaoHibernateImpl<Rol, Long> implements
		RolDAO {

    @SuppressWarnings("unchecked")
    public List<Rol> consultarRoles(FiltroRolesDTO filtroRolesDTO) throws NSJPNegocioException {
        StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM Rol r ");
        hqlQuery.append(" WHERE esActivo = ")
        		.append(true)
        		.append(" AND institucionPertenece = ")
        		.append(filtroRolesDTO.getConfInstitucionId());
        if(filtroRolesDTO.getIdRolSelec()!=null && !filtroRolesDTO.getIdRolSelec().equals("")){
        	hqlQuery.append(" AND r.rolId not in ("+filtroRolesDTO.getIdRolSelec()+")");
        }
       
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        if (pag != null && pag.getCampoOrd() != null) {
       	 logger.debug("pag.getCampoOrd():" + pag.getCampoOrd());
         hqlQuery.append(" order by ");
         if(pag.getCampoOrd().equals("Clave")){
         	hqlQuery.append("r.nombreRol");
         }else{
         	hqlQuery.append(pag.getCampoOrd());
         }
         
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
        List<Rol> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(1);
            query.setMaxResults(-1);
            final List<Rol> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
	@Override
	public Rol consultarRol(Rol rol) throws NSJPNegocioException {
		Rol rolResp = null;
		StringBuffer queryString = new StringBuffer();
		if (rol != null) {
			if (rol.getRolId() != null) {
				
				queryString.append(" FROM Rol r WHERE r.rolId = '")
						.append(rol.getRolId()).append("'");
				Query query = super.getSession().createQuery(
						queryString.toString());
				if (!query.list().isEmpty()) {
					rolResp = (Rol) query.list().get(0);
				}
			}

		}
		return rolResp;
	}

}

/**
* Nombre del Programa : ProgramaDAOImpl.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/02/2012
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.programa.CatProgramaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatPrograma;
import mx.gob.segob.nsjp.model.CentroDetencion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */

@Repository
public class CatProgramaDAOImpl extends GenericDaoHibernateImpl<CatPrograma, Long> implements CatProgramaDAO {
	
	/**
	 * M&eacute;todo que consulta todos los programas
	 * @return Lista de Programas
	 * @throws NSJPNegocioException
	 */
    @SuppressWarnings("unchecked")
	public List<CatPrograma> consultarProgramas() throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" FROM CatPrograma cp WHERE bActivo = 1 ");
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
        List<CatPrograma> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<CatPrograma> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
    
	/**
	 * M&eacute;todo que consulta un programa por id
	 * @return CatPrograma
	 * @throws NSJPNegocioException
	 */
    @SuppressWarnings("unchecked")
    public CatPrograma consultarProgramaPorId(CatPrograma catPrograma) throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" FROM CatPrograma cp WHERE bActivo = 1 ");
        hqlQuery.append(" AND CatPrograma_id = ").append(catPrograma.getCatProgramaId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<CatPrograma> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
}
        
        return null;
    }

	/**
	 * M&eacute;todo que elimina un programa por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarProgramaPorId(CatPrograma catPrograma) throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" UPDATE CatPrograma cp SET bActivo = ? WHERE CatPrograma_Id = ? ");
        
        int  res = super.getSession().createQuery(hqlQuery.toString()).
        							  setParameter(0, Boolean.FALSE).
        							  setParameter(1, catPrograma.getCatProgramaId()).
        							  executeUpdate();
        if(res == 0){
        	return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<CatPrograma> consultarProgramasPorCentroDetencion(
			CentroDetencion centroDetencion) throws NSJPNegocioException {
		if (centroDetencion != null &&
				centroDetencion.getCentroDetencionId() != null &&
				centroDetencion.getCentroDetencionId() > 0L){
			
	    	StringBuffer hqlQuery = new StringBuffer();
	        hqlQuery.append(" SELECT cp ")
	        		.append(" FROM CatPrograma as cp ")
	        		.append(" JOIN cp.centroDetencions as cd")
	        		.append(" WHERE bActivo = 1 ")
	        		.append(" AND cd.centroDetencionId = ? ");
	        
	        final PaginacionDTO pag = PaginacionThreadHolder.get();
	        if (pag != null && pag.getCampoOrd() != null) {
	            hqlQuery.append(" order by ");
	            hqlQuery.append(pag.getCampoOrd());
	            hqlQuery.append(" ").append(pag.getDirOrd());
	        }
	        
	        Query query = super.getSession().createQuery(hqlQuery.toString());
	        query.setParameter(0, centroDetencion.getCentroDetencionId());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(pag.getFirstResult());
	            if (pag.getRows() != null & pag.getRows() > 0) {
	                query.setMaxResults(pag.getRows());
	            } else {
	                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT); // default
	            }
	        }
	        logger.debug("query :: " + query);
			List<CatPrograma> resp = (List<CatPrograma>)query.list();
	        logger.debug("resp.size() :: " + resp.size());

	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<CatPrograma> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        return resp;
		}
		return consultarProgramas();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CatPrograma> consultarProgramasDisponibles(
			CentroDetencion centroDetencion, List<CatPrograma> programasAsignados, Date fechaActual) throws NSJPNegocioException {
		if (centroDetencion != null &&
				centroDetencion.getCentroDetencionId() != null &&
				centroDetencion.getCentroDetencionId() > 0L){
			
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			List<Long> idsProgramasAsignados = creaListaIdsCatPrograma(programasAsignados);
			
	    	String hqlQuery = creaQueryProgramasDisponibles(centroDetencion, idsProgramasAsignados,
	    													fechaActual, pag);

	        Query query = super.getSession().createQuery(hqlQuery);
	        asignaParametrosProgramasDisponibles(query, centroDetencion, idsProgramasAsignados, fechaActual, pag);
	        
	        logger.debug("query :: " + query);
			List<CatPrograma> resp = (List<CatPrograma>)query.list();
	        logger.debug("resp.size() :: " + resp.size());
	        if (pag != null && pag.getPage() != null) {
	            query.setFirstResult(0);
	            query.setMaxResults(-1);
	            final List<CatPrograma> temp = query.list();
	            logger.debug("temp.size() :: " + temp.size());
	            pag.setTotalRegistros(new Long(temp.size()));
	            PaginacionThreadHolder.set(pag);
	        }
	        return resp;
		}
		return consultarProgramas();
	}
       
	
	private List<Long> creaListaIdsCatPrograma(List<CatPrograma> catProgramas){
		List<Long> ids = null;
		if (catProgramas != null && !catProgramas.isEmpty()){
			ids = new ArrayList<Long>();
			for (CatPrograma cp: catProgramas){
				ids.add(cp.getCatProgramaId());
			}
		}
		return ids;
	}
	
	private String creaQueryProgramasDisponibles(CentroDetencion centro, List<Long> idsProgramasAsignados, 
			Date fechaActual, PaginacionDTO pag){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT cp ")
		  .append(" FROM CatPrograma as cp ")
		  .append(" JOIN cp.centroDetencions as cd")
		  .append(" WHERE bActivo = 1 ");

		if (centro != null){
			sb.append(" AND cd.centroDetencionId = :centro ");
		}

		if (idsProgramasAsignados != null && !idsProgramasAsignados.isEmpty()){
			sb.append(" AND cp.catProgramaId not in (:idsProgramasAsignados) ");
		}

		if (fechaActual != null){
			sb.append(" AND cp.dfechaInicioPrograma >= :fechaActual ");
		}
		
		if (pag != null && pag.getCampoOrd() != null) {
            sb.append(" order by ");
            sb.append(pag.getCampoOrd());
            sb.append(" ").append(pag.getDirOrd());
        }

		return sb.toString();
	}
	
	private void asignaParametrosProgramasDisponibles(Query query, CentroDetencion centro, 
			List<Long> idsProgramasAsignados, Date fechaActual, PaginacionDTO pag){
		if (centro != null){
			query.setParameter("centro", centro.getCentroDetencionId());
		}
		
		if (idsProgramasAsignados != null && !idsProgramasAsignados.isEmpty()){
			query.setParameterList("idsProgramasAsignados", idsProgramasAsignados);
		}
		
		if (fechaActual != null){
			query.setParameter("fechaActual", fechaActual);
		}
		
		if (pag != null && pag.getPage() != null) {
            query.setFirstResult(pag.getFirstResult());
            if (pag.getRows() != null & pag.getRows() > 0) {
                query.setMaxResults(pag.getRows());
            } else {
                query.setMaxResults(PaginacionDTO.DEFAULT_MAX_RESULT);
            }
        }
	}
}

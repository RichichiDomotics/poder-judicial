/**
* Nombre del Programa : CatCursoDAOImpl.java
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
import mx.gob.segob.nsjp.dao.programa.CatCursoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatCurso;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de CatCurso
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public class CatCursoDAOImpl extends GenericDaoHibernateImpl<CatCurso, Long> implements CatCursoDAO {

	/**
	 * M&eacute;todo que consulta todos los cursos
	 * @return Lista de CatCurso
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public List<CatCurso> consultarCatCurso()throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM CatCurso cc WHERE bActivo = 1 ");
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
        List<CatCurso> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<CatCurso> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
	
	/**
	 * M&eacute;todo que consulta un curso por id
	 * @return CatCurso
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public CatCurso consultarCatCursoPorId(CatCurso catCurso)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM CatCurso cc WHERE bActivo = 1 ");
        hqlQuery.append(" AND CatCurso_id = ").append(catCurso.getCatCursoId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<CatCurso> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
}
        
        return null;
    }	
	
	/**
	 * M&eacute;todo que elimina un curso por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarCursoPorId(CatCurso catCurso) throws NSJPNegocioException {
        
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append(" UPDATE CatCurso cc SET bActivo = ? WHERE catCursoId = ? ");
//        hqlQuery.append(" AND catCursoId NOT IN ( ");
//        hqlQuery.append(" 	SELECT cur.catCursoId FROM CatPrograma cp JOIN cp.catCursos cur ");
//        hqlQuery.append(" 	WHERE cur.catCursoId = cc.catCursoId ");
//        hqlQuery.append(" ) ");
        int  res = super.getSession().createQuery(hqlQuery.toString()).
        							  setParameter(0, Boolean.FALSE).
        							  setParameter(1, catCurso.getCatCursoId()).
        							  executeUpdate();
        if(res == 0){
        	return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }	
	
}

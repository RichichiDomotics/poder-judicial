/**
* Nombre del Programa : CatTipoProgramaDAOImpl.java
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
import mx.gob.segob.nsjp.dao.programa.CatTipoProgramaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatTipoPrograma;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de CatTipoPrograma
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public class CatTipoProgramaDAOImpl extends GenericDaoHibernateImpl<CatTipoPrograma, Long> implements CatTipoProgramaDAO {

	@SuppressWarnings("unchecked")
	/**
	 * M&eacute;todo que consulta todos los tipos de programas
	 * @return Lista de CatTipoPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoPrograma> consultarCatTipoPrograma()throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM CatTipoPrograma ctp ");
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
        List<CatTipoPrograma> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());

        if (pag != null && pag.getPage() != null) {
            query.setFirstResult(0);
            query.setMaxResults(-1);
            final List<CatTipoPrograma> temp = query.list();
            logger.debug("temp.size() :: " + temp.size());
            pag.setTotalRegistros(new Long(temp.size()));
            PaginacionThreadHolder.set(pag);
        }
        return resp;
    }
    
}

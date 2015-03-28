/**
* Nombre del Programa : CatUIEspecializadaDAOImpl.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/03/2012
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
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.CatUIEspecializadaDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.CatUIEspecializada;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
@Repository
public class CatUIEspecializadaDAOImpl extends GenericDaoHibernateImpl<CatUIEspecializada, Long>
		implements CatUIEspecializadaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatUIEspecializada> consultarTodos() {
		
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("from CatUIEspecializada catUIE ");
		queryStr.append(" order by catUIE.catUIEId");

		logger.debug("queryStr :: " + queryStr);
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
//		Query query = super.getSession().createQuery(queryStr.toString());
        return super.ejecutarQueryPaginado(queryStr, pag);
//		return query.list();
	}
}

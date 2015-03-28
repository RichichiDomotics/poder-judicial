/**
* Nombre del Programa : CatCategoriaRelacionDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.dao.relacion.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.relacion.CatCategoriaRelacionDAO;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;

/**
 * Implementacion de los metodos de acceso a datos de CatCategoriaRelacion.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class CatCategoriaRelacionDAOImpl extends
		GenericDaoHibernateImpl<CatCategoriaRelacion, Long> implements
		CatCategoriaRelacionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatCategoriaRelacion> consultarCatCategoriaRelacionSiDocumento(
			Boolean esDocumento) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("FROM CatCategoriaRelacion ccr");
		queryStr.append(" WHERE ccr.esDocumento="+esDocumento);
		
		Query query = super.getSession().createQuery(queryStr.toString());

		return query.list();
	}

}

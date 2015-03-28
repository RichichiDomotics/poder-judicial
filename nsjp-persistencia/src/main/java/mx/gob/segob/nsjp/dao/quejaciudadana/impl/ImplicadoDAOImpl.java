/**
* Nombre del Programa : ImplicadoDAOImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Sep 2011
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
package mx.gob.segob.nsjp.dao.quejaciudadana.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.quejaciudadana.ImplicadoDAO;
import mx.gob.segob.nsjp.model.Implicado;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class ImplicadoDAOImpl extends GenericDaoHibernateImpl<Implicado, Long>
		implements ImplicadoDAO {

	@Override
	public Implicado consultarImplicadoPorId(
			Implicado aoImplicado) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM Implicado im");
		queryStr.append(" WHERE im.implicadoId = " + aoImplicado.getImplicadoId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (Implicado)qry.uniqueResult();
	}
	

}

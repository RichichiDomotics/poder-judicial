/**
* Nombre del Programa : NotaEvolucionDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad NotaEvolucion
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
package mx.gob.segob.nsjp.dao.sesion.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.sesion.NotaEvolucionDAO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.NotaEvolucion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad NotaEvolucion.
 * @author rgama
 *
 */
@Repository
public class NotaEvolucionDAOImpl extends
		GenericDaoHibernateImpl<NotaEvolucion, Long> implements NotaEvolucionDAO{

	@Override
	public NotaEvolucion consultarNotaEvolucionPorId(
			NotaEvolucion aoNotaEvolucion) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM NotaEvolucion ne");
		queryStr.append(" WHERE ne.sesionId = " + aoNotaEvolucion.getSesionId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (NotaEvolucion)qry.uniqueResult();
	}
}

/**
* Nombre del Programa : EntrevistaComplementariaDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad EntrevistaComplementaria
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
import mx.gob.segob.nsjp.dao.sesion.EntrevistaComplementariaDAO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.EntrevistaInicial;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad EntrevistaComplementaria.
 * @author rgama
 *
 */
@Repository
public class EntrevistaComplementariaDAOImpl extends
		GenericDaoHibernateImpl<EntrevistaComplementaria, Long> implements EntrevistaComplementariaDAO{

	@Override
	public EntrevistaComplementaria consultarEntrevistaComplementariaPorId(
			EntrevistaComplementaria aoEntrevistaComplementaria) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM EntrevistaComplementaria ec");
		queryStr.append(" WHERE ec.sesionId = " + aoEntrevistaComplementaria.getSesionId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (EntrevistaComplementaria)qry.uniqueResult();
	}
}

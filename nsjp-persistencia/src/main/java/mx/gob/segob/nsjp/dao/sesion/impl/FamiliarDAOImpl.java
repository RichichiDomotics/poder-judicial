/**
* Nombre del Programa : FamiliarDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Familiar
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

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.sesion.FamiliarDAO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.EntrevistaInicial;
import mx.gob.segob.nsjp.model.Familiar;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad Familiar.
 * @author rgama
 *
 */
@Repository
public class FamiliarDAOImpl extends
		GenericDaoHibernateImpl<Familiar, Long> implements FamiliarDAO{

	@Override
	public Familiar consultarFamiliarPorId(Familiar aoFamiliar) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM Familiar f");
		queryStr.append(" WHERE f.familiarId = " + aoFamiliar.getFamiliarId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (Familiar)qry.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Familiar> consultarFamiliaresPorIdEntrevistaComplementaria(
			EntrevistaComplementaria aoEntrevistaComplementaria) {
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM Familiar f");
		queryStr.append(" WHERE f.entrevistaComplementaria.sesionId=" + aoEntrevistaComplementaria.getSesionId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return qry.list();
	}
}

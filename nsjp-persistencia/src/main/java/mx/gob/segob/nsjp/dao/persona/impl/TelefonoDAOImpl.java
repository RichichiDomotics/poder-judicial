/**
* Nombre del Programa : TelefonoDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de los metodos de acceso a datos de la entidad Telefono
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
package mx.gob.segob.nsjp.dao.persona.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.model.Telefono;

/**
 * Implementacion de los metodos de acceso a datos de la entidad Telefono.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class TelefonoDAOImpl extends GenericDaoHibernateImpl<Telefono, Long>
		implements TelefonoDAO {

	@SuppressWarnings("unchecked")
	public List<Telefono> consultarTelefonosByPersona(Long elementoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT t ")
					.append("FROM Telefono t ")
					.append("WHERE t.persona.elementoId=:elementoId");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("elementoId", elementoId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Telefono> consultarTelefonosByFuncionario(Long claveFuncionario) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT t ")
					.append("FROM Telefono t ")
					.append("WHERE t.funcionario.claveFuncionario=:claveFuncionario");
		Query query = super.getSession().createQuery(queryString.toString());
		query.setParameter("claveFuncionario", claveFuncionario);
		return query.list();
	}
}

/**
* Nombre del Programa : ServidorPublicoDAOImp.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos para la entidad ServidorPublico
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
package mx.gob.segob.nsjp.dao.involucrado.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.ServidorPublicoDAO;
import mx.gob.segob.nsjp.model.ServidorPublico;

/**
 * Implementacion de metodos de acceso a datos para la entidad ServidorPublico.
 * @version 1.0		
 * @author cesarAgustin
 *
 */
@Repository
public class ServidorPublicoDAOImp extends GenericDaoHibernateImpl<ServidorPublico, Long>
		implements ServidorPublicoDAO {

	@Override
	public ServidorPublico obtenerServidorPublicoInvolucrado(
			Long servidorPublicoId) throws NSJPNegocioException {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM ServidorPublico sp ")
					.append("WHERE sp.involucrado=")
					.append(servidorPublicoId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (ServidorPublico)query.uniqueResult();
	}

}

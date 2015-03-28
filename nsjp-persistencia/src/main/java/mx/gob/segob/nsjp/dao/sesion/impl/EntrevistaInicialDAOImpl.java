/**
* Nombre del Programa : EntrevistaInicialDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad EntrevistaInicial
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
import mx.gob.segob.nsjp.dao.sesion.EntrevistaInicialDAO;
import mx.gob.segob.nsjp.model.EntrevistaInicial;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad EntrevistaInicial.
 * @author rgama
 *
 */
@Repository
public class EntrevistaInicialDAOImpl extends
		GenericDaoHibernateImpl<EntrevistaInicial, Long> implements EntrevistaInicialDAO{

	public EntrevistaInicial consultarEntrevistaInicialPorId(EntrevistaInicial aoEntrevistaInicialDTO){
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM EntrevistaInicial ei");
		queryStr.append(" WHERE ei.sesionId = " + aoEntrevistaInicialDTO.getSesionId());		
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (EntrevistaInicial)qry.uniqueResult();
	}
	
}

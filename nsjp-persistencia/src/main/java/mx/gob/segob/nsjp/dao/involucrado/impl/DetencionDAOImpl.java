/**
* Nombre del Programa : DetencionDAOImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
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
package mx.gob.segob.nsjp.dao.involucrado.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.model.Detencion;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
@Repository
public class DetencionDAOImpl extends GenericDaoHibernateImpl<Detencion, Long>
        implements DetencionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Detencion> consultarDetencionByInvolucrado(Long involucradoId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Detencion d WHERE ")
					.append("d.involucrado=").append(involucradoId);
		Query query = super.getSession().createQuery(queryString.toString());		
		return query.list();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public Detencion consultarDetencion(Long idInvolucrado, String expediente) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT d FROM Detencion d ").
                append("WHERE d.involucrado = ").append(idInvolucrado).append(" ").
                append("AND d.fechaFinDetencion IS NULL");
        Query query = super.getSession().createQuery(sb.toString());
        return (Detencion) query.uniqueResult();
    }

}

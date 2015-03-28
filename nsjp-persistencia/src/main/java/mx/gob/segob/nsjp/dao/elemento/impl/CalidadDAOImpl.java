/**
* Nombre del Programa : CalidadDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de los metodos de acceso a datos de la entidad Calidad
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
package mx.gob.segob.nsjp.dao.elemento.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.model.Calidad;
import org.hibernate.Query;

/**
 * Implementacion de los metodos de acceso a datos de la entidad Calidad.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class CalidadDAOImpl extends GenericDaoHibernateImpl<Calidad, Long>
		implements CalidadDAO {

	@SuppressWarnings("unchecked")
    @Override
    public List<Calidad> consultarElementoXActividad(Long idActividad) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Actividad a ").
                append("INNER JOIN a.expediente e ").
                append("INNER JOIN e.elementos elems ").
                append("INNER JOIN elems.calidad c ").
                append("WHERE a = ").append(idActividad);
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
    }

}

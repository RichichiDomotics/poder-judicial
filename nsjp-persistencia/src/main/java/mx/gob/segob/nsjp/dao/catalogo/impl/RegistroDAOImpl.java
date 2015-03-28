/**
 * Nombre del Programa : RegistroDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
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
package mx.gob.segob.nsjp.dao.catalogo.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.RegistroDAO;
import mx.gob.segob.nsjp.model.Registro;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class RegistroDAOImpl extends GenericDaoHibernateImpl<Registro, Long>
        implements
            RegistroDAO {
    @Override
    public Long getNext() {
        StringBuffer queryString = new StringBuffer();

        queryString
                .append("SELECT MAX(v.registroId) ")
                .append(" FROM Registro v");

        Query query = super.getSession().createQuery(queryString.toString());
        Long max = (Long) query.uniqueResult();
        if (max == null || max.longValue()==0){
            return 1L;
        }
        return ++max;
    }
}

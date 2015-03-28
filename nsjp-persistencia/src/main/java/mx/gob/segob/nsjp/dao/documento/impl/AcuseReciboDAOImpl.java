/**
 * Nombre del Programa : AcuseReciboDAOImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
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
package mx.gob.segob.nsjp.dao.documento.impl;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.AcuseReciboDAO;
import mx.gob.segob.nsjp.model.AcuseRecibo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Repository
public class AcuseReciboDAOImpl extends GenericDaoHibernateImpl<AcuseRecibo, Long>
        implements
            AcuseReciboDAO {
	
	@Override
    public Long consultarUltimoAcuse() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select isnull(MAX(ar.documentoId),0)" +
        		" from AcuseRecibo ar");
        Query qry = super.getSession().createQuery(queryStr.toString());
        return (Long) qry.uniqueResult();
    }

}

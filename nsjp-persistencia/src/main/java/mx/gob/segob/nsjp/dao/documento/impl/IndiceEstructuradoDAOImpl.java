/**
 * Nombre del Programa : IndiceEstructuradoDAOImpl.java
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

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.IndiceEstructuradoDAO;
import mx.gob.segob.nsjp.model.IndiceEstructurado;

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
public class IndiceEstructuradoDAOImpl extends GenericDaoHibernateImpl<IndiceEstructurado, Long>
        implements IndiceEstructuradoDAO {
	
    @Override
    @SuppressWarnings("unchecked")
    public List<IndiceEstructurado> consultarIndicesEstructuradosPorTipoOficio(Long tipoOficioId) {
        StringBuffer queryString = new StringBuffer();
        queryString
                .append("SELECT ie ")
                .append("FROM IndiceEstructurado ie ");
                if(tipoOficioId != null)
        			queryString.append("WHERE ie.tipoOficio.valorId = ").append(tipoOficioId);               

        logger.info("/***** " + queryString.toString());
        Query query = super.getSession().createQuery(queryString.toString());
        return query.list();
    }
	
}

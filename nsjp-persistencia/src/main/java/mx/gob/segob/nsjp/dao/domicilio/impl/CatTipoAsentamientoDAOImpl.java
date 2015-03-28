/**
 * Nombre del Programa : CatTipoAsentamientoDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion para accesar a la entidad cattipoasentamiento
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
package mx.gob.segob.nsjp.dao.domicilio.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.CatTipoAsentamientoDAO;
import mx.gob.segob.nsjp.model.CatTipoAsentamiento;

import org.springframework.stereotype.Repository;

/**
 * Implementacion para accesar a la entidad cattipoasentamiento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class CatTipoAsentamientoDAOImpl
        extends
            GenericDaoHibernateImpl<CatTipoAsentamiento, Long>
        implements
            CatTipoAsentamientoDAO {

    @Override
    public List<CatTipoAsentamiento> consultarTodos() {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new CatTipoAsentamiento(v.catTipoAsentamientoId, v.nombre)");
        queryStr.append(" from CatTipoAsentamiento v ");
        queryStr.append(" order by v.nombre");
        @SuppressWarnings("unchecked")
        List<CatTipoAsentamiento> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

}

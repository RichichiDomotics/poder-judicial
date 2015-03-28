/**
 * Nombre del Programa : MuicipioDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Apr 2011
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
package mx.gob.segob.nsjp.dao.domicilio.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.MunicipioDAO;
import mx.gob.segob.nsjp.model.Municipio;

import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class MunicipioDAOImpl extends GenericDaoHibernateImpl<Municipio, Long>
        implements
            MunicipioDAO {

    @Override
    public List<Municipio> consultarPorEntidadFederativa(
            Long identidadFederativa) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("select new Municipio(v.municipioId, v.nombreMunicipio)");
        queryStr.append(" from Municipio v ");
        queryStr.append(" where v.entidadFederativa.entidadFederativaId = ");
        queryStr.append(identidadFederativa);
        queryStr.append(" order by v.nombreMunicipio");
        @SuppressWarnings("unchecked")
        List<Municipio> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

}

/**
 * Nombre del Programa : NotaExpedienteDAOImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 07/07/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para el DAO de NotaExpediente
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
package mx.gob.segob.nsjp.dao.expediente.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.NotaExpedienteDAO;
import mx.gob.segob.nsjp.model.NotaExpediente;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el DAO de NotaExpediente
 * 
 * @version 1.0
 * @author rgama
 *
 */
@Repository
public class NotaExpedienteDAOImpl extends GenericDaoHibernateImpl<NotaExpediente, Long> implements NotaExpedienteDAO {

    /**
     * Operación que realiza la funcionalidad de consultar las notas asociadas a un expediente.
     * Recibe el identificador del expediente.
     *
     * @param idExpediente
     * @return Devuelve un listado de notas asociadas al expediente
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<NotaExpediente> consultarNotasPorExpediente(Long idExpediente) {
    	List<NotaExpediente> notas = new ArrayList<NotaExpediente>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(" from NotaExpediente o where o.expediente.expedienteId  = ");
        queryString.append(idExpediente);
        logger.debug("Query:" + queryString);
        Query query = super.getSession().createQuery(queryString.toString());
        notas = query.list();
        logger.debug("Total de notas recuperadas:" + notas.size() + " asociadas al expediente "+ idExpediente);
        return notas;
    }

  
}

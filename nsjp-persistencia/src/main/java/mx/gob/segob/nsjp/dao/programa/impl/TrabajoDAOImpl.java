/**
* Nombre del Programa : TrabajoDAOImpl.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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
package mx.gob.segob.nsjp.dao.programa.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.programa.TrabajoDAO;
import mx.gob.segob.nsjp.model.Trabajo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de Trabajo
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public class TrabajoDAOImpl extends GenericDaoHibernateImpl<Trabajo, Long> implements TrabajoDAO {

	/**
	 * M&eacute;todo que consulta un trabajo por id
	 * @return Trabajo
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public Trabajo consultarTrabajoPorId(Trabajo trabajo)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM Trabajo rev ");
        hqlQuery.append(" WHERE Trabajo_id = ").append(trabajo.getTrabajoId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<Trabajo> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
        }
        
        return null;
    }
}

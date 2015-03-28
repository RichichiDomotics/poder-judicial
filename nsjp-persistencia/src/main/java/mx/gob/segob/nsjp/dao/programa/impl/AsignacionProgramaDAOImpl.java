/**
* Nombre del Programa : AsignacionProgramaDAOImpl.java
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
import mx.gob.segob.nsjp.dao.programa.AsignacionProgramaDAO;
import mx.gob.segob.nsjp.model.AsignacionPrograma;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de AsignacionPrograma
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public class AsignacionProgramaDAOImpl extends GenericDaoHibernateImpl<AsignacionPrograma, Long> implements AsignacionProgramaDAO {

	/**
	 * M&eacute;todo que consulta una AsignacionPrograma por id
	 * @return AsignacionPrograma
	 * @throws NSJPNegocioException
	 */
	@SuppressWarnings("unchecked")
	public AsignacionPrograma consultarAsignacionProgramaPorId(AsignacionPrograma asignacionPrograma)throws NSJPNegocioException{
    	StringBuffer hqlQuery = new StringBuffer();
        hqlQuery.append("FROM AsignacionPrograma rev ");
        hqlQuery.append(" WHERE AsignacionPrograma_id = ").append(asignacionPrograma.getAsignacionProgramaId()).append(" ");
        Query query = super.getSession().createQuery(hqlQuery.toString());

        logger.debug("query :: " + query);
        List<AsignacionPrograma> resp = query.list();
        logger.debug("resp.size() :: " + resp.size());
        if(!resp.isEmpty()){
        	return resp.get(0);
        }
        
        return null;
    }
}

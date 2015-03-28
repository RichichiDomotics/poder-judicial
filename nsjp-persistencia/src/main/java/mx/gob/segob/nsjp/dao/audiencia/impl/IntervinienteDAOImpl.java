/**
* Nombre del Programa : IntervinienteDAOImpl.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/10/2011
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
package mx.gob.segob.nsjp.dao.audiencia.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;

import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Interviniente;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
@Repository
public class IntervinienteDAOImpl extends GenericDaoHibernateImpl<Interviniente, Long>implements IntervinienteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Interviniente> consultarIntervinientePorAudiencia(Long idAudiencia) throws NSJPNegocioException{

		final StringBuffer qryStr = new StringBuffer();
		qryStr.append("from Interviniente as i");
		qryStr.append(" where i.audiencia = ");
		qryStr.append(idAudiencia);
		logger.debug("qryStr :: " + qryStr);

		return super.getHibernateTemplate().find(qryStr.toString());

	}
	

	
}

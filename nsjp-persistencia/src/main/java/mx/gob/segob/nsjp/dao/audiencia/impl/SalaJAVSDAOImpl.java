/**
* Nombre del Programa : SalaJAVSDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/11/2011
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

import mx.gob.segob.nsjp.dao.audiencia.SalaJAVSDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.SalaJAVS;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación para el acceso a datos de las sala de JAVS.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class SalaJAVSDAOImpl 
	extends
		GenericDaoHibernateImpl<SalaJAVS, Long>
	implements 
		SalaJAVSDAO {

	@Override
	public SalaJAVS recuperarSalaJAVS(Long salaAudienciaId) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT sj ");
		queryStr.append("FROM SalaJAVS sj where sj.salaAudiencia.salaAudienciaId = ");
		queryStr.append(salaAudienciaId);

		Query qry = super.getSession().createQuery(queryStr.toString());
		SalaJAVS salaJAVSRetorno  =  (SalaJAVS) qry.uniqueResult();
		
		return salaJAVSRetorno;
	}	

}

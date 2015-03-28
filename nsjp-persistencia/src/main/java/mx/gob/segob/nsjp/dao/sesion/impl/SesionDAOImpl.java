/**
* Nombre del Programa : SesionDAOImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Sesion
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
package mx.gob.segob.nsjp.dao.sesion.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.sesion.SesionDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Sesion;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad Sesion.
 * @author rgama
 *
 */
@Repository
public class SesionDAOImpl extends
		GenericDaoHibernateImpl<Sesion, Long> implements SesionDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Sesion> consultarSesionesPorIdNumeroExpediente(
			Long aoNumeroExpedienteId) {		
		final StringBuffer queryStr = new StringBuffer();		
		queryStr.append(" FROM Sesion s");
		queryStr.append(" WHERE s.numeroExpediente.numeroExpedienteId=" + aoNumeroExpedienteId);		
		Query qry = super.getSession().createQuery(queryStr.toString());
		final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("fecha")) {
                queryStr.append(" order by ");
                queryStr.append("s.fechaSesion");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("sesion")) {
                queryStr.append(" order by ");
                queryStr.append("s.numeroSesion");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);
	}
	
	
	public Long obtenerSiguinteNumeroDeSesionPorNumeroExpediente(Long numeroExpedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT COUNT(*) FROM Sesion s ")
					.append("WHERE s.numeroExpediente.numeroExpedienteId=").append(numeroExpedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		Long maximo = (Long) query.uniqueResult();
		if (maximo == null)
			return 1L;
		else
		return (maximo + 1);
	}


}

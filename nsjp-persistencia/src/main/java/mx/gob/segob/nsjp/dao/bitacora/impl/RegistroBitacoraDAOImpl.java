/**
* Nombre del Programa  : RegistroBitacoraDAOImpl.java
* Autor                : CuauhtemocPS
* Compania             : Ultrasist
* Proyecto             : NSJP                    Fecha: 31/08/2011
* Marca de cambio      : N/A
* Descripcion General  : Implementación del DAO para Registrar en la bitacora.
* Programa Dependiente :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion   :N/A
* Dias de ejecucion    :N/A                      Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                :N/A
* Compania             :N/A
* Proyecto             :N/A                      Fecha: N/A
* Modificacion         :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.bitacora.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.model.RegistroBitacora;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del DAO para Registro en la bitacora.
 * @version 1.0
 * @author CuauhtemocPS
 */
@Repository
public class RegistroBitacoraDAOImpl extends GenericDaoHibernateImpl<RegistroBitacora, Long>
		implements RegistroBitacoraDAO {

	@Override
	public Short obtenerUltimoFolioBitacora(RegistroBitacora bitacora) throws NSJPNegocioException {
		 
				final StringBuffer query = new StringBuffer();
				query.append(" SELECT MAX(r.consecutivo) ");
				query.append(" FROM RegistroBitacora r ");
				query.append(" WHERE r.numeroExpediente = ").append(bitacora.getNumeroExpediente().getNumeroExpedienteId());
				query.append(" and r.tipoMovimiento = ").append(bitacora.getTipoMovimiento().getValorId());
				logger.debug("query :: " + query);
				Query hbq = super.getSession().createQuery(query.toString());
				return (Short) hbq.uniqueResult();
		
	}

    @Override
    public List<RegistroBitacora> consultarByNumeroExpedienteId(
            Long numeroExpedienteId) {

        final StringBuffer query = new StringBuffer();
        query.append(" select new RegistroBitacora(r.registroBitacoraId, tm.valorId, tm.valor, r.fechaInicio, r.nuevo, r.consecutivo) ");
        query.append(" FROM RegistroBitacora r inner join r.tipoMovimiento tm inner join r.numeroExpediente ne");
        query.append(" where ne.numeroExpedienteId = ");
        query.append(numeroExpedienteId);
        query.append(" order by tm.valorId, r.consecutivo");
        logger.debug("query :: " + query);
        Query hbq = super.getSession().createQuery(query.toString());
        return hbq.list();

    }

}

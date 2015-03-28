/**
 * Nombre del Programa : ConfActividadDocumentoDAOImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.actividad.impl;

import java.util.List;
import mx.gob.segob.nsjp.dao.actividad.ConfActividadDocumentoDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.ConfActividadDocumento;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
public class ConfActividadDocumentoDAOImpl
    extends GenericDaoHibernateImpl<ConfActividadDocumento, Long>
    implements ConfActividadDocumentoDAO {

	@SuppressWarnings("unchecked")
    @Override
    public List<ConfActividadDocumento> consultarConfActividadDocumento(
            Funcionario funcionario, NumeroExpediente numeroExpediente, Long idCategoriaActidad) {
        // Debemos consultar el estado del expediente.
        Valor estatus = numeroExpediente.getEstatus();
        JerarquiaOrganizacional area = funcionario.getArea();
        if (estatus == null) {
            throw new IllegalStateException("No es posible consulta conActivid"
                    + "adDocumento para el expediente = " + numeroExpediente.getNumeroExpediente() +
                    " Se requiere que el expediente tenga un estatus asociado");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("estatus = " + estatus);
            if (estatus != null) {
                logger.debug("estatus.getValorId() = " + estatus.getValorId());
            }
            logger.debug("funcionario.getArea() = " +
                    area);
        }
        // Buscamos los registros que tengan grupoActividad = estatus
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT conf FROM ConfActividadDocumento conf ").
                append("WHERE conf.grupoActividad = ").append(estatus.getValorId()).append(" ").
                append("AND conf.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ").append(area.getJerarquiaOrganizacionalId()).                
        		append(" AND conf.muestraEnCombo = 1");
        if(idCategoriaActidad!= null)
        	sb.append(" AND conf.categoriaActividad = ").append( idCategoriaActidad);
        
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
    }

    @Override
    public ConfActividadDocumento consultaConfActividadDocumentoPorIdActividad(Long idTipoActividad) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT conf FROM ConfActividadDocumento conf ").
                append("WHERE conf.tipoActividad.valorId = :idTipoActividad");
        Query q = getSession().createQuery(sb.toString());
        q.setParameter("idTipoActividad", idTipoActividad);
        return (ConfActividadDocumento) q.uniqueResult();
    }
   
    @Override
    public List<Valor> consultarEstadosDistintosPorJerarquiaOrganizacional(Long idJerarquiaOrganizacional) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT(estadoCambioExpediente)  FROM ConfActividadDocumento conf ").
                append("WHERE conf.jerarquiaOrganizacional.jerarquiaOrganizacionalId = ").
                append(idJerarquiaOrganizacional);
        Query q = getSession().createQuery(sb.toString());
        return q.list();
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<ConfActividadDocumento> consultarPorTipoActFormaAndJerarquia(
			Long idTipoActividad, Long idTipoForma, Long jeraquia) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM ConfActividadDocumento cad")
					.append(" WHERE cad.tipoActividad=").append(idTipoActividad)
					.append(" AND cad.forma=").append(idTipoForma)
					.append(" AND cad.jerarquiaOrganizacional=").append(jeraquia);
		Query query = super.getSession().createQuery(queryString.toString());		
		return query.list();
	}
}

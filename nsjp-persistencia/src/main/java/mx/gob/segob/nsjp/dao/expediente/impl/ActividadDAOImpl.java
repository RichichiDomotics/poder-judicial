/**
 * Nombre del Programa : ActividadDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 May 2011
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
package mx.gob.segob.nsjp.dao.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Documento;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class ActividadDAOImpl extends GenericDaoHibernateImpl<Actividad, Long>
		implements ActividadDAO {

	@Override
	public Actividad obtenerActividadActual(Long idExpediente) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select a ");
		queryStr.append(" from Actividad a where a.actividadId =");
		queryStr.append(" (select MAX(amax.actividadId) from Actividad amax");
		queryStr.append(" where amax.expediente.expedienteId = ");
		queryStr.append(idExpediente);
		queryStr.append(")");
		queryStr.append(" and a.expediente.expedienteId = ");
		queryStr.append(idExpediente);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (Actividad) qry.uniqueResult();
	}

	@Override
	public Documento consultarDocumentosXExpediente(Long expedienteId,
			Long tipoDocumento) {
		final StringBuffer queryStr = new StringBuffer();

		queryStr.append("SELECT a.documento");
		queryStr.append(" FROM Actividad a");
		queryStr.append(" WHERE a.expediente=" + expedienteId);
		if (tipoDocumento != null)
			queryStr.append(" AND a.documento.tipoDocumento=" + tipoDocumento);

		Query qry = super.getSession().createQuery(queryStr.toString());

		return (Documento) qry.uniqueResult();
	}

	@Override
	public Actividad consultarActividadXExpedienteYDocumento(Long expediente,
			Long documentoId) {
		final StringBuffer queryStr = new StringBuffer();

		queryStr.append(" FROM Actividad a");
		queryStr.append(" WHERE 1=1");
		if(expediente!=null)
		queryStr.append(" AND a.expediente=" + expediente);
		if(documentoId!=null)
		queryStr.append(" AND a.documento.documentoId=" + documentoId);

		Query qry = super.getSession().createQuery(queryStr.toString());

		return (Actividad) qry.uniqueResult();
	}
	@Override
	public Long create(Actividad o) {
	    logger.debug("Creando actividad para el expediente :: " +  o.getExpediente().getExpedienteId());
	    return super.create(o);
	}
	
	public Actividad consultarActividadPorExpedienteIdTipoActividad(Long idExpediente, Long tipoActividad) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select a ");
		queryStr.append(" from Actividad a where a.actividadId =");
		queryStr.append(" (select MAX(amax.actividadId) from Actividad amax");
		queryStr.append(" where amax.expediente.expedienteId = ");
		queryStr.append(idExpediente);
		queryStr.append( " AND amax.tipoActividad.valorId = ");
		queryStr.append(tipoActividad);
		queryStr.append(")");
		queryStr.append(" and a.expediente.expedienteId = ");
		queryStr.append(idExpediente);
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (Actividad) qry.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Actividad> consultarActividadesPorTipoActividadExpedienteId(Long idExpediente, List<Long> idTipoActividades, boolean documentoRec){
		final StringBuffer queryStr = new StringBuffer();
		String cadenaIdTA = "";
		if (idTipoActividades != null && !idTipoActividades.isEmpty())
			cadenaIdTA = idTipoActividades.toString().substring(1,
					idTipoActividades.toString().length() - 1);

		queryStr.append("select a ");
		queryStr.append(" from Actividad a where 1=1 ");
		if(idExpediente!=null && idExpediente > 0){
			queryStr.append(" AND a.expediente.expedienteId = ");
			queryStr.append(idExpediente);
		}

		if (!cadenaIdTA.trim().isEmpty()) {
			queryStr.append(" AND a.tipoActividad.valorId in ( ");
			queryStr.append(cadenaIdTA);
			queryStr.append(" ) ");
		}
		
		if(documentoRec){
			queryStr.append(" AND a.documento is not null ");
		}
			
		Query qry = super.getSession().createQuery(queryStr.toString());
		return (List<Actividad>) qry.list();
	}

	@Override
	public Long consultarNumeroActividadesPorTipoAtencionExpedienteId(
			Long expedienteId, Long tipoAtencionId) {
		final StringBuffer queryStr = new StringBuffer();
		
		queryStr.append("select count(actv.actividadId)");
		queryStr.append(" from Valor ate, Valor act, Valor tipate, Actividad actv");
		queryStr.append(" where ate.registro.registroId = act.registro.registroId");
		queryStr.append(" and ate.valor = convert(varchar(20), tipate.valorId )");
		queryStr.append(" and act.valorId = actv.tipoActividad.valorId");
		queryStr.append(" and tipate.campoCatalogo = 187");
		queryStr.append(" and act.campoCatalogo = 69");
		queryStr.append(" and ate.campoCatalogo = 169");
		queryStr.append(" and actv.expediente.expedienteId = " + expedienteId);
		queryStr.append(" and tipate.valorId = " + tipoAtencionId);
			
		Query qry = super.getSession().createQuery(queryStr.toString());

		return (Long) qry.uniqueResult();
	}
}

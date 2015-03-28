/**
 * Nombre del Programa : NotificacionDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Acceso a Datos para la notificacion
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
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Objeto de Acceso a Datos para la notificacion.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class NotificacionDAOImpl
        extends GenericDaoHibernateImpl<Notificacion, Long>
        implements
        NotificacionDAO {

    @Override
    public List<Notificacion> consultarNotificacionesPorAudienciaInvolucrado(
            Long audienciaId, Long invoId) {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("from Notificacion n");
//        queryStr.append(" where n.involucradoAudiencia.id.audienciaId = ");
        queryStr.append(" where n.audiencia.audienciaId = ");
        queryStr.append(audienciaId);
//        queryStr.append(" and n.involucradoAudiencia.id.involucradoId = ");
        queryStr.append(" and n.involucrado.elementoId = ");
        queryStr.append(invoId);
        queryStr.append(" order by fechaCreacion desc");
        @SuppressWarnings("unchecked")
        List<Notificacion> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {

            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Notificacion> consultarNotificacionesXUsuario(Funcionario funcionario,
            Valor estatus, int numeroDePagina, int tamanoDePagina,
            String campoOrden, int direccionOrden) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT n FROM Notificacion n ");
        consultaComunNotificacion(sb, funcionario, estatus);
        sb.append("ORDER BY ");
        // orden por el que vamos a regresar la consulta
        if (campoOrden.equals("numeroCaso")) {
            sb.append("c.numeroGeneralCaso ");
//        } else if (campoOrden.equals("numeroExpediente")) {
//            sb.append("nes.numeroExpediente ");
//        } else if (campoOrden.equals("asunto")) {
//            sb.append("n.motivo ");
        } else if (campoOrden.equals("fechaElaboracion")) {
            sb.append("n.fechaCreacion ");
        }else{
            if (logger.isDebugEnabled()) {
                logger.debug("no se ha ordenando por ninguna columna valida = " + "");
                logger.debug("campoOrden = " + campoOrden);
            }
            sb.append("e.caso.numeroGeneralCaso ");
        }
        if (direccionOrden == 0) {
            sb.append("DESC ");
        }else{
            sb.append("ASC ");
        }
        Query query = getSession().createQuery(sb.toString());
        query = query.setFirstResult(tamanoDePagina * (numeroDePagina - 1));
        query.setMaxResults(tamanoDePagina);
        return query.list();
    }

    @Override
    public long consultarNumeroTotalDeNotificacionesXUsuario(Funcionario funcionario, Valor estatus) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(n) FROM Notificacion n ");
        consultaComunNotificacion(sb, funcionario, estatus);
        Query query = getSession().createQuery(sb.toString());
        return (Long)query.uniqueResult();
    }

    private void consultaComunNotificacion(StringBuilder sb, Funcionario funcionario, Valor estatus) {
        //sb.append("INNER JOIN  n.involucradoAudiencia ia ").
//                append("INNER JOIN ia.involucrado i ").
    	sb.append("INNER JOIN n.involucrado i "). 
                append("INNER JOIN e.caso c ").
                append("WHERE n.responsableDocumento = ").
                append(funcionario.getClaveFuncionario()).append(" ").
                append("AND n.estatus = ").append(estatus.getValorId()).append(" ");
//                append("INNER JOIN nes.funcionario f ").
//                append("AND nes.numeroExpediente IS NOT NULL ").
//                append("AND f = ").
//                append(funcionario.getClaveFuncionario()).append(" ").
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Notificacion> consultarNotificacionesAlmacen(Almacen almacen,
            long tipoMovimiento, long estadoNotificacion) {
        // TODO Jacob. Falta implementar la funcionalidad
        return Collections.EMPTY_LIST;
    }

	
    @Override
	public String obtenerUltimoFolioNotificacion() throws NSJPNegocioException {
		final StringBuffer query = new StringBuffer();
		query.append("SELECT MAX(n.folioNotificacion) ");
		query.append("FROM Notificacion n ");
		query.append("WHERE n.confInstitucion.esInstalacionActual = true  ");
		query.append("and n.folioNotificacion like '%"+Calendar.getInstance().get(Calendar.YEAR)+"%'");
		logger.debug("query :: " + query);
		Query hbq = super.getSession().createQuery(query.toString());
		return (String) hbq.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> consultarNotificacionesXFuncionario(
            Long claveFuncionario, Long estatus) {
        final StringBuffer strQuery = new StringBuffer();

        strQuery.append(" FROM Notificacion n");
        strQuery.append(" WHERE n.funcionario.claveFuncionario=");
        strQuery.append(claveFuncionario);
        if (estatus != null) {
            strQuery.append(" AND n.estatus=");
            strQuery.append(estatus);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("strQuery :: " + strQuery);
        }
        Query query = super.getSession().createQuery(strQuery.toString());
        return query.list();
    }

	@Override
	public Long obtenerMaximaNotificacion() {
		final StringBuffer strQuery = new StringBuffer();
		
		strQuery.append(" SELECT COUNT(*) from Notificacion");
		
		Query query = super.getSession().createQuery(strQuery.toString());
		return (Long) query.uniqueResult();
	}

	@Override
	public List<Notificacion> consultarNotificacionesPorAudienciaFuncionario(
            Long id, Long claveFuncionario) throws NSJPNegocioException {
        final StringBuffer queryStr = new StringBuffer();
        queryStr.append("from Notificacion n");
        queryStr.append(" where n.audiencia.audienciaId = ");
        queryStr.append(id);
        queryStr.append(" and n.funcionario.claveFuncionario = ");
        queryStr.append(claveFuncionario);
        //queryStr.append(" order by fechaCreacion desc");
        @SuppressWarnings("unchecked")
        List<Notificacion> resp = super.getHibernateTemplate().find(
                queryStr.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("resp.size() :: " + resp.size());
        }
        return resp;
    }
}

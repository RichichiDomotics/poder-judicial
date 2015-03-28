/**
 * Nombre del Programa : TurnoDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 4 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Turno
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Turno;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de metodos de acceso a datos de la entidad Turno.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class TurnoDAOImpl extends GenericDaoHibernateImpl<Turno, Long>
        implements
            TurnoDAO {

    @Override
    public String obtenerUltimoNumero(String tipoTurno, Boolean urgente,
            Date fecha, long discriminanteId) {
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        StringBuffer queryString = new StringBuffer();
        
        if(discriminanteId == 0L){
        	queryString.append("SELECT t.numeroTurno ").append("FROM Turno t ")
            .append("WHERE t.turnoId = ")
            .append("(SELECT MAX(tu.turnoId) ")
            .append("FROM Turno tu WHERE ")
            .append("CONVERT (nvarchar, tu.fechaAtencion, 112)=")
            .append(formato.format(fecha));
		    if (tipoTurno.equals(TipoTurno.ADMINISTRATIVO.toString()))
		        queryString.append(" AND tu.tipoTurno = :tipoTurno)");
		    else
		        queryString
		                .append(" AND tu.tipoTurno = :tipoTurno AND tu.esUrgente = :urgente)");
		
		    Query query = super.getSession().createQuery(queryString.toString());
		    query.setParameter("tipoTurno", tipoTurno);
		    if (!tipoTurno.equals(TipoTurno.ADMINISTRATIVO.toString()))
		        query.setParameter("urgente", urgente);
		    return (String) query.uniqueResult();
        }
        //Si la institucion es PGJ
        else{

        	queryString.append("SELECT t.numeroTurno ").append("FROM Turno t ")
            .append("WHERE t.turnoId = ")
            .append("(SELECT MAX(tu.turnoId) ")
            .append("FROM Turno tu WHERE ")
            .append("CONVERT (nvarchar, tu.fechaAtencion, 112)=")
            .append(formato.format(fecha));
		    if (tipoTurno.equals(TipoTurno.ADMINISTRATIVO.toString()))
		        queryString.append(" AND tu.tipoTurno = :tipoTurno");
		    else
		        queryString.append(" AND tu.tipoTurno = :tipoTurno AND tu.esUrgente = :urgente");
		    
		    //Consulta el ultimo turno para la agencia actual
		    queryString.append(" AND tu.discriminante.catDiscriminanteId=")
		    .append(discriminanteId);
		    queryString.append(")");
		
		    Query query = super.getSession().createQuery(queryString.toString());
		    query.setParameter("tipoTurno", tipoTurno);
		    if (!tipoTurno.equals(TipoTurno.ADMINISTRATIVO.toString()))
		        query.setParameter("urgente", urgente);
		    return (String) query.uniqueResult();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Turno> obtenerTurnosAtencionPorIdUsuario(Long idUsuario,
            Date today, TipoTurno tTurno,Long discriminanteId) {
        final StringBuffer queryStr = new StringBuffer();
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

        queryStr.append(" FROM Turno e ")
                .append(" WHERE 1=1 ");
        queryStr.append(" AND e.usuario.usuarioId = ").append(idUsuario);
        if(today != null)
        	queryStr.append(" AND CONVERT (nvarchar, e.fechaAtencion, 112) = ")
                .append(formato.format(today));

        if (tTurno != null) {
            queryStr.append(" AND e.tipoTurno = '");
            queryStr.append(tTurno.name()).append("'");
        }
        	queryStr.append(" AND e.discriminante.catDiscriminanteId=").append(discriminanteId);
        	queryStr.append(" AND e.expediente.expedienteId is not null ");

        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
            if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append("e.expediente");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("2")) {
                queryStr.append(" order by ");
                queryStr.append("e.fechaAtencion");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);
    }

  

    @Override
    public Turno obtenerProximoTurnoAtencion(String tipoturno, Boolean urgente,
            Date dia, long discriminanteId) {
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        StringBuffer queryString = new StringBuffer();
        
        if(discriminanteId == 0L){
        	 queryString
             .append("SELECT o FROM Turno o WHERE o.turnoId = (SELECT MIN(t.turnoId) ")
             .append(" FROM Turno t")
             .append(" Where t.tipoTurno = :tipoTurno")
             .append(" AND t.estatus.valorId=")
             .append(EstatusTurno.ESPERA.getValorId())
             .append(" AND CONVERT (nvarchar, t.fechaAtencion, 112)=")
             .append(formato.format(dia));
		     if (urgente)
		         queryString.append(" AND t.esUrgente=").append(urgente).append(")");
		     else
		         queryString.append(")");
		     Query qry = super.getSession().createQuery(queryString.toString());
		     qry.setParameter("tipoTurno", tipoturno);
		     return (Turno) qry.uniqueResult();
        }
        else{
       	 queryString
            .append("SELECT o FROM Turno o WHERE o.turnoId = (SELECT MIN(t.turnoId) ")
            .append(" FROM Turno t")
            .append(" Where t.tipoTurno = :tipoTurno")
            .append(" AND t.estatus.valorId=")
            .append(EstatusTurno.ESPERA.getValorId())
            .append(" AND CONVERT (nvarchar, t.fechaAtencion, 112)=")
            .append(formato.format(dia));
       	 	
       	 	//Consulta el ultimo turno para la agencia actual
		    queryString.append(" AND t.discriminante.catDiscriminanteId=")
		    .append(discriminanteId);
		    
		     if (urgente)
		         queryString.append(" AND t.esUrgente=").append(urgente).append(")");
		     else
		         queryString.append(")");
		     Query qry = super.getSession().createQuery(queryString.toString());
		     qry.setParameter("tipoTurno", tipoturno);
		     return (Turno) qry.uniqueResult();
       }
       
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.dao.expediente.TurnoDAO#obtenerTurnosPendientesPorTipo
     * (mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno)
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Turno> obtenerTurnosPendientesPorTipo(TipoTurno tipo) {
        return getHibernateTemplate().find(
                "from Turno t where t.tipoTurno = ? order by t.turnoId",
                tipo.name());
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Turno> obtenerTurnosPorFiltro(Turno turnoFiltro) {
    	
    	final StringBuffer queryStr = new StringBuffer();
        final SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

        queryStr.append(" FROM Turno t WHERE 1=1 ");
        if(turnoFiltro.getTurnoId()!= null )
        	queryStr.append(" AND t.turnoId = ").append(turnoFiltro.getTurnoId());
        
        if(turnoFiltro.getExpediente()!= null && turnoFiltro.getExpediente().getExpedienteId()!=null )
        	queryStr.append(" AND t.expediente.expedienteId = ").append(turnoFiltro.getExpediente().getExpedienteId());
        
//        if(turnoFiltro.getUsuario()!= null && turnoFiltro.getUsuario().getUsuarioId()!=null )
//        	queryStr.append(" AND t.usuario.usuarioId = ").append(turnoFiltro.getUsuario().getUsuarioId());
        
        //Usado para distritos
        if (turnoFiltro.getUsuario() != null
        		&& turnoFiltro.getUsuario().getFuncionario() != null
        		&& turnoFiltro.getUsuario().getFuncionario().getDiscriminante() != null
        		&& turnoFiltro.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {
        	
        	queryStr.append(" AND t.discriminante.catDiscriminanteId=").append(turnoFiltro.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId());        	   
        }
        
        if(turnoFiltro.getNumeroTurno()!= null && !turnoFiltro.getNumeroTurno().trim().isEmpty() )
        	queryStr.append(" AND t.numeroTurno = ").append(turnoFiltro.getNumeroTurno());
        
        if(turnoFiltro.getTipoTurno()!= null && !turnoFiltro.getTipoTurno().trim().isEmpty() )
        	queryStr.append(" AND t.tipoTurno = '").
        		append(turnoFiltro.getTipoTurno()).append("'");
                
        if(turnoFiltro.getEsUrgente()!= null)
        	queryStr.append(" AND t.esUrgente = ").append(turnoFiltro.getEsUrgente());
        
        if(turnoFiltro.getFechaAtencion()!= null )
        	queryStr.append(" AND CONVERT (nvarchar, t.fechaAtencion, 112) = ")
                .append(formato.format(turnoFiltro.getFechaAtencion()));
        
        if(turnoFiltro.getEstatus()!= null && turnoFiltro.getEstatus().getValorId()!=null)
        	queryStr.append(" AND t.estatus.valorId = ").append(turnoFiltro.getEstatus().getValorId());
        	
        final PaginacionDTO pag = PaginacionThreadHolder.get();
        logger.debug("pag :: " + pag);
        if (pag != null && pag.getCampoOrd() != null) {
        	if (pag.getCampoOrd().equals("1")) {
                queryStr.append(" order by ");
                queryStr.append(" t.turnoId");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("2")) {
                queryStr.append(" order by ");
                queryStr.append(" t.expediente");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("3")) {
                queryStr.append(" order by ");
                queryStr.append(" t.usuario");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("4")) {
                queryStr.append(" order by ");
                queryStr.append(" t.numeroTurno");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("5")) {
                queryStr.append(" order by ");
                queryStr.append(" t.tipoTurno");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("6")) {
                queryStr.append(" order by ");
                queryStr.append(" t.esUrgente");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("7")) {
                queryStr.append(" order by ");
                queryStr.append(" t.fechaAtencion");
                queryStr.append(" ").append(pag.getDirOrd());
            }
            if (pag.getCampoOrd().equals("8")) {
                queryStr.append(" order by ");
                queryStr.append(" t.estatus");
                queryStr.append(" ").append(pag.getDirOrd());
            }
        }

        return super.ejecutarQueryPaginado(queryStr, pag);
    }
}

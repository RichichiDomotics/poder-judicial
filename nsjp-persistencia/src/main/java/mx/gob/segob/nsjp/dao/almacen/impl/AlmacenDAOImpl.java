/**
 * Nombre del Programa : AlmacenDAOImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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
package mx.gob.segob.nsjp.dao.almacen.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Caso;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
public class AlmacenDAOImpl
        extends GenericDaoHibernateImpl<Almacen, Long>
        implements AlmacenDAO {

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
    @Override
    public List<Almacen> consultarAlmacenesPorTipo(Long idTipoAlmacen, Boolean estatus, Caso caso) {
		final StringBuffer queryString = new StringBuffer();
        if (caso != null) {
        	queryString.append("SELECT a FROM Caso c ").
                    append("INNER JOIN c.expedientes exs ").
                    append("INNER JOIN exs.elementos elems ").
                    append("INNER JOIN elems.almacen a ").
                    append("WHERE a.estatusActivo = ").append(estatus).append(" ").
                    append("AND c.numeroGeneralCaso  = ").append(caso.getNumeroGeneralCaso());
        } else {
        	queryString.append("SELECT a FROM Almacen a").
                    append(" WHERE a.estatusActivo = ").append(estatus).
                    append(" ");
        }
        if(idTipoAlmacen != null){
        	if (idTipoAlmacen == 0) {
            	queryString.append(" AND a.esVirtual = ").append(Boolean.FALSE);
            } else {
            	queryString.append(" AND a.esVirtual = ").append(Boolean.TRUE);
            }
        }
        
       
        final PaginacionDTO pag = PaginacionThreadHolder.get();

		logger.debug("pag :: " + pag);
		if (pag != null && pag.getCampoOrd() != null) {
			if (pag.getCampoOrd().equals("1")) {
				queryString.append(" order by ");
				queryString.append("a.esVirtual");
				queryString.append(" ").append(pag.getDirOrd());
			}
			if (pag.getCampoOrd().equals("2")) {
				queryString.append(" order by ");
				queryString.append("a.nombreAlmacen");
				queryString.append(" ").append(pag.getDirOrd());
			}
			if (pag.getCampoOrd().equals("3")) {
				queryString.append(" order by ");
				queryString.append("a.descripcion");
				queryString.append(" ").append(pag.getDirOrd());
			}
		}
		return super.ejecutarQueryPaginado(queryString, pag);   
    }

    @Override
    public boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodia cadenaDeCustodia,
            Almacen almacen) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT cc FROM Almacen a").
                append(" INNER JOIN a.objetos os").
                append(" INNER JOIN os.evidencia e").
                append(" INNER JOIN e.cadenaDeCustodia cc").
                append(" WHERE cc.folio = :folio").
                append(" AND a = ").append(almacen.getIdentificadorAlmacen());
        Query q = getSession().createQuery(sb.toString());
        q.setParameter("folio", cadenaDeCustodia.getFolio());
        return q.uniqueResult() != null;
    }

	@Override
	public Almacen obtenerAlmacenByNumExpediente(Long numeroExpedienteId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Almacen a WHERE ")
					.append("a.numeroExpediente=")
					.append(numeroExpedienteId);
		Query query = super.getSession().createQuery(queryString.toString());
		return (Almacen) query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Almacen> consultarAlmacenesExpedienteByEstatus(Boolean estatus, Boolean tipo) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Almacen a WHERE ")
					.append("a.estatusActivo=")
					.append(estatus).append(" AND a.resguardaEvidencias=")
					.append(Boolean.FALSE).append(" AND a.esVirtual=").append(tipo);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
	
	@Override
	public Almacen consultarDetalleAlmacenPorId (Long almacenId){
		Almacen resp = new Almacen();
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Almacen a WHERE ")
					.append("a.identificadorAlmacen=")
					.append(almacenId);
		Query query = super.getSession().createQuery(queryString.toString());
		resp= (Almacen) query.uniqueResult();
		
		
		return resp;
	}
}

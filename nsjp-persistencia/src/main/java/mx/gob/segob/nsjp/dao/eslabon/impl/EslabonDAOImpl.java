/**
 * Nombre del Programa : EslabonDAOImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
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
package mx.gob.segob.nsjp.dao.eslabon.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
public class EslabonDAOImpl
    extends GenericDaoHibernateImpl<Eslabon, Long>
    implements EslabonDAO {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public Eslabon consultaUltimoEslabonXEvidencia(Evidencia evidencia) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM Eslabon e ").
                append("WHERE e.evidencia = ").append(evidencia.getEvidenciaId()).
                append("ORDER BY e.numeroEslabon DESC ");
        Query query = super.getSession().createQuery(sb.toString());
        List<Eslabon> eslabones = query.list();
        Eslabon ultimo = null;
        if(eslabones != null && !eslabones.isEmpty()){
            ultimo = eslabones.get(0);
        }
        return ultimo;
    }
    
    @SuppressWarnings("unchecked")
	public List<Eslabon> consultaEslabonesPorEvidencia(Long idEvidencia) {
        StringBuilder sb = new StringBuilder();
        sb.append(" FROM Eslabon e ").
                append("WHERE e.evidencia = ").append(idEvidencia).
                append("ORDER BY e.eslabonId DESC ");
        logger.info("query_consultarDocumentosXEslabonesDEvidencia : "+sb.toString());
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> consultarDocumentosXEslabonesDEvidencia(
			Long evidenciaId) {
		StringBuilder sb = new StringBuilder();
		
        sb.append("SELECT e.documento FROM Eslabon e ")
          .append(" WHERE e.evidencia="+evidenciaId);
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Evidencia> consultarEvidenciasNoDevueltas(
			Long identificadorAlmacen, Long estatusEv, Date date) {
		StringBuilder sb = new StringBuilder();
		
        sb.append("SELECT e.evidencia FROM Eslabon e ")
          .append(" WHERE e.evidencia.objeto.almacen="+identificadorAlmacen)
          .append(" AND e.evidencia.estatus="+estatusEv)
          .append(" AND CONVERT (varchar, e.fechaRecibe, 112) < "+DateUtils.formatearBD(date));
        
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
	}
   
}

/**
* Nombre del Programa : CatRelacionDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de los metodos de acceso a datos de CatRelacion
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
package mx.gob.segob.nsjp.dao.relacion.impl;

import java.util.List;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.relacion.CatRelacionDAO;
import mx.gob.segob.nsjp.model.CatRelacion;
import org.hibernate.Query;

/**
 * Implementacion de los metodos de acceso a datos de CatRelacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class CatRelacionDAOImpl extends GenericDaoHibernateImpl<CatRelacion, Long>
		implements CatRelacionDAO {

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
    @Override
    public List<CatRelacion> consultarRelacionesXCategoria(Long idCategoriaRelacion) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT cr FROM CatRelacion cr ").append(""
                + "WHERE cr.catCategoriaRelacion = ").append(idCategoriaRelacion);
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
    }

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
    @Override
    public List<CatRelacion> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacion catCategoriaRelacion) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT cr FROM CatRelacion cr ").
                append("INNER JOIN cr.catCategoriaRelacion ccr ").
                append("WHERE ccr = ").append(catCategoriaRelacion.getCatCategoriaRelacionId());
        Query query = super.getSession().createQuery(sb.toString());
        return query.list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CatRelacion> consultarParentescos() {
	     StringBuilder sb = new StringBuilder();
	        sb.append("SELECT cr FROM CatRelacion cr ").
	                append("WHERE cr.esParentesco = 1");
	        Query query = super.getSession().createQuery(sb.toString());
	        return query.list();	
	}
}
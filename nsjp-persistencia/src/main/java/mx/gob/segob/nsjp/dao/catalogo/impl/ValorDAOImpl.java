/**
 * Nombre del Programa : ValorDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de los metodos de acceso a datos de la entidad Valor
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
package mx.gob.segob.nsjp.dao.catalogo.impl;

import java.util.List;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementacion de los metodos de acceso a datos de la entidad Valor.
 * 
 * @version 1.0
 * @author cesar
 * 
 */
@Repository
public class ValorDAOImpl extends GenericDaoHibernateImpl<Valor, Long>
		implements ValorDAO {

	private final static Long COMPLEJIDAD = 122L;

	@Override
	public Valor consultarComplejidadTipoAudiencia(Valor tipo) {
		StringBuffer queryString = new StringBuffer();

		queryString
				.append("SELECT v ")
				.append(" FROM Valor v")
				.append(" WHERE v.registro.registroId="
						+ tipo.getRegistro().getRegistroId())
				.append(" AND v.campoCatalogo=" + COMPLEJIDAD);

		Query query = super.getSession().createQuery(queryString.toString());
		
		return (Valor) query.uniqueResult();
	}
	
	@Override
	public int actualizarComplejidadTipoAudiencia(Long registroId, Long complejidad) {
		StringBuffer queryString = new StringBuffer();
		
		queryString
				.append(" UPDATE Valor v SET v.valor = :complejidad")
				.append(" WHERE v.registro.registroId=:registroId")
				.append(" AND v.campoCatalogo= 122");
		
		Query query = super.getSession().createQuery(queryString.toString());
			query.setString("registroId", ""+registroId);
			query.setString("complejidad", ""+complejidad);
		
		return query.executeUpdate();
	}

    @SuppressWarnings("unchecked")
	@Override
    public List<Valor> existeNombreValor(String valor) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT v from Valor v ").
                append("WHERE v.valor = :valor");
        Query q = getSession().createQuery(sb.toString());
        q.setParameter("valor", valor);
        return q.list();
    }

    @Override
    public List<String> obtenerNombresColumnas(Long idCat) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT nombreCampo from CampoCatalogo v ").
                append("WHERE v.catalogo.catalogoId = :idCat");
        Query q = getSession().createQuery(sb.toString());
        q.setParameter("idCat", idCat);
        return q.list();
    }

    @Override
    public Long getNext() {
        StringBuffer queryString = new StringBuffer();

        queryString
                .append("SELECT MAX(v.valorId) ")
                .append(" FROM Valor v");

        Query query = super.getSession().createQuery(queryString.toString());
        Long max = (Long) query.uniqueResult();
        if (max == null || max.longValue()==0){
            return 1L;
        }
        return ++max;
    }

    
    
}

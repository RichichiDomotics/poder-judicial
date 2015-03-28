/**
 * 
 */
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.CartaCumplimientoDAO;
import mx.gob.segob.nsjp.model.CartaCumplimiento;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author adrian
 *
 */
@Repository
public class CartaCumplimientoDAOImpl extends GenericDaoHibernateImpl<CartaCumplimiento, Long>
		implements CartaCumplimientoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CartaCumplimiento> consultarControversiasResueltas() {
StringBuffer queryString = new StringBuffer();
		
//		queryString.append("SELECT new CartaCumplimiento(d.documentoId, d.tipoDocumento,")
//		.append(" d.archivoDigital, d.nombreDocumento,")
//		.append(" d.fechaCreacion, d.responsableDocumento,")
//		.append(" d.actividad, d.esLeido, d.fechaLectura, d.juezLectura)");
		queryString.append(" FROM CartaCumplimiento d");
		queryString.append(" WHERE d.tipoDocumento=" + TipoDocumento.CARTA_DE_CUMPLIMIENTO_DE_ACUERDO.getValorId());
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}


}

/**
 * Nombre del Programa : DictamenDAOImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Jul 2011
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
package mx.gob.segob.nsjp.dao.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.documento.DictamenDAO;
import mx.gob.segob.nsjp.model.Dictamen;
import mx.gob.segob.nsjp.model.Funcionario;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementación del dao para Dictamen.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Repository
public class DictamenDAOImpl extends GenericDaoHibernateImpl<Dictamen, Long>
        implements
            DictamenDAO {
	@SuppressWarnings("unchecked")
    @Override
    public Long existeDocumentoDefinitivo(Long idSolicitudPericial) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("SELECT d.documentoId FROM Dictamen d ");
        queryString.append("WHERE d.solicitudPericial.documentoId=");
        queryString.append(idSolicitudPericial);
        queryString.append(" AND d.archivoDigital is not null");
        queryString.append(" AND d.textoParcial is null");
        logger.debug("queryString :: " + queryString);
        Query query = super.getSession().createQuery(queryString.toString());
        List<Long> temp = query.list();
        if (temp != null && !temp.isEmpty()) {
            return temp.get(0);
        }
        return null;
    }
   /*
    * (non-Javadoc)
    * @see mx.gob.segob.nsjp.dao.documento.DictamenDAO#consultarDictamenesRecibidosParaUsuario(mx.gob.segob.nsjp.comun.enums.forma.Formas, mx.gob.segob.nsjp.model.Funcionario)
    */
	@SuppressWarnings("unchecked")
	@Override
	public List<Dictamen> consultarDictamenesRecibidosParaUsuario(
			Formas tipoDocumento, Funcionario funcionario) {
		return getHibernateTemplate().find("from Dictamen d where d.archivoDigital is not null and " +
				" d.forma.formaId = ? order by d.fechaCreacion asc",tipoDocumento.getValorId());
	}
}

/**
* Nombre del Programa : MedidaAdjuntosDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion para el objeto de acceso a datos de la Medida Alterna
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
package mx.gob.segob.nsjp.dao.medida.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.medida.MedidaAdjuntosDAO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.MedidaAdjuntos;
import mx.gob.segob.nsjp.model.MedidaAdjuntosId;

/**
 * Implementacion para el objeto de acceso a datos de la Medida Alterna.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class MedidaAdjuntosDAOImpl extends GenericDaoHibernateImpl<MedidaAdjuntos, MedidaAdjuntosId>
		implements MedidaAdjuntosDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<ArchivoDigital> consultarArchivosDigitalesPorMedida(
			Long medidaId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT ma.archivoDigital FROM MedidaAdjuntos ma")
					.append(" WHERE ma.medida=").append(medidaId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	
}

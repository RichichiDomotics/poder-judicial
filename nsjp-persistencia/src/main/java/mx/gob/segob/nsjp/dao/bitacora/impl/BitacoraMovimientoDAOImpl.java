/**
* Nombre del Programa : BitacoraMovimientoDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
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
package mx.gob.segob.nsjp.dao.bitacora.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraMovimientoDAO;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository("bitacoraMovimientoDAO")
public class BitacoraMovimientoDAOImpl extends GenericDaoHibernateImpl<BitacoraMovimiento, Long>
		implements BitacoraMovimientoDAO {

	@SuppressWarnings("unchecked")
	public List<BitacoraMovimiento> consultarBitacoraMovimientoPorFiltro(
			String numeroExpediente, Long idCategoriaElemento ){
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" FROM BitacoraMovimiento BM ")
					.append(" WHERE 1=1 ");
		if(numeroExpediente!= null && !numeroExpediente.trim().isEmpty() )
			queryString.append(" AND BM.numeroExpediente.numeroExpediente LIKE '%")
					.append( numeroExpediente ).append("%' ");
		
		if(idCategoriaElemento!= null && idCategoriaElemento>0)
			queryString.append(" AND BM.categoriaElemento.categoriaElementoId =  ")
						.append(idCategoriaElemento);
		
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}
}

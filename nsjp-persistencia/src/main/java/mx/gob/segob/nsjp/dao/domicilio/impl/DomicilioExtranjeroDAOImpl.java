/**
* Nombre del Programa : DomicilioExtranjeroDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de accedo a datos para la tabla de entidad {@link DomicilioExtranjero}
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
package mx.gob.segob.nsjp.dao.domicilio.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioExtranjeroDAO;
import mx.gob.segob.nsjp.model.DomicilioExtranjero;

/**
 * Objeto de accedo a datos para la tabla de entidad {@link DomicilioExtranjero}.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class DomicilioExtranjeroDAOImpl extends
		GenericDaoHibernateImpl<DomicilioExtranjero, Long> implements
		DomicilioExtranjeroDAO {

}

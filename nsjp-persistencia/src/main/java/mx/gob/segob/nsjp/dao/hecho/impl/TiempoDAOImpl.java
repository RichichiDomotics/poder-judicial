/**
* Nombre del Programa : TiempoDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad Tiempo
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
package mx.gob.segob.nsjp.dao.hecho.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.hecho.TiempoDAO;
import mx.gob.segob.nsjp.model.Tiempo;

/**
 * Implementacion de metodos de acceso a datos de la entidad Tiempo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class TiempoDAOImpl extends GenericDaoHibernateImpl<Tiempo, Long> implements
		TiempoDAO {

}

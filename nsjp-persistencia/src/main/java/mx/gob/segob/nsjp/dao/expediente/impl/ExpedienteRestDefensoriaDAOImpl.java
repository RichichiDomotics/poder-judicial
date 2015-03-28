/**
* Nombre del Programa : ExpedienteRestDefensoriaDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos para la entidad ExpedienteRestDefensoria
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
package mx.gob.segob.nsjp.dao.expediente.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteRestDefensoriaDAO;
import mx.gob.segob.nsjp.model.ExpedienteRestDefensoria;

/**
 * Implementacion de metodos de acceso a datos para la entidad ExpedienteRestDefensoria.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class ExpedienteRestDefensoriaDAOImpl extends
		GenericDaoHibernateImpl<ExpedienteRestDefensoria, Long> implements
		ExpedienteRestDefensoriaDAO {

}

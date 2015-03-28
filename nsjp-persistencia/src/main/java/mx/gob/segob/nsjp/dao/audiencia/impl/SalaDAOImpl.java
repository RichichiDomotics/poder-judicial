/**
* Nombre del Programa : SalaDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/10/2011
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
package mx.gob.segob.nsjp.dao.audiencia.impl;

import mx.gob.segob.nsjp.dao.audiencia.SalaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.Sala;

import org.springframework.stereotype.Repository;

/**
 * Implementación para el acceso a datos de la entidad Sala
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class SalaDAOImpl  extends
	GenericDaoHibernateImpl<Sala, Long> implements SalaDAO {
	

}

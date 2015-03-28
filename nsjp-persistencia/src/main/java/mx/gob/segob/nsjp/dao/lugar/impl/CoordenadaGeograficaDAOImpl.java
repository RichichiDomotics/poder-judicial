/**
* Nombre del Programa : CoordenadaGeograficaDAOImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion de metodos de acceso a datos de la entidad CoordenadaGeografica
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
package mx.gob.segob.nsjp.dao.lugar.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.lugar.CoordenadaGeograficaDAO;
import mx.gob.segob.nsjp.model.CoordenadaGeografica;

/**
 * Implementacion de metodos de acceso a datos de la entidad CoordenadaGeografica.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Repository
public class CoordenadaGeograficaDAOImpl extends
		GenericDaoHibernateImpl<CoordenadaGeografica, Long> implements
		CoordenadaGeograficaDAO {

}

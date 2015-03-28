/**
* Nombre del Programa : RelacionPruebaInvolucradoDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/09/2011
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
package mx.gob.segob.nsjp.dao.prueba.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.prueba.RelacionPruebaInvolucradoDAO;
import mx.gob.segob.nsjp.model.RelacionPruebaInvolucrado;

/**
 * Implementación de los métodos de accesos a datos de la entidad de Relacion Prueba con Involucrado.
 * @version 1.0
 * @author GustavoBP
 */
@Repository
public class RelacionPruebaInvolucradoDAOImpl extends GenericDaoHibernateImpl<RelacionPruebaInvolucrado, Long> implements
		RelacionPruebaInvolucradoDAO {

}

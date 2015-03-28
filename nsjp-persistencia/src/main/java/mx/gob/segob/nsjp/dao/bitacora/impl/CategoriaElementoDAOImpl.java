/**
* Nombre del Programa : CategoriaElementoDAOImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del DAO para Categoria Elemento.
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

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.bitacora.CategoriaElementoDAO;
import mx.gob.segob.nsjp.model.CategoriaElemento;

/**
 * Implementación del DAO para Categoria Elemento.
 * @version 1.0
 * @author GustavoBP
 */
@Repository
public class CategoriaElementoDAOImpl extends GenericDaoHibernateImpl<CategoriaElemento, Long>
		implements CategoriaElementoDAO {

}

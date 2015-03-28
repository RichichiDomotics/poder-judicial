/**
* Nombre del Programa : AliasInvolucradoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de la entidad AliasInvolucrado
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
package mx.gob.segob.nsjp.dao.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AliasInvolucrado;

/**
 * Contrato para los metodos de acceso a datos de la entidad AliasInvolucrado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AliasInvolucradoDAO extends GenericDao<AliasInvolucrado, Long> {

	/**
	 * Obtiene los alias de un involucrado, del cual se recibe el identificador para realizar la consulta
	 * @param elementoId
	 * @return
	 */
	List<AliasInvolucrado> consultarAliasByInvolucrado(Long elementoId);

}

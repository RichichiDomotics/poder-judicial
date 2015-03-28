/**
* Nombre del Programa : InvolucradoOcupacionDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad InvolucradoOcupacion
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
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;

/**
 * Contrato de metodos de acceso a datos para la entidad InvolucradoOcupacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface InvolucradoOcupacionDAO extends
		GenericDao<InvolucradoOcupacion, Long> {

	/**
	 * 
	 * @param involucradoId
	 * @return
	 */
	public List<InvolucradoOcupacion> consultarOcupacionByInvolucrado(Long involucradoId);
	
}

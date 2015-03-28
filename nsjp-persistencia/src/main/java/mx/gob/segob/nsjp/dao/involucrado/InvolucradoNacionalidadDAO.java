/**
* Nombre del Programa : InvolucradoNacionalidadDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos para el acceso a datos de la entidad InvolucradoNacionalidad
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
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;

/**
 * Contrato de metodos para el acceso a datos de la entidad InvolucradoNacionalidad.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface InvolucradoNacionalidadDAO extends
		GenericDao<InvolucradoNacionalidad, Long> {

	/**
	 * 
	 * @param involucradoId
	 * @return
	 */
	public List<InvolucradoNacionalidad> consultarNacionalidadByInvolucrado (
			Long involucradoId);
}

/**
* Nombre del Programa : HechoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad Hecho
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
package mx.gob.segob.nsjp.dao.hecho;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Hecho;

/**
 * Contrato de metodos de acceso a datos de la entidad Hecho.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface HechoDAO extends GenericDao<Hecho, Long> {

	/**
	 * Consulta los hechos para obtener aquellos relacionados al expedinte enviado como parametro
	 * @param expedienteId
	 * @return lista de hechos asociados al expediente
	 */
	Hecho consultarHechoByExpediente(Long expedienteId);

	/**
	 * Consulta los hechos segun criterios (Expediente, Tiempo, Lugar)
	 * @param hecho
	 * @return
	 */
	List<Hecho> consultarHechos(Hecho hecho);

}

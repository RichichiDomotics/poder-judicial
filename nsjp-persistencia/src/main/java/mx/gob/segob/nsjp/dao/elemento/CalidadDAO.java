/**
* Nombre del Programa : CalidadDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de la entidad Calidad
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
package mx.gob.segob.nsjp.dao.elemento;

import java.util.List;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Calidad;

/**
 * Contrato para los metodos de acceso a datos de la entidad Calidad.
 * @version 1.0
 * @author cesar
 *
 */
public interface CalidadDAO extends GenericDao<Calidad, Long> {

    List<Calidad> consultarElementoXActividad(Long idActividad);

}

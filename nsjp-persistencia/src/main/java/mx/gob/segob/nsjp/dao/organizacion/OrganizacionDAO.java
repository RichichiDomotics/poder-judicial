/**
* Nombre del Programa : OrganizacionDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de Organizacion
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
package mx.gob.segob.nsjp.dao.organizacion;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Organizacion;

/**
 * Contrato para los metodos de acceso a datos de Organizacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface OrganizacionDAO extends GenericDao<Organizacion, Long> {
	Organizacion obtenerOrganizacionByRelacion(Long elementoId, Long catRelacionId);

}

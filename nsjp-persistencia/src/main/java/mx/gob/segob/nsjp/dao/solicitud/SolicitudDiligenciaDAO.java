/**
* Nombre del Programa : SolicitudDiligenciaDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos para el acceso a datos de la Entidad SolicitudDiligencia
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
package mx.gob.segob.nsjp.dao.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;

/**
 * Contrato de metodos para el acceso a datos de la Entidad SolicitudDiligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitudDiligenciaDAO extends
		GenericDao<SolicitudDiligencia, Long> {

	/**
	 * 
	 * @param expedienteId
	 * @return
	 */
	List<SolicitudDiligencia> consultarDiligenciasDelExpediente(
			Long expedienteId);

}

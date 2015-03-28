/**
* Nombre del Programa : AvisoDetencionDAO.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del DAO para avisos de detencion
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
package mx.gob.segob.nsjp.dao.avisodetencion;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AvisoDetencion;

/**
 * Contrato del DAO para avisos de detencion
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface AvisoDetencionDAO extends GenericDao<AvisoDetencion, Long> {
	
	/**
	 * Servicio para obtener avisos de detencion por un estatus
	 * @param estatus
	 * @return List<AvisoDetencion>
	 */
	
	List<AvisoDetencion> obtenerAvisosDetencionPorEstatus(Long estatus);

	/**
	 * 
	 * @param time
	 * @param valorId
	 * @return
	 */
	List<AvisoDetencion> consultarAvisosDetencionHistoricoByEstatus(Date fechHistorico, Long estatusAviso);
	/**
	 * Consulta los avisos por expediente.
	 * @param expedienteId
	 * @return
	 */
	 List<AvisoDetencion> consultarAvisosDetencionPorExpediente(Long expedienteId);
}

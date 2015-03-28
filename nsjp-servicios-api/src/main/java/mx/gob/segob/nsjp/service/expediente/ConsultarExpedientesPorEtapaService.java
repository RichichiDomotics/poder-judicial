/**
* Nombre del Programa : ConsultarExpedientesPorEtapaService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de Consultar Expedientes por etapa
* en base al area y al usuario logueado en el sistema
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
package mx.gob.segob.nsjp.service.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del servicio de Consultar Expedientes por etapa
 * en base al area y al usuario logueado en el sistema
 * @version 1.0
 * @author rgama
 *
 */
public interface ConsultarExpedientesPorEtapaService {
	
	/**
     * Permite filtrar los Expediente en base a:
     * @param etapa Permite filtrar  por las difierentes etapas que puede tener un expediente
     * @param usuarioId Permite filtrar los expedientes designados a un defensor
     * @param areaId Representa el area a la que esta asociada el expediente
     * @return List<Expediente>
     */
    public List<ExpedienteDTO> consultarExpedientesPorEtapa(EtapasExpediente etapa, Long usuarioId,Long areaId) throws NSJPNegocioException;

	/**
	 * Consulta la etapa en la que se encuentra el Expediente identificado por <code>idExpediente</code>
	 * @param numeroExpedienteId identificador del expediente del cual se quiere conocer la etapa.
	 * @return Etapa actual en la que se encuentra el expediente
	 * @throws NSJPNegocioException en caso de que el expediente no pueda cambiar de etapa
	 */
	public EtapasExpediente consultarEtapaDelExpediente(Long numeroExpedienteId) throws NSJPNegocioException;
	
	/**
	 * Cambia la etapa del expediente identificado por <code>numeroExpedienteId</code> por <code>etapa</code>
	 * @param numeroExpedienteId expediente al cual se desea cambiar la etapa
	 * @param etapa nueva etapa del expediente
	 * @throws NSJPNegocioException
	 */
	public void cambiarEtapaDelExpediente(Long numeroExpedienteId, EtapasExpediente etapa) throws NSJPNegocioException;
}

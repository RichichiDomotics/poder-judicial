/**
 * Nombre del Programa : ActualizarEstatusAlertaService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 02 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para la actualizacio del estatus de una Alerta
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
package mx.gob.segob.nsjp.service.alerta;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;

/**
 * Contrato para la actualizacio del estatus de una Alerta
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public interface ActualizarEstatusAlertaService {
    /**
     * Actualiza una solicitud al estatus recibido.
     * 
     * @param alerta.alertaId: Representa el id de la Alerta a actualizar
     * @param alerta.fechaAlerta: Representa la fecha de la Alerta a actualizar
     * @param estatusAlerta: Representa el nuevo estatus
     * @throws NSJPNegocioException
     */

	 public void actualizaEstatusyFechaAlerta(AlertaDTO alerta,
	    		EstatusAlarmaAlerta estatusAlerta) throws NSJPNegocioException;
}

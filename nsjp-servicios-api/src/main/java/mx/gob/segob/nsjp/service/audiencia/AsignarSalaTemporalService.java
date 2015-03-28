/**
 * Nombre del Programa : AsignarSalaTemporal.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio para asigna la sala temporal
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * Servicio para asigna la sala temporal.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AsignarSalaTemporalService {
    /**
     * Asigna la sala temporal a la audiencia, los pasos que ejecuta son:
     * <ol>
     * <li>Registra la sala temporal</li>
     * <li>Hace la asociación de la audiencia con la sala temporal</li>
     * <li>Asigna la fecha y hora de la audiencia</li>
     * </ol>
     * 
     * @param audConSala
     *            <code>sala</code> con los siguientes valores de la sala:
     *            <ul>
     *            <li>domicilioSala</li>
     *            <li>ubicacionSala</li>
     *            <li>motivo</li>
     *            </ul>
     *            Los valores de audiencia:
     *            <ul>
     *            <li>id</li>
     *            <li>fechaEvento</li>
     *            <li>duracionEstimada</li>
     *            </ul>
     * @throws NSJPNegocioException en caso de error.
     */
    void asignarSalaTemporal(AudienciaDTO audConSala)
            throws NSJPNegocioException;
}

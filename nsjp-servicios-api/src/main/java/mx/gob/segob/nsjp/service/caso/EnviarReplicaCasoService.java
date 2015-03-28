/**
* Nombre del Programa : ReplicarCasoService.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para replicar al caso en las demás intituciones
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
package mx.gob.segob.nsjp.service.caso;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Servicio para enviar la replica del caso a las demás intituciones.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface EnviarReplicaCasoService {
    /**
     * Método para enviar la replica del caso a las demás intituciones.
     * @param expdienteConCaso
     * @throws NSJPNegocioException
     */
    void enviarReplicaCaso(ExpedienteDTO expdienteConCaso) throws NSJPNegocioException;
    
    /**
     * Servicio que cambia la bandera indicando que el expediente fue replicado.
     * 
     * @param idExpediente
     * @throws NSJPNegocioException
     */
    void actualizarExpedienteReplicado(Long idExpediente) throws NSJPNegocioException;
}

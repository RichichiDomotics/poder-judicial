/**
 * Nombre del Programa : ClonarExpedienteService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Nov 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio para clonar el expediene de defensoria al llegar el segundo defendido
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Servicio para clonar el expediene de defensoria al llegar el segundo
 * defendido.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ClonarExpedienteService {
    /**
     * Clonara un expediente con todos sus elementos.
     * 
     * @param idExpedienteOriginal
     * @return
     * @throws NSJPNegocioException
     */
    ExpedienteDTO clonarExpediente(Long idExpedienteOriginal)
            throws NSJPNegocioException;
}

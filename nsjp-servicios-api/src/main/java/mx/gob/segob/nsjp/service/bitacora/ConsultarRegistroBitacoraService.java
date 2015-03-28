/**
 * Nombre del Programa : ConsultarRegustroBitacoraService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.bitacora;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.bitacora.RegistroBitacoraDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ConsultarRegistroBitacoraService {
    /**
     * 
     * @param expInput
     *            requerido <b>numeroExpedienteId</b>.
     * @return
     * @throws NSJPNegocioException
     */
    List<RegistroBitacoraDTO> consultarRegistrosByExpediente(
            ExpedienteDTO expInput) throws NSJPNegocioException;
    
    List<EtapasExpediente> consultarEtapasPasadas(ExpedienteDTO input) throws NSJPNegocioException;
}

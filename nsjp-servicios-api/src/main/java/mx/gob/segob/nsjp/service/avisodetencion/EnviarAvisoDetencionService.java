/**
 * Nombre del Programa : EnviarAvisoDetencionService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio par aenviar el aviso de detención
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
package mx.gob.segob.nsjp.service.avisodetencion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;

/**
 * Servicio par aenviar el aviso de detención.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface EnviarAvisoDetencionService {
    /**
     * Registra en BD el aviso de detención y lo envía por WS a defensoria.
     * 
     * @param input
     *            <ul>
     *            <li>involucradoDTO.elementoId</li>
     *            <li>involucradoDTO.nombresDemograficoDTO</li>
     *            <li>involucradoDTO.expedienteDTO.casoDTO.numeroGeneralCaso</li>
     *            <li>usuario.areaActual</li>
     *            <li>fechaInicioDetencion</li>
     *            <li>fechaRecepcionDetencion</li>
     *            <li></li>
     *            </ul>
     * @throws NSJPNegocioException
     */
    void enviarAvisoDetencion(DetencionDTO input,Long idAgencia, String claveAgencia, Long idFuncionarioSolicitante) throws NSJPNegocioException;
}

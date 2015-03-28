/**
 * Nombre del Programa : RevisarAvisoHDelictivoService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para recibir el aviso de hecho delictivo
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoWSDTO;

/**
 * Contrato para recibir el aviso de hecho delictivo.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface RecibirAvisoHDelictivoService {
    /**
     * Método a exponerse vía web service para recibir el aviso de hecho delictivo.
     * @param aviso
     * @throws NSJPNegocioException
     */
    void recibirAvisoHDelictivoService(AvisoHechoDelictivoWSDTO aviso)
            throws NSJPNegocioException;

}

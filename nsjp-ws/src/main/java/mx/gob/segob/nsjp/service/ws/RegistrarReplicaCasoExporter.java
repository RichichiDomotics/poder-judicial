/**
 * Nombre del Programa : RecibirReplicaCasoExporter.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Exporter para recibir la replica del caso.
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
package mx.gob.segob.nsjp.service.ws;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoWSDTO;

/**
 * Exporter para recibir la replica del caso.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService
public interface RegistrarReplicaCasoExporter {
    /**
     * Recibe la información de la replica del caso.
     * 
     * @param casoWs
     * @throws NSJPNegocioException
     */
    void registrarReplicaCaso(CasoWSDTO casoWs) throws NSJPNegocioException;

}

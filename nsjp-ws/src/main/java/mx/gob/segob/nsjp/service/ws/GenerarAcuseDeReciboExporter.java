/**
 * Nombre del Programa : GenerarAcuseDeReciboExporter.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 25 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Exporter del servicio que genera un acuse de recibo y lo asocia a la solicitud correspondiente
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

/**
 * Exporter del servicio que genera un acuse de recibo y lo asocia a la
 * solicitud correspondiente.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@WebService
public interface GenerarAcuseDeReciboExporter {
    /**
     * Servicio que guarda en base de datos el acuse de recibo de una solicitud. Si la solicitud
     * se origino localmente, lo almacenará en la base de datos local, de lo contrarío, envia 
     * una petición al sistem origen para almacenar el acuse de recibo y actualizar el estado
     * de la solicitud 
     * @param folio Folio de la solicitud que espera el Acuse de Recibo
     * @throws NSJPNegocioException
     */
    void generarAcuseDeRecibo(String folio) throws NSJPNegocioException;
}

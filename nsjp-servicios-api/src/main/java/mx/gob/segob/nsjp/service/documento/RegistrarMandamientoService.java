/**
 * Nombre del Programa : RegistrarMandamientoService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Sep 2011
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.MandamientoWSDTO;

/**
 * Servicio para registrar el mandamiento en SSP enviado por PJ.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface RegistrarMandamientoService {

    /**
     * Servicio para registrar el mandamiento en SSP enviado por PJ.
     * 
     * @param manda
     * @throws NSJPNegocioException
     */
    void registrarMandamiento(MandamientoWSDTO manda)
            throws NSJPNegocioException;

}

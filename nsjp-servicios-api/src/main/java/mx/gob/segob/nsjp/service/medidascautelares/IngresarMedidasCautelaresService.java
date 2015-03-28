/**
 * Nombre del Programa : IngresarMedidasCautelaresService.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del Service para ingresar medidas cautelares
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
package mx.gob.segob.nsjp.service.medidascautelares;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;

/**
 * Contrato del Service para ingresar medidas cautelares
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public interface IngresarMedidasCautelaresService {

    /**
     * 
     * @param medida
     * @return Long
     * @throws NSJPNegocioException
     */
    Long ingresarMedidaCautelar(MedidaCautelarDTO medida)
            throws NSJPNegocioException;
    /**
     * Desactiva la medida cautelar
     * @param medidaCautelar
     * @throws NSJPNegocioException
     */
    void desactivarMedidaCautelar(MedidaCautelarDTO medidaCautelar)
            throws NSJPNegocioException;
    /**
     * Realiza el envío de una medida cautelar al área de SSP
     * Se debe de contar ya con la medida en BD y su documento digital emitido
     * @param medidaId Medida a enviar
     */
    void enviarMedidaCautelarSSP(Long medidaId);

}

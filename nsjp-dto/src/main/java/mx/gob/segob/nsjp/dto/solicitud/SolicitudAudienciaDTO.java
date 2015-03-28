/**
 * Nombre del Programa : SolicitudAudienciaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia para la solicitud de la audiencia.
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
package mx.gob.segob.nsjp.dto.solicitud;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * Objeto de transferencia para la solicitud de la audiencia..
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class SolicitudAudienciaDTO extends SolicitudDTO {

    private AudienciaDTO audiencia;

    /**
     * Método de acceso al campo audiencia.
     * 
     * @return El valor del campo audiencia
     */
    public AudienciaDTO getAudiencia() {
        return audiencia;
    }

    /**
     * Asigna el valor al campo audiencia.
     * 
     * @param audiencia
     *            el valor audiencia a asignar
     */
    public void setAudiencia(AudienciaDTO audiencia) {
        this.audiencia = audiencia;
    }

}

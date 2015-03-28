/**
 * Nombre del Programa : SeguimientoMandamientoMedidaWSDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Sep 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para resolver de manera generica el seguimiento de medidas y mandamientos de SSP hacia PJ
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
package mx.gob.segob.nsjp.dto.documento;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO para resolver de manera generica el seguimiento de medidas y mandamientos
 * de SSP hacia PJ.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class SeguimientoMandamientoMedidaWSDTO extends GenericWSDTO {
    /**
     * Constante para indicar la actualizacion de un mandamiento.
     */
    public final static Long TIPO_OPERACION_MANDAMIENTO = 1L;
    /**
     * Constante para indicar la actualizacion de una medida cautelar.
     */
    public final static Long TIPO_OPERACION_MEDIDA_CAUTELAR = 2L;
    /**
     * Constante para indicar la actualizacion de una medida alterna.
     */
    public final static Long TIPO_OPERACION_MEDIDA_ALTERNA = 3L;
    
    public final static Long TIPO_OPERACION_SOLICITUD_TRANSCRIPCION = 4L;

    private String folioDocumento;
    private Long tipoOperacion;

    private ArchivoDigitalWSDTO archivoDigital;
    /**
     * Identificador del estatus de la medida
     */
    private Long idEstatus;
    /**
     * Método de acceso al campo folioDocumento.
     * 
     * @return El valor del campo folioDocumento
     */
    public String getFolioDocumento() {
        return folioDocumento;
    }
    /**
     * Asigna el valor al campo folioDocumento.
     * 
     * @param folioDocumento
     *            el valor folioDocumento a asignar
     */
    public void setFolioDocumento(String folioDocumento) {
        this.folioDocumento = folioDocumento;
    }
    /**
     * Método de acceso al campo archivoDigital.
     * 
     * @return El valor del campo archivoDigital
     */
    public ArchivoDigitalWSDTO getArchivoDigital() {
        return archivoDigital;
    }
    /**
     * Asigna el valor al campo archivoDigital.
     * 
     * @param archivoDigital
     *            el valor archivoDigital a asignar
     */
    public void setArchivoDigital(ArchivoDigitalWSDTO archivoDigital) {
        this.archivoDigital = archivoDigital;
    }
    /**
     * Método de acceso al campo idEstatus.
     * 
     * @return El valor del campo idEstatus
     */
    public Long getIdEstatus() {
        return idEstatus;
    }
    /**
     * Asigna el valor al campo idEstatus.
     * 
     * @param idEstatus
     *            el valor idEstatus a asignar
     */
    public void setIdEstatus(Long idEstatus) {
        this.idEstatus = idEstatus;
    }
    /**
     * Método de acceso al campo tipoOperacion.
     * 
     * @return El valor del campo tipoOperacion
     */
    public Long getTipoOperacion() {
        return tipoOperacion;
    }
    /**
     * Asigna el valor al campo tipoOperacion.
     * 
     * @param tipoOperacion
     *            el valor tipoOperacion a asignar
     */
    public void setTipoOperacion(Long tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
}

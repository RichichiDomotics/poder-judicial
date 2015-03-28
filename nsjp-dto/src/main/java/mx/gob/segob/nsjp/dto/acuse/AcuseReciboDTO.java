/**
 * Nombre del Programa : AcuseRecibo.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.dto.acuse;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
//TODO VAP eliminar clase
/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * @deprecated Usar el del paquete documento
 * 
 */
public class AcuseReciboDTO extends GenericDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6303663202822933134L;

    private Date fechaAcuse;
    private String nombreRecibe;
    private int folioAcuse;
    /**
     * Método de acceso al campo fechaAcuse.
     * 
     * @return El valor del campo fechaAcuse
     */
    public Date getFechaAcuse() {
        return fechaAcuse;
    }
    /**
     * Asigna el valor al campo fechaAcuse.
     * 
     * @param fechaAcuse
     *            el valor fechaAcuse a asignar
     */
    public void setFechaAcuse(Date fechaAcuse) {
        this.fechaAcuse = fechaAcuse;
    }
    /**
     * Método de acceso al campo nombreRecibe.
     * 
     * @return El valor del campo nombreRecibe
     */
    public String getNombreRecibe() {
        return nombreRecibe;
    }
    /**
     * Asigna el valor al campo nombreRecibe.
     * 
     * @param nombreRecibe
     *            el valor nombreRecibe a asignar
     */
    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }
    /**
     * Método de acceso al campo folioAcuse.
     * 
     * @return El valor del campo folioAcuse
     */
    public int getFolioAcuse() {
        return folioAcuse;
    }
    /**
     * Asigna el valor al campo folioAcuse.
     * 
     * @param folioAcuse
     *            el valor folioAcuse a asignar
     */
    public void setFolioAcuse(int folioAcuse) {
        this.folioAcuse = folioAcuse;
    }

}
